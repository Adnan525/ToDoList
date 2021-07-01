package com.app.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        getSupportActionBar().hide();

        Intent recycler = new Intent(this, RecycleView.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(recycler);

            }
        }, 2000);
    }
}