package araikovichinc.barbershop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import araikovichinc.barbershop.R;

/**
 * Created by Tigran on 13.03.2018.
 */

public class BookFinishActivity extends AppCompatActivity {

    TextView date;
    Button onHomeActivityBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_finish);
        date = (TextView)findViewById(R.id.finish_date);
        final Intent intent = getIntent();
        date.setText(intent.getIntExtra("day", 0) + "."+
                intent.getIntExtra("month", 0) + "."+
                intent.getIntExtra("year", 0));
        onHomeActivityBtn = (Button)findViewById(R.id.on_home_btn);
        onHomeActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }

    @Override
    public void onBackPressed() {
        next();
    }

    private void next(){
        Intent intent = new Intent(BookFinishActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
