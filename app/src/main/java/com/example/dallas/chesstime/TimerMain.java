///**
// * This Android program can be used to replicate a physical two person chess timer.
// *
// * One player presses a startButton, with the turnButtons being used thereafter.
// *
// * The center resetButton can be short pressed to pause/resume the timers,
// * and a long press restarts the timer by reloading the main activity.
// *
// * @author Dallas Gianniny
// * @version 0.9.4
// * @date November 7 2018
// */
package com.example.dallas.chesstime;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

public class TimerMain extends AppCompatActivity {

//    /**
//     * Used for creating and displaying timers.
//     *
//     * BetterCountDownTimer uses built in CountDownTimer
//     * in conjunction with UI elements to display a timer (secs).
//     */
    class BetterCountDownTimer {
        private long millisUntilFinished;
        private long initialTime;
        private long prevTime;
        private Button button;
        private CountDownTimer countDownTimer;

        BetterCountDownTimer() {
        }

        Button getButton() {
            return button;
        }

        void setButton(Button button) {
            this.button = button;
        }

        void setMillisUntilFinished(long millisUntilFinished) {
            this.millisUntilFinished = millisUntilFinished;
        }

        long getMillisUntilFinished() {
            return millisUntilFinished;
        }

        void setInitialTime(long initialTime) {
            this.initialTime = initialTime;
        }

        long getPrevTime() {
            return prevTime;
        }

        void setPrevTime() {
            this.prevTime = this.millisUntilFinished;
        }

        /**
         * Creates a new CountDownTimer using param millisInFuture.
         * <p>
         * Sets this.button text to seconds remaining.
         * Saves time remaining to private attribute millisUntilFinished.
         * Changes turnButton color based on a ratio of time remaining.
         * <p>
         * On finish sets button background to icon background
         * and sets text to empty string.
         * <p>
         * Uses an ArrayList to change turnIndicator green after 1 second.
         *
         * @param millisInFuture Starting time for countDownTimer, effectively setting timer.
         */
        void createCountDownTimer(long millisInFuture) {
            setMillisUntilFinished(millisInFuture);
            final ArrayList<Boolean> oneTick = new ArrayList<>();
            this.countDownTimer = new CountDownTimer(millisInFuture, 1000) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    setMillisUntilFinished(millisUntilFinished);
                    long minutes = millisUntilFinished / 60000;
                    long seconds = (millisUntilFinished / 1000) - (minutes * 60);
                    if (seconds < 10) {
                        button.setText(minutes + ":0" + seconds);
                    } else button.setText(minutes + ":" + seconds);
                    changeButtonColor(button, millisUntilFinished, initialTime);
                    oneTick.add(true);
                    if (oneTick.size() > 1) {
                        toggleGreenTurnIndicator(true);
                        oneTick.clear();
                    }
                }

                @Override
                public void onFinish() {
                    button.setBackgroundResource(R.drawable.chesstime);
                    button.setText(" ");
                }
            };
        }
    }

    class PlayerTurn {
        private boolean turn;
        private boolean paused;

        PlayerTurn() {
            this.paused = false;
        }

        boolean isTurn() {
            return turn;
        }

        void setTurn(boolean turn) {
            this.turn = turn;
        }

        boolean isPaused() {
            return paused;
        }

        void setPaused(boolean paused) {
            this.paused = paused;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_main);

//        /**
//         * Attempts to get extra information from StartMenu activity.
//         */
        Intent intent = getIntent();
        int num = intent.getIntExtra(StartMenu.EXTRA_NUM, 0);
//        /**
//         * Assigns amount of time for each player.
//         */
        final long userSetTime = minutesToMilliseconds(num);

//        /**
//         * Hides status bar.
//         *
//         * Forces screen on.
//         */
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        getWindow().getDecorView().setSystemUiVisibility(3328);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//        /**
//         * Assigns button UI elements by id.
//         *
//         * Names without the number 2 are associated with elements
//         * on the nearest the physical bottom of the phone.
//         */
        final Button turnButton = findViewById(R.id.button);
        final Button turnButton2 = findViewById(R.id.button2);
        final Button resetButton = findViewById(R.id.resetButton);

//        /**
//         * Assigns linear layout UI elements by id.
//         * Hides turn indicator before game begins.
//         */
        refreshTurnIndicatorVis(false, false);

//        /**
//         * Constructs instance of BetterCountDownTimer and PlayerTurn for each player.
//         */
        final BetterCountDownTimer betterCountDownTimer = new BetterCountDownTimer();
        final BetterCountDownTimer betterCountDownTimer2 = new BetterCountDownTimer();
        final PlayerTurn playerTurn = new PlayerTurn();
        final PlayerTurn playerTurn2 = new PlayerTurn();

//        /**
//         * Assigns button UI elements and initial time to respective timers.
//         */
        betterCountDownTimer.setButton(turnButton);
        betterCountDownTimer2.setButton(turnButton2);
        betterCountDownTimer.setInitialTime(userSetTime);
        betterCountDownTimer2.setInitialTime(userSetTime);

//        /**
//         * On resetButton long press, resets whole activity, effectively resetting timer.
//         */
        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            }
        });
//        /**
//         * On resetButton short press, pauses both timers.
//         *
//         * Must be short pressed a second time to resume play.
//         */
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isButtonTextEmpty(turnButton) || !isButtonTextEmpty(turnButton2)) {
                    if (!playerTurn.isPaused()) {
                        long millisUntilFinished = betterCountDownTimer.getMillisUntilFinished();
                        long millisUntilFinished2 = betterCountDownTimer2.getMillisUntilFinished();
                        betterCountDownTimer.countDownTimer.cancel();
                        betterCountDownTimer2.countDownTimer.cancel();
                        betterCountDownTimer.createCountDownTimer(millisUntilFinished);
                        betterCountDownTimer2.createCountDownTimer(millisUntilFinished2);
                        playerTurn.setPaused(true);
                    } else {
                        if (betterCountDownTimer.getMillisUntilFinished() > 1999 && betterCountDownTimer2.getMillisUntilFinished() > 1999) {
                            if (playerTurn.isTurn()) betterCountDownTimer.countDownTimer.start();
                            if (playerTurn2.isTurn()) betterCountDownTimer2.countDownTimer.start();
                            playerTurn.setPaused(false);
                        }
                    }
                }
            }
        });

//        /**
//         * Main buttons used for starting and stopping timers
//         * both at start and once game is already in motion.
//         *
//         * Checks if button texts are empty (game not started)
//         * and if true starts timer.
//         *
//         * Checks if game is paused, if timer is greater than near zero,
//         * if button(timer) texts are not empty, and if it is the player's turn.
//         *
//         * Modifies playerTurn.turn boolean flags.
//         *
//         * Attempts to force at least 1 second to pass each turn
//         */
        turnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButtonTextEmpty(turnButton) && isButtonTextEmpty(turnButton2)) {
                    changeButtonColor(betterCountDownTimer.button, betterCountDownTimer.getMillisUntilFinished(), userSetTime);
                    betterCountDownTimer.createCountDownTimer(userSetTime);
                    betterCountDownTimer2.createCountDownTimer(userSetTime);
                    betterCountDownTimer.setPrevTime();
                    betterCountDownTimer2.setPrevTime();
                    betterCountDownTimer.countDownTimer.start();
                    playerTurn.setTurn(true);
                    refreshTurnIndicatorVis(true, false);
                } else if (!playerTurn.isPaused()) {
                    if (betterCountDownTimer.getPrevTime() - betterCountDownTimer.getMillisUntilFinished() > 1000) {
                        if (betterCountDownTimer.getMillisUntilFinished() > 1999) {
                            if (!isButtonTextEmpty(turnButton) || !isButtonTextEmpty(turnButton2)) {
                                if (playerTurn.isTurn()) {
                                    long millisUntilFinished = betterCountDownTimer.getMillisUntilFinished();
                                    betterCountDownTimer.setPrevTime();
                                    betterCountDownTimer.countDownTimer.cancel();
                                    betterCountDownTimer.createCountDownTimer(millisUntilFinished);
                                    betterCountDownTimer2.countDownTimer.start();
                                }
                            }
                            playerTurn.setTurn(false);
                            playerTurn2.setTurn(true);
                            refreshTurnIndicatorVis(false, true);
                            toggleGreenTurnIndicator(false);
                        }
                    }
                }
            }
        });
        turnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButtonTextEmpty(turnButton) && isButtonTextEmpty(turnButton2)) {
                    changeButtonColor(betterCountDownTimer2.button, betterCountDownTimer2.getMillisUntilFinished(), userSetTime);
                    betterCountDownTimer.createCountDownTimer(userSetTime);
                    betterCountDownTimer2.createCountDownTimer(userSetTime);
                    betterCountDownTimer.setPrevTime();
                    betterCountDownTimer2.setPrevTime();
                    betterCountDownTimer2.countDownTimer.start();
                    playerTurn2.setTurn(true);
                    refreshTurnIndicatorVis(false, true);
                } else if (!playerTurn2.isPaused()) {
                    if (betterCountDownTimer2.getPrevTime() - betterCountDownTimer2.getMillisUntilFinished() > 1000) {
                        if (betterCountDownTimer2.getMillisUntilFinished() > 1999) {
                            if (!isButtonTextEmpty(turnButton) || !isButtonTextEmpty(turnButton2)) {
                                if (playerTurn2.isTurn()) {
                                    long millisUntilFinished = betterCountDownTimer2.getMillisUntilFinished();
                                    betterCountDownTimer2.setPrevTime();
                                    betterCountDownTimer2.countDownTimer.cancel();
                                    betterCountDownTimer2.createCountDownTimer(millisUntilFinished);
                                    betterCountDownTimer.countDownTimer.start();
                                }
                            }
                            playerTurn.setTurn(true);
                            playerTurn2.setTurn(false);
                            refreshTurnIndicatorVis(true, false);
                            toggleGreenTurnIndicator(false);
                        }
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
    public boolean isButtonTextEmpty(Button button) {
        return button.getText().toString().equals("");
    }

    /**
     * Changes button background color based on time remaining
     * cut into fifths
     *
     * @param button              Button UI element to have its color changed.
     * @param millisUntilFinished Amount of milliseconds remaining on timer.
     * @param initialTime         Initial time set for timer by user (in ms).
     */
    public void changeButtonColor(Button button, long millisUntilFinished, long initialTime) {
        float ratio = ((float) millisUntilFinished / initialTime);
        if (ratio > 0.8)
            button.setBackgroundColor(getResources().getColor(R.color.Five));
        else if ((ratio <= 0.8) && (ratio > 0.6))
            button.setBackgroundColor(getResources().getColor(R.color.Four));
        else if ((ratio <= 0.6) && (ratio > 0.4))
            button.setBackgroundColor(getResources().getColor(R.color.Three));
        else if ((ratio <= 0.4) && (ratio > 0.2))
            button.setBackgroundColor(getResources().getColor(R.color.Two));
        else if ((ratio <= 0.2) && (ratio > 0.1))
            button.setBackgroundColor(getResources().getColor(R.color.One));
        else if (ratio <= 0.1) {
            if ((((int) millisUntilFinished / 1000) % 2) != 0) {
                button.setBackgroundColor(getResources().getColor(R.color.OneHalf));
            } else button.setBackgroundColor(getResources().getColor(R.color.Gray));
        }
    }

    /**
     * Converts minutes to milliseconds.
     *
     * @param num integer value of minutes.
     * @return long value conversion of minutes into milliseconds.
     */
    public long minutesToMilliseconds(int num) {
        num *= 60000;
        return num;
    }

    /**
     * Shows/hides halves of linear layout to show turn status.
     *
     * @param player1 if true, half toward player 1 will show
     * @param player2 if true, half toward player 2 will show
     */
    public void refreshTurnIndicatorVis(boolean player1, boolean player2) {
        View turnIndicator = findViewById(R.id.turnIndicator);
        View turnIndicator2 = findViewById(R.id.turnIndicator2);
        if (player1) {
            turnIndicator.setVisibility(View.VISIBLE);
            turnIndicator2.setVisibility(View.INVISIBLE);
        } else if (player2) {
            turnIndicator.setVisibility(View.INVISIBLE);
            turnIndicator2.setVisibility(View.VISIBLE);
        } else {
            turnIndicator.setVisibility(View.INVISIBLE);
            turnIndicator2.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Toggles turnIndicator color; true green, false white
     * @param green true to toggle color to green, false for white
     */
    public void toggleGreenTurnIndicator(boolean green) {
        View turnIndicator = findViewById(R.id.turnIndicator);
        View turnIndicator2 = findViewById(R.id.turnIndicator2);
        if (green) {
            turnIndicator.setBackgroundColor(getResources().getColor(R.color.Five));
            turnIndicator2.setBackgroundColor(getResources().getColor(R.color.Five));
        } else {
            turnIndicator.setBackgroundColor(getResources().getColor(R.color.White));
            turnIndicator2.setBackgroundColor(getResources().getColor(R.color.White));
        }
    }
}