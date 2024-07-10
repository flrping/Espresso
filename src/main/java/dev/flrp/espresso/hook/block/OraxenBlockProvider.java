package dev.flrp.espresso.hook.block;

import io.th0rgal.oraxen.api.OraxenBlocks;
import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OraxenBlockProvider implements BlockProvider {

    @Override
    public String getName() {
        return "Oraxen";
    }

    @Override
    public BlockType getType() {
        return BlockType.ORAXEN;
    }

    @Override
    public String getCustomBlockName(ItemStack block) {
        if(!isEnabled()) return null;
        return OraxenItems.getIdByItem(block);
    }

    @Override
    public String getCustomBlockName(Block block) {
        if(!isEnabled()) return null;
        return OraxenBlocks.getOraxenBlock(block.getLocation()).getItemID();
    }

    @Override
    public boolean isCustomBlock(ItemStack block) {
        if(!isEnabled()) return false;
        return OraxenItems.getIdByItem(block) != null;
    }

    @Override
    public boolean isCustomBlock(Block block) {
        if(!isEnabled()) return false;
        return OraxenBlocks.getOraxenBlock(block.getLocation()) != null;
    }

    @Override
    public boolean isCustomBlock(String blockName) {
        return OraxenBlocks.isOraxenBlock(blockName);
    }

    @Override
    public void giveBlock(Player player, String blockName) {
        if(!isEnabled()) return;
        player.getInventory().addItem(OraxenItems.getItemById(blockName).build());
    }

    @Override
    public void giveBlock(Player player, String blockName, int amount) {
        if(!isEnabled()) return;
        ItemStack item = OraxenItems.getItemById(blockName).build();
        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

    @Override
    public Set<String> getCustomBlockNames() {
        if(!isEnabled()) return new HashSet<>();
        return OraxenBlocks.getBlockIDs();
    }

    @Override
    public ItemStack getItemStack(String blockName) {
        if(!isEnabled()) return null;
        return OraxenItems.getItemById(blockName).build();
    }

}
