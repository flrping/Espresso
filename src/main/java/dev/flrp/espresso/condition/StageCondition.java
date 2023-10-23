package dev.flrp.espresso.condition;

public class StageCondition implements Condition {

    private int minStage;
    private int maxStage;

    @Override
    public ConditionType getType() {
        return ConditionType.STAGE;
    }

    public StageCondition(int minStage, int maxStage) {
        this.minStage = minStage;
        this.maxStage = maxStage;
    }

    public boolean check(int stage) {
        return stage >= minStage && stage <= maxStage;
    }

    public int getMinStage() {
        return minStage;
    }

    public void setMinStage(int minStage) {
        this.minStage = minStage;
    }

    public int getMaxStage() {
        return maxStage;
    }

    public void setMaxStage(int maxStage) {
        this.maxStage = maxStage;
    }

}
