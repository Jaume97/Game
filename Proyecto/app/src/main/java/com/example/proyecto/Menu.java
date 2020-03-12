package com.example.proyecto;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static androidx.core.content.ContextCompat.checkSelfPermission;


public class Menu extends Escena {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    Rect juego,opciones,salon,creditos,tutorial;
    Bitmap camara;
    File fileImagen;
    String pathActualFotos=null;
    Activity activity;


    public Menu(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla, Activity activity) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);
        this.camara=escalaAltura(getBitmapFromAssets("camera.png"),altoPantalla/6);
        this.activity=activity;
        juego= new Rect(anchoPantalla/6*2,0,anchoPantalla/6*4,altoPantalla/6*1);
        opciones= new Rect(juego.left,juego.bottom+getPixels(20),juego.right,altoPantalla/6*2+getPixels(20));
        salon= new Rect(juego.left,opciones.bottom+getPixels(10),juego.right,altoPantalla/6*3+getPixels(20));
        creditos= new Rect(juego.left,salon.bottom+getPixels(10),juego.right,altoPantalla/6*4+getPixels(20));
        tutorial= new Rect(juego.left,creditos.bottom+getPixels(10),juego.right,altoPantalla/6*5+getPixels(20));



    }


    public int onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
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
            }
        }
        return numeroEscena;

    }

//    private void funcionPrueba() {
//        File f= new File(Environment.getExternalStorageState(),Environment.DIRECTORY_PICTURES);
//        boolean isCreada=f.exists();
//
//        if(!isCreada){
//            isCreada=f.mkdirs();
//        }
//
//        if(isCreada){
//            Long consecutivo=System.currentTimeMillis()/1000;
//            String nombre=consecutivo.toString()+".jpg";
//
//            pathActualFotos=Environment.getExternalStorageState()+File.separator+Environment.DIRECTORY_PICTURES
//                    +File.separator+nombre;
//
//            fileImagen=new File(pathActualFotos);
//
//            Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagen));
//
//            start
//        }
//    }

//    private File creaNombreUnicoArchivoImagen() throws IOException {
//        String formatoFecha=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String nombreImagen="jpg_" + formatoFecha + "_";
//        File directorio= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        File imagen=File.createTempFile(nombreImagen, ".jpg", directorio);
//        pathActualFotos = imagen.getAbsolutePath(); // Path para el recoger luego el archivo
//        Log.i("Path fotos",pathActualFotos);
//        return imagen;
//    }

//    private void abrirCamara() {
//        if (checkSelfPermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            String[] permisos = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
//            ActivityCompat.requestPermissions(activity, permisos, 3);
//        }else{
//            Intent fotoIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////            if(fotoIntent.resolveActivity(getPackageManager())!=null){
//              File archivoImagen=null;
//
//            try {
//                archivoImagen=creaNombreUnicoArchivoImagen();
//                if(archivoImagen!=null){
//                    Uri photoUri= FileProvider.getUriForFile(context,"",archivoImagen);
//                    fotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
//                    start
//                }
//            } catch (IOException e) {
//                Log.i("foto","error");
//                e.printStackTrace();
//            }
//
////            }
//        }
//    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            AppCompatActivity app = new MainActivity();
            app.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
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

    }

}
