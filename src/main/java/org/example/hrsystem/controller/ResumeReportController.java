package org.example.hrsystem.controller;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.example.hrsystem.service.JasperReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/reports")
@AllArgsConstructor
public class ResumeReportController {
    private final JasperReportService jasperReportService;

    @GetMapping("/jasper/applicant/{id}")
    public byte[] exportJasperReportBooks(
            HttpServletResponse response,
            @PathVariable Long id
    ) throws IOException, JRException {
        return jasperReportService.exportJasperReport(response, id);
    }
}
