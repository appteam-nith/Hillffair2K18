package appteam.nith.hillffair2k18.model;

/**
 * Created by ThisIsNSH on 9/20/2018.
 */

public class Schedule {

    String title;
    String subtitle;
    String img;
    String likes;

    Schedule(String title, String subtitle, String img, String likes) {
        this.title = title;
        this.subtitle = subtitle;
        this.img = img;
        this.likes = likes;
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

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
