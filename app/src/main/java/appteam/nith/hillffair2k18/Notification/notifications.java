package appteam.nith.hillffair2k18.Notification;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 8/30/16.
 */
public class notifications {

    String bigpicture;
    String launchurl;
    @SerializedName("id")
    private String id;
    @SerializedName("headings")
    private ContentModel headings;
    @SerializedName("contents")
    private ContentModel contents;
    ;
    @SerializedName("big_picture")
    private String big_picture;
    private String small_icon;
    private String notification_id;

    public String getImg() {
        return big_picture;
    }

    public void setImg(String img) {
        this.big_picture = img;
    }

    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
    }

    public ContentModel getTitle() {
        return headings;
    }

    public void setTitle(ContentModel title) {
        this.headings = title;
    }

    public ContentModel getBody() {
        return contents;
    }

    public void setBody(ContentModel body) {
        this.contents = body;
    }

    public String getBigpicture() {
        return bigpicture;
    }

    public void setBigpicture(String bigpicture) {
        this.bigpicture = bigpicture;
    }

    public String getLaunchurl() {
        return launchurl;
    }

    public void setLaunchurl(String launchurl) {
        this.launchurl = launchurl;
    }

    public String getSmall_icon() {
        return small_icon;
    }

    public void setSmall_icon(String small_icon) {
        this.small_icon = small_icon;
    }


    public String getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(String notification_id) {
        this.notification_id = notification_id;
    }


}
