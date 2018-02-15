package araikovichinc.barbershop.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import araikovichinc.barbershop.pojo.GenderCard;

/**
 * Created by Tigran on 12.02.2018.
 */

public interface GenderCategoriesFragmentView extends MvpView {
    void setTitle(String title);
    void setAdapter(ArrayList<GenderCard> cards);
    void setProgress(int visibility);
    void nextActivity(int genderId, String title);
    void showToast(String toast);
    void setRefreshVisibility(int visibility);
}
