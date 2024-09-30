package org.example.hrsystem.controller;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.AdminSignUpRequestDto;
import org.example.hrsystem.dto.request.ApplicantSignUpRequestDto;
import org.example.hrsystem.dto.request.HrSpecialistRequestDto;
import org.example.hrsystem.dto.request.SignInRequestDto;
import org.example.hrsystem.dto.response.AuthenticationResponseDto;
import org.example.hrsystem.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/admin/sign-up")
    public AuthenticationResponseDto adminSignUp(
            @RequestBody AdminSignUpRequestDto adminSignUpRequestDto
    ) {
        return authenticationService.adminSignUp(adminSignUpRequestDto);
    }

    @PostMapping("/applicant/sign-up")
    public AuthenticationResponseDto applicantSignUp(
            @RequestBody ApplicantSignUpRequestDto applicantSignUpRequestDto
    ) {
        return authenticationService.applicantSignUp(applicantSignUpRequestDto);
    }

    @PostMapping("/hr/sign-up")
    public AuthenticationResponseDto hrSpecialistSignUp(
            @RequestBody HrSpecialistRequestDto hrSpecialistRequestDto
    ) {
        return authenticationService.hrSpecialistSignUp(hrSpecialistRequestDto);
    }

    @PostMapping("/sign-in")
    public AuthenticationResponseDto signIn(
            @RequestBody SignInRequestDto signInRequestDto
    ) {
        return authenticationService.signIn(signInRequestDto);
    }
}
