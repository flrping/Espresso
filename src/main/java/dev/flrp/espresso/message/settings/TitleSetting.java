package dev.flrp.espresso.message.settings;

public class TitleSetting {

    private int fadeIn;
    private int stay;
    private int fadeOut;

    private boolean isSubTitle = false;
    private String title = null;
    private String subTitle = null;

    public int getFadeIn() {
        return fadeIn;
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public int getStay() {
        return stay;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    public boolean isSubTitle() {
        return isSubTitle;
    }

    public void setIsSubTitle(boolean isSubTitle) {
        this.isSubTitle = isSubTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

}
