package com.example.aquarium.Controllers;


import com.example.aquarium.Entities.User;
import com.example.aquarium.Repositories.UserRepository;
import com.example.aquarium.Service.EmailService;
import com.example.aquarium.Service.NotificationServiceEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Value("${app.reset-password-url}")
    private String resetPasswordBaseUrl;

    private final NotificationServiceEmail notificationServiceEmail;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    @Autowired
    public AuthController(NotificationServiceEmail notificationServiceEmail, UserRepository UserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService
            , BCryptPasswordEncoder BCryptPasswordEncoder) {
        this.notificationServiceEmail = notificationServiceEmail;
        this.userRepository = UserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password-form";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String mail, Model model) {
        System.out.println("Password reset request received for:" + mail);

        Optional<User> optionalUser = userRepository.findActiveUserByMail(mail);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            String token = java.util.UUID.randomUUID().toString();
            user.setResetToken(token);
            user.setTokenExpiration(LocalDateTime.now().plusHours(1));
            userRepository.save(user);

            String resetUrl = resetPasswordBaseUrl + "?token=" + token;

            try {
                System.out.println("Trying to send reset email to: " + mail);
                notificationServiceEmail.sendPasswordResetEmail(mail, resetUrl);
                System.out.println("Email sent successfully to: " + mail);
                model.addAttribute("message", "Password reset link has been sent");
            } catch (Exception e) {
                System.err.println("Error sending password reset mail: " + e.getMessage());
                e.printStackTrace();
                model.addAttribute("message", "Error sending password reset mail, please try again later ");
            }
        } else {
            System.out.println("Not found any active user with the mail: " + mail);
            model.addAttribute("error", "Not found any account associated with this mail");
        }

        return "forgot-password-confirmation";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        Optional<User> optionalUser = userRepository.findByResetToken(token);

        if (optionalUser.isPresent()) {
            model.addAttribute("token", token);
            return "reset-password-form";
        } else {
            model.addAttribute("error", "Invalid token");
            return "error";
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String newPassword,
                                Model model) {
        Optional<User> optionalUser = userRepository.findByResetToken(token);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getTokenExpiration().isBefore(LocalDateTime.now())) {
                model.addAttribute("error", "Token is expired");
                return "error";
            }

            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            user.setResetToken(null);
            user.setTokenExpiration(null);
            userRepository.save(user);

            model.addAttribute("message", "Your password has been reset");
            return "reset-password-success";
        } else {
            model.addAttribute("error", "Invalid token");
            return "error";
        }
    }
}

