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


    public boolean nodo=false,arista=false,mynigga=false;
    public Button bt1,bt2,bt3;
    public SurfaceView sf1;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
