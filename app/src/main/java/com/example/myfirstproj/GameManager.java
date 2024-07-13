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
    public boolean[][]giftsOnMap;
    private int picachuIndex;
    boolean hit;
    boolean finish;
    boolean gift;
    public static final int COLUMNS = 5;
    public static final int ROWS = 9;


    public GameManager() {
        picachuIndex = 2;
        pokemonballsOnMap = new boolean[ROWS][COLUMNS];
        giftsOnMap = new boolean[ROWS][COLUMNS];
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
    public int getPicachuIndex(){
        return picachuIndex;
    }



        public void setHit (boolean hit1){
        hit = hit1;
    }

    public void setHitGift(boolean hit1) {gift = hit;}

    public boolean isGift(){return gift;}

    public boolean finish(){
        return finish;
    }

    public boolean active(int row , int col){
        return pokemonballsOnMap[row][col];
    }

    public boolean giftActive (int row, int col){return giftsOnMap[row][col];}

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
       updateGifts();
       updateAllNew();
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

    public void updateGifts() {
        for (int i = getRows()-1; i >=0;i--)
             for (int j = 0; j <getCols();j++){

        {
                if( giftActive(i,j) && i == getRows()-1) {
                    giftsOnMap[i][j] = false;

                    if (j == picachuIndex) {
                        gift=true;

                    }
                }
                else if(i != getRows()-1){
                    giftsOnMap[i+1][j]=giftsOnMap[i][j];

                }
            }
        }
    }

    public void updateAllNew(){
        int col =getRand();
        for(int i = 0;i<getCols();i++)
            pokemonballsOnMap[0][i] = col == i;
        int colForGift = getRand();
        while(col == colForGift){
            colForGift=getRand();
        }
        for(int i = 0;i<getCols();i++)
            giftsOnMap[0][i] = colForGift == i;

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