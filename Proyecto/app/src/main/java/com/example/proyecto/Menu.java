package com.example.proyecto;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import static android.provider.Settings.System.getString;


public class Menu extends Escena {
    Rect juego,opciones,salon,creditos,tutorial;
    Bitmap camara;
    public Menu(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla,Bitmap camara) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);
        this.camara=Bitmap.createScaledBitmap(camara,anchoPantalla/12,altoPantalla/8,false);
        juego= new Rect(anchoPantalla/6*1,0,anchoPantalla/6*4,altoPantalla/6*1);
        opciones= new Rect(anchoPantalla/6*1,altoPantalla/6*1,anchoPantalla/6*4,altoPantalla/6*2-getPixels(10));
        salon= new Rect(anchoPantalla/6*1,altoPantalla/6*2,anchoPantalla/6*4,altoPantalla/6*3-getPixels(10));
        creditos= new Rect(anchoPantalla/6*1,altoPantalla/6*3,anchoPantalla/6*4,altoPantalla/6*4-getPixels(10));
        tutorial= new Rect(anchoPantalla/6*1,altoPantalla/6*4,anchoPantalla/6*4,altoPantalla/6*5-getPixels(10));


    }

    public int onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                if(juego.contains((int) event.getX(),(int) event.getY())){
                    return 2;
            }if(opciones.contains((int) event.getX(),(int) event.getY())){
                    return  3;
            }if(salon.contains((int) event.getX(),(int) event.getY())){
                    return  4;
            }if(creditos.contains((int) event.getX(),(int) event.getY())){
                    return 5;
            }if(tutorial.contains((int) event.getX(),(int) event.getY())){
                   return 6;
            }
        }
        return numeroEscena;

    }
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawRect(juego,paint);
        c.drawRect(opciones,paint);
        c.drawRect(salon,paint);
        c.drawRect(creditos,paint);
        c.drawRect(tutorial,paint);
        c.drawBitmap(camara,anchoPantalla/25*23,0,null);

        c.drawText(context.getResources().getString(R.string.Juego),anchoPantalla/2,altoPantalla/6*1,lapiz);
        c.drawText(context.getResources().getString(R.string.Opciones),anchoPantalla/2,altoPantalla/6*2,lapiz);
        c.drawText(context.getResources().getString(R.string.FAME),anchoPantalla/2,altoPantalla/6*3,lapiz);
        c.drawText(context.getResources().getString(R.string.CREDITOS),anchoPantalla/2,altoPantalla/6*4,lapiz);
        c.drawText(context.getResources().getString(R.string.TUTORIAL),anchoPantalla/2,altoPantalla/6*5,lapiz);

    }

}
