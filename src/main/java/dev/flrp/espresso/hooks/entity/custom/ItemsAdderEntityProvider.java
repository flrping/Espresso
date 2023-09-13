package dev.flrp.espresso.hooks.entity.custom;

import dev.flrp.espresso.utils.StringUtils;
import dev.lone.itemsadder.api.CustomEntity;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nullable;

public class ItemsAdderEntityProvider implements EntityProvider {

    @Override
    public String getName() {
        return "ItemsAdder";
    }

    @Override @Nullable
    public String getCustomEntityName(LivingEntity entity) {
        if(!isCustomEntity(entity)) return null;
        CustomEntity customEntity = CustomEntity.byAlreadySpawned(entity);
        return StringUtils.getItemsAdderName(customEntity.getNamespacedID());
    }

    @Override
    public boolean isCustomEntity(LivingEntity entity) {
        if(!isEnabled()) return false;
        return CustomEntity.isCustomEntity(entity);
    }

}
