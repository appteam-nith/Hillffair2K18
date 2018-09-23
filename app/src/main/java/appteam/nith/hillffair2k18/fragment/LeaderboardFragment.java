package appteam.nith.hillffair2k18.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import appteam.nith.hillffair2k18.R;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class LeaderboardFragment extends Fragment implements View.OnClickListener {

    private TextView point, referral, popular;
    private Activity activity;

    public LeaderboardFragment() {
    }

    public LeaderboardFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        getData();
        point = view.findViewById(R.id.point);
        referral = view.findViewById(R.id.referral);
        popular = view.findViewById(R.id.popular);
        popular.setOnClickListener(this);
        point.setOnClickListener(this);
        referral.setOnClickListener(this);

        return view;
    }

    public void getData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popular:
                break;
            case R.id.point:
                break;
            case R.id.referral:
                break;
        }

    }
}
