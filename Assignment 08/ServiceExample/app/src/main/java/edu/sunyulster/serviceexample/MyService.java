package edu.sunyulster.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    private static final String TAG = "ServiceExample";

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand " + startId);

        Runnable runnable = () -> {
            int i = 0;
            while (i <= 3) {

                try {
                    Thread.sleep(10000);
                    i++;
                } catch (Exception e) {

                }
                Log.i(TAG, "Luke Saucer's Service running" + startId);
            }
        };
        Thread serviceThread = new Thread(runnable);
        serviceThread.start();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "Service onDestroy");
    }
}