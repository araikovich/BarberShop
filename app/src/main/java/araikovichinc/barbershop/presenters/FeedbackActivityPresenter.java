package araikovichinc.barbershop.presenters;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.mvp.views.FeedbackActivityView;
import araikovichinc.barbershop.pojo.FeedbackModel;
import araikovichinc.barbershop.repository.FeedbackModelRepository;

/**
 * Created by Tigran on 17.03.2018.
 */

@InjectViewState
public class FeedbackActivityPresenter extends MvpPresenter<FeedbackActivityView> {

    @Inject
    FeedbackModelRepository repository;

    public FeedbackActivityPresenter(){
        MyApp.getModelComponent().inject(this);
    }

    public void loadFeedback(){
        getViewState().setProgressVisible(View.VISIBLE);
        getViewState().setRefreshVisible(View.GONE);
        repository.loadFeedback(new LoadCallBack<ArrayList<FeedbackModel>>() {
            @Override
            public void onLoadSuccess(ArrayList<FeedbackModel> result) {
                getViewState().setProgressVisible(View.GONE);
                getViewState().loadFeedback(result);
            }

            @Override
            public void onFail(String message) {
                getViewState().setRefreshVisible(View.VISIBLE);
            }
        });
    }
}
