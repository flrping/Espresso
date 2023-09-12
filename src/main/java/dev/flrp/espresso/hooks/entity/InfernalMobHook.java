package dev.flrp.espresso.hooks.entity;

import io.hotmail.com.jacob_vejvoda.infernal_mobs.infernal_mobs;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfernalMobHook implements Modified {

    private static final infernal_mobs infernalMobs = Bukkit.getPluginManager().isPluginEnabled("InfernalMobs") ?
            (infernal_mobs) Bukkit.getPluginManager().getPlugin("InfernalMobs") : null;

    private static final List<String> modifierList = new ArrayList<>(Arrays.asList("confusing", "ghost", "morph", "mounted", "flying", "gravity", "firework", "necromancer",
            "archer", "molten", "mama", "potions", "explode", "berserk", "weakness", "vengeance", "webber", "storm", "sprint", "lifesteal",
            "ghastly", "ender", "cloaked", "1up", "sapper", "rust", "bullwark", "quicksand", "thief", "tosser", "withering", "blinding", "armoured",
            "poisonous"));

    @Override
    public boolean hasModifiers(LivingEntity entity) {
        return entity.hasMetadata("infernalMetadata");
    }

    @Override
    public boolean hasModifier(LivingEntity entity, String modifier) {
        return false;
    }

    @Override
    public List<String> getEntityModifiers(LivingEntity entity) {
        return null;
    }

    @Override
    public List<String> getModifierList() {
        return modifierList;
    }

}
