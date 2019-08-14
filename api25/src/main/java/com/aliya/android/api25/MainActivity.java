package com.aliya.android.api25;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "toast:show()");
                Toast.makeText(new ToastContext(MainActivity.this), "NB", Toast.LENGTH_SHORT).show();
            }
        }, 1000);
//        finish();
    }
}
