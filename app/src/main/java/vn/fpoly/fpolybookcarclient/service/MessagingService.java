package vn.fpoly.fpolybookcarclient.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.activity.SplashScreenActivity;

public class MessagingService extends FirebaseMessagingService {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    DatabaseReference dataDriver = FirebaseDatabase.getInstance().getReference();

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            Intent intent = new Intent("myFunction");
            // add data
            intent.putExtra("finish", remoteMessage.getData().get("idClient"));
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            Log.d("LONgKUTE", "onMessageReceived: "+remoteMessage.getData().get("idClient"));
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
//        getToken();

    }

    private void getToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            onNewToken(task.getResult().getToken());
                        }
                    }
                });
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        sendTokenToServer(s);
    }

    private void sendTokenToServer(String s) {

        try {
            dataDriver.child("Client").child(auth.getCurrentUser().getUid()).child("token").setValue(s);

        } catch (Exception e) {

        }
    }
}
