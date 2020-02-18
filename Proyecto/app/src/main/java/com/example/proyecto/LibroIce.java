package com.example.proyecto;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class LibroIce {
    Bitmap image;
    int posX,posY,posXHUD;
    Rect heatbox;

    /**
     * Inicializa las propiedades a los parametros del constructor y la heatbox de Nick
     * @param image
     * @param posX
     * @param posY
     * @param PosXHUD
     */
    public LibroIce(Bitmap image, int posX, int posY,int PosXHUD) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.posXHUD=PosXHUD;
        heatbox=new Rect(posX,posY,posX+image.getWidth(),posY+image.getHeight());
    }

    /**
     * Dibuja a Nick con su correspondiente heatbox
     * @param c
     * @param paint
     */
    public void dibujar(Canvas c,Paint paint){
        //heatbox=new Rect(posX,posY,posX+image.getWidth(),posY+image.getHeight());
        c.drawRect(heatbox,paint);
        c.drawBitmap(image,posX,posY,null);
    }

    /**
     * Dibuja el la parte superior izquierda el grimorio d hielo cuando Nick lo recoje
     * @param c
     */
    public void dibujarHUD(Canvas c){
        c.drawBitmap(image,posXHUD,0,null);
    }

    /**
     * Elimina el Rect del grimorio de hielo
     */
    public void removeBook(){
        heatbox=null;
    }

    /**
     * Sobreescribe la propiedad por el parametro
     * @param posX
     */
    public void setPosX(int posX) {
        this.posX = posX;
        actualizoRext();
    }

    /**
     * Sobreescribe la propiedad por el parametro
     * @param posY
     */
    public void setPosY(int posY) {
        this.posY = posY;
        actualizoRext();
    }

    /**
     * Suma la posicion y del grimorio mas la cantidad que se pasa por parametro, para que el grimorio se mueva segun se mueva Nick
     * @param cant
     */
    public void sumaY(int cant){
        posY+=cant;
        actualizoRext();
    }

    /**
     * Suma la posicion x del grimorio mas la cantidad que se pasa por parametro, para que el grimorio se mueva segun se mueva Nick
     * @param cant
     */
    public void sumaX(int cant){
        posX+=cant;
        actualizoRext();
    }

    /**
     *  Actualiza el rect del grimorio para que le siga a la imagen del propio.
     */
    public void actualizoRext(){
        heatbox=new Rect(posX,posY,posX+image.getWidth(),posY+image.getHeight());
    }
}
