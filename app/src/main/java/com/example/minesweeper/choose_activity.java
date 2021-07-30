package com.example.minesweeper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import Views.Easy_random;

public class choose_activity extends AppCompatActivity implements View.OnClickListener {
    Button b_easy,b_medium,b_hard,b_expert,b_back;
    public static int choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_mode);
        b_easy=(Button)findViewById(R.id.easy);
        b_medium=(Button)findViewById(R.id.medium);
        b_hard=(Button)findViewById(R.id.hard);
        b_expert=(Button)findViewById(R.id.expert);
        b_back=(Button)findViewById(R.id.leave);
        b_easy.setOnClickListener(this);
        b_medium.setOnClickListener(this);
        b_hard.setOnClickListener(this);
        b_expert.setOnClickListener(this);
        b_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.easy:
                choose=0;
                break;
            case R.id.medium:
                choose=1;
                break;
            case R.id.hard:
                choose=2;
                break;
            case R.id.expert:
                choose=3;
                break;
        }
        if(v.getId()==R.id.easy||v.getId()==R.id.medium||v.getId()==R.id.hard||v.getId()==R.id.expert)
        {
            Intent intent=new Intent(choose_activity.this,Mainactivity_easy.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        if(v.getId()==R.id.leave) {
            Intent intent=new Intent(choose_activity.this,MainActivity_startmode.class);
            startActivity(intent);
           overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(choose_activity.this,MainActivity_startmode.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }
}
