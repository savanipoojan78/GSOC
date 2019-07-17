package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String notificationCahnnelId="new_learner";
    private static final int NOTIFICATION_ID = 0;
    public  NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button notify=(Button)findViewById(R.id.notify);
        Button upDate=(Button)findViewById(R.id.update);
        Button cancel=(Button)findViewById(R.id.cancel);
        notify.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
               createNotification();
                NotificationCompat.Builder notify2=getNotificationBuilder();
                notificationManager.notify(NOTIFICATION_ID,notify2.build());
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.cancel(NOTIFICATION_ID);
            }
        });
        upDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification();

            }
        });
    }
    public void updateNotification()
    {
        Bitmap image= BitmapFactory.decodeResource(getResources(),R.drawable.mascot_1);
        NotificationCompat.Builder notify=getNotificationBuilder();
        notify.setStyle(new NotificationCompat.BigPictureStyle().bigLargeIcon(image).setBigContentTitle("Notification Updated!!!"));
        notificationManager.notify(NOTIFICATION_ID,notify.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotification() {

        notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=new NotificationChannel(notificationCahnnelId,"learner",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("New _ Learner");
            NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
            notificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());
            notificationManager.createNotificationChannel(notificationChannel);

        }

    }
   private NotificationCompat.Builder getNotificationBuilder()
   {
       Intent i=new Intent(this,MainActivity.class);
       PendingIntent p=PendingIntent.getActivity(this,NOTIFICATION_ID,i,PendingIntent.FLAG_UPDATE_CURRENT);
       NotificationCompat.Builder notifyBuilder=new NotificationCompat.Builder(this,notificationCahnnelId)
                .setContentTitle("Notification")
                .setContentText("Poojan Savani")
                .setSmallIcon(R.drawable.ic_android)
               .setContentIntent(p).setAutoCancel(true)


                ;
        return notifyBuilder;

   }
}
