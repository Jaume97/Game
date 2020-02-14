package com.example.proyecto;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class LibroIce {
    Bitmap image;
    int posX,posY,posXHUD;
    Rect heatbox;
    public LibroIce(Bitmap image, int posX, int posY,int PosXHUD) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.posXHUD=PosXHUD;
        heatbox=new Rect(posX,posY,posX+image.getWidth(),posY+image.getHeight());
    }

    public void dibujar(Canvas c,Paint paint){
        //heatbox=new Rect(posX,posY,posX+image.getWidth(),posY+image.getHeight());
        c.drawRect(heatbox,paint);
        c.drawBitmap(image,posX,posY,null);
    }
    public void dibujarHUD(Canvas c){
        c.drawBitmap(image,posXHUD,0,null);
    }
    public void removeBook(){
        heatbox=null;
    }

    public void setPosX(int posX) {
        this.posX = posX;
        actualizoRext();
    }

    public void setPosY(int posY) {
        this.posY = posY;
        actualizoRext();
    }

    public void sumaY(int cant){
        posY+=cant;
        actualizoRext();
    }

    public void sumaX(int cant){
        posX+=cant;
        actualizoRext();
    }


    public void actualizoRext(){
        heatbox=new Rect(posX,posY,posX+image.getWidth(),posY+image.getHeight());
    }
}
