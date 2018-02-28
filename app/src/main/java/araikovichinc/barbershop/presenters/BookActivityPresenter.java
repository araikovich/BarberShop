package araikovichinc.barbershop.presenters;

import android.content.res.Resources;
import android.support.design.widget.BottomSheetBehavior;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Calendar;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.R;
import araikovichinc.barbershop.mvp.views.BookActivityView;

/**
 * Created by Tigran on 28.02.2018.
 */

@InjectViewState
public class BookActivityPresenter extends MvpPresenter<BookActivityView> {

    private boolean isGenderSelected = false, isDateSelected = false, isHairdresserSelected = false, isServiceSelcted = false;

    public BookActivityPresenter(){

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
    }

    public void setDate(CalendarDay day){
        String selectedDay = day.getDay() + "." + (day.getMonth() + 1) + "." + day.getYear();
        getViewState().setDateView(selectedDay);
        getViewState().showDateSheet(BottomSheetBehavior.STATE_HIDDEN);
    }
}
