package araikovichinc.barbershop.datasource;

import java.util.ArrayList;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.pojo.HairstyleCategoryCard;

/**
 * Created by Tigran on 24.02.2018.
 */

public interface HairstyleCategoryDataSource {
    void getHairstyleCategories(int genderId, LoadCallBack callBack);
    void saveCategories(ArrayList<HairstyleCategoryCard> cards);
}
