package araikovichinc.barbershop.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.callbacks.OnDeleteCallBack;
import araikovichinc.barbershop.callbacks.OnSaveCallBack;
import araikovichinc.barbershop.datasource.FeedbackDataSource;
import araikovichinc.barbershop.pojo.FeedbackModel;
import araikovichinc.barbershop.repository.remote.FeedbackModelRemoteDataSource;

/**
 * Created by Tigran on 18.03.2018.
 */

public class FeedbackModelRepository implements FeedbackDataSource {

    private FeedbackModelRemoteDataSource remoteDataSource;

    @Inject
    public FeedbackModelRepository(FeedbackModelRemoteDataSource remoteDataSource){
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void loadFeedback(final LoadCallBack callBack) {
        remoteDataSource.loadFeedback(new LoadCallBack<ArrayList<FeedbackModel>>() {
            @Override
            public void onLoadSuccess(ArrayList<FeedbackModel> result) {
                callBack.onLoadSuccess(result);
            }

            @Override
            public void onFail(String message) {
                callBack.onFail(message);
            }
        });
    }

    @Override
    public void saveFeedback(FeedbackModel model, final OnSaveCallBack callBack) {
        remoteDataSource.saveFeedback(model, new OnSaveCallBack() {
            @Override
            public void onSaved() {
                callBack.onSaved();
            }

            @Override
            public void onSaveFail() {
                callBack.onSaveFail();
            }
        });
    }

    @Override
    public void deleteFeedback(int id, OnDeleteCallBack callBack) {

    }
}
