package araikovichinc.barbershop.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.mvp.views.MyBooksActivityView;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.repository.ReservationModelRepository;

/**
 * Created by Tigran on 28.03.2018.
 */

@InjectViewState
public class MyBooksActivityPresenter extends MvpPresenter<MyBooksActivityView> {
    @Inject
    ReservationModelRepository repository;

    public MyBooksActivityPresenter(){
        MyApp.getModelComponent().inject(this);
    }

    public void loadReservations(){
        repository.loadReservationList(new LoadCallBack<ArrayList<Reservation>>() {
            @Override
            public void onLoadSuccess(ArrayList<Reservation> result) {
                getViewState().loadRecyclerList(result);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
