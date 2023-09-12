package dev.flrp.espresso.hooks.entity.custom;

import dev.flrp.espresso.utils.StringUtils;
import dev.lone.itemsadder.api.CustomEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

public class ItemsAdderEntityProvider implements EntityProvider {

    private final boolean enabled = Bukkit.getPluginManager().isPluginEnabled("ItemsAdder");

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getCustomEntityName(LivingEntity entity) {
        CustomEntity customEntity = CustomEntity.byAlreadySpawned(entity);
        return customEntity != null ? StringUtils.getItemsAdderName(customEntity.getNamespacedID()) : null;
    }

    @Override
    public boolean isCustomEntity(LivingEntity entity) {
        if(!enabled) return false;
        return CustomEntity.isCustomEntity(entity);
    }



}
