package com.example.proyecto;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.prefs.Preferences;

import static androidx.core.content.PermissionChecker.checkSelfPermission;

public class Pantalla extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    Context context;
    boolean funcionando=false;
    Hilo hilo;
    int altoPantalla=0, anchoPantalla=0;

    public Bitmap getFondo() {
        return fondo;
    }

    public void setFondo(Bitmap fondo) {
        this.fondo = fondo;
    }

    public  Bitmap fondo;
        Bitmap camara;
    Activity activity;
    public MediaPlayer mediaPlayer;
    Escena escenaActual;


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
        camara=getBitmapFromAssets("camera.png");
        ((MainActivity)act).getFoto();

        //fondo=BitmapFactory.decodeFile (((MainActivity)act).sharedPreferences.getString("fondo",""));
//        Log.i("tag", "peta: ");
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

//    public void abrirCamara() {
//        if (checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            String[] permisos = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
//            ActivityCompat.requestPermissions(activity, permisos, 3);
//        }else{
//
//        }
//        Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(fotoIntent.resolveActivity()){
//
//        }
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int nuevaEscena=escenaActual.onTouchEvent(event);
        //Log.i("escena",""+nuevaEscena); //Switch: Crearon todas las escenas y en sus escenas pintar lo necesario
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

