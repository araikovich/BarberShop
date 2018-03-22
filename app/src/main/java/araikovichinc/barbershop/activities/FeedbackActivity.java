package araikovichinc.barbershop.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.adapters.FeedbackRecyclerAdapter;
import araikovichinc.barbershop.mvp.views.FeedbackActivityView;
import araikovichinc.barbershop.pojo.FeedbackModel;
import araikovichinc.barbershop.presenters.FeedbackActivityPresenter;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tigran on 14.03.2018.
 */

public class FeedbackActivity extends MvpAppCompatActivity implements FeedbackActivityView {

    @InjectPresenter
    FeedbackActivityPresenter presenter;

    private RecyclerView feedbackList;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private CircleImageView reloadBtn;
    private FeedbackRecyclerAdapter adapter;
    private FloatingActionButton addFeedbackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);
        init();
    }

    private void init(){
        toolbar = (Toolbar)findViewById(R.id.feedback_toolbar);
        toolbar.setTitle(R.string.feedback);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addFeedbackBtn = (FloatingActionButton)findViewById(R.id.add_feedback_btn);
        addFeedbackBtn.setImageResource(R.drawable.add_feedback);
        addFeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        progressBar = (ProgressBar)findViewById(R.id.feedback_progress_bar);
        reloadBtn = (CircleImageView)findViewById(R.id.feedback_refresh);
        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        feedbackList = (RecyclerView)findViewById(R.id.feedback_recycler_view);
        feedbackList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FeedbackRecyclerAdapter();
        feedbackList.setAdapter(adapter);
        feedbackList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && addFeedbackBtn.getVisibility() == View.VISIBLE) {
                    addFeedbackBtn.hide();
                } else if (dy < 0 && addFeedbackBtn.getVisibility() != View.VISIBLE) {
                    addFeedbackBtn.show();
                }
            }
        });

        presenter.loadFeedback();
    }

    @Override
    public void loadFeedback(ArrayList<FeedbackModel> feedback) {
        adapter.setFeedback(feedback);
    }

    @Override
    public void setProgressVisible(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void setRefreshVisible(int visibility) {
        reloadBtn.setVisibility(visibility);
    }

    @Override
    public void next() {

    }
}
