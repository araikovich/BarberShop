package araikovichinc.barbershop.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;

import araikovichinc.barbershop.pojo.HairdresserModel;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.pojo.ServiceModel;

/**
 * Created by Tigran on 28.02.2018.
 */

@StateStrategyType(SkipStrategy.class)
public interface BookActivityView extends MvpView {
    void showGenderSheet(int state);
    void showDateSheet(int state);
    void showHairdresserSheet(int state);
    void showServiceSheet(int state);
    void setSelectedServiceItem(ServiceModel serviceList);
    void setHairdressersList(ArrayList<HairdresserModel> hairdressersList);
    void setServiceList(ArrayList<ServiceModel> services);
    void setGenderView(String gender);
    void setDateView(String date);
    void setHairdresserView(String hairdresser);
    void setSum(int sum);
    void deleteSelectedItem(int serviceId);
    void toastShow(String toast);
    void clearReservation();
    void updateSum(int sum);
    void nextStep(Reservation reservation);
}
