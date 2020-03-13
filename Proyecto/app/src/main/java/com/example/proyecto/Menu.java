package com.example.proyecto;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
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

    String[][] letras={
            {"q","w","e","r","t","y","u","i","o","p","<"},
            {"a","s","d","f","g","h","j","k","l","ñ","ok"},
            {"z","x","c","v","b","n","m",",",".","-","_"},
    };

    Rect[][] teclas;
    Paint lapizTeclado;

    Tecla[][] arr;
    String nombreUsuario="";

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

        lapizTeclado= new Paint();
        lapizTeclado.setTextSize(70);
        lapizTeclado.setColor(Color.BLACK);
        lapizTeclado.setTypeface(faw);
        lapizTeclado.setTextAlign(Paint.Align.CENTER);

        teclas=new Rect[letras.length][];
        arr=new Tecla[letras.length][];
        for (int i = 0; i <letras.length ; i++) {
            teclas[i]=new Rect[letras[i].length];
            arr[i]=new Tecla[letras[i].length];
        }

        int distanciaX=0;
        int distanciaY=3;
        for (int i = 0; i <teclas.length ; i++) {
            for (int j = 0; j <teclas[i].length ; j++) {
//                Log.i("sddd", i+" "+j+"  JuegoView: "+letras[i][j]);
                // aqui creo el objeto tecla: el rect en función del ancho de pantalla y del tamaño de teclas[i].length (nº de teclas por linea)
                // luego con el mismo bucle en dibujar dibujo el objeto tecla
                // y en onTouch controlo para cada tecla si es pulsada, y según la tecla que sea hago una cosa y otra
                Tecla t= new Tecla();
                t.tecla=new Rect(anchoPantalla/letras[i].length*distanciaX,altoPantalla/letras[i].length*(letras[i].length-distanciaY),anchoPantalla/letras[i].length*(distanciaX+1),altoPantalla/letras[i].length*((letras[i].length-distanciaY)+1));
//                t.texto=letras[i][j];

                Log.i("cor", "left: "+t.tecla.left+" rigth : "+t.tecla.right+" anchopantalla: "+anchoPantalla);
                teclas[i][j]=t.tecla;
                t.texto=letras[i][j];
                arr[i][j]=t;
                //const
                distanciaX++;
            }
            distanciaX=0;
            distanciaY--;
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
                        nombreUsuario="";
                    }
                    //evento de pulsacion de teclado.
                    for (int i = 0; i <teclas.length ; i++) {
                        for (int j = 0; j <teclas[i].length ; j++) {
                            if(arr[i][j].tecla.contains((int) event.getX(),(int) event.getY())){
//                                Log.i("pulsa", "onTouchEvent: "+arr[i][j].texto);
                                if(!arr[i][j].texto.equals("<") && !arr[i][j].texto.equals("ok")){
                                    nombreUsuario+=arr[i][j].texto;
                                }else{
                                    Log.i("paso", "onTouchEvent: ");
                                    if(arr[i][j].texto.equals("<")){

                                        char[] nuevoUsu=new char[nombreUsuario.length()];

                                        for (int k = 0; k < nombreUsuario.length(); k++) {
                                            nuevoUsu[k]=nombreUsuario.charAt(k);
                                        }

                                        nombreUsuario="";

                                        for (int x = 0; x < nuevoUsu.length-1; x++) {
                                            nombreUsuario+=nuevoUsu[x];
                                        }

                                    }else{
                                        isWritten=false;
                                        Log.i("nombreUsu", ""+nombreUsuario);
                                        editor.putString("posibleRecord",nombreUsuario);
                                        editor.commit();
                                    }
                                }


                            }
                        }
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
            for (int i = 0; i <teclas.length ; i++) {
                for (int j = 0; j <teclas[i].length ; j++) {
                    Log.i("sddd", i+" "+j+"  JuegoView: "+letras[i][j]);
                    c.drawRect(teclas[i][j],paint);
                    c.drawText(letras[i][j],teclas[i][j].left+getPixels(20),teclas[i][j].bottom-getPixels(5),lapizTeclado);

                }
            }

            c.drawText(nombreUsuario,anchoPantalla/2,altoPantalla/2,lapiz);

        }


        c.drawRect(rectTeclado,paint);
        c.drawBitmap(teclado,anchoPantalla-teclado.getWidth(),0,null);



    }

}
