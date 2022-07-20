package com.example.clicky;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

import me.bogerchan.niervisualizer.NierVisualizerManager;
import me.bogerchan.niervisualizer.renderer.IRenderer;
import me.bogerchan.niervisualizer.renderer.line.LineRenderer;

public class MainActivity extends AppCompatActivity {

    RelativeLayout field;
    TextView  score,time;
    Button play;

    private CountDownTimer countDownTimer;
    private long timeMilliSeconds = 10000;  //10 seconds

    int count = 0;

    Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field = findViewById(R.id.field);
        play = findViewById(R.id.play);
        time = findViewById(R.id.time);
        score = findViewById(R.id.score);

        score.setText("0");
        time.setText("10 sec");

//        // Define ActionBar object
//        actionBar = getSupportActionBar();


        window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color


        field.setEnabled(false);
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play();
            }
        });

        field.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                count++;
                score.setText("" + count);
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));


                field.setBackgroundColor(color);
              //  ContextCompat.getColor(MainActivity.this, color)
                window.setStatusBarColor(color);


            }
        });

    }

    public void play(){
        play.setVisibility(View.GONE);
        field.setEnabled(true);
        count = 0;
        startTime();


    }

    private void startTime(){
        countDownTimer = new CountDownTimer(timeMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
               timeMilliSeconds = millisUntilFinished;
               update();
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class );
               intent.putExtra("score", count);
               startActivity(intent);
               finish();
            }
        }.start();
    }
    public void update(){
        int seconds = (int) timeMilliSeconds / 1000;
        time.setText(""+ seconds);

    }
}