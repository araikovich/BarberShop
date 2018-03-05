package araikovichinc.barbershop.repository.remote;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.HairdressersDataSource;
import araikovichinc.barbershop.pojo.HairdresserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 01.03.2018.
 */

public class HairdressersRemoteDataSource  implements HairdressersDataSource{

    private ServerApi serverApi;

    @Inject
    public HairdressersRemoteDataSource(ServerApi serverApi){
        this.serverApi = serverApi;
    }

    @Override
    public void loadHairdressers(int genderId, final LoadCallBack callBack) {
        Call<ArrayList<HairdresserModel>> call = serverApi.getHairdressers(genderId);
        call.enqueue(new Callback<ArrayList<HairdresserModel>>() {
            @Override
            public void onResponse(Call<ArrayList<HairdresserModel>> call, Response<ArrayList<HairdresserModel>> response) {
                callBack.onLoadSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<HairdresserModel>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }
}
