package com.example.gamega;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Store extends AppCompatActivity {

    MediaPlayer backgroundSong;
    String coinsText;
    RelativeLayout shopP1,shopP2,shopP3,shopP4;
    Dialog shop1D,shop2D,shop3D,shop4D;

    RelativeLayout unlock1,unlock2,unlock3,unlock4;

    private Button back1,back2,back3,back4;
    private Button buy1,buy2,buy3,buy4;

    SharedPreferences sp;

    ImageButton back;
    Boolean isPlaying;
    SharedPreferences music;
    boolean shop1,shop2,shop3,shop4;

    int score,action,sum;

    private ImageView TreasureBox1,TreasureBox2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         coinsText= getString(R.string.not_enough_coins);

        music= getSharedPreferences("BackgroundMusic",MODE_PRIVATE);
        isPlaying=music.getBoolean("BackgroundMusic",false);

        backgroundSong = MediaPlayer.create(Store.this, R.raw.background_music);
        backgroundSong.setLooping(true);
        if(!isPlaying) {
            backgroundSong.start();
        }

        setContentView(R.layout.store);

        TreasureBox1=findViewById(R.id.close);
        TreasureBox2=findViewById(R.id.open);

        sp = getSharedPreferences("Details",0);
        sum = sp.getInt("sum",0);

        TextView score_Lable=(TextView) findViewById(R.id.tv_coins);
        score_Lable.setText(""+ sum);

        SharedPreferences sp2 = getSharedPreferences("Details",MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sp2.edit();

        SharedPreferences sharedPreferences=getSharedPreferences("FIRST_PLACE",MODE_PRIVATE);
        score=sharedPreferences.getInt("FIRST_SCORE",0);

        shopP1=(RelativeLayout) findViewById(R.id.shop1);
        shopP2=(RelativeLayout) findViewById(R.id.shop2);
        shopP3=(RelativeLayout) findViewById(R.id.shop3);
        shopP4=(RelativeLayout) findViewById(R.id.shop4);

        unlock1=(RelativeLayout) findViewById(R.id.unlock1);
        unlock2=(RelativeLayout) findViewById(R.id.unlock2);
        unlock3=(RelativeLayout) findViewById(R.id.unlock3);
        unlock4=(RelativeLayout) findViewById(R.id.unlock4);

        back=(ImageButton) findViewById(R.id.backBtn);

        final SharedPreferences settings= getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action=settings.getInt("ACTION",1);
        shop1=settings.getBoolean("SHOP1",false);
        shop2=settings.getBoolean("SHOP2",false);
        shop3=settings.getBoolean("SHOP3",false);
        shop4=settings.getBoolean("SHOP4",false);

        if(shop1){
            unlock1.setVisibility(View.GONE);
        }
        if(shop2){unlock2.setVisibility(View.GONE);}
        if(shop3){unlock3.setVisibility(View.GONE);}
        if(shop4) {
            unlock4.setVisibility(View.GONE);
            TreasureBox1.setVisibility(View.GONE);
            TreasureBox2.setVisibility(View.VISIBLE);
        }

        shop1D =new Dialog(this);
        shop2D =new Dialog(this);
        shop3D =new Dialog(this);
        shop4D =new Dialog(this);

        shopP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shop1D.setContentView(R.layout.hourglass_info);
                shop1D.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                shop1D.setCancelable(false);
                back1=shop1D.findViewById(R.id.back1);
                buy1=shop1D.findViewById(R.id.buy1);

                buy1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(shop1) { action = 1;}
                        else if(sum>=250) {
                            shop1 = true;
                            action = 1;
                            sum -= 250;
                            unlock1.setVisibility(View.GONE);

                            SharedPreferences preferencesTime=getSharedPreferences("time",Context.MODE_PRIVATE);
                            Boolean timer=preferencesTime.getBoolean("time",false);
                            SharedPreferences.Editor editorTimer=preferencesTime.edit();
                            editorTimer.putBoolean("time",true);
                            editorTimer.commit();

                            SharedPreferences.Editor editor = settings.edit();
                            editor.putInt("ACTION", action);
                            editor2.putInt("sum",sum);
                            editor2.commit();
                            editor.putBoolean("SHOP1", shop1);
                            editor.commit();
                        }
                        else
                        {
                            Toast.makeText(Store.this,coinsText,Toast.LENGTH_SHORT).show();
                        }
                        shop1D.dismiss();
                    }
                });

                back1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shop1D.dismiss();
                    }
                });
                shop1D.show();
            }
        });


        shopP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shop2D.setContentView(R.layout.money_bag_info);
                shop2D.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                shop2D.setCancelable(false);

                back2=shop2D.findViewById(R.id.back2);
                buy2=shop2D.findViewById(R.id.buy2);

                buy2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(shop2) { action = 2;}
                        else if(sum>=1000) {
                            shop2 = true;
                            action = 2;
                            sum -= 1000;
                            unlock2.setVisibility(View.GONE);

                            SharedPreferences preferencesMoney=getSharedPreferences("Money",Context.MODE_PRIVATE);
                            Boolean timer=preferencesMoney.getBoolean("Money",false);
                            SharedPreferences.Editor editorTimer=preferencesMoney.edit();
                            editorTimer.putBoolean("Money",true);
                            editorTimer.commit();

                            SharedPreferences.Editor editor = settings.edit();
                            editor.putInt("ACTION", action);
                            editor2.putInt("sum",sum);
                            editor2.commit();
                            editor.putBoolean("SHOP2", shop2);
                            editor.commit();
                        }
                        else
                        {
                            Toast.makeText(Store.this,coinsText,Toast.LENGTH_SHORT).show();//עבריתתתתת
                        }
                        shop2D.dismiss();
                    }
                });

                back2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shop2D.dismiss();
                    }
                });
                shop2D.show();
            }
        });

        shopP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shop3D.setContentView(R.layout.pokeball_info);
                shop3D.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                shop3D.setCancelable(false);

                back3=shop3D.findViewById(R.id.back3);
                buy3=shop3D.findViewById(R.id.buy3);

                buy3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(shop3) { action = 3; }
                        else if(sum>=2500) {
                            shop3 = true;
                            action = 3;
                            sum -=2500;
                            unlock3.setVisibility(View.GONE);

                            SharedPreferences preferencesball=getSharedPreferences("Life4",Context.MODE_PRIVATE);
                            Boolean life4=preferencesball.getBoolean("Life4",false);
                            SharedPreferences.Editor editorlife=preferencesball.edit();
                            editorlife.putBoolean("Life4",true);
                            editorlife.commit();

                            SharedPreferences.Editor editor = settings.edit();
                            editor.putInt("ACTION", action);
                            editor2.putInt("sum",sum);
                            editor2.commit();
                            editor.putBoolean("SHOP3", shop3);
                            editor.commit();
                        }
                        else
                        {
                            Toast.makeText(Store.this,coinsText,Toast.LENGTH_SHORT).show();
                        }
                        shop3D.dismiss();
                    }
                });

                back3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shop3D.dismiss();

                    }
                });
                shop3D.show();
            }
        });

        shopP4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shop4D.setContentView(R.layout.treasure_box);
                shop4D.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                shop4D.setCancelable(false);
                back4=shop4D.findViewById(R.id.back4);
                buy4=shop4D.findViewById(R.id.buy4);

                buy4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(shop4) { action = 4; }
                        else if(sum>=5000) {
                            shop4 = true;
                            action = 4;
                            sum -= 5000;

                            SharedPreferences preferencesTreasure=getSharedPreferences("Treasure",Context.MODE_PRIVATE);
                            Boolean life4=preferencesTreasure.getBoolean("Treasure",false);
                            SharedPreferences.Editor editorTreasure=preferencesTreasure.edit();
                            editorTreasure.putBoolean("Treasure",true);
                            editorTreasure.commit();

                            unlock4.setVisibility(View.GONE);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putInt("ACTION", action);
                            editor2.putInt("sum",sum);
                            editor2.commit();
                            editor.putBoolean("SHOP4", shop4);
                            editor.commit();
                        }
                        else
                        {
                            Toast.makeText(Store.this,coinsText,Toast.LENGTH_SHORT).show();
                        }
                        shop4D.dismiss();
                    }
                });

                back4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shop4D.dismiss();
                    }
                });
                shop4D.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSong.stop();
                startActivity(new Intent(Store.this, Menu.class));
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);

        if(isPlaying==false) {
            menu.findItem(R.id.mute).setIcon(R.drawable.volume_up);
        }
        else
        {
            menu.findItem(R.id.mute).setIcon(R.drawable.volume_off);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mute){
            if(isPlaying==false) {
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
        }
        return super.onOptionsItemSelected(item);
    }


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
