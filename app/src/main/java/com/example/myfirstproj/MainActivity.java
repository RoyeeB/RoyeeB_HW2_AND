package com.example.myfirstproj;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private AppCompatImageView [] mygame_IMG_hearts;
    private AppCompatImageView [][] mygame_IMG_pokemonballs;
    private AppCompatImageView [][] mygame_IMG_gifts;
    private AppCompatImageView [] mygame_IMG_picachus;
    private AppCompatImageButton mygame_right_BTN_arrow;
    private AppCompatImageButton mygame_left_BTN_arrow;
    private GameManager gameManager;
    private int DELAY = 900;
    final Handler handler = new Handler();
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean sensorMode = false;
    private boolean speedMode= false;
    public static final String SENSOR_MODE = "SENSOR_MODE";
    public static final String SPEED_MODE = "SPEED_MODE";
    int score = 0 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initAll();
        gameManager = new GameManager();

        sensorMode = getIntent().getExtras().getBoolean(SENSOR_MODE);
        speedMode = getIntent().getExtras().getBoolean(SPEED_MODE);

        if (sensorMode)
            moveBySensors();
        else{
            initViewPicachu();
        }
        if (speedMode)
            DELAY = 400;
        else{
           DELAY= 1000;
        }
        startTimer();

    }


private void initAll(){
    mygame_left_BTN_arrow = findViewById(R.id.mygame_left_BTN_arrow);
    mygame_right_BTN_arrow = findViewById(R.id.mygame_right_BTN_arrow);
    initHearts();
    initPicachus();
    initPokemonballs();
    initGifts();
}

private void initHearts(){
    mygame_IMG_hearts = new AppCompatImageView[]{
            findViewById(R.id.mygame_IMG_heart1),
            findViewById(R.id.mygame_IMG_heart2),
            findViewById(R.id.mygame_IMG_heart3),
    };
}

private void initPokemonballs(){
    mygame_IMG_pokemonballs = new AppCompatImageView[][]{
            {findViewById(R.id.mygame_IMG_pokemonball1),
            findViewById(R.id.mygame_IMG_pokemonball2),
            findViewById(R.id.mygame_IMG_pokemonball3),
            findViewById(R.id.mygame_IMG_pokemonball4),
            findViewById(R.id.mygame_IMG_pokemonball5)},

            {findViewById(R.id.mygame_IMG_pokemonball6),
             findViewById(R.id.mygame_IMG_pokemonball7),
             findViewById(R.id.mygame_IMG_pokemonball8),
             findViewById(R.id.mygame_IMG_pokemonball9),
             findViewById(R.id.mygame_IMG_pokemonball10)},

            {findViewById(R.id.mygame_IMG_pokemonball11),
            findViewById(R.id.mygame_IMG_pokemonball12),
            findViewById(R.id.mygame_IMG_pokemonball13),
            findViewById(R.id.mygame_IMG_pokemonball14),
            findViewById(R.id.mygame_IMG_pokemonball15)},

            {findViewById(R.id.mygame_IMG_pokemonball16),
            findViewById(R.id.mygame_IMG_pokemonball17),
            findViewById(R.id.mygame_IMG_pokemonball18),
            findViewById(R.id.mygame_IMG_pokemonball19),
            findViewById(R.id.mygame_IMG_pokemonball20)},

            {findViewById(R.id.mygame_IMG_pokemonball21),
            findViewById(R.id.mygame_IMG_pokemonball22),
            findViewById(R.id.mygame_IMG_pokemonball23),
            findViewById(R.id.mygame_IMG_pokemonball24),
            findViewById(R.id.mygame_IMG_pokemonball25)},

            {findViewById(R.id.mygame_IMG_pokemonball26),
            findViewById(R.id.mygame_IMG_pokemonball27),
            findViewById(R.id.mygame_IMG_pokemonball28),
            findViewById(R.id.mygame_IMG_pokemonball29),
            findViewById(R.id.mygame_IMG_pokemonball30)},

            {findViewById(R.id.mygame_IMG_pokemonball31),
            findViewById(R.id.mygame_IMG_pokemonball32),
            findViewById(R.id.mygame_IMG_pokemonball33),
            findViewById(R.id.mygame_IMG_pokemonball34),
            findViewById(R.id.mygame_IMG_pokemonball35)},

            {findViewById(R.id.mygame_IMG_pokemonball36),
            findViewById(R.id.mygame_IMG_pokemonball37),
            findViewById(R.id.mygame_IMG_pokemonball38),
            findViewById(R.id.mygame_IMG_pokemonball39),
            findViewById(R.id.mygame_IMG_pokemonball40)},

            {findViewById(R.id.mygame_IMG_pokemonball41),
            findViewById(R.id.mygame_IMG_pokemonball42),
            findViewById(R.id.mygame_IMG_pokemonball43),
            findViewById(R.id.mygame_IMG_pokemonball44),
            findViewById(R.id.mygame_IMG_pokemonball45)},
    };

}

    private void initGifts(){
        mygame_IMG_gifts = new AppCompatImageView[][]{
                {findViewById(R.id.mygame_IMG_gift1),
                        findViewById(R.id.mygame_IMG_gift2),
                        findViewById(R.id.mygame_IMG_gift3),
                        findViewById(R.id.mygame_IMG_gift4),
                        findViewById(R.id.mygame_IMG_gift5)},

                {findViewById(R.id.mygame_IMG_gift6),
                        findViewById(R.id.mygame_IMG_gift7),
                        findViewById(R.id.mygame_IMG_gift8),
                        findViewById(R.id.mygame_IMG_gift9),
                        findViewById(R.id.mygame_IMG_gift10)},

                {findViewById(R.id.mygame_IMG_gift11),
                        findViewById(R.id.mygame_IMG_gift12),
                        findViewById(R.id.mygame_IMG_gift13),
                        findViewById(R.id.mygame_IMG_gift14),
                        findViewById(R.id.mygame_IMG_gift15)},

                {findViewById(R.id.mygame_IMG_gift16),
                        findViewById(R.id.mygame_IMG_gift17),
                        findViewById(R.id.mygame_IMG_gift18),
                        findViewById(R.id.mygame_IMG_gift19),
                        findViewById(R.id.mygame_IMG_gift20)},

                {findViewById(R.id.mygame_IMG_gift21),
                        findViewById(R.id.mygame_IMG_gift22),
                        findViewById(R.id.mygame_IMG_gift23),
                        findViewById(R.id.mygame_IMG_gift24),
                        findViewById(R.id.mygame_IMG_gift25)},

                {findViewById(R.id.mygame_IMG_gift26),
                        findViewById(R.id.mygame_IMG_gift27),
                        findViewById(R.id.mygame_IMG_gift28),
                        findViewById(R.id.mygame_IMG_gift29),
                        findViewById(R.id.mygame_IMG_gift30)},

                {findViewById(R.id.mygame_IMG_gift31),
                        findViewById(R.id.mygame_IMG_gift32),
                        findViewById(R.id.mygame_IMG_gift33),
                        findViewById(R.id.mygame_IMG_gift34),
                        findViewById(R.id.mygame_IMG_gift35)},

                {findViewById(R.id.mygame_IMG_gift36),
                        findViewById(R.id.mygame_IMG_gift37),
                        findViewById(R.id.mygame_IMG_gift38),
                        findViewById(R.id.mygame_IMG_gift39),
                        findViewById(R.id.mygame_IMG_gift40)},

                {findViewById(R.id.mygame_IMG_gift41),
                        findViewById(R.id.mygame_IMG_gift42),
                        findViewById(R.id.mygame_IMG_gift43),
                        findViewById(R.id.mygame_IMG_gift44),
                        findViewById(R.id.mygame_IMG_gift45)},
        };

    }

private void initPicachus(){
    mygame_IMG_picachus = new AppCompatImageView[]{
            findViewById(R.id.mygame_IMG_picahu1),
            findViewById(R.id.mygame_IMG_picahu2),
            findViewById(R.id.mygame_IMG_picahu3),
            findViewById(R.id.mygame_IMG_picahu4),
            findViewById(R.id.mygame_IMG_picahu5),
    };
}

private void initViewPicachu() {
    mygame_left_BTN_arrow.setOnClickListener(v -> {

        if (mygame_IMG_picachus[1].isShown()) {
            mygame_IMG_picachus[0].setVisibility(View.VISIBLE);
            mygame_IMG_picachus[1].setVisibility(View.INVISIBLE);
            gameManager.setPicachuIndex(0);
        } else if (mygame_IMG_picachus[2].isShown()) {
            mygame_IMG_picachus[1].setVisibility(View.VISIBLE);
            mygame_IMG_picachus[2].setVisibility(View.INVISIBLE);
            gameManager.setPicachuIndex(1);
        }
        else if (mygame_IMG_picachus[3].isShown()) {
            mygame_IMG_picachus[2].setVisibility(View.VISIBLE);
            mygame_IMG_picachus[3].setVisibility(View.INVISIBLE);
            gameManager.setPicachuIndex(2);
        }
        else if (mygame_IMG_picachus[4].isShown()) {
            mygame_IMG_picachus[3].setVisibility(View.VISIBLE);
            mygame_IMG_picachus[4].setVisibility(View.INVISIBLE);
            gameManager.setPicachuIndex(3);
        }

    });

    mygame_right_BTN_arrow.setOnClickListener(v1 -> {
        if (mygame_IMG_picachus[1].isShown()) {
            mygame_IMG_picachus[2].setVisibility(View.VISIBLE);
            mygame_IMG_picachus[1].setVisibility(View.INVISIBLE);
            gameManager.setPicachuIndex(2);

        }
        else if (mygame_IMG_picachus[0].isShown()) {
            mygame_IMG_picachus[1].setVisibility(View.VISIBLE);
            mygame_IMG_picachus[0].setVisibility(View.INVISIBLE);
            gameManager.setPicachuIndex(1);
        }
        else if (mygame_IMG_picachus[2].isShown()) {
            mygame_IMG_picachus[3].setVisibility(View.VISIBLE);
            mygame_IMG_picachus[2].setVisibility(View.INVISIBLE);
            gameManager.setPicachuIndex(3);
        }
        else if (mygame_IMG_picachus[3].isShown()) {
            mygame_IMG_picachus[4].setVisibility(View.VISIBLE);
            mygame_IMG_picachus[3].setVisibility(View.INVISIBLE);
            gameManager.setPicachuIndex(4);
        }
    });
}

