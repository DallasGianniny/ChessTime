package com.example.dallas.chesstime;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.button);
        final BetterTimer testTimer = new BetterTimer(60000, 1000);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEmptyText()) {
                   // testTimer.resumeTimer();
                    testTimer.startTimer();
                }
                //   resumeTimer();
            }
        });
    }

//    void resumeTimer() {
//        TextView textView = findViewById(R.id.textView);
//        if (textView.getText().toString().equals("")) {
//
//            CountDownTimer timer = new CountDownTimer(60000, 1000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    TextView textView = findViewById(R.id.textView);
//                    textView.setText("seconds remaining: " + millisUntilFinished / 1000);
//                }
//
//                @Override
//                public void onFinish() {
//
//                }
//            }.start();
//
//        }
//    }

    public class BetterTimer {

        private int initialTime;
        private int remainingTime;
        private int tickInterval;

        public BetterTimer(int initialTime, int tickInterval) {
            this.initialTime = initialTime;
            this.tickInterval = tickInterval;
            this.remainingTime = initialTime;
        }

        public void setRemainingTime(int remainingTime) {
            this.remainingTime = remainingTime;
        }

        public void resumeTimer() {

            CountDownTimer timer = new CountDownTimer(this.remainingTime, this.tickInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    TextView textView = findViewById(R.id.textView);
                    textView.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                }
            }.start();

        }

        public void startTimer() {

            CountDownTimer timer = new CountDownTimer(this.initialTime, this.tickInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    TextView textView = findViewById(R.id.textView);
                    textView.setText("seconds remaining: " + millisUntilFinished / 1000);
                    if(millisUntilFinished == 0){
                        textView.setText("seconds remaining: " + 0);
                    }
                }

                @Override
                public void onFinish() {

                }
            }.start();

        }


    }

    public boolean checkIfEmptyText() {
        TextView textView = findViewById(R.id.textView);
        if (textView.getText().toString().equals("")) {
            return true;
        } else return false;

    }
}
