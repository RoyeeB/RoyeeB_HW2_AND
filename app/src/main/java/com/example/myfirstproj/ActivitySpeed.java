package com.example.myfirstproj;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ActivitySpeed extends AppCompatActivity {

    private MaterialButton mygame_BTN_fast;
    private MaterialButton mygame_BTN_slow;
    private boolean sensorMode = false;
    public static final String SENSOR_MODE = "SENSOR_MODE";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose_speed);
        sensorMode = getIntent().getExtras().getBoolean(SENSOR_MODE);

        findView();
        clickedPageSpeed(sensorMode);

    }
    public void next_fast(boolean sensorMode) {
        Intent gameIntent = new Intent(this, MainActivity.class);

        Bundle bundle = new Bundle();
        bundle.putBoolean(MainActivity.SENSOR_MODE, sensorMode);
        bundle.putBoolean(MainActivity.SPEED_MODE, true);
        gameIntent.putExtras(bundle);
        startActivity(gameIntent);
    }
    public void next_slow(boolean sensorMode) {
        Intent gameIntent = new Intent(this, MainActivity.class);

        Bundle bundle = new Bundle();
        bundle.putBoolean(MainActivity.SENSOR_MODE, sensorMode);
        bundle.putBoolean(MainActivity.SPEED_MODE, false);

        gameIntent.putExtras(bundle);
        startActivity(gameIntent);
    }


    private void clickedPageSpeed(boolean sensorMode){
        mygame_BTN_fast.setOnClickListener(v -> next_fast(sensorMode));
        mygame_BTN_slow.setOnClickListener(v ->  next_slow(sensorMode));
    }




    private void findView() {
        mygame_BTN_fast = findViewById(R.id.mygame_BTN_fast);
        mygame_BTN_slow = findViewById(R.id.mygame_BTN_slow);

    }
}
