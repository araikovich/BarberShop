package araikovichinc.barbershop.datasource;

import araikovichinc.barbershop.callbacks.LoadCallBack;

/**
 * Created by Tigran on 05.03.2018.
 */

public interface FreeTimeDataSource {
    void loadFreeTimes(int hairdresserId, int totalTime, LoadCallBack callBack);
}
