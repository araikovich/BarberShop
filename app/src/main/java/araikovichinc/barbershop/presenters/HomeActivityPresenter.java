package araikovichinc.barbershop.presenters;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.activities.GenderCategoryActivity;
import araikovichinc.barbershop.activities.HomeActivity;
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
        switch (itemId){
            case R.id.nav_hairstyles:
                getViewState().onGenderActivity();
                break;
            case R.id.nav_book:
                getViewState().onBookActivity();
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
