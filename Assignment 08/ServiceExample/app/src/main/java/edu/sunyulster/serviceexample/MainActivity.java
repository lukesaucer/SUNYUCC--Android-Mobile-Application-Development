package edu.sunyulster.serviceexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.sunyulster.serviceexample.databinding.ActivityMainBinding;
import android.view.View;
import android.content.Intent;
import static androidx.core.app.JobIntentService.enqueueWork;



public class MainActivity extends AppCompatActivity {

    static final int SERVICE_ID = 1001;

    public void buttonClick(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}