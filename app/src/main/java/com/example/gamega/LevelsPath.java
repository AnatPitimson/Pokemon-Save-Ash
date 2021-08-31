package com.example.gamega;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LevelsPath extends Activity {

    int sum;
    int score;
    int power;
    private boolean treasure;

    ImageButton level2B,level3B,level4B,level5B,level6B,level7B,level8B;
    ImageButton level2G,level3G,level4G,level5G,level6G,level7G,level8G;

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);

        sp = getSharedPreferences("Details",0);
        sum = sp.getInt("sum",0);
        TextView score_Lable=(TextView) findViewById(R.id.scoreLabel);
        score_Lable.setText(""+ sum);

        SharedPreferences sharedPreferences=getSharedPreferences("FIRST_PLACE",MODE_PRIVATE);
        score=sharedPreferences.getInt("FIRST_SCORE",0);

        SharedPreferences sharedPreferencesPower=getSharedPreferences("power",MODE_PRIVATE);
        power=sharedPreferencesPower.getInt("power",0);

        SharedPreferences Treasure_=getSharedPreferences("Treasure",MODE_PRIVATE);
        treasure=Treasure_.getBoolean("Treasure",false);

        if(treasure)
        {
            power+=5;
        }

        level2B=findViewById(R.id.level2b);
        level3B=findViewById(R.id.level3b);
        level4B=findViewById(R.id.level4b);
        level5B=findViewById(R.id.level5b);
        level6B=findViewById(R.id.level6b);
        level7B=findViewById(R.id.level7b);
        level8B=findViewById(R.id.level8b);

        level2G=findViewById(R.id.level2g);
        level3G=findViewById(R.id.level3g);
        level4G=findViewById(R.id.level4g);
        level5G=findViewById(R.id.level5g);
        level6G=findViewById(R.id.level6g);
        level7G=findViewById(R.id.level7g);
        level8G=findViewById(R.id.level8g);

        ImageButton level_1 = findViewById(R.id.level1);
        level_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsPath.this, Game.class);
                intent.putExtra("Power", 0);
                intent.putExtra("L1", true);
                intent.putExtra("L2", false);
                intent.putExtra("L3", false);
                intent.putExtra("L4", false);
                intent.putExtra("L5", false);
                intent.putExtra("L6", false);
                intent.putExtra("L7", false);
                intent.putExtra("L8", false);
                startActivity(intent);
            }
        });

        if (power >= 5){
            level2B.setVisibility(View.INVISIBLE);
            level2G.setVisibility(View.VISIBLE);
            ImageButton level_2 = findViewById(R.id.level2g);
            level_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LevelsPath.this, Game.class);
                    intent.putExtra("Power", 5);
                    intent.putExtra("L1", true);
                    intent.putExtra("L2", true);
                    intent.putExtra("L3", false);
                    intent.putExtra("L4", false);
                    intent.putExtra("L5", false);
                    intent.putExtra("L6", false);
                    intent.putExtra("L7", false);
                    intent.putExtra("L8", false);
                    startActivity(intent);
                }
            });
        }

        if (power >= 10){
            level3B.setVisibility(View.INVISIBLE);
            level3G.setVisibility(View.VISIBLE);
            ImageButton level_3 = findViewById(R.id.level3g);
            level_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LevelsPath.this, Game.class);
                    intent.putExtra("Power", 10);
                    intent.putExtra("L1", true);
                    intent.putExtra("L2", true);
                    intent.putExtra("L3", true);
                    intent.putExtra("L4", false);
                    intent.putExtra("L5", false);
                    intent.putExtra("L6", false);
                    intent.putExtra("L7", false);
                    intent.putExtra("L8", false);

                    startActivity(intent);
                }
            });
        }


        if (power >= 15){
            level4B.setVisibility(View.INVISIBLE);
            level4G.setVisibility(View.VISIBLE);
            ImageButton level_4 = findViewById(R.id.level4g);
            level_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LevelsPath.this, Game.class);
                    intent.putExtra("Power", 15);
                    intent.putExtra("L1", true);
                    intent.putExtra("L2", true);
                    intent.putExtra("L3", true);
                    intent.putExtra("L4", true);
                    intent.putExtra("L5", false);
                    intent.putExtra("L6", false);
                    intent.putExtra("L7", false);
                    intent.putExtra("L8", false);
                    startActivity(intent);
                }
            });
        }

        if (power >= 20){
            level5B.setVisibility(View.INVISIBLE);
            level5G.setVisibility(View.VISIBLE);
            ImageButton level_5 = findViewById(R.id.level5g);
            level_5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LevelsPath.this, Game.class);
                    intent.putExtra("Power", 20);
                    intent.putExtra("L1", true);
                    intent.putExtra("L2", true);
                    intent.putExtra("L3", true);
                    intent.putExtra("L4", true);
                    intent.putExtra("L5", true);
                    intent.putExtra("L6", false);
                    intent.putExtra("L7", false);
                    intent.putExtra("L8", false);
                    startActivity(intent);
                }
            });
        }

        if (power >= 25){
            level6B.setVisibility(View.INVISIBLE);
            level6G.setVisibility(View.VISIBLE);
            ImageButton level_6 = findViewById(R.id.level6g);
            level_6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LevelsPath.this, Game.class);
                    intent.putExtra("Power", 25);
                    intent.putExtra("L1", true);
                    intent.putExtra("L2", true);
                    intent.putExtra("L3", true);
                    intent.putExtra("L4", true);
                    intent.putExtra("L5", true);
                    intent.putExtra("L6", true);
                    intent.putExtra("L7", false);
                    intent.putExtra("L8", false);
                    startActivity(intent);
                }
            });
        }

        if (power >= 30){
            level7B.setVisibility(View.INVISIBLE);
            level7G.setVisibility(View.VISIBLE);
            ImageButton level_7 = findViewById(R.id.level7g);
            level_7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LevelsPath.this, Game.class);
                    intent.putExtra("Power", 30);
                    intent.putExtra("L1", true);
                    intent.putExtra("L2", true);
                    intent.putExtra("L3", true);
                    intent.putExtra("L4", true);
                    intent.putExtra("L5", true);
                    intent.putExtra("L6", true);
                    intent.putExtra("L7", true);
                    intent.putExtra("L8", false);
                    startActivity(intent);
                }
            });
        }

        if (power >= 35){
            level8B.setVisibility(View.INVISIBLE);
            level8G.setVisibility(View.VISIBLE);
            ImageButton level_8 = findViewById(R.id.level8g);
            level_8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LevelsPath.this, Game.class);
                    intent.putExtra("Power", 35);
                    intent.putExtra("L1", true);
                    intent.putExtra("L2", true);
                    intent.putExtra("L3", true);
                    intent.putExtra("L4", true);
                    intent.putExtra("L5", true);
                    intent.putExtra("L6", true);
                    intent.putExtra("L7", true);
                    intent.putExtra("L8", true);
                    startActivity(intent);
                }
            });
        }



        ImageButton back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsPath.this, Menu.class);
                startActivity(intent);
            }
        });
    }
}