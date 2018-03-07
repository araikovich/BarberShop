package araikovichinc.barbershop.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.FreeTimeDataSource;
import araikovichinc.barbershop.pojo.TimeModel;
import araikovichinc.barbershop.repository.remote.FreeTimeRemoteDataSource;

/**
 * Created by Tigran on 05.03.2018.
 */

public class FreeTimeRepository implements FreeTimeDataSource {

    FreeTimeRemoteDataSource remoteDataSource;

    @Inject
    public FreeTimeRepository(FreeTimeRemoteDataSource remoteDataSource){
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void loadFreeTimes(int hairdresserId, int totalTime, final LoadCallBack callBack) {
        remoteDataSource.loadFreeTimes(hairdresserId, totalTime, new LoadCallBack<ArrayList<TimeModel>>() {
            @Override
            public void onLoadSuccess(ArrayList<TimeModel> result) {
                callBack.onLoadSuccess(result);
            }

            @Override
            public void onFail(String message) {
                callBack.onFail(message);
            }
        });
    }
}
