package araikovichinc.barbershop.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import araikovichinc.barbershop.pojo.FeedbackModel;

/**
 * Created by Tigran on 17.03.2018.
 */

public interface FeedbackActivityView extends MvpView {
    void loadFeedback(ArrayList<FeedbackModel> feedback);
    void setProgressVisible(int visibility);
    void setRefreshVisible(int visibility);
    void next();
}
