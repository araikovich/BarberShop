package araikovichinc.barbershop.presenters;

import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.mvp.views.GenderCategoriesFragmentView;
import araikovichinc.barbershop.pojo.GenderCard;
import araikovichinc.barbershop.repository.GenderCardRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 12.02.2018.
 */

@InjectViewState
public class GenderCategoriesFragmentPresenter extends MvpPresenter<GenderCategoriesFragmentView> {

    @Inject
    GenderCardRepository repository;

    public GenderCategoriesFragmentPresenter(){
        MyApp.getModelComponent().inject(this);
    }

    public void setTitle(String title){
        getViewState().setTitle(title);
    }



    public void nextActivity(int genderId, String title){
        getViewState().nextActivity(genderId, title);
    }

    public void loadData(){
        getViewState().setProgress(View.VISIBLE);
        getViewState().setRefreshVisibility(View.GONE);
        repository.getCards(new LoadCallBack<ArrayList<GenderCard>>() {
            @Override
            public void onLoadSuccess(ArrayList<GenderCard> result) {
                getViewState().setAdapter(result);
                getViewState().setProgress(View.GONE);
            }

            @Override
            public void onFail(String message) {
                getViewState().setRefreshVisibility(View.VISIBLE);
                getViewState().showToast(message);
            }
        });
    }
}
