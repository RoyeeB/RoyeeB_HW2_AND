package com.example.myfirstproj;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MenuOfGame extends AppCompatActivity {
    private MaterialButton mygame_BTN_start;
    private MaterialButton mygame_BTN_hightscore;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_open_page);
        findView();
        clickedPageMenu();

    }


    private void clickedPageMenu(){
        mygame_BTN_start.setOnClickListener(v -> startActivity( new Intent(MenuOfGame.this , SenOrBtn.class)));
        mygame_BTN_hightscore.setOnClickListener((v -> startActivity(new Intent(MenuOfGame.this , ActivityTop10.class))));
    }




    private void findView() {
        mygame_BTN_start = findViewById(R.id.mygame_BTN_start);
        mygame_BTN_hightscore = findViewById(R.id.mygame_BTN_hightscore);

    }
}
