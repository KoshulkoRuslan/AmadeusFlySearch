package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.myapplication.databases.AppCodeIataDatabase;
import com.example.myapplication.token.AmadeusService;


public class StartActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TokenStatus tokenStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        tokenStatus = () -> {
            Intent intent = new Intent(StartActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        };

        progressBar = findViewById(R.id.progressBar);
        AmadeusService.getAmadeusService(this,tokenStatus);
    }
    public interface TokenStatus{
        void tokenIsReady();
    }
}

