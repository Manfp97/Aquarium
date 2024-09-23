package com.example.aquarium.Controllers;


import com.example.aquarium.Entities.Product;
import com.example.aquarium.Service.ProductServi;
import com.example.aquarium.Service.ShopServi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductServi productServi;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String SHOP_COOKIE_NAME = "shop";


    /**
    @GetMapping
    public String showShop(HttpServletRequest request, Model model) throws JsonProcessingException {
        Map<Integer, ProductShop> shop = obtainCookiesshop(request);

        double shopTotal = shop.values().stream()
                .mapToDouble(pc -> pc.getProduct().getPrice() * pc.getQuantity())
                .sum();

        model.addAttribute("shop", shop.values());
        model.addAttribute("shopTotal", shopTotal);

        return "shop";

    }


    @PostMapping("/add/{idProduct}")
    public String addProductToShop(@PathVariable Integer idProduct, HttpServletRequest request, HttpServletRequest response) throws JsonProcessingException {
        Product product = productServi.findById(idProduct)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Map<Integer, ProductShop> shop = obtainCookiesshop(request);

        if (shop.containsKey(idProduct)) {
            shop.get(idProduct).increaseQuantity();
        } else {
            shop.put(idProduct, new ProductShop(product, 1));
        }

        keepShopinCookies(shop, response);
        return "redirect:/shop";


    }
**/

    private Map<Integer, ProductShop> obtainCookiesshop(HttpServletRequest request) throws JsonProcessingException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (SHOP_COOKIE_NAME.equals(cookie.getName())) {
                    try {
                        String shopJson = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        try {
                            return objectMapper.readValue(shopJson, new TypeReference<Map<Integer, ProductShop>>() {});
                        } catch (JsonProcessingException e) {
                            List<Product> listProducts = objectMapper.readValue(shopJson, new TypeReference<List<Product>>() {});
                            Map<Integer, ProductShop> mapShop = new HashMap<>();
                            for (Product product : listProducts) {
                                mapShop.put(product.getIdproduct(), new ProductShop(product, 1));
                            }
                            return mapShop;
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException("Error decoding the cookie value", e);
                    }
                }
            }
        }
        return new HashMap<>();
    }

/**
    private void keepShopinCookies(Map<Integer, ProductShop> shop, HttpServletRequest response) throws JsonProcessingException {
        try {
            String shopJson = objectMapper.writeValueAsString(shop);
            String shopEncoded = URLEncoder.encode(shopJson, "UTF-8");
            Cookie cookie = new Cookie(SHOP_COOKIE_NAME, shopEncoded);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error coding the cookie value", e);
        }
    }
 **/

    public static class ProductShop {
        private Product product;
        private int quantity;

        public ProductShop() {}

        public ProductShop(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {return product;}

        public void setProduct(Product product) {this.product = product;}

        public int getQuantity() {return quantity;}

        public void setQuantity(int quantity) {this.quantity = quantity;}

        public void increaseQuantity() {this.quantity++;}
    }
}
