package vn.fpoly.fpolybookcarclient.View.Activity;

import android.content.res.ColorStateList;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.widget.TextView;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.View.Fragment.AccountFragment;
import vn.fpoly.fpolybookcarclient.View.Fragment.ActivityFragment;
import vn.fpoly.fpolybookcarclient.View.Fragment.HomeFragment;
import vn.fpoly.fpolybookcarclient.View.Fragment.MailFragment;
import vn.fpoly.fpolybookcarclient.View.Fragment.PayFragment;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(new HomeFragment());
                    getSupportFragmentManager().addOnBackStackChangedListener(null);
                    return true;
                case R.id.navigation_activity:
                    loadFragment(new ActivityFragment());
                    return true;
                case R.id.navigation_pay:
                    loadFragment(new PayFragment());
                    return true;
                case R.id.navigation_email:
                    loadFragment(new MailFragment());
                    return true;
                case R.id.navigation_accout:
                    loadFragment(new AccountFragment());
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

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setItemIconTintList(null);
        loadFragment(new HomeFragment());

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.conten, fragment).commit();
            return true;
        }
        return false;
    }

}
