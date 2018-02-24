package araikovichinc.barbershop.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.HairstyleCategoryDataSource;
import araikovichinc.barbershop.pojo.HairstyleCategoryCard;
import araikovichinc.barbershop.repository.local.HairstyleCategoryLocalDataSource;
import araikovichinc.barbershop.repository.remote.HairstyleCategoryRemoteDataSource;

/**
 * Created by Tigran on 23.02.2018.
 */

public class HairstyleCategoryRepository implements HairstyleCategoryDataSource {

    private HairstyleCategoryLocalDataSource localDataSource;
    private HairstyleCategoryRemoteDataSource remoteDataSource;

    @Inject
    public HairstyleCategoryRepository(HairstyleCategoryLocalDataSource localDataSource,
                                       HairstyleCategoryRemoteDataSource remoteDataSource){
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getHairstyleCategories(final int genderId, final LoadCallBack callBack) {
        remoteDataSource.getHairstyleCategories(genderId, new LoadCallBack<ArrayList<HairstyleCategoryCard>>() {
            @Override
            public void onLoadSuccess(ArrayList<HairstyleCategoryCard> result) {
                localDataSource.saveCategories(result);
                callBack.onLoadSuccess(result);
            }

            @Override
            public void onFail(String message) {
                localDataSource.getHairstyleCategories(genderId, new LoadCallBack<ArrayList<HairstyleCategoryCard>>() {
                    @Override
                    public void onLoadSuccess(ArrayList<HairstyleCategoryCard> result) {
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
    public void saveCategories(ArrayList<HairstyleCategoryCard> cards) {

    }
}
