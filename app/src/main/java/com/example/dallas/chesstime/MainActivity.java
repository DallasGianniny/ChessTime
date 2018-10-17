package com.example.dallas.chesstime;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final int millisInFuture = 300000;
//        final int countDownInterval = 1000;
//
//        final TimerInfo timerInfo = new TimerInfo();
//        final TimerInfo timerInfo2 = new TimerInfo();
//
//        final TextView textView = findViewById(R.id.textView);
//        final TextView textView2 = findViewById(R.id.textView2);
//
//        Button button = findViewById(R.id.button);
//        Button button2 = findViewById(R.id.button2);
//
//        final BetterTimer player1Timer = new BetterTimer();
//        final BetterTimer player2Timer = new BetterTimer();
//
//        timerInfo.setMillisUntilFinished(millisInFuture);
//        timerInfo2.setMillisUntilFinished(millisInFuture);
//
//        final Player player1 = new Player();
//        final Player player2 = new Player();
//
//        player1.setTimeRemaining(timerInfo);
//        player2.setTimeRemaining(timerInfo2);
//
//        player1.setTextViewNum(textView);
//        player2.setTextViewNum(textView2);
//
//        player1.setTimerInfo(timerInfo);
//        player2.setTimerInfo(timerInfo2);
//
//        player1.setTurn(true);
//        player2.setTurn(false);
//
//        player1.setNewTimer(player1Timer);
//        player2.setNewTimer(player2Timer);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (checkIfEmptyText(textView2)) {
//                    player1.startNewTimer();
//                }
//                else if (player2.getTimeRemaining() < 299000){
//                    timerInfo2.setMillisUntilFinished(player2.getTimeRemaining());
//                    player2.setTimeRemaining(timerInfo2);
//                    player2.newTimer.cancel();
//                    player2.setNewTimer(player2Timer);
//                    player2.startNewTimer();
//                } else {
//
//                }
//            }
//        });
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (checkIfEmptyText(textView)) {
//                    player2.startNewTimer();
//                } else if (player1.getTimeRemaining() < 299000){
//                    timerInfo.setMillisUntilFinished(player1.getTimeRemaining());
//                    player1.setTimeRemaining(timerInfo);
//                    player1.newTimer.cancel();
//                    player1.setNewTimer(player1Timer);
//                    player1.startNewTimer();
//                } else {
//
//                }
//            }
//        });
//    }
//
//    public boolean checkIfEmptyText(TextView textView) {
//        if (textView.getText().toString().equals("")) {
//            return true;
//        } else return false;
//    }
//
//    public class TimerInfo {
//        public int millisUntilFinished;
//
//        public TimerInfo() {}
//
//        public int getMillisUntilFinished() {
//            return millisUntilFinished;
//        }
//
//        public void setMillisUntilFinished(int millisUntilFinished) {
//            this.millisUntilFinished = millisUntilFinished;
//        }
//    }
//
//    public class Player {
//        private int timeRemaining;
//        private boolean isTurn;
//        private CountDownTimer newTimer;
//        private int countDownInterval = 1000;
//        private TextView textView;
//        private TimerInfo timerInfo;
//
//        public Player() {}
//
//        public int getTimeRemaining() {
//            return timeRemaining;
//        }
//
//        public void setTimeRemaining(TimerInfo timerInfo) {
//            this.timeRemaining = timerInfo.getMillisUntilFinished();
//        }
//
//        public boolean isTurn() {
//            return isTurn;
//        }
//
//        public void setTurn(boolean turn) {
//            isTurn = turn;
//        }
//
//        public void startNewTimer() {
//            this.newTimer.start();
//        }
//
//        public void setNewTimer(BetterTimer betterTimer){
//            this.newTimer = betterTimer.newTimer(this.getTimeRemaining(), countDownInterval, this.textView, this.timerInfo);
//        }
//
//        public void resumeTimer(){
//
//        }
//
//        public void setTextViewNum(TextView textView){
//            this.textView = textView;
//        }
//
//        public void setTimerInfo(TimerInfo timerInfo){
//            this.timerInfo = timerInfo;
//        }
//
//    }
//
//    public class BetterTimer{
//
//        public BetterTimer() {}
//
//        public CountDownTimer newTimer(int millisInFuture, final int countDownInterval, final TextView textView, final TimerInfo timerInfo){
//            CountDownTimer newTimer = new CountDownTimer(millisInFuture, countDownInterval) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    textView.setText("" + millisUntilFinished / countDownInterval);
//                    timerInfo.setMillisUntilFinished((int) millisUntilFinished);
//                }
//
//                @Override
//                public void onFinish() {
//                    textView.setText("Time Up!");
//                }
//            };
//            return newTimer;
//        }
//    }

        class Timer {
            private long time;
            private long player1Time;
            private long player2Time;
            private int playerState;
            private CountDownTimer countDownTimer;

            public Timer(long time) {
                this.time = time;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public long getPlayer1Time() {
                return player1Time;
            }

            public void setPlayer1Time(long player1Time) {
                this.player1Time = player1Time;
            }

            public long getPlayer2Time() {
                return player2Time;
            }

            public void setPlayer2Time(long player2Time) {
                this.player2Time = player2Time;
            }

            public int getPlayerState() {
                return playerState;
            }

            public void setPlayerState(int playerState) {
                this.playerState = playerState;
            }

            public void setCountDownTimer(CountDownTimer countDownTimer) {
                this.countDownTimer = countDownTimer;
            }
        }

        class Playerx {
            private int playerNum;
            private boolean turn;
            public int turnNum;

            public Playerx(int playerNum) {
                this.playerNum = playerNum;
            }

            public boolean isTurn() {
                return turn;
            }

            public void setTurn(boolean turn) {
                this.turn = turn;
            }
        }

        final Playerx player1 = new Playerx(1);
        final Playerx player2 = new Playerx(2);

        Button startButton = findViewById(R.id.firstTurnbutton);
        Button startButton2 = findViewById(R.id.firstTurnButton2);
        Button turnButton = findViewById(R.id.button);
        Button turnButton2 = findViewById(R.id.button2);
        final TextView textView = findViewById(R.id.textView);
        final TextView textView2 = findViewById(R.id.textView2);

        final CountDownTimer countDownTimer = new CountDownTimer(300000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textView.setText("" + millisUntilFinished /1000);
                textView2.setText("");
            }

            @Override
            public void onFinish() {
                textView.setText("finish");
                textView2.setText("finito");
            }
        }.start();

        final long x = 2000;
        final long y = 10000;


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1.setTurn(true);
                player2.setTurn(false);
                countDownTimer.cancel();
                countDownTimer.onTick(x);
                countDownTimer.start();
            }
        });
        startButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2.setTurn(true);
                player1.setTurn(false);
                countDownTimer.cancel();
                countDownTimer.onTick(y);
                countDownTimer.start();
            }
        });

        turnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        turnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}

