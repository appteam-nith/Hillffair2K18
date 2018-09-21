package appteam.nith.hillffair2k18.model;

/**
 * Created by ThisIsNSH on 9/21/2018.
 */

public class Scroll {

    String name;
    Integer image;

    public Scroll(String name, Integer image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
