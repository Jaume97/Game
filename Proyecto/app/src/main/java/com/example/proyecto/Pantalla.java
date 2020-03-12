package com.example.proyecto;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Clase donde se almacena el control de escenas y el SurfaceView.
 */
public class Pantalla extends SurfaceView implements SurfaceHolder.Callback {
    //Clase con la que realizamos el SurfacesView
    private SurfaceHolder surfaceHolder;
    //el contexto de la actividad
    Context context;
    //booleana con la creamos o destruimos el surfaceView y el hilo.
    boolean funcionando=false;
    //el hilo de la aplicacion
    Hilo hilo;
    //los altos y anchos de pantalla del dispositivo.
    int altoPantalla=0, anchoPantalla=0;
    //El bitmap del fondo del menu principal.
    public  Bitmap fondo;
    //Clase activity inicializa a MainActivity y que se pasa como parametro a menu para modificar la imagen de fondo.
    Activity activity;
    //Clase inicializada con la musica de la app.
    public MediaPlayer mediaPlayer;
    //Clase Escena que contiene la escena que estamos visualizando.
    Escena escenaActual;

    /**
     * Sobreescribe el valor de Fondo por el parametro.
     * @param fondo
     */
    public void setFondo(Bitmap fondo) {
        this.fondo = fondo;
    }
    /**
     * Inicializa algunas propiedades a parametros, la musica, el hilo, y el fondo de las escenas(salvo el juego).
     * @param context
     * @param act
     */
    public Pantalla(Context context, Activity act) {
        super(context);
        this.context=context;
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);

        this.activity=act;
        mediaPlayer= MediaPlayer.create(context,R.raw.storytrack_complete);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(0.3f,0.3f);

        hilo=new Hilo();
        setFocusable(true);

        fondo=getBitmapFromAssets("gran-dragon-mitologia.jpg");
        ((MainActivity)act).getFoto();
    }

    /**
     * Metodo que coje un fichero Bitmap en la carpeta assets
     * @param fichero
     * @return Bitmap escogido o null en caso de error
     */
    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is=getContext().getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    /**
     * Metodo donde se inician los altos y anchos de pantalla, la escena por defecto al iniciar la app y la musica(si esta activada).
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla=width;
        altoPantalla=height;
        escenaActual=new Menu(1,fondo,context,anchoPantalla,altoPantalla,activity);
        if (!funcionando){
            funcionando=true;
            if (hilo.getState() == Thread.State.NEW) {
                hilo.start();
            }

            if (hilo.getState() == Thread.State.TERMINATED) {
                hilo=new Hilo();
                hilo.start();
            }
        }
        if(escenaActual.preferences.getBoolean("volu",true)){
            escenaActual.startMusic(mediaPlayer);
        }

    }

    /**
     * Metodo que destruye el surface view.
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        funcionando=false;
        escenaActual.endMusic(mediaPlayer);
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Evento que cambia de escena segun la opcion seleccionada.
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int nuevaEscena=escenaActual.onTouchEvent(event);
        if (escenaActual.numeroEscena!=nuevaEscena) {
            switch (nuevaEscena) {
                case 1:
                    escenaActual = new Menu(1, fondo, context, anchoPantalla, altoPantalla,activity);
                    break;
                case 2:
                    escenaActual = new Juego(2, fondo, context, anchoPantalla, altoPantalla);
                    break;
                case 3:
                    escenaActual = new Opciones(3, fondo, context, anchoPantalla, altoPantalla, mediaPlayer);
                    break;
                case 4:
                    escenaActual = new Hall_Of_Fame(4, fondo, context, anchoPantalla, altoPantalla);
                    break;
                case 5:
                    escenaActual = new Creditos(5, fondo, context, anchoPantalla, altoPantalla);
                    break;
                case 6:
                    escenaActual = new Tutorial(6, fondo, context, anchoPantalla, altoPantalla);
                    break;
            }
        }
        return true;
    }

    /**
     * Clase donde se inicia el hilo de la app.
     */
    class Hilo extends Thread{
        public Hilo(){

        }
        @Override
        public void run() {
            while (funcionando){
                Canvas c=null;
                try {
                    c = surfaceHolder.lockCanvas();
                    if (c != null) {
                        synchronized (surfaceHolder) {
                            escenaActual.actualizarFisica();
                            escenaActual.dibujar(c);
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    if (c!=null)surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}

