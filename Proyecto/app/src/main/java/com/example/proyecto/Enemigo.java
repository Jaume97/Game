package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Enemigo extends Personaje {
    long tiempo=0,tiempoHurt=0;
    int tick=100,tickHurt=200;
    int cont=0,frameHurt=0;
    int random;
    Bitmap[] imagenesDañoFire,imagenesDañoIce;
    boolean isHurtWhitFire=false,isHurtWhitIce=false;
    boolean isAlive=true;
    public Enemigo(Context context, Bitmap imagenes, int altoPantalla, int anchoPantalla, int indiceX,
                   int indiceY, int cantidadFramesX, int cantidadFramesY, int framesCojerX, int framesCojerY, float posx, float posY, int tamañoEscalado,Bitmap[] imagenesDañoFire,Bitmap[] imagenesDañoIce) {
        super(context, imagenes, altoPantalla, anchoPantalla, indiceX, indiceY, cantidadFramesX, cantidadFramesY, framesCojerX, framesCojerY, posx, posY, tamañoEscalado);
        random=(int) (Math.random()*4+1);
        this.imagenesDañoFire=imagenesDañoFire;
        this.imagenesDañoIce=imagenesDañoIce;
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
        if(isHurtWhitFire){
            if(System.currentTimeMillis()-tiempoHurt>tickHurt){
                frameHurt++;
                if(frameHurt>=imagenesDañoFire.length){
                    frameHurt=0;
                    isHurtWhitFire=false;
                }
                tiempoHurt=System.currentTimeMillis();

            }
        }else{
            if(System.currentTimeMillis()-tiempoHurt>tickHurt){
                frameHurt++;
                if(frameHurt>=imagenesDañoIce.length){
                    frameHurt=0;
                    isHurtWhitIce=false;
                }
                tiempoHurt=System.currentTimeMillis();

            }
        }

    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        if(isHurtWhitFire){
            c.drawBitmap(imagenesDañoFire[frameHurt],posX,posY,null);
        }
        if(isHurtWhitIce){
            c.drawBitmap(imagenesDañoIce[frameHurt],posX,posY,null);
        }

    }
}
