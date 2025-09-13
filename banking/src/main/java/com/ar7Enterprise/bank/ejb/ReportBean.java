package com.ar7Enterprise.bank.ejb;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.annotation.Secured;
import com.ar7Enterprise.bank.core.model.Transaction;
import com.ar7Enterprise.bank.core.model.User;
import com.ar7Enterprise.bank.core.service.ReportService;
import com.ar7Enterprise.bank.core.service.TransactionService;
import com.ar7Enterprise.bank.core.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Stateless
@Logged
public class ReportBean implements ReportService {

    @EJB
    private UserService userService;
    @EJB
    private TransactionService transactionService;

    @Override
    @Secured(roles = {"USER", "ADMIN", "SUPER_ADMIN"})
    public byte[] generateMonthlyTransactionReport(String email) {
        User user = userService.getUserByEmail(email);
        LocalDateTime from = LocalDateTime.now().minusDays(30);
        List<Transaction> transactions = transactionService.getUserTransactionsInPeriod(user, from, LocalDateTime.now());

        return generatePDF(transactions, user);
    }

    @Override
    public byte[] generatePDF(List<Transaction> transactions, User user) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("Monthly Report for " + user.getName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("Date: " + LocalDate.now().toString()));
            document.add(new Paragraph(" "));

            for (Transaction t : transactions) {
                String transfer = t.getFrom().equals(user) ? "Sent to " : "Received from ";
                String receiver = t.getFrom().equals(user) ? t.getTo().getEmail() : t.getFrom().getEmail();
                String line = String.format("%s %.2f - %s%s on %s",
                        t.getType(), t.getAmount(), transfer, receiver, t.getCreatedAt().toString());
                document.add(new Paragraph(line));
            }

            document.close();
        } catch (Exception e) {
        }

        return out.toByteArray();
    }
}
