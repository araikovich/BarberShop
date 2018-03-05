package araikovichinc.barbershop.repository.remote;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.ServiceDataSource;
import araikovichinc.barbershop.pojo.ServiceModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 04.03.2018.
 */

public class ServiceRemoteDataSource implements ServiceDataSource {

    private ServerApi serverApi;

    @Inject
    public ServiceRemoteDataSource(ServerApi serverApi){
        this.serverApi = serverApi;
    }

    @Override
    public void loadServices(int genderId, final LoadCallBack callBack) {
        Call<ArrayList<ServiceModel>> call = serverApi.getServiceCategories(genderId);
        call.enqueue(new Callback<ArrayList<ServiceModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ServiceModel>> call, Response<ArrayList<ServiceModel>> response) {
                callBack.onLoadSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ServiceModel>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }
}
