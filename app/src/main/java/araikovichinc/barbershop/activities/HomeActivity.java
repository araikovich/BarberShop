package araikovichinc.barbershop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.adapters.SalesRecyclerAdapter;
import araikovichinc.barbershop.callbacks.OnAddFeedbackFragmentsActionListener;
import araikovichinc.barbershop.mvp.views.HomeActivityView;
import araikovichinc.barbershop.pojo.SaleModel;
import araikovichinc.barbershop.presenters.HomeActivityPresenter;


public class HomeActivity extends MvpAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeActivityView{

    @InjectPresenter
    HomeActivityPresenter presenter;

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    RecyclerView salesRecycler;
    SalesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("MyLogs", "Home activity onCreate");
        initViews();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        presenter.changePage(item.getItemId());
        return true;
    }

    @Override
    public void initViews(){
        //init toolbar
        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        toolbar.setTitle(R.string.Sales);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlack));

        //drawer and toggle
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //navigation view
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Sales
        salesRecycler = (RecyclerView)findViewById(R.id.sales_recycler);
        salesRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SalesRecyclerAdapter(getApplicationContext());
        salesRecycler.setAdapter(adapter);
        presenter.loadSales();
    }

    @Override
    public void onGenderActivity() {
        Intent intent = new Intent(HomeActivity.this, GenderCategoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBookActivity() {
        Intent intent = new Intent(HomeActivity.this, BookActivity.class);
        startActivity(intent);
    }

    @Override
    public void setSales(ArrayList<SaleModel> sales) {
        adapter.setSales(sales);
    }

    @Override
    public void onFeedbackActivity() {
        Intent intent = new Intent(HomeActivity.this, FeedbackActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMapActivity() {
        Intent intent = new Intent(HomeActivity.this, WhereWeAreActivity.class);
        startActivity(intent);
    }

    @Override
    public void onContactsActivity() {
        Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMyBooksActivity() {
        Intent intent = new Intent(HomeActivity.this, MyBooksActivity.class);
        startActivity(intent);
    }

    @Override
    public void setFragment(Fragment fragment) {

    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }
}