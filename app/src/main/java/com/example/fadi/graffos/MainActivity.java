package com.example.fadi.graffos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    ArrayList<Nodo> circulos = new ArrayList<Nodo>();
    ArrayList<Arista> rayas = new ArrayList<Arista>();
    int v[][]=new int[20][20]; //Matriz en general con un max de 20x20
    public boolean nodo=false,arista=false,mynigga=false;
    public Button bt1,bt2,bt3;
    public SurfaceView sf1;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<19;i++) //Llenar de puro 0 la matriz
        {
            for(int j=0;j<19;j++) {
                v[i][j]=0;
            }
        }
        sf1=(SurfaceView) findViewById(R.id.sf1);
        bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
    }
    private ArrayList<Path> _graphics = new ArrayList<Path>();
    public void hacernodo(View view)
    {
        if(!nodo) {
            nodo = true;
            bt1.setTextColor(Color.RED);
        }
        else {
            bt1.setTextColor(Color.BLACK);
            nodo = false;
        }
    }
    public void hacerarista(View view)
    {
        if(!arista) {
            arista = true;
            bt2.setTextColor(Color.RED);
        }
        else {
            bt2.setTextColor(Color.BLACK);
            arista = false;
        }
    }
    public void clear(View view)
    {
        limpio();
    }
    public void limpio()
    {
        SurfaceHolder holdeR= sf1.getHolder();
        Canvas c = holdeR.lockCanvas();
        c.drawColor(Color.WHITE);
        holdeR.unlockCanvasAndPost(c);

    }
    class Vista extends View {

        float x=5;
        float y=5;
        boolean yas;
        String go="accion";
        Path path = new Path();

        public Vista(Context context) {
            super(context);
        }

        /*public void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            paint.setColor(Color.BLUE);
            int ancho = canvas.getWidth();
        }

        public boolean onTouchEvent(MotionEvent e)
        {
            if(e.getAction()== MotionEvent.ACTION_DOWN) {
                x = e.getX();
                y = e.getY();
                yas=true;
                Log.e("Coord",""+x+" "+y);
            }
            return true;
        }*/
    }
    // Creacion programatica del Nodo y agregandolo a un ArrayList
    public void createNodo()
    {
        float x=0,y=0;
        boolean selected=false;
        int id=circulos.size();
        Nodo a = new Nodo(x,y,selected,id);
        circulos.add(a);
    }
    // Asignar aristas
    public void createArista()
    {
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
            v[i][j]=1;
        }
    }

}
