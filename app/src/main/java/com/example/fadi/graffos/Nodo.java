package com.example.fadi.graffos;

/**
 * Created by Diego on 16/08/2017.
 */

public class Nodo {
    private float x, y;
    private int id;

    public Nodo(float x, float y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void dibujar(int id) {

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
