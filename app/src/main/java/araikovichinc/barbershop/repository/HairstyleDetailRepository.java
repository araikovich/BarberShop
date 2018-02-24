package araikovichinc.barbershop.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.HairstyleDetailDataSource;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;
import araikovichinc.barbershop.repository.local.HairstyleDetailLocalDataSource;
import araikovichinc.barbershop.repository.remote.HairstyleDetailRemoteDataSource;

/**
 * Created by Tigran on 23.02.2018.
 */

public class HairstyleDetailRepository implements HairstyleDetailDataSource {

    private HairstyleDetailLocalDataSource localDataSource;
    private HairstyleDetailRemoteDataSource remoteDataSource;

    @Inject
    public HairstyleDetailRepository(HairstyleDetailLocalDataSource localDataSource,
                                     HairstyleDetailRemoteDataSource remoteDataSource){
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }


    @Override
    public void loadHairstyleDetail(final int hairstyleId, final LoadCallBack callBack) {
        remoteDataSource.loadHairstyleDetail(hairstyleId, new LoadCallBack<ArrayList<HairstyleDetailCard>>() {
            @Override
            public void onLoadSuccess(ArrayList<HairstyleDetailCard> result) {
                localDataSource.saveHairstyleDetail(result);
                callBack.onLoadSuccess(result);
            }

            @Override
            public void onFail(String message) {
                localDataSource.loadHairstyleDetail(hairstyleId, new LoadCallBack<ArrayList<HairstyleDetailCard>>() {
                    @Override
                    public void onLoadSuccess(ArrayList<HairstyleDetailCard> result) {
                        callBack.onLoadSuccess(result);
                    }

                    @Override
                    public void onFail(String message) {
                        callBack.onFail(message);
                    }
                });
            }
        });
    }

    @Override
    public void saveHairstyleDetail(ArrayList<HairstyleDetailCard> cards) {

    }
}
