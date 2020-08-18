package com.example.api23;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.example.api23.widget.MaskDrawable;

public class MainActivity extends AppCompatActivity {

    RadioButton rb0;
    RadioButton rb1;
    private MaskDrawable mMaskDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rb0 = (RadioButton) findViewById(R.id.rb_top_0);
        rb1 = (RadioButton) findViewById(R.id.rb_top_1);

        findViewById(R.id.root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb0.setChecked(!rb0.isChecked());
                rb1.setChecked(!rb1.isChecked());
            }
        });

        Drawable[] ds = rb0.getCompoundDrawables();
        mMaskDrawable = new MaskDrawable(ds[0]);

        rb0.setCompoundDrawablesWithIntrinsicBounds(mMaskDrawable, ds[1], ds[2], ds[3]);

    }

}
