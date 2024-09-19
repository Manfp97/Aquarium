package com.example.aquarium.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceEmail {

    private final EmailService emailService;

    @Autowired
    public NotificationServiceEmail(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendNotification(String userMail, String userName) {
        String subject = "Welcome to MFPAquarium, " + userName + "!";
        String body = createWelcomeEmailBody(userMail, userName);

        emailService.sendEmail(userMail, subject, body);
    }

    public void sendPasswordResetEmail(String mail, String resetUrl) {
        String subject = "Password reset email";
        String body = createPasswordResetEmailBody(mail, resetUrl);

        emailService.sendEmail(mail, subject, body);
    }

    private String createWelcomeEmailBody(String userMail, String userName) {
        return "<html>" +
                "<body style='font-family: Arial, sans-serif; color: #333; line-height: 1.6;'>" +
                "<h2 style='color: #4CAF50;'>Hello, " + userName + "!</h2>" +
                "<p>We are delighted to welcome you to <strong>MFPAquarium</strong>, your ideal place to find your ideal Aquarium.</p>" +
                "<p>Your account has been successfully created! Now you can access to all functionalities.</p>" +
                "<h3>What can you do next?</h3>" +
                "<ul>" +
                "<li><strong>Explore:</strong> Navigate through our platform and find your favourite aquarium.</li>" +
                "<li><strong>Personalise:</strong> Complete your profile and receive personalised recommendations.</li>" +
                "<li><strong>Start your journey:</strong> Start decorating your own fish tank.</li>" +
                "</ul>" +
                "<p>If you have any question of need help, please make sure to contact us.</p>" +
                "<p style='margin-bottom: 30px;'>We hope you enjoy everything MFPAquariums has to offer you!</p>" +
                "<p style='font-size: 14px; color: #777;'>Yours sincerely,<br/>" +
                "The MFPAquarium team</p>" +
                "<hr style='border: 0; height: 1px; background: #ddd; margin-top: 30px;'/>" +
                "<p style='font-size: 12px; color: #777;'>This mail was sent to " + userMail + ". If you do not recognise this activity, please contact us.</p>" +
                "</body>" +
                "</html>";
    }

    private String createPasswordResetEmailBody(String mail, String resetUrl) {
        return "<html>" +
                "<body style='font-family: Arial, sans-serif; color: #333; line-height: 1.6;'>" +
                "<h2 style='color: #4CAF50;'>Password reset</h2>" +
                "<p>You asked for a password reset. Click the following link to establish a new password:</p>" +
                "<p><a href='" + resetUrl + "' style='color: #4CAF50;'>Reset password</a></p>" +
                "<p>If you haven't requested this change, please ignore this mail.</p>" +
                "<p style='margin-bottom: 30px;'>Yours sincerely,<br/>" +
                "The MFPAquarium team</p>" +
                "<hr style='border: 0; height: 1px; background: #ddd; margin-top: 30px;'/>" +
                "<p style='font-size: 12px; color: #777;'>This mail was sent to " + mail + ". If you don't recognise this activity, please contact us as soon as possible.</p>" +
                "</body>" +
                "</html>";
    }
}
