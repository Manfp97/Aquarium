package com.example.aquarium.Service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Resend resend;

    public EmailService(@Value("${resend.api.key}") String apiKey) {
        this.resend = new Resend(apiKey);
    }

    public String sendEmail(String to, String subject, String htmlContent) {
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("MFPAquariums <notificaciones@mfp-aquariums.es>")
                .to(to)
                .subject(subject)
                .html(htmlContent)
                .build();

        System.out.println("Sending mail to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("HTML Content: " + htmlContent);

        try{
            CreateEmailResponse data = resend.emails().send(params);
            return data.getId();
        } catch (ResendException e) {
            e.printStackTrace();
            throw new RuntimeException("Error sending mail", e);
        }

    }
}
