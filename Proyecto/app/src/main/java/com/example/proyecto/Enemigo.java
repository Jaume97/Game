package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class Enemigo extends Personaje {
    long tiempo=0;
    int tick=100;
    int cont=0;
    int random;
    public Enemigo(Context context, Bitmap imagenes, int altoPantalla, int anchoPantalla, int indiceX, int indiceY, int cantidadFramesX, int cantidadFramesY, int framesCojerX, int framesCojerY, float posx, float posY, int tamañoEscalado) {
        super(context, imagenes, altoPantalla, anchoPantalla, indiceX, indiceY, cantidadFramesX, cantidadFramesY, framesCojerX, framesCojerY, posx, posY, tamañoEscalado);
        random=(int) (Math.random()*4+1);
    }

    @Override
    public void actualizaFisica() {
        super.actualizaFisica();
        if(System.currentTimeMillis()-tiempo>tick){
            cont++;
                switch (random){//PIXELES???
                    case 1://derecha
                        this.mueveX(getPixels(3));
                        actual=frames.de;
                        break;
                    case 2://izquierda
                        this.mueveX(getPixels(-3));
                        actual=frames.iz;
                        break;
                    case 3://arriba
                        this.mueveY(getPixels(-3));
                        actual=frames.ar;
                        break;
                    case 4://abajo
                        this.mueveY(getPixels(3));
                        actual=frames.ab;
                        break;

                    default:
                        Log.i("no-gira",""+random);
                        break;
                }
            if(cont==20){
                random=(int) (Math.random()*4+1);
                cont=0;
            }
            tiempo=System.currentTimeMillis();
        }
    }
}
