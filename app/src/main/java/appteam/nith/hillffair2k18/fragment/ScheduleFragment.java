package appteam.nith.hillffair2k18.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import appteam.nith.hillffair2k18.adapter.ScheduleAdapter;
import appteam.nith.hillffair2k18.model.Schedule;
import appteam.nith.hillffair2k18.model.Wall;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class ScheduleFragment extends Fragment {

    private ScheduleAdapter scheduleAdapter;
    private RecyclerView recyclerView;
    private List<Schedule> scheduleList = new ArrayList<>();
    private Activity activity;

    public ScheduleFragment() {
    }

    public ScheduleFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AndroidNetworking.initialize(getActivity().getApplicationContext());
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        recyclerView = view.findViewById(R.id.firstRec);
        scheduleAdapter = new ScheduleAdapter(scheduleList, activity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(scheduleAdapter);
        getData();
        return view;
    }

    public void getData() {
//        scheduleList.add(new Schedule("Captain Marvel", "Her to Hero", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "05:00 Pm"));
//        scheduleList.add(new Schedule("Thanos", "Infinity Stones", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "06:00 PM"));
//        scheduleList.add(new Schedule("Iron Man", "Attitude and talent", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "07:00 PM"));
        AndroidNetworking.get("http://192.168.43.52:5000/getschedule")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        try {
                            int users = response.length();
                            for (int i = 0;i<users;i++) {
                                JSONObject json = response.getJSONObject(i);
                                String clubname = json.getString("club_id");
                                String event_name = json.getString("event_name");
                                String event_time = json.getString("event_time");
                                scheduleList.add(new Schedule(clubname, event_name, "1", event_time));
                            }
                            scheduleAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

        scheduleAdapter.notifyDataSetChanged();
    }

}
