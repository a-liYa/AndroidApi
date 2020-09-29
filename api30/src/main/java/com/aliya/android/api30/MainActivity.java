package com.aliya.android.api30;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mTvHell;
    ConstraintLayout mConstraint;
    private View mInflate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mConstraint = findViewById(R.id.constraint);
        mTvHell = findViewById(R.id.hell_world);
        mTvHell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = mTvHell.getLayoutParams();
                mConstraint.removeView(mTvHell);
                mConstraint.addView(mInflate, params);
            }
        });

        mInflate = getLayoutInflater().inflate(R.layout.layout_view, mConstraint, false);

    }
}
