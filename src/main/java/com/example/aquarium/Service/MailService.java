package com.example.aquarium.Service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.resend.services.emails.model.CreateEmailOptions;

@Service
public class MailService {

    private final Resend resend;

    public MailService(@Value("${resend.api.key") String apiKey) {
        this.resend = new Resend(apiKey);
    }

    public String sendMail(String to, String subject, String htmlContent) {
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("MFPAquarium <notifications@mfp-aquarium.es>")
                .to(to)
                .subject(subject)
                .html(htmlContent)
                .build();

        System.out.println("Email sent to " + to);
        System.out.println("Email subject " + subject);
        System.out.println("Email html content " + htmlContent);

        try {
            CreateEmailResponse data = resend.emails().send(params);
            return data.getId();
        } catch (ResendException e) {
            e.printStackTrace();
            throw new RuntimeException("Error sending email", e);
        }
    }
}
