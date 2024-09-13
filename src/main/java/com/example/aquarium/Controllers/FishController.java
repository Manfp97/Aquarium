package com.example.aquarium.Controllers;

import com.example.aquarium.Entities.Fish;
import com.example.aquarium.Service.FishServi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/fish")
public class FishController {

    @Autowired
    private FishServi fishServi;

    @GetMapping

    public String listFish(Model model) {
        List<Fish> listFish = fishServi.SearchForEntities();
        model.addAttribute("listFish", listFish);
        return "fish";
    }

    @GetMapping("/id")
    public String showFish(@PathVariable Integer id, Model model) {
        Optional<Fish> fishOptional = fishServi.findById(id);
        if (fishOptional.isPresent()) {
            Fish fish = fishOptional.get();
            model.addAttribute("fish", fish);
            return "fish";
        } else {
            return "redirect:/fish";
        }
    }

    @PostMapping
    public String createFish(@RequestBody Fish fish) throws Exception {
        fishServi.keep(fish);
        return "redirect:/fish";
    }

    @DeleteMapping("/id")
    public String deleteFish(@PathVariable Integer id, Model model) {
        fishServi.deleteById(id);
        return "redirect:/fish";
    }
}
