package appteam.nith.hillffair2k18.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.fragment.ScheduleFragment;

public class DashActivity extends AppCompatActivity implements ScheduleFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, new ScheduleFragment());
        ft.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
