package org.example.hrsystem.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.hrsystem.enums.Role;

@Getter
@Setter
public class ApplicantSignUpRequestDto {
    private String firstName;
    private String lastName;
    private String addressRu;
    private String addressKg;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
}
