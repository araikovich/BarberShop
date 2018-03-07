package araikovichinc.barbershop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.adapters.TimesRecyclerAdapter;
import araikovichinc.barbershop.mvp.views.SelectTimeActivityView;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.pojo.TimeModel;
import araikovichinc.barbershop.presenters.SelectTimeActivityPresenter;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tigran on 05.03.2018.
 */

public class SelectTimeActivity extends MvpAppCompatActivity implements SelectTimeActivityView {

    @InjectPresenter
    SelectTimeActivityPresenter presenter;

    Reservation reservation;

    RecyclerView recyclerView;
    Toolbar toolbar;
    ProgressBar progressBar;
    CircleImageView reload;
    TimesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);
        initViews();
    }

    private void initViews() {
        Intent intent = getIntent();
        reservation = (Reservation) intent.getSerializableExtra("reservation");
        presenter.setReservation(reservation);

        toolbar = (Toolbar)findViewById(R.id.times_toolbar);
        toolbar.setTitle(R.string.select_time);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progressBar = (ProgressBar)findViewById(R.id.times_progress);
        reload = (CircleImageView)findViewById(R.id.times_refresh);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadTimes(1, 2);            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.times_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(adapter == null)
            adapter = new TimesRecyclerAdapter();
        adapter.setOnTimeClickListener(new TimesRecyclerAdapter.OnTimeClickListener() {
            @Override
            public void onTimeClick(int timeFromHour, int timeFromMin, int timeToHour, int timeToMin) {
                //presenter.onNextActivity(timeFromHour, timeFromMin, timeToHour, timeToMin);
            }
        });
        recyclerView.setAdapter(adapter);
        presenter.loadTimes(1, 2);
    }

    @Override
    public void loadTimeCards(ArrayList<TimeModel> times) {
        adapter.setTimes(times);
    }

    @Override
    public void setProgressVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void showNoFreeTimeEx(int visibility) {

    }

    @Override
    public void setToolbarText(String text) {

    }

    @Override
    public void nextStep(Reservation reservation) {
        Intent intent = new Intent(SelectTimeActivity.this, ConfirmReservationActivity.class);
        intent.putExtra("reservation", reservation);
        startActivity(intent);
    }

    @Override
    public void toastShow(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showReload(int visibility) {
        reload.setVisibility(visibility);
    }
}
