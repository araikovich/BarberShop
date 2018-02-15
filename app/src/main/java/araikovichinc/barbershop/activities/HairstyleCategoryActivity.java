package araikovichinc.barbershop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.adapters.HairstyleCategoryRecyclerAdapter;
import araikovichinc.barbershop.components.DaggerModelComponent;
import araikovichinc.barbershop.components.ModelComponent;
import araikovichinc.barbershop.models.GenderCategoryModel;
import araikovichinc.barbershop.models.HairstyleCategoryModel;
import araikovichinc.barbershop.modules.ContextModule;
import araikovichinc.barbershop.mvp.views.HairstyleCategoryActivityView;
import araikovichinc.barbershop.pojo.HairstyleCategoryCard;
import araikovichinc.barbershop.presenters.HairstyleCategoryActivityPresenter;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tigran on 13.02.2018.
 */

public class HairstyleCategoryActivity extends MvpAppCompatActivity implements HairstyleCategoryActivityView {

    @InjectPresenter
    HairstyleCategoryActivityPresenter presenter;

    Toolbar toolbar;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    HairstyleCategoryRecyclerAdapter adapter;
    CircleImageView refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hairstyle_category);
        initView();
    }

    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.hairstyle_categories_toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.hairstyles_category_recycler_view);
        progressBar = (ProgressBar)findViewById(R.id.hairstyle_categories_progressbar);
        refresh = (CircleImageView)findViewById(R.id.hairstyle_categories_refresh);

        //set up toolbar;
        final Intent intent = getIntent();
        presenter.changetitle(intent.getStringExtra("title"));
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
                presenter.setCards(intent.getIntExtra("genderId", 0));
            }
        });

        //set up recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(adapter == null){
            adapter = new HairstyleCategoryRecyclerAdapter(this);
        }else {
            adapter.setContext(this);
        }
        recyclerView.setAdapter(adapter);

        //getting model
        ModelComponent component = DaggerModelComponent.builder().contextModule(new ContextModule(this)).build();
        HairstyleCategoryModel model = component.getHairstyleCategoryModel();
        presenter.setModel(model);
        //load data
        if (!adapter.isLoaded())
            presenter.setCards(intent.getIntExtra("genderId", 0));
    }

    @Override
    public void setAdapter(ArrayList<HairstyleCategoryCard> cards) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyLogs", "On Destroy");
    }
}
