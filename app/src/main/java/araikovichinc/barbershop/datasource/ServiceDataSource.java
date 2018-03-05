package araikovichinc.barbershop.datasource;

import araikovichinc.barbershop.callbacks.LoadCallBack;

/**
 * Created by Tigran on 04.03.2018.
 */

public interface ServiceDataSource {
    void loadServices(int genderId, LoadCallBack callBack);
}
