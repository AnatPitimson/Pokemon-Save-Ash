package com.example.gamega;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Moving_Pokador extends View {

    private Bitmap ball;
    int x_dir,y_dir;
    int ball_x,ball_y;

    public Moving_Pokador(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ball= BitmapFactory.decodeResource(getResources(),R.drawable.ball_);
        x_dir=5;
        y_dir=5;
        ball_x=50;
        ball_y=50;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);

        if(ball_x>=canvas.getWidth()-100)
        {
            x_dir=-5;
        }
        if(ball_x<=0)
        {
            x_dir=5;
        }
        if(ball_y>=canvas.getHeight()-100)
        {
            y_dir=-5;
        }
        if(ball_y<=0)
        {
            y_dir=5;
        }


        ball_x+=x_dir;
        ball_y+=y_dir;
        canvas.drawBitmap(ball,ball_x,ball_y,paint);
        invalidate();

    }
}



