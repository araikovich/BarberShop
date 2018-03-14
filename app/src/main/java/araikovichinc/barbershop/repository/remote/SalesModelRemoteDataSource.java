package araikovichinc.barbershop.repository.remote;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.SalesModelDataSource;
import araikovichinc.barbershop.pojo.SaleModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 13.03.2018.
 */

public class SalesModelRemoteDataSource implements SalesModelDataSource{
    private ServerApi api;

    @Inject
    public SalesModelRemoteDataSource(ServerApi api){
        this.api = api;
    }

    @Override
    public void loadSales(final LoadCallBack callBack) {
        Call<ArrayList<SaleModel>> call = api.getSales();
        call.enqueue(new Callback<ArrayList<SaleModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SaleModel>> call, Response<ArrayList<SaleModel>> response) {
                callBack.onLoadSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<SaleModel>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }
}
