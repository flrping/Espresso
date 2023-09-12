package dev.flrp.espresso.hooks.economy;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultEconomyProvider implements EconomyProvider {

    private static Economy economy;
    private final boolean enabled;

    public VaultEconomyProvider() {
        enabled = setupEconomy();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return economy.hasAccount(offlinePlayer);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return economy.getBalance(offlinePlayer);
    }

    @Override
    public boolean deposit(OfflinePlayer offlinePlayer, double amount) {
        return economy.depositPlayer(offlinePlayer, amount).transactionSuccess();
    }

    @Override
    public boolean withdraw(OfflinePlayer offlinePlayer, double amount) {
        return economy.withdrawPlayer(offlinePlayer, amount).transactionSuccess();
    }

    @Override
    public boolean createAccount(OfflinePlayer offlinePlayer) {
        return economy.createPlayerAccount(offlinePlayer);
    }

    private static boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return true;
    }

}
