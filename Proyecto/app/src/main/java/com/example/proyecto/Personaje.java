package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
enum Direccion{
    derecha,izquierda,arriba,abajo
}
public class Personaje {
    Direccion direccion;
    Bitmap imagenes;
    Bitmap[] actual;
    Frames frames;
    int altoPantalla,anchoPantalla;
    Rect hitbox;//Todos los personajes tiene su hitbox;
    float posX,posY;
    Paint paint;
    int frame=0,tick=200;
    long tiempo=0;
    Context context;
    public Bitmap[] getActual() {
        return actual;
    }

    public void setActual(Bitmap[] actual) {
        this.actual = actual;
    }

    public Personaje(Context context,Bitmap imagenes, int altoPantalla, int anchoPantalla, int indiceX,
                     int indiceY, int cantidadFramesX, int cantidadFramesY, int framesCojerX, int framesCojerY, float posx, float posY,int tamañoEscalado) {
        this.context=context;
        this.imagenes = imagenes;
        this.posX=posx;
        this.posY=posY;
        this.altoPantalla = altoPantalla;
        this.anchoPantalla = anchoPantalla;
        paint= new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        frames=getImagenes(imagenes,indiceX,indiceY,cantidadFramesX,cantidadFramesY,framesCojerX,framesCojerY,tamañoEscalado);
        actual=frames.ab;
        direccion=Direccion.abajo;
        actualizoRext();

    }
    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(). getMetrics(metrics);
        return (int)(dp*metrics.density);
    }
    public Frames getImagenes(Bitmap img,int numPerX,int numPerY,int numFramesH,int numFramesV,int fraPersoX,int fraPersoY,int tamañoEscalado){
        int sizeX=img.getWidth()/numFramesH;
        int sizeY=img.getHeight()/numFramesV;

        Frames frames1= new Frames();
        frames1.iz= new Bitmap[fraPersoX];
        frames1.de= new Bitmap[fraPersoX];
        frames1.ab= new Bitmap[fraPersoX];
        frames1.ar= new Bitmap[fraPersoX];



        int iniX=(numPerX)*fraPersoX;
        int iniY=(numPerY)*fraPersoY;
        for (int j = 0; j < fraPersoY; j++) {
            for (int i = 0; i < fraPersoX; i++) {
                switch (j){
                    case 0: frames1.ar[i]=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/tamañoEscalado) ;
                        break;
                    case 1: frames1.de[i]=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/tamañoEscalado) ;
                        break;
                    case 2: frames1.ab[i]=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/tamañoEscalado) ;
                        break;
                    case 3: frames1.iz[i]=escalaAltura(Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY),altoPantalla/tamañoEscalado) ;
                        break;
                }
            }
        }

        return frames1;
    }
    /**
     * Metodo que escala en altura el Bitmap pasado por el parametro
     * @param res
     * @param nuevoAlto
     * @return imagen rescalada
     */
    public Bitmap escalaAltura(Bitmap res, int nuevoAlto ) {
        Bitmap bitmapAux=res;
        if (nuevoAlto==bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) /
                bitmapAux.getHeight(), nuevoAlto, true);
    }

    public void mueveX(float posX){
        this.posX+=posX;
        actualizoRext();

    }

    public void dibujar(Canvas c){

        c.drawBitmap(actual[frame],posX,posY,null);
        c.drawRect(hitbox,paint);
    }
    public void actualizaFisica(){
        if(System.currentTimeMillis()-tiempo>tick){
            frame++;
            if(frame>=actual.length){
                frame=0;
            }
            tiempo=System.currentTimeMillis();
        }
    }

    public void mueveY(float posY){
        this.posY+=posY;
        actualizoRext();

    }
    public Rect clonaRect(){
        return new Rect(hitbox.left, hitbox.top,hitbox.right,hitbox.bottom);
    }
    public void actualizoRext(){
        hitbox=new Rect((int)posX,(int)posY,(int)posX+actual[0].getWidth(),(int)posY+actual[0].getHeight());
    }
}
