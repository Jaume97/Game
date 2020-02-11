package com.example.proyecto;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.Log;

import java.util.Random;

public class Jormunand {
    Bitmap imagenes;
    Bitmap[] iz,de,ar,ab;
    float posX,posY;
    int altoPantalla,anchoPantalla;
    public Jormunand(Bitmap imagenes,float x,float y,int anchoPantalla,int altoPantalla){
        this.imagenes=imagenes;
        this.posX=x;
        this.posY=y;
        this.anchoPantalla=anchoPantalla;
        this.altoPantalla=altoPantalla;
        getImagenes(imagenes,0,0,3,4,3,4);  //numPerX=indice de la parte de eje x donde empieza;numPerY=eje y donde se empieza a contar; numFramesH=cantidad de imagenes a contar en el eje x;
                                                                                                        //numFramesV=cantidad de imagenes en el eje y; fraPerso cantidad de imagenes que quieres en el eje X;lo mismo con fraPersoY;

    }

    public void dibujar(Canvas c){

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
                    case 0: ar[i]=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/3) ;
                        break;
                    case 1: de[i]=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/3) ;
                    Log.i("derecha","X:"+i+" Y:"+j);
                        break;
                    case 2: ab[i]=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/3) ;
                        break;
                    case 3: iz[i]=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/3) ;
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
