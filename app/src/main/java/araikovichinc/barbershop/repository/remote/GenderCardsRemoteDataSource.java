package araikovichinc.barbershop.repository.remote;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.GenderCardsDataSource;
import araikovichinc.barbershop.pojo.GenderCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 23.02.2018.
 */

public class GenderCardsRemoteDataSource implements GenderCardsDataSource {

    private ServerApi api;

    @Inject
    public GenderCardsRemoteDataSource(ServerApi api){
        this.api = api;
    }

    @Override
    public void getCards(final LoadCallBack callBack) {
        Call<ArrayList<GenderCard>> call = api.getSexCategories();
        call.enqueue(new Callback<ArrayList<GenderCard>>() {
            @Override
            public void onResponse(Call<ArrayList<GenderCard>> call, Response<ArrayList<GenderCard>> response) {
                callBack.onLoadSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<GenderCard>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void saveCardsToDb(ArrayList<GenderCard> cards) {

    }
}