protected void onPause() {
        super.onPause();
        stopTimer();
        if (sensorMode)
            stopSensor();
    }

@Override
protected void onStart() {
        super.onStart();
        startTimer();
        if (sensorMode)
            startSensor();
    }

private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
         else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

private void startTimer() {
        handler.postDelayed(runnable, DELAY);
    if (sensorMode)
        startSensor();
    }

private void stopTimer() {
   handler.removeCallbacks(runnable);
    if (sensorMode)
        stopSensor();
    }

private void refreshViewHearts() {
    boolean[] lifes = gameManager.getLifes();
    int lenght = gameManager.getLifes().length;
    for (int i = 0; i < lenght; i++) {
        if (lifes[i])
            mygame_IMG_hearts[i].setVisibility(View.VISIBLE);
        else
            mygame_IMG_hearts[i].setVisibility(View.INVISIBLE);
    }

}


 private void refreshsAllView(){
        gameManager.updateAll();
        if (gameManager.hit) {
            refreshViewHearts();
            if (gameManager.finish()) {
                vibrate();
                Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
                Intent gameOverIntent = new Intent(MainActivity.this, GameOver.class);
                Bundle bundle = new Bundle();
                bundle.putInt("score", score);
                gameOverIntent.putExtras(bundle);
                startActivity(gameOverIntent);
                this.finish();
                stopTimer();
                finish();
                gameManager.setFinish(false);
            } else if (gameManager.hit) {
                Toast.makeText(this, "Ouchh you caught", Toast.LENGTH_SHORT).show();
                vibrate();
                gameManager.setHit(false);
            } else if (gameManager.isGift()) {
                Toast.makeText(this,"You get gift!", Toast.LENGTH_SHORT).show();
                score = score + 100;
                gameManager.setHitGift(false);
                Toast.makeText(this, "+100 points", Toast.LENGTH_SHORT).show();

            }
        }

        refreshViewPokemonballs();
        refreshViewGift();
}


Runnable runnable = new Runnable() {
    @Override
    public void run() {
        handler.postDelayed(this, DELAY);
        score =score + 10;
        refreshsAllView();
    }
};

    private void refreshViewPokemonballs() {
        int i,j;
        for (i=0;i<gameManager.getRows();i++){
            for(j=0;j< gameManager.getCols();j++){
                if (gameManager.active(i,j) == true )
                    mygame_IMG_pokemonballs[i][j].setVisibility(View.VISIBLE);
                else
                    mygame_IMG_pokemonballs[i][j].setVisibility(View.INVISIBLE);

            }
        }
    }
    private void refreshViewGift() {
        for (int i = 0; i < gameManager.getRows(); i++) {
            for (int j = 0; j < gameManager.getCols(); j++) {
                if (gameManager.giftActive(i,j) == true) {
                    mygame_IMG_gifts[i][j].setVisibility(View.VISIBLE);
                } else {
                    mygame_IMG_gifts[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
    }


    private void moveBySensors() {
        mygame_right_BTN_arrow.setVisibility(View.INVISIBLE);
        mygame_left_BTN_arrow.setVisibility(View.INVISIBLE);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }
    private final SensorEventListener sensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            movePicachuBySensors(sensorEvent.values[0]);
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
    public void startSensor() {
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
    public void stopSensor() {
        sensorManager.unregisterListener(sensorEventListener);
    }

    private void movePicachuBySensors(float x) {
        mygame_IMG_picachus[gameManager.getPicachuIndex()].setVisibility(View.INVISIBLE);
        if (x < -4) {
            gameManager.setPicachuIndex(4);
        }  else if (-3.5 < x && x < -1.5) {
            gameManager.setPicachuIndex(3);
        }else if (-1 < x && x< 1) {
            gameManager.setPicachuIndex(2);
        } else if (1.5 < x && x < 3.5) {
            gameManager.setPicachuIndex(1);
        } else if (4 < x) {
            gameManager.setPicachuIndex(0);
        }
        mygame_IMG_picachus[gameManager.getPicachuIndex()].setVisibility(View.VISIBLE);
    }

    }





