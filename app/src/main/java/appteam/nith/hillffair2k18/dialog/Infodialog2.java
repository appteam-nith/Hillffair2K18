package appteam.nith.hillffair2k18.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.activity.RouletteActivity;
import appteam.nith.hillffair2k18.adapter.TeamAdapter;
import appteam.nith.hillffair2k18.model.Team;

/**
 * Code by ThisIsNSH on Someday.
 */

public class Infodialog2 extends Dialog {

    private Activity activity;
    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private List<Team> teamList = new ArrayList<>();

    String info;
    TextView next, dialog;
    EditText editBet;
    int check = 1;

    public Infodialog2(@NonNull Activity context) {
        super(context);
        initUI();
    }

    public void initUI() {
        setContentView(R.layout.activity_infodialog2);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.3f;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(lp);
        getData();
        setCanceledOnTouchOutside(true);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void getData() {
        teamList.clear();
        recyclerView = findViewById(R.id.thirdRec);
        teamAdapter = new TeamAdapter(teamList, activity);
        teamList.add(new Team("Captaion Marvel", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "Chief"));
        teamList.add(new Team("Thanos", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "Villan"));
        teamList.add(new Team("Iron Man", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "Hero"));
        AndroidNetworking.get("http://hillffair.tk/getcoreteam")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        try {
                            int users = response.length();
                            for (int i = 0; i < users; i++) {
                                JSONObject json = response.getJSONObject(i);
                                String name = json.getString("name");
                                String profile = json.getString("profile_pic");
                                String position = json.getString("position");
                                teamList.add(new Team(name, "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", position));
                            }
                            teamAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        recyclerView.setAdapter(teamAdapter);
    }

}