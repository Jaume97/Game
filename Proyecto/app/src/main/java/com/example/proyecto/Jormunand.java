package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.util.Timer;

public class Jormunand extends Enemigo {
    Bitmap[] imagenesDaño;
    public Jormunand(Context context,Bitmap imagenes, int altoPantalla, int anchoPantalla, int indiceX, int indiceY, int cantidadFramesX, int cantidadFramesY, int framesCojerX, int framesCojerY, float posx, float posY, int tamañoEscalado,Bitmap[] imagenesDaño) {
        super(context,imagenes, altoPantalla, anchoPantalla, indiceX, indiceY, cantidadFramesX, cantidadFramesY, framesCojerX, framesCojerY, posx, posY,tamañoEscalado);
        this.imagenesDaño=imagenesDaño;
    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);

    }

    @Override
    public void actualizaFisica() {
        super.actualizaFisica();
    }
}
