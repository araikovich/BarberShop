package araikovichinc.barbershop.fargments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.activities.HairstyleCategoryActivity;
import araikovichinc.barbershop.adapters.GenderCategoriesRecyclerAdapter;
import araikovichinc.barbershop.callbacks.CategoryOnClickListener;
import araikovichinc.barbershop.callbacks.OnFragmentInteractionListener;
import araikovichinc.barbershop.mvp.views.GenderCategoriesFragmentView;
import araikovichinc.barbershop.pojo.GenderCard;
import araikovichinc.barbershop.presenters.GenderCategoriesFragmentPresenter;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tigran on 11.02.2018.
 */

public class GenderCategoriesFragment extends MvpAppCompatFragment implements GenderCategoriesFragmentView {

    @InjectPresenter
    GenderCategoriesFragmentPresenter presenter;

    private OnFragmentInteractionListener listener;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private GenderCategoriesRecyclerAdapter adapter;
    private CircleImageView reloadBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gender_categories, container, false);
        initViews(v);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        //
        adapter.setContext(null);
    }

    @Override
    public void setTitle(String title) {
        if(listener!=null)
            listener.setTitle(title);
    }

    @Override
    public void setAdapter(ArrayList<GenderCard> cards) {
        adapter.setItems(cards);
    }

    @Override
    public void setProgress(int visibility) {
        progressBar.setVisibility(visibility);
    }

    private void initViews(View v){
        presenter.setTitle(getResources().getString(R.string.hairstyles));
        reloadBtn = (CircleImageView)v.findViewById(R.id.gender_categories_refresh);
        recyclerView = (RecyclerView)v.findViewById(R.id.gender_categories_recycler_view);
        progressBar = (ProgressBar) v.findViewById(R.id.gender_categories_progress_bar);
        if(adapter == null)
            adapter = new GenderCategoriesRecyclerAdapter(getContext());
        else
            adapter.setContext(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
    public void nextActivity(int genderId, String title){
        Log.d("MyLogs", "Call nextActivity");
        Intent intent = new Intent(getActivity(), HairstyleCategoryActivity.class);
        intent.putExtra("genderId", genderId);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRefreshVisibility(int visibility) {
        reloadBtn.setVisibility(visibility);
    }

}
