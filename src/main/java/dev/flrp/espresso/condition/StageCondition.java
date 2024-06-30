package dev.flrp.espresso.condition;

public class StageCondition implements Condition {

    private int min;
    private int max;

    public StageCondition(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public ConditionType getType() {
        return ConditionType.STAGE;
    }

    public boolean check(int stage) {
        return stage >= min && stage <= max;
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
