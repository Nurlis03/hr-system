package org.example.hrsystem.service.impl;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.example.hrsystem.dto.response.AwardCertificationResponseDto;
import org.example.hrsystem.dto.response.EducationResponseDto;
import org.example.hrsystem.dto.response.WorkExperienceResponseDto;
import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.Skill;
import org.example.hrsystem.exception.NotFoundException;
import org.example.hrsystem.mapper.AwardCertificationMapper;
import org.example.hrsystem.mapper.EducationMapper;
import org.example.hrsystem.mapper.WorkExperienceMapper;
import org.example.hrsystem.repository.ApplicantRepository;
import org.example.hrsystem.service.JasperReportService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JasperReportServiceImpl implements JasperReportService {

    private final ApplicantRepository applicantRepository;
    private final EducationMapper educationMapper;
    private final WorkExperienceMapper workExperienceMapper;
    private final AwardCertificationMapper awardCertificationMapper;

    @Override
    public byte[] exportJasperReport(
            HttpServletResponse response,
            Long applicantId
    ) throws IOException, JRException {

        // Configure the response headers
        configureResponse(response);

        // Fetch the applicant or throw an error if not found
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new NotFoundException("Applicant with id " + applicantId + " not found"));

        // Load and compile the JRXML report
        File file = ResourceUtils.getFile("classpath:reports/resume.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Prepare data for the report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstName", applicant.getFirstName());
        parameters.put("lastName", applicant.getLastName());
        parameters.put("email", applicant.getEmail());
        parameters.put("phoneNumber", applicant.getPhoneNumber());
        parameters.put("addressRu", applicant.getAddressRu());

        // Convert lists to JRBeanCollectionDataSource and add them to the parameters
        List<EducationResponseDto> educationResponseDtoList = educationMapper.toEducationDto(applicant.getEducation());
        List<WorkExperienceResponseDto> workExperienceResponseDtoList = workExperienceMapper.toWorkExperienceResponseDto(applicant.getWorkExperience());
        List<AwardCertificationResponseDto> awardCertificationResponseDtoList = awardCertificationMapper.toAwardCertificationMapperResponseDto(applicant.getAwardsAndCertifications());
        List<Skill> skillList = applicant.getSkills();

        // Join all skill names into a single comma-separated string
        String skillsString = skillList.stream()
                .map(Skill::getSkillName) // Get the skillName of each Skill
                .filter(Objects::nonNull) // Filter out any null skill names
                .collect(Collectors.joining(", ")); // Join the skill names with ", " as the delimiter

        // Add the string of skill names to the parameters map
        parameters.put("skills", skillsString);

        // Add other data sources to the parameters
        parameters.put("educationDataSource", new JRBeanCollectionDataSource(educationResponseDtoList));
        parameters.put("workExperienceDataSource", new JRBeanCollectionDataSource(workExperienceResponseDtoList));
        parameters.put("awardCertificationDataSource", new JRBeanCollectionDataSource(awardCertificationResponseDtoList));

        // Fill the report with data and the parameters
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        // Return the generated PDF as a byte array
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private void configureResponse(HttpServletResponse response) {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + getNameOfReport() + ".pdf";
        response.setHeader(headerKey, headerValue);
    }

    private String getNameOfReport() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        return "resume_report_" + dateFormat.format(new Date());
    }
}