package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Clase del esqueleto del juego.
 */
public class Esqueleto extends Enemigo {
    //Vidas del Esqueleto
    int vidas=2;//cambiar a 10

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
    public Esqueleto(Context context, Bitmap imagenes, int altoPantalla, int anchoPantalla, int indiceX, int indiceY, int cantidadFramesX, int cantidadFramesY, int framesCojerX, int framesCojerY, float posx, float posY, int tamañoEscalado, Bitmap[] imagenesDañoFire, Bitmap[] imagenesDañoIce) {
        super(context, imagenes, altoPantalla, anchoPantalla, indiceX, indiceY, cantidadFramesX, cantidadFramesY, framesCojerX, framesCojerY, posx, posY, tamañoEscalado, imagenesDañoFire, imagenesDañoIce);
    }
}
