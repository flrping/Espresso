package dev.flrp.espresso.table;

public interface Lootable extends Cloneable {

    /**
     * @return The loot type.
     */
    LootType getType();

    /**
     * @return The identifier of the loot.
     */
    String getIdentifier();

    /**
     * Set the identifier of the loot.
     *
     * @param identifier The identifier of the loot.
     */
    void setIdentifier(String identifier);

    /**
     * @return Get the weight of the loot.
     */
    double getWeight();

    /**
     * Set the weight of the loot.
     *
     * @param weight The weight of the loot.
     */
    void setWeight(double weight);

    /**
     * Get the minimum amount of the loot.
     */
    double getMin();

    /**
     * Set the minimum amount of the loot.
     *
     * @param min The minimum amount of the loot.
     */
    void setMin(double min);

    /**
     * Get the maximum amount of the loot.
     */
    double getMax();

    /**
     * Set the maximum amount of the loot.
     *
     * @param max The maximum amount of the loot.
     */
    void setMax(double max);

    /**
     * Roll the amount of the loot.
     */
    default double rollAmount() {
        return Math.random() * (getMax() - getMin()) + getMin();
    }

    /**
     * Generates a LootResult from this loot.
     */
    default LootResult generateResult() {
        LootResult result = new LootResult();
        result.setLootable(this);
        result.setAmount((int) rollAmount());
        return result;
    }

    /**
     * @return If this loot has a custom message.
     */
    boolean hasMessage();

    /**
     * Sets a custom message for this loot.
     */
    void setMessage(String message);

    /**
     * @return Get the custom message of the loot.
     */
    String getMessage();

}
