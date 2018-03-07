package araikovichinc.barbershop.presenters;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.R;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.mvp.views.SelectTimeActivityView;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.pojo.TimeModel;
import araikovichinc.barbershop.repository.FreeTimeRepository;

/**
 * Created by Tigran on 05.03.2018.
 */

@InjectViewState
public class SelectTimeActivityPresenter extends MvpPresenter<SelectTimeActivityView> {

    @Inject
    FreeTimeRepository repository;

    Reservation reservation;

    public SelectTimeActivityPresenter(){
        MyApp.getModelComponent().inject(this);
    }

    public void loadTimes(int hairdresserId, int totalTime){
        getViewState().showReload(View.GONE);
        repository.loadFreeTimes(hairdresserId, totalTime, new LoadCallBack<ArrayList<TimeModel>>() {
            @Override
            public void onLoadSuccess(ArrayList<TimeModel> result) {
                getViewState().setProgressVisibility(View.GONE);
                getViewState().loadTimeCards(result);
            }

            @Override
            public void onFail(String message) {
                getViewState().showReload(View.VISIBLE);
                getViewState().setProgressVisibility(View.GONE);
                getViewState().toastShow(MyApp.getAppContext().getResources().getString(R.string.connection_error));
            }
        });
    }

    public void setReservation(Reservation reservation){
        this.reservation = reservation;
    }

    public void onNextActivity(int timeFromHour, int timeFromMin, int timeToHour, int timeToMin){
        this.reservation.setTimeFromHour(timeFromHour);
        this.reservation.setTimeToHour(timeToHour);
        this.reservation.setTimeFromMin(timeFromMin);
        this.reservation.setTimeToMin(timeToMin);
        getViewState().nextStep(reservation);
    }
}
