package com.example.proyecto;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Clase donde se establece las imagenes y las fisicas del hechizo de Nick.
 */
public class Spell {
    //Imagenes del hechizo.
    Bitmap[] image;
    //posiciones X y Y del hechizo
    float posX,posY;
    //la hitbox del hechizo.
    Rect heatbox;
    //el numero de frame del hechizo y el intervalo de tiempo entre movimiento del hechizo, respectivamente.
    int frameFire=0,tick=300;
    //Tiempo que dura la animacion del hechizo.
    long tiempo=0;

    /**
     * Inicializa las propiedades a parametros.
     * @param image
     * @param posX
     * @param posY
     */
    public Spell(Bitmap[] image, float posX, float posY) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        heatbox=new Rect((int)posX,(int)posY,(int)posX+image[frameFire].getWidth(),(int)posY+image[frameFire].getHeight());
    }

    /**
     * Dibuja las imagenes del hechizo.
     * @param c
     * @param paint
     */
    public void dibujar(Canvas c, Paint paint){
        c.drawRect(heatbox,paint);
        c.drawBitmap(image[frameFire],posX,posY,null);
    }

    /**
     * Actualiza la hitbox del hechizo.
     */
    public void actualizoRect(){
        heatbox=new Rect((int)posX,(int)posY,(int)posX+image[frameFire].getWidth(),(int)posY+image[frameFire].getHeight());
    }

    /**
     * Suma el numero pasado por el parametro al eje X de la posicion del hechizo.
     * @param cant
     */
    public void sumaX(int cant){
        posX+=cant;
        actualizoRect();
    }

    /**
     * Suma el numero pasado por el parametro al eje X de la posicion del hechizo.
     * @param cant
     */
    public void sumaY(int cant){
        posY+=cant;
        actualizoRect();
    }

    /**
     * Modifica la fisica y el movimiento del hechizo.
     */
    public void actualizaFisica(){
        if(System.currentTimeMillis()-tiempo>tick){
            frameFire++;
            if(frameFire>=image.length){
                frameFire=0;
            }
            tiempo=System.currentTimeMillis();
        }
    }

    /**
     * Devuelve lahitbox del hechizo para comprobaciones de colisiones.
     * @return hitbox del spell.
     */
    public Rect clonaRect(){
        return new Rect(heatbox.left, heatbox.top,heatbox.right,heatbox.bottom);
    }
}
