package araikovichinc.barbershop.datasource;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.callbacks.OnDeleteCallBack;
import araikovichinc.barbershop.callbacks.OnSaveCallBack;
import araikovichinc.barbershop.pojo.FeedbackModel;

/**
 * Created by Tigran on 18.03.2018.
 */

public interface FeedbackDataSource {
    void loadFeedback(LoadCallBack callBack);
    void saveFeedback(FeedbackModel model, OnSaveCallBack callBack);
    void deleteFeedback(int id, OnDeleteCallBack callBack);
}
