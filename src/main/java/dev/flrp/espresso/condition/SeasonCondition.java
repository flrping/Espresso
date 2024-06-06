package dev.flrp.espresso.condition;

import java.util.List;

public class SeasonCondition implements Condition {

    private List<String> seasons;

    @Override
    public ConditionType getType() {
        return null;
    }

    public boolean check(String season) {
        return seasons.contains(season);
    }

    public List<String> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<String> seasons) {
        this.seasons = seasons;
    }

    public boolean isSeason(String season) {
        return seasons.contains(season);
    }

    public void addSeason(String season) {
        seasons.add(season);
    }

    public void removeSeason(String season) {
        seasons.remove(season);
    }

}
