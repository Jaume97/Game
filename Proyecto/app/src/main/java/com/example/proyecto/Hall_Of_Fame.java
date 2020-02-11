package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class Hall_Of_Fame extends Escena {
    public Hall_Of_Fame(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);
    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);

    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
            if(atras.contains((int) event.getX(),(int) event.getY())){
                //DEBERIA GUARDAR LAS BOOLEANAS DE VOLUMEN Y VIBRACION AQUI;
                return 1;
            }
        }
        return numeroEscena;
    }
}
