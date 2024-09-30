package org.example.hrsystem.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class JobApplicationRequestDto {
    Long vacancyId;
    String coverLetter;
    MultipartFile resume;
}
