package dev.flrp.espresso.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerUtils {

    public static ItemStack itemInHand(Player player) {
        return player.getInventory().getItemInMainHand();
    }

}
