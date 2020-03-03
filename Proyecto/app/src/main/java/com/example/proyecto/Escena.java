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
    int numeroEscena;
    Context context;
    int altoPantalla,anchoPantalla;
    Bitmap fondo,home;
    Vibrator vibrator;
    Typeface faw;
    Paint lapiz,paint;
//    public boolean vibra=true,volu=true,idiomaEspañol=false;
    Rect atras;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
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
    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is=context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }
    public Bitmap escalaAnchura(Bitmap res, int nuevoAncho) {
        Bitmap bitmapAux= res;
        if (nuevoAncho==bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) /
                bitmapAux.getWidth(), true);
    }
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


    public int onTouchEvent(MotionEvent event){
        return numeroEscena;
    }
    public void dibujar(Canvas c){
        if(numeroEscena!=2){
            c.drawBitmap(fondo,0,0,null);
        }
        if(numeroEscena!=2 && numeroEscena!=1){
            c.drawRect(atras,paint);
            c.drawBitmap(home,0,0,null);
        }
    }

    public int getNumeroEscena() {
        return numeroEscena;
    }
    public void actualizarFisica(){

    }
    public void startMusic(MediaPlayer mediaPlayer){
        mediaPlayer.start();
    }
    public void endMusic(MediaPlayer mediaPlayer){
        mediaPlayer.stop();
    }
    public void pauseMusic(MediaPlayer mediaPlayer){
        mediaPlayer.pause();
    }
    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(). getMetrics(metrics);
        return (int)(dp*metrics.density);
    }

}

