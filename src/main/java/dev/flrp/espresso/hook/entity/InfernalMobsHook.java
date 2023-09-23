package dev.flrp.espresso.hook.entity;

import io.hotmail.com.jacob_vejvoda.infernal_mobs.infernal_mobs;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InfernalMobsHook implements Modified {

    private static final infernal_mobs infernalMobs = (infernal_mobs) Bukkit.getPluginManager().getPlugin("InfernalMobs");

    private static final List<String> modifierList = new ArrayList<>(Arrays.asList("confusing", "ghost", "morph", "mounted", "flying", "gravity", "firework", "necromancer",
            "archer", "molten", "mama", "potions", "explode", "berserk", "weakness", "vengeance", "webber", "storm", "sprint", "lifesteal",
            "ghastly", "ender", "cloaked", "1up", "sapper", "rust", "bullwark", "quicksand", "thief", "tosser", "withering", "blinding", "armoured",
            "poisonous"));

    @Override
    public String getName() {
        return "InfernalMobs";
    }

    @Override
    public boolean hasModifiers(LivingEntity entity) {
        return entity.hasMetadata("infernalMetadata");
    }

    @Override
    public boolean hasModifier(LivingEntity entity, String modifier) {
        return entity.hasMetadata("infernalMetadata") &&
                entity.getMetadata("infernalMetadata").stream()
                        .map(MetadataValue::asString)
                        .flatMap(mods -> Arrays.stream(mods.split(",")))
                        .anyMatch(mod -> mod.equals(modifier));
    }

    @Override
    public List<String> getEntityModifiers(LivingEntity entity) {
        if (entity.hasMetadata("infernalMetadata")) {
            return entity.getMetadata("infernalMetadata").stream()
                    .map(MetadataValue::asString)
                    .flatMap(mods -> Arrays.stream(mods.split(",")))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<String> getModifierList() {
        return modifierList;
    }

}
