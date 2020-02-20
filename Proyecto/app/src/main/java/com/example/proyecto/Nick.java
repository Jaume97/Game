package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Nick extends Personaje{
    int vidas=3;
    boolean spellFrozen=false,isFigthing=false;
    Frames framesAttack;
    long tiempo=0;
    int tick=150;
    int frameAttack =0;
    float posXCopy;
    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public Nick(Context context,Bitmap imagenes, int altoPantalla, int anchoPantalla, int indiceX, int indiceY, int cantidadFramesX, int cantidadFramesY, int framesCojerX, int framesCojerY, float posx, float posY,int tamañoEscalado,Bitmap imagenAtaque) {
        super(context,imagenes, altoPantalla, anchoPantalla, indiceX, indiceY, cantidadFramesX, cantidadFramesY, framesCojerX, framesCojerY, posx, posY,tamañoEscalado);
        framesAttack=getImagenes(imagenAtaque,0,0,7,4,7,4,4);
        posXCopy=posX;
    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        if(isFigthing){
            if(direccion==Direccion.izquierda){
                actual=framesAttack.iz;
            }else if(direccion==Direccion.derecha){
                actual=framesAttack.de;
            }else if(direccion==Direccion.arriba){
                actual=framesAttack.ar;
            }else if(direccion==Direccion.abajo){
                actual=framesAttack.ab;
            }
        }
    }

    @Override
    public void actualizaFisica() {//FALLA AQUI
        super.actualizaFisica();
        if(isFigthing){
            if(System.currentTimeMillis()-tiempo>tick){
                frameAttack++;
                if(frameAttack >=actual.length){
                    frameAttack =0;
                    //Comprobacion de la direccion de Nick al acabar el ataque
                    if(direccion==Direccion.izquierda){
                        actual=frames.iz;
                    }else if(direccion==Direccion.derecha){
                        actual=frames.de;
                    }else if(direccion==Direccion.arriba){
                        actual=frames.ar;
                    }else if(direccion==Direccion.abajo){
                        actual=frames.ab;
                    }
                    isFigthing=false;
                    frame=0;
                }
                tiempo=System.currentTimeMillis();
            }
        }

    }
}
