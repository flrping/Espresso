package dev.flrp.espresso.table;

import dev.flrp.espresso.hook.economy.EconomyProvider;
import org.bukkit.entity.Player;

public class LootableEconomy implements Lootable {

    private String identifier;
    private double weight;
    private double amount;
    private double addition = 0;
    private EconomyProvider economyProvider;

    private String message;

    public LootableEconomy(String identifier, double weight, double amount) {
        this.identifier = identifier;
        this.weight = weight;
        this.amount = amount;
    }

    public LootableEconomy(String identifier, double weight, double amount, EconomyProvider economyProvider) {
        this.economyProvider = economyProvider;
        this.identifier = identifier;
        this.weight = weight;
        this.amount = amount;
    }

    @Override
    public LootType getType() {
        return LootType.ECONOMY;
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
    public double getAddition() {
        return addition;
    }

    @Override
    public void setAddition(double addition) {
        this.addition = addition;
    }

    @Override
    public void reward(Player player) {
        economyProvider.deposit(player, amount);
    }

    @Override
    public void reward(Player player, double amount) {
        economyProvider.deposit(player, amount);
    }

    @Override
    public boolean hasMessage() {
        return message != null;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @return The economy provider.
     */
    public EconomyProvider getEconomyProvider() {
        return economyProvider;
    }

    /**
     * Set the economy provider.
     * @param economyProvider The economy provider.
     */
    public void setEconomyProvider(EconomyProvider economyProvider) {
        this.economyProvider = economyProvider;
    }

}
