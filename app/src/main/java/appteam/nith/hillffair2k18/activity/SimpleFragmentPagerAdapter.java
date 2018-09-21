package appteam.nith.hillffair2k18.activity;

/**
 * Created by com on 21-09-2018.
 */
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

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new WallFragment();
        } else if (position == 1) {
            return new ScheduleFragment();
        } else if (position == 2) {
            return new ScheduleFragment();
        } else if (position == 3) {
            return new LeaderboardFragment();
        } else if (position == 4) {
            return new ClubsFragment();
        } else if (position == 5) {
            return new CoreTeamFragment();
        } else {
            return new SponsersFragment();
        }
    }



    @Override
    public int getCount() {
        return 7;
    }
}
