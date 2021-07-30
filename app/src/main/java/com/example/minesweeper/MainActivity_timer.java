package com.example.minesweeper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import Views.Minesweeper_game;
import Views.Time_Random;

public class MainActivity_timer extends AppCompatActivity {
    Time_Random time_random;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_mode);
        time_random=new Time_Random(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(MainActivity_timer.this,choose_activity_timer.class);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }
}