package appteam.nith.hillffair2k18.model;

/**
 * Created by ThisIsNSH on 9/20/2018.
 */

public class Wall {

    String name;
    String profile;
    String image;
    String likes;
    String share;
    String desc;

    public Wall(String name, String desc, String profile, String image, String likes, String share) {
        this.name = name;
        this.desc = desc;
        this.profile = profile;
        this.image = image;
        this.likes = likes;
        this.share = share;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }
}
