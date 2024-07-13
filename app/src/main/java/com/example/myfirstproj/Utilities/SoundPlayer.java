package com.example.myfirstproj.Utilities;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.myfirstproj.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SoundPlayer {

    private Context context;
    private Executor executor;
    private MediaPlayer mediaPlayer;



    public SoundPlayer(Context context){
        this.context=context;
        this.executor = Executors.newSingleThreadExecutor();


    }

    public void playSound(int res){
        if (mediaPlayer !=null){
            stopSound();
        }
        if(mediaPlayer == null){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer = MediaPlayer.create(context,res);
                    mediaPlayer.setLooping(false);
                    mediaPlayer.setVolume(1.0f,1.0f);
                    mediaPlayer.start();

                }
            });
        }

    }

    public void stopSound(){
        if (mediaPlayer != null){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            });
        }
    }

//    put it in mainActivity
//    private SoundPlayer soundPlayer;
//    private void makeSound(){
//        soundPlayer.playSound(R.raw.snd_crash);
//    }


}
