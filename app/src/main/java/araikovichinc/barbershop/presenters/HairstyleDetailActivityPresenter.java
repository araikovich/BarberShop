package araikovichinc.barbershop.presenters;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.models.HairstyleDetailModel;
import araikovichinc.barbershop.mvp.views.HairstyleDetailActivityView;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 16.02.2018.
 */

@InjectViewState
public class HairstyleDetailActivityPresenter extends MvpPresenter<HairstyleDetailActivityView> {

    HairstyleDetailModel model;

    public void setModel(HairstyleDetailModel model) {
        if(this.model == null)
        this.model = model;
    }

    public void loadCards(final int hairstyleId){
        if(model.isdLoading()){
            return;
        }else {
            getViewState().setProgress(View.VISIBLE);
            getViewState().showRefresh(View.GONE);
            model.loadDetailFromServer(hairstyleId, new Callback<ArrayList<HairstyleDetailCard>>() {
                @Override
                public void onResponse(Call<ArrayList<HairstyleDetailCard>> call, Response<ArrayList<HairstyleDetailCard>> response) {
                    getViewState().setAdapter(response.body());
                    getViewState().setProgress(View.GONE);
                    model.saveDetail(response.body());
                }

                @Override
                public void onFailure(Call<ArrayList<HairstyleDetailCard>> call, Throwable t) {
                    model.loadCardsFromDb(hairstyleId, new LoadCallBack<ArrayList<HairstyleDetailCard>>() {
                        @Override
                        public void onLoadSuccess(ArrayList<HairstyleDetailCard> result) {
                            getViewState().setAdapter(result);
                            getViewState().setProgress(View.GONE);
                        }

                        @Override
                        public void onFail(String message) {
                            getViewState().showRefresh(View.VISIBLE);
                            getViewState().setProgress(View.GONE);
                            getViewState().showToast("Проблемы с сетью, попроюуйте еще раз");
                        }
                    });
                }
            });
        }
    }

    public void setTitle(String title){
        getViewState().setTitle(title);
    }
}
