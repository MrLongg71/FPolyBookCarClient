package vn.fpoly.fpolybookcarclient.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.fpoly.fpolybookcarclient.R;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnlogin, btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        btnlogin.setOnClickListener(this);
        btnsignup.setOnClickListener(this);
    }

    private void initView() {
        btnlogin = findViewById(R.id.btnlogin);
        btnsignup = findViewById(R.id.btnsigup);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnlogin:
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                break;
            case R.id.btnsigup:
                startActivity(new Intent(SplashActivity.this, SigninActivity.class));
                break;
        }
    }
}
