package araikovichinc.barbershop.presenters;

import android.support.constraint.solver.Goal;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.mvp.views.HairstyleDetailActivityView;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;
import araikovichinc.barbershop.repository.HairstyleDetailRepository;


/**
 * Created by Tigran on 16.02.2018.
 */

@InjectViewState
public class HairstyleDetailActivityPresenter extends MvpPresenter<HairstyleDetailActivityView> {

    @Inject
    HairstyleDetailRepository repository;

   public HairstyleDetailActivityPresenter(){
       MyApp.getModelComponent().inject(this);
   }

    public void loadCards(final int hairstyleId){
        getViewState().setProgressBar(View.VISIBLE);
        getViewState().showRefresh(View.GONE);
        repository.loadHairstyleDetail(hairstyleId, new LoadCallBack<ArrayList<HairstyleDetailCard>>() {
            @Override
            public void onLoadSuccess(ArrayList<HairstyleDetailCard> result) {
                getViewState().setProgressBar(View.GONE);
                getViewState().setAdapter(result);
            }

            @Override
            public void onFail(String message) {
                getViewState().showRefresh(View.VISIBLE);
                getViewState().setProgressBar(View.GONE);
            }
        });
    }

    public void setTitle(String title){
        getViewState().setTitle(title);
    }
}
