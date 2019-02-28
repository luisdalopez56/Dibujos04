package com.videos.luisdalopez56.dibujos04;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    SensorManager gestorSensor;
    Sensor acelerometro;
    Grafico grafico;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        grafico = new Grafico(this);

        gestorSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = gestorSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        gestorSensor.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                grafico.onSensorEvent(event);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, acelerometro, SensorManager.SENSOR_DELAY_GAME);

        setContentView(grafico);


    }

    private class Grafico extends View {

        int x = 25;
        int y = 25;
        static final int RAD_CIRCULO = 25;
        int ancho;
        int alto;
        Paint pincel;

        public Grafico(Context context) {
            super(context);

        }



        @Override
        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);

            ancho = canvas.getWidth();
            alto = canvas.getHeight();

            pincel = new Paint();
            pincel.setColor(Color.BLUE);
            pincel.setAntiAlias(true);

            canvas.drawCircle(x,y, RAD_CIRCULO, pincel);
            invalidate();

        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            ancho = w;
            alto = h;

        }

        public void onSensorEvent (SensorEvent event) {

            if (x <=0 + RAD_CIRCULO){
                x = 0 + RAD_CIRCULO;
            }
            if(x >= ancho - RAD_CIRCULO){
                x = ancho - RAD_CIRCULO;
            }
            if (y <= 0 + RAD_CIRCULO){
                y = 0 + RAD_CIRCULO;
            }
            if (y >= alto - RAD_CIRCULO){
                y = alto - RAD_CIRCULO;
            }
            x = x - (int) event.values[0];
            y = y + (int) event.values[1];
        }


    }
}
