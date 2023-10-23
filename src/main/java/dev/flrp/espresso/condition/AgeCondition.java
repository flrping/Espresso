package dev.flrp.espresso.condition;

public class AgeCondition implements Condition {

    private int minAge;
    private int maxAge;

    @Override
    public ConditionType getType() {
        return ConditionType.AGE;
    }

    public AgeCondition(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public boolean check(int age) {
        return age >= minAge && age <= maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

}
