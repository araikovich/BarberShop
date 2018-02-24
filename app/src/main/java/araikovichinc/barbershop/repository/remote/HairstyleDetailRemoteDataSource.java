package araikovichinc.barbershop.repository.remote;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.HairstyleDetailDataSource;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 24.02.2018.
 */

public class HairstyleDetailRemoteDataSource implements HairstyleDetailDataSource{

    private ServerApi api;

    @Inject
    public HairstyleDetailRemoteDataSource(ServerApi api){
        this.api = api;
    }

    @Override
    public void loadHairstyleDetail(int hairstyleId, final LoadCallBack callBack) {
        Call<ArrayList<HairstyleDetailCard>> call = api.getHairstyleDetailing(hairstyleId);
        call.enqueue(new Callback<ArrayList<HairstyleDetailCard>>() {
            @Override
            public void onResponse(Call<ArrayList<HairstyleDetailCard>> call, Response<ArrayList<HairstyleDetailCard>> response) {
                callBack.onLoadSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<HairstyleDetailCard>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void saveHairstyleDetail(ArrayList<HairstyleDetailCard> cards) {

    }
}
