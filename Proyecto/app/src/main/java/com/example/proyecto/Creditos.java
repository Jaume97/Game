package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;

public class Creditos extends Escena {
    TextPaint textPaint;
    StaticLayout layoutJor, layoutIcono, layoutGolem, layoutNick;
    public Creditos(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);

        textPaint = new TextPaint();
        textPaint.setTypeface(faw);
        textPaint.setTextSize(30);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setFakeBoldText(true);

//        lapiz.setTextSize(90);

        layoutJor = new StaticLayout("\"Red Dragon\" " + context.getResources().getString(R.string.por) + " AntumDeluge " + context.getResources().getString(R.string.licencia) + " CCBY 3.0 : https://opengameart.org/content/flying-dragon-rework"
                , textPaint, anchoPantalla / 2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        layoutIcono = new StaticLayout("\"Wyvern Icon\" " + context.getResources().getString(R.string.por) + " Lorc under " + context.getResources().getString(R.string.licencia) + " CCBY 3.0 : https://game-icons.net/1x1/lorc/wyvern.html"
                , textPaint, anchoPantalla / 2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        layoutGolem = new StaticLayout("\"Golem\" " + context.getResources().getString(R.string.por) + " William.Thompsonj " + context.getResources().getString(R.string.licencia) + " GPL 3.0 : https://opengameart.org/content/lpc-golem"
                , textPaint, anchoPantalla / 2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        layoutNick = new StaticLayout("\"Universal LPC Male\" " + context.getResources().getString(R.string.por) + " Gaurav Mujal " + context.getResources().getString(R.string.licencia) + " GPL 3.0 : https://opengameart.org/content/universal-lpc-sprite-male-01"
                , textPaint, anchoPantalla / 2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);

        c.drawText("Jormunand", anchoPantalla / 2, (altoPantalla / 12*1), lapiz);

        c.save(); // guardamos el lienzo
        c.translate(anchoPantalla / 4, (altoPantalla / 12 * 2)); // Movemos el lienzo
        layoutJor.draw(c); // Dibujamos el texto
        c.restore(); // Recuperamos el lienzo a su posición original

        c.drawText("Icono", anchoPantalla / 2, (altoPantalla / 12 * 4), lapiz);

        c.save(); // guardamos el lienzo
        c.translate(anchoPantalla / 4, (altoPantalla / 12 * 5)); // Movemos el lienzo
        layoutIcono.draw(c); // Dibujamos el texto
        c.restore(); // Recuperamos el lienzo a su posición original

        c.drawText("Golem", anchoPantalla / 2, (altoPantalla / 12 * 7), lapiz);

        c.save(); // guardamos el lienzo
        c.translate(anchoPantalla / 4, (altoPantalla / 12 * 8)); // Movemos el lienzo
        layoutGolem.draw(c); // Dibujamos el texto
        c.restore(); // Recuperamos el lienzo a su posición original

        c.drawText("Nick", anchoPantalla / 2, (altoPantalla / 12 * 10), lapiz);

        c.save(); // guardamos el lienzo
        c.translate(anchoPantalla / 4, (altoPantalla / 12 * 11)); // Movemos el lienzo
        layoutNick.draw(c); // Dibujamos el texto
        c.restore(); // Recuperamos el lienzo a su posición original


    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (atras.contains((int) event.getX(), (int) event.getY())) {
                    //DEBERIA GUARDAR LAS BOOLEANAS DE VOLUMEN Y VIBRACION AQUI;
                    return 1;
                }
                break;
        }
        return numeroEscena;
    }
}
