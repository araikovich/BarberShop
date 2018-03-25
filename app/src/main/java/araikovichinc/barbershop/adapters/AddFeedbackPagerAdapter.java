package araikovichinc.barbershop.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

/**
 * Created by Tigran on 22.03.2018.
 */

public class AddFeedbackPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments;

    public AddFeedbackPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void setFragments(ArrayList<Fragment> fragments){
        this.fragments.addAll(fragments);
        notifyDataSetChanged();
    }
}
