package dev.flrp.espresso.table;

public class LootableCommand implements Lootable {

    private String identifier;
    private String command;
    private double weight;
    private double min;
    private double max;

    private String message;

    public LootableCommand(String identifier, String command, double weight) {
        this.identifier = identifier;
        this.command = command;
        this.weight = weight;
    }

    public LootableCommand(String identifier, String command, double weight, double min, double max) {
        this.identifier = identifier;
        this.command = command;
        this.weight = weight;
        this.min = min;
        this.max = max;
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
    public double getMin() {
        return min;
    }

    @Override
    public void setMin(double min) {
        this.min = min;
    }

    @Override
    public double getMax() {
        return max;
    }

    @Override
    public void setMax(double max) {
        this.max = max;
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

    @Override
    public LootableCommand clone() {
        try {
            return (LootableCommand) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
