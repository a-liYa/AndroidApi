package com.aliya.android.api26;

import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.text.DecimalFormat;

/**
 * App
 *
 * @author a_liYa
 * @date 2019/3/20 11:11.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void pressureSensor() {
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (mPressure == null) {
            Log.e("TAG", "您的手机不支持气压传感器，无法使用本软件功能.");
        } else {
            sensorManager.registerListener(pressureListener, mPressure,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    SensorEventListener pressureListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float sPV = event.values[0];
            DecimalFormat df = new DecimalFormat("0.00");
            df.getRoundingMode();
            // 计算海拔
            double height =
                    44330000 * (1 - (Math.pow((Double.parseDouble(df.format(sPV)) / 1013.25),
                            (float) 1.0 / 5255.0)));
            Log.e("TAG", "海拔高度: " + df.format(height));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }
    };
}
