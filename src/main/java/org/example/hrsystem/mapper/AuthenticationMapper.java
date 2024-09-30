package org.example.hrsystem.mapper;

import org.example.hrsystem.dto.request.AdminSignUpRequestDto;
import org.example.hrsystem.dto.request.ApplicantSignUpRequestDto;
import org.example.hrsystem.dto.request.HrSpecialistRequestDto;
import org.example.hrsystem.entity.Admin;
import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.HrSpecialist;
import org.example.hrsystem.entity.User;
import org.example.hrsystem.enums.Role;

public interface AuthenticationMapper {
    Applicant mapToApplicant(ApplicantSignUpRequestDto applicantSignUpRequestDto);

    Admin mapToAdmin(AdminSignUpRequestDto adminSignUpRequestDto);

    HrSpecialist mapToHrSpecialist(HrSpecialistRequestDto hrSpecialistRequestDto);
}
