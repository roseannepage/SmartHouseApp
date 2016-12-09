package com.example.roe.smarthouseapp;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.max;

/**
 * fragment for my smart window in the living room
 * @author Roseanne Page
 */
public class lr_blinds_activity extends Fragment {

    ProgressBar progBar;
    TextView currentT;
    ImageView imgV;
    SeekBar slider;
    protected static final String ACTIVITY_NAME = "Lr _blinds_Activity";

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private String mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public static void lr_blinds_activity() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_lr_blinds_activity, container, false);

        currentT = (TextView)rootView.findViewById(R.id.currentTemp);
        imgV = (ImageView)rootView.findViewById(R.id.weatherImg) ;
        progBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        progBar.setMax(100);
        progBar.setVisibility(View.VISIBLE);

        slider = (SeekBar) rootView.findViewById(R.id.seekBar1);


        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int levels = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                levels = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast toast;
                String text;
                int duration;

                text = "Blinds were changed";
                duration = Toast.LENGTH_SHORT;
                toast = Toast.makeText(getActivity(), text, duration);
                toast.show();


                Log.i(ACTIVITY_NAME, "User changed blinds");
            }

        });

        new ForecastQuery().execute();
        return rootView;
    }

    /**
     * asynctask for showing the current weather in ottawa.
     */
    public class ForecastQuery  extends AsyncTask<String, Integer, String>{

        String current, min, max;
        Bitmap weatherImg;
        String bitmapTemp;
        protected String doInBackground(String ... args)
        {



            try{

                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000); //in milliseconds
                urlConnection.setConnectTimeout(15000); //in millisenconds
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);


                urlConnection.connect();
                InputStream istream = urlConnection.getInputStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(istream, null);
                boolean finished = false;
                int type = xpp.getEventType();

                while(type != XmlPullParser.END_DOCUMENT) {

                    switch (type) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            finished = true;
                            break;
                        case XmlPullParser.START_TAG:

                            String name = xpp.getName();
                            if (name.equals("temperature")) {
                                publishProgress(25);
                                current = xpp.getAttributeValue(null,"value");


                                publishProgress(50);



                            }

                            if (name.equals("weather")) {
                                publishProgress(75);
                                bitmapTemp = xpp.getAttributeValue(null,"icon");
                                publishProgress(100);


                            }

                            break;
                        case XmlPullParser.END_TAG:
                            break;
                        case XmlPullParser.TEXT:
                            break;
                    }
                    type = xpp.next(); //advances to next xml event
                }
                urlConnection.disconnect();
                if(fileExist(bitmapTemp + ".png")){
                    File file = getActivity().getFileStreamPath(bitmapTemp + ".png");
                    FileInputStream fis = new FileInputStream(file);
                    weatherImg = BitmapFactory.decodeStream(fis);
                }else{
                    URL imgURL = new URL("http://openweathermap.org/img/w/" + bitmapTemp + ".png");
                    urlConnection = (HttpURLConnection) imgURL.openConnection();
                    urlConnection.connect();
                    istream = urlConnection.getInputStream();
                    weatherImg = BitmapFactory.decodeStream(istream);

                    FileOutputStream fos = getActivity().openFileOutput(bitmapTemp + ".png", Context.MODE_PRIVATE);
                    weatherImg.compress(Bitmap.CompressFormat.PNG, 80, fos);
                    fos.flush();
                    fos.close();
                    urlConnection.disconnect();
                }
            }
            catch(Exception e)
            {
                Log.e("XML PARSING", e.getMessage());
            }

            return null;
        }





        @Override
        protected void onProgressUpdate(Integer ...value)
        {
            progBar.setVisibility(View.VISIBLE);
            progBar.setProgress(value[0]);

        }


        @Override
        protected void onPostExecute(String srgs){
            progBar.setVisibility(View.INVISIBLE);
            currentT.setText("Current Temp: " + current + "ºC");

            imgV.setImageBitmap(weatherImg);
        }

        public Boolean fileExist(String name){
            File file = getActivity().getFileStreamPath(name);
            return file.exists();
        }
    }

}

