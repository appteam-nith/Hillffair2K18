package appteam.nith.hillffair2k18.model;

/**
 * Created by naman on 22-09-2018.
 */

public class Contributor {

    String name, image, link;

    public Contributor(String name, String link, String image) {
        this.name = name;
        this.link = link;
        this.image = image;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
