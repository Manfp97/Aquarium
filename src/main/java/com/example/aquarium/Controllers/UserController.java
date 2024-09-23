package com.example.aquarium.Controllers;

import com.example.aquarium.Entities.Roles;
import com.example.aquarium.Entities.User;
import com.example.aquarium.Entities.UserDetails;
import com.example.aquarium.Repositories.ProductRepository;
import com.example.aquarium.Repositories.RolesRepository;
import com.example.aquarium.Repositories.UserRepository;
import com.example.aquarium.Service.NotificationServiceEmail;
import com.example.aquarium.Service.ProductServi;
import com.example.aquarium.Service.RolesServi;
import com.example.aquarium.Service.UserServi;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServi service;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServi productServi;

    @Autowired
    private RolesServi rolesServi;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired

    private NotificationServiceEmail notificationServiceEmail;

    @GetMapping("/perfil/{id}")
    public String showProfileById(@PathVariable Integer id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        Optional<User> user = service.findById(id);

        if (user.isPresent()) {
            List<Roles> roles = rolesServi.getRepo().findAll();
            model.addAttribute("roles", roles);
            model.addAttribute("user", user.get());
            return "detailsuser";
        } else {
            return "redirect:/404";
        }
    }

    @PostMapping("/profile/{id}")
    public String updateProfile(@PathVariable Integer id, @ModelAttribute("user") User user
            , @RequestParam(value = "password", required = false) String newPassword,
                                Model model, RedirectAttributes redirectAttributes) {
        try {
            User existingUser = service.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

            UserDetails userDetails = existingUser.getUserDetails();
            if (userDetails == null) {
                userDetails = new UserDetails();
                userDetails.setUser(existingUser);
            }

            userDetails.setName(user.getUserDetails().getName());
            userDetails.setSurname(user.getUserDetails().getSurname());
            userDetails.setAddress(user.getUserDetails().getAddress());
            userDetails.setPassport(user.getUserDetails().getPassport());
            userDetails.setMail(user.getUserDetails().getMail());

            existingUser.setUserDetails(userDetails);

            if (newPassword != null && !newPassword.isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(newPassword));
            }

            service.keep(existingUser);

            redirectAttributes.addFlashAttribute("message", "Profile updated");
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "User not found");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating profile");
        }

        return "redirect:/user/profile/" + id;
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user, Model model) {
        try {
            if (user.getUserDetails() == null ||
                    user.getUserDetails().getName() == null ||
                    user.getUserDetails().getSurname() == null) {
                model.addAttribute("message", "Name and surname are required");
                model.addAttribute("roles", rolesServi.getRepo().findAll());
                return "createuser";
            }

            Roles rol = rolesRepository.findByNameRol("ROLE_USER");
            if (rol == null) {
                model.addAttribute("message", "Rol default not found");
                model.addAttribute("roles", rolesServi.getRepo().findAll());
                return "createuser";
            }
            user.setRol(rol);
            ;

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            service.keep(user);

            String userMail = user.getUserDetails().getMail();
            String userName = user.getUsername();

            notificationServiceEmail.sendNotification(userMail, userName);

            model.addAttribute("message", "User and details created");
            return "redirect:/user/new";
        } catch (Exception e) {
            model.addAttribute("message", "Error creating user and details");
            model.addAttribute("roles", rolesServi.getRepo().findAll());
            return "createuser";
        }
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute("user") User user, Model model) {
        try {
            User existingUser = service.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

            existingUser.setUsername(user.getUsername());

            if (!user.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            if (user.getUserDetails() != null) {
                UserDetails userDetails = existingUser.getUserDetails();
                if (userDetails == null) {
                    userDetails = new UserDetails();
                    userDetails.setUser(existingUser);
                }

                userDetails.setName(user.getUserDetails().getName());
                userDetails.setSurname(user.getUserDetails().getSurname());
                userDetails.setAddress(user.getUserDetails().getAddress());
                userDetails.setMail(user.getUserDetails().getMail());
                userDetails.setPassport(user.getUserDetails().getPassport());

                existingUser.setUserDetails(userDetails);
            }

            if (user.getRol() != null && user.getRol().getIdroles() != null) {
                Roles rol = rolesRepository.findById(user.getRol().getIdroles())
                        .orElseThrow(() -> new EntityNotFoundException("Rol not valid"));
                existingUser.setRol(rol);
            }

            service.keep(existingUser);
            model.addAttribute("message", "User updated successfully");
            return "redirect:/user/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("message", "User not found");
            return "redirect:/user/list";
        } catch (Exception e) {
            model.addAttribute("message", "Error updating user");
            return "redirect:/user/list";
        }

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return "redirect:/user7list";
        } catch (EntityNotFoundException e) {
            return "redirect:/404";
        }
    }


}
