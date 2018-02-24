package araikovichinc.barbershop.datasource;

import java.util.ArrayList;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.pojo.GenderCard;

/**
 * Created by Tigran on 23.02.2018.
 */

public interface GenderCardsDataSource {
    void getCards(LoadCallBack callBack);
    void saveCardsToDb(ArrayList<GenderCard> cards);
}
