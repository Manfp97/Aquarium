package com.example.aquarium.Controllers;


import com.example.aquarium.Entities.Product;
import com.example.aquarium.Repositories.ProductRepository;
import com.example.aquarium.Service.ProductServi;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServi service;
    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository, ProductServi service) {
        this.productRepository = productRepository;
        this.service = service;
    }

    @GetMapping
    public String listAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "9") int size,
            @RequestParam(required = false, defaultValue = "chondrictium") String category,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage;

        if (category != null && !category.isEmpty()) {
            productPage = productRepository.findByCategory(category, pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }

        if (productPage.isEmpty()) {
            return "error";
        } else {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, productPage.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("page", productPage);
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("products", productPage.getContent());
            model.addAttribute("category", category);
            return "product";
        }
    }

    @GetMapping("/id")
    public String getById(@PathVariable Integer id, Model model) {
        Optional<Product> product = service.findById(id);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "detailproduct";
        } else {
            return "redirect/404";
        }
    }

    @GetMapping("/new")
    public String showFormulary(Model model) {
        model.addAttribute("product", new Product());
        return "createproduct";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        try {
            service.keep(product);
            redirectAttributes.addFlashAttribute("message", "Product successfully created");
                    return "redirect:/product/new";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error creating the product");
                    return "redirect:/product/new";
        }
    }

    @PostMapping("/id")
    public String update(@PathVariable Integer id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        try {
            Optional<Product> existingProduct = service.findById(id);
            if (existingProduct.isPresent()) {
                Product updatedProduct = existingProduct.get();
                updatedProduct.setName(product.getName());
                updatedProduct.setPrice(product.getPrice());
                updatedProduct.setCategory(product.getCategory());
                updatedProduct.setType(product.getType());

                service.keep(updatedProduct);
                redirectAttributes.addFlashAttribute("message", "Product successfully updated");
                return "redirect:/product";
            } else {
                redirectAttributes.addFlashAttribute("message", "Product not found");
                return "redirect:/product";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error actualising product" + e.getMessage());
            return "redirect:/product";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return "redirect:/product";
        } catch (EntityNotFoundException e) {
            return "redirect:/404";
        }
    }
}
