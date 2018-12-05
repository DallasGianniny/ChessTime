/**
 * This Android program can be used to replicate a physical two person chess timer.
 * <p>
 * Initial button press starts respective timer, additional button presses start opposite timer.
 * <p>
 * The center resetButton can be short pressed to pause/resume the timers,
 * and a long press restarts the timer by reloading the main activity.
 * <p>
 * Initial time is set on start menu with top seek bar.
 *
 * @author Dallas Gianniny
 * @version 1.0.1
 * @date December 4 2018
 */
package com.example.dallas.chesstime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class StartMenu extends AppCompatActivity {

    //Extra information keys.
    public static final String EXTRA_NUM = "com.example.dallas.chesstime.EXTRA_NUM";

    //Used to store onItemSelected data from Spinner.
    class ExtraData {
        int number;

        ExtraData() {
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    ExtraData extraData = new ExtraData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        //Hides the status bar.
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        getWindow().getDecorView().setSystemUiVisibility(3328);

        //Initialize UI elements
        Button setTimeButton = findViewById(R.id.setTimeButton);
        SeekBar seekBar = findViewById(R.id.seekBar);
        final TextView selectedTimeText = findViewById(R.id.selectedTimeText);

        //Default selection
        selectedTimeText.setText("30:00");

        //User time input 0-59 corrected to 1-60
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarFix = 1;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarFix = i + 1;
                selectedTimeText.setText(seekBarFix + ":00");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //Sends extra data to TimerMain on press.
        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimerActivity();
            }
        });
    }

    //Adds extra data to intent (timer_main)
    public void openTimerActivity() {
        SeekBar seekBar = findViewById(R.id.seekBar);
        int number = seekBar.getProgress() + 1;
        extraData.setNumber(number);
        Intent intent = new Intent(this, TimerMain.class);
        intent.putExtra(EXTRA_NUM, extraData.getNumber());
        startActivity(intent);
    }
}