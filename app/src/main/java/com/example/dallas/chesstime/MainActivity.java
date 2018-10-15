package com.example.dallas.chesstime;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int millisInFuture = 300000;
        final int countDownInterval = 1000;

        final TimerInfo timerInfo = new TimerInfo();
        final TimerInfo timerInfo2 = new TimerInfo();

        final TextView textView = findViewById(R.id.textView);
        final TextView textView2 = findViewById(R.id.textView2);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);

        BetterTimer player1Timer = new BetterTimer();
        BetterTimer player2Timer = new BetterTimer();

        timerInfo.setMillisUntilFinished(millisInFuture);
        timerInfo2.setMillisUntilFinished(millisInFuture);

        final Player player1 = new Player();
        final Player player2 = new Player();

        player1.setTimeRemaining(timerInfo);
        player2.setTimeRemaining(timerInfo2);

        player1.setTextViewNum(textView);
        player2.setTextViewNum(textView2);

        player1.setTimerInfo(timerInfo);
        player2.setTimerInfo(timerInfo2);

        player1.setTurn(true);
        player2.setTurn(false);

        player1.setNewTimer(player1Timer);
        player2.setNewTimer(player2Timer);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEmptyText(textView2)) {
                    player2.startNewTimer();
                }
                else {
                    player2.setTimeRemaining(timerInfo2);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEmptyText(textView)) {
                    player1.startNewTimer();
                } else {
                    player1.setTimeRemaining(timerInfo);
                }
            }
        });
    }

    public boolean checkIfEmptyText(TextView textView) {
        if (textView.getText().toString().equals("")) {
            return true;
        } else return false;
    }

    public class TimerInfo {
        public int millisUntilFinished;

        public TimerInfo() {}

        public int getMillisUntilFinished() {
            return millisUntilFinished;
        }

        public void setMillisUntilFinished(int millisUntilFinished) {
            this.millisUntilFinished = millisUntilFinished;
        }
    }

    public class Player {
        private int timeRemaining;
        private boolean isTurn;
        private CountDownTimer newTimer;
        private int countDownInterval = 1000;
        private TextView textView;
        private TimerInfo timerInfo;

        public Player() {}

        public int getTimeRemaining() {
            return timeRemaining;
        }

        public void setTimeRemaining(TimerInfo timerInfo) {
            this.timeRemaining = timerInfo.getMillisUntilFinished();
        }

        public boolean isTurn() {
            return isTurn;
        }

        public void setTurn(boolean turn) {
            isTurn = turn;
        }

        public void startNewTimer() {
            this.newTimer.start();
        }

        public void setNewTimer(BetterTimer betterTimer){
            this.newTimer = betterTimer.newTimer(this.getTimeRemaining(), countDownInterval, this.textView, this.timerInfo);
        }

        public void resumeTimer(){

        }

        public void setTextViewNum(TextView textView){
            this.textView = textView;
        }

        public void setTimerInfo(TimerInfo timerInfo){
            this.timerInfo = timerInfo;
        }

    }

    public class BetterTimer{

        public BetterTimer() {}

        public CountDownTimer newTimer(int millisInFuture, final int countDownInterval, final TextView textView, final TimerInfo timerInfo){
            CountDownTimer newTimer = new CountDownTimer(millisInFuture, countDownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    textView.setText("" + millisUntilFinished / countDownInterval);
                    timerInfo.setMillisUntilFinished((int) millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    textView.setText("Time Up!");
                }
            };
            return newTimer;
        }
    }
}
