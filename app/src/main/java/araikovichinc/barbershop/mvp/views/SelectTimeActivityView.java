package araikovichinc.barbershop.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.pojo.TimeModel;

/**
 * Created by Tigran on 05.03.2018.
 */

public interface SelectTimeActivityView extends MvpView {
    void loadTimeCards(ArrayList<TimeModel> times);
    void setProgressVisibility(int visibility);
    void showNoFreeTimeEx(int visibility);
    void setToolbarText(String text);
    void nextStep(Reservation reservation);
    void toastShow(String message);
    void showReload(int visibility);
}
