package org.example.hrsystem.service.impl;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.AdminSignUpRequestDto;
import org.example.hrsystem.dto.request.ApplicantSignUpRequestDto;
import org.example.hrsystem.dto.request.HrSpecialistRequestDto;
import org.example.hrsystem.dto.request.SignInRequestDto;
import org.example.hrsystem.dto.response.AuthenticationResponseDto;
import org.example.hrsystem.entity.Admin;
import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.HrSpecialist;
import org.example.hrsystem.entity.User;
import org.example.hrsystem.enums.Role;
import org.example.hrsystem.exception.UserAlreadyExistsException;
import org.example.hrsystem.mapper.AuthenticationMapper;
import org.example.hrsystem.repository.AdminRepository;
import org.example.hrsystem.repository.ApplicantRepository;
import org.example.hrsystem.repository.HrSpecialistRepository;
import org.example.hrsystem.repository.UserRepository;
import org.example.hrsystem.security.JwtService;
import org.example.hrsystem.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationMapper authenticationMapper;
    private final ApplicantRepository applicantRepository;
    private final AdminRepository adminRepository;
    private final HrSpecialistRepository hrSpecialistRepository;

    @Override
    public AuthenticationResponseDto applicantSignUp(ApplicantSignUpRequestDto applicantSignUpRequestDto) {
        if (userRepository.existsByEmail(applicantSignUpRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("User with email: " + applicantSignUpRequestDto.getEmail() + " already exists" );
        }
        Applicant applicant = authenticationMapper.mapToApplicant(applicantSignUpRequestDto);

        applicantRepository.save(applicant);

        String accessToken = jwtService.generateToken(applicant.getEmail());
        return AuthenticationResponseDto
                .builder()
                .accessToken(accessToken)
                .build();
    }

    @Override
    public AuthenticationResponseDto adminSignUp(AdminSignUpRequestDto adminSignUpRequestDto) {
        if (userRepository.existsByEmail(adminSignUpRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("User with email: " + adminSignUpRequestDto.getEmail() + " already exists" );
        }
        Admin admin = authenticationMapper.mapToAdmin(adminSignUpRequestDto);

        adminRepository.save(admin);

        String accessToken = jwtService.generateToken(admin.getEmail());
        return AuthenticationResponseDto
                .builder()
                .accessToken(accessToken)
                .build();
    }

    @Override
    public AuthenticationResponseDto hrSpecialistSignUp(HrSpecialistRequestDto hrSpecialistRequestDto) {
        if (userRepository.existsByEmail(hrSpecialistRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("User with email: " + hrSpecialistRequestDto.getEmail() + " already exists" );
        }
        HrSpecialist hrSpecialist = authenticationMapper.mapToHrSpecialist(hrSpecialistRequestDto);

        hrSpecialistRepository.save(hrSpecialist);

        String accessToken = jwtService.generateToken(hrSpecialist.getEmail());
        return AuthenticationResponseDto
                .builder()
                .accessToken(accessToken)
                .build();
    }

    @Override
    public AuthenticationResponseDto signIn(SignInRequestDto signInRequestDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.getEmail(),
                        signInRequestDto.getPassword()
                )
        );
        User user = userRepository.findByEmail(
                signInRequestDto.getEmail()
        ).orElseThrow(() -> new UsernameNotFoundException(
                "User not found with email: " + signInRequestDto.getEmail())
        );
        String accessToken = jwtService.generateToken(user.getEmail());
        return AuthenticationResponseDto
                .builder()
                .accessToken(accessToken)
                .build();
    }
}