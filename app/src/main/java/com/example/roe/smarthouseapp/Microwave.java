package com.example.roe.smarthouseapp;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Microwave extends AppCompatActivity/* implements View.OnClickListener */{

    private EditText cookTime;
    private Button start;
    private Button stop;
    private Button reset;
    private TextView timer;

    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private Chronometer chronometer;
    private Vibrator vibrator;

    private long time;
    private int zero = 0;
    private String timeSet;
    private long progress = 0;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microwave);

        final int duration = Toast.LENGTH_SHORT;

        cookTime = (EditText) findViewById(R.id.cookTime);
        start = (Button) findViewById(R.id.button1);
        stop = (Button) findViewById(R.id.button2);
        reset = (Button) findViewById(R.id.button3);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        cookTime.setText("");
        start.setEnabled(true);
        stop.setEnabled(false);
        reset.setEnabled(false);

        cookTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cookTime.setText("");
            }//end onClick function
        });//end ClickListener

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(cookTime.getText().toString())) {
                    cookTime.setError("Enter time here");
                    return;
                }else{
                    start.setEnabled(false);
                    stop.setEnabled(true);
                    reset.setEnabled(true);

                    if(zero == 0) {
                        try{
                            timeSet = cookTime.getText().toString();
                            DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
                            date = formatter.parse(timeSet);
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }

                        Toast toast = Toast.makeText(Microwave.this, "Starting Microwave for " + timeSet + " minutes.", duration);
                        toast.show();
                        time = Integer.parseInt(timeSet) * 1000;
                        zero = 1;
                    }//end if statement

                    countDownTimer =  new CountDownTimer(time, 1000) {

                        TextView timer = (TextView) findViewById(R.id.timer);

                        public void onTick(long millisUntilFinished) {
                            String v = String.format("%02d", millisUntilFinished / 60000);
                            int va = (int)( (millisUntilFinished % 60000 )/ 1000);
                            timer.setText(v +":"+ String.format("%02d", va));
                            progress = time - millisUntilFinished;
                            progressBar.setProgress((int)progress);
                            time -= 1000;
                        }//end onTick function

                        public void onFinish() {
                            cookTime.setText("");
                            timer.setText("00:00");
                            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(500);
                        }//end onFinish function

                    }.start();//*/
                }//end if statement
            }//end onClick function);
        });//end Start Button Listener


        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                start.setEnabled(true);
                stop.setEnabled(false);
                reset.setEnabled(true);

                countDownTimer.cancel();

                Toast toast = Toast.makeText(Microwave.this, "Stopping Microwave", duration);
                toast.show();

            }//end onClick function);
        });//end Stop Button Listener

        reset.setOnClickListener(new View.OnClickListener() {

            TextView timer = (TextView) findViewById(R.id.timer);

            @Override
            public void onClick(View view) {
                start.setEnabled(true);
                stop.setEnabled(false);
                reset.setEnabled(false);

                cookTime.setText("");
                countDownTimer.cancel();
                timer.setText("00:00");
                progress = 0;

                zero = 0;

                Toast toast = Toast.makeText(Microwave.this, "Reset Timer", duration);
                toast.show();
            }//end onClick function);
        });//end Stop Button Listener

    }//end onCreate function

    protected void onProgressUpdate(Integer ...value){
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(value[0]);
    }//end onProgressUpdate function

    protected void onPostExecute(String result){
        //progressBar.setVisibility(View.INVISIBLE);
    }//end onPostExecute function

    /**
     * This is activity garage that allows the user to open and close the
     * garage door and turn a light off and on. This class is written
     * by Narges Karimi Tabar.
     */
    public static class ActivityGarage extends AppCompatActivity {
        /** Object to hold activity name   */
        protected static final String ACTIVITY_NAME = "ActivityGarage";
        /** Attribute that holds an object of type Switch  */
        Switch mySwitch;
        /** Attribute that holds an object of type ImageButton  */
        ImageButton doorClosed;
        /** Attribute that holds an object of type ImageButton  */
        ImageButton doorOpened;
        /** Attribute that holds an object of type ImageButton  */
        ImageButton yellowBulb;
        /** Attribute that holds an object of type ImageButton  */
        ImageButton whiteBulb;

        /**
         * This functions sets the main funtionalities of different objects
         * like imagebutton and switch.
         * @param savedInstanceState
         */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_garage);
            Log.i(ACTIVITY_NAME, "In OnCreate()");

            mySwitch = (Switch) findViewById(R.id.switchLight);
            //set the switch to on
            //  mySwitch.setChecked(true);

            // attaching the listerning to check if the state changes
            mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /**
                 * Switching the switch button to turn the light off and on
                 * @param buttonView
                 * @param isChecked
                 */
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Context context = getApplicationContext();
                    yellowBulb = (ImageButton) findViewById(R.id.garageLightOn);
                    if (isChecked) { // if the switch is checked
                        CharSequence text = "Light On"; // the text message that will show in the toast
                        yellowBulb.setVisibility(View.VISIBLE);
                        int duration = Toast.LENGTH_SHORT; // setting the duration the toast is on screen for
                        Toast toast = Toast.makeText(context, text, duration); // making Toast object toast
                        // toast.show(); // displaying toast object on bottom of screen
                        mySwitch.setChecked(true);

                    } else { // if the switch is not checked
                        whiteBulb = (ImageButton) findViewById(R.id.garageLight);
                        CharSequence text = "Light Off";
                        yellowBulb.setVisibility(View.INVISIBLE);
                        whiteBulb.setVisibility(View.VISIBLE);

                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        //  toast.show(); // displaying toast object on bottom of screen
                        mySwitch.setChecked(false);
                    }

                }
            });

            Button openButton = (Button) findViewById(R.id.openDoor);
            doorClosed = (ImageButton) findViewById(R.id.doorButtonCLosed);
            //set the switch to on
            // mySwitch.setChecked(true);

            // attaching the listerning to check if the state changes
            openButton.setOnClickListener(new View.OnClickListener() {
                /**
                 * Setting the light to turn on when the garage door is opened
                 * @param v
                 */
                @Override
                public void onClick(View v) {
                    //set the switch to on
                    CharSequence text = "Door Opened";
                    doorClosed.setVisibility(View.GONE);
                    doorOpened.setVisibility(View.VISIBLE);
                    int duration = Toast.LENGTH_SHORT; // setting the duration the toast is on screen for
                    Toast toast = Toast.makeText(ActivityGarage.this, text, duration); // making Toast object toast
                    toast.show();
                    mySwitch.setChecked(true);
                }
            });

            Button closeButton = (Button) findViewById(R.id.closeDoor);
            doorOpened = (ImageButton) findViewById(R.id.doorButtonOpened);
            closeButton.setOnClickListener(new View.OnClickListener() {
                /**
                 * Setting the light to turn off when the garage door is closed
                 * @param v
                 */
                @Override
                public void onClick(View v) {
                    //set the switch to on
                    CharSequence text = "Door Closed";
                    doorClosed.setVisibility(View.VISIBLE);
                    doorOpened.setVisibility(View.INVISIBLE);
                    int duration = Toast.LENGTH_SHORT; // setting the duration the toast is on screen for
                    Toast toast = Toast.makeText(ActivityGarage.this, text, duration); // making Toast object toast
                    toast.show();
                    mySwitch.setChecked(false);
                }
            });

            Button stopButton = (Button) findViewById(R.id.stopDoor);
            doorOpened = (ImageButton) findViewById(R.id.doorButtonOpened);
            stopButton.setOnClickListener(new View.OnClickListener() {
                /**
                 * Creating a toast message when the door is getting closed
                 * @param v
                 */
                @Override
                public void onClick(View v) {
                    //set the switch to on
                    CharSequence text = "Door opening/closing Stopped";
                    int duration = Toast.LENGTH_SHORT; // setting the duration the toast is on screen for
                    Toast toast = Toast.makeText(ActivityGarage.this, text, duration); // making Toast object toast
                    toast.show();
                }
            });
        }
    }
}//end Microwave class
