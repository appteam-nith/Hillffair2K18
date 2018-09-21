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
import appteam.nith.hillffair2k18.adapter.ClubAdapter;
import appteam.nith.hillffair2k18.model.Club;

public class ClubsFragment extends Fragment {

    private ClubAdapter clubAdapter;
    private RecyclerView recyclerView;
    private Activity activity;
    private List<Club> clubList = new ArrayList<>();

    public ClubsFragment() {
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
        recyclerView.setNestedScrollingEnabled(false);
        clubAdapter = new ClubAdapter(clubList, activity);
        getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(clubAdapter);
        return view;
    }

    public void getData() {

    }
}
