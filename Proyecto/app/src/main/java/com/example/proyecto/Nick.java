package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;

public class Nick extends Personaje{
    int vidas=3;
    boolean spellFrozen=false,isFigthing=false,spellHit=false;
    Frames framesAttack;
    long tiempo=0;
    int tick=250;
    int frameAttack =0;
    float posXCopy;

    Spell spell;

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

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

        if(isFigthing && !spellHit){
            spell.dibujar(c,paint);
            if(direccion==Direccion.izquierda){
                actual=framesAttack.iz;
                spell.sumaX(-getPixels(5));
            }else if(direccion==Direccion.derecha){
                actual=framesAttack.de;
                spell.sumaX(getPixels(5));
            }else if(direccion==Direccion.arriba){
                actual=framesAttack.ar;
                spell.sumaY(-getPixels(5));
            }else if(direccion==Direccion.abajo){
                actual=framesAttack.ab;
                spell.sumaY(getPixels(5));
            }


        }
        super.dibujar(c);
    }

    @Override
    public void actualizaFisica() {
        super.actualizaFisica();
        if(isFigthing){
            spell.actualizaFisica();
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
