package dev.flrp.espresso.hook.block;

import com.nexomc.nexo.api.NexoBlocks;
import com.nexomc.nexo.api.NexoItems;
import jakarta.annotation.Nullable;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NexoBlockProvider implements BlockProvider {

    @Override
    public String getName() {
        return "Nexo";
    }

    @Override
    public BlockType getType() {
        return BlockType.NEXO;
    }

    @Override
    @Nullable
    public String getCustomBlockName(ItemStack block) {
        if (!isEnabled()) return null;
        return NexoItems.idFromItem(block);
    }

    @Override
    @Nullable
    public String getCustomBlockName(Block block) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCustomBlock(ItemStack block) {
        if (!isEnabled()) return false;
        return NexoBlocks.isCustomBlock(block);
    }

    @Override
    public boolean isCustomBlock(Block block) {
        if (!isEnabled()) return false;
        return NexoBlocks.isCustomBlock(block);
    }

    @Override
    public boolean isCustomBlock(String blockName) {
        if (!isEnabled()) return false;
        return NexoBlocks.isCustomBlock(blockName);
    }

    @Override
    public void giveBlock(Player player, String blockName) {
        if (!isEnabled()) return;
        var itemBuilder = NexoItems.itemFromId(blockName);
        if (itemBuilder == null) return;
        player.getInventory().addItem(itemBuilder.build());
    }

    @Override
    public void giveBlock(Player player, String blockName, int amount) {
        if (!isEnabled()) return;
        var itemBuilder = NexoItems.itemFromId(blockName);
        if (itemBuilder == null) return;
        ItemStack itemStack = itemBuilder.build();
        itemStack.setAmount(amount);
        player.getInventory().addItem(itemStack);
    }

    @Override
    public Set<String> getCustomBlockNames() {
        if (!isEnabled()) return new HashSet<>();
        return new HashSet<>(Arrays.asList(NexoBlocks.blockIDs()));
    }

    @Override
    public ItemStack getItemStack(String blockName) {
        if (!isEnabled()) return null;
        var itemBuilder = NexoItems.itemFromId(blockName);
        return itemBuilder != null ? itemBuilder.build() : null;
    }

}
