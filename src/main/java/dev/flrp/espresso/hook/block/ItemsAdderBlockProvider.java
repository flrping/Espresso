package dev.flrp.espresso.hook.block;

import dev.flrp.espresso.util.StringUtils;
import dev.lone.itemsadder.api.CustomBlock;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ItemsAdderBlockProvider implements BlockProvider {

    @Override
    public String getName() {
        return "ItemsAdder";
    }

    @Override
    public BlockType getType() {
        return BlockType.ITEMS_ADDER;
    }

    @Override
    public String getCustomBlockName(ItemStack block) {
        if (!isEnabled()) return null;
        CustomBlock customBlock = CustomBlock.byItemStack(block);
        return customBlock != null ? StringUtils.getItemsAdderName(customBlock.getId()) : null;
    }

    @Override
    public String getCustomBlockName(Block block) {
        if (!isEnabled()) return null;
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        return customBlock != null ? StringUtils.getItemsAdderName(customBlock.getId()) : null;
    }

    @Override
    public boolean isCustomBlock(ItemStack block) {
        if (!isEnabled()) return false;
        return CustomBlock.byItemStack(block) != null;
    }

    @Override
    public boolean isCustomBlock(Block block) {
        if (!isEnabled()) return false;
        return CustomBlock.byAlreadyPlaced(block) != null;
    }

    @Override
    public boolean isCustomBlock(String blockName) {
        if (!isEnabled()) return false;

        Collection<String> namespacedIds = CustomBlock.getNamespacedIdsInRegistry();
        if (namespacedIds.contains(blockName)) {
            return true;
        }

        String blockWithoutNamespace = blockName.contains(":") ? blockName.split(":")[1] : blockName;
        return namespacedIds.stream().anyMatch(id -> {
            int colonIndex = id.indexOf(':');
            String localName = colonIndex >= 0 ? id.substring(colonIndex + 1) : id;
            return localName.equals(blockWithoutNamespace);
        });
    }

    @Override
    public void giveBlock(Player player, String blockName) {
        if (!isEnabled()) return;
        CustomBlock customBlock = CustomBlock.getInstance(blockName);
        if (customBlock != null) {
            player.getInventory().addItem(customBlock.getItemStack());
        }
    }

    @Override
    public void giveBlock(Player player, String blockName, int amount) {
        if (!isEnabled()) return;
        CustomBlock customBlock = CustomBlock.getInstance(blockName);
        if (customBlock != null) {
            ItemStack block = customBlock.getItemStack();
            block.setAmount(amount);
            player.getInventory().addItem(block);
        }
    }

    @Override
    public Set<String> getCustomBlockNames() {
        if (!isEnabled()) return new HashSet<>();
        return CustomBlock.getNamespacedIdsInRegistry();
    }

    @Override
    public ItemStack getItemStack(String blockName) {
        if (!isEnabled()) return null;
        CustomBlock customBlock = CustomBlock.getInstance(blockName);
        return customBlock != null ? customBlock.getItemStack() : null;
    }

}
