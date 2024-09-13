package com.example.aquarium.Controllers;

import com.example.aquarium.Entities.DeliveryNote;
import com.example.aquarium.Service.DeliveryNoteServi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/deliverynote")
public class DeliveryNoteController {

    @Autowired
    private DeliveryNoteServi deliveryNoteServi;

    @GetMapping
    public String listDeliveryNote(Model model) {
        List<DeliveryNote> listDeliveryNote = deliveryNoteServi.SearchForEntities();
        model.addAttribute("deliverynote", listDeliveryNote);
        return "deliverynote";
    }

    @GetMapping("/id")
    public String showDeliveryNote(@PathVariable Integer id, Model model) {
        Optional<DeliveryNote> deliveryNoteOptional = deliveryNoteServi.findById(id);
        if (deliveryNoteOptional.isPresent()) {
            DeliveryNote deliveryNote = deliveryNoteOptional.get();
            model.addAttribute("deliverynote", deliveryNote);
            return "deliverynote";
        } else {
            return "redirect:/deliverynote";
        }
    }
}
