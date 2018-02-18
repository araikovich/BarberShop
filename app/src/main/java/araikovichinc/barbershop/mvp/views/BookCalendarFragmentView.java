package araikovichinc.barbershop.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.prolificinteractive.materialcalendarview.CalendarDay;

/**
 * Created by Tigran on 17.02.2018.
 */

public interface BookCalendarFragmentView extends MvpView {
    void nextStep(CalendarDay day);
    void setTitle(String title);
}
