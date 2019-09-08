package vn.fpoly.fpolybookcarclient.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import vn.fpoly.fpolybookcarclient.R;

public class VerifyPhoneFragment extends Fragment {
    EditText edtphone1,edtphone2,edtphone3,edtphone4;
    Button btnvery;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verifyphone, container, false);
        initView(view);
        btnvery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                LoginFragment loginFragment = new LoginFragment();
                fragmentTransaction.replace(R.id.frame_client,loginFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
    private void initView(View view){
        edtphone1   = view.findViewById(R.id.edtnumber1);
        edtphone2   = view.findViewById(R.id.edtnumber1);
        edtphone3   = view.findViewById(R.id.edtnumber1);
        edtphone4   = view.findViewById(R.id.edtnumber1);
        btnvery     = view.findViewById(R.id.btnvery);
    }
}
