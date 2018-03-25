package araikovichinc.barbershop.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.R;
import araikovichinc.barbershop.callbacks.OnSaveCallBack;
import araikovichinc.barbershop.mvp.views.AddFedbackActivityView;
import araikovichinc.barbershop.pojo.FeedbackModel;
import araikovichinc.barbershop.repository.FeedbackModelRepository;

/**
 * Created by Tigran on 23.03.2018.
 */

@InjectViewState
public class AddFeedbackActivityPresenter extends MvpPresenter<AddFedbackActivityView> {

    private FeedbackModel feedbackModel;

    @Inject
    FeedbackModelRepository repository;

    public void setCurrFragment(int currFragment) {
        this.currFragment = currFragment;
    }

    private int currFragment;

    public AddFeedbackActivityPresenter(){
        feedbackModel = new FeedbackModel();
        MyApp.getModelComponent().inject(this);
    }

    private void onNextPressed(){
        getViewState().setCurrentFragment(currFragment + 1);
        currFragment++;
    }

    public void onBackPressed(){
        getViewState().setCurrentFragment(currFragment - 1);
        currFragment--;
    }

    public void saveFeedback(String feedbackText ,int day, int month, int year){
        if(feedbackText.length() == 0){
            getViewState().showToastMessage(MyApp.getAppContext().getResources().getString(R.string.add_text));
        }else {
            feedbackModel.setText(feedbackText);
            feedbackModel.setDay(day);
            feedbackModel.setMonth(month);
            feedbackModel.setYear(year);
            repository.saveFeedback(feedbackModel, new OnSaveCallBack() {
                @Override
                public void onSaved() {
                    getViewState().showToastMessage(MyApp.getAppContext().getResources().getString(R.string.feedback_saved));
                    getViewState().finishActivity();
                }

                @Override
                public void onSaveFail() {
                    getViewState().showToastMessage(MyApp.getAppContext().getResources().getString(R.string.connection_error));
                }
            });
        }
    }

    public void onRatingNext(int rating){
        if(rating == 0){
            getViewState().showToastMessage(MyApp.getAppContext().getResources().getString(R.string.add_rating));
        }else {
            feedbackModel.setRating(rating);
            onNextPressed();
        }
    }

    public void onTitleNext(String title){
        if(title.length() == 0){
            getViewState().showToastMessage(MyApp.getAppContext().getResources().getString(R.string.add_title));
        }else {
            feedbackModel.setTitle(title);
            onNextPressed();
        }
    }
}
