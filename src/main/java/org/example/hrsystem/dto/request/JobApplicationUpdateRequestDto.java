package org.example.hrsystem.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class JobApplicationUpdateRequestDto {
    Long jobApplicationId;
    String coverLetter;
    MultipartFile resume;
}
