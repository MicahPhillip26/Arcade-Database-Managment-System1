package com.example.arcadeui;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ArcadeService {
    private final Map<String, ArcadeAccount> accounts = new HashMap<>();

    public List<ArcadeAccount> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public ArcadeAccount getAccount(String cardId) {
        return accounts.get(cardId);
    }

    public boolean addAccount(ArcadeAccount account) {
        if (accounts.containsKey(account.getCardId())) return false;
        accounts.put(account.getCardId(), account);
        return true;
    }

    public boolean updateAccountTokens(String cardId, int newTokens) {
        ArcadeAccount acc = accounts.get(cardId);
        if (acc == null) return false;
        acc.setTokensEarned(newTokens);
        return true;
    }

    public boolean removeAccount(String cardId) {
        return accounts.remove(cardId) != null;
    }
}
