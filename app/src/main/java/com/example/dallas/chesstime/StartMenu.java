/**
 * This Android program can be used to replicate a physical two person chess timer.
 * <p>
 * One player presses a startButton, with the turnButtons being used thereafter.
 * <p>
 * The center resetButton can be short pressed to pause/resume the timers,
 * and a long press restarts the timer by reloading the main activity.
 *
 * @author Dallas Gianniny
 * @version 0.9.0
 * @date October 24 2018
 */
package com.example.dallas.chesstime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class StartMenu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * Extra information keys.
     */
    public static final String EXTRA_NUM = "com.example.dallas.chesstime.EXTRA_NUM";
    public static final String EXTRA_TEXT = "com.example.dallas.chesstime.EXTRA_TEXT";

    /**
     * Used to store onItemSelected data from Spinner.
     */
    class ExtraData {
        int number;
        String string;

        public ExtraData() {
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }
    }

    ExtraData extraData = new ExtraData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        getWindow().getDecorView().setSystemUiVisibility(3328);

        final Spinner spinner = findViewById(R.id.minutesSelectSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numeric_labels_array, R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(this);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = findViewById(R.id.setTimeButton);

        /**
         * Sends extra data to TimerMain on press.
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimerActivity();
            }
        });
    }

    /**
     * Adds extra data to intent.
     */
    public void openTimerActivity() {
        Intent intent = new Intent(this, TimerMain.class);
        intent.putExtra(EXTRA_TEXT, extraData.getString());
        intent.putExtra(EXTRA_NUM, extraData.getNumber());
        startActivity(intent);
    }

    /**
     * Assigns String/int user Spinner selection to ExtraData class.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String string = parent.getItemAtPosition(position).toString();
        int number = Integer.parseInt(string);
        extraData.setNumber(number);
        extraData.setString(string);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
