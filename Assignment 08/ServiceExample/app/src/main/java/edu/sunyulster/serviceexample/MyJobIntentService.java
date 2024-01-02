package edu.sunyulster.serviceexample;

import android.content.Intent;
import androidx.core.app.JobIntentService;
import androidx.annotation.NonNull;
import android.util.Log;

public class MyJobIntentService extends JobIntentService{

    private static final String TAG = "ServiceExample";

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.i(TAG, "Job Service started");

        int i = 0;
        while (i <= 3) {

            try {
                Thread.sleep(10000);
                i++;
            } catch (Exception e) {

            }
            Log.i(TAG, "Lucas Saucer's Service Running");
        }
    }
}
