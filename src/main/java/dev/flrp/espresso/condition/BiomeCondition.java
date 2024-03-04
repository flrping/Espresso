package dev.flrp.espresso.condition;

import org.bukkit.block.Biome;

import java.util.ArrayList;
import java.util.List;

public class BiomeCondition implements Condition {

    private List<Biome> biomes = new ArrayList<>();

    @Override
    public ConditionType getType() {
        return ConditionType.BIOME;
    }

    public boolean check(Biome biome) {
        return biomes.contains(biome);
    }

    public List<Biome> getBiomes() {
        return biomes;
    }

    public void setBiomes(List<Biome> biomes) {
        this.biomes = biomes;
    }

    public void addBiome(Biome biome) {
        biomes.add(biome);
    }

    public void removeBiome(Biome biome) {
        biomes.remove(biome);
    }

}
