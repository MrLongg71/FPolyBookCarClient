package vn.fpoly.fpolybookcarclient.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.View.Fragment.SplashScreenFragment;

public class ClientActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_client, new SplashScreenFragment()).commit();
    }
}
