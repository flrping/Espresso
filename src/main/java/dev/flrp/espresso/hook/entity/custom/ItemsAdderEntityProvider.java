package dev.flrp.espresso.hook.entity.custom;

import dev.flrp.espresso.util.StringUtils;
import dev.lone.itemsadder.api.CustomEntity;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemsAdderEntityProvider implements EntityProvider {

    private final List<String> entityNames = new ArrayList<>();

    public ItemsAdderEntityProvider() {
        for(String name : CustomEntity.getNamespacedIdsInRegistry()) {
            entityNames.add(StringUtils.getItemsAdderName(name));
        }
    }

    @Override
    public String getName() {
        return "ItemsAdder";
    }

    @Override
    public EntityType getType() {
        return EntityType.ITEMS_ADDER;
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

    @Override
    public boolean isCustomEntity(String entity) {
        if (!isEnabled()) return false;

        // Check if the entity name matches any custom entity name
        if (CustomEntity.getNamespacedIdsInRegistry().contains(entity)) {
            return true;
        }

        // Check if the entity name matches any custom entity name without the namespace ID
        String entityWithoutNamespace = entity.contains(":") ? entity.split(":")[1] : entity;
        return CustomEntity.getNamespacedIdsInRegistry().stream().anyMatch(id -> id.endsWith(entityWithoutNamespace));
    }

    @Override
    public List<String> getCustomEntityNames() {
        if(!isEnabled()) return null;
        return entityNames;
    }

}
