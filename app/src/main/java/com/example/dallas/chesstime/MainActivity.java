package com.example.dallas.chesstime;

import android.os.CountDownTimer;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
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

        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        getWindow().getDecorView().setSystemUiVisibility(3328);

        class BetterCountDownTimer {
            private TextView textView;
            private CountDownTimer countDownTimer;
            private long millisUntilFinished;

            public BetterCountDownTimer(TextView textView) {
                this.textView = textView;
            }

            public void setMillisUntilFinished(long millisUntilFinished) {
                this.millisUntilFinished = millisUntilFinished;
            }

            public long getMillisUntilFinished() {
                return millisUntilFinished;
            }

            public void createCountDownTimer(long millisInFuture) {
                CountDownTimer countDownTimer = new CountDownTimer(millisInFuture, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        textView.setText("" + millisUntilFinished / 1000);
                        setMillisUntilFinished(millisUntilFinished);
                    }

                    @Override
                    public void onFinish() {
                        textView.setText("Out of Time!");
                    }
                };
                this.countDownTimer = countDownTimer;
            }
        }

        class PlayerTurn{
            private boolean turn;

            public PlayerTurn() {
            }

            public boolean isTurn() {
                return turn;
            }

            public void setTurn(boolean turn) {
                this.turn = turn;
            }
        }

        final long fiveMinutes = 300000;

        Button startButton = findViewById(R.id.firstTurnbutton);
        Button startButton2 = findViewById(R.id.firstTurnButton2);
        Button turnButton = findViewById(R.id.button);
        Button turnButton2 = findViewById(R.id.button2);
        final TextView textView = findViewById(R.id.textView);
        final TextView textView2 = findViewById(R.id.textView2);
        final BetterCountDownTimer betterCountDownTimer = new BetterCountDownTimer(textView);
        final BetterCountDownTimer betterCountDownTimer2 = new BetterCountDownTimer(textView2);
        final PlayerTurn player1Turn = new PlayerTurn();
        final PlayerTurn player2Turn = new PlayerTurn();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEmptyText(textView) && checkIfEmptyText(textView2)) {
                    betterCountDownTimer.createCountDownTimer(fiveMinutes);
                    betterCountDownTimer2.createCountDownTimer(fiveMinutes);
                    betterCountDownTimer.countDownTimer.start();
                    player1Turn.setTurn(true);
                }
            }
        });
        startButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEmptyText(textView2) && checkIfEmptyText(textView)) {
                    betterCountDownTimer2.createCountDownTimer(fiveMinutes);
                    betterCountDownTimer.createCountDownTimer(fiveMinutes);
                    betterCountDownTimer2.countDownTimer.start();
                    player2Turn.setTurn(true);
                }
            }
        });

        turnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfEmptyText(textView) || !checkIfEmptyText(textView2)) {
                    if (player1Turn.isTurn()) {
                    long millisUntilFinished = betterCountDownTimer.getMillisUntilFinished();
                    betterCountDownTimer.countDownTimer.cancel();
                    betterCountDownTimer.createCountDownTimer(millisUntilFinished);

                        betterCountDownTimer2.countDownTimer.start();
                    }
                    player1Turn.setTurn(false);
                    player2Turn.setTurn(true);
                }
            }
        });
        turnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfEmptyText(textView) || !checkIfEmptyText(textView2)) {
                    if (player2Turn.isTurn()) {
                    long millisUntilFinished = betterCountDownTimer2.getMillisUntilFinished();
                    betterCountDownTimer2.countDownTimer.cancel();
                    betterCountDownTimer2.createCountDownTimer(millisUntilFinished);

                        betterCountDownTimer.countDownTimer.start();
                    }
                    player2Turn.setTurn(false);
                    player1Turn.setTurn(true);
                }
            }
        });
    }

    public boolean checkIfEmptyText(TextView textView) {
        return textView.getText().toString().equals("");
    }
}

