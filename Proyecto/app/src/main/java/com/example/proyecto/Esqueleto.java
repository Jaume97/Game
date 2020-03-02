package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;

public class Esqueleto extends Enemigo {
    int vidas=10;
    public Esqueleto(Context context, Bitmap imagenes, int altoPantalla, int anchoPantalla, int indiceX, int indiceY, int cantidadFramesX, int cantidadFramesY, int framesCojerX, int framesCojerY, float posx, float posY, int tamañoEscalado, Bitmap[] imagenesDañoFire, Bitmap[] imagenesDañoIce) {
        super(context, imagenes, altoPantalla, anchoPantalla, indiceX, indiceY, cantidadFramesX, cantidadFramesY, framesCojerX, framesCojerY, posx, posY, tamañoEscalado, imagenesDañoFire, imagenesDañoIce);
    }
}
