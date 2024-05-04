package dev.flrp.espresso.hook.block;

public enum BlockType {

    NONE,
    ITEMS_ADDER,
    ORAXEN;

    public static BlockType getByName(String string) {
        for(BlockType type : values()) {
            if(type.name().equalsIgnoreCase(string)) {
                return type;
            }
        }
        return NONE;
    }

}
