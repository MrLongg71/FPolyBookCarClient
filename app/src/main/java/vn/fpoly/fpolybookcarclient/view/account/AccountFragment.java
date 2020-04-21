package vn.fpoly.fpolybookcarclient.view.account;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.library.CallBackFragment;
import vn.fpoly.fpolybookcarclient.model.objectClass.Client;
import vn.fpoly.fpolybookcarclient.presenter.account.PresenterAccount;

public class AccountFragment extends Fragment implements IViewAccount {
    private FragmentManager fragmentManager;
    private TextView txtNameAccount, txtPhoneAccount, txtEmailAccount;
    private CircleImageView imgAccount;
    private PresenterAccount presenterAccount;
    private Button btnUpdateAccount;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        CallBackFragment.CallbackHome(view, fragmentManager);
        initView(view);
        progressDialog.show();
        presenterAccount.getInfoAccount();
        return view;
    }

    private void initEvent(final Client client) {
        btnUpdateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                @SuppressLint("InflateParams") View dialogEditAccount = inflater.inflate(R.layout.custom_dialog_edit_account, null, false);
                alertDialog.setView(dialogEditAccount);
                final TextInputEditText edtEmail, edtPhone, edtName;
                Button btnUpdateNew = dialogEditAccount.findViewById(R.id.btnUpdateAccountNew);
                Button btnUpdateCacel = dialogEditAccount.findViewById(R.id.btnUpdateAccountCancel);
                edtEmail = dialogEditAccount.findViewById(R.id.edtEmailAccountUpdate);
                edtName = dialogEditAccount.findViewById(R.id.edtNameAccountUpdate);
                edtPhone = dialogEditAccount.findViewById(R.id.edtPhoneAccountUpdate);
                if(client != null){
                    edtEmail.setText(client.getEmail());
                    edtName.setText(client.getName());
                    edtPhone.setText(client.getPhone());
                }
                btnUpdateNew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressDialog.show();
                        client.setEmail(edtEmail.getText().toString().trim());
                        client.setName(edtName.getText().toString().trim());
                        client.setPhone(edtPhone.getText().toString().trim());
                        presenterAccount.updateInfoAccount(client);
                        alertDialog.dismiss();
                    }
                });
                btnUpdateCacel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }

        });

    }


    private void initView(View view) {
        presenterAccount = new PresenterAccount(this);
        txtNameAccount = view.findViewById(R.id.txtNameAccount);
        txtPhoneAccount = view.findViewById(R.id.txtPhoneAccount);
        txtEmailAccount = view.findViewById(R.id.txtEmailAccount);
        imgAccount = view.findViewById(R.id.imgAccount);
        btnUpdateAccount = view.findViewById(R.id.btnUpdateAccount);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");
    }

    @Override
    public void displayInfoAccount(Client client) {
        progressDialog.dismiss();
        txtEmailAccount.setText(client.getEmail());
        txtNameAccount.setText(client.getName());
        txtPhoneAccount.setText(client.getPhone());
        initEvent(client);

    }

    @Override
    public void updateInfoAccountSuccess() {
        Toasty.success(getActivity(),"Success");
        progressDialog.dismiss();
        presenterAccount.getInfoAccount();
    }

    @Override
    public void upadteInfoAccountFailed() {
        Toasty.success(getActivity(),"Failed");
        progressDialog.dismiss();
    }
}
