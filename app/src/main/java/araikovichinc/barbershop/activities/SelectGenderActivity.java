package araikovichinc.barbershop.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.bumptech.glide.Glide;

import araikovichinc.barbershop.R;

/**
 * Created by Tigran on 17.02.2018.
 */

public class SelectGenderActivity extends MvpAppCompatActivity {

    ImageView manImage, womanImage;
    Button manBtn, womanBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gender);
        initView();
    }

    private void initView(){
        manImage = (ImageView)findViewById(R.id.man_image);
        womanImage = (ImageView)findViewById(R.id.woman_image);
        Glide.with(this).load(R.drawable.man).into(manImage);
        Glide.with(this).load(R.drawable.woman).into(womanImage);

        toolbar = (Toolbar)findViewById(R.id.select_gender_toolbar);
        manBtn = (Button)findViewById(R.id.man_btn);
        womanBtn = (Button)findViewById(R.id.woman_btn);

        toolbar.setTitle("Выберете пол");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
