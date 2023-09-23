package dev.flrp.espresso.hook.economy;

import me.realized.tokenmanager.TokenManagerPlugin;
import org.bukkit.OfflinePlayer;

public class TokenManagerEconomyProvider implements EconomyProvider {

    public TokenManagerPlugin tokenManager;

    public TokenManagerEconomyProvider() {
        tokenManager = isEnabled() ? TokenManagerPlugin.getInstance() : null;
    }

    @Override
    public String getName() {
        return "TokenManager";
    }

    @Override
    public EconomyType getType() {
        return EconomyType.TOKEN_MANAGER;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return tokenManager != null && tokenManager.getTokens(offlinePlayer.getPlayer()).isPresent();
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return tokenManager != null ? tokenManager.getTokens(offlinePlayer.getPlayer()).orElse(0) : 0;
    }

    @Override
    public boolean deposit(OfflinePlayer offlinePlayer, double amount) {
        return tokenManager != null && tokenManager.addTokens(offlinePlayer.getPlayer(), Math.round(amount));
    }

    @Override
    public boolean withdraw(OfflinePlayer offlinePlayer, double amount) {
        return tokenManager != null && tokenManager.removeTokens(offlinePlayer.getPlayer(), Math.round(amount));
    }

    @Override
    public boolean createAccount(OfflinePlayer offlinePlayer) {
        return tokenManager != null && tokenManager.addTokens(offlinePlayer.getPlayer(), 0);
    }

}
