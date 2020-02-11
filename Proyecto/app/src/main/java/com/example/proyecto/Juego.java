package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class Juego extends Escena {
    Jormunand jormunand;
    Nick nick;
    Bitmap[] dibujoNick,dibujoJormunand;
    Bitmap imagenes_Jormunand,imagenes_Nick,imgCruceta,imgAttack,mapa,heartFull,heartEmpty;
    Rect izquierda,derecha,arriba,abajo,ataque;
    int tick=200,distanciaPaso;
    long tiempo=0;
    int frame=0;
    int x,y;
    boolean arr=false, aba=false, der=false, izz=false;
int cruceY, cruceX;

    public Juego(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);
        imagenes_Jormunand=getBitmapFromAssets("flying_dragon-red.png");
        imagenes_Nick=getBitmapFromAssets("nick.png");
        heartFull=getBitmapFromAssets("heart-full.png");
        heartFull=escalaAltura(heartFull,altoPantalla/10);
        distanciaPaso=getPixels(5);



        mapa=getBitmapFromAssets("map.jpg");
        Log.i("mapa",""+(mapa==null));

        mapa=escalaAnchura(mapa,anchoPantalla*4);

        imgCruceta=getBitmapFromAssets("dpad.png");
        imgCruceta=escalaAltura(imgCruceta,altoPantalla/4);
        imgCruceta=escalaAnchura(imgCruceta,anchoPantalla/4);

        float porSize1=(2000f/72f)/100f;
        float porSize2=(3200f/72f)/100f;


        imgAttack=getBitmapFromAssets("attack.png");
        imgAttack=escalaAltura(imgAttack,altoPantalla/5);
        imgAttack=escalaAnchura(imgAttack,anchoPantalla/5);

        int posYR=altoPantalla/28*20;
        cruceY=altoPantalla-imgCruceta.getHeight();
        cruceX=0;

        izquierda=new Rect(cruceX,(int)(cruceY+porSize1*imgCruceta.getHeight()),cruceX+(int)(porSize1*imgCruceta.getWidth()),cruceY+imgCruceta.getHeight()-(int)(porSize1*imgCruceta.getHeight()));
        arriba= new Rect(cruceX+(int)(porSize1*imgCruceta.getWidth()),cruceY,cruceX+(int)(porSize1*imgCruceta.getWidth())+(int)(porSize2*imgCruceta.getWidth()),(int)(cruceY+porSize1*imgCruceta.getHeight()));

        derecha= new Rect(cruceX+(int)(porSize1*imgCruceta.getWidth())+(int)(porSize2*imgCruceta.getWidth()),(int)(cruceY+porSize1*imgCruceta.getHeight()),cruceX+imgCruceta.getWidth(),cruceY+imgCruceta.getHeight()-(int)(porSize1*imgCruceta.getHeight()));

        abajo= new Rect(cruceX+(int)(porSize1*imgCruceta.getWidth()),cruceY+imgCruceta.getHeight()-(int)(porSize1*imgCruceta.getHeight()),cruceX+(int)(porSize1*imgCruceta.getWidth())+(int)(porSize2*imgCruceta.getWidth()),cruceY+imgCruceta.getHeight());
        ataque= new Rect(anchoPantalla/11*9,altoPantalla/9*6,anchoPantalla,altoPantalla);

        jormunand= new Jormunand(imagenes_Jormunand,200,200,anchoPantalla,altoPantalla);
        nick=new Nick(imagenes_Nick,200,400,context,anchoPantalla,altoPantalla);
        dibujoNick=nick.iz;
        dibujoJormunand=jormunand.ab;
         x=anchoPantalla*-2;
         y=altoPantalla*-2;
    }
//    public void getImagenes(Bitmap img,int numPerX,int numPerY,int numFramesH,int numFramesV,int fraPersoX,int fraPersoY){
//        int sizeX=img.getWidth()/numFramesH;
//        int sizeY=img.getHeight()/numFramesV;
//
//        ar= new Bitmap[3];
//        iz= new Bitmap[3];
//        de= new Bitmap[3];
//        ab= new Bitmap[3];
//
//        int iniX=(numPerX)*fraPersoX;
//        int iniY=(numPerY)*fraPersoY;
//        for (int j = 0; j < fraPersoY; j++) {
//            for (int i = 0; i < fraPersoX; i++) {
//                switch (j){
//                    case 0: ar[i]=Bitmap.createBitmap(img,(iniX+i)*sizeX,(iniY+j)*sizeY,sizeX,sizeY);
//                        break;
//                    case 1:
//                        break;
//                    case 2:
//                        break;
//                    case 3:
//                        break;
//                }
//                //Log.i("imgs","get imagenes: "+j+":"+i);
//            }
//        }
//    }

//    public int redimensionarRect(){
//
//    }
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawColor(Color.BLACK);
        //JUEGO
        c.drawBitmap(mapa,x,y,null);

        c.drawBitmap(heartFull,0,0,null);
        c.drawBitmap(heartFull,heartFull.getWidth()+getPixels(1.5f),0,null);
        c.drawBitmap(heartFull,(heartFull.getWidth()+getPixels(1.5f))*2,0,null);

        c.drawBitmap(dibujoNick[frame],anchoPantalla/2,altoPantalla/2,null);
        c.drawBitmap(dibujoJormunand[frame],jormunand.posX,jormunand.posY,null);
        c.drawRect(nick.hitboxNick,paint);
        c.drawRect(jormunand.jormunandHitbox,paint);

        c.drawBitmap(imgCruceta,cruceX,cruceY,null);
        c.drawBitmap(imgAttack,anchoPantalla-imgAttack.getWidth(),altoPantalla-imgAttack.getHeight(),null);
        c.drawRect(izquierda,paint);
        c.drawRect(arriba,paint);
        c.drawRect(derecha,paint);
        c.drawRect(abajo,paint);
        c.drawRect(ataque,paint);


    }

    @Override
    public void actualizarFisica() {
        super.actualizarFisica();
        if(System.currentTimeMillis()-tiempo>tick){
            frame++;
            if(frame>=dibujoNick.length){
                frame=0;
            }
            if(frame>=dibujoJormunand.length){
                frame=0;
            }
            tiempo=System.currentTimeMillis();
        }


        if (arr) {y+=distanciaPaso;jormunand.posY+=distanciaPaso;};
        if (aba) {y-=distanciaPaso;jormunand.posY-=distanciaPaso;};
        if (der) {x-=distanciaPaso;jormunand.posX-=distanciaPaso;};
        if (izz) {x+=distanciaPaso;jormunand.posX+=distanciaPaso;};


    }

    public void paro(){
        der=false;
        izz=false;
        aba=false;
        arr=false;
    }


    @Override
    public int onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                if(izquierda.contains((int) event.getX(),(int) event.getY())){
                    paro();
                    izz=true;
                    dibujoNick=nick.iz;
                    if(nick.getX()-distanciaPaso<=0){

                    }else{
                        nick.setX(nick.getX()-distanciaPaso);

                    }
                }else if(derecha.contains((int) event.getX(),(int) event.getY())){
                    dibujoNick=nick.de;
                    paro();
                    der=true;
                }else if(arriba.contains((int) event.getX(),(int) event.getY())){
                    dibujoNick=nick.ar;
                    paro();
                    arr=true;
                    nick.setY(nick.getY()-50);
                }else if(abajo.contains((int) event.getX(),(int) event.getY())){
                    dibujoNick=nick.ab;
                    paro();
                    aba=true;
                }
                break;
                case MotionEvent.ACTION_UP:
                    paro();
                    break;
        }
        return numeroEscena;
    }
}
