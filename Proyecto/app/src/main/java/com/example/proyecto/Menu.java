package com.example.proyecto;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;



public class Menu extends Escena {
    Rect juego,opciones,salon,creditos,tutorial,rectCamara;
    Bitmap camara;
    public Menu(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla,Bitmap camara) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);
        this.camara=escalaAltura(getBitmapFromAssets("camera.png"),altoPantalla/6);

        rectCamara=new Rect(anchoPantalla-this.camara.getWidth(),0,anchoPantalla,this.camara.getHeight());
        juego= new Rect(anchoPantalla/6*2,0,anchoPantalla/6*4,altoPantalla/6*1);
        opciones= new Rect(juego.left,juego.bottom+getPixels(20),juego.right,altoPantalla/6*2+getPixels(20));
        salon= new Rect(juego.left,opciones.bottom+getPixels(10),juego.right,altoPantalla/6*3+getPixels(20));
        creditos= new Rect(juego.left,salon.bottom+getPixels(10),juego.right,altoPantalla/6*4+getPixels(20));
        tutorial= new Rect(juego.left,creditos.bottom+getPixels(10),juego.right,altoPantalla/6*5+getPixels(20));


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
        c.drawRect(rectCamara,paint);
        c.drawBitmap(camara,anchoPantalla-camara.getWidth(),0,null);

        c.drawText(context.getResources().getString(R.string.Juego),anchoPantalla/2,altoPantalla/6*1,lapiz);
        c.drawText(context.getResources().getString(R.string.Opciones),anchoPantalla/2,altoPantalla/6*2,lapiz);
        c.drawText(context.getResources().getString(R.string.FAME),anchoPantalla/2,altoPantalla/6*3,lapiz);
        c.drawText(context.getResources().getString(R.string.CREDITOS),anchoPantalla/2,altoPantalla/6*4,lapiz);
        c.drawText(context.getResources().getString(R.string.TUTORIAL),anchoPantalla/2,altoPantalla/6*5,lapiz);

    }

}
