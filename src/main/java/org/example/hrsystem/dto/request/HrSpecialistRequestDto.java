package org.example.hrsystem.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.hrsystem.enums.Role;

@Getter
@Setter
public class HrSpecialistRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
