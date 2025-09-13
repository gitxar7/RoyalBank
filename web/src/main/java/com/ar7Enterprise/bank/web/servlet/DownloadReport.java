package com.ar7Enterprise.bank.web.servlet;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.service.ReportService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/download-report")
public class DownloadReport extends HttpServlet {
    @EJB
    private ReportService reportService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("user");
        if (email == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        byte[] pdfBytes = reportService.generateMonthlyTransactionReport(email);

        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=\"Monthly_Report.pdf\"");
        resp.getOutputStream().write(pdfBytes);
    }
}
