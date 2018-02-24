package araikovichinc.barbershop.presenters;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.activities.HomeActivity;
import araikovichinc.barbershop.fargments.BookCalendarFragment;
import araikovichinc.barbershop.fargments.GenderCategoriesFragment;
import araikovichinc.barbershop.mvp.views.HomeActivityView;

/**
 * Created by Tigran on 11.02.2018.
 */

@InjectViewState
public class HomeActivityPresenter extends MvpPresenter<HomeActivityView> {

    public HomeActivityPresenter(){
        Log.d("MyLogs", "HomeActivityPresenter");
    }

    int currPage;

    public void changePage(int itemId){
        if(currPage == itemId)
            return;
        currPage = itemId;
        Fragment fragment;
        switch (itemId){
            case R.id.nav_hairstyles:
                fragment = new GenderCategoriesFragment();
                getViewState().setFragment(fragment);
                break;
            case R.id.nav_book:
                fragment = new BookCalendarFragment();
                getViewState().setFragment(fragment);
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
