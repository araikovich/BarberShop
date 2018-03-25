package araikovichinc.barbershop.mvp.views;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Tigran on 23.03.2018.
 */

public interface AddFedbackActivityView extends MvpView {
    void setCurrentFragment(int fragmentId);
    void showToastMessage(String message);
    void finishActivity();
}
