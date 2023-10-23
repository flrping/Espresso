package dev.flrp.espresso.table;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LootableCommand implements Lootable {

    private String identifier;
    private String command;
    private double weight;
    private double amount;
    private double addition = 0;

    private String message;

    public LootableCommand(String identifier, String command, double weight, double amount) {
        this.identifier = identifier;
        this.command = command;
        this.weight = weight;
        this.amount = amount;
    }

    @Override
    public LootType getType() {
        return LootType.COMMAND;
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
        for(int i = 0; i < amount; i++) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
    }

    @Override
    public void reward(Player player, double amount) {
        for(int i = 0; i < amount; i++) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
