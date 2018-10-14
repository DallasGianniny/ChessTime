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

//        CountDownTimer initialCountDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                textView.setText("" + millisUntilFinished / countDownInterval);
//                timerInfo.setMillisUntilFinished((int) millisUntilFinished);
//            }
//
//            @Override
//            public void onFinish() {
//                textView.setText("Time Up!");
//            }
//        };
//
//        CountDownTimer initialCountDownTimer2 = new CountDownTimer(millisInFuture, countDownInterval) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                textView2.setText("" + millisUntilFinished / countDownInterval);
//                timerInfo2.setMillisUntilFinished((int) millisUntilFinished);
//            }
//
//            @Override
//            public void onFinish() {
//                textView2.setText("Time Up!");
//            }
//        };

//        final Player player1 = new Player(new CountDownTimer(millisInFuture, countDownInterval) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                textView.setText("Seconds Remaining: " + millisUntilFinished / countDownInterval);
//                timerInfo.setMillisUntilFinished((int) millisUntilFinished);
//            }
//
//            @Override
//            public void onFinish() {
//                textView.setText("Time Up!");
//            }
//        });
//
//        final Player player2 = new Player(new CountDownTimer(millisInFuture, countDownInterval) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                textView2.setText("Seconds Remaining: " + millisUntilFinished / countDownInterval);
//                timerInfo2.setMillisUntilFinished((int) millisUntilFinished);
//            }
//
//            @Override
//            public void onFinish() {
//                textView2.setText("Time Up!");
//            }
//        });

        final Player player1 = new Player(initialCountDownTimer);
        final Player player2 = new Player(initialCountDownTimer2);

        player1.setTextViewNum(textView);
        player1.setTimerInfo(timerInfo);

        player2.setTextViewNum(textView2);
        player2.setTimerInfo(timerInfo2);

        player1.setTurn(true);
        player2.setTurn(false);

        //final BetterTimer testTimer = new BetterTimer(60000);
        // Player player1 = new Player(testTimer, testTimer.getMillisRemaining())


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEmptyText(textView)) {
                    player1.startTimer();
                } else if (!checkIfEmptyText(textView) && player1.isTurn()) {

                } else {
                    player1.setTimeRemaining(timerInfo);

                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEmptyText(textView2)) {
                    player2.startTimer();
                } else {
                    Player.BetterTimer
                    player2.setTimeRemaining(timerInfo2);
                }
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

    //    public class BetterTimer {
//
//        private int millisInFuture;
//        private int millisRemaining;
//        private int countDownInterval;
//        private CountDownTimer countDownTimer;
//
//        public BetterTimer(int millisInFuture) {
//            this.millisInFuture = millisInFuture;
//        }
//
//        private void setMillisRemaining(int millisRemaining) {
//            this.millisRemaining = millisRemaining;
//        }
//
//        public int getMillisRemaining() {
//            return millisRemaining;
//        }
//
//        public void setCountDownInterval(int countDownInterval) {
//            this.countDownInterval = countDownInterval;
//        }
//
//        public void createNewTimer(int millisRemaining, int countDownInterval){
//            CountDownTimer countDownTimer = new CountDownTimer(millisRemaining, countDownInterval) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//
//                }
//
//                @Override
//                public void onFinish() {
//
//                }
//            };
//            this.countDownTimer = countDownTimer;
//        }
//
//        public void resumeTimer() {
//
//            CountDownTimer timer = new CountDownTimer(this.millisRemaining, this.countDownInterval) {
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
//
//        public void startTimer() {
//
//            CountDownTimer newTimer = new CountDownTimer(this.millisInFuture, this.countDownInterval) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    TextView textView = findViewById(R.id.textView);
//                    textView.setText("seconds remaining: " + millisUntilFinished / 1000);
//                    if(millisUntilFinished == 0){
//                        textView.setText("seconds remaining: " + 0);
//                    }
//                }
//
//                @Override
//                public void onFinish() {
//
//                }
//            }.start();
//
//        }
//
//
//    }
//
    public boolean checkIfEmptyText(TextView textView) {
        if (textView.getText().toString().equals("")) {
            return true;
        } else return false;
    }
//
//    public class Player{
//        private int timeRemaining;
//
//        private BetterTimer timer;
//
//        public Player(int timeRemaining, BetterTimer timer) {
//            this.timeRemaining = timeRemaining;
//            this.timer = timer;
//        }
//
//        public int getTimeRemaining() {
//            return timeRemaining;
//        }
//
//        public void setTimeRemaining(int timeRemaining) {
//            this.timeRemaining = timeRemaining;
//        }
//
//
//    }

    public class TimerInfo {
        public int millisUntilFinished;

        public TimerInfo() {
        }

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
        private CountDownTimer initialTimer;
        private CountDownTimer newTimer;
        private int countDownInterval = 1000;
        private TextView textView;
        private TimerInfo timerInfo;

        public Player(CountDownTimer timer) {
            this.initialTimer = timer;
        }

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

        public void startTimer() {
            this.initialTimer.start();
        }

        public void startNewTimer() {
            this.newTimer.start();
        }

        public void setNewTimer(BetterTimer newTimer){
            this.newTimer = newTimer.newTimer(this.getTimeRemaining(), countDownInterval, this.textView, this.timerInfo);
        }

        public void resumeTimer(){



        }

        public void setTextViewNum(TextView textView){
            this.textView = textView;
        }

        public void setTimerInfo(TimerInfo timerInfo){
            this.timerInfo = timerInfo;
        }

        public class BetterTimer{

            public BetterTimer() {
            }

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

//        private CountDownTimer newTimer(){
//            CountDownTimer newTimer = new CountDownTimer(this.getTimeRemaining(), countDownInterval) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//
//                }
//
//                @Override
//                public void onFinish() {
//
//                }
//            }
//        }

    }

}
