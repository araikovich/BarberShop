package araikovichinc.barbershop.repository.remote;


import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.FreeTimeDataSource;
import araikovichinc.barbershop.pojo.TimeModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 05.03.2018.
 */

public class FreeTimeRemoteDataSource implements FreeTimeDataSource {

    private ServerApi serverApi;

    @Inject
    public FreeTimeRemoteDataSource(ServerApi serverApi){
        this.serverApi = serverApi;
    }

    @Override
    public void loadFreeTimes(int hairdresserId, int totalTime, final LoadCallBack callBack) {
        Call<ArrayList<TimeModel>> call = serverApi.getFreeTime(hairdresserId, totalTime);
        call.enqueue(new Callback<ArrayList<TimeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TimeModel>> call, Response<ArrayList<TimeModel>> response) {
                callBack.onLoadSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<TimeModel>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }
}
