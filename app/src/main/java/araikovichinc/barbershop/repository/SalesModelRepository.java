package araikovichinc.barbershop.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.SalesModelDataSource;
import araikovichinc.barbershop.pojo.SaleModel;
import araikovichinc.barbershop.repository.remote.SalesModelRemoteDataSource;

/**
 * Created by Tigran on 13.03.2018.
 */

public class SalesModelRepository implements SalesModelDataSource {

    private SalesModelRemoteDataSource remoteDataSource;

    @Inject
    public SalesModelRepository(SalesModelRemoteDataSource remoteDataSource){
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void loadSales(final LoadCallBack callBack) {
        remoteDataSource.loadSales(new LoadCallBack<ArrayList<SaleModel>>() {
            @Override
            public void onLoadSuccess(ArrayList<SaleModel> result) {
                callBack.onLoadSuccess(result);
            }

            @Override
            public void onFail(String message) {
                callBack.onFail(message);
            }
        });
    }
}
