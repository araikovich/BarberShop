package araikovichinc.barbershop.presenters;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.R;
import araikovichinc.barbershop.activities.GenderCategoryActivity;
import araikovichinc.barbershop.activities.HomeActivity;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.mvp.views.HomeActivityView;
import araikovichinc.barbershop.pojo.SaleModel;
import araikovichinc.barbershop.repository.SalesModelRepository;

/**
 * Created by Tigran on 11.02.2018.
 */

@InjectViewState
public class HomeActivityPresenter extends MvpPresenter<HomeActivityView> {

    @Inject
    SalesModelRepository repository;

    public HomeActivityPresenter(){
        MyApp.getModelComponent().inject(this);
    }

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
                getViewState().onFeedbackActivity();
                break;
            case R.id.nav_contacts:

                break;
            case R.id.nav_question:

                break;
        }
    }

    public void loadSales(){
        repository.loadSales(new LoadCallBack<ArrayList<SaleModel>>() {
            @Override
            public void onLoadSuccess(ArrayList<SaleModel> result) {
                getViewState().setSales(result);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
