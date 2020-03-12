package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Clase donde se almacena los jugadores que llegaron al final del juego.
 */
public class Hall_Of_Fame extends Escena {
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
    }

    /**
     * Metodo que dibuja los nombres de los jugadores que llegaron al final del juego.
     * @param c
     */
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);

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
        }
        return numeroEscena;
    }
}
