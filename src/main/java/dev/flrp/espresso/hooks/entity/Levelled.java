package dev.flrp.espresso.hooks.entity;

import org.bukkit.entity.LivingEntity;

public interface Levelled {

    boolean hasLevel(LivingEntity entity);

    double getLevel(LivingEntity entity);

}
