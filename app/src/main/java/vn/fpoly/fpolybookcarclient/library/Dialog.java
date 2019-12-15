package vn.fpoly.fpolybookcarclient.library;

import android.app.Activity;
import android.content.Intent;

import com.developer.kalert.KAlertDialog;

import vn.fpoly.fpolybookcarclient.R;

public class Dialog{
    public static void Success(final Activity activity){
        KAlertDialog pDialog = new KAlertDialog(activity, KAlertDialog.SUCCESS_TYPE);
        pDialog .setTitleText(activity.getString(R.string.success));
        pDialog .setContentText(activity.getString(R.string.activateaccout));
        pDialog .show();
    }
    public static void Error(Activity activity,String message){
        KAlertDialog pDialog = new KAlertDialog(activity, KAlertDialog.ERROR_TYPE);
        pDialog .setTitleText(activity.getString(R.string.error));
        pDialog .setContentText(message);
        pDialog .show();
    }

}
