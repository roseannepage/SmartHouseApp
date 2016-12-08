package com.example.roe.smarthouseapp;

/**
 * Created by Narges on 2016-11-24.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class SingleListItem extends Activity {
    String product;
    Button continueButton;
    protected static final String ACTIVITY_NAME = "SingleListItem";
    HouseActivity house = new HouseActivity();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_list_item_view);

        TextView txtProduct = (TextView) findViewById(R.id.itemLabel);

        Intent intent = getIntent();
        // getting attached intent data
        product = intent.getStringExtra("Item");
        // displaying selected item name
        txtProduct.setText(product);

        continueButton = (Button) findViewById(R.id.continueButton);
        continueButton.setText(product);

       /* if (product == "Garage") {
            Intent i = new Intent(SingleListItem.this, WeatherForecast.class);
            startActivity(i);
        }  */

     /*   continueButton.setOnClickListener((View.OnClickListener) this);*/
       /* continueButton.setOnClickListener(new View.OnClickListener() {
                                              public void onClick(View view) {
                                                  Intent intent = new Intent(SingleListItem.this, WeatherForecast.class);
                                                  startActivity(intent);
                                              }
                                          }
        );*/

    }
}