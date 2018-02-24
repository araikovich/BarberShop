package araikovichinc.barbershop.presenters;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.mvp.views.HairstyleCategoryActivityView;
import araikovichinc.barbershop.pojo.HairstyleCategoryCard;
import araikovichinc.barbershop.repository.HairstyleCategoryRepository;

/**
 * Created by Tigran on 13.02.2018.
 */

@InjectViewState
public class HairstyleCategoryActivityPresenter extends MvpPresenter<HairstyleCategoryActivityView> {

    @Inject
    HairstyleCategoryRepository repository;


    public HairstyleCategoryActivityPresenter(){
        MyApp.getModelComponent().inject(this);
    }

    public void setCards(final int genderId){
        getViewState().setProgressBar(View.VISIBLE);
        getViewState().showRefresh(View.GONE);
        repository.getHairstyleCategories(genderId, new LoadCallBack<ArrayList<HairstyleCategoryCard>>() {
            @Override
            public void onLoadSuccess(ArrayList<HairstyleCategoryCard> result) {
                getViewState().setAdapter(result);
                getViewState().setProgressBar(View.GONE);
            }

            @Override
            public void onFail(String message) {
                getViewState().setProgressBar(View.GONE);
                getViewState().showRefresh(View.VISIBLE);
            }
        });
    }

    public void changetitle(String title){
        getViewState().setTitle(title);
    }

    public void next(int hairstyleId, String hairstyleTitle){
        getViewState().nextActivity(hairstyleId, hairstyleTitle);
    }

}
