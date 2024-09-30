package org.example.hrsystem.service.impl;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.example.hrsystem.service.MinIOService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class MinIOServiceImpl implements MinIOService {
    private final MinioClient minioClient;

    private MinIOServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Value("${minio.bucket.name}")
    private String bucketName;

    @Override
    public String uploadFile(
            InputStream fileStream,
            String fileName
    ) throws URISyntaxException,
            IOException,
            ServerException,
            InsufficientDataException,
            ErrorResponseException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InvalidResponseException,
            XmlParserException,
            InternalException
    {
            String objectName = UUID.randomUUID() + "_" + fileName;
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(fileStream, fileStream.available(), -1)
                            .build()
            );
            return constructFileUrl(objectName);
    }

    @Override
    public void deleteFile(String objectName)
            throws ServerException,
            InsufficientDataException,
            ErrorResponseException,
            IOException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InvalidResponseException,
            XmlParserException,
            InternalException
    {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build()
        );
    }

    private String constructFileUrl(String objectName) throws URISyntaxException {
        URI uri = new URI( bucketName + "/" + objectName);
        return uri.toString();
    }
}
