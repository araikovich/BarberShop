package araikovichinc.barbershop.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import araikovichinc.barbershop.mvp.views.ConfirmReservationActivityView;
import araikovichinc.barbershop.pojo.Reservation;

/**
 * Created by Tigran on 11.03.2018.
 */

@InjectViewState
public class ConfirmReservationActivityPresenter extends MvpPresenter<ConfirmReservationActivityView> {

    private Reservation reservation;

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
        getViewState().confirm(reservation.getDay(), reservation.getMonth(), reservation.getYear());
    }
}
