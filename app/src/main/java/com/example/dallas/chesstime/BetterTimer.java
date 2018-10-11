package com.example.dallas.chesstime;

import android.os.CountDownTimer;
import android.widget.TextView;

public class BetterTimer {

    private int initialTime;
    private int remainingTime;
    private int tickInterval;

    public BetterTimer(int initialTime, int tickInterval) {
        this.initialTime = initialTime;
        this.tickInterval = tickInterval;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void startTimer() {

        CountDownTimer timer = new CountDownTimer(this.remainingTime, this.tickInterval) {
            @Override
            public void onTick(long millisUntilFinished) {

               // textView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }


}
