package appteam.nith.hillffair2k18.fragment;

import android.app.Activity;
import android.content.Context;
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
    private Context context;
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
        fifthRec.setNestedScrollingEnabled(false);
        wallAdapter = new WallAdapter(wallList, activity);
        getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        fifthRec.setLayoutManager(linearLayoutManager);
        fifthRec.setAdapter(wallAdapter);
        return view;
    }

    void getData() {
        wallList.add(new Wall("Nishant", "https://m.media-amazon.com/images/M/MV5BMjUzZTJmZDItODRjYS00ZGRhLTg2NWQtOGE0YjJhNWVlMjNjXkEyXkFqcGdeQXVyMTg4NDI0NDM@._V1_UY317_CR51,0,214,317_AL_.jpg", "https://m.media-amazon.com/images/M/MV5BMjUzZTJmZDItODRjYS00ZGRhLTg2NWQtOGE0YjJhNWVlMjNjXkEyXkFqcGdeQXVyMTg4NDI0NDM@._V1_UY317_CR51,0,214,317_AL_.jpg", "100", "20"));
        wallList.add(new Wall("Nishant", "https://m.media-amazon.com/images/M/MV5BMjUzZTJmZDItODRjYS00ZGRhLTg2NWQtOGE0YjJhNWVlMjNjXkEyXkFqcGdeQXVyMTg4NDI0NDM@._V1_UY317_CR51,0,214,317_AL_.jpg", "https://m.media-amazon.com/images/M/MV5BMjUzZTJmZDItODRjYS00ZGRhLTg2NWQtOGE0YjJhNWVlMjNjXkEyXkFqcGdeQXVyMTg4NDI0NDM@._V1_UY317_CR51,0,214,317_AL_.jpg", "100", "20"));
        wallList.add(new Wall("Nishant", "https://m.media-amazon.com/images/M/MV5BMjUzZTJmZDItODRjYS00ZGRhLTg2NWQtOGE0YjJhNWVlMjNjXkEyXkFqcGdeQXVyMTg4NDI0NDM@._V1_UY317_CR51,0,214,317_AL_.jpg", "https://m.media-amazon.com/images/M/MV5BMjUzZTJmZDItODRjYS00ZGRhLTg2NWQtOGE0YjJhNWVlMjNjXkEyXkFqcGdeQXVyMTg4NDI0NDM@._V1_UY317_CR51,0,214,317_AL_.jpg", "100", "20"));
        wallAdapter.notifyDataSetChanged();
    }
}
