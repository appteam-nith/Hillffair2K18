package appteam.nith.hillffair2k18.Notification;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jatin on 25/10/17.
 */

public class NotificationArrayModel {

    @SerializedName("notifications")
    private ArrayList<notifications> list;

    public NotificationArrayModel(ArrayList<notifications> list) {
        this.list = list;
    }

    public ArrayList<notifications> getList() {
        return list;
    }

    public void setList(ArrayList<notifications> list) {
        this.list = list;
    }
}
