package com.example.dallas.chesstime;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        getWindow().getDecorView().setSystemUiVisibility(3328);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        class BetterCountDownTimer {
            private TextView textView;
            private Button button;
            private long millisUntilFinished;
            private CountDownTimer countDownTimer;

            public BetterCountDownTimer() {
            }

            public TextView getTextView() {
                return textView;
            }

            public void setTextView(TextView textView) {
                this.textView = textView;
            }

            public Button getButton() {
                return button;
            }

            public void setButton(Button button) {
                this.button = button;
            }

            public void setMillisUntilFinished(long millisUntilFinished) {
                this.millisUntilFinished = millisUntilFinished;
            }

            public long getMillisUntilFinished() {
                return millisUntilFinished;
            }

            public void createCountDownTimer(long millisInFuture) {
                setMillisUntilFinished(millisInFuture);
                CountDownTimer countDownTimer = new CountDownTimer(millisInFuture, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        button.setText("" + millisUntilFinished / 1000);
                        setMillisUntilFinished(millisUntilFinished);
                        changeButtonColor(button, millisUntilFinished);
                    }

                    @Override
                    public void onFinish() {
                        button.setBackgroundColor(getResources().getColor(R.color.Gray));
                        button.setText("0");
                    }
                };
                this.countDownTimer = countDownTimer;
            }
        }

        class PlayerTurn {
            private boolean turn;
            private boolean paused;

            public PlayerTurn() {
                this.paused = false;
            }

            public boolean isTurn() {
                return turn;
            }

            public void setTurn(boolean turn) {
                this.turn = turn;
            }

            public boolean isPaused() {
                return paused;
            }

            public void setPaused(boolean paused) {
                this.paused = paused;
            }
        }

        final long fiveMinutes = 300000;    //TODO make this a number set by user in another activity

        Button startButton = findViewById(R.id.firstTurnbutton);
        Button startButton2 = findViewById(R.id.firstTurnButton2);
        final Button turnButton = findViewById(R.id.button);
        final Button turnButton2 = findViewById(R.id.button2);
        Button resetButton = findViewById(R.id.resetButton);

        final BetterCountDownTimer betterCountDownTimer = new BetterCountDownTimer();
        final BetterCountDownTimer betterCountDownTimer2 = new BetterCountDownTimer();
        final PlayerTurn playerTurn = new PlayerTurn();
        final PlayerTurn playerTurn2 = new PlayerTurn();

        betterCountDownTimer.setButton(turnButton);
        betterCountDownTimer2.setButton(turnButton2);

        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfEmptyButtonText(turnButton) || !checkIfEmptyButtonText(turnButton2)) {
                    if (!playerTurn.isPaused() && !playerTurn2.isPaused()) {
                        long millisUntilFinished = betterCountDownTimer.getMillisUntilFinished();
                        long millisUntilFinished2 = betterCountDownTimer2.getMillisUntilFinished();
                        betterCountDownTimer.countDownTimer.cancel();
                        betterCountDownTimer2.countDownTimer.cancel();
                        betterCountDownTimer.createCountDownTimer(millisUntilFinished);
                        betterCountDownTimer2.createCountDownTimer(millisUntilFinished2);
                        playerTurn.setPaused(true);
                        playerTurn2.setPaused(true);
                    } else {
                        if (playerTurn.isTurn()) betterCountDownTimer.countDownTimer.start();
                        if (playerTurn2.isTurn()) betterCountDownTimer2.countDownTimer.start();
                        playerTurn.setPaused(false);
                        playerTurn2.setPaused(false);
                    }
                }
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEmptyButtonText(turnButton) && checkIfEmptyButtonText(turnButton2)) {
                    betterCountDownTimer.createCountDownTimer(fiveMinutes);
                    betterCountDownTimer2.createCountDownTimer(fiveMinutes);
                    betterCountDownTimer.countDownTimer.start();
                    playerTurn.setTurn(true);
                }
            }
        });
        startButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEmptyButtonText(turnButton) && checkIfEmptyButtonText(turnButton2)) {
                    betterCountDownTimer2.createCountDownTimer(fiveMinutes);
                    betterCountDownTimer.createCountDownTimer(fiveMinutes);
                    betterCountDownTimer2.countDownTimer.start();
                    playerTurn2.setTurn(true);
                }
            }
        });
        turnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playerTurn.isPaused()) {
                    if (betterCountDownTimer.getMillisUntilFinished() > 1999) {
                        if (!checkIfEmptyButtonText(turnButton) || !checkIfEmptyButtonText(turnButton2)) {
                            if (playerTurn.isTurn()) {
                                long millisUntilFinished = betterCountDownTimer.getMillisUntilFinished();
                                betterCountDownTimer.countDownTimer.cancel();
                                betterCountDownTimer.createCountDownTimer(millisUntilFinished);
                                betterCountDownTimer2.countDownTimer.start();
                            }
                        }
                        playerTurn.setTurn(false);
                        playerTurn2.setTurn(true);
                    }
                }
            }
        });
        turnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playerTurn2.isPaused()) {
                    if (betterCountDownTimer2.getMillisUntilFinished() > 1999) {
                        if (!checkIfEmptyButtonText(turnButton) || !checkIfEmptyButtonText(turnButton2)) {
                            if (playerTurn2.isTurn()) {
                                long millisUntilFinished = betterCountDownTimer2.getMillisUntilFinished();
                                betterCountDownTimer2.countDownTimer.cancel();
                                betterCountDownTimer2.createCountDownTimer(millisUntilFinished);
                                betterCountDownTimer.countDownTimer.start();
                            }
                        }
                        playerTurn2.setTurn(false);
                        playerTurn.setTurn(true);
                    }
                }
            }
        });
    }

    public boolean checkIfEmptyText(TextView textView) {
        return textView.getText().toString().equals("");
    }

    public boolean checkIfEmptyButtonText(Button button) {
        return button.getText().toString().equals("");
    }

    public void changeButtonColor(Button button, long millisUntilFinished) {
        if (millisUntilFinished > 240000)
            button.setBackgroundColor(getResources().getColor(R.color.Five));
        if ((millisUntilFinished < 239999) && (millisUntilFinished > 180000))
            button.setBackgroundColor(getResources().getColor(R.color.Four));
        if ((millisUntilFinished < 179999) && (millisUntilFinished > 120000))
            button.setBackgroundColor(getResources().getColor(R.color.Three));
        if ((millisUntilFinished < 119999) && (millisUntilFinished > 60000))
            button.setBackgroundColor(getResources().getColor(R.color.Two));
        if ((millisUntilFinished < 59999) && (millisUntilFinished > 30000))
            button.setBackgroundColor(getResources().getColor(R.color.One));
        if (millisUntilFinished < 29999) {
            if ((((int) millisUntilFinished / 1000) % 2) == 0) {
                button.setBackgroundColor(getResources().getColor(R.color.OneHalf));
            } else button.setBackgroundColor(getResources().getColor(R.color.Gray));
        }
    }
}