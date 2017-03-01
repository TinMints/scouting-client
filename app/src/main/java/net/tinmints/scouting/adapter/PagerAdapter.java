package net.tinmints.scouting.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.tinmints.scouting.AfterMatchFragment;
import net.tinmints.scouting.AutoTabFragment;
import net.tinmints.scouting.MatchFragment;
import net.tinmints.scouting.TeamDataTabFragment;
import net.tinmints.scouting.TeleOpTabFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    MatchFragment tab0 = new MatchFragment();
    TeamDataTabFragment tab1 = new TeamDataTabFragment();
    AutoTabFragment tab2 = new AutoTabFragment();
    TeleOpTabFragment tab3 = new TeleOpTabFragment();
    AfterMatchFragment tab4 = new AfterMatchFragment();

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab0 = new MatchFragment();
                return tab0;
            case 1:
                tab1 = new TeamDataTabFragment();
                return tab1;
            case 2:
                tab2 = new AutoTabFragment();
                return tab2;
            case 3:
                tab3 = new TeleOpTabFragment();
                return tab3;
            case 4:
                tab4 = new AfterMatchFragment();
                return tab4;
            default:
                return null;
        }
    }

    public void switchTab(int position) {
        switch (position) {
            case 0:
                tab0.onResume();
                tab1.onPause();
                tab2.onPause();
                tab3.onPause();
                tab4.onPause();
                break;
            case 1:
                tab0.onPause();
                tab1.onResume();
                tab2.onPause();
                tab3.onPause();
                tab4.onPause();
                break;
            case 2:
                tab0.onPause();
                tab1.onPause();
                tab2.onResume();
                tab3.onPause();
                tab4.onPause();
                break;
            case 3:
                tab0.onPause();
                tab1.onPause();
                tab2.onPause();
                tab3.onResume();
                tab4.onPause();
                break;
            case 4:
                tab0.onPause();
                tab1.onPause();
                tab2.onPause();
                tab3.onPause();
                tab4.onResume();
                break;
            default:
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
