package araikovichinc.barbershop.datasource;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.callbacks.OnSaveCallBack;
import araikovichinc.barbershop.pojo.Reservation;

/**
 * Created by Tigran on 27.03.2018.
 */

public interface ReservationModelDataSource {
    void saveReservation(Reservation reservation);
    void loadReservationList(LoadCallBack callBack);
    void loadReservation(String reservationId, LoadCallBack callBack);
}
