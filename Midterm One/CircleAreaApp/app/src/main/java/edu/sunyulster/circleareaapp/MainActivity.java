package edu.sunyulster.circleareaapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import edu.sunyulster.circleareaapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Restored";
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");

        CharSequence userText = binding.radiusText.getText();
        outState.putCharSequence("savedText", userText);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");

        CharSequence userText = savedInstanceState.getCharSequence("savedText");
        binding.radiusText.setText(userText + " (restored)");
    }

    public void calculateArea(View view)
    {
        double pi = 3.142, area;
        double radius = Double.parseDouble(binding.radiusText.getText().toString());
        // calculating the area of the circle
        area = pi * radius * radius;
        // printing the area of the circle
        binding.areaText.setText(String.format("Area is : " + area));
    }
}
