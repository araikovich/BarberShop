package araikovichinc.barbershop.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import araikovichinc.barbershop.pojo.HairstyleCategoryCard;

/**
 * Created by Tigran on 13.02.2018.
 */

public interface HairstyleCategoryActivityView extends MvpView {
    void setProgress(int visibility);
    void setAdapter(ArrayList<HairstyleCategoryCard> cards);
    void setTitle(String title);
    void showToast(String toast);
    void showRefresh(int visibility);
}
