package araikovichinc.barbershop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.adapters.HairdressersRecyclerAdapter;
import araikovichinc.barbershop.adapters.SelectedServiceRecyclerAdapter;
import araikovichinc.barbershop.adapters.ServiceRecyclerAdapter;
import araikovichinc.barbershop.mvp.views.BookActivityView;
import araikovichinc.barbershop.pojo.HairdresserModel;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.pojo.ServiceModel;
import araikovichinc.barbershop.presenters.BookActivityPresenter;

/**
 * Created by Tigran on 28.02.2018.
 */


public class BookActivity extends MvpAppCompatActivity implements View.OnClickListener, BookActivityView {

    @InjectPresenter
    BookActivityPresenter presenter;


    Toolbar toolbar;
    Button genderBtn, dateBtn, hairdresserBtn, serviceBtn, nextStepBtn;
    BottomSheetBehavior genderSheet, calendarSheet, hairdresserSheet, serviceSheet;
    ImageView manImage, womanImage;
    MaterialCalendarView calendarView;
    RecyclerView hairdressersRecycler, serviceListRecycler, serviceSelectedListRecycler;
    HairdressersRecyclerAdapter hairdressersRecyclerAdapter;
    ServiceRecyclerAdapter serviceRecyclerAdapter;
    SelectedServiceRecyclerAdapter selectedServiceRecyclerAdapter;
    TextView totalSum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();
    }

    private void initViews(){
        //init toolbar
        toolbar = (Toolbar)findViewById(R.id.book_toolbar);
        toolbar.setTitle(R.string.insert_data);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        totalSum = (TextView)findViewById(R.id.total_sum);

        //init buttons
        genderBtn = (Button)findViewById(R.id.select_gender_btn);
        dateBtn = (Button)findViewById(R.id.select_date_btn);
        hairdresserBtn = (Button)findViewById(R.id.select_hairdresser_btn);
        serviceBtn = (Button)findViewById(R.id.select_service_btn);
        nextStepBtn = (Button)findViewById(R.id.confirm_reservation);

        genderBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);
        hairdresserBtn.setOnClickListener(this);
        serviceBtn.setOnClickListener(this);
        nextStepBtn.setOnClickListener(this);

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
            //hairdressers sheet
        linearLayout = (LinearLayout)findViewById(R.id.hairdressers_bottom_sheet);
        hairdresserSheet = BottomSheetBehavior.from(linearLayout);
        hairdressersRecycler = (RecyclerView)findViewById(R.id.hairdressers_recycler);
        hairdressersRecycler.setLayoutManager(new LinearLayoutManager(this));
        hairdressersRecyclerAdapter = new HairdressersRecyclerAdapter(getApplicationContext(), hairdressersRecycler);
        hairdressersRecycler.setAdapter(hairdressersRecyclerAdapter);
        hairdressersRecyclerAdapter.setOnClick(new HairdressersRecyclerAdapter.OnHairdresserClick() {
            @Override
            public void onClick(int hairdresserId, String name, String photo) {
                presenter.setHairdresser(name, hairdresserId, photo);
            }

            @Override
            public void onClickSamePosition() {
                showHairdresserSheet(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
            //service sheet
        linearLayout = (LinearLayout)findViewById(R.id.service_bottom_sheet);
        serviceSheet = BottomSheetBehavior.from(linearLayout);
        serviceListRecycler = (RecyclerView)findViewById(R.id.service_recycler);
        serviceListRecycler.setLayoutManager(new LinearLayoutManager(this));
        if(serviceRecyclerAdapter == null)
            serviceRecyclerAdapter = new ServiceRecyclerAdapter();
        serviceListRecycler.setAdapter(serviceRecyclerAdapter);
        serviceRecyclerAdapter.setOnServiceClickListener(new ServiceRecyclerAdapter.OnServiceClickListener() {
            @Override
            public void onServiceClick(ServiceModel service) {
                presenter.addService(service);
            }

            @Override
            public void onAgainClick(int serviceId) {
                presenter.deleteService(serviceId);
            }
        });
            //SelectedServicesRecycler
        serviceSelectedListRecycler = (RecyclerView)findViewById(R.id.services_list_recycler);
        serviceSelectedListRecycler.setLayoutManager(new LinearLayoutManager(this));
        if(selectedServiceRecyclerAdapter == null){
            selectedServiceRecyclerAdapter = new SelectedServiceRecyclerAdapter();
        }
        serviceSelectedListRecycler.setAdapter(selectedServiceRecyclerAdapter);

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
            case R.id.select_hairdresser_btn:
                presenter.showHairdresserSheet();
                break;
            case R.id.select_service_btn:
                presenter.showServiceSheet();
                break;
            case R.id.confirm_reservation:
                presenter.onSelectTimeActivity();
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
        hairdresserSheet.setState(state);
    }

    @Override
    public void showServiceSheet(int state) {
        serviceSheet.setState(state);
    }

    @Override
    public void setSelectedServiceItem(ServiceModel service) {
        selectedServiceRecyclerAdapter.setSelectedService(service);
    }

    @Override
    public void setHairdressersList(ArrayList<HairdresserModel> hairdressersList) {
        hairdressersRecyclerAdapter.setHairdressers(hairdressersList);
    }

    @Override
    public void setServiceList(ArrayList<ServiceModel> services) {
        serviceRecyclerAdapter.setServices(services);
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
        hairdresserBtn.setText(getResources().getText(R.string.master) + " " + hairdresser);
    }

    @Override
    public void setSum(int sum) {

    }

    @Override
    public void deleteSelectedItem(int serviceId) {
        selectedServiceRecyclerAdapter.deleteService(serviceId);
    }

    @Override
    public void toastShow(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearReservation() {
        hairdresserBtn.setText(getText(R.string.select_master));
        selectedServiceRecyclerAdapter.clearList();
    }

    @Override
    public void updateSum(int sum) {
        totalSum.setText(sum + "");
    }

    @Override
    public void nextStep(Reservation reservation) {
        Intent intent = new Intent(BookActivity.this, SelectTimeActivity.class);
        intent.putExtra("reservation", reservation);
        startActivity(intent);
    }


}
