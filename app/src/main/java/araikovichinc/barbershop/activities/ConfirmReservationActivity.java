package araikovichinc.barbershop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.adapters.SelectedServiceRecyclerAdapter;
import araikovichinc.barbershop.mvp.views.ConfirmReservationActivityView;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.pojo.ServiceModel;
import araikovichinc.barbershop.presenters.ConfirmReservationActivityPresenter;

/**
 * Created by Tigran on 05.03.2018.
 */

public class ConfirmReservationActivity extends MvpAppCompatActivity implements ConfirmReservationActivityView {

    @InjectPresenter
    ConfirmReservationActivityPresenter presenter;

    private TextView hairdressersName, dateText, timeText, totalSumText;
    private Toolbar toolbar;
    private RecyclerView serviceList;
    private Button confirmBtn;
    private SelectedServiceRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reservation);
        init();
    }

    private void init(){
        hairdressersName = (TextView)findViewById(R.id.confirm_master);
        dateText = (TextView)findViewById(R.id.confirm_date);
        timeText = (TextView)findViewById(R.id.confirm_time);
        totalSumText = (TextView)findViewById(R.id.confirm_total_sum);

        toolbar = (Toolbar) findViewById(R.id.confirm_toolbar);
        toolbar.setTitle(R.string.confirm_reservation);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        serviceList = (RecyclerView)findViewById(R.id.confirm_recycler);
        serviceList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectedServiceRecyclerAdapter();
        serviceList.setAdapter(adapter);

        Intent intent = getIntent();
        Reservation reservation = (Reservation) intent.getSerializableExtra("reservation");
        presenter.setReservation(reservation);

        confirmBtn = (Button)findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.nextStep();
            }
        });
    }

    @Override
    public void setHairdresserName(String name) {
        hairdressersName.setText(name);
    }

    @Override
    public void setDate(int day, int month, int year) {
        dateText.setText(day + "." + month + "." + year);
    }

    @Override
    public void setTime(int timeFromHour, int timeFromMin, int timeToHour, int timeToMin) {
        Date date = new Date(0,0,0,timeFromHour, timeFromMin);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.GERMAN);
        timeText.setText(simpleDateFormat.format(date));
        timeText.append(" - ");
        date = new Date(0,0,0,timeToHour, timeToMin);
        timeText.append(simpleDateFormat.format(date));
    }

    @Override
    public void setServiceList(ArrayList<ServiceModel> serviceList) {
        adapter.setSelectedServices(serviceList);
    }

    @Override
    public void confirm(int day, int month, int year) {
        Intent intent = new Intent(ConfirmReservationActivity.this, BookFinishActivity.class);
        intent.putExtra("day", day);
        intent.putExtra("month", month);
        intent.putExtra("year", year);
        startActivity(intent);
    }

    @Override
    public void setTotalSum(int sum) {
        totalSumText.setText(sum + "");
    }
}
