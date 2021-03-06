package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Clase que almacena todas las opciones del juego.
 */
public class Opciones extends Escena {
    //Botones de vibracion, volumen, y el idioma a seleccionar(Español,Ingles)
    Rect vibracion,volumen,idioma;
    //Clase inicializada con la musica de la app.
    MediaPlayer mediaPlayer;

    /**
     * Inicializa las propiedades a los parametros.
     * @param numeroEscena
     * @param fondo
     * @param context
     * @param anchoPantalla
     * @param altoPantalla
     * @param mediaPlayer
     */
    public Opciones(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla, MediaPlayer mediaPlayer) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);
        vibracion=new Rect(anchoPantalla/6*1,0,anchoPantalla/6*4,altoPantalla/6*1);
        volumen= new Rect(anchoPantalla/6*1,altoPantalla/6*2,anchoPantalla/6*4,altoPantalla/6*3);
        idioma= new Rect(anchoPantalla/6*1,altoPantalla/6*4,anchoPantalla/6*4,altoPantalla/6*5);
        Log.i("Español?", ""+preferences.getBoolean("idioma",false));
        this.mediaPlayer=mediaPlayer;
    }

    /**
     * Dibuja los elementos de las opciones.
     * @param c
     */
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawRect(vibracion,paint);
        c.drawRect(volumen,paint);
        c.drawRect(idioma,paint);

        c.drawText(context.getString(R.string.VIBRACION)+":"+(preferences.getBoolean("vibra",true)?"ON":"OFF"),vibracion.left+((vibracion.right-vibracion.left)/2),altoPantalla/6*1,lapiz);

        if(preferences.getBoolean("volu",true)){
            c.drawText(context.getString(R.string.VOLUMEN)+": ON",volumen.left+((volumen.right-volumen.left)/2),altoPantalla/6*3,lapiz);

        }else{
            c.drawText(context.getString(R.string.VOLUMEN)+": OFF",volumen.left+((volumen.right-volumen.left)/2),altoPantalla/6*3,lapiz);
        }

        c.drawText(context.getString(R.string.Idioma)+":"+(preferences.getBoolean("idioma",false)?"ESPAÑOL":"ENGLISH"),idioma.left+((idioma.right-idioma.left)/2),idioma.bottom,lapiz);
    }

    /**
     *  Evento para cambiar de idioma y activar/desactivar volumen y vibracion.
     * @param event
     * @return numero de escena.
     */
    @Override
    public int onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                if(vibracion.contains((int) event.getX(),(int) event.getY())) {
                    editor.putBoolean("vibra",!preferences.getBoolean("vibra",true));
                    editor.commit();
                    if(preferences.getBoolean("vibra",true)){
                        vibrator.vibrate(300);
                    }
                }if(volumen.contains((int) event.getX(),(int) event.getY())) {
                    editor.putBoolean("volu",!preferences.getBoolean("volu",true));
                    editor.commit();
                    if(preferences.getBoolean("volu",true)){
                        mediaPlayer.start();
                    }else{
                        mediaPlayer.pause();
                    }
                }if(atras.contains((int) event.getX(),(int) event.getY())){
                    return 1;
                }if(idioma.contains((int) event.getX(),(int) event.getY())){
                    editor.putBoolean("idioma",!preferences.getBoolean("idioma",false));
                    editor.commit();
                    Log.i("Español?",""+preferences.getBoolean("idioma",false));
                    if(preferences.getBoolean("idioma",false)){
                        changeLanguage("es");
                    }else{
                        changeLanguage("en");
                    }
                }
            }
        return numeroEscena;
    }
}
