package com.example.roe.smarthouseapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RadioActivity extends AppCompatActivity {

    protected TextView scrollText;
    protected Button preset1;
    protected Button preset2;
    protected Button preset3;
    protected Button preset4;
    protected Button preset5;
    protected Button preset6;

    protected String preset1Info = "89.9 | Hot 89.9 (Top 40/Pop) : Justin Beiber - Baby";
    protected String preset2Info = "105.3 | KISS FM (Top 40/Pop) : Drake - Hotline Bling";
    protected String preset3Info = "98.5 | The Jewel 98.5 (Adult Contemporary) : The Beatles - Hello Goodbye";
    protected String preset4Info = "106.9 | JUMP! 106.9 (Top 40/Pop) : Pharrel Williams - Happy";
    protected String preset5Info = "106.1 | 106.1 CHEZ (Classic Rock) : Joan Jett & the Blackhearts - We Love Rock N Roll";
    protected String preset6Info = "100.3 | Majic 100 (Adult Contemporary) : Meghan Trainer - I'll Be Home";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        scrollText = (TextView) findViewById(R.id.scrollingTicker);
        scrollText.setSelected(true);

        preset1 = (Button) findViewById(R.id.preset1);
        preset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollText.setText(preset1Info);
            }
        });

        preset1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final EditText input = new EditText(getApplicationContext());
                input.setTextColor(Color.BLACK);
                input.setFilters(new InputFilter[] {
                        new InputFilter.LengthFilter(5), DigitsKeyListener.getInstance(false, true) // false sign, true boolean
                });
                input.setKeyListener(DigitsKeyListener.getInstance());

                AlertDialog.Builder alert = new AlertDialog.Builder(RadioActivity.this);
                alert.setTitle("Change Preset 1")
                        .setView(input)
                        .setPositiveButton("Change Preset 1", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!input.getText().toString().equals("")) {
                                        /* user long pressed button */
                                    preset1Info = input.getText().toString();
                                } else {
                                        /* user didn't enter anything in the dialog */
                                    Toast.makeText(getApplicationContext(), "Nothing was entered!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    /* user cancelled the trip */
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        });

        preset2 = (Button) findViewById(R.id.preset2);
        preset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollText.setText(preset2Info);
            }
        });

        preset2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final EditText input = new EditText(getApplicationContext());
                input.setTextColor(Color.BLACK);
                input.setFilters(new InputFilter[] {
                        new InputFilter.LengthFilter(5), DigitsKeyListener.getInstance(false, true) // false sign, true boolean
                });
                input.setKeyListener(DigitsKeyListener.getInstance());

                AlertDialog.Builder alert = new AlertDialog.Builder(RadioActivity.this);
                alert.setTitle("Change Preset 2")
                        .setView(input)
                        .setPositiveButton("Change Preset 2", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!input.getText().toString().equals("")) {
                                        /* user long pressed button */
                                    preset2Info = input.getText().toString();
                                } else {
                                        /* user didn't enter anything in the dialog */
                                    Toast.makeText(getApplicationContext(), "Nothing was entered!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    /* user cancelled the trip */
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        });


        preset3 = (Button) findViewById(R.id.preset3);
        preset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollText.setText(preset3Info);
            }
        });

        preset3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final EditText input = new EditText(getApplicationContext());
                input.setTextColor(Color.BLACK);
                input.setFilters(new InputFilter[] {
                        new InputFilter.LengthFilter(5), DigitsKeyListener.getInstance(false, true) // false sign, true boolean
                });
                input.setKeyListener(DigitsKeyListener.getInstance());

                AlertDialog.Builder alert = new AlertDialog.Builder(RadioActivity.this);
                alert.setTitle("Change Preset 3")
                        .setView(input)
                        .setPositiveButton("Change Preset 3", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!input.getText().toString().equals("")) {
                                        /* user long pressed button */
                                    preset3Info = input.getText().toString();
                                } else {
                                        /* user didn't enter anything in the dialog */
                                    Toast.makeText(getApplicationContext(), "Nothing was entered!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    /* user cancelled the trip */
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        });

        preset4 = (Button) findViewById(R.id.preset4);
        preset4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollText.setText(preset4Info);
            }
        });

        preset4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final EditText input = new EditText(getApplicationContext());
                input.setTextColor(Color.BLACK);
                input.setFilters(new InputFilter[] {
                        new InputFilter.LengthFilter(5), DigitsKeyListener.getInstance(false, true) // false sign, true boolean
                });
                input.setKeyListener(DigitsKeyListener.getInstance());

                AlertDialog.Builder alert = new AlertDialog.Builder(RadioActivity.this);
                alert.setTitle("Change Preset 4")
                        .setView(input)
                        .setPositiveButton("Change Preset 4", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!input.getText().toString().equals("")) {
                                        /* user long pressed button */
                                    preset4Info = input.getText().toString();
                                } else {
                                        /* user didn't enter anything in the dialog */
                                    Toast.makeText(getApplicationContext(), "Nothing was entered!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    /* user cancelled the trip */
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        });

        preset5 = (Button) findViewById(R.id.preset5);
        preset5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollText.setText(preset5Info);
            }
        });

        preset5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final EditText input = new EditText(getApplicationContext());
                input.setTextColor(Color.BLACK);
                input.setFilters(new InputFilter[] {
                        new InputFilter.LengthFilter(5), DigitsKeyListener.getInstance(false, true) // false sign, true boolean
                });
                input.setKeyListener(DigitsKeyListener.getInstance());

                AlertDialog.Builder alert = new AlertDialog.Builder(RadioActivity.this);
                alert.setTitle("Change Preset 5")
                        .setView(input)
                        .setPositiveButton("Change Preset 5", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!input.getText().toString().equals("")) {
                                        /* user long pressed button */
                                    preset5Info = input.getText().toString();
                                } else {
                                        /* user didn't enter anything in the dialog */
                                    Toast.makeText(getApplicationContext(), "Nothing was entered!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    /* user cancelled the trip */
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        });


        preset6 = (Button) findViewById(R.id.preset6);
        preset6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollText.setText(preset6Info);
            }
        });

        preset6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final EditText input = new EditText(getApplicationContext());
                input.setTextColor(Color.BLACK);
                input.setFilters(new InputFilter[] {
                        new InputFilter.LengthFilter(5), DigitsKeyListener.getInstance(false, true) // false sign, true boolean
                });
                input.setKeyListener(DigitsKeyListener.getInstance());

                AlertDialog.Builder alert = new AlertDialog.Builder(RadioActivity.this);
                alert.setTitle("Change Preset 6")
                        .setView(input)
                        .setPositiveButton("Change Preset 6", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!input.getText().toString().equals("")) {
                                        /* user long pressed button */
                                    preset6Info = input.getText().toString();
                                } else {
                                        /* user didn't enter anything in the dialog */
                                    Toast.makeText(getApplicationContext(), "Nothing was entered!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    /* user cancelled the trip */
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        });

    }
}
