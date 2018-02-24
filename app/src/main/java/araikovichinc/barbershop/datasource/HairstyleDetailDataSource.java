package araikovichinc.barbershop.datasource;

import java.util.ArrayList;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;

/**
 * Created by Tigran on 24.02.2018.
 */

public interface HairstyleDetailDataSource {
    void loadHairstyleDetail(int hairstyleId, LoadCallBack callBack);
    void saveHairstyleDetail(ArrayList<HairstyleDetailCard> cards);
}
