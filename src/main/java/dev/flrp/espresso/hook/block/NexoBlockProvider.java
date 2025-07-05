package dev.flrp.espresso.hook.block;

import com.nexomc.nexo.api.NexoBlocks;
import com.nexomc.nexo.api.NexoItems;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
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

    @Override @Nullable
    public String getCustomBlockName(ItemStack block) {
        if(!isEnabled()) return null;
        return NexoItems.idFromItem(block);
    }

    @Override @Nullable
    public String getCustomBlockName(Block block) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCustomBlock(ItemStack block) {
        if(!isEnabled()) return false;
        return NexoBlocks.isCustomBlock(block);
    }

    @Override
    public boolean isCustomBlock(Block block) {
        return NexoBlocks.isCustomBlock(block);
    }

    @Override
    public boolean isCustomBlock(String blockName) {
        return NexoBlocks.isCustomBlock(blockName);
    }

    @Override
    public void giveBlock(Player player, String blockName) {
        if(!isEnabled()) return;
        player.getInventory().addItem(NexoItems.itemFromId(blockName).build());
    }

    @Override
    public void giveBlock(Player player, String blockName, int amount) {
        if(!isEnabled()) return;
        ItemStack itemStack = NexoItems.itemFromId(blockName).build();
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
        if(!isEnabled()) return null;
        return NexoItems.itemFromId(blockName).build();
    }

}
