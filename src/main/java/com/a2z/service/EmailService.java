package com.a2z.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Chetanand Meher
 */

@Service
@RequiredArgsConstructor
public class EmailService {
    public final JavaMailSender mailSender;


    public void sendVerificationOtpEmail(String email, String otp, String subject, String body) throws MessagingException {
        // Implementation for sending email
        try{
            // Create a Simple Mail Message.
            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject(subject);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setText(body + "\n\nYour OTP is: " + otp);

            mailSender.send(message);
        }
        catch (MailException e){
            System.out.println("Error sending email: " + e.getMessage());
            throw new MailSendException("Failed to send email to " + email, e);
        }
    }

}
