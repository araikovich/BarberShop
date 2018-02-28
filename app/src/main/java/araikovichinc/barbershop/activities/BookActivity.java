package araikovichinc.barbershop.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.mvp.views.BookActivityView;
import araikovichinc.barbershop.pojo.HairdresserModel;
import araikovichinc.barbershop.pojo.ServiceModel;
import araikovichinc.barbershop.presenters.BookActivityPresenter;

/**
 * Created by Tigran on 28.02.2018.
 */


public class BookActivity extends MvpAppCompatActivity implements View.OnClickListener, BookActivityView {

    @InjectPresenter
    BookActivityPresenter presenter;


    Toolbar toolbar;
    Button genderBtn, dateBtn, hairdresserBtn, serviceBtn;
    BottomSheetBehavior genderSheet, calendarSheet, hairdresserSheet, serviceSheet;
    ImageView manImage, womanImage;
    MaterialCalendarView calendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();
    }

    private void initViews(){
        //init toolbar
        toolbar = (Toolbar)findViewById(R.id.book_toolbar);
        toolbar.setTitle(R.string.hairstyles);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //init buttons
        genderBtn = (Button)findViewById(R.id.select_gender_btn);
        dateBtn = (Button)findViewById(R.id.select_date_btn);
        hairdresserBtn = (Button)findViewById(R.id.select_hairdresser_btn);
        serviceBtn = (Button)findViewById(R.id.select_service_btn);

        genderBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);

        //init bottom sheets
            //gender bottom sheet
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gender_bottom_sheet);
        genderSheet = BottomSheetBehavior.from(linearLayout);
        manImage = (ImageView)findViewById(R.id.man_img);
        womanImage = (ImageView)findViewById(R.id.woman_img);
        Glide.with(this).load(R.drawable.man).into(manImage);
        Glide.with(this).load(R.drawable.woman).into(womanImage);
        manImage.setOnClickListener(this);
        womanImage.setOnClickListener(this);
            //calendar sheet
        linearLayout = (LinearLayout)findViewById(R.id.calendar_bottom_sheet);
        calendarSheet = BottomSheetBehavior.from(linearLayout);
        calendarView = (MaterialCalendarView)findViewById(R.id.calendar);
        calendarView.setCurrentDate(Calendar.getInstance());
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                presenter.setDate(date);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_gender_btn:
                presenter.showGenderSheet();
                break;
            case R.id.man_img:
                presenter.setGender(1);
                break;
            case R.id.woman_img:
                presenter.setGender(2);
                break;
            case R.id.select_date_btn:
                presenter.showCalendarSheet();
                break;
        }
    }

    @Override
    public void showGenderSheet(int state) {
        genderSheet.setState(state);
    }

    @Override
    public void showDateSheet(int state) {
        calendarSheet.setState(state);
    }

    @Override
    public void showHairdresserSheet(int state) {

    }

    @Override
    public void showServiceSheet(int state) {

    }

    @Override
    public void setSelectedServiceList(ArrayList<ServiceModel> serviceList) {

    }

    @Override
    public void setHairdressersList(ArrayList<HairdresserModel> hairdressersList) {

    }

    @Override
    public void setGenderView(String gender) {
        genderBtn.setText(gender);
    }

    @Override
    public void setDateView(String date) {
        dateBtn.setText(date);
    }

    @Override
    public void setHairdresserView(String hairdresser) {

    }

    @Override
    public void setSum(int sum) {

    }

    @Override
    public void toastShow(String toast) {

    }

}
