package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;
enum Direccion{
    derecha,izquierda,arriba,abajo
}

public class Juego extends Escena {
    Direccion direccion=Direccion.izquierda;
    Jormunand jormunand;
    LibroIce libroIce;
    Nick nick;
    Bitmap[] dibujoNick,dibujoJormunand;
    Bitmap imagenes_Jormunand,imagenes_Nick,imgCruceta,imgAttack,mapa,heartFull,heartEmpty,bookFrozen,imagenes_attack;
    Rect izquierda,derecha,arriba,abajo,ataque;
    int tick=200,distanciaPaso;
    long tiempo=0;
    int frame=0;
    int x,y;

    boolean arr=false, aba=false, der=false, izz=false;
    int cruceY, cruceX,posicionXBook,posicionYBook;


    boolean ciz=false,cde=false,car=false,cab=false;
    int pasoColision=10;
    int tickColision=10;
    long tcoli=0, tinicoli=0;
    int ttotal=500;

    public Juego(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);
        //Sprite Jormunand
        imagenes_Jormunand=getBitmapFromAssets("flying_dragon-red.png");
        //Sprite Nick
        imagenes_Nick=getBitmapFromAssets("nick.png");
        //Sprite nick attack
        imagenes_attack=getBitmapFromAssets("sprite-nick-attack.png");
        //Sprite HeartFull
        heartFull=getBitmapFromAssets("heart-full.png");
        heartFull=escalaAltura(heartFull,altoPantalla/10);
        //Sprite HeartEmpty
        heartEmpty=getBitmapFromAssets("heart-empty.png");
        heartEmpty=escalaAltura(heartEmpty,altoPantalla/10);
        //BookFrozen
        bookFrozen=getBitmapFromAssets("bookice.png");
        bookFrozen=escalaAnchura(bookFrozen,anchoPantalla/15);

        distanciaPaso=getPixels(5);
        x=anchoPantalla*-2;
        y=altoPantalla*-2;

        posicionXBook=(int)(Math.random()*(x-1));
        posicionYBook=(int)(Math.random()*(y-1));

        libroIce=new LibroIce(bookFrozen,posicionXBook,posicionYBook,(heartFull.getWidth()+getPixels(1.5f))*3);

        //Mapa
        mapa=getBitmapFromAssets("map.jpg");
        mapa=escalaAnchura(mapa,anchoPantalla*4);

        imgCruceta=getBitmapFromAssets("dpad.png");
        imgCruceta=escalaAltura(imgCruceta,altoPantalla/4);
        imgCruceta=escalaAnchura(imgCruceta,anchoPantalla/4);

        float porSize1=(2000f/72f)/100f;
        float porSize2=(3200f/72f)/100f;


        imgAttack=getBitmapFromAssets("attack.png");
        imgAttack=escalaAltura(imgAttack,altoPantalla/5);
        imgAttack=escalaAnchura(imgAttack,anchoPantalla/5);

//        int posYR=altoPantalla/28*20;

        cruceY=altoPantalla-imgCruceta.getHeight();
        cruceX=0;




        izquierda=new Rect(cruceX,(int)(cruceY+porSize1*imgCruceta.getHeight()),cruceX+(int)(porSize1*imgCruceta.getWidth()),
                cruceY+imgCruceta.getHeight()-(int)(porSize1*imgCruceta.getHeight()));

        arriba= new Rect(cruceX+(int)(porSize1*imgCruceta.getWidth()),cruceY,cruceX+(int)(porSize1*imgCruceta.getWidth())+(int)(porSize2*imgCruceta.getWidth()),(int)(cruceY+porSize1*imgCruceta.getHeight()));

        derecha= new Rect(cruceX+(int)(porSize1*imgCruceta.getWidth())+(int)(porSize2*imgCruceta.getWidth()),
                (int)(cruceY+porSize1*imgCruceta.getHeight()),cruceX+imgCruceta.getWidth(),cruceY+imgCruceta.getHeight()-(int)(porSize1*imgCruceta.getHeight()));

        abajo= new Rect(cruceX+(int)(porSize1*imgCruceta.getWidth()),cruceY+imgCruceta.getHeight()-(int)(porSize1*imgCruceta.getHeight()),
                cruceX+(int)(porSize1*imgCruceta.getWidth())+(int)(porSize2*imgCruceta.getWidth()),cruceY+imgCruceta.getHeight());

        ataque= new Rect(anchoPantalla-imgAttack.getWidth(),altoPantalla-imgAttack.getHeight(),anchoPantalla,altoPantalla);

