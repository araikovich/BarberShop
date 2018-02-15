package araikovichinc.barbershop.modules;

import android.content.Context;

import araikovichinc.barbershop.utils.DBHelper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Tigran on 13.02.2018.
 */


@Module(includes = ContextModule.class)
public class DataBaseModule {

    @Provides
    DBHelper getDBHelper(Context context){
        return new DBHelper(context);
    }
}
