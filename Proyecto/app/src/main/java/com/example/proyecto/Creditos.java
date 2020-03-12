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

/**
 * La escena de creditos donde se ven los agradecimientos y licencias de las personas volcadas en la app.
 */
public class Creditos extends Escena {
    //el lapiz con el que excribimos.
    TextPaint textPaint;
    //los layouts donde se almacen los distintos agradecimientos o licencias.
    StaticLayout layoutJor, layoutIcono, layoutGolem, layoutNick;

    /**
     * Inicializa las propiedades a parametros.
     * @param numeroEscena
     * @param fondo
     * @param context
     * @param anchoPantalla
     * @param altoPantalla
     */
    public Creditos(int numeroEscena, Bitmap fondo, Context context, int anchoPantalla, int altoPantalla) {
        super(numeroEscena, fondo, context, anchoPantalla, altoPantalla);

        textPaint = new TextPaint();
        textPaint.setTypeface(faw);
        textPaint.setTextSize(30);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setFakeBoldText(true);


        layoutJor = new StaticLayout("\"Red Dragon\" " + context.getResources().getString(R.string.por) + " AntumDeluge " + context.getResources().getString(R.string.licencia) + " CCBY 3.0 : https://opengameart.org/content/flying-dragon-rework"
                , textPaint, anchoPantalla / 2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        layoutIcono = new StaticLayout("\"Wyvern Icon\" " + context.getResources().getString(R.string.por) + " Lorc under " + context.getResources().getString(R.string.licencia) + " CCBY 3.0 : https://game-icons.net/1x1/lorc/wyvern.html"
                , textPaint, anchoPantalla / 2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        layoutGolem = new StaticLayout("\"Golem\" " + context.getResources().getString(R.string.por) + " William.Thompsonj " + context.getResources().getString(R.string.licencia) + " GPL 3.0 : https://opengameart.org/content/lpc-golem"
                , textPaint, anchoPantalla / 2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        layoutNick = new StaticLayout("\"Universal LPC Male\" " + context.getResources().getString(R.string.por) + " Gaurav Mujal " + context.getResources().getString(R.string.licencia) + " GPL 3.0 : https://opengameart.org/content/universal-lpc-sprite-male-01"
                , textPaint, anchoPantalla / 2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

    }

    /**
     * Metodo que dibuja los agradecimientos y licencias de las personas que apartaron a la app.
     * @param c
     */
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);

        c.drawText("Jormunand", anchoPantalla / 2, (altoPantalla / 12*1), lapiz);

        c.save();
        c.translate(anchoPantalla / 4, (altoPantalla / 12 * 2));
        layoutJor.draw(c);
        c.restore();

        c.drawText("Icono", anchoPantalla / 2, (altoPantalla / 12 * 4), lapiz);

        c.save();
        c.translate(anchoPantalla / 4, (altoPantalla / 12 * 5));
        layoutIcono.draw(c);
        c.restore();

        c.drawText("Golem", anchoPantalla / 2, (altoPantalla / 12 * 7), lapiz);

        c.save();
        c.translate(anchoPantalla / 4, (altoPantalla / 12 * 8));
        layoutGolem.draw(c);
        c.restore();

        c.drawText("Nick", anchoPantalla / 2, (altoPantalla / 12 * 10), lapiz);

        c.save();
        c.translate(anchoPantalla / 4, (altoPantalla / 12 * 11));
        layoutNick.draw(c);
        c.restore();


    }

    /**
     * Evento que cambia de escena segun la opcion seleccionada.
     * @param event
     * @return
     */
    @Override
    public int onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (atras.contains((int) event.getX(), (int) event.getY())) {
                    return 1;
                }
                break;
        }
        return numeroEscena;
    }
}
