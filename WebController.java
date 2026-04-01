package com.example.arcadeui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    private final ArcadeService service;

    public WebController(ArcadeService service) { this.service = service; }

    @GetMapping("/accounts")
    public String viewAccounts(Model model) {
        model.addAttribute("accounts", service.getAllAccounts());
        return "accounts";
    }
}
