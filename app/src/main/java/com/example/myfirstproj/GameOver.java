package com.example.myfirstproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.Comparator;

public class GameOver extends AppCompatActivity {




    private TextView gameOver_LBL_score;
    private TextView gameOver_LBL_result;
    private EditText gameOver_LBL_name;
    private MaterialButton gameOver_BTN_saveRecord;
    private MaterialButton gameOver_BTN_back;

    private GPS gpsService;

    private MyDB DB;

    private String playerName;
    private int score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        score = getIntent().getExtras().getInt("score");
        findView();

        gameOver_LBL_result.setText("Score: " + score);
        initView();

    }

    private void initView() {
        gameOver_BTN_back.setOnClickListener(view -> finishGame());
        gameOver_BTN_saveRecord.setOnClickListener(view -> {
            double latitude = 0.0;
            double longitude = 0.0;

            playerName = gameOver_LBL_name.getText().toString();

            gpsService = new GPS(GameOver.this);
            if (gpsService.canGetLocation()) {
                latitude = gpsService.getLatitude();
                longitude = gpsService.getLongitude();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            } else {
                gpsService.showSettingsAlert();
            }
            // * End of Location Service

            gameOver_LBL_name.setVisibility(View.INVISIBLE);
            gameOver_BTN_saveRecord.setVisibility(View.INVISIBLE);

            saveRecord(playerName, score, longitude, latitude);
        });
    }

    private void findView() {

        gameOver_LBL_result = findViewById(R.id.gameOver_LBL_result);
        gameOver_LBL_name = findViewById(R.id.gameOver_LBL_name);
        gameOver_LBL_score = findViewById(R.id.gameOver_LBL_score);
        gameOver_BTN_saveRecord = findViewById(R.id.gameOver_BTN_saveRecord);
        gameOver_BTN_back = findViewById(R.id.gameOver_BTN_back);


    }

    private void saveRecord(String player_name, int score, double longitude, double latitude) {

        String js = MSPV3.getMe().getString("MY_DB", "");
        DB = new Gson().fromJson(js, MyDB.class);

        DB.getRecords().add(new Score()
                .setName(player_name)
                .setScore(score)
                .setLatitude(latitude)
                .setLongitude(longitude)
        );

       DB.getRecords().sort(new SortByScore());

        String json = new Gson().toJson(DB);
        MSPV3.getMe().putString("MY_DB", json);
    }

    public void finishGame(){
        Intent game = new Intent(this, MenuOfGame.class);
        startActivity(game);


    }
}
class SortByScore implements Comparator<Score> {

    @Override
    public int compare(Score rec1, Score rec2) {

        return rec2.getScore() - rec1.getScore();
    }


}

