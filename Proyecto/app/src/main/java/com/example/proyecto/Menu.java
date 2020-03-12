package com.example.proyecto;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;


/**
 * Clase del menu principal del juego
 */
public class Menu extends Escena {
    //los botones de juego,opciones,HallofFame,creditos,tutorial y teclado. respectivamente.
    Rect juego,opciones,salon,creditos,tutorial,rectTeclado;
    //Imagen del teclado.
    Bitmap teclado;
    //Booleana que indica si se va a escribir el nombre para el hall-of-fame.
    boolean isWritten=false;
    //
    Rect[] keyboardAbajo= new Rect[7];

    /**
     * Inicializa las propiedades a parametros tanto de la clase heredada como la propia clase.
     * @param numeroEscena
     * @param fondo
     * @param context
     * @param anchoPantalla
     * @param altoPantalla
     * @param activity
     */
    public Menu(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla, Activity activity) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);

        teclado=escalaAltura(getBitmapFromAssets("toolPencil.png"),altoPantalla/8);
        juego= new Rect(anchoPantalla/6*2,0,anchoPantalla/6*4,altoPantalla/6*1);
        rectTeclado=new Rect(anchoPantalla-teclado.getWidth(),0,anchoPantalla,teclado.getHeight());
        opciones= new Rect(juego.left,juego.bottom+getPixels(20),juego.right,altoPantalla/6*2+getPixels(20));
        salon= new Rect(juego.left,opciones.bottom+getPixels(10),juego.right,altoPantalla/6*3+getPixels(20));
        creditos= new Rect(juego.left,salon.bottom+getPixels(10),juego.right,altoPantalla/6*4+getPixels(20));
        tutorial= new Rect(juego.left,creditos.bottom+getPixels(10),juego.right,altoPantalla/6*5+getPixels(20));
        generarTecladoAbajo();

    }

    /**
     * Rellena un array de Rect[] de tamaño 7.
     */
    public void generarTecladoAbajo(){
        Rect rect=new Rect(0,altoPantalla/7*6,anchoPantalla/7*1,altoPantalla);
        keyboardAbajo[0]=rect;
        for (int i=1;i>7;i++){
             rect= new Rect(rect.right,rect.top,rect.right+anchoPantalla/7*1,altoPantalla);
             keyboardAbajo[i]=rect;
        }
    }

    /**
     * Cambia de escena segun la opcion seleccionada.
     * @param event
     * @return
     */
    public int onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                if(!isWritten){
                    if(juego.contains((int) event.getX(),(int) event.getY())){
                        return 2;
                    }if(opciones.contains((int) event.getX(),(int) event.getY())){
                        return  3;
                    }if(salon.contains((int) event.getX(),(int) event.getY())){
                        return  4;
                    }if(creditos.contains((int) event.getX(),(int) event.getY())){
                        return 5;
                    }if(tutorial.contains((int) event.getX(),(int) event.getY())){
                        return 6;
                    }if(rectTeclado.contains((int) event.getX(),(int) event.getY())){
                        isWritten=true;
                    }
                }else{
                    if(rectTeclado.contains((int) event.getX(),(int) event.getY())){
                        isWritten=false;
                    }
                }
        }
        return numeroEscena;

    }

    /**
     * Dibuja los componentes propios de la escena.
     * @param c
     */
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        if(!isWritten){
            c.drawRect(juego,paint);
            c.drawRect(opciones,paint);
            c.drawRect(salon,paint);
            c.drawRect(creditos,paint);
            c.drawRect(tutorial,paint);

            c.drawText(context.getResources().getString(R.string.Juego),anchoPantalla/2,altoPantalla/6*1,lapiz);
            c.drawText(context.getResources().getString(R.string.Opciones),anchoPantalla/2,altoPantalla/6*2,lapiz);
            c.drawText(context.getResources().getString(R.string.FAME),anchoPantalla/2,altoPantalla/6*3,lapiz);
            c.drawText(context.getResources().getString(R.string.CREDITOS),anchoPantalla/2,altoPantalla/6*4,lapiz);
            c.drawText(context.getResources().getString(R.string.TUTORIAL),anchoPantalla/2,altoPantalla/6*5,lapiz);
        }else{


        }


        c.drawRect(rectTeclado,paint);
        c.drawBitmap(teclado,anchoPantalla-teclado.getWidth(),0,null);



    }

}
