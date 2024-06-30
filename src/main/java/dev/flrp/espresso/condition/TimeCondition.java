package dev.flrp.espresso.condition;

public class TimeCondition implements Condition {

    private int min;
    private int max;

    public TimeCondition(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public ConditionType getType() {
        return ConditionType.TIME;
    }

    public boolean check(int time) {
        return time >= min && time <= max;
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
