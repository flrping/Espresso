package dev.flrp.espresso.condition;

public class LevelCondition implements Condition {

    private int min;
    private int max;

    public LevelCondition(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public ConditionType getType() {
        return ConditionType.LEVEL;
    }

    public boolean check(int level) {
        return level >= min && level <= max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

}
