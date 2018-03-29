package araikovichinc.barbershop.components;

import araikovichinc.barbershop.modules.ApiModule;
import araikovichinc.barbershop.modules.ContextModule;
import araikovichinc.barbershop.modules.DataBaseModule;
import araikovichinc.barbershop.presenters.AddFeedbackActivityPresenter;
import araikovichinc.barbershop.presenters.BookActivityPresenter;
import araikovichinc.barbershop.presenters.ConfirmReservationActivityPresenter;
import araikovichinc.barbershop.presenters.FeedbackActivityPresenter;
import araikovichinc.barbershop.presenters.GenderCategoriesActivityPresenter;
import araikovichinc.barbershop.presenters.HairstyleCategoryActivityPresenter;
import araikovichinc.barbershop.presenters.HairstyleDetailActivityPresenter;
import araikovichinc.barbershop.presenters.HomeActivityPresenter;
import araikovichinc.barbershop.presenters.MyBookDetailActivityPresenter;
import araikovichinc.barbershop.presenters.MyBooksActivityPresenter;
import araikovichinc.barbershop.presenters.SelectTimeActivityPresenter;
import dagger.Component;

/**
 * Created by Tigran on 13.02.2018.
 */

@Component(modules = {ApiModule.class, ContextModule.class, DataBaseModule.class})
public interface ModelComponent {
    void inject(HairstyleCategoryActivityPresenter presenter);
    void inject(HairstyleDetailActivityPresenter presenter);
    void inject(GenderCategoriesActivityPresenter presenter);
    void inject(BookActivityPresenter presenter);
    void inject(SelectTimeActivityPresenter presenter);
    void inject(HomeActivityPresenter presenter);
    void inject(FeedbackActivityPresenter presenter);
    void inject(AddFeedbackActivityPresenter presenter);
    void inject(ConfirmReservationActivityPresenter presenter);
    void inject(MyBooksActivityPresenter presenter);
    void inject(MyBookDetailActivityPresenter presenter);
}
