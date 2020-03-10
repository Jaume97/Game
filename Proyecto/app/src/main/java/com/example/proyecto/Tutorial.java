package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.MotionEvent;

public class Tutorial extends Escena {
    Bitmap jor,nick,pad,corasao,bookIce,padAttack;
    StaticLayout textLayoutJor,textLayoutNick,textLayoutPad,textlayoutCorason,textLayoutBook,textLayoutPadAttack;
    TextPaint textPaint;
    float dy=0;
    float positionY=0;
    public Tutorial(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);

        textPaint= new TextPaint();
        textPaint.setTypeface(faw);
        textPaint.setTextSize(40);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setFakeBoldText(true);

        jor=escalaAltura(getBitmapFromAssets("JormunandTutorial.png"),altoPantalla/4);
        nick=escalaAltura(getBitmapFromAssets("NickCreditos.png"),altoPantalla/5);
        pad=escalaAltura(getBitmapFromAssets("dpad.png"),altoPantalla/6);
        corasao=escalaAltura(getBitmapFromAssets("heart-full.png"),altoPantalla/7);
        bookIce=escalaAltura(getBitmapFromAssets("bookice.png"),altoPantalla/7);
        padAttack=escalaAltura(getBitmapFromAssets("attack.png"),altoPantalla/6);

        textLayoutJor=new StaticLayout(context.getResources().getString(R.string.CreditosJormunand),textPaint,anchoPantalla-anchoPantalla/5*2-getPixels(25), Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
        textLayoutNick=new StaticLayout(context.getResources().getString(R.string.CreditosNick),textPaint,anchoPantalla-anchoPantalla/5*2-getPixels(25), Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
        textLayoutPad= new StaticLayout(context.getResources().getString(R.string.CreditosPad),textPaint,anchoPantalla-anchoPantalla/5*2-getPixels(25), Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
        textlayoutCorason= new StaticLayout(context.getResources().getString(R.string.CreditosCorazon),textPaint,anchoPantalla-anchoPantalla/5*2-getPixels(25), Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
        textLayoutBook= new StaticLayout(context.getResources().getString(R.string.Book),textPaint,anchoPantalla-anchoPantalla/5*2-getPixels(25), Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
        textLayoutPadAttack=new StaticLayout(context.getResources().getString(R.string.CreditosPadAttack),textPaint,anchoPantalla-anchoPantalla/5*2-getPixels(25), Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
    }

    @Override
    public void dibujar(Canvas c) {//context.getResources().getString(R.string.CreditosJormunand)
        super.dibujar(c);
        c.drawBitmap(jor,anchoPantalla/5*1,dy+0,null);

        c.save(); // guardamos el lienzo
        c.translate(anchoPantalla/5*2+getPixels(5),dy+jor.getHeight()/4); // Movemos el lienzo
        textLayoutJor.draw(c); // Dibujamos el texto
        c.restore(); // Recuperamos el lienzo a su posición original

        c.drawBitmap(nick,anchoPantalla/5*1+(jor.getWidth()/4),dy+jor.getHeight()+getPixels(2),null);

        c.save(); // guardamos el lienzo
        c.translate(anchoPantalla/5*2+getPixels(5),dy+jor.getHeight()+nick.getHeight()/4); // Movemos el lienzo
        textLayoutNick.draw(c); // Dibujamos el texto
        c.restore(); // Recuperamos el lienzo a su posición original

        c.drawBitmap(pad,anchoPantalla/5*1+(jor.getWidth()/4),dy+jor.getHeight()+nick.getHeight()+getPixels(4),null);

        c.save(); // guardamos el lienzo
        c.translate(anchoPantalla/5*2+getPixels(5),dy+jor.getHeight()+nick.getHeight()+pad.getHeight()/4); // Movemos el lienzo
        textLayoutPad.draw(c); // Dibujamos el texto
        c.restore(); // Recuperamos el lienzo a su posición original

        c.drawBitmap(corasao,anchoPantalla/5*1+(jor.getWidth()/4),dy+jor.getHeight()+nick.getHeight()+pad.getHeight()+getPixels(10),null);

        c.save(); // guardamos el lienzo
        c.translate(anchoPantalla/5*2+getPixels(5),dy+jor.getHeight()+nick.getHeight()+pad.getHeight()+corasao.getHeight()/4); // Movemos el lienzo
        textlayoutCorason.draw(c); // Dibujamos el texto
        c.restore(); // Recuperamos el lienzo a su posición original

        c.drawBitmap(bookIce,anchoPantalla/5*1+(jor.getWidth()/4),dy+jor.getHeight()+nick.getHeight()+pad.getHeight()+corasao.getHeight()+getPixels(15),null);

        c.save(); // guardamos el lienzo
        c.translate(anchoPantalla/5*2+getPixels(5),dy+jor.getHeight()+nick.getHeight()+pad.getHeight()+corasao.getHeight()+(bookIce.getHeight()/2)); // Movemos el lienzo
        textLayoutBook.draw(c); // Dibujamos el texto
        c.restore(); // Recuperamos el lienzo a su posición original

        c.drawBitmap(padAttack,anchoPantalla/5*1+(jor.getWidth()/4),dy+jor.getHeight()+nick.getHeight()+pad.getHeight()+corasao.getHeight()+bookIce.getHeight()+getPixels(20),null);

        c.save(); // guardamos el lienzo
        c.translate(anchoPantalla/5*2+getPixels(5),dy+jor.getHeight()+nick.getHeight()+pad.getHeight()+corasao.getHeight()+bookIce.getHeight()+(padAttack.getHeight()/2)+getPixels(5)); // Movemos el lienzo
        textLayoutPadAttack.draw(c); // Dibujamos el texto
        c.restore(); // Recuperamos el lienzo a su posición original

    }

    @Override
    public void actualizarFisica() {
        super.actualizarFisica();
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        float positionYOld=positionY;
         positionY=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                if(atras.contains((int) event.getX(),(int) event.getY())){
                    //DEBERIA GUARDAR LAS BOOLEANAS DE VOLUMEN Y VIBRACION AQUI;
                    return 1;
                }
                break;
            case MotionEvent.ACTION_MOVE:

               if (positionYOld!=0 ){
                   if(!(dy+jor.getHeight()+nick.getHeight()+pad.getHeight()+corasao.getHeight()+getPixels(155)<altoPantalla)) {

                       dy += (positionY - positionYOld);
                       if (dy > 0) {
                           dy = 0;
                       }
                   }else {
                       if ((positionY - positionYOld)>0){
                           dy += (positionY - positionYOld);
                           if (dy > 0) {
                               dy = 0;
                           }
                       }
                   }
               }
                Log.i("getY","PositionY:"+positionY+" Event.getY:"+positionYOld+" dy:"+dy+" "+(dy+jor.getHeight()+nick.getHeight()+pad.getHeight()+corasao.getHeight()+getPixels(15)));
                break;
        }
        return numeroEscena;
    }
}
