package araikovichinc.barbershop.components;

import com.arellomobile.mvp.MvpPresenter;

import araikovichinc.barbershop.modules.ApiModule;
import araikovichinc.barbershop.modules.ContextModule;
import araikovichinc.barbershop.modules.DataBaseModule;
import araikovichinc.barbershop.modules.ReservationModule;
import araikovichinc.barbershop.presenters.GenderCategoriesFragmentPresenter;
import araikovichinc.barbershop.presenters.HairstyleCategoryActivityPresenter;
import araikovichinc.barbershop.presenters.HairstyleDetailActivityPresenter;
import dagger.Component;

/**
 * Created by Tigran on 13.02.2018.
 */

@Component(modules = {ApiModule.class, ContextModule.class, DataBaseModule.class})
public interface ModelComponent {
    void inject(HairstyleCategoryActivityPresenter presenter);
    void inject(HairstyleDetailActivityPresenter presenter);
    void inject(GenderCategoriesFragmentPresenter presenter);
}
