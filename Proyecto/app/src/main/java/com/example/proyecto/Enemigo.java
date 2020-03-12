package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Enemigo extends Personaje {
    //el tiempo de la IA del enemigo y el tiempo que tarda en recibir el daño
    long tiempo=0,tiempoHurt=0;
    //intervalo de tiempo de movimiento de IA(tick) y intervalo de tiempo al recibir daño(tickHurt).
    int tick=100,tickHurt=200;
    //numero de seleccion de imagen para el movimiento de la IA(cont)
    //Numero de seleccion de imagen para la explosion al ser dañado(frameHurt);
    int cont=0,frameHurt=0;
    //numero aleatorio para escoger la direccion de movimiento de la IA
    int random;
    //arrays de imagenes para las explosion de fuego y hielo respectivamente.
    Bitmap[] imagenesDañoFire,imagenesDañoIce;
    //booleanas que indican si el enemigo es dañado por fuego o por hielo.
    boolean isHurtWhitFire=false,isHurtWhitIce=false;
    //booleana que indica si el enemigo esta vivo.
    boolean isAlive=true;

    /**
     * Clase que hereda de personaje, inicializa las propiedades a parametros.
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
     * @param imagenesDañoFire
     * @param imagenesDañoIce
     */
    public Enemigo(Context context, Bitmap imagenes, int altoPantalla, int anchoPantalla, int indiceX,
                   int indiceY, int cantidadFramesX, int cantidadFramesY, int framesCojerX, int framesCojerY, float posx, float posY, int tamañoEscalado,Bitmap[] imagenesDañoFire,Bitmap[] imagenesDañoIce) {
        super(context, imagenes, altoPantalla, anchoPantalla, indiceX, indiceY, cantidadFramesX, cantidadFramesY, framesCojerX, framesCojerY, posx, posY, tamañoEscalado);
        random=(int) (Math.random()*4+1);
        this.imagenesDañoFire=imagenesDañoFire;
        this.imagenesDañoIce=imagenesDañoIce;
    }

    /**
     * Evento donde se ejecuta el movimiento del enemigo y el daño de fuego o hielo.
     */
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

    /**
     * Evento que dibuja el daño de fuego o hielo dependiendo del elemento de ataque.
     * @param c
     */
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
