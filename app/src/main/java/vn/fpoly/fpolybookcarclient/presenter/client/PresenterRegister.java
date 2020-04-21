package vn.fpoly.fpolybookcarclient.presenter.client;

import android.app.Activity;
import android.widget.TextView;

import vn.fpoly.fpolybookcarclient.model.client.ModelRegister;
import vn.fpoly.fpolybookcarclient.model.objectClass.Client;

import vn.fpoly.fpolybookcarclient.view.client.IViewRegister;

public class PresenterRegister implements IPPresenterRegister {

    private IViewRegister IViewRegister;
    private ModelRegister modelRegister;
    public PresenterRegister(IViewRegister IViewRegister){
        this.IViewRegister = IViewRegister;
        modelRegister = new ModelRegister();
    }


    @Override
    public void doSignUp(Client client,String password) {
        modelRegister.createClientWithEmailAndPhone(client,password,this);
    }

    @Override
    public void resultSignUp(boolean success, String message) {
        if(success){
            IViewRegister.onSuccessSignUp();
        }else {
            IViewRegister.onFailedSignUp();
        }
    }

    @Override
    public void doSendVerifyOTP(String phone, TextView txtResend, Activity activity) {
        modelRegister.doSendSMS(phone,txtResend,activity,this);

    }

    @Override
    public void doVerifyOTP(String codeVerify, Activity activity) {
        modelRegister.initCheckVerifyOTP(codeVerify,activity,this);
    }


    @Override
    public void resultVerifyOTP(boolean success, String message) {
        if(success){
            IViewRegister.onVerifyOTPSuccess();
        }else {
            IViewRegister.onVerifyOTPFailed();
        }
    }

    @Override
    public void resultSendOTP(boolean success, String message) {
        if(success){
            IViewRegister.onSendSuccess();
        }else {
            IViewRegister.onSendFailed();
        }
    }
}
