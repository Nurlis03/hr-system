package org.example.hrsystem.service;

import org.example.hrsystem.dto.request.AdminSignUpRequestDto;
import org.example.hrsystem.dto.request.ApplicantSignUpRequestDto;
import org.example.hrsystem.dto.request.HrSpecialistRequestDto;
import org.example.hrsystem.dto.request.SignInRequestDto;
import org.example.hrsystem.dto.response.AuthenticationResponseDto;

public interface AuthenticationService {
    AuthenticationResponseDto applicantSignUp(ApplicantSignUpRequestDto applicantSignUpRequestDto);
    AuthenticationResponseDto adminSignUp(AdminSignUpRequestDto adminSignUpRequestDto);
    AuthenticationResponseDto hrSpecialistSignUp(HrSpecialistRequestDto hrSpecialistRequestDto);
    AuthenticationResponseDto signIn(SignInRequestDto signInRequestDto);
}
