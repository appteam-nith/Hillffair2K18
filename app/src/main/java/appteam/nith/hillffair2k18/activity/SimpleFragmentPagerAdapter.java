package appteam.nith.hillffair2k18.activity;

/**
 * Created by com on 21-09-2018.
 */

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import appteam.nith.hillffair2k18.fragment.ClubsFragment;
import appteam.nith.hillffair2k18.fragment.CoreTeamFragment;
import appteam.nith.hillffair2k18.fragment.LeaderboardFragment;
import appteam.nith.hillffair2k18.fragment.QuizGamesFragment;
import appteam.nith.hillffair2k18.fragment.ScheduleFragment;
import appteam.nith.hillffair2k18.fragment.SponsersFragment;
import appteam.nith.hillffair2k18.fragment.WallFragment;


public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    Activity activity;


    public SimpleFragmentPagerAdapter(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new WallFragment(activity);
        } else if (position == 1) {
            return new QuizGamesFragment(activity);
        } else if (position == 2) {
            return new ScheduleFragment(activity);
        } else if (position == 3) {
            return new LeaderboardFragment(activity);
        } else if (position == 4) {
            return new ClubsFragment(activity);
        } else if (position == 5) {
            return new CoreTeamFragment(activity);
        } else if (position == 6) {
            return new SponsersFragment(activity);
        }
        return new SponsersFragment(activity);

    }


    @Override
    public int getCount() {
        return 7;
    }


}
