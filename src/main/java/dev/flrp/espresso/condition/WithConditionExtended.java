package dev.flrp.espresso.condition;

import dev.flrp.espresso.hook.item.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WithConditionExtended implements Condition {

    private HashMap<ItemType, List<String>> materials = new HashMap<>();

    /**
     * Get the condition type.
     *
     * @return The condition type.
     */
    @Override
    public ConditionType getType() {
        return ConditionType.WITH;
    }

    /**
     * Check if the item type has the material.
     *
     * @param itemType The item type, or the hook the item is from. (e.g. ITEMS_ADDER). NONE is for default materials.
     * @param material The material to check.
     * @return If the material is present.
     */
    public boolean check(ItemType itemType, String material) {
        if (!materials.containsKey(itemType)) return false;
        return materials.get(itemType).contains(material);
    }

    /**
     * Get the materials.
     *
     * @return The materials.
     */
    public HashMap<ItemType, List<String>> getMaterials() {
        return materials;
    }

    /**
     * Set the materials.
     *
     * @param materials The materials.
     */
    public void setMaterials(HashMap<ItemType, List<String>> materials) {
        this.materials = materials;
    }

    /**
     * Add a material to the item type.
     *
     * @param itemType The item type, or the hook the item is from. (e.g. ITEMS_ADDER). NONE is for default materials.
     * @param material The material to add.
     */
    public void addMaterial(ItemType itemType, String material) {
        if (!materials.containsKey(itemType)) materials.put(itemType, new ArrayList<>());
        materials.get(itemType).add(material);
    }

    /**
     * Remove a material from the item type.
     *
     * @param itemType The item type, or the hook the item is from. (e.g. ITEMS_ADDER). NONE is for default materials.
     * @param material The material to remove.
     */
    public void removeMaterial(ItemType itemType, String material) {
        if (!materials.containsKey(itemType) || !materials.get(itemType).contains(material)) return;
        materials.get(itemType).remove(material);
    }

}
