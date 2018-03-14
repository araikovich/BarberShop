package araikovichinc.barbershop.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import araikovichinc.barbershop.pojo.ServiceModel;

/**
 * Created by Tigran on 11.03.2018.
 */

public interface ConfirmReservationActivityView extends MvpView {
    void setHairdresserName(String name);

    void setDate(int day, int month, int year);

    void setTime(int timeFromHour, int timeFromMin, int timeToHour, int timeToMin);

    void setServiceList(ArrayList<ServiceModel> serviceList);

    void confirm(int day, int month, int year);

    void setTotalSum(int sum);
}
