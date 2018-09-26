package appteam.nith.hillffair2k18.fragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.TeamAdapter;
import appteam.nith.hillffair2k18.model.Team;
import appteam.nith.hillffair2k18.model.Wall;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

/**
 * Coded by ThisIsNSH on Someday.
 */
public class CoreTeamFragment extends Fragment {

    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private List<Team> teamList = new ArrayList<>();
    private Activity activity;

    public CoreTeamFragment() {
    }

    public CoreTeamFragment(Activity activity) {
        this.activity = activity;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_core_team, container, false);
        AndroidNetworking.initialize(getActivity().getApplicationContext());
        recyclerView = view.findViewById(R.id.thirdRec);
        teamAdapter = new TeamAdapter(teamList, activity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getData();
        recyclerView.setAdapter(teamAdapter);
        Log.e("CodeFragment", "onCreateView: ");
        return view;
    }

    public void getData() {
        teamList.clear();
//        teamList.add(new Team("Captaion Marvel", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "Chief"));
//        teamList.add(new Team("Thanos", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "Villan"));
//        teamList.add(new Team("Iron Mam", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "Hero"));
//        teamAdapter.notifyDataSetChanged();
        AndroidNetworking.get("http://hillffair.tk/getcoreteam")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        try {
                            int users = response.length();
                            for (int i = 0;i<users;i++) {
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


    }

}