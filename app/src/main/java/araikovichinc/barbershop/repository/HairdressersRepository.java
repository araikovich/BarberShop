package araikovichinc.barbershop.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.HairdressersDataSource;
import araikovichinc.barbershop.pojo.HairdresserModel;
import araikovichinc.barbershop.repository.remote.HairdressersRemoteDataSource;

/**
 * Created by Tigran on 01.03.2018.
 */

public class HairdressersRepository implements HairdressersDataSource {

    private HairdressersRemoteDataSource remoteDataSource;

    @Inject
    public HairdressersRepository(HairdressersRemoteDataSource remoteDataSource){
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void loadHairdressers(int genderId, final LoadCallBack callBack) {
        remoteDataSource.loadHairdressers(genderId, new LoadCallBack<ArrayList<HairdresserModel>>() {
            @Override
            public void onLoadSuccess(ArrayList<HairdresserModel> result) {
                callBack.onLoadSuccess(result);
            }

            @Override
            public void onFail(String message) {
                callBack.onFail(message);
            }
        });
    }
}
