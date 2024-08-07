package dev.flrp.espresso.hook.block;

import dev.flrp.espresso.hook.Hook;
import dev.flrp.espresso.hook.HookPurpose;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public interface BlockProvider extends Hook {

    BlockType getType();

    @Override
    default HookPurpose getPurpose() {
        return HookPurpose.BLOCK;
    }

    /**
     * Gets the name of the custom block.
     *
     * @return The name of the custom block, usually its personal identifier from the plugin.
     */
    String getCustomBlockName(ItemStack block);

    /**
     * Gets the name of the custom block.
     *
     * @return The name of the custom block, usually its personal identifier from the plugin.
     */
    String getCustomBlockName(Block block);

    /**
     * Checks if the block is a custom block.
     *
     * @return Whether the block is a custom block.
     */
    boolean isCustomBlock(ItemStack block);

    /**
     * Checks if the block is a custom block.
     *
     * @return Whether the block is a custom block.
     */
    boolean isCustomBlock(Block block);

    /**
     * Checks if the block is a custom block.
     *
     * @return Whether the block is a custom block.
     */
    boolean isCustomBlock(String blockName);

    /**
     * Gives the player a custom block.
     *
     * @param player The player to give the block to.
     * @param blockName The name of the custom block.
     */
    void giveBlock(Player player, String blockName);

    /**
     * Gives the player a custom block.
     *
     * @param player The player to give the block to.
     * @param blockName The name of the custom block.
     * @param amount The amount of the custom block.
     */
    void giveBlock(Player player, String blockName, int amount);

    /**
     * Gets a list of custom block names.
     *
     * @return A list of custom block names.
     */
    Set<String> getCustomBlockNames();

    /**
     * Gets the ItemStack from the custom block name.
     *
     * @param blockName The name of the custom block.
     * @return The block from the custom block name.
     */
    ItemStack getItemStack(String blockName);

}
