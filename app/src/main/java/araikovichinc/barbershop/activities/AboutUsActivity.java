package araikovichinc.barbershop.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;

import araikovichinc.barbershop.R;

/**
 * Created by Tigran on 25.03.2018.
 */

public class AboutUsActivity extends AppCompatActivity implements OnClickListener {

    Toolbar toolbar;
    ImageView vodafonCall, kievstarCall, lifeCall, vkBtn, facebookBtn, instBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolbar = (Toolbar)findViewById(R.id.about_us_toolbar);
        toolbar.setTitle(R.string.contacts);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        vodafonCall = (ImageButton)findViewById(R.id.vod_call_btn);
        kievstarCall = (ImageButton)findViewById(R.id.kiev_call_btn);
        lifeCall = (ImageButton)findViewById(R.id.life_call_btn);
        vkBtn = (ImageView)findViewById(R.id.vk_btn);
        instBtn = (ImageView)findViewById(R.id.inst_btn);
        facebookBtn = (ImageView)findViewById(R.id.facebook_btn);

        vodafonCall.setOnClickListener(this);
        kievstarCall.setOnClickListener(this);
        lifeCall.setOnClickListener(this);
        vkBtn.setOnClickListener(this);
        instBtn.setOnClickListener(this);
        facebookBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.vod_call_btn:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:+380997723823"));
                startActivity(intent);
                break;
            case R.id.kiev_call_btn:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:+380674660466"));
                startActivity(intent);
                break;
            case R.id.life_call_btn:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:+380937078885"));
                startActivity(intent);
                break;
            case R.id.vk_btn:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/id52971944"));
                startActivity(intent);
                break;
            case R.id.inst_btn:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/tc_araikovich/?hl=ru"));
                startActivity(intent);
                break;
            case R.id.facebook_btn:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/tigranololo"));
                startActivity(intent);
                break;
        }
    }
}
