package com.aliya.android.api27;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.HorizontalScrollView;

public class MainActivity extends AppCompatActivity {

    HorizontalScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView = findViewById(R.id.scroll_view);

        findViewById(R.id.tv_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollView.setVisibility(
                        mScrollView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

    }
}
