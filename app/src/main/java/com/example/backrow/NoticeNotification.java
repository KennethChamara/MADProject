package com.example.backrow;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NoticeNotification extends Application {
    public static final String CHANNEL_ID = "CHANNEL01";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Notice",
                    NotificationManager.IMPORTANCE_HIGH
            );

            channel.setDescription("New Notice");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

}
