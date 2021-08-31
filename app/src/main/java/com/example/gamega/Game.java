package com.example.gamega;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class Game extends LevelsPath {

    private int counterMaxPower;
    private boolean touch = false;
    private boolean level1=false,level2 = false, level3 = false, level4 = false;
    private boolean level5=false,level6 = false, level7 = false, level8 = false;

    String scoreText;

    private boolean l1,l2 , l3 , l4,l5,l6 , l7 , l8;

    KonfettiView konfettiView;

    private ImageView power;
    private int powerX,powerY,powerSpeed;
    int powerCounter;
    ImageView power0,power1,power2,power3,power4,power5;

    private ImageView piki;
    private AnimationDrawable pikiAnimation;
    private int pikiY,pikiSpeed,pikiSize;

    private ImageView life1,life2,life3, life4;

    private boolean buy_life;
    private boolean buyTimer;
    private boolean buy_Money_Bag;

    private Sound sound;

    private int screenHeight,screenWidth,frameHeight;

    private TextView pauseText,levelUp;

    private ImageView coin;
    private AnimationDrawable coinAnimation;
    private int coinX,coinY,coinSpeed;

    private ImageView coin2;
    private AnimationDrawable coin2Animation;
    private int coin2X,coin2Y,coin2Speed;

    private ImageView ground_bad;
    private AnimationDrawable ground_bad_Animation;
    private int ground_bad_X,ground_bad_Y,ground_bad_Speed;

    private ImageView toto;
    private AnimationDrawable totoAnimation;
    private int totoX,totoY,totoSpeed;

    private ImageView dragon;
    private AnimationDrawable dragonAnimation;
    private int dragonX,dragonY,dragonSpeed;

    private Timer timer = new Timer();
    private Handler handler = new Handler();

    private Button tap_to_start;
    private ImageView tapPic;
    private AnimationDrawable tapAnimation;
    private ImageButton pause_Btn;
    private FrameLayout pause_Frame;
    private ImageButton homeBtn,muteBtn,unMuteBtn,playBtn;
    private boolean volume=true;

    private TextView timer_text_view;

    private TextView scoreTV;
    int score=0;
    int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        scoreText=getString(R.string.score_game_over);

        konfettiView = findViewById(R.id.viewKonfetti);

        power0=findViewById(R.id.power0);
        power1=findViewById(R.id.power1);
        power2=findViewById(R.id.power2);
        power3=findViewById(R.id.power3);
        power4=findViewById(R.id.power4);
        power5=findViewById(R.id.power5);

        powerCounter=getIntent().getIntExtra("Power",0);

        level1=getIntent().getBooleanExtra("L1", false);
        level2=getIntent().getBooleanExtra("L2", false);
        level3=getIntent().getBooleanExtra("L3", false);
        level4=getIntent().getBooleanExtra("L4", false);
        level5=getIntent().getBooleanExtra("L5", false);
        level6=getIntent().getBooleanExtra("L6", false);
        level7=getIntent().getBooleanExtra("L7", false);
        level8=getIntent().getBooleanExtra("L8", false);

        l1=getIntent().getBooleanExtra("L1", false);
        l2=getIntent().getBooleanExtra("L2", false);
        l3=getIntent().getBooleanExtra("L3", false);
        l4=getIntent().getBooleanExtra("L4", false);
        l5=getIntent().getBooleanExtra("L5", false);
        l6=getIntent().getBooleanExtra("L6", false);
        l7=getIntent().getBooleanExtra("L7", false);
        l8=getIntent().getBooleanExtra("L8", false);

        timer_text_view=findViewById(R.id.timerTextView);

        SharedPreferences buylife4=getSharedPreferences("Life4",MODE_PRIVATE);
        buy_life=buylife4.getBoolean("Life4",false);

        SharedPreferences buyTimer_=getSharedPreferences("time",MODE_PRIVATE);
        buyTimer=buyTimer_.getBoolean("time",false);

        SharedPreferences buy_MoneyBag=getSharedPreferences("Money",MODE_PRIVATE);
        buy_Money_Bag=buy_MoneyBag.getBoolean("Money",false);

        sound = new Sound(this);

        pauseText=findViewById(R.id.paused_pic);
        levelUp=findViewById(R.id.win_pic);

        tapPic=findViewById(R.id.finger_tap);
        tapAnimation = (AnimationDrawable) tapPic.getDrawable();
        tapAnimation.start();

        tap_to_start = findViewById(R.id.tap_to_start_btn);
        tap_to_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tap_to_start.setVisibility(View.GONE);
                pause_Btn.setVisibility(View.VISIBLE);
                tapAnimation.stop();
                tapPic.setVisibility(View.GONE);
                totoY=-500;
                dragonY =-500;
                ground_bad_Y=-500;

                if(buyTimer)
                {
                    buyTimer=false;
                    SharedPreferences.Editor editor=buyTimer_.edit();
                    editor.putBoolean("time",buyTimer);
                    editor.commit();
                    BuyTimerItem();
                }

                coin.setVisibility(View.VISIBLE);
                coin2.setVisibility(View.VISIBLE);
                power.setVisibility(View.VISIBLE);
                ground_bad.setVisibility(View.VISIBLE);
                pikiAnimation.start();
                ground_bad_Animation.start();
                coinAnimation.start();
                coin2Animation.start();

                FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
                frameHeight = frame.getHeight();

                pikiY = (int) piki.getY();
                pikiSize = piki.getHeight();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                moveObjects();
                            }
                        });
                    }
                }, 0, 20);

                if(buy_Money_Bag)
                {
                    ground_bad_Y=-500;
                    totoY=-500;
                    dragonY =-500;
                    ground_bad.setVisibility(View.INVISIBLE);
                    dragon.setVisibility(View.INVISIBLE);
                    toto.setVisibility(View.INVISIBLE);
                    totoSpeed=0;
                    dragonSpeed=0;
                    ground_bad_Speed=0;
                    totoAnimation.stop();
                    dragonAnimation.stop();
                    ground_bad_Animation.stop();
                    BuyTimerItem();
                    buy_Money_Bag=false;
                    SharedPreferences.Editor editor=buy_MoneyBag.edit();
                    editor.putBoolean("Money",buy_Money_Bag);
                    editor.commit();
                }
            }

        });

        power=findViewById(R.id.power);

        piki = findViewById(R.id.piki);
        pikiAnimation = (AnimationDrawable) piki.getDrawable();

        coin = findViewById(R.id.coin1);
        coinAnimation = (AnimationDrawable) coin.getDrawable();

        coin2 = findViewById(R.id.coin2);
        coin2Animation = (AnimationDrawable) coin2.getDrawable();

        ground_bad = findViewById(R.id.angry1);
        ground_bad_Animation = (AnimationDrawable) ground_bad.getDrawable();

        toto = findViewById(R.id.angry2);
        totoAnimation = (AnimationDrawable) toto.getDrawable();

        dragon =findViewById(R.id.angry3);
        dragonAnimation =(AnimationDrawable) dragon.getDrawable();

        life4 = findViewById(R.id.heart_four);
        life3 = findViewById(R.id.heart_one);
        life2 = findViewById(R.id.heart_two);
        life1 = findViewById(R.id.heart_three);

        if(buy_life)
        {
            life4.setVisibility(View.VISIBLE);

            SharedPreferences.Editor editor=buylife4.edit();
            buy_life=false;
            editor.putBoolean("Life4",buy_life);
            editor.commit();
        }

        scoreTV = (TextView) findViewById(R.id.score_tv);

        pause_Frame = findViewById(R.id.pauseFrame);

        pause_Btn = findViewById(R.id.pauseBtn);
        pause_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    timer.cancel();
                    timer = null;
                    pikiAnimation.stop();
                    coinAnimation.stop();
                    coin2Animation.stop();
                    totoAnimation.stop();
                    dragonAnimation.stop();
                    ground_bad_Animation.stop();
                    visibility(false);
                    pause_Frame.setVisibility(View.VISIBLE);
                    pauseText.setVisibility(View.VISIBLE);
            }
        });

        unMuteBtn=findViewById(R.id.mute);
        unMuteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(volume==false) {
                    volume = true;
                    muteBtn.setVisibility(View.VISIBLE);
                    unMuteBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        muteBtn=findViewById(R.id.umMute);
        muteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(volume==true) {
                    volume = false;
                    muteBtn.setVisibility(View.INVISIBLE);
                    unMuteBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        homeBtn = findViewById(R.id.home_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, Menu.class);
                startActivity(intent);
            }
        });

        playBtn = findViewById(R.id.play_btn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause_Frame.setVisibility(View.INVISIBLE);
                play();
            }
        });

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenHeight = size.y;
        screenWidth = size.x;

        pikiSpeed = Math.round(screenHeight / 80);
        changeSpeed(150);

    }


    public void moveObjects() {

        powerX -= powerSpeed;
        if (powerX < 0) {
            powerX = screenWidth + 2500;//יותר גבוהה פחות הופעות יותר קשה
            powerY = (int) Math.floor(Math.random() * (frameHeight - power.getHeight()));
        }
        power.setX(powerX);
        power.setY(powerY);


        coinX -= coinSpeed;
        if (coinX < 0) {
            coinX = screenWidth + 20;
            coinY = (int) Math.floor(Math.random() * (frameHeight - coin.getHeight()));
        }
        coin.setX(coinX);
        coin.setY(coinY);


        coin2X -= coin2Speed;
        if (coin2X < 0) {
            coin2X = screenWidth + 500;
            coin2Y = (int) Math.floor(Math.random() * (frameHeight - coin2.getHeight()));
        }
        coin2.setX(coin2X);
        coin2.setY(coin2Y);

        ground_bad_X -= ground_bad_Speed;
        if (ground_bad_X < 0) {
            ground_bad_X = screenWidth + 300;
            ground_bad_Y = (int) Math.floor(Math.random() * (frameHeight - ground_bad.getHeight()));
        }
        ground_bad.setX(ground_bad_X);
        ground_bad.setY(ground_bad_Y);


        if(level3||level4) {
            totoX -= totoSpeed;
            if (totoX < 0) {
                totoX = screenWidth + 100;
                totoY = (int) Math.floor(Math.random() * (frameHeight - toto.getHeight()));
            }
            toto.setX(totoX);
            toto.setY(totoY);
        }

        if(level5||level6) {
            dragonX -= dragonSpeed;
            if (dragonX < 0) {
                dragonX = screenWidth + 500;
                dragonY = (int) Math.floor(Math.random() * (frameHeight - dragon.getHeight()));
            }
            dragon.setX(dragonX);
            dragon.setY(dragonY);
        }

        //anat
        if(l1)
        {
            totoSpeed=0;
            dragonSpeed=0;
            level2=false;
            level3=false;
            level4=false;
            level5=false;
            level6=false;
            level7=false;
            level8=false;
            l1=false;
        }

        if(l2)
        {
            changeSpeed(120);
            dragonSpeed=0;
            totoAnimation.start();
            level2=true;
            level3=false;
            level4=false;
            level5=false;
            level6=false;
            level7=false;
            level8=false;
            l2=false;
        }

        if(l3)
        {
            changeSpeed(110);
            toto.setVisibility(View.VISIBLE);
            totoAnimation.start();
            dragonAnimation.start();
            level2=true;
            level3=true;
            level4=false;
            level5=false;
            level6=false;
            level7=false;
            level8=false;
            l3=false;
        }

        if(l4)
        {
            changeSpeed(100);
            toto.setVisibility(View.VISIBLE);
            level2=true;
            level3=true;
            level4=true;
            level5=false;
            level6=false;
            level7=false;
            level8=false;
            l4=false;
        }
        if(l5)
        {
            changeSpeed(100);
            toto.setVisibility(View.VISIBLE);
            dragon.setVisibility(View.VISIBLE);
            level2=true;
            level3=true;
            level4=true;
            level5=true;
            level6=false;
            level7=false;
            level8=false;
            l5=false;
        }
        if(l6)
        {
            changeSpeed(90);
            toto.setVisibility(View.VISIBLE);
            dragon.setVisibility(View.VISIBLE);
            level2=true;
            level3=true;
            level4=true;
            level5=true;
            level6=true;
            level7=false;
            level8=false;
            l6=false;
        }
        if(l7)
        {
            changeSpeed(85);
            toto.setVisibility(View.VISIBLE);
            dragon.setVisibility(View.VISIBLE);
            level2=true;
            level3=true;
            level4=true;
            level5=true;
            level6=true;
            level7=true;
            level8=false;
            l7=false;
        }
        if(l8)
        {
            changeSpeed(80);
            toto.setVisibility(View.VISIBLE);
            dragon.setVisibility(View.VISIBLE);
            level2=true;
            level3=true;
            level4=true;
            level5=true;
            level6=true;
            level7=true;
            level8=true;
            l8=false;
        }

        if (powerCounter == 5&&level2==false) {
                level2=true;
                levelUp();
                power0.setVisibility(View.VISIBLE);
                changeSpeed(120);
        }

        if (powerCounter == 10&&level3==false) {
                level3=true;
                levelUp();
                power0.setVisibility(View.VISIBLE);
                changeSpeed(110);
        }

        if (powerCounter == 15&&level4==false) {
                level4=true;
                levelUp();
                power0.setVisibility(View.VISIBLE);
                changeSpeed(100);
        }
        if (powerCounter == 20&&level5==false) {
            level5=true;
            levelUp();
            power0.setVisibility(View.VISIBLE);
            changeSpeed(100);
        }
        if (powerCounter == 25&&level6==false) {
            level6=true;
            levelUp();
            power0.setVisibility(View.VISIBLE);
            changeSpeed(90);
        }
        if (powerCounter == 30&&level7==false) {
            level7=true;
            levelUp();
            power0.setVisibility(View.VISIBLE);
            changeSpeed(85);
        }
        if (powerCounter == 35&&level8==false) {
            level8=true;
            levelUp();
            power0.setVisibility(View.VISIBLE);
            changeSpeed(80);
        }

        if(powerCounter== 40)
        {
            timer.cancel();
            timer = null;
            SharedPreferences preferencesPower=getSharedPreferences("power",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferencesPower.edit();
            editor.putInt("power",powerCounter);
            editor.commit();
            sound.win_Sound_();
            Intent intent= new Intent(Game.this, Win.class);
            intent.putExtra("Score", score);
            startActivity(intent);
            finish();
        }

        if (touch == true) { pikiY -= pikiSpeed; }
        else { pikiY += pikiSpeed; }

        if (pikiY < 0) { pikiY = 0; }

        if (pikiY > frameHeight - pikiSize) { pikiY = frameHeight - pikiSize; }

        piki.setY(pikiY);
        scoreTV.setText(scoreText+""+score);//anat fix it!!!
        hit();
    }


    public void hit() {
        int coinCenterX = coinX + coin.getWidth() / 2;
        int coinCenterY = coinY + coin.getHeight() / 2;

        if (0 <= coinCenterX && coinCenterX <= pikiSize && pikiY <= coinCenterY && coinCenterY <= pikiY + pikiSize) {
            score += 10;
            coinX = -10;
            if(volume)
            sound.coinSound();
        }

        int coin2CenterX = coin2X + coin2.getWidth() / 2;
        int coin2CenterY = coin2Y + coin2.getHeight() / 2;

        if (0 <= coin2CenterX && coin2CenterX <= pikiSize && pikiY <= coin2CenterY && coin2CenterY <= pikiY + pikiSize) {
            score += 10;
            coin2X = -10;
            if(volume)
                sound.coinSound();
        }

        int powerCenterX = powerX + power.getWidth() / 2;
        int powerCenterY = powerY + power.getHeight() / 2;

        if (0 <= powerCenterX && powerCenterX <= pikiSize && pikiY <= powerCenterY && powerCenterY <= pikiY + pikiSize) {
            powerX=-10;
            powerCounter++;
            checkPower();
            if(volume)
                sound.lightningSound();
        }

        int ground_bad_centerX = ground_bad_X + ground_bad.getWidth() / 2;
        int ground_bad_centerY = ground_bad_Y + ground_bad.getHeight() / 2;

        if (0 <= ground_bad_centerX && ground_bad_centerX <= pikiSize && pikiY <= ground_bad_centerY && ground_bad_centerY <= pikiY + pikiSize) {
            ground_bad_X = -10;
            if(volume)
                sound.punchSound();
            checkLife();
        }

        int toto_centerX = totoX + toto.getWidth() / 2;
        int toto_centerY = totoY + toto.getHeight() / 2;

        if (0 <= toto_centerX && toto_centerX <= pikiSize && pikiY <= toto_centerY && toto_centerY <= pikiY + pikiSize) {
            totoX = -10;
            if(volume)
                sound.punchSound();
            checkLife();
        }

        int dragon_centerX = dragonX + dragon.getWidth() / 2;
        int dragon_centerY = dragonY + dragon.getHeight() / 2;

        if (0 <= dragon_centerX && dragon_centerX <= pikiSize && pikiY <= dragon_centerY && dragon_centerY <= pikiY + pikiSize) {
            dragonX = -10;
            if(volume)
                sound.punchSound();
            checkLife();
        }
    }

    public void checkLife() {
        if(life4.getVisibility()==View.VISIBLE){ life4.setVisibility(View.INVISIBLE); }
        else if (life1.getVisibility()==View.VISIBLE) { life1.setVisibility(View.INVISIBLE); }
        else if (life2.getVisibility()==View.VISIBLE) { life2.setVisibility(View.INVISIBLE); }
        else if (life3.getVisibility()==View.VISIBLE
                && life2.getVisibility()==View.INVISIBLE
                && life1.getVisibility()==View.INVISIBLE) {
            life3.setVisibility(View.INVISIBLE);
            if(volume)
                sound.gameOverSound();
            timer.cancel();
            timer = null;

            SharedPreferences preferencesPower=getSharedPreferences("power",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferencesPower.edit();

            if(powerCounter>=0&&powerCounter<5){powerCounter=0;}
            else if(powerCounter>=5&&powerCounter<10){powerCounter=5;}
            else if(powerCounter>=10&&powerCounter<15){powerCounter=10;}
            else if(powerCounter>=15&&powerCounter<20){powerCounter=15;}
            else if(powerCounter>=20&&powerCounter<25){powerCounter=20;}
            else if(powerCounter>=25&&powerCounter<30){powerCounter=25;}
            else if(powerCounter>=30&&powerCounter<35){powerCounter=30;}
            else if(powerCounter>=35&&powerCounter<40){powerCounter=35;}
            else if(powerCounter>=40&&powerCounter<45){powerCounter=40;}

            counterMaxPower=preferencesPower.getInt("power",0);
                    //getIntent().getIntExtra("Power",0);

            if(powerCounter>=counterMaxPower)
            {
                editor.putInt("power", powerCounter);
                editor.commit();
            }
            else
            {
                editor.putInt("power", counterMaxPower);
                editor.commit();
            }

            Intent intent = new Intent(getApplicationContext(), GameOver.class);
            intent.putExtra("Score", score);
            startActivity(intent);
            finish();
        }
    }


    public void changeSpeed(int speed) {
        coinSpeed = Math.round(screenWidth / speed);
        coin2Speed = Math.round(screenWidth / speed);
        powerSpeed= Math.round(screenWidth / speed);
        ground_bad_Speed = Math.round(screenWidth / speed);
        totoSpeed = Math.round(screenWidth / speed);
        dragonSpeed =Math.round(screenWidth/speed);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true;
            piki.setImageDrawable(getResources().getDrawable(R.drawable.piki1));
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            touch = false;
            piki.setImageDrawable(getResources().getDrawable(R.drawable.piki_animation));
            pikiAnimation = (AnimationDrawable) piki.getDrawable();
            pikiAnimation.start();
        }
        return true;
    }


    public void play() {

        pikiAnimation.start();
        coin2Animation.start();
        coinAnimation.start();
        totoAnimation.start();
        dragonAnimation.start();
        ground_bad_Animation.start();
        pauseText.setVisibility(View.INVISIBLE);
        levelUp.setVisibility(View.INVISIBLE);

        if(level3||level4) { toto.setVisibility(View.VISIBLE); }
        if(level5||level6) { dragon.setVisibility(View.VISIBLE); }
        visibility(true);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        moveObjects();
                    }
                });
            }
        }, 0, 20);
    }

    public void visibility(boolean v)
    {
        if(v) {
            piki.setVisibility(View.VISIBLE);
            coin.setVisibility(View.VISIBLE);
            coin2.setVisibility(View.VISIBLE);
            power.setVisibility(View.VISIBLE);
            pause_Btn.setVisibility(View.VISIBLE);
            ground_bad.setVisibility(View.VISIBLE);
        }
        else
        {
            piki.setVisibility(View.INVISIBLE);
            coin.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);
            power.setVisibility(View.INVISIBLE);
            pause_Btn.setVisibility(View.INVISIBLE);
            ground_bad.setVisibility(View.INVISIBLE);
            toto.setVisibility(View.INVISIBLE);
            dragon.setVisibility(View.INVISIBLE);
        }
    }

    public void levelUp() {
        timer.cancel();
        timer = null;
        pikiAnimation.stop();
        coinAnimation.stop();
        coin2Animation.stop();
        totoAnimation.stop();
        dragonAnimation.stop();
        ground_bad_Animation.stop();
        totoY = -500;
        dragonY = -500;
        ground_bad_Y = -500;
        coinY = -500;
        coin2Y = -500;
        powerY = -500;
        visibility(false);
        power0.setVisibility(View.INVISIBLE);
        power1.setVisibility(View.INVISIBLE);
        power2.setVisibility(View.INVISIBLE);
        power3.setVisibility(View.INVISIBLE);
        power4.setVisibility(View.INVISIBLE);
        power5.setVisibility(View.INVISIBLE);
        pause_Frame.setVisibility(View.VISIBLE);
        levelUp.setVisibility(View.VISIBLE);
        sound.winSound();
        konfettiView.build()
                //.addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .addColors(Color.RED,Color.YELLOW,Color.WHITE,Color.BLUE,Color.GREEN,Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(500L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 1000L);
    }


    public void BuyTimerItem()
    {
            timer_text_view.setVisibility(View.VISIBLE);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                changeSpeed(200);
                progress= Integer.parseInt(timer_text_view.getText().toString());
                progress--;
                if(progress==0)
                {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            timer_text_view.setVisibility(View.INVISIBLE);
                            changeSpeed(110);//change to the level speed
                            ground_bad.setVisibility(View.VISIBLE);

                            if(level3||level4) { toto.setVisibility(View.VISIBLE); }
                            if(level5||level6) { dragon.setVisibility(View.VISIBLE); }
                        }
                    });
                }
                else
                {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            timer_text_view.setText(progress+"");
                        }
                    });
                }
            }
        },1000,1000);
    }

    public void checkPower()
    {
        if(power1.getVisibility()==View.INVISIBLE){
            power1.setVisibility(View.VISIBLE); }
        else if (power2.getVisibility()==View.INVISIBLE)
        { power2.setVisibility(View.VISIBLE);}
        else if (power3.getVisibility()==View.INVISIBLE)
        { power3.setVisibility(View.VISIBLE);}
        else if (power4.getVisibility()==View.INVISIBLE) {
            power4.setVisibility(View.VISIBLE);}
        else if (power5.getVisibility()==View.INVISIBLE) {
            power5.setVisibility(View.VISIBLE);}
    }

}