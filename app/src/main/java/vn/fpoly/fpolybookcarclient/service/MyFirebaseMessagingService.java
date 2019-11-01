package vn.fpoly.fpolybookcarclient.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("tokencvgfdzgv" , s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("LONG123456", remoteMessage.getData() + " " + remoteMessage.getNotification().getTitle() );

    }
    //    FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//        @Override
//        public void onComplete(@NonNull Task<InstanceIdResult> task) {
//            if(!task.isSuccessful()){
//                Log.d("failed" , "Fail token");
//            }
//            String token  = task.getResult().getToken();
//        }
//    });
}
