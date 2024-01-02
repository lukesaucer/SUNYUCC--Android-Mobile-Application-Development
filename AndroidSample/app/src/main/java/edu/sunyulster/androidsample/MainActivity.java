package edu.sunyulster.androidsample;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import edu.sunyulster.androidsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void convertCurrency(View view){

        //EditText dollarText = findViewById(R.id.dollarText);
        //TextView textView = findViewById(R.id.textView);

        if (!binding.dollarText.getText().toString().equals("")) {
            float dollarValue = Float.parseFloat(binding.dollarText.getText().toString());
            float euroValue = (float) (dollarValue * 0.85F);
            binding.textView.setText(String.format(Locale.ENGLISH, "%f", euroValue));
        } else{
                binding.textView.setText(R.string.no_value_string);
            }
        }

    }
