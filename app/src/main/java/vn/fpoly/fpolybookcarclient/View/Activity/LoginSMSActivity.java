package vn.fpoly.fpolybookcarclient.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.View.Fragment.VerifyPhoneFragment;

public class LoginSMSActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtPhoneLogin;
    Button btnLoginPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sms);
        initView();
        btnLoginPhone.setOnClickListener(this);
    }

    private void initView() {
        edtPhoneLogin = findViewById(R.id.edtPhoneLogin);
        btnLoginPhone = findViewById(R.id.btnLoginPhone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginPhone:
                inputNummberPhone();
                break;
        }
    }
    private void inputNummberPhone(){
        String phone = edtPhoneLogin.getText().toString().trim();
        Bundle bundle = new Bundle();
        bundle.putString("phone", phone);

        VerifyPhoneFragment verifyPhoneFragment = new VerifyPhoneFragment();
        verifyPhoneFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fr_add_verify, verifyPhoneFragment).commit();
        btnLoginPhone.setVisibility(View.GONE);
        edtPhoneLogin.setVisibility(View.GONE);
    }
}
