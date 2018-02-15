package araikovichinc.barbershop.modules;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.models.GenderCategoryModel;
import araikovichinc.barbershop.models.HairstyleCategoryModel;
import araikovichinc.barbershop.utils.DBHelper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Tigran on 13.02.2018.
 */

@Module(includes = {ApiModule.class, ContextModule.class, DataBaseModule.class})
public class ModelsModule {

    @Provides
    GenderCategoryModel getGenderCategoryModel(ServerApi api, DBHelper db){
        return new GenderCategoryModel(api, db);
    }

    @Provides
    HairstyleCategoryModel getHairstyleCategoryModel(ServerApi api, DBHelper db){
        return new HairstyleCategoryModel(api, db);
    }
}
