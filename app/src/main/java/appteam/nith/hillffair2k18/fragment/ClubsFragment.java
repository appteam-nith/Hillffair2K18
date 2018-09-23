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

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.ClubAdapter;
import appteam.nith.hillffair2k18.model.Club;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class ClubsFragment extends Fragment {

    private ClubAdapter clubAdapter;
    private RecyclerView recyclerView;
    private Activity activity;
    private List<Club> clubList = new ArrayList<>();

    public ClubsFragment() {
    }

    public ClubsFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);
        recyclerView = view.findViewById(R.id.secondRec);
        clubAdapter = new ClubAdapter(clubList, activity);
        getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(clubAdapter);
        Log.e("ClubsFragment", "onCreateView: ");
        return view;
    }

    public void getData() {
        clubList.add(new Club("Captain Marvel", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "Comming Soon"));
        clubList.add(new Club("Thanos", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "Destiny Arrives"));
        clubList.add(new Club("Iron Man", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "I love money"));
        clubAdapter.notifyDataSetChanged();

    }
}
