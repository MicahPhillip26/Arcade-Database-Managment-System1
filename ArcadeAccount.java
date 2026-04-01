package com.example.arcadeui;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class ArcadeAccount {
    @Id
    private String cardId;
    private String customerName;
    private LocalDate accountCreationDate;
    private int gamesPlayed;
    private int tokensEarned;

    public ArcadeAccount() {} // Default constructor for JPA

    public ArcadeAccount(String cardId, String customerName, LocalDate date, int games, int tokens) {
        this.cardId = cardId;
        this.customerName = customerName;
        this.accountCreationDate = date;
        this.gamesPlayed = games;
        this.tokensEarned = tokens;
    }

    // getters and setters ...

    public String getRewardEligible() {
        if (tokensEarned >= 100) return "Pizza";
        if (tokensEarned >= 50) return "Soda";
        return "None";
    }

    @Override
    public String toString() {
        return String.format("CardID: %s | Name: %s | Created: %s | Games: %d | Tokens: %d | Reward: %s",
                cardId, customerName, accountCreationDate, gamesPlayed, tokensEarned, getRewardEligible());
    }

    @Id
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setTokensEarned(int newTokens) {
    }
}
