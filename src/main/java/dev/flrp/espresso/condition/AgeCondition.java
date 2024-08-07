package dev.flrp.espresso.condition;

public class AgeCondition implements Condition {

    private int min;
    private int max;

    public AgeCondition(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public ConditionType getType() {
        return ConditionType.AGE;
    }

    public boolean check(int age) {
        return age >= min && age <= max;
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
