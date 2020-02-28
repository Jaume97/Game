package com.example.proyecto;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Spell {
    Bitmap[] image;
    float posX,posY;
    Rect heatbox;
    int frameFire=0,tick=300;
    long tiempo=0;
    public Spell(Bitmap[] image, float posX, float posY) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        heatbox=new Rect((int)posX,(int)posY,(int)posX+image[frameFire].getWidth(),(int)posY+image[frameFire].getHeight());
    }
    public void dibujar(Canvas c, Paint paint){
        c.drawRect(heatbox,paint);
        c.drawBitmap(image[frameFire],posX,posY,null);
    }

    public void actualizoRect(){
        heatbox=new Rect((int)posX,(int)posY,(int)posX+image[frameFire].getWidth(),(int)posY+image[frameFire].getHeight());
    }
    public void sumaX(int cant){
        posX+=cant;
        actualizoRect();
    }
    public void sumaY(int cant){
        posY+=cant;
        actualizoRect();
    }
    public void actualizaFisica(){
        if(System.currentTimeMillis()-tiempo>tick){
            frameFire++;
            if(frameFire>=image.length){
                frameFire=0;
            }
            tiempo=System.currentTimeMillis();
        }
    }
    public void removeSpell(){
        heatbox=null;
    }
}
