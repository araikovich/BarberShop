package araikovichinc.barbershop.modules;

import araikovichinc.barbershop.api.ServerApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tigran on 13.02.2018.
 */

@Module
public class ApiModule {
    public final String baseUrl = "http://192.168.1.3";

    @Provides
    public ServerApi getServerApi(Retrofit retrofit){
        return retrofit.create(ServerApi.class);
    }

    @Provides
    public Retrofit getRetrofit(String baseUrl, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).build();
    }

    @Provides
    public GsonConverterFactory getGsonFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    public String getBaseUrl(){
        return baseUrl;
    }
}
