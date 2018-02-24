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
import araikovichinc.barbershop.adapters.HairstyleDetailRecyclerAdapter;
import araikovichinc.barbershop.mvp.views.HairstyleDetailActivityView;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;
import araikovichinc.barbershop.presenters.HairstyleDetailActivityPresenter;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tigran on 16.02.2018.
 */

public class HairstyleDetailActivity extends MvpAppCompatActivity implements HairstyleDetailActivityView{

    @InjectPresenter
    HairstyleDetailActivityPresenter presenter;

    RecyclerView recyclerView;
    ProgressBar progressBar;
    CircleImageView refresh;
    Toolbar toolbar;
    HairstyleDetailRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hairstyle_detail);
        initView();
    }

    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.hairstyle_detail_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.hairstyles_detail_recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.hairstyle_detail_progressbar);
        refresh = (CircleImageView)findViewById(R.id.hairstyle_detail_refresh);

        final Intent intent = getIntent();
        presenter.setTitle(intent.getStringExtra("hairstyleTitle"));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadCards(intent.getIntExtra("hairstyleId", 0));
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(adapter == null)
            adapter = new HairstyleDetailRecyclerAdapter(this);
        else
            adapter.setContext(this);
        recyclerView.setAdapter(adapter);


        if(!adapter.isLoaded())
            presenter.loadCards(intent.getIntExtra("hairstyleId", 0));


    }

    @Override
    public void setProgressBar(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void setAdapter(ArrayList<HairstyleDetailCard> cards) {
        adapter.setCards(cards);
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRefresh(int visibility) {
        refresh.setVisibility(visibility);
    }

}
