package com.fobid.notification.demo;

import android.app.Notification;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_URL = "https://scontent-icn1-1.cdninstagram.com/t51.2885-15/e35/15535332_1342614049093283_2539749161536847872_n.jpg";
    private int notificationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setColor(ResourcesCompat.getColor(getResources(), R.color.red, null))
                .setSmallIcon(R.drawable.ic_stat_name)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.app_name) + " TEST");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(MainActivity.this)
                        .load(IMAGE_URL)
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                Bitmap bitmap = ImageUtils.getCircularBitmap(resource);

                                builder.setLargeIcon(bitmap);

                                NotificationManagerCompat.from(MainActivity.this)
                                        .notify(++notificationId, builder.build());
                            }
                        });
            }
        });
    }
}
