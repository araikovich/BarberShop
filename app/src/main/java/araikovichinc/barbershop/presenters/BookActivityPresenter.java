package araikovichinc.barbershop.presenters;

import android.support.design.widget.BottomSheetBehavior;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.R;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.mvp.views.BookActivityView;
import araikovichinc.barbershop.pojo.HairdresserModel;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.pojo.ServiceModel;
import araikovichinc.barbershop.repository.HairdressersRepository;
import araikovichinc.barbershop.repository.ServiceModelRepository;

/**
 * Created by Tigran on 28.02.2018.
 */

@InjectViewState
public class BookActivityPresenter extends MvpPresenter<BookActivityView> {

    @Inject
    HairdressersRepository hairdressersRepository;
    @Inject
    ServiceModelRepository serviceModelRepository;

    private boolean isGenderSelected = false, isDateSelected = false, isHairdresserSelected = false, isServiceSelcted = false;
    private Reservation reservation;

    public BookActivityPresenter(){
        MyApp.getModelComponent().inject(this);
        reservation = new Reservation();
    }

    public void showGenderSheet(){
        getViewState().showGenderSheet(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void showCalendarSheet(){
        getViewState().showDateSheet(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void showHairdresserSheet(){
        if(isGenderSelected)
            getViewState().showHairdresserSheet(BottomSheetBehavior.STATE_EXPANDED);
        else
            getViewState().toastShow(MyApp.getAppContext().getString(R.string.gender_not_selected));
    }

    public void showServiceSheet(){
        if(isGenderSelected)
            getViewState().showServiceSheet(BottomSheetBehavior.STATE_EXPANDED);
        else
            getViewState().toastShow(MyApp.getAppContext().getString(R.string.gender_not_selected));
    }

    public void setGender(int genderId){
        if(genderId == 1) {
            getViewState().setGenderView(MyApp.getAppContext().getResources().getString(R.string.man));
        }
        if(genderId == 2)
            getViewState().setGenderView(MyApp.getAppContext().getResources().getString(R.string.woman));
        isGenderSelected = true;
        getViewState().showGenderSheet(BottomSheetBehavior.STATE_HIDDEN);
        reservation.deleteHairdresser();
        getViewState().clearReservation();
        isHairdresserSelected =false;
        isServiceSelcted = false;
        loadHairdressers(genderId);
        loadService(genderId);
        getViewState().clearReservation();
        reservation.getServices().clear();
        getViewState().updateSum(reservation.getTotalSum());
    }

    public void setDate(CalendarDay day){
        isDateSelected = true;
        String selectedDay = day.getDay() + "." + (day.getMonth() + 1) + "." + day.getYear();
        getViewState().setDateView(selectedDay);
        getViewState().showDateSheet(BottomSheetBehavior.STATE_HIDDEN);
        reservation.setDay(day.getDay());
        reservation.setMonth(day.getMonth());
        reservation.setYear(day.getYear());
    }

    public void setHairdresser(String name, int hairdresserId, String photo){
        isHairdresserSelected = true;
        getViewState().setHairdresserView(name);
        getViewState().showHairdresserSheet(BottomSheetBehavior.STATE_HIDDEN);
        reservation.setHairdresser(hairdresserId, name, photo);
    }


    private void loadHairdressers(int genderId){
        hairdressersRepository.loadHairdressers(genderId, new LoadCallBack<ArrayList<HairdresserModel>>() {
            @Override
            public void onLoadSuccess(ArrayList<HairdresserModel> result) {
                getViewState().setHairdressersList(result);
            }

            @Override
            public void onFail(String message) {
                getViewState().toastShow(MyApp.getAppContext().getString(R.string.connection_error));
            }
        });
    }

    private void loadService(int genderId){
        serviceModelRepository.loadServices(genderId, new LoadCallBack<ArrayList<ServiceModel>>() {
            @Override
            public void onLoadSuccess(ArrayList<ServiceModel> result) {
                getViewState().setServiceList(result);
            }

            @Override
            public void onFail(String message) {
                getViewState().toastShow(MyApp.getAppContext().getString(R.string.connection_error));
            }
        });
    }


    public void addService(ServiceModel service) {
        reservation.setTotalSum(reservation.getTotalSum() + service.getPrice());
        reservation.addSrvice(service);
        getViewState().setSelectedServiceItem(service);
        getViewState().updateSum(reservation.getTotalSum());
        isServiceSelcted = true;
    }

    public void deleteService(int serviceId) {
        reservation.deleteService(serviceId);
        getViewState().deleteSelectedItem(serviceId);
        getViewState().updateSum(reservation.getTotalSum());
    }

    public void onSelectTimeActivity(){
        if(isGenderSelected && isDateSelected && isHairdresserSelected && isServiceSelcted)
            getViewState().nextStep(reservation);
        else
            getViewState().toastShow(MyApp.getAppContext().getResources().getString(R.string.insert_all_data));
    }


}
