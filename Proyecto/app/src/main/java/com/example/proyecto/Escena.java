package com.example.proyecto;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
import android.view.MotionEvent;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class Escena {
    //El numero de escena.
    int numeroEscena;
    //el contexto de la actividad.
    Context context;
    //los altos y anchos de pantalla(Ahora inicializados a su valor real)
    int altoPantalla,anchoPantalla;
    //el fondo del menu principal y la imagen de "back".
    Bitmap fondo,home;
    //la clase que inicia las funciones de vibracion
    Vibrator vibrator;
    //clase que almacena la fuente de letra
    Typeface faw;
    //Pincel para pintar y lapiz para escribir.
    Paint lapiz,paint;
    //Boton de atras que retorna la escena Menu
    Rect atras;
    //El fichero de preferencias
    SharedPreferences preferences;
    //El editor del fichero de preferencias.
    SharedPreferences.Editor editor;

    /**
     * Contructor que inicializa las propiedades a parametros,ademas crea dos paints(un lapiz y una brocha),
     * el archivo de preferencias, el vibrador y los botones de la camara y home.
     * @param numeroEscena
     * @param fondo
     * @param context
     * @param anchoPantalla
     * @param altoPantalla
     */
    public Escena(int numeroEscena, Bitmap fondo,Context context, int anchoPantalla, int altoPantalla){
        this.numeroEscena=numeroEscena;
        this.context=context;
        this.anchoPantalla=anchoPantalla;
        this.altoPantalla=altoPantalla;
        this.fondo=Bitmap.createScaledBitmap(fondo,anchoPantalla,altoPantalla,false);


        preferences=context.getSharedPreferences("preferencias",context.MODE_PRIVATE);
        editor=preferences.edit();
        if(!preferences.contains("idioma")){
            editor.putBoolean("idioma",false);
            editor.putBoolean("vibra",true);
            editor.putBoolean("volu",true);
            editor.commit();
        }
        changeLanguage(preferences.getBoolean("idioma",false)?"es":"en");


        vibrator= (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);

        lapiz=new Paint();
        lapiz.setTextSize(90);
        lapiz.setColor(Color.BLACK);
        faw = Typeface.createFromAsset(context.getAssets(), "fonts/Avara.ttf");
        lapiz.setTypeface(faw);
        lapiz.setTextAlign(Paint.Align.CENTER);

        paint= new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(getPixels(2));


        home=escalaAltura(getBitmapFromAssets("back.png"),altoPantalla/6);
        atras= new Rect(0,0,home.getWidth(),home.getHeight());

    }

    /**
     * Coje un fichero Bitmap de la carpeta assets
     * @param fichero
     * @return Bitmap de la carpeta assets
     */
    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is=context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Metodo que escala proporcionalmente de anchura según el segundo parametro
     * el tamaño de un Bitmapm pasado por el parametro.
     * @param res
     * @param nuevoAncho
     * @return Bitmap reescalado.
     */
    public Bitmap escalaAnchura(Bitmap res, int nuevoAncho) {
        Bitmap bitmapAux= res;
        if (nuevoAncho==bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) /
                bitmapAux.getWidth(), true);
    }

    /**
     * Metodo que escala proporcionalmente de altura según el segundo parametro
     * el tamaño de un Bitmap pasado por el primer parametro.
     * @param res
     * @param nuevoAlto
     * @return
     */
    public Bitmap escalaAltura(Bitmap res, int nuevoAlto ) {
        Bitmap bitmapAux=res;
        if (nuevoAlto==bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) /
                bitmapAux.getHeight(), nuevoAlto, true);
    }

    /**
     * Establece el idioma del sistema
     * @param languageCode Codigo del nuevo lenguaje: es, en, gl, ....
     *
     */
    public void changeLanguage(String languageCode) {
        Resources res=context.getResources();
        DisplayMetrics dm=res.getDisplayMetrics();
        android.content.res.Configuration conf=res.getConfiguration();
        conf.locale=new Locale(languageCode.toLowerCase());
        res.updateConfiguration(conf, dm);
    }

    /**
     * Evento de pulsacion que devuelve un valor
     * @param event
     * @return el numero de escena;
     */
    public int onTouchEvent(MotionEvent event){
        return numeroEscena;
    }

    /**
     * Metodo que dibuja sobre la pantalla el fondo si no es la escena juego
     * y el boton de home si no es el juego y la escena de menu.
     * @param c
     */
    public void dibujar(Canvas c){
        if(numeroEscena!=2){
            c.drawBitmap(fondo,0,0,null);
        }
        if(numeroEscena!=2 && numeroEscena!=1){
            c.drawRect(atras,paint);
            c.drawBitmap(home,0,0,null);
        }
    }

    /**
     * Metodo que devuelve el numero de escena.
     * @return
     */
    public int getNumeroEscena() {
        return numeroEscena;
    }

    /**
     * Evento que se genera cada cierto tiempo y que sirve para actualizar el comportamiento de la app(En este caso no hace nada).
     */
    public void actualizarFisica(){

    }

    /**
     * Metodo donde inicia la reproduccion de la musica.
     * @param mediaPlayer
     */
    public void startMusic(MediaPlayer mediaPlayer){
        mediaPlayer.start();
    }

    /**
     * Metodo donde detiene la reproducion de la musica.
     * @param mediaPlayer
     */
    public void endMusic(MediaPlayer mediaPlayer){
        mediaPlayer.stop();
    }

    /**
     * Metodo que devuelve el tamaño de pixeles del numero pasado por el parametro.
     * @param dp
     * @return
     */
    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(). getMetrics(metrics);
        return (int)(dp*metrics.density);
    }

}

