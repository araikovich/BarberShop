package araikovichinc.barbershop.modules;

import araikovichinc.barbershop.pojo.Reservation;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Tigran on 17.02.2018.
 */

@Module
public class ReservationModule {
    @Provides
    public Reservation getReservation(){
        return new Reservation();
    }
}
