package araikovichinc.barbershop.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import araikovichinc.barbershop.pojo.Reservation;

/**
 * Created by Tigran on 28.03.2018.
 */

public interface MyBooksActivityView extends MvpView {
    void progressBarVisible(int visibility);
    void loadRecyclerList(ArrayList<Reservation> reservations);
    void showNullList(int visibility);
    void onDetailActivity(int reservationId);
}
