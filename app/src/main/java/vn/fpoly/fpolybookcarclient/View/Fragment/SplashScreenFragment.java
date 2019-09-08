package vn.fpoly.fpolybookcarclient.View.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import vn.fpoly.fpolybookcarclient.R;

public class SplashScreenFragment extends Fragment implements View.OnClickListener {

    ProgressBar progressBar;
    Button btnsigup, btnlogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splashscreen, container, false);
        initEvent(view);
        btnsigup.setOnClickListener(this);
        btnlogin.setOnClickListener(this);

        return view;
    }

    private void initEvent(View view) {
        progressBar = view.findViewById(R.id.progressbar);
        btnlogin = view.findViewById(R.id.btnlogin);
        btnsigup = view.findViewById(R.id.btnsigup);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnsigup:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SignInFragment signInFragment = new SignInFragment();
                fragmentTransaction.replace(R.id.frame_client,signInFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.btnlogin:
                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                LoginFragment loginFragment = new LoginFragment();
                fragmentTransaction1.replace(R.id.frame_client,loginFragment);
                fragmentTransaction1.commit();
                fragmentTransaction1.addToBackStack(null);
                break;
        }
    }
}
