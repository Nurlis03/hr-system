package org.example.hrsystem.service;

import net.sf.jasperreports.engine.JRException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface JasperReportService {
    byte[] exportJasperReport(
            HttpServletResponse response,
            Long applicantId
    ) throws IOException, JRException;
}
