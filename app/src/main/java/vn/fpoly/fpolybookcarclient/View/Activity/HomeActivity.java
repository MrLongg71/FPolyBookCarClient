package vn.fpoly.fpolybookcarclient.View.Activity;

import android.content.res.ColorStateList;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.TextView;

import vn.fpoly.fpolybookcarclient.R;

public class HomeActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_activity:
                    mTextMessage.setText(R.string.activity);
                    return true;
                case R.id.navigation_pay:
                    mTextMessage.setText(R.string.pay);
                    return true;
                case R.id.navigation_email:
                    mTextMessage.setText(R.string.mailbox);
                    return true;
                case R.id.navigation_accout:
                    mTextMessage.setText(R.string.account);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setItemIconTintList(null);
//        navView.setItemBackgroundResource(R.color.colorAccent);

    }

}
