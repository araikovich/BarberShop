package araikovichinc.barbershop.mvp.views;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Tigran on 11.02.2018.
 */


public interface HomeActivityView extends MvpView {
    void setFragment(Fragment fragment);
    void setTitle(String title);
}
