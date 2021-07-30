package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_startmode extends AppCompatActivity {
    Button b_non,b_time,b_leave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_mode);
        b_non=(Button)findViewById(R.id.non_timermode);
        b_time=(Button)findViewById(R.id.timermode);
        b_leave=(Button)findViewById(R.id.satrt_exit);
        b_non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_startmode.this,choose_activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_down,R.anim.slide_down_minus);
            }
        });
        b_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_startmode.this,choose_activity_timer.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_down,R.anim.slide_down_minus);
            }
        });
        b_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_startmode.this,home_activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(MainActivity_startmode.this,home_activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }
}
