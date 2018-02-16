package araikovichinc.barbershop.components;

import araikovichinc.barbershop.models.GenderCategoryModel;
import araikovichinc.barbershop.models.HairstyleCategoryModel;
import araikovichinc.barbershop.models.HairstyleDetailModel;
import araikovichinc.barbershop.modules.ApiModule;
import araikovichinc.barbershop.modules.ContextModule;
import araikovichinc.barbershop.modules.DataBaseModule;
import araikovichinc.barbershop.modules.ModelsModule;
import dagger.Component;

/**
 * Created by Tigran on 13.02.2018.
 */

@Component(modules = ModelsModule.class)
public interface ModelComponent {
    GenderCategoryModel getGenderCategoryModel();
    HairstyleCategoryModel getHairstyleCategoryModel();
    HairstyleDetailModel getHairstyleDetailModel();
}
