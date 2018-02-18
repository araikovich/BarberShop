package araikovichinc.barbershop.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import araikovichinc.barbershop.mvp.views.BookCalendarFragmentView;

/**
 * Created by Tigran on 17.02.2018.
 */

@InjectViewState
public class BookCalendarFragmentPresenter extends MvpPresenter<BookCalendarFragmentView> {

    public void nextStep(CalendarDay day){
        getViewState().nextStep(day);
    }

    public void setTitle(String title){
        getViewState().setTitle(title);
    }
}
