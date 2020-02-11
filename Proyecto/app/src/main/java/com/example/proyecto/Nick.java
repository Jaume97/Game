package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

public class Nick {
    Bitmap imagenes;
    Bitmap imgRescalada;
    Rect hitboxNick;
    float x,y;
    Context context;
    int altoPantalla,anchoPantalla;
    int vidas=3;
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    Bitmap[] ar,iz,de,ab;
    public Nick(Bitmap imagenes, float x, float y, Context context,int anchoPantalla,int altoPantalla){
        this.imagenes=imagenes;
        this.x=x;
        this.y=y;
        this.context=context;
        this.altoPantalla=altoPantalla;
        this.anchoPantalla=anchoPantalla;
        getImagenes(imagenes,0,0,3,4,3,4);
        hitboxNick=new Rect(anchoPantalla/2,altoPantalla/2,(anchoPantalla/2)+imgRescalada.getWidth(),(altoPantalla/2)+imgRescalada.getHeight());
    }
    public void getImagenes(Bitmap img,int numPerX,int numPerY,int numFramesH,int numFramesV,int fraPersoX,int fraPersoY){
        int sizeX=img.getWidth()/numFramesH;
        int sizeY=img.getHeight()/numFramesV;

        ar= new Bitmap[3];
        iz= new Bitmap[3];
        de= new Bitmap[3];
        ab= new Bitmap[3];

        int iniX=(numPerX)*fraPersoX;
        int iniY=(numPerY)*fraPersoY;
        for (int j = 0; j < fraPersoY; j++) {
            for (int i = 0; i < fraPersoX; i++) {
                switch (j){
                    case 0:
                        imgRescalada=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/10);
                        imgRescalada=escalaAnchura(imgRescalada,anchoPantalla/10);
                        ar[i]=imgRescalada;
                        break;
                    case 1:
                        imgRescalada=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/10);
                        imgRescalada=escalaAnchura(imgRescalada,anchoPantalla/10);
                        de[i]=imgRescalada;
                        break;
                    case 2:
                        imgRescalada=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/10);
                        imgRescalada=escalaAnchura(imgRescalada,anchoPantalla/10);
                        ab[i]=imgRescalada;
                        break;
                    case 3:
                        imgRescalada=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/10);
                        imgRescalada=escalaAnchura(imgRescalada,anchoPantalla/10);
                        iz[i]=imgRescalada;
                        break;
                }
            }
        }
    }
    public Bitmap escalaAnchura(Bitmap res, int nuevoAncho) {
        Bitmap bitmapAux= res;
        if (nuevoAncho==bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) /
                bitmapAux.getWidth(), true);
    }
    public Bitmap escalaAltura(Bitmap res, int nuevoAlto ) {
        Bitmap bitmapAux=res;
        if (nuevoAlto==bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) /
                bitmapAux.getHeight(), nuevoAlto, true);
    }

}
