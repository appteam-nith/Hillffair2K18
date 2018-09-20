package appteam.nith.hillffair2k18.model;

/**
 * Created by naman on 20-09-2018.
 */

public class Team {

    String name;
    String image;
    String position;

    Team(String name, String image, String position) {
        this.name = name;
        this.image = image;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
