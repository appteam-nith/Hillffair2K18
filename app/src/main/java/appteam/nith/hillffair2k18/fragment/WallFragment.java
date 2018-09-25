package appteam.nith.hillffair2k18.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.WallAdapter;
import appteam.nith.hillffair2k18.model.Wall;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class WallFragment extends Fragment implements View.OnClickListener {

    private WallAdapter wallAdapter;
    private FloatingActionButton fab;
    private RecyclerView fifthRec;
    private List<Wall> wallList = new ArrayList<>();
    private Activity activity;
    private int PICK_PHOTO_CODE = 1046;

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
        AndroidNetworking.initialize(getActivity().getApplicationContext());
        View view = inflater.inflate(R.layout.fragment_wall, container, false);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        fifthRec = view.findViewById(R.id.fifthRec);
        wallAdapter = new WallAdapter(wallList, activity);
        fifthRec.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        fifthRec.setAdapter(wallAdapter);
        getData();
        Log.e("WallFragment", "onCreateView: ");
        return view;
    }

    void getData() {
        wallList.clear();
        wallList.add(new Wall("Captain Marvel", "Look mom I am a superhero", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "9000", ""));
        wallList.add(new Wall("Thanos", "I will kill half of the universe.", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "800", ""));
        wallList.add(new Wall("Iron Man", "You know who I am. I am the best.", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "100", ""));
        wallList.add(new Wall("Captain America", "I love to protect universe.", "https://wallpapercave.com/wp/wp1808936.jpg", "https://wallpapercave.com/wp/wp1808936.jpg", "200", ""));
//         AndroidNetworking.get("http://hillffair.tk/getwall")
//                 .build()
//                 .getAsJSONArray(new JSONArrayRequestListener() {
//                     @Override
//                     public void onResponse(JSONArray response) {
//                         // do anything with response
//                         try {
//                             int users = response.length();
//                             for (int i = 0;i<users;i++) {
//                                 JSONObject json = response.getJSONObject(i);
//                                 String name = json.getString("name");
//                                 String roll = json.getString("rollno");
//                                 String likes = json.getString("likes");
//                                 String image = json.getString("image_id");
//                                 wallList.add(new Wall(name, roll, "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", image, likes, ""));
//                             }
//                             wallAdapter.notifyDataSetChanged();

//                         } catch (JSONException e) {
//                             e.printStackTrace();
//                         }
//                     }
//                     @Override
//                     public void onError(ANError error) {
//                         // handle error
//                     }
//                 });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (intent.resolveActivity(activity.getPackageManager()) != null) {
                    startActivityForResult(intent, PICK_PHOTO_CODE);
                }
                break;
        }
    }
}
