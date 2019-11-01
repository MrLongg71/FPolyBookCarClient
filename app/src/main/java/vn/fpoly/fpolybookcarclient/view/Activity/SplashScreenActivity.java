package vn.fpoly.fpolybookcarclient.view.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.client.ClientActivity;
import vn.fpoly.fpolybookcarclient.service.MyFirebaseMessagingService;

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
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_exit);
                startService(new Intent(SplashScreenActivity.this, MyFirebaseMessagingService.class));
                FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.d("failed", "Fail token");
                        }
                        String token = task.getResult().getToken();
                        Log.d("TOKEN" , token);
                    }
                });
                finish();
            }

        }, 5000);
    }
}
