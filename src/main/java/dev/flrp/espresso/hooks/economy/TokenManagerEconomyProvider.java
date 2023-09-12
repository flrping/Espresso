package dev.flrp.espresso.hooks.economy;

import me.realized.tokenmanager.TokenManagerPlugin;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class TokenManagerEconomyProvider implements EconomyProvider {

    public TokenManagerPlugin tokenManager;
    private final boolean enabled;

    public TokenManagerEconomyProvider() {
        enabled = Bukkit.getPluginManager().isPluginEnabled("TokenManager");
        tokenManager =  enabled ? TokenManagerPlugin.getInstance() : null;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return enabled && tokenManager.getTokens(offlinePlayer.getPlayer()).isPresent();
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return enabled ? tokenManager.getTokens(offlinePlayer.getPlayer()).orElse(0) : 0;
    }

    @Override
    public boolean deposit(OfflinePlayer offlinePlayer, double amount) {
        return enabled && tokenManager.addTokens(offlinePlayer.getPlayer(), Math.round(amount));
    }

    @Override
    public boolean withdraw(OfflinePlayer offlinePlayer, double amount) {
        return enabled && tokenManager.removeTokens(offlinePlayer.getPlayer(), Math.round(amount));
    }

    @Override
    public boolean createAccount(OfflinePlayer offlinePlayer) {
        return enabled && tokenManager.addTokens(offlinePlayer.getPlayer(), 0);
    }

}
