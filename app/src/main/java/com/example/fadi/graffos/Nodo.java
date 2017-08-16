package com.example.fadi.graffos;

/**
 * Created by Diego on 16/08/2017.
 */

public class Nodo {
    private float x,y;
    private boolean selected;
    private int id;

    public Nodo(float x, float y, boolean selected, int id) {
        this.x = x;
        this.y = y;
        this.selected = selected;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void dibujar(int id)
    {

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
