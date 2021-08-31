package com.example.gamega;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity
{

    MediaPlayer backgroundSong;

    Boolean isPlaying;
    SharedPreferences music;

    private ImageButton muteBtn,unMuteBtn;
    int sum;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);



        Button Score_Btn =findViewById(R.id.ScoreBtn);
        Score_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Menu.this, ScoreBoard.class);
                backgroundSong.stop();
                startActivity(intent);
            }
        });

        Button sGame=findViewById(R.id.StartBtn);
        sGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Menu.this, LevelsPath.class);
                backgroundSong.stop();
                startActivity(intent);
            }
        });


        Button ourStory=findViewById(R.id.ourStoryBtn);
        ourStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Menu.this, OurStory.class);
                backgroundSong.stop();
                startActivity(intent);
            }
        });

        ImageButton store=findViewById(R.id.storeBtn);

    }



    @Override
    protected void onStart() {
        super.onStart();

        backgroundSong = MediaPlayer.create(this, R.raw.background_music);
        backgroundSong.setLooping(true);
        music= getSharedPreferences("BackgroundMusic",MODE_PRIVATE);
        isPlaying=music.getBoolean("BackgroundMusic",false);
        if(!isPlaying) {
            play();
        }

        sp = getSharedPreferences("Details",0);
        sum = sp.getInt("sum",0);


        TextView score_Lable=(TextView) findViewById(R.id.tv_coins);
        score_Lable.setText(""+ sum);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mute){
            if(!isPlaying) {
                item.setIcon(R.drawable.volume_off);
                isPlaying = true;
                SharedPreferences.Editor editor=music.edit();
                editor.putBoolean("BackgroundMusic",isPlaying);
                editor.commit();
                backgroundSong.pause();
            }
            else
            {
                item.setIcon(R.drawable.volume_up);
                isPlaying = false;
                SharedPreferences.Editor editor=music.edit();
                editor.putBoolean("BackgroundMusic",isPlaying);
                editor.commit();
                backgroundSong.start();
            }
        }
        if (item.getItemId() == R.id.storeBtn){
            Intent intent= new Intent(Menu.this, Store.class);
            backgroundSong.stop();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    //anat

    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundSong.release();
        play();
    }

    protected void play(){
        backgroundSong = MediaPlayer.create(this, R.raw.background_music);
        backgroundSong.setLooping(true);
        if(!isPlaying){ backgroundSong.start();}
    }

    protected void stop(){
        backgroundSong.pause();
        backgroundSong.release();
    }
}
