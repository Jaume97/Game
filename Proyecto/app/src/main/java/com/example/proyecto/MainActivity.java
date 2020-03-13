package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Actividad Principal donde se almacena el Layout Pantalla que es el layout de la propia pantalla del dispositivo.
 */
public class MainActivity extends AppCompatActivity {
    //el path de la foto que se saca al principio del juego.
    String pathActualFotos;
    //la propia foto que se saca al principio del juego.
    public static Bitmap foto;
    //Clase donde se almacena el layout del juego y el control de escenas.
    Pantalla pantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView=getWindow().getDecorView();
        int opciones= View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Oculta la barra de navegación
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(opciones);



         pantalla= new Pantalla(this,MainActivity.this);
        pantalla.setKeepScreenOn(true);
        setContentView(pantalla);
        getSupportActionBar().hide();

    }

    @Override
    protected void onResume() {
        super.onResume();

            View decorView=getWindow().getDecorView();
            int opciones= View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Oculta la barra de navegación
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(opciones);
        getSupportActionBar().hide();





    }

    /**
     * Metodo que devuelve una imagen(File) capturada por la camara del dispositivo
     * @return File, la imagen capturada
     * @throws IOException
     */
    private File creaNombreUnicoArchivoImagen() throws IOException {
        String formatoFecha=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreImagen="jpg_" + formatoFecha + "_";
        File directorio= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagen=File.createTempFile(nombreImagen, ".jpg", directorio);
        pathActualFotos = imagen.getAbsolutePath(); // Path para el recoger luego el archivo
        Log.i("Path fotos",pathActualFotos);
        return imagen;
    }

    /**
     * Metodo en el que activa la camara del dispositivo para capturar una imagen que si la sacas se pone de menu principal
     * sino hay una imagen de menu por defecto.
     */
    public void getFoto(){
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i("TAG", "getFoto: 1");
            String[] permisos = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(MainActivity.this, permisos, 3);
        } else {
            Log.i("TAG", "getFoto: 2");

            Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (fotoIntent.resolveActivity(getPackageManager()) != null) {
                File archivoImagen = null; // Creamos el fichero donde se guardará la foto
                try {
                    archivoImagen = creaNombreUnicoArchivoImagen();
                    if (archivoImagen != null) {
                        Uri photoURI = FileProvider.getUriForFile(this,"com.example.proyecto.fileprovider", archivoImagen);
                        fotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(fotoIntent, 2); // requestCode 2 para foto normal
                    } else {
                        Log.e("ERROR", "No creó el archivo: ");
                    }
                } catch (Exception ex) {
                    Log.e("ERROR", ex.getLocalizedMessage());
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Metodo que se inicia cuando se llama a startActivityForResult y que siempre el requestCode es 3
     * (Asi está codificado) en el que coje la imagen capturada para mandarsela a la clase escena y almacenarla
     * como imagen del menu principal.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap imagen = (Bitmap) data.getExtras().get("data");
            try {
                FileOutputStream fout = openFileOutput("imagen.jpg", MODE_PRIVATE);
                imagen.compress(Bitmap.CompressFormat.JPEG, 80, fout);
                fout.close();
                String[] files = getApplicationContext().fileList();
                for (String file : files) {
                    Log.i("imagen -> ", file);
                }
            } catch (IOException e) {
                Log.e("Error imagen", e.getLocalizedMessage());
            }
        } else if (requestCode == 2 && resultCode == RESULT_OK) { // Foto normal
            if (pathActualFotos!=null) {
                Log.i("TAG", "onActivityResult: no es nulo");
                foto = BitmapFactory.decodeFile(pathActualFotos);
                foto=Bitmap.createScaledBitmap(foto,1920,1080,false);
                pantalla.setFondo(foto);
            } else Log.e("Error", "No hay ruta a la imagen");
        }
    }

}
