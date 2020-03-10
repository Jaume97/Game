package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;


public class Juego extends Escena {
    Jormunand jormunand;
    LibroIce libroIce;
    Nick nick;
    Esqueleto esqueleto;
    Bitmap[] fire={escalaAltura(getBitmapFromAssets("flame_0_fixed.png"),altoPantalla/8),escalaAltura(getBitmapFromAssets("flame_1_fixed.png"),
            altoPantalla/8),escalaAltura(getBitmapFromAssets("flame_2_fixed.png"),altoPantalla/8)};

    Bitmap[] frost={escalaAltura(getBitmapFromAssets("frost_0.png"),altoPantalla/14),escalaAnchura(getBitmapFromAssets("frost_1.png"),
            anchoPantalla/25)};

    Bitmap[] cloudFire={escalaAnchura(getBitmapFromAssets("cloud_fire_0.png"),anchoPantalla/5),escalaAnchura(getBitmapFromAssets("cloud_fire_1.png"),anchoPantalla/5),
            escalaAnchura(getBitmapFromAssets("cloud_fire_2.png"),anchoPantalla/5),escalaAnchura(getBitmapFromAssets("cloud_fire_3.png"),anchoPantalla/5),
            escalaAnchura(getBitmapFromAssets("cloud_fire_2.png"),anchoPantalla/5),escalaAnchura(getBitmapFromAssets("cloud_fire_1.png"),anchoPantalla/5),
            escalaAnchura(getBitmapFromAssets("cloud_fire_0.png"),anchoPantalla/5)};

    Bitmap[] cloudIce={escalaAnchura(getBitmapFromAssets("cloud_cold_0.png"),anchoPantalla/5),escalaAnchura(getBitmapFromAssets("cloud_cold_1.png"),anchoPantalla/5),
            escalaAnchura(getBitmapFromAssets("cloud_cold_2.png"),anchoPantalla/5),escalaAnchura(getBitmapFromAssets("cloud_cold_1.png"),anchoPantalla/5),
            escalaAnchura(getBitmapFromAssets("cloud_cold_0.png"),anchoPantalla/5)};
    Bitmap imagenes_Jormunand,imagenes_Nick,imgCruceta,imgAttack,mapa,heartFull,heartEmpty,bookFrozen,imagenes_attack,imagen_zombieAndEsqueleto;
    Rect izquierda,derecha,arriba,abajo,ataque,juegoFinal;
    int tick=200,distanciaPaso;
    long tiempo=0;
    int frame=0;
    int x,y;

    boolean arr=false, aba=false, der=false, izz=false;
    int cruceY, cruceX,posicionXBook,posicionYBook;

    boolean ciz=false,cde=false,car=false,cab=false;
    boolean isHitting=false,gameEnd=false,uDie=false;
    int pasoColision=10;
    int tickColision=10;
    long tcoli=0, tinicoli=0;
    int ttotal=500;

    Spell spell;
    public Juego(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);

        gameEnd=false;
        //Sprite Jormunand
        imagenes_Jormunand=getBitmapFromAssets("flying_dragon-red.png");
        //Sprite Nick
        imagenes_Nick=getBitmapFromAssets("nick.png");
        //Sprite nick attack
        imagenes_attack=getBitmapFromAssets("frames-ataque-fixed.png");
        //Sprite HeartFull
        heartFull=getBitmapFromAssets("heart-full.png");
        heartFull=escalaAltura(heartFull,altoPantalla/10);
        //Sprite HeartEmpty
        heartEmpty=getBitmapFromAssets("heart-empty.png");
        heartEmpty=escalaAltura(heartEmpty,altoPantalla/10);
        //BookFrozen
        bookFrozen=getBitmapFromAssets("bookice.png");
        bookFrozen=escalaAnchura(bookFrozen,anchoPantalla/15);
        //Sprite Zombie y Esqueleto
        imagen_zombieAndEsqueleto=getBitmapFromAssets("golem_walk_fixed.png");

        distanciaPaso=getPixels(5);
        x=anchoPantalla*-2;
        y=altoPantalla*-2;



        libroIce=new LibroIce(bookFrozen,-1000,-1000,(heartFull.getWidth()+getPixels(1.5f))*3);

        //Mapa
//        mapa=getBitmapFromAssets("map.jpg");
        mapa=getBitmapFromAssets("mapa.png");
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


