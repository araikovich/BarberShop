package araikovichinc.barbershop;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.model.Model;

import araikovichinc.barbershop.components.DaggerModelComponent;
import araikovichinc.barbershop.components.ModelComponent;
import araikovichinc.barbershop.modules.ContextModule;

/**
 * Created by Tigran on 20.02.2018.
 */

public class MyApp extends Application {

    private static ModelComponent component;
    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerModelComponent.builder().contextModule(new ContextModule(this)).build();
        appContext = this;
    }

    @NonNull
    public static ModelComponent getModelComponent(){
        return component;
    }

}
