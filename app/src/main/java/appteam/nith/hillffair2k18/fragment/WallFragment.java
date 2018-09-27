package appteam.nith.hillffair2k18.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.WallAdapter;
import appteam.nith.hillffair2k18.model.Wall;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class WallFragment extends Fragment implements View.OnClickListener {

    String user_id;
    ProgressBar loadwall;
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
        loadwall = view.findViewById(R.id.loadwall);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        SharedPreferences prefs = this.getActivity().getSharedPreferences("roll number", Context.MODE_PRIVATE);
        user_id = prefs.getString("name", "gsbs");
        fifthRec = view.findViewById(R.id.fifthRec);
        wallAdapter = new WallAdapter(wallList, activity);
        fifthRec.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        fifthRec.setAdapter(wallAdapter);
        getData();
        Log.e("WallFragment", "onCreateView: ");
        return view;
    }

    void getData() {
        loadwall.setVisibility(View.VISIBLE);
        wallList.clear();
        SharedPreferences prefs = activity.getSharedPreferences("number", Context.MODE_PRIVATE);
        final String check = prefs.getString("roll number", "gsb");
        AndroidNetworking.get(activity.getString(R.string.baseUrl) + "getwall/0/" + check)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            loadwall.setVisibility(View.GONE);
                            System.out.println(response);
                            int users = response.length();
                            for (int i = 0; i < users; i++) {
                                JSONObject json = response.getJSONObject(i);
                                String name = json.getString("name");
                                String roll = json.getString("rollno");
                                String likes = json.getString("likes");
                                String imgUrl = json.getString("image_url");
                                String profile = json.getString("profile_pic");
                                String image = json.getString("id");
                                int inttt = json.getInt("liked");
                                wallList.add(new Wall(name, roll, profile, imgUrl, likes, image, inttt));
                            }
                            wallAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
//        wallAdapter.notifyDataSetChanged();

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
