package araikovichinc.barbershop.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;

import araikovichinc.barbershop.pojo.HairdresserModel;
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
    void setSelectedServiceList(ArrayList<ServiceModel> serviceList);
    void setHairdressersList(ArrayList<HairdresserModel> hairdressersList);
    void setGenderView(String gender);
    void setDateView(String date);
    void setHairdresserView(String hairdresser);
    void setSum(int sum);
    void toastShow(String toast);
}
