package dev.flrp.espresso.util;

import dev.flrp.espresso.Espresso;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerUtils {

    private static final Espresso instance = Espresso.getInstance();

    public static ItemStack itemInHand(Player player) {
        if (instance.getServer().getVersion().contains("1.8")) {
            return player.getItemInHand();
        } else {
            return player.getInventory().getItemInMainHand();
        }
    }

}