        juegoFinal=new Rect(anchoPantalla/10*3,altoPantalla/10*3,anchoPantalla/10*7,altoPantalla/10*7);

        izquierda=new Rect(cruceX,(int)(cruceY+porSize1*imgCruceta.getHeight()),cruceX+(int)(porSize1*imgCruceta.getWidth()),
                cruceY+imgCruceta.getHeight()-(int)(porSize1*imgCruceta.getHeight()));

        arriba= new Rect(cruceX+(int)(porSize1*imgCruceta.getWidth()),cruceY,cruceX+(int)(porSize1*imgCruceta.getWidth())+(int)(porSize2*imgCruceta.getWidth()),(int)(cruceY+porSize1*imgCruceta.getHeight()));

        derecha= new Rect(cruceX+(int)(porSize1*imgCruceta.getWidth())+(int)(porSize2*imgCruceta.getWidth()),
                (int)(cruceY+porSize1*imgCruceta.getHeight()),cruceX+imgCruceta.getWidth(),cruceY+imgCruceta.getHeight()-(int)(porSize1*imgCruceta.getHeight()));

        abajo= new Rect(cruceX+(int)(porSize1*imgCruceta.getWidth()),cruceY+imgCruceta.getHeight()-(int)(porSize1*imgCruceta.getHeight()),
                cruceX+(int)(porSize1*imgCruceta.getWidth())+(int)(porSize2*imgCruceta.getWidth()),cruceY+imgCruceta.getHeight());

        ataque= new Rect(anchoPantalla-imgAttack.getWidth(),altoPantalla-imgAttack.getHeight(),anchoPantalla,altoPantalla);

        jormunand= new Jormunand(context,imagenes_Jormunand,altoPantalla,anchoPantalla,0,0,3,4,3,4,200,200,3,cloudFire,cloudIce);
        jormunand.isAlive=false;

        nick=new Nick(context,imagenes_Nick,altoPantalla,anchoPantalla,0,0,3,4,3,4,anchoPantalla/2,altoPantalla/2,4,imagenes_attack);

        esqueleto= new Esqueleto(context,imagen_zombieAndEsqueleto,altoPantalla,anchoPantalla,0,0,7,4,7,4,1200,1200,3,cloudFire,cloudIce);
        Log.i("tamaño","PosXMapa:"+x+"");
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
        if(jormunand.isAlive){
            jormunand.dibujar(c);
        }
        if(esqueleto.isAlive){
            esqueleto.dibujar(c);
        }


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

