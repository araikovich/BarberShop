package araikovichinc.barbershop.repository.remote;

import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.callbacks.OnDeleteCallBack;
import araikovichinc.barbershop.callbacks.OnSaveCallBack;
import araikovichinc.barbershop.datasource.FeedbackDataSource;
import araikovichinc.barbershop.pojo.FeedbackModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tigran on 18.03.2018.
 */

public class FeedbackModelRemoteDataSource implements FeedbackDataSource {

    private ServerApi serverApi;

    @Inject
    public FeedbackModelRemoteDataSource(ServerApi serverApi){
        this.serverApi = serverApi;
    }

    @Override
    public void loadFeedback(final LoadCallBack callBack) {
        Call<ArrayList<FeedbackModel>> call = serverApi.getFeedback();
        call.enqueue(new Callback<ArrayList<FeedbackModel>>() {
            @Override
            public void onResponse(Call<ArrayList<FeedbackModel>> call, Response<ArrayList<FeedbackModel>> response) {
                callBack.onLoadSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<FeedbackModel>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void saveFeedback(FeedbackModel model, final OnSaveCallBack callBack) {
        Call<Integer> call = serverApi.saveFeedback(model.getRating(), model.getTitle(), model.getText(),
                model.getDay(), model.getMonth(), model.getYear());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.body() == 1){
                    callBack.onSaved();
                }else
                    callBack.onSaveFail();
                Log.d("MyLogs", "Request sanded" + response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                callBack.onSaveFail();
                Log.d("MyLogs", "Request failed");
            }
        });
    }

    @Override
    public void deleteFeedback(int id, OnDeleteCallBack callBack) {

    }
}
