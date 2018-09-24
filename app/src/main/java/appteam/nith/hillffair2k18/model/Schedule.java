package appteam.nith.hillffair2k18.model;

/**
 * Coded by ThisIsNSH on 9/20/2018.
 */

public class Schedule {

    String title;
    String subtitle;
    String img;
    String time;

    public Schedule(String title, String subtitle, String img, String time) {
        this.title = title;
        this.subtitle = subtitle;
        this.img = img;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
