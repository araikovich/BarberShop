package araikovichinc.barbershop.presenters;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.fargments.GenderCategoriesFragment;
import araikovichinc.barbershop.mvp.views.HomeActivityView;

/**
 * Created by Tigran on 11.02.2018.
 */

@InjectViewState
public class HomeActivityPresenter extends MvpPresenter<HomeActivityView> {

    int currPage;

    public void changePage(int itemId){
        if(currPage == itemId)
            return;;
        Fragment fragment;
        switch (itemId){
            case R.id.nav_hairstyles:
                currPage = itemId;
                fragment = new GenderCategoriesFragment();
                getViewState().setFragment(fragment);
                break;
            case R.id.nav_book:

                break;
            case R.id.nav_where_we_are:

                break;
            case R.id.nav_feedback:

                break;
            case R.id.nav_contacts:

                break;
            case R.id.nav_question:

                break;
        }
    }

}