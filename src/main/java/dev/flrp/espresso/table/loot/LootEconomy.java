package dev.flrp.espresso.table.loot;

import dev.flrp.espresso.hook.economy.EconomyProvider;
import dev.flrp.espresso.table.LootableEconomy;
import org.bukkit.entity.Player;

public class LootEconomy implements LootableEconomy {

    private String identifier;
    private double weight;
    private double amount;

    private EconomyProvider economyProvider;

    public LootEconomy(String identifier, double weight, double amount) {
        this.identifier = identifier;
        this.weight = weight;
        this.amount = amount;
    }

    public LootEconomy(String identifier, double weight, double amount, EconomyProvider economyProvider) {
        this.economyProvider = economyProvider;
        this.identifier = identifier;
        this.weight = weight;
        this.amount = amount;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public void reward(Player player) {
        economyProvider.deposit(player, amount);
    }

    @Override
    public EconomyProvider getEconomyProvider() {
        return economyProvider;
    }

    @Override
    public void setEconomyProvider(EconomyProvider economyProvider) {
        this.economyProvider = economyProvider;
    }
}
