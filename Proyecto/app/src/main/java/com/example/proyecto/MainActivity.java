package com.example.proyecto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public  SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    String pathActualFotos;
    File fileImagen;
    public static Bitmap foto;
    Pantalla pantalla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        View decorView=getWindow().getDecorView();
        int opciones= View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Oculta la barra de navegación
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(opciones);

        sharedPreferences=getSharedPreferences("imagen",MODE_PRIVATE);
        editor=sharedPreferences.edit();



         pantalla= new Pantalla(this,MainActivity.this);
        pantalla.setKeepScreenOn(true);
        setContentView(pantalla);
        getSupportActionBar().hide();

    }
    public void funcionPrueba() {
        File f= new File(Environment.getExternalStorageState(),Environment.DIRECTORY_PICTURES);
        boolean isCreada=f.exists();

        if(!isCreada){
            isCreada=f.mkdirs();
        }
        Log.i("TAG", "funcionPrueba: 1");
    //    if(isCreada){
            Log.i("TAG", "funcionPrueba: 2");
            Long consecutivo=System.currentTimeMillis()/1000;
            String nombre=consecutivo.toString()+".jpg";

            pathActualFotos=Environment.getExternalStorageState()+File.separator+Environment.DIRECTORY_PICTURES
                    +File.separator+nombre;

            fileImagen=new File(pathActualFotos);

            Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));

            startActivityForResult(intent,1);
//        }
    }


    public void dispatchTakePictureIntent() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] permisos = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(MainActivity.this, permisos, 3);
        }else{
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }


    }

    @Override
    protected void onResume() {
        super.onResume();


            //  setContentView(R.layout.activity_main);
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


    private File creaNombreUnicoArchivoImagen() throws IOException {
        String formatoFecha=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreImagen="jpg_" + formatoFecha + "_";
        File directorio= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagen=File.createTempFile(nombreImagen, ".jpg", directorio);
        pathActualFotos = imagen.getAbsolutePath(); // Path para el recoger luego el archivo
        Log.i("Path fotos",pathActualFotos);
        return imagen;
    }
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      //  ImageView iv = (ImageView) findViewById(R.id.imageView);
        if (requestCode == 1 && resultCode == RESULT_OK) { // Thumbnail
            Bitmap imagen = (Bitmap) data.getExtras().get("data");
           // iv.setImageBitmap(imagen);
            try { // Guardo la imagen
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
                pantalla.setFondo(foto);
//                Log.i("tamaño",imagen.getWidth()+":"+imagen.getHeight());
               // iv.setImageBitmap(imagen);
            } else Log.e("Error", "No hay ruta a la imagen");
        }
    }

}
