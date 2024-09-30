package org.example.hrsystem.mapper.impl;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.AdminSignUpRequestDto;
import org.example.hrsystem.dto.request.ApplicantSignUpRequestDto;
import org.example.hrsystem.dto.request.HrSpecialistRequestDto;
import org.example.hrsystem.entity.Admin;
import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.HrSpecialist;
import org.example.hrsystem.entity.User;
import org.example.hrsystem.enums.Role;
import org.example.hrsystem.mapper.AuthenticationMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationMapperImpl implements AuthenticationMapper {
    private final PasswordEncoder passwordEncoder;

    @Override
    public Applicant mapToApplicant(ApplicantSignUpRequestDto applicantSignUpRequestDto) {
        return Applicant
                .builder()
                .email(applicantSignUpRequestDto.getEmail())
                .password(passwordEncoder.encode(applicantSignUpRequestDto.getPassword()))
                .role(applicantSignUpRequestDto.getRole())
                .firstName(applicantSignUpRequestDto.getFirstName())
                .lastName(applicantSignUpRequestDto.getLastName())
                .addressKg(applicantSignUpRequestDto.getAddressKg())
                .addressRu(applicantSignUpRequestDto.getAddressRu())
                .phoneNumber(applicantSignUpRequestDto.getPhoneNumber())
                .build();
    }

    @Override
    public Admin mapToAdmin(AdminSignUpRequestDto adminSignUpRequestDto) {
        return Admin
                .builder()
                .email(adminSignUpRequestDto.getEmail())
                .password(passwordEncoder.encode(adminSignUpRequestDto.getPassword()))
                .role(adminSignUpRequestDto.getRole())
                .firstName(adminSignUpRequestDto.getFirstName())
                .lastName(adminSignUpRequestDto.getLastName())
                .build();
    }

    @Override
    public HrSpecialist mapToHrSpecialist(HrSpecialistRequestDto hrSpecialistRequestDto) {
        return HrSpecialist
                .builder()
                .email(hrSpecialistRequestDto.getEmail())
                .password(passwordEncoder.encode(hrSpecialistRequestDto.getPassword()))
                .role(hrSpecialistRequestDto.getRole())
                .firstName(hrSpecialistRequestDto.getFirstName())
                .lastName(hrSpecialistRequestDto.getLastName())
                .build();
    }
}
