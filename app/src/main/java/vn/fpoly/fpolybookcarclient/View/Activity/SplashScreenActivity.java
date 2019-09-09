package vn.fpoly.fpolybookcarclient.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.View.Fragment.SplashScreenFragment;

public class SplashScreenActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(SplashScreenActivity.this, ClientActivity.class));
                overridePendingTransition(R.anim.animation_enter,R.anim.animation_exit);
                finish();
            }
        },3000);
    }
}
