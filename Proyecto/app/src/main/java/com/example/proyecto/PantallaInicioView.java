package com.example.proyecto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.*;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

public class PantallaInicioView extends View {
    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().
                getMetrics(metrics);
        return (int)(dp*metrics.density);
    }

    int anchoPantalla,altoPantalla;
    Paint paint;
    Rect juego,opciones,salon,creditos,tutorial;
    Bitmap imagen= getBitmapFromAssets("gran-dragon-mitologia.jpg");
    public PantallaInicioView(Context context) {
        super(context);
        paint= new Paint();
        paint.setTextSize(110);
        paint.setAntiAlias(true);
        juego= new Rect(getPixels(150),getPixels(0),getPixels(500),getPixels(50));
        opciones= new Rect(getPixels(150),getPixels(50),getPixels(500),getPixels(100));
        salon= new Rect(getPixels(150),getPixels(100),getPixels(500),getPixels(150));
        creditos= new Rect(getPixels(150),getPixels(150),getPixels(500),getPixels(200));
        tutorial= new Rect(getPixels(150),getPixels(200),getPixels(500),getPixels(250));


    }
    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is=getContext().getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(imagen,0,0,null);

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(juego,paint);

        paint.setColor(Color.BLUE);
        canvas.drawRect(opciones,paint);

        paint.setColor(Color.RED);
        canvas.drawRect(salon,paint);

        paint.setColor(Color.BLACK);
        canvas.drawRect(creditos,paint);

        paint.setColor(Color.MAGENTA);
        canvas.drawRect(tutorial,paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        imagen=Bitmap.createScaledBitmap(imagen,w,h,false);
        this.anchoPantalla=w;
        this.altoPantalla=h;
        super.onSizeChanged(w, h, oldw, oldh);
    }


}
