package com.example.myfirstproj;

import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class GameManager {
    public static final int MAX_LIFES = 3;
    private int lifes = MAX_LIFES;
    public boolean[] lifesArrBool;
    boolean[][] pokemonballsOnMap;
    private int picachuIndex;
    boolean hit;
    boolean finish;
    public static final int COLUMNS = 3;
    public static final int ROWS = 5;


    public GameManager() {
        picachuIndex = 1;
        pokemonballsOnMap = new boolean[ROWS][COLUMNS];
        initHeartLive();
    }

    private void initHeartLive() {
        lifesArrBool = new boolean[3];
        lifesArrBool[0] = true;
        lifesArrBool[1] = true;
        lifesArrBool[2] = true;
    }

    public void lifeMinusOne(){
        lifes--;
    }

    public boolean[] getLifes(){
        return lifesArrBool;
    }

    public int getRows(){
        return ROWS;
    }

    public int getCols(){
        return COLUMNS;
    }

    public void setPicachuIndex(int picachuIndex){
        this.picachuIndex=picachuIndex;
    }

    public void setHit (boolean hit1){
        hit = hit1;
    }

    public boolean finish(){
        return finish;
    }

    public boolean active(int row , int col){
        return pokemonballsOnMap[row][col];
    }

    public void newPokemonball (){
        int i ;
        int col = getRand();
        for (i=0;i<getCols();i++){
            if (i==col) {
                pokemonballsOnMap[0][i] = true;
            }

        }

    }

    public int getRand(){
        int number;
        Random r = new Random();
        number = r.nextInt(COLUMNS);
        return number;
    }

    public void updateAll(){
        updateAllGame();
        newPokemonball();
    }

    private void updateAllGame() {
        int i, j;
        int rows = getRows();
        int cols = getCols();

        for (i = rows-1; i >= 0; i--) {
            for (j = 0; j < cols; j++) {
                if (active(i, j)) {
                    if (i == rows-1) {
                        pokemonballsOnMap[i][j] = false;
                        if (j == picachuIndex) {
                            hit = true;
                            lifeMinusOne();
                            lifesArrBool[lifes] = false;

                            if (lifes == 0) {
                                resetGame();
                                finish = true;
                            }
                        }
                    } else {
                        pokemonballsOnMap[i+1][j] = pokemonballsOnMap[i][j];
                        pokemonballsOnMap[i][j] = false;
                    }

                }
            }
        }
    }

    private void resetGame() {

        lifes = 3;
        finish = false;
        lifesArrBool = new boolean[MAX_LIFES];
        for (int i = 0; i < MAX_LIFES; i++) {
            lifesArrBool[i] = true;
        }
    }


    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}