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

            public PlayerTurn() { }

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
        final Button turnButton = findViewById(R.id.button);
        final Button turnButton2 = findViewById(R.id.button2);
        Button resetButton = findViewById(R.id.resetButton);

        final BetterCountDownTimer betterCountDownTimer = new BetterCountDownTimer();
        final BetterCountDownTimer betterCountDownTimer2 = new BetterCountDownTimer();
        final PlayerTurn player1Turn = new PlayerTurn();
        final PlayerTurn player2Turn = new PlayerTurn();

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
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEmptyButtonText(turnButton) && checkIfEmptyButtonText(turnButton2)) {
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
                if (checkIfEmptyButtonText(turnButton) && checkIfEmptyButtonText(turnButton2)) {
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
                if (betterCountDownTimer.getMillisUntilFinished() > 1999) {
                    if (!checkIfEmptyButtonText(turnButton) || !checkIfEmptyButtonText(turnButton2)) {
                        if (player1Turn.isTurn()) {
                            long millisUntilFinished = betterCountDownTimer.getMillisUntilFinished();
                            betterCountDownTimer.countDownTimer.cancel();
                            betterCountDownTimer.createCountDownTimer(millisUntilFinished);
                            betterCountDownTimer2.countDownTimer.start();
                        }
                    }
                    player1Turn.setTurn(false);
                    player2Turn.setTurn(true);
                }
            }
        });
        turnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (betterCountDownTimer2.getMillisUntilFinished() > 1999) {
                    if (!checkIfEmptyButtonText(turnButton) || !checkIfEmptyButtonText(turnButton2)) {
                        if (player2Turn.isTurn()) {
                            long millisUntilFinished = betterCountDownTimer2.getMillisUntilFinished();
                            betterCountDownTimer2.countDownTimer.cancel();
                            betterCountDownTimer2.createCountDownTimer(millisUntilFinished);
                            betterCountDownTimer.countDownTimer.start();
                        }
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

