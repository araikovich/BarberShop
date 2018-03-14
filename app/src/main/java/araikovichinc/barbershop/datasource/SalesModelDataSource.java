package araikovichinc.barbershop.datasource;

import araikovichinc.barbershop.callbacks.LoadCallBack;

/**
 * Created by Tigran on 13.03.2018.
 */

public interface SalesModelDataSource {
    void loadSales(LoadCallBack callBack);
}
