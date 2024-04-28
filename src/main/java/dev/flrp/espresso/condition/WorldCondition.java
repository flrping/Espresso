package dev.flrp.espresso.condition;

import java.util.ArrayList;
import java.util.List;

public class WorldCondition implements Condition {

    private List<String> worlds = new ArrayList<>();

    @Override
    public ConditionType getType() {
        return ConditionType.WORLD;
    }

    public boolean check(String world) {
        return worlds.contains(world);
    }

    public List<String> getWorlds() {
        return worlds;
    }

    public void setWorlds(List<String> worlds) {
        this.worlds = worlds;
    }

    public void addWorld(String world) {
        worlds.add(world);
    }

    public void removeWorld(String world) {
        worlds.remove(world);
    }

}
