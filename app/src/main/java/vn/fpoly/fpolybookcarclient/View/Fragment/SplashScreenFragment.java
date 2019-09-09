package vn.fpoly.fpolybookcarclient.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.View.Activity.SplashScreenActivity;

public class SplashScreenFragment extends Fragment implements View.OnClickListener {
      ProgressBar progressBar;
    private Button btnsigup, btnlogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splashscreen, container, false);

        initEvent(view);
        loadAnimation();

        btnsigup.setOnClickListener(this);
        btnlogin.setOnClickListener(this);

        return view;
    }

    private void initEvent(View view) {
        progressBar     = view.findViewById(R.id.progressbar);
        btnlogin        = view.findViewById(R.id.btnlogin);
        btnsigup        = view.findViewById(R.id.btnsigup);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnsigup:
                SignInFragment signInFragment = new SignInFragment();
                loadFragment(signInFragment);

                break;
            case R.id.btnlogin:
                LoginFragment loginFragment = new LoginFragment();
                loadFragment(loginFragment);

                break;
        }
    }
    private void loadFragment(Fragment fragment){
        if(fragment != null){
            getFragmentManager().beginTransaction().replace(R.id.frame_client,fragment).addToBackStack(null).commit();
            getActivity().overridePendingTransition(R.anim.animation_enter,R.anim.fade_in);
        }
    }

    private void loadAnimation(){
        Animation  animation = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_button );
        btnlogin.startAnimation(animation);
        btnsigup.startAnimation(animation);
    }
}
