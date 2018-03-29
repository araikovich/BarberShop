package araikovichinc.barbershop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.adapters.SelectedServiceRecyclerAdapter;
import araikovichinc.barbershop.adapters.ServiceRecyclerAdapter;
import araikovichinc.barbershop.mvp.views.ConfirmReservationActivityView;
import araikovichinc.barbershop.pojo.ServiceModel;
import araikovichinc.barbershop.presenters.MyBookDetailActivityPresenter;

/**
 * Created by Tigran on 28.03.2018.
 */

public class MyBookDetailActivity extends MvpAppCompatActivity implements ConfirmReservationActivityView {

    @InjectPresenter
    MyBookDetailActivityPresenter presenter;

    Toolbar toolbar;
    TextView hairdresserName, time, date, totalSum;
    RecyclerView services;
    SelectedServiceRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book_detail);

        toolbar = (Toolbar)findViewById(R.id.book_detail_toolbar);
        toolbar.setTitle(R.string.more);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        hairdresserName = (TextView)findViewById(R.id.book_detail_master);
        time = (TextView)findViewById(R.id.book_detail_time);
        date = (TextView)findViewById(R.id.book_detail_date);
        totalSum = (TextView)findViewById(R.id.book_detail_total_sum);

        services = (RecyclerView)findViewById(R.id.book_detail_recycler);
        adapter = new SelectedServiceRecyclerAdapter();
        services.setLayoutManager(new LinearLayoutManager(this));
        services.setAdapter(adapter);
        presenter.setReservation(getIntent().getStringExtra("reservationId"));
    }
    @Override
    public void setHairdresserName(String name) {
        hairdresserName.setText(name);
    }

    @Override
    public void setDate(int day, int month, int year) {
        date.setText(day+"."+month+"."+year);
    }

    @Override
    public void setTime(int timeFromHour, int timeFromMin, int timeToHour, int timeToMin) {
        Date date = new Date(0,0,0,timeFromHour, timeFromMin);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.GERMAN);
        time.setText(simpleDateFormat.format(date));
        time.append(" - ");
        date = new Date(0,0,0,timeToHour, timeToMin);
        time.append(simpleDateFormat.format(date));
    }

    @Override
    public void setServiceList(ArrayList<ServiceModel> serviceList) {
        adapter.setSelectedServices(serviceList);
    }

    @Override
    public void confirm(int day, int month, int year) {

    }

    @Override
    public void setTotalSum(int sum) {
        totalSum.setText(sum+"");
    }
}
