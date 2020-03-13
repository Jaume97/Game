package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Clase donde se almacena los jugadores que llegaron al final del juego.
 */
public class Hall_Of_Fame extends Escena {
    Rect borrar;
    TextPaint textPaint;
    StaticLayout text;
    Bitmap croos;
    /**
     * Inicializa las propiedades a parametros.
     * @param numeroEscena
     * @param fondo
     * @param context
     * @param anchoPantalla
     * @param altoPantalla
     */
    public Hall_Of_Fame(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);

        croos=escalaAltura(getBitmapFromAssets("cross.png"),altoPantalla/6);

        textPaint= new TextPaint();
        textPaint.setTypeface(faw);
        textPaint.setTextSize(60);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setFakeBoldText(true);

        text=new StaticLayout(preferences.getString("record",""),textPaint,anchoPantalla/2, Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);

        borrar= new Rect(anchoPantalla-croos.getWidth(),0,anchoPantalla,croos.getHeight());
        lapiz.setTextSize(30);
        lapiz.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * Metodo que dibuja los nombres de los jugadores que llegaron al final del juego.
     * @param c
     */
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);

        Log.i("TAG", ""+preferences.getString("record",""));
        c.save();
        c.translate(anchoPantalla/2,0);
        text.draw(c);
        c.restore();

        c.drawRect(borrar,paint);
        c.drawBitmap(croos,anchoPantalla-croos.getWidth(),0,null);

    }

    /**
     * Evento de pulsacion que devuelve el numero de escena segun la opcion seleccionada.
     * @param event
     * @return
     */
    @Override
    public int onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
            if(atras.contains((int) event.getX(),(int) event.getY())){
                return 1;
            }
            if(borrar.contains((int) event.getX(),(int) event.getY())){
                editor.remove("record");
                editor.commit();
            }
        }
        return numeroEscena;
    }
}
