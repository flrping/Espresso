package dev.flrp.espresso.hook.economy;

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
    public String getName() {
        return "Vault";
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public EconomyType getType() {
        return EconomyType.VAULT;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return enabled && economy.hasAccount(offlinePlayer);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return enabled ? economy.getBalance(offlinePlayer) : 0;
    }

    @Override
    public boolean deposit(OfflinePlayer offlinePlayer, double amount) {
        return enabled && economy.depositPlayer(offlinePlayer, amount).transactionSuccess();
    }

    @Override
    public boolean withdraw(OfflinePlayer offlinePlayer, double amount) {
        return enabled && economy.withdrawPlayer(offlinePlayer, amount).transactionSuccess();
    }

    @Override
    public boolean createAccount(OfflinePlayer offlinePlayer) {
        return enabled && economy.createPlayerAccount(offlinePlayer);
    }

    private boolean setupEconomy() {
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
