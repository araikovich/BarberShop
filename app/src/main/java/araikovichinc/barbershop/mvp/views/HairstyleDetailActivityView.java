package araikovichinc.barbershop.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import araikovichinc.barbershop.pojo.HairstyleCategoryCard;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;

/**
 * Created by Tigran on 16.02.2018.
 */

public interface HairstyleDetailActivityView extends MvpView {
    void setProgress(int visibility);
    void setAdapter(ArrayList<HairstyleDetailCard> cards);
    void setTitle(String title);
    void showToast(String toast);
    void showRefresh(int visibility);
}
