package com.example.myfirstproj;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class SenOrBtn extends AppCompatActivity {

    private MaterialButton mygame_BTN_buttons;
    private MaterialButton mygame_BTN_sensor;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose_sensor);
        findView();
        clickedPageSesBtn();
    }


    private void clickedPageSesBtn(){
        mygame_BTN_buttons.setOnClickListener(v -> chooseSpeedPage(false));
        mygame_BTN_sensor.setOnClickListener(v -> chooseSpeedPage(true));
    }

    public void chooseSpeedPage(boolean sensorMode) {
        Intent gameIntent = new Intent(this, ActivitySpeed.class);

        Bundle bundle = new Bundle();
        bundle.putBoolean(MainActivity.SENSOR_MODE, sensorMode);

        gameIntent.putExtras(bundle);
        startActivity(gameIntent);
    }


    private void findView() {
        mygame_BTN_buttons = findViewById(R.id.mygame_BTN_buttons);
        mygame_BTN_sensor = findViewById(R.id.mygame_BTN_sensor);

    }
}