        jormunand= new Jormunand(context,imagenes_Jormunand,altoPantalla,anchoPantalla,0,0,3,4,3,4,200,200,3);
        nick=new Nick(context,imagenes_Nick,altoPantalla,anchoPantalla,0,0,3,4,3,4,anchoPantalla/2,altoPantalla/2,4,imagenes_attack);


    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawColor(Color.BLACK);
        //JUEGO
        c.drawBitmap(mapa,x,y,null);
        switch(nick.getVidas()){
            case 3:
                c.drawBitmap(heartFull,0,0,null);
                c.drawBitmap(heartFull,heartFull.getWidth()+getPixels(1.5f),0,null);
                c.drawBitmap(heartFull,(heartFull.getWidth()+getPixels(1.5f))*2,0,null);
                break;
            case 2:
                c.drawBitmap(heartFull,0,0,null);
                c.drawBitmap(heartFull,heartFull.getWidth()+getPixels(1.5f),0,null);
                c.drawBitmap(heartEmpty,(heartFull.getWidth()+getPixels(1.5f))*2,0,null);
                break;
            case 1:
                c.drawBitmap(heartFull,0,0,null);
                c.drawBitmap(heartEmpty,heartFull.getWidth()+getPixels(1.5f),0,null);
                c.drawBitmap(heartEmpty,(heartFull.getWidth()+getPixels(1.5f))*2,0,null);
            case 0:
                c.drawBitmap(heartEmpty,0,0,null);
                c.drawBitmap(heartEmpty,heartFull.getWidth()+getPixels(1.5f),0,null);
                c.drawBitmap(heartEmpty,(heartFull.getWidth()+getPixels(1.5f))*2,0,null);
                break;
                //AQUI SE ACABA EL JUEGO
        }


        nick.dibujar(c);
        jormunand.dibujar(c);

        if(!nick.spellFrozen){
            libroIce.dibujar(c,paint);
        }else{
            libroIce.dibujarHUD(c);
        }
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
        jormunand.actualizaFisica();
        nick.actualizaFisica();
        if(libroIce.heatbox!=null){
            if(nick.clonaRect().intersect(libroIce.heatbox)){
                nick.spellFrozen=true;
                libroIce.removeBook();
            }
        }
        if(jormunand.clonaRect()!=null) {
            if (nick.clonaRect().intersect(jormunand.clonaRect())) {
                tinicoli = System.currentTimeMillis();
                switch (direccion) {
                    case izquierda:
                        if (!ciz)nick.setVidas(nick.getVidas() - 1);
                        ciz=true;
                        break;
                    case derecha:
                        if (!cde)nick.setVidas(nick.getVidas() - 1);
                        cde=true;
                        break;
                    case abajo:
                        if (!cab)nick.setVidas(nick.getVidas() - 1);
                        cab=true;
                        break;
                    case arriba:
                        if (!car)nick.setVidas(nick.getVidas() - 1);
                        car=true;
                        break;
                }

            }

        }

        if (ciz || cde || cab || car){
            if (System.currentTimeMillis() -tinicoli>ttotal){
                ciz=false;
                cde=false;
                cab=false;
                car=false;
            }else {
                if (System.currentTimeMillis() - tcoli > tickColision) {
                    if(ciz){
                        x-=distanciaPaso;jormunand.mueveX(-distanciaPaso);libroIce.sumaX(-distanciaPaso);
                    }else if(cde){
                        x+=distanciaPaso;jormunand.mueveX(distanciaPaso);libroIce.sumaX(distanciaPaso);
                    }else if(cab){
                        y+=distanciaPaso;jormunand.mueveY(distanciaPaso);libroIce.sumaY(distanciaPaso);
                    }else if(car){
                        y-=distanciaPaso;jormunand.mueveY(-distanciaPaso);libroIce.sumaY(-distanciaPaso);
                    }

                }
            }

        }

        if(!ciz && !cde && !cab && !car){
            if (arr) {y+=distanciaPaso;jormunand.mueveY(distanciaPaso);libroIce.sumaY(distanciaPaso);}
            if (aba) {y-=distanciaPaso;jormunand.mueveY(-distanciaPaso);libroIce.sumaY(-distanciaPaso);}
            if (der) {x-=distanciaPaso;jormunand.mueveX(-distanciaPaso);libroIce.sumaX(-distanciaPaso);}
            if (izz) {x+=distanciaPaso;jormunand.mueveX(distanciaPaso);libroIce.sumaX(distanciaPaso);}

        }


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
                    direccion=Direccion.izquierda;
                    nick.actual=nick.frames.iz;
                }else if(derecha.contains((int) event.getX(),(int) event.getY())){
                    paro();
                    der=true;
                    direccion=Direccion.derecha;
                    nick.actual=nick.frames.de;
                }else if(arriba.contains((int) event.getX(),(int) event.getY())){
                    paro();
                    arr=true;
                    direccion=Direccion.arriba;
                    nick.actual=nick.frames.ar;
                }else if(abajo.contains((int) event.getX(),(int) event.getY())){
                    paro();
                    aba=true;
                    direccion=Direccion.abajo;
                    nick.actual=nick.frames.ab;
                }

                if(ataque.contains((int) event.getX(),(int) event.getY())){
                    nick.isFigthing=true;
                }
                break;
                case MotionEvent.ACTION_UP:
                    paro();
                    break;
        }
        return numeroEscena;
    }
}
