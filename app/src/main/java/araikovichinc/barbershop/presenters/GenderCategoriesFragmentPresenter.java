package araikovichinc.barbershop.presenters;

import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.models.GenderCategoryModel;
import araikovichinc.barbershop.mvp.views.GenderCategoriesFragmentView;
import araikovichinc.barbershop.pojo.GenderCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 12.02.2018.
 */

@InjectViewState
public class GenderCategoriesFragmentPresenter extends MvpPresenter<GenderCategoriesFragmentView> {

    GenderCategoryModel model;

    public void setTitle(String title){
        getViewState().setTitle(title);
    }

    public void setModel(GenderCategoryModel model){
        if(this.model == null)
        this.model = model;
    }

    public void nextActivity(int genderId, String title){
        getViewState().nextActivity(genderId, title);
    }

    public void loadData(){
        if(model.isLoading())
            return;
        model.setLoading(true);
        getViewState().setProgress(View.VISIBLE);
        getViewState().setRefreshVisibility(View.GONE);
        model.loadGenderCards(new Callback<ArrayList<GenderCard>>() {
            @Override
            public void onResponse(Call<ArrayList<GenderCard>> call, Response<ArrayList<GenderCard>> response) {
                Log.d("MyLogs", "Server Works");
                getViewState().setAdapter(response.body());
                getViewState().setProgress(View.GONE);
                model.setLoading(false);
                model.saveGenderCards(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<GenderCard>> call, Throwable t) {
                model.loadGenderCardsFormDb(new LoadCallBack<ArrayList<GenderCard>>() {
                    @Override
                    public void onLoadSuccess(ArrayList<GenderCard> cards) {
                        getViewState().setAdapter(cards);
                        getViewState().setProgress(View.GONE);
                    }

                    @Override
                    public void onFail(String message) {
                        getViewState().setProgress(View.GONE);
                        getViewState().setRefreshVisibility(View.VISIBLE);
                        getViewState().showToast("Проблемы с сетью, попробуйте еще раз");
                    }
                });
            }
        });

    }
}
