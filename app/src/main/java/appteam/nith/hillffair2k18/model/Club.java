package appteam.nith.hillffair2k18.model;

/**
 * Created by naman on 20-09-2018.
 */

public class Club {

    String name;
    String image;
    String info;


    Club(String name, String image, String info) {
        this.name = name;
        this.image = image;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
