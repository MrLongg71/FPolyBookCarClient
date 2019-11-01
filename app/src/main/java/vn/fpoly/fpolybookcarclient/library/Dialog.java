package vn.fpoly.fpolybookcarclient.library;

import android.app.Activity;

import com.developer.kalert.KAlertDialog;

import vn.fpoly.fpolybookcarclient.R;

public class Dialog{
    public static void Success(Activity activity){
        KAlertDialog pDialog = new KAlertDialog(activity, KAlertDialog.SUCCESS_TYPE);
        pDialog .setTitleText(activity.getString(R.string.success));
        pDialog .setContentText(activity.getString(R.string.activateaccout));
        pDialog .show();
        pDialog.dismissWithAnimation();
    }
    public static void Error(Activity activity){
        KAlertDialog pDialog = new KAlertDialog(activity, KAlertDialog.ERROR_TYPE);
        pDialog .setTitleText(activity.getString(R.string.error));
        pDialog .setContentText(activity.getString(R.string.activateaccout));
        pDialog .show();
    }

}
