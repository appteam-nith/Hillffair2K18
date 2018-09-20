package appteam.nith.hillffair2k18.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.List;

import appteam.nith.hillffair2k18.adapter.RecyclerViewAdapter;
import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.fragment.WallFragment;

public class DashActivity extends AppCompatActivity {

    ScrollView scrollView;
    Context context;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    public static String[] subjects = {"WALL","GAMES","SCHEDULE","CORE TEAM","CLUBS","MEMBERS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, new WallFragment(DashActivity.this));
        ft.commit();
        setupdata();
    }

    public void setupdata(){
        context = getApplicationContext();
        relativeLayout =  findViewById(R.id.relativelayout1);
        recyclerView =  findViewById(R.id.recyclerview1);
        recylerViewLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(recylerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(context, subjects);
        recyclerView.setAdapter(recyclerViewAdapter);
        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        snapHelperStart.attachToRecyclerView(recyclerView);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
