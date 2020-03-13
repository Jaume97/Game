package com.example.proyecto;

import android.graphics.Rect;

public class Tecla {
    Rect tecla;
    String texto;

    public boolean pulsado(int x, int y) {
        if (tecla.contains(x, y)) return true;
        return false;
    }
}
