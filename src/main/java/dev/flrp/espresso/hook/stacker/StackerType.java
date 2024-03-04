package dev.flrp.espresso.hook.stacker;

public enum StackerType {

    NONE,
    ROSE_STACKER,
    STACK_MOB,
    ULTIMATE_STACKER,
    WILD_STACKER;

    public static StackerType getByName(String name) {
        for (StackerType stackerType : values()) {
            if (stackerType.name().equalsIgnoreCase(name)) {

                return stackerType;
            }
        }
        return NONE;
    }

}
