package dev.flrp.espresso.table;

import org.bukkit.entity.Player;

public interface Lootable {

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
     * Runs a command for this loot if needed.
     */
    void reward(Player player);

}
