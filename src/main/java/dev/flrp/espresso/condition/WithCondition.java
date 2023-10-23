package dev.flrp.espresso.condition;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class WithCondition implements Condition {

    private List<Material> materials = new ArrayList<>();

    @Override
    public ConditionType getType() {
        return ConditionType.WITH;
    }

    public boolean check(Material material) {
        return materials.contains(material);
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public void addMaterial(Material material) {
        materials.add(material);
    }

    public void removeMaterial(Material material) {
        materials.remove(material);
    }

}
