package dev.flrp.espresso.hooks.economy;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.OfflinePlayer;

public class PlayerPointsEconomyProvider implements EconomyProvider {

    public PlayerPointsAPI playerPointsAPI;

    public PlayerPointsEconomyProvider() {
        playerPointsAPI = isEnabled() ? PlayerPoints.getInstance().getAPI() : null;
    }

    @Override
    public String getName() {
        return "PlayerPoints";
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return playerPointsAPI != null && playerPointsAPI.look(offlinePlayer.getUniqueId()) >= 0;
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return playerPointsAPI != null ? playerPointsAPI.look(offlinePlayer.getUniqueId()) : 0;
    }

    @Override
    public boolean deposit(OfflinePlayer offlinePlayer, double amount) {
        return playerPointsAPI != null && playerPointsAPI.give(offlinePlayer.getUniqueId(), (int) Math.round(amount));
    }

    @Override
    public boolean withdraw(OfflinePlayer offlinePlayer, double amount) {
        return playerPointsAPI != null && playerPointsAPI.take(offlinePlayer.getUniqueId(), (int) Math.round(amount));
    }

    @Override
    public boolean createAccount(OfflinePlayer offlinePlayer) {
        return playerPointsAPI != null && playerPointsAPI.give(offlinePlayer.getUniqueId(), 0);
    }
}
