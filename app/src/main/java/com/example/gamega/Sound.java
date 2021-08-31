package com.example.gamega;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class Sound {
    private AudioAttributes audio;
    final int MAX=2;

    private static  int coinSound_;
    private static  int gameOverSound_;
    private static  int winSound_;
    private static  int punchSound_;
    private static  SoundPool sound;
    private static  int lightningSound_;
    private static  int win_Sound_;


    public  Sound (Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            audio = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            sound = new SoundPool.Builder()
                    .setAudioAttributes(audio)
                    .setMaxStreams(MAX)
                    .build();
        }
        else {
            sound = new SoundPool(MAX, AudioManager.STREAM_MUSIC, 0);
        }

        coinSound_ = sound.load(context, R.raw.coin_sound, 1);
        gameOverSound_ = sound.load(context, R.raw.game_over_sound, 1);
        winSound_ = sound.load(context, R.raw.level_win_sound, 1);
        punchSound_ = sound.load(context, R.raw.punch_sound, 1);
        lightningSound_ = sound.load(context, R.raw.lightning_sound, 1);
        win_Sound_ = sound.load(context, R.raw.win_sound_game, 1);


    }

    public void coinSound() { sound.play(coinSound_, 1.0f, 1.0f, 1, 0, 1.0f); }

    public void gameOverSound() {
        sound.play(gameOverSound_, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void winSound() {
        sound.play(winSound_, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void punchSound() {
        sound.play(punchSound_, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void lightningSound() {
        sound.play(lightningSound_, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void win_Sound_() { sound.play(win_Sound_, 1.0f, 1.0f, 1, 0, 1.0f); }

    //public void stop() {sound.pause(backgroundMusic);}


}
