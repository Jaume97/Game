package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    public SharedPreferences sharedPreferences;

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

//       PantallaInicioSurface pantallaInicioSurface=new PantallaInicioSurface(this);
//       pantallaInicioSurface.setKeepScreenOn(true);
//       setContentView(pantallaInicioSurface);
        sharedPreferences=getPreferences(Context.MODE_PRIVATE);

        Pantalla pantalla= new Pantalla(this);
        pantalla.setKeepScreenOn(true);
        setContentView(pantalla);
getSupportActionBar().hide();

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
//       PantallaInicioSurface pantallaInicioSurface=new PantallaInicioSurface(this);
//       pantallaInicioSurface.setKeepScreenOn(true);
//       setContentView(pantallaInicioSurface);





    }
}
