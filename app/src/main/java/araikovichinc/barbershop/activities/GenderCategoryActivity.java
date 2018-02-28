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
import araikovichinc.barbershop.adapters.GenderCategoriesRecyclerAdapter;
import araikovichinc.barbershop.callbacks.CategoryOnClickListener;
import araikovichinc.barbershop.mvp.views.GenderCategoriesFragmentView;
import araikovichinc.barbershop.pojo.GenderCard;
import araikovichinc.barbershop.presenters.GenderCategoriesActivityPresenter;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tigran on 28.02.2018.
 */

public class GenderCategoryActivity extends MvpAppCompatActivity implements GenderCategoriesFragmentView {

    @InjectPresenter
    GenderCategoriesActivityPresenter presenter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private GenderCategoriesRecyclerAdapter adapter;
    private CircleImageView reloadBtn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_categories);
        initViews();
        toolbar.setTitle(R.string.hairstyles);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initViews(){
        presenter.setTitle(getResources().getString(R.string.hairstyles));
        reloadBtn = (CircleImageView)findViewById(R.id.gender_categories_refresh);
        recyclerView = (RecyclerView)findViewById(R.id.gender_categories_recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.gender_categories_progress_bar);
        toolbar = (Toolbar)findViewById(R.id.gender_categories_toolbar);
        if(adapter == null)
            adapter = new GenderCategoriesRecyclerAdapter(this);
        else
            adapter.setContext(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(new CategoryOnClickListener() {
            @Override
            public void onClick(int genderId, String tittle) {
                presenter.nextActivity(genderId, tittle);
            }
        });
        recyclerView.setAdapter(adapter);

        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadData();
            }
        });


        if(!adapter.isLoaded())
            presenter.loadData();
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setAdapter(ArrayList<GenderCard> cards) {
        adapter.setItems(cards);
    }

    @Override
    public void setProgressVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void nextActivity(int genderId, String title) {
        Intent intent = new Intent(GenderCategoryActivity.this, HairstyleCategoryActivity.class);
        intent.putExtra("genderId", genderId);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(GenderCategoryActivity.this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRefreshVisibility(int visibility) {
        reloadBtn.setVisibility(visibility);
    }

}
