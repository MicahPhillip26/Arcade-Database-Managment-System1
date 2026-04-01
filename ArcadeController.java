package com.example.arcadeui;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class ArcadeController {

    private final ArcadeService service;

    public ArcadeController(ArcadeService service) {
        this.service = service;
    }

    @GetMapping
    public List<ArcadeAccount> getAllAccounts() {
        return service.getAllAccounts();
    }

    @GetMapping("/{cardId}")
    public ArcadeAccount getAccount(@PathVariable String cardId) {
        return service.getAccount(cardId);
    }

    @PostMapping
    public String addAccount(@RequestBody ArcadeAccount account) {
        return service.addAccount(account) ? "Account added" : "Card ID exists";
    }

    @PutMapping("/{cardId}/tokens")
    public String updateTokens(@PathVariable String cardId, @RequestParam int tokens) {
        return service.updateAccountTokens(cardId, tokens) ? "Tokens updated" : "Account not found";
    }

    @DeleteMapping("/{cardId}")
    public String removeAccount(@PathVariable String cardId) {
        return service.removeAccount(cardId) ? "Account removed" : "Account not found";
    }
}
