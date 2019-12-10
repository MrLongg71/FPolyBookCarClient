package vn.fpoly.fpolybookcarclient.view.splashscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.client.LoginFragment;

public class SplashScreenFragment extends Fragment implements View.OnClickListener {
    private  ProgressBar progressBar;
    private Button btnsigup, btnlogin;
    private TextView txtIntro;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splashscreen, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initEvent(view);
        loadAnimation();
        btnlogin.setOnClickListener(this);

        return view;
    }

    private void initEvent(View view) {
        progressBar     = view.findViewById(R.id.progressbar);
        btnlogin        = view.findViewById(R.id.btnlogin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnlogin:
                LoginFragment loginFragment = new LoginFragment();
                loadFragment(loginFragment);
                break;
        }
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getFragmentManager().beginTransaction().replace(R.id.frame_client, fragment).addToBackStack(null).commit();
            getActivity().overridePendingTransition(R.anim.animation_enter, R.anim.fade_in);
        }
    }

    private void loadAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_button);
        btnlogin.startAnimation(animation);

    }
}







