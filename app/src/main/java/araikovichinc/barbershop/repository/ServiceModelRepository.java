package araikovichinc.barbershop.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.ServiceDataSource;
import araikovichinc.barbershop.pojo.ServiceModel;
import araikovichinc.barbershop.repository.remote.ServiceRemoteDataSource;

/**
 * Created by Tigran on 04.03.2018.
 */

public class ServiceModelRepository implements ServiceDataSource {

    private ServiceRemoteDataSource serviceRemoteDataSource;

    @Inject
    public ServiceModelRepository(ServiceRemoteDataSource serviceRemoteDataSource){
        this.serviceRemoteDataSource = serviceRemoteDataSource;
    }

    @Override
    public void loadServices(int genderId, final LoadCallBack callBack) {
        serviceRemoteDataSource.loadServices(genderId, new LoadCallBack<ArrayList<ServiceModel>>() {
            @Override
            public void onLoadSuccess(ArrayList<ServiceModel> result) {
                callBack.onLoadSuccess(result);
            }

            @Override
            public void onFail(String message) {
                callBack.onFail(message);
            }
        });
    }
}
