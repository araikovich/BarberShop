package araikovichinc.barbershop.components;

import araikovichinc.barbershop.modules.ReservationModule;
import araikovichinc.barbershop.pojo.Reservation;
import dagger.Component;

/**
 * Created by Tigran on 17.02.2018.
 */


@Component(modules = ReservationModule.class)
public interface ReservationComponent {
    Reservation getResevation();
}
