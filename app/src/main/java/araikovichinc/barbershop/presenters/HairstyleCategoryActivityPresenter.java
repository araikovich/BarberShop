package araikovichinc.barbershop.presenters;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


import java.util.ArrayList;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.models.HairstyleCategoryModel;
import araikovichinc.barbershop.mvp.views.HairstyleCategoryActivityView;
import araikovichinc.barbershop.pojo.HairstyleCategoryCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 13.02.2018.
 */

@InjectViewState
public class HairstyleCategoryActivityPresenter extends MvpPresenter<HairstyleCategoryActivityView> {

    private HairstyleCategoryModel model;

    public void setModel(HairstyleCategoryModel model){
        if(this.model == null)
        this.model = model;
    }

    public void setCards(final int genderId){
        if(model.isIsdLoading())
            return;
        getViewState().showRefresh(View.GONE);
        model.loadCategoriesFromServer(genderId, new Callback<ArrayList<HairstyleCategoryCard>>() {
            @Override
            public void onResponse(Call<ArrayList<HairstyleCategoryCard>> call, Response<ArrayList<HairstyleCategoryCard>> response) {
                getViewState().setAdapter(response.body());
                getViewState().setProgressBar(View.GONE);
                model.saveCardToDb(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<HairstyleCategoryCard>> call, Throwable t) {
                model.getCardsFromDb(genderId, new LoadCallBack<ArrayList<HairstyleCategoryCard>>() {
                    @Override
                    public void onLoadSuccess(ArrayList<HairstyleCategoryCard> result) {
                        getViewState().setAdapter(result);
                        getViewState().setProgressBar(View.GONE);
                    }

                    @Override
                    public void onFail(String message) {
                        getViewState().setProgressBar(View.GONE);
                        getViewState().showRefresh(View.VISIBLE);
                        getViewState().showToast("Пробдемы с сетью, попробуйте еще раз");
                    }
                });
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
