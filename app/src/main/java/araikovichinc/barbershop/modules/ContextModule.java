package araikovichinc.barbershop.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tigran on 13.02.2018.
 */

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    public Context getContext(){
        return context;
    }
}
