package araikovichinc.barbershop.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.mvp.views.ConfirmReservationActivityView;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.repository.ReservationModelRepository;

/**
 * Created by Tigran on 11.03.2018.
 */

@InjectViewState
public class ConfirmReservationActivityPresenter extends MvpPresenter<ConfirmReservationActivityView> {

    private Reservation reservation;

    @Inject
    ReservationModelRepository repository;

    public ConfirmReservationActivityPresenter(){
        MyApp.getModelComponent().inject(this);
    }

    public void setReservation(Reservation reservation){
        this.reservation = reservation;
        getViewState().setHairdresserName(reservation.getHairdresser().getName());
        getViewState().setDate(reservation.getDay(), reservation.getMonth(), reservation.getYear());
        getViewState().setTime(reservation.getTimeFromHour(), reservation.getTimeFromMin(),
                reservation.getTimeToHour(), reservation.getTimeToMin());
        getViewState().setServiceList(reservation.getServices());
        getViewState().setTotalSum(reservation.getTotalSum());
    }

    public void nextStep() {
        repository.saveReservation(reservation);
        getViewState().confirm(reservation.getDay(), reservation.getMonth(), reservation.getYear());
    }
}
