package vn.fpoly.fpolybookcarclient.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.fpoly.fpolybookcarclient.R;

public class SigninActivity extends AppCompatActivity {
    EditText edtcountry;
    Button btnsignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        initEvent();
        edtcountry.setFocusable(false);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void initEvent(){
        edtcountry      = findViewById(R.id.edtcode);
        btnsignin       = findViewById(R.id.btnSigUp);

    }
}
