package araikovichinc.barbershop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.adapters.MyBooksRecyclerAdapter;
import araikovichinc.barbershop.mvp.views.MyBooksActivityView;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.presenters.MyBooksActivityPresenter;

/**
 * Created by Tigran on 27.03.2018.
 */

public class MyBooksActivity extends MvpAppCompatActivity implements MyBooksActivityView{

    @InjectPresenter
    MyBooksActivityPresenter presenter;

    Toolbar toolbar;
    RecyclerView recyclerView;
    MyBooksRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);

        toolbar = (Toolbar) findViewById(R.id.my_books_toolbar);
        toolbar.setTitle(R.string.feedback);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.my_books_recycler);
        adapter = new MyBooksRecyclerAdapter(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new MyBooksRecyclerAdapter.OnMyReservationClickListener() {
            @Override
            public void onReservationItemClick(String reservationId) {
                Intent intent = new Intent(MyBooksActivity.this, MyBookDetailActivity.class);
                intent.putExtra("reservationId", reservationId);
                startActivity(intent);
            }
        });
        presenter.loadReservations();
    }

    @Override
    public void progressBarVisible(int visibility) {

    }

    @Override
    public void loadRecyclerList(ArrayList<Reservation> reservations) {
        adapter.setReservations(reservations);
    }

    @Override
    public void showNullList(int visibility) {

    }

    @Override
    public void onDetailActivity(int reservationId) {

    }
}
