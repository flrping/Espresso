package dev.flrp.espresso.condition;

import org.bukkit.entity.Player;

public class PermissionCondition implements Condition {

    private String permission;

    @Override
    public ConditionType getType() {
        return ConditionType.PERMISSION;
    }

    public boolean check(Player player) {
        return player.hasPermission(permission);
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}
