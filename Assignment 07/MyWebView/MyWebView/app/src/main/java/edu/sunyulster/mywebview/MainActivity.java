package edu.sunyulster.mywebview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.net.URL;
import android.net.Uri
import android.content.Intent;
import android.webkit.WebView;
import android.view.View;
import edu.sunyulster.mywebview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        handleIntent();
    }

    private void handleIntent() {
        Intent intent = getIntent();

        Uri data = intent.getData();
        URL url = null;

        try {
            url = new URL(data.getScheme()),

            Uri data = intent.getData();
            URL url = null;

            try {
                url = new URL(data.getScheme(),
                        data.getHost(),
                        data.getPath());
            } catch (Exception e) {
                e.printStackTrace();
            }

            binding.webView1.loadUrl(url.toString());
        }
    }
}