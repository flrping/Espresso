package dev.flrp.espresso.table;

import org.bukkit.entity.Player;

public interface Lootable {

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
     * @param identifier The identifier of the loot.
     */
    void setIdentifier(String identifier);

    /**
     * @return Get the weight of the loot.
     */
    double getWeight();

    /**
     * Set the weight of the loot.
     * @param weight The weight of the loot.
     */
    void setWeight(double weight);

    /**
     * @return Get the amount of the loot.
     */
    double getAmount();

    /**
     *  Set the amount of the loot.
     *  @param amount The amount of the loot.
     */
    void setAmount(double amount);

    /**
     * Get the additional amount of the loot.
     * @return The additional amount of the loot.
     */
    double getAddition();

    /**
     * Set the additional amount of the loot.
     * @param addition The additional amount of the loot.
     */
    void setAddition(double addition);

    /**
     * Rewards the player with the loot.
     * @param player The player to reward.
     */
    void reward(Player player);

    /**
     * Rewards the player with the loot.
     * @param player The player to reward.
     * @param amount The amount of the loot.
     */
    void reward(Player player, double amount);

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
