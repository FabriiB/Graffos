package com.example.fadi.graffos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    ArrayList<Nodo> circulos = new ArrayList<Nodo>();
    ArrayList<Arista> rayas = new ArrayList<Arista>();
    LinearLayout parent;
    public Button bt1,bt2,bt3;
    public boolean nodo = false, arista = false,aristafinal = false;
    @SuppressLint("WrongViewCast")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent = (LinearLayout)findViewById(R.id.parent);
        View MyView = new MyView(this);
        parent.addView(MyView);
        bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
    }

    public void hacernodo(View view) {
        if(!nodo) {
            nodo = true;
            arista = false;
            changeColor();
        }

        else {
            nodo = false;
            changeColor();
        }
    }


    public void hacerarista(View view) {
        if(!arista) {
            arista = true;
            nodo = false;
            changeColor();
        }
        else {
            arista = false;
            changeColor();
        }
    }

    public void changeColor(){
        if(!nodo){
            bt1.setTextColor(Color.BLACK);
        }
        if(!arista){
            bt2.setTextColor(Color.BLACK);
        }
        if(nodo){
            bt1.setTextColor(Color.RED);
            bt2.setTextColor(Color.BLACK);
        }
        if(arista){
            bt2.setTextColor(Color.RED);
            bt1.setTextColor(Color.BLACK);
        }
    }

    public void clear(View view)
    {
        limpio();
    }

    public void limpio()
    {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        finish();

    }



    class MyView extends View {

        float _x;
        float _y;
        ArrayList<Float> cordX = new ArrayList<Float>();
        ArrayList<Float> cordY = new ArrayList<Float>();
        ArrayList<Float> cordXB = new ArrayList<Float>();
        ArrayList<Float> cordYB = new ArrayList<Float>();
        ArrayList<Float> cordX1 = new ArrayList<Float>();
        ArrayList<Float> cordY1 = new ArrayList<Float>();
        ArrayList<Float> cordX2 = new ArrayList<Float>();
        ArrayList<Float> cordY2 = new ArrayList<Float>();
        Paint paint;



        public MyView (Context context) {
            super(context);
            init();
        }

        public void init(){
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            paint.setTextSize(60);
            paint.setStrokeWidth(10);

        }



        @Override
        public void onDraw(Canvas canvas) {
            int radio = 100;
            super.onDraw(canvas);
            canvas.drawPaint(paint);
            for(int i = 0; i<cordX2.size();i++){
                paint.setColor(Color.BLACK);
                canvas.drawLine(cordX1.get(i),cordY1.get(i),cordX2.get(i),cordY2.get(i),paint);
                paint.setColor(Color.WHITE);
            }

            for(int i = 0; i < cordX.size();i++){
                paint.setColor(Color.parseColor("#CD5C5D"));
                canvas.drawCircle(cordX.get(i),cordY.get(i),radio,paint);
                paint.setColor(Color.WHITE);
                if(!cordXB.isEmpty()){
                    paint.setColor(Color.BLUE);
                    canvas.drawCircle(cordXB.get(0),cordYB.get(0),radio,paint);
                    paint.setColor(Color.WHITE);
                }
                canvas.drawText(String.valueOf(i+1),cordX.get(i)-15,cordY.get(i)+15,paint);
                paint.setColor(Color.WHITE);
            }


        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            switch (e.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if(nodo){
                        _x = e.getX();
                        _y = e.getY();
                        cordX.add(e.getX());
                        cordY.add(e.getY());
                        invalidate();
                    }
                    else if(arista){
                        existe(e.getX(),e.getY());
                        invalidate();
                    }
                    else if(aristafinal){
                        crearLinea(e.getX(),e.getY());
                        invalidate();
                    }
                    break;
                default:
                    break;
            }

            return true;

        }

        public void existe(float xx,float yy){
            for(int i = 0;i < cordX.size();i++ ){
                if(((int)xx/100 == cordX.get(i).intValue()/100)&&((int)yy/100 == cordY.get(i).intValue()/100)){
                    cordXB.add(cordX.get(i));
                    cordYB.add(cordY.get(i));
                    cordX1.add(cordX.get(i));
                    cordY1.add(cordY.get(i));
                    aristafinal = true;
                    arista = false;
                }
            }
        }

        public void crearLinea(float xx,float yy){
            for(int i = 0;i < cordX.size();i++ ){
                if(((int)xx/100 == cordX.get(i).intValue()/100)&&((int)yy/100 == cordY.get(i).intValue()/100)) {
                    cordX2.add(xx);
                    cordY2.add(yy);
                    cordXB.clear();
                    cordYB.clear();
                    arista = true;
                    aristafinal = false;
                }
            }
        }
    }



    // Creacion programatica del Nodo y agregandolo a un ArrayList
    public void createNodo() {
        float x=0,y=0;
        boolean selected=false;
        int id=circulos.size();
        Nodo a = new Nodo(x,y,selected,id);
        circulos.add(a);
    }


    // Asignar aristas
    public void createArista() {
        float x1=0,y1=0,x2=0,y2=0;
        boolean dir=false;
        Arista a = new Arista(x1,y1,x2,y2,dir);
        conexion(x1,y1,x2,y2);
        rayas.add(a);
    }


    //Llenar la matriz de conexiones al momento de hacer la arista entre los dos
    public void conexion(float x1,float y1,float x2,float y2)
    {
        int i=(-1),j=(-1); //Valores a manera de flags para posicionar en la matriz
        for(Nodo a:circulos)
        {
            if(i==(-1)) {
                if (x1 == a.getX() && y1 == a.getY()) {
                    i = a.getId();
                } else if (x2 == a.getX() && y2 == a.getY()) {
                    i=a.getId();
                }
            }
            else
                break;
        }
        for(Nodo a:circulos)
        {
            if(j==(-1)) {
                if (x1 == a.getX() && y1 == a.getY()) {
                    j = a.getId();
                } else if (x2 == a.getX() && y2 == a.getY()) {
                    j=a.getId();
                }
            }
            else
                break;
        }
        if(i!=(-1) && j!=(-1))
        {
//            v[i][j]=1; //PonerTodo
        }
    }

}
