package com.tr.nata.projectandroid.Firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tr.nata.projectandroid.HomeActivity;
import com.tr.nata.projectandroid.HomeAdminActivity;
import com.tr.nata.projectandroid.LoginActivity;
import com.tr.nata.projectandroid.MainActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.SplashScreenActivity;
import com.tr.nata.projectandroid.TryPerofilleActivity;

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s){

        Log.d("token",s);

        SharedPreferences shared = getSharedPreferences("fcm_token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("fcm_token_user",s);
        editor.apply();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);

        String message = remoteMessage.getNotification().getBody();
        String title = remoteMessage.getNotification().getTitle();

        //proses memvalidasi login user
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        String status_login = sharedPref.getString("status_login_string","");
        String status_user = sharedPref.getString("status_user","");
        String user_token = sharedPref.getString("user_token","kosong");

//        Intent intent = new Intent(MessagingService.this,TryPerofilleActivity.class);
        if (!user_token.equals("kosong") && status_login.equals("true") && status_user.equals("1")){

            Intent intent = new Intent(MessagingService.this,TryPerofilleActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"my_chanel")
                    .setContentText(message)
                    .setContentTitle(title)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setSound(sound)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,notificationBuilder.build());
        }else {
            Intent intent = new Intent(MessagingService.this,LoginActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"my_chanel")
                    .setContentText(message)
                    .setContentTitle(title)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setSound(sound)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,notificationBuilder.build());
        }

    }
}
