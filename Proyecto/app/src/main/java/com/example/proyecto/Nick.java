package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Clase del protagonista del juego.
 */
public class Nick extends Personaje{
    //vidas de Nick.
    int vidas=3;
    //booleas de si lanza hielo, esta luchando y acertó hechizo respectivamente.
    boolean spellFrozen=false,isFigthing=false,spellHit=false;
    //la clase donde se almacenan los frames del ataque de Nick.
    Frames framesAttack;
    //Tiempo que dura el ataque.
    long tiempo=0;
    //Intervalo de tiempo entre movimiento de ataque.
    int tick=250;
    //el frame de ataque actual.
    int frameAttack =0;
    //Clase donde almacena el movimiento y la fisica y el tipo de hechizo, del ataque de Nick.
    Spell spell;


    /**
     * Sobreescribe el valor de la propiedad por el parametro.
     * @param spell
     */
    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    /**
     * Devuelve el valor de las vidas de Nick
     * @return vidas
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * Sobreescribe el valor de la propiedad por el parametro.
     * @param vidas
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    /**
     * Inicializa las propiedades a parametros tanto de la clase heredada como la propia clase.
     * @param context
     * @param imagenes
     * @param altoPantalla
     * @param anchoPantalla
     * @param indiceX
     * @param indiceY
     * @param cantidadFramesX
     * @param cantidadFramesY
     * @param framesCojerX
     * @param framesCojerY
     * @param posx
     * @param posY
     * @param tamañoEscalado
     * @param imagenAtaque
     */
    public Nick(Context context,Bitmap imagenes, int altoPantalla, int anchoPantalla, int indiceX, int indiceY, int cantidadFramesX, int cantidadFramesY, int framesCojerX, int framesCojerY, float posx, float posY,int tamañoEscalado,Bitmap imagenAtaque) {
        super(context,imagenes, altoPantalla, anchoPantalla, indiceX, indiceY, cantidadFramesX, cantidadFramesY, framesCojerX, framesCojerY, posx, posY,tamañoEscalado);
        framesAttack=getImagenes(imagenAtaque,0,0,7,4,7,4,4);



    }

    /**
     * Dibuja tanto la animacion de ataque de nick como la animacion del hechizo.
     * @param c
     */
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

    /**
     * Modifica la fisica del hechizo y de la animacion de ataque de nick.
     */
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
