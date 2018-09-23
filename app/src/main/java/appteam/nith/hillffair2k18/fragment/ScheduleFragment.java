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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.ScheduleAdapter;
import appteam.nith.hillffair2k18.model.Schedule;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class ScheduleFragment extends Fragment {

    private ScheduleAdapter scheduleAdapter1, scheduleAdapter2, scheduleAdapter3;
    private RecyclerView recyclerView1, recyclerView2, recyclerView3;
    private List<Schedule> scheduleList1 = new ArrayList<>();
    private List<Schedule> scheduleList2 = new ArrayList<>();
    private List<Schedule> scheduleList3 = new ArrayList<>();
    private Activity activity;
    private TextView date1, date2, date3, noSch;
    private LinearLayout linearLayout;
    private LinearLayoutManager linearLayoutManager1, linearLayoutManager2, linearLayoutManager3;

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
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        date1 = view.findViewById(R.id.date1);
        date2 = view.findViewById(R.id.date2);
        date3 = view.findViewById(R.id.date3);
        noSch = view.findViewById(R.id.noSch);

        linearLayout = view.findViewById(R.id.main);

        recyclerView1 = view.findViewById(R.id.firstRec1);
        recyclerView2 = view.findViewById(R.id.firstRec2);
        recyclerView3 = view.findViewById(R.id.firstRec3);
        scheduleAdapter1 = new ScheduleAdapter(scheduleList1, activity);
        scheduleAdapter2 = new ScheduleAdapter(scheduleList2, activity);
        scheduleAdapter3 = new ScheduleAdapter(scheduleList3, activity);

        linearLayoutManager1 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager2 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager3 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView1.setAdapter(scheduleAdapter1);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.setAdapter(scheduleAdapter2);
        recyclerView3.setLayoutManager(linearLayoutManager3);
        recyclerView3.setAdapter(scheduleAdapter3);
        getData();
        Log.e("ScheduleFragment", "onCreateView: ");
        return view;
    }

    public void getData() {
        date1.setText("3 October");
        date2.setText("4 October");
        date3.setText("5 October");

        scheduleList1.add(new Schedule("Captain Marvel", "Her to Hero", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "05:00 Pm"));
        scheduleList1.add(new Schedule("Thanos", "Infinity Stones", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "06:00 PM"));
        scheduleList1.add(new Schedule("Iron Man", "Attitude and talent", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "07:00 PM"));

        scheduleList2.add(new Schedule("Captain Marvel", "Her to Hero", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "05:00 Pm"));
        scheduleList2.add(new Schedule("Thanos", "Infinity Stones", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "06:00 PM"));
        scheduleList2.add(new Schedule("Iron Man", "Attitude and talent", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "07:00 PM"));

        scheduleList3.add(new Schedule("Captain Marvel", "Her to Hero", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "05:00 Pm"));
        scheduleList3.add(new Schedule("Thanos", "Infinity Stones", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "06:00 PM"));
        scheduleList3.add(new Schedule("Iron Man", "Attitude and talent", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "07:00 PM"));

        scheduleAdapter1.notifyDataSetChanged();
        scheduleAdapter2.notifyDataSetChanged();
        scheduleAdapter3.notifyDataSetChanged();
    }
}
