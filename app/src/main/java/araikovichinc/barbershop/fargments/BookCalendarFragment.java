package araikovichinc.barbershop.fargments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.activities.SelectGenderActivity;
import araikovichinc.barbershop.callbacks.OnFragmentInteractionListener;
import araikovichinc.barbershop.components.DaggerReservationComponent;
import araikovichinc.barbershop.components.ReservationComponent;
import araikovichinc.barbershop.mvp.views.BookCalendarFragmentView;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.presenters.BookCalendarFragmentPresenter;
import info.hoang8f.widget.FButton;

/**
 * Created by Tigran on 17.02.2018.
 */

public class BookCalendarFragment extends MvpAppCompatFragment implements BookCalendarFragmentView{

    @InjectPresenter
    BookCalendarFragmentPresenter presenter;

    private OnFragmentInteractionListener listener;

    private MaterialCalendarView calendarView;
    private FButton bookBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_book_calendar, container, false);
        initView(v);
        return v;
    }

    private void initView(View v){
        calendarView = (MaterialCalendarView)v.findViewById(R.id.calendar);
        bookBtn = (FButton)v.findViewById(R.id.book_btn);

        presenter.setTitle(getString(R.string.select_date));

        calendarView.setCurrentDate(Calendar.getInstance());
        calendarView.setDateSelected(Calendar.getInstance(), true);

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.nextStep(calendarView.getSelectedDate());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void nextStep(CalendarDay day) {
        bookBtn.setEnabled(false);
        ReservationComponent component = DaggerReservationComponent.builder().build();
        Reservation reservation = component.getResevation();
        reservation.setDay(day.getDay());
        reservation.setMonth(day.getMonth());
        reservation.setYear(day.getYear());
        Intent intent = new Intent(getActivity(), SelectGenderActivity.class);
        intent.putExtra("Reservation", reservation);
        startActivity(intent);
    }

    @Override
    public void setTitle(String title) {
        if(listener!=null)
            listener.setTitle(title);
    }

    @Override
    public void onResume() {
        super.onResume();
        bookBtn.setEnabled(true);
    }
}
