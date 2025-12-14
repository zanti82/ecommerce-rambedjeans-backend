package com.rambedjeans.ecommerce_jeans.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ensayo {
    @GetMapping("/")
    public String home() {
        return "¡Backend de RAMBED E-commerce funcionando! 🚀";
    }
    
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
    
}
