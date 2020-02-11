package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

public class PantallaInicioSurface extends SurfaceView implements SurfaceHolder.Callback {
    int anchoPantalla,altoPantalla;
    boolean esTitulo=true,esJuego,esOpciones,esSalon,esCreditos,esTutorial;
    boolean vibra=true,volu=true;
    Paint paint,lapiz;
    public MediaPlayer mediaPlayer;
    Context contex;
    Hilo hilo;
    Rect juego,opciones,salon,creditos,tutorial,vibracion,volumen,atras,foto;
    SurfaceHolder surfaceHolder;
    boolean funcionando=false;
    Bitmap imagen,home,camara;
    Vibrator vibrator;
    WindowManager windowManager;
    DisplayMetrics metrics;

    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is=getContext().getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }



    int getPixels(float dp) {
        return (int)(dp*metrics.density);
    }

    public PantallaInicioSurface(Context context) {
        super(context);
        this.surfaceHolder=getHolder();
        this.surfaceHolder.addCallback(this);
        this.contex=context;

        metrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealMetrics(metrics);
        anchoPantalla=metrics.widthPixels;
        altoPantalla=metrics.heightPixels;
        Log.i("medidas","ancho:"+anchoPantalla+" alto:"+altoPantalla);

        hilo=new Hilo();
        setFocusable(true);
        imagen=getBitmapFromAssets("gran-dragon-mitologia.jpg");
        home=getBitmapFromAssets("back.png");
        camara=getBitmapFromAssets("camera.png");

        vibrator= (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        mediaPlayer=MediaPlayer.create(contex,R.raw.menutheme);

        paint= new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);

        lapiz= new Paint();
        Typeface faw = Typeface.createFromAsset(context.getAssets(), "fonts/Avara.ttf");

        lapiz.setTypeface(faw);


        juego= new Rect(anchoPantalla/6*1,0,anchoPantalla/6*4,altoPantalla/6*1);
        opciones= new Rect(anchoPantalla/6*1,altoPantalla/6*1,anchoPantalla/6*4,altoPantalla/6*2);
        salon= new Rect(anchoPantalla/6*1,altoPantalla/6*2,anchoPantalla/6*4,altoPantalla/6*3);
        creditos= new Rect(anchoPantalla/6*1,altoPantalla/6*3,anchoPantalla/6*4,altoPantalla/6*4);
        tutorial= new Rect(anchoPantalla/6*1,altoPantalla/6*4,anchoPantalla/6*4,altoPantalla/6*5);

        vibracion=new Rect(anchoPantalla/6*1,0,anchoPantalla/6*4,altoPantalla/6*1);
        volumen= new Rect(anchoPantalla/6*1,altoPantalla/6*2,anchoPantalla/6*4,altoPantalla/6*3);

        atras= new Rect(0,0,anchoPantalla/8*1,altoPantalla/7*1);
        foto= new Rect(anchoPantalla/8*7,0,anchoPantalla,altoPantalla/7*1);
    }
    public void actualizarFisica(){

    }

    public void dibujar(Canvas c){
        try {
            c.drawBitmap(imagen,0,0,null);


            if(esTitulo){
                paint.setColor(Color.BLUE);
                c.drawRect(juego,paint);

                paint.setColor(Color.GREEN);
                c.drawRect(opciones,paint);

                paint.setColor(Color.YELLOW);
                c.drawRect(salon,paint);

                paint.setColor(Color.BLACK);
                c.drawRect(creditos,paint);

                paint.setColor(Color.RED);
                c.drawRect(tutorial,paint);

                c.drawRect(foto,paint);
                c.drawBitmap(camara,getPixels(600),0,null);

                lapiz.setTextSize(90);
                lapiz.setColor(Color.BLACK);
                c.drawText("JUEGO",anchoPantalla/6*1,altoPantalla/6*1,lapiz);
                c.drawText("OPCIONES",anchoPantalla/6*1,altoPantalla/6*2,lapiz);
                c.drawText("SALON DE LA FAMA",anchoPantalla/6*1,altoPantalla/6*3,lapiz);
                c.drawText("CREDITOS",anchoPantalla/6*1,altoPantalla/6*4,lapiz);
                c.drawText("TUTORIAL",anchoPantalla/6*1,altoPantalla/6*5,lapiz);
            }else{
                c.drawRect(atras,paint);
                c.drawBitmap(home,0,0,null);
            }
            if(esOpciones){
                paint.setColor(Color.BLUE);
                c.drawRect(vibracion,paint);

                paint.setColor(Color.BLACK);
                c.drawRect(volumen,paint);

                if(vibra){
                    c.drawText("VIBRACIÓN: ON",anchoPantalla/6*1,altoPantalla/6*1,lapiz);
                }else{
                    c.drawText("VIBRACIÓN: OFF",anchoPantalla/6*1,altoPantalla/6*1,lapiz);
                }

                if(volu){
                    c.drawText("VOLUMEN: ON",anchoPantalla/6*1,altoPantalla/6*3,lapiz);
                }else{
                    c.drawText("VOLUMEN: OFF",anchoPantalla/6*1,altoPantalla/6*3,lapiz);
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        hilo.setFuncionando(true);
        if(hilo.getState()==Thread.State.NEW){
            hilo.start();

        }
        if(hilo.getState()==Thread.State.TERMINATED){
            hilo=new Hilo();
            hilo.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla=width;
        altoPantalla=height;
        hilo.setSurfaceSize(width,height);
        if(volu){
            mediaPlayer.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hilo.setFuncionando(false);
        mediaPlayer.stop();
        try {
            hilo.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (surfaceHolder){
            int accion=event.getAction();
            float x=event.getX();
            float y=event.getY();

            switch (accion){
                case MotionEvent.ACTION_UP:
                    if(esTitulo){
                        if(juego.contains((int)x,(int)y)){
                            esJuego=true;
                            esTitulo=false;

                        }else if(opciones.contains((int)x,(int)y)){
                            esOpciones=true;
                            esTitulo=false;
                        }else if(salon.contains((int)x,(int)y)){
                            esSalon=true;
                            esTitulo=false;
                        }else if(creditos.contains((int)x,(int)y)){
                            esCreditos=true;
                            esTitulo=false;
                        }else if(tutorial.contains((int)x,(int)y)){
                            esTutorial=true;
                            esTitulo=false;
                        }else if(foto.contains((int)x,(int)y)){

                            //EVENTO DE SACAR UNA FOTO
                        }
                    }else{
                        //Opciones
                        if(esOpciones){
                            if(vibracion.contains((int)x,(int)y)){
                                vibra=!vibra;
                                if (vibra){
                                    vibrator.vibrate(300);
                                }
                            }else{
                                if(volumen.contains((int)x,(int)y)){
                                    volu=!volu;
                                }
                            }
                            //Volumen ON OFF
                            if(volu){
                                mediaPlayer.start();
                            }else{
                                mediaPlayer.pause();

                            }
                            //back
                            if(atras.contains((int)x,(int)y)){
                                esOpciones=false;
                                esTitulo=true;
                            }
                        }
                        //Creditos
                        if(esCreditos){
                            if(atras.contains((int)x,(int)y)){
                                esCreditos=false;
                                esTitulo=true;
                            }
                        }
                        //Salon
                        if(esSalon){
                            if(atras.contains((int)x,(int)y)){
                                esSalon=false;
                                esTitulo=true;
                            }
                        }

                    }

            }
        }
        return true;
    }

    class Hilo extends Thread{
        public Hilo(){

        }

        @Override
        public void run() {
            while(funcionando){
                Canvas c=null;
                try {
                    if(!surfaceHolder.getSurface().isValid()){
                        continue;
                    }
                    c=surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder){
                        actualizarFisica();
                        dibujar(c);
                    }
                }finally {
                    if(c!=null){
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
        void  setFuncionando(boolean flag){
            funcionando=flag;
        }
        public void setSurfaceSize(int width,int height){
            synchronized (surfaceHolder){
                if(imagen!=null){
                    imagen=Bitmap.createScaledBitmap(imagen,width,height,false);
                    home=Bitmap.createScaledBitmap(home,width/9,height/9,false);
                    camara=Bitmap.createScaledBitmap(camara,width/9,height/8,false);
                }
            }
        }
    }
}
