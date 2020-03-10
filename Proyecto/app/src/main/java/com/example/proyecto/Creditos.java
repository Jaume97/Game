package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class Creditos extends Escena {
    public Creditos(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);
    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);

    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        if(atras.contains((int) event.getX(),(int) event.getY())){
            return 1;
        }
        return  numeroEscena;
    }
}
