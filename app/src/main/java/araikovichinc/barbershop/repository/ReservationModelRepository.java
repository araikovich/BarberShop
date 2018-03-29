package araikovichinc.barbershop.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.ReservationModelDataSource;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.repository.local.ReservationModelLocalDataSource;

/**
 * Created by Tigran on 27.03.2018.
 */

public class ReservationModelRepository implements ReservationModelDataSource {

    ReservationModelLocalDataSource localDataSource;

    @Inject
    public ReservationModelRepository(ReservationModelLocalDataSource localDataSource){
        this.localDataSource = localDataSource;
    }

    @Override
    public void saveReservation(Reservation reservation) {
        localDataSource.saveReservation(reservation);
    }

    @Override
    public void loadReservationList(final LoadCallBack callBack) {
        localDataSource.loadReservationList(new LoadCallBack<ArrayList<Reservation>>() {
            @Override
            public void onLoadSuccess(ArrayList<Reservation> result) {
                callBack.onLoadSuccess(result);
            }

            @Override
            public void onFail(String message) {
                callBack.onFail(message);
            }
        });
    }

    @Override
    public void loadReservation(String reservationId, LoadCallBack callBack) {
        localDataSource.loadReservation(reservationId, callBack);
    }
}
