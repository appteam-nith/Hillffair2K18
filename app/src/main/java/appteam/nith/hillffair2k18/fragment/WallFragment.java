package appteam.nith.hillffair2k18.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.WallAdapter;
import appteam.nith.hillffair2k18.model.Wall;

public class WallFragment extends Fragment {

    private WallAdapter wallAdapter;
    private RecyclerView fifthRec;
    private List<Wall> wallList = new ArrayList<>();
    private Activity activity;

    public WallFragment() {
    }

    public WallFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wall, container, false);
        fifthRec = view.findViewById(R.id.fifthRec);
        wallAdapter = new WallAdapter(wallList, activity);
        getData();
        fifthRec.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        fifthRec.setAdapter(wallAdapter);
        wallAdapter.notifyDataSetChanged();
        return view;
    }

    void getData() {
        wallList.add(new Wall("Captain Marvel", "I am better than Superman", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "9000", ""));
        wallList.add(new Wall("Thanos", "I will kill half of the universe.", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "800", ""));
        wallList.add(new Wall("Iron Man", "You know who I am. I am the best.", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "100", ""));
        wallList.add(new Wall("Captain America", "I love to protect universe.", "https://wallpapercave.com/wp/wp1808936.jpg", "https://wallpapercave.com/wp/wp1808936.jpg", "200", ""));
        wallAdapter.notifyDataSetChanged();
    }
}
