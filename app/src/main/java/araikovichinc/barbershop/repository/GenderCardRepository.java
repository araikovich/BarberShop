package araikovichinc.barbershop.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.GenderCardsDataSource;
import araikovichinc.barbershop.pojo.GenderCard;
import araikovichinc.barbershop.repository.local.GenderCardsLocalDataSource;
import araikovichinc.barbershop.repository.remote.GenderCardsRemoteDataSource;

/**
 * Created by Tigran on 23.02.2018.
 */

public class GenderCardRepository implements GenderCardsDataSource {

    private GenderCardsLocalDataSource localDataSource;
    private GenderCardsRemoteDataSource remoteDataSource;


    @Inject
    public GenderCardRepository(GenderCardsLocalDataSource localDataSource,
                                GenderCardsRemoteDataSource remoteDataSource){
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getCards(final LoadCallBack callBack) {
        remoteDataSource.getCards(new LoadCallBack<ArrayList<GenderCard>>() {
            @Override
            public void onLoadSuccess(ArrayList<GenderCard> result) {
                localDataSource.saveCardsToDb(result);
                callBack.onLoadSuccess(result);
            }

            @Override
            public void onFail(String message) {
                localDataSource.getCards(new LoadCallBack<ArrayList<GenderCard>>() {
                    @Override
                    public void onLoadSuccess(ArrayList<GenderCard> result) {
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
    public void saveCardsToDb(ArrayList<GenderCard> cards) {

    }
}
