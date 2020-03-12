package com.example.proyecto;

import android.graphics.Bitmap;

/**
 * Clase donde se almacenan las imagenes de cualquier personaje del juego,tiene 4 arrays(uno por cada sentido de orientacion).
 */
public class Frames {
    //Arrays de imagenes del movimiento a la izquierda,derecha,arriba y abajo respectivamente.
    Bitmap[] iz,de,ar,ab;

    /**
     * Devuelve el valor de de[].
     * @return
     */
    public Bitmap[] getDe() {
        return de;
    }
}
