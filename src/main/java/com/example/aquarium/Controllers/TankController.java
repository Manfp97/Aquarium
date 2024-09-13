package com.example.aquarium.Controllers;


import com.example.aquarium.Entities.Tank;
import com.example.aquarium.Repositories.TankRepository;
import com.example.aquarium.Service.TankServi;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/tank")
public class TankController {

    @Autowired
    private TankServi service;

    @Autowired
    private TankRepository TankRepository;
    @Autowired
    private TankRepository tankRepository;

    @GetMapping
    public String listAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "9") int size,
            @RequestParam(required = false, defaultValue = "sweetwater") String category,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tank> tankPage;

        if (category != null && !category.isEmpty()) {
            tankPage = tankRepository.findByCategory(category, pageable);
        } else {
            tankPage = tankRepository.findAll(pageable);
        }

        if (tankPage.isEmpty()) {
            return "tanknotfound";
        } else {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, tankPage.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("page", tankPage);
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("tanks", tankPage.getContent());
            model.addAttribute("category", category);

            return "tank";
        }
    }

    @GetMapping("/list")
    public String listAllEditable(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tank> tankPage = tankRepository.findAll(pageable);

        List<Integer> pageNumbers = IntStream.rangeClosed(1, tankPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("tankPage", tankPage);
        model.addAttribute("pageNumbers", pageNumbers);
        return "tank";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        Optional<Tank> tank = service.findById(id);

        if (tank.isPresent()) {
            model.addAttribute("tank", tank.get());
            return "tankdetails";
        } else {
            return "redirect:/404";
        }
    }

    @GetMapping("/seemore/{id}")
    public String getByIdSeeMore(@PathVariable Integer id, Model model) {
        Optional<Tank> tank = service.findById(id);

        if (tank.isPresent()) {
            model.addAttribute("tank", tank.get());
            return "tankdetails";
        } else {
            return "redirect:/404";
        }
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Tank tank, Model model) {
        try {
            Optional<Tank> existingTank = service.findById(id);
            if (existingTank.isPresent()) {
                Tank updatedTank = existingTank.get();
                updatedTank.setSize(tank.getSize());
                updatedTank.setCategory(tank.getCategory());
                updatedTank.setIdtank(tank.getIdtank());

                service.keep(updatedTank);
                model.addAttribute("message", "Tank updated successfully");
                return "redirect:/tank/list";
            } else {
                model.addAttribute("message", "Tank not found");
                return "redirect:/404";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error updating tank" + e.getMessage());
            return "redirect:/404";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return "redirect:/tank/list";
        } catch (EntityNotFoundException e) {
            return "redirect:/404";
        }
    }

    @GetMapping("/new")
    public String showFormNew(Model model) {
        model.addAttribute("tank", new Tank());
        return "createtank";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("tank") Tank tank, Model model) {
        try {
            service.keep(tank);
            model.addAttribute("message", "Tank created successfully");
            return "redirect:/tank/list";
        } catch (Exception e) {
            model.addAttribute("message", "Error creating tank");
            return "redirect:/404";
        }
    }
}
