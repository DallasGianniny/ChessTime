/**
 * This Android program can be used to replicate a physical two person chess timer.
 * <p>
 * One player presses a startButton, with the turnButtons being used thereafter.
 * <p>
 * The center resetButton can be short pressed to pause/resume the timers,
 * and a long press restarts the timer by reloading the main activity.
 *
 * @author Dallas Gianniny
 * @version 0.1.0
 * @date October 19 2018
 */
package com.example.dallas.chesstime;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        getWindow().getDecorView().setSystemUiVisibility(3328);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        /**
         * Used for creating and displaying timers.
         * <p>
         * BetterCountDownTimer uses built in CountDownTimer
         * in conjunction with UI elements to display a timer (secs).
         */
        class BetterCountDownTimer {
            private Button button;
            private long millisUntilFinished;
            private CountDownTimer countDownTimer;

            public BetterCountDownTimer() {
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

            /**
             * Creates a new CountDownTimer using param millisInFuture.
             * <p>
             * Sets this.button text to seconds remaining.
             * Saves time remaining to private attribute millisUntilFinished.
             * Changes turnButton color based on time remaining.
             *
             * @param millisInFuture Starting time for countDownTimer, effectively setting timer.
             */
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

        /**
         * Assigns button UI elements by id.
         * <p>
         * Names without the number 2 are associated with elements
         * on the nearest the physical bottom of the phone.
         */
        Button startButton = findViewById(R.id.firstTurnbutton);
        Button startButton2 = findViewById(R.id.firstTurnButton2);
        final Button turnButton = findViewById(R.id.button);
        final Button turnButton2 = findViewById(R.id.button2);
        final Button resetButton = findViewById(R.id.resetButton);

        /**
         * Constructs instance of BetterCountDownTimer and PlayerTurn for each player.
         */
        final BetterCountDownTimer betterCountDownTimer = new BetterCountDownTimer();
        final BetterCountDownTimer betterCountDownTimer2 = new BetterCountDownTimer();
        final PlayerTurn playerTurn = new PlayerTurn();
        final PlayerTurn playerTurn2 = new PlayerTurn();

        /**
         * Assigns button UI elements to respective timers.
         */
        betterCountDownTimer.setButton(turnButton);
        betterCountDownTimer2.setButton(turnButton2);

        /**
         * On resetButton long press, resets whole activity, effectively resetting timer
         */
        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            }
        });
        /**
         * On resetButton short press, pauses both timers.
         * <p>
         * Must be short pressed a second time to resume play.
         */
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
        /**
         * Initializes both timers at a predetermined value and starts one timer on press.
         */
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
        /**
         * Main buttons used for starting and stopping timers
         * once game is already in motion.
         * <p>
         * Checks if game is paused, if timer is greater than near zero,
         * if button(timer) texts are not empty, and if it is the player's turn.
         * <p>
         * Modifies playerTurn.turn boolean flags.
         */
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

    /**
     * Checks if button text is empty string.
     *
     * @param button Button UI element to have text field checked.
     * @return boolean true if empty string, else false.
     */
    public boolean checkIfEmptyButtonText(Button button) {
        return button.getText().toString().equals("");
    }

    /**
     * Changes background color of button based on given time remaining.
     * <p>
     * 5 minutes > Green > 4 minutes
     * <p>
     * 4 minutes > Light Green > 3 minutes
     * <p>
     * 3 minutes > Light Orange > 2 minutes
     * <p>
     * 2 minutes > Orange > 1 minute
     * <p>
     * 1 minute > Red > 30 seconds
     * <p>
     * 30 seconds > Flashing Dark Red > 0 seconds
     *
     * @param button              Button UI element to have its color changed.
     * @param millisUntilFinished Amount of milliseconds remaining on timer.
     */
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