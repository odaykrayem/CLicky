package com.example.clicky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    Button playAgain;
    TextView score,bestScore;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        playAgain = findViewById(R.id.play_again);
        score = findViewById(R.id.score);
        bestScore = findViewById(R.id.best_score);

        playAgain.setEnabled(false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                playAgain.setEnabled(true);
            }
        };
        handler.postDelayed(runnable, 2000);

        Intent intent = getIntent();
        int scores = intent.getIntExtra("score", 0);

        score.setText("" + scores);
        SharedPreferences prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
        int bestScores = prefs.getInt("bestScore", 0);

        if(scores > bestScores){
            SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
            editor.putInt("bestScore", scores);
            editor.apply();
            bestScore.setText(""+ scores);
        }else{
         bestScore.setText(""+ bestScores);
        }

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();



            }
        });

    }
}