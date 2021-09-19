package com.barmej.android_degree_studentproject_interactiveapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final Random mRandom = new Random();
    private final static String TAG = MainActivity.class.getSimpleName(); // Tag log
    private final static String BUNDLE_KEY = "CURRENT_INDEX";
    private int currentIndex;


    private final int[] images = {
            R.drawable.beach,
            R.drawable.bike,
            R.drawable.football,
            R.drawable.museum,
            R.drawable.park,
            R.drawable.restaurant,
            R.drawable.running,
            R.drawable.shop,
            R.drawable.swimming,
            R.drawable.walking
    };
    private final int[] activitiesNames = {
            R.string.activity_name_1,
            R.string.activity_name_2,
            R.string.activity_name_3,
            R.string.activity_name_4,
            R.string.activity_name_5,
            R.string.activity_name_6,
            R.string.activity_name_7,
            R.string.activity_name_8,
            R.string.activity_name_9,
            R.string.activity_name_10
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateRandomActivity(null);
    }
    public void setRandomActivity(){
        // Set image for image view
        ((ImageView)findViewById(R.id.activity_image)).setImageDrawable(
                ContextCompat.getDrawable(this, images[currentIndex])
        );

        // Set text for text view
        ((TextView)findViewById(R.id.activity_name)).setText(activitiesNames[currentIndex]);
    }
    public void generateRandomActivity(View view){
        currentIndex = mRandom.nextInt(images.length); // Get random number between 0 and array length - 1

        // Set
        setRandomActivity();
    }

    // Next activity
    public void nextActivity(View view){
        if(currentIndex < images.length - 1)
            currentIndex++;
        else 
            currentIndex = 0;
        setRandomActivity();
//        Log.d(TAG, "Index = " + currentIndex);
    }

    // Previous activity
    public void previousActivity(View view){
        if(currentIndex > 0)
            currentIndex--;
        else
            currentIndex = images.length - 1;
        setRandomActivity();
//        Log.d(TAG,"Index = " + currentIndex);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY, currentIndex);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentIndex = savedInstanceState.getInt(BUNDLE_KEY);
        // Set restored data
        setRandomActivity();
    }
}