package vn.fpoly.fpolybookcarclient.view.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.Fragment.SplashScreenFragment;

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
