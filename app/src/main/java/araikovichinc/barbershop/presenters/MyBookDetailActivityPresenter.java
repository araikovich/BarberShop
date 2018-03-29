package araikovichinc.barbershop.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.mvp.views.ConfirmReservationActivityView;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.repository.ReservationModelRepository;

/**
 * Created by Tigran on 28.03.2018.
 */

@InjectViewState
public class MyBookDetailActivityPresenter extends MvpPresenter<ConfirmReservationActivityView> {

    @Inject
    ReservationModelRepository repository;

    public MyBookDetailActivityPresenter(){
        MyApp.getModelComponent().inject(this);
    }

    public void setReservation(String reservationId){
        repository.loadReservation(reservationId, new LoadCallBack<Reservation>() {
            @Override
            public void onLoadSuccess(Reservation result) {
                getViewState().setTotalSum(result.getTotalSum());
                getViewState().setTime(result.getTimeFromHour(), result.getTimeFromMin(), result.getTimeToHour(), result.getTimeToMin());
                getViewState().setDate(result.getDay(), result.getMonth(), result.getYear());
                getViewState().setHairdresserName(result.getHairdresser().getName());
                getViewState().setServiceList(result.getServices());
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
