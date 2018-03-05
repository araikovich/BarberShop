package araikovichinc.barbershop.datasource;

import araikovichinc.barbershop.callbacks.LoadCallBack;

/**
 * Created by Tigran on 01.03.2018.
 */

public interface HairdressersDataSource {
    void loadHairdressers(int genderId, LoadCallBack callBack);
}
