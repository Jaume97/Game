package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.util.Timer;

/**
 * Clase con toda la informacion de Jormunand(Final boss).
 */
public class Jormunand extends Enemigo {
    //vidas de jormunand.
    int vidas=2;//cambiar a 20

    /**
     * Inicializa las propiedades a parametros de la clase heredada.
     * @param context
     * @param imagenes
     * @param altoPantalla
     * @param anchoPantalla
     * @param indiceX
     * @param indiceY
     * @param cantidadFramesX
     * @param cantidadFramesY
     * @param framesCojerX
     * @param framesCojerY
     * @param posx
     * @param posY
     * @param tamañoEscalado
     * @param imagenesDañoFire
     * @param imagenesDañoIce
     */
    public Jormunand(Context context,Bitmap imagenes, int altoPantalla, int anchoPantalla, int indiceX, int indiceY, int cantidadFramesX,
                     int cantidadFramesY, int framesCojerX, int framesCojerY, float posx, float posY, int tamañoEscalado,Bitmap[] imagenesDañoFire,Bitmap[] imagenesDañoIce) {
        super(context,imagenes, altoPantalla, anchoPantalla, indiceX, indiceY, cantidadFramesX, cantidadFramesY, framesCojerX, framesCojerY, posx, posY,tamañoEscalado,imagenesDañoFire,imagenesDañoIce);

    }

    /**
     * Dibuja los elementos de jormunand.
     * @param c
     */
    //Aqui se agregaria el ataque de jormunand.
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);

    }

    /**
     * Modifica la fisica de jormunand.
     */
    @Override
    public void actualizaFisica() {
        super.actualizaFisica();
    }
}
