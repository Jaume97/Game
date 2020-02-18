package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Nick extends Personaje{
    int vidas=3;
    boolean spellFrozen=false,isFigthing=false;
    Frames framesAttack;
    long tiempo=0;
    int tick=500;
    int frame=0;
    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public Nick(Context context,Bitmap imagenes, int altoPantalla, int anchoPantalla, int indiceX, int indiceY, int cantidadFramesX, int cantidadFramesY, int framesCojerX, int framesCojerY, float posx, float posY,int tamañoEscalado,Bitmap imagenAtaque) {
        super(context,imagenes, altoPantalla, anchoPantalla, indiceX, indiceY, cantidadFramesX, cantidadFramesY, framesCojerX, framesCojerY, posx, posY,tamañoEscalado);
        framesAttack=getImagenes(imagenAtaque,0,0,7,21,7,4,4);
    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        if(isFigthing){
            if(actual[0].sameAs(frames.iz[0])){
                actual=framesAttack.iz;
            }else if(actual[0].sameAs(frames.de[0])){
                actual=framesAttack.de;
            }else if(actual[0].sameAs(frames.ar[0])){
                actual=framesAttack.ar;
            }else if(actual[0].sameAs(frames.ab[0])){
                actual=framesAttack.ab;
            }
        }
    }

    @Override
    public void actualizaFisica() {//FALLA AQUI
        super.actualizaFisica();
        if(isFigthing){
            if(System.currentTimeMillis()-tiempo>tick){
                frame++;
                if(frame>=actual.length){
                    frame=0;
                    //Comprobacion de la direccion de Nick al acabar el ataque
                    if(actual[0].sameAs(framesAttack.iz[0])){
                        actual=frames.iz;
                    }else if(actual[0].sameAs(framesAttack.de[0])){
                        actual=frames.de;
                    }else if(actual[0].sameAs(framesAttack.ar[0])){
                        actual=frames.ar;
                    }else if(actual[0].sameAs(framesAttack.ab[0])){
                        actual=frames.ab;
                    }
                    isFigthing=false;
                }
                tiempo=System.currentTimeMillis();
            }
        }

    }
}
