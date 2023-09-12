package dev.flrp.espresso.hooks.entity;

import org.bukkit.entity.LivingEntity;

import java.util.List;

public interface Modified {

    boolean hasModifier(LivingEntity entity, String modifier);

    boolean hasModifiers(LivingEntity entity);

    List<String> getEntityModifiers(LivingEntity entity);

    List<String> getModifierList();

}
