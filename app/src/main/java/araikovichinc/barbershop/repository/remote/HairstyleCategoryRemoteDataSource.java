package araikovichinc.barbershop.repository.remote;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.HairstyleCategoryDataSource;
import araikovichinc.barbershop.pojo.HairstyleCategoryCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 24.02.2018.
 */

public class HairstyleCategoryRemoteDataSource implements HairstyleCategoryDataSource{

    private ServerApi api;

    @Inject
    public HairstyleCategoryRemoteDataSource(ServerApi serverApi){
        this.api = serverApi;
    }

    @Override
    public void getHairstyleCategories(int genderId, final LoadCallBack callBack) {
        Call<ArrayList<HairstyleCategoryCard>> call = api.getHairstyleCategories(genderId);
        call.enqueue(new Callback<ArrayList<HairstyleCategoryCard>>() {
            @Override
            public void onResponse(Call<ArrayList<HairstyleCategoryCard>> call, Response<ArrayList<HairstyleCategoryCard>> response) {
                callBack.onLoadSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<HairstyleCategoryCard>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void saveCategories(ArrayList<HairstyleCategoryCard> cards) {

    }
}
