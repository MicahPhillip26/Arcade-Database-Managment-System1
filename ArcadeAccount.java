import java.time.LocalDate;

public class ArcadeAccount {
    private String cardId;
    private String customerName;
    private LocalDate accountCreationDate;
    private int gamesPlayed;
    private int tokensEarned;

    public ArcadeAccount(String cardId, String customerName, LocalDate date, int games, int tokens) {
        this.cardId = cardId;
        this.customerName = customerName;
        this.accountCreationDate = date;
        this.gamesPlayed = games;
        this.tokensEarned = tokens;
    }

    // Getters and setters
    public String getCardId() { return cardId; }
    public String getCustomerName() { return customerName; }
    public LocalDate getAccountCreationDate() { return accountCreationDate; }
    public int getGamesPlayed() { return gamesPlayed; }
    public int getTokensEarned() { return tokensEarned; }

    public void setCustomerName(String n) { customerName = n; }
    public void setAccountCreationDate(LocalDate d) { accountCreationDate = d; }
    public void setGamesPlayed(int g) { gamesPlayed = g; }
    public void setTokensEarned(int t) { tokensEarned = t; }

    /**
     * method: getRewardEligible
     * purpose: Determines the player's reward based on token count.
     * parameters: none
     * return: String – "Pizza", "Soda", or "None"
     */
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
}