        if(gameEnd){
            if(uDie){
                c.drawText(context.getResources().getString(R.string.Muerte),anchoPantalla/2,altoPantalla/2,lapiz);
            }else{
                c.drawText(context.getResources().getString(R.string.Win),anchoPantalla/2,altoPantalla/2,lapiz);
            }
            c.drawRect(juegoFinal,paint);
        }


    }

    @Override
    public void actualizarFisica() {
        super.actualizarFisica();
        if(!gameEnd){
            if(jormunand.isAlive){
                jormunand.actualizaFisica();
            }
            if(esqueleto.isAlive){
                esqueleto.actualizaFisica();
            }
            nick.actualizaFisica();

            if(libroIce.heatbox!=null){
                if(nick.clonaRect().intersect(libroIce.heatbox)){
                    nick.spellFrozen=true;
                    libroIce.removeBook();
                }
            }
            if(jormunand.clonaRect()!=null || jormunand.clonaRect()!=null) {
                //Deteccion de colisiones cuerpo a cuerpo
                if ((nick.clonaRect().intersect(jormunand.clonaRect()) && jormunand.isAlive)|| (nick.clonaRect().intersect(esqueleto.clonaRect()) && esqueleto.isAlive)) {
                    tinicoli = System.currentTimeMillis();
                    if(preferences.getBoolean("vibra",true)){
                        vibrator.vibrate(100);
                    }

                    switch (nick.direccion) {
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
                    if(nick.getVidas()==0){
                        uDie=true;
                        gameEnd=true;
                    }

                }
                if(nick.isFigthing){
                    if(jormunand.clonaRect().intersect(nick.spell.clonaRect()) && jormunand.isAlive && !isHitting){
                        isHitting=true;
                        if(!nick.spellFrozen){
                            jormunand.isHurtWhitFire=true;
                            jormunand.vidas--;
                        }else{
                            jormunand.isHurtWhitIce=true;
                            jormunand.vidas--;
                        }
                        Log.i("coli","colisionSpell,vidasJor:"+jormunand.vidas);
                        nick.spellHit=true;
                        //MUERTE
                        if(jormunand.vidas==0){
                            jormunand.isAlive=false;
                            gameEnd=true;
                        }
                    }
                    if(esqueleto.clonaRect().intersect(nick.spell.clonaRect()) && esqueleto.isAlive && !isHitting){
                        isHitting=true;
                        nick.spellHit=true;

                        if(!nick.spellFrozen){
                            esqueleto.isHurtWhitFire=true;
                            esqueleto.vidas--;
                        }else{
                            esqueleto.isHurtWhitIce=true;
                            esqueleto.vidas--;
                        }
                        Log.i("coli","colisionSpell,vidasEsqueleto:"+esqueleto.vidas);
                        //MUERTE
                        if(esqueleto.vidas==0){
                            esqueleto.isAlive=false;
                            jormunand.isAlive=true;
                        }
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
                        if(ciz){movimientoPersonajes("mIzquierda");}
                        else if(cde){movimientoPersonajes("mDerecha");}
                        else if(cab){movimientoPersonajes("mAbajo");}
                        else if(car){movimientoPersonajes("mArriba");}
                    }
                }

            }
//
            if(!ciz && !cde && !cab && !car){
                if (arr && y+nick.actual[0].getHeight()/2<0) {movimientoPersonajes("mAbajo");}
                if (aba && y+mapa.getHeight()-nick.actual[0].getHeight()>=altoPantalla) {movimientoPersonajes("mArriba");}
                if (der && x+mapa.getWidth()>=anchoPantalla) {movimientoPersonajes("mIzquierda");}
                if (izz && x+nick.actual[0].getWidth()*2<0) {movimientoPersonajes("mDerecha");}
            }
        }

    }
    public void movimientoPersonajes(String flag){

            switch (flag){
                case "mIzquierda":
                    x-=distanciaPaso;jormunand.mueveX(-distanciaPaso);libroIce.sumaX(-distanciaPaso);esqueleto.mueveX(-distanciaPaso);
                    break;
                case "mDerecha":
                    x+=distanciaPaso;jormunand.mueveX(distanciaPaso);libroIce.sumaX(distanciaPaso);esqueleto.mueveX(distanciaPaso);
                    break;
                case "mAbajo":
                    y+=distanciaPaso;jormunand.mueveY(distanciaPaso);libroIce.sumaY(distanciaPaso);esqueleto.mueveY(distanciaPaso);
                    break;
                case "mArriba":
                    y-=distanciaPaso;jormunand.mueveY(-distanciaPaso);libroIce.sumaY(-distanciaPaso);esqueleto.mueveY(-distanciaPaso);
                    break;
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
        if(!nick.isFigthing){
            switch (event.getAction()){
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_DOWN:
                    if(izquierda.contains((int) event.getX(),(int) event.getY())){
                        paro();
                        izz=true;
                        nick.direccion=Direccion.izquierda;
                        nick.actual=nick.frames.iz;
                    }else if(derecha.contains((int) event.getX(),(int) event.getY())){
                        paro();
                        der=true;
                        nick.direccion=Direccion.derecha;
                        nick.actual=nick.frames.de;
                    }else if(arriba.contains((int) event.getX(),(int) event.getY())){
                        paro();
                        arr=true;
                        nick.direccion=Direccion.arriba;
                        nick.actual=nick.frames.ar;
                    }else if(abajo.contains((int) event.getX(),(int) event.getY())){
                        paro();
                        aba=true;
                        nick.direccion=Direccion.abajo;
                        nick.actual=nick.frames.ab;
                    }

                    if(ataque.contains((int) event.getX(),(int) event.getY())){
                        nick.spellHit=false;
                        nick.isFigthing=true;
                        isHitting=false;
                        spell = new Spell(nick.spellFrozen?frost:fire,anchoPantalla/2,nick.spellFrozen?(altoPantalla/2+getPixels(20)):(altoPantalla/2+getPixels(40)));
                        nick.setSpell(spell);
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    paro();
                    if(juegoFinal.contains((int) event.getX(),(int) event.getY()) && gameEnd){
                        return 1;
                    }
                    break;
            }
        }

        return numeroEscena;
    }
}
