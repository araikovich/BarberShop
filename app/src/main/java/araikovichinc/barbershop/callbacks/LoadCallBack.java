package araikovichinc.barbershop.callbacks;

import java.util.ArrayList;

import araikovichinc.barbershop.pojo.GenderCard;

/**
 * Created by Tigran on 12.02.2018.
 */

public interface LoadCallBack<T> {
    void onLoadSuccess(T result);
    void onFail(String message);
}
