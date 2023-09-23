package dev.flrp.espresso.table.loot;

import dev.flrp.espresso.table.LootableCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LootCommand implements LootableCommand {

    private String identifier;
    private String command;
    private double weight;
    private double amount;

    public LootCommand(String identifier, double weight, String command) {
        this.identifier = identifier;
        this.weight = weight;
    }

    public LootCommand(String identifier, String command, double weight, double amount) {
        this.identifier = identifier;
        this.command = command;
        this.weight = weight;
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
        for(int i = 0; i < amount; i++) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }
}
