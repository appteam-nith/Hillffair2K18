package appteam.nith.hillffair2k18.Notification;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jatin on 25/10/17.
 */

public class ContentModel {
    @SerializedName("en")
    private String en;

    public ContentModel(String title) {
        this.en = title;
    }

    public String getTitle() {
        return en;
    }

    public void setTitle(String title) {
        this.en = title;
    }
}
