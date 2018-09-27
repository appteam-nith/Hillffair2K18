package appteam.nith.hillffair2k18.model;

/**
 * Created by naman on 22-09-2018.
 */

public class contributorsItem {

    String name, image, githubUrl;

    public contributorsItem(String name, String image, String githubUrl) {
        this.name = name;
        this.image = image;
        this.githubUrl = githubUrl;
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

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
}
