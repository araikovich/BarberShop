package araikovichinc.barbershop.mvp.views;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Tigran on 11.02.2018.
 */


@StateStrategyType(SkipStrategy.class)
public interface HomeActivityView extends MvpView {
    void setFragment(Fragment fragment);
    void setTitle(String title);
    void initViews();
    void onGenderActivity();
    void onBookActivity();
}
