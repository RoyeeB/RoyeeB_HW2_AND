package com.example.myfirstproj;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
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
    private AppCompatImageView [] mygame_IMG_picachus;
    private AppCompatImageButton mygame_right_BTN_arrow;
    private AppCompatImageButton mygame_left_BTN_arrow;
    private GameManager gameManager;
    final int DELAY = 1300;
    final Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initAll();

        gameManager = new GameManager();
        initViewPicachu();
    }

private void initAll(){
    mygame_left_BTN_arrow = findViewById(R.id.mygame_left_BTN_arrow);
    mygame_right_BTN_arrow = findViewById(R.id.mygame_right_BTN_arrow);
    initHearts();
    initPicachus();
    initPokemonballs();
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
            findViewById(R.id.mygame_IMG_pokemonball3)},
            {findViewById(R.id.mygame_IMG_pokemonball4),
            findViewById(R.id.mygame_IMG_pokemonball5),
            findViewById(R.id.mygame_IMG_pokemonball6)},
            {findViewById(R.id.mygame_IMG_pokemonball7),
            findViewById(R.id.mygame_IMG_pokemonball8),
            findViewById(R.id.mygame_IMG_pokemonball9)},
            {findViewById(R.id.mygame_IMG_pokemonball10),
            findViewById(R.id.mygame_IMG_pokemonball11),
            findViewById(R.id.mygame_IMG_pokemonball12)},
            {findViewById(R.id.mygame_IMG_pokemonball13),
            findViewById(R.id.mygame_IMG_pokemonball14),
            findViewById(R.id.mygame_IMG_pokemonball15)}
    };

}

private void initPicachus(){
    mygame_IMG_picachus = new AppCompatImageView[]{
            findViewById(R.id.mygame_IMG_picahu1),
            findViewById(R.id.mygame_IMG_picahu2),
            findViewById(R.id.mygame_IMG_picahu3),
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
    });
}

protected void onPause() {
        super.onPause();
        stopTimer();
    }

@Override
protected void onStart() {
        super.onStart();
        startTimer();
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
    }

private void stopTimer() {
   handler.removeCallbacks(runnable);}

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
        if (gameManager.hit){
            refreshViewHearts();
            if (gameManager.finish()) {
             Toast.makeText(this,"Game Over", Toast.LENGTH_SHORT).show();
              gameManager.setFinish(false);}

            else{
            Toast.makeText(this,"Ouchh you caught",Toast.LENGTH_SHORT).show();}
            vibrate();
            gameManager.setHit(false);
        }

        refreshViewPokemonballs();
}

Runnable runnable = new Runnable() {
    @Override
    public void run() {
        handler.postDelayed(this, DELAY);
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




    }





