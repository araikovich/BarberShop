package araikovichinc.barbershop.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.adapters.AddFeedbackPagerAdapter;
import araikovichinc.barbershop.callbacks.OnAddFeedbackFragmentsActionListener;
import araikovichinc.barbershop.custom_views.CustomViewPager;
import araikovichinc.barbershop.fragments.AddFeedbackTextFragment;
import araikovichinc.barbershop.fragments.AddFeedbackTitleFragment;
import araikovichinc.barbershop.fragments.RatingBarFragment;
import araikovichinc.barbershop.mvp.views.AddFedbackActivityView;
import araikovichinc.barbershop.presenters.AddFeedbackActivityPresenter;

/**
 * Created by Tigran on 22.03.2018.
 */

public class AddFeedbackActivity extends MvpAppCompatActivity implements OnAddFeedbackFragmentsActionListener,
                                                                            AddFedbackActivityView{
    @InjectPresenter
    AddFeedbackActivityPresenter presenter;

    CustomViewPager viewPager;
    private LinearLayout pagerIndicator;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fedback);
        init();
    }

    private void init(){
        viewPager = (CustomViewPager) findViewById(R.id.add_feedback_view_pager);
        viewPager.setPagingEnabled(false);
        AddFeedbackPagerAdapter adapter = new AddFeedbackPagerAdapter(getSupportFragmentManager());
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RatingBarFragment());
        fragments.add(new AddFeedbackTitleFragment());
        fragments.add(new AddFeedbackTextFragment());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);

        pagerIndicator = (LinearLayout)findViewById(R.id.viewPagerCountDots);
        dots = new ImageView[3];

        for (int i = 0; i < 3; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselected_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pagerIndicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selected_item_dot));
    }

    @Override
    public void onAddRatingNextClick(float rating) {
        presenter.setCurrFragment(viewPager.getCurrentItem());
        presenter.onRatingNext(Math.round(rating));
    }

    @Override
    public void onAddTitleBackClick() {
        presenter.onBackPressed();
    }

    @Override
    public void onAddTitleNextClick(String title) {
        presenter.setCurrFragment(viewPager.getCurrentItem());
        presenter.onTitleNext(title);
    }

    @Override
    public void onAddTextBackClick() {
        presenter.onBackPressed();
    }

    @Override
    public void onAddTextDoneClick(String text, int day, int month, int year) {
        presenter.saveFeedback(text, day, month, year);
    }

    @Override
    public void setCurrentFragment(int fragmentId) {
        for (int i = 0; i < 3; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselected_item_dot));
        }

        dots[fragmentId].setImageDrawable(getResources().getDrawable(R.drawable.selected_item_dot));
        viewPager.setCurrentItem(fragmentId);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
