package vn.fpoly.fpolybookcarclient.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;




import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.client.ClientActivity;
import vn.fpoly.fpolybookcarclient.service.MessagingService;

public class SplashScreenActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, ClientActivity.class));
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_exit);
                startService(new Intent(SplashScreenActivity.this, MessagingService.class));

                finish();
            }

        }, 3500);
    }
}
