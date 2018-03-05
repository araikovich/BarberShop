package araikovichinc.barbershop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.pojo.Reservation;

/**
 * Created by Tigran on 05.03.2018.
 */

public class SelectTimeActivity extends MvpAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_select_time);
        Intent intent = getIntent();
        Reservation reservation = (Reservation) intent.getSerializableExtra("reservation");
        Log.d("MyLogs", reservation.getDay() + "");
    }
}
