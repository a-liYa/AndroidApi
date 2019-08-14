package com.aliya.android.api27;

import android.content.pm.ActivityInfo;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.SeekBar;

import com.aliya.android.api27.compat.ActivityOrientationCompat;
import com.aliya.android.api27.widget.card.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView mCardView;
    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityOrientationCompat.setRequestedOrientation(this,
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardView = findViewById(R.id.card_view);
        mSeekBar = findViewById(R.id.seek_bar);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changeToImageView((float) progress / seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mCardView.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(new Rect(0, 0, view.getWidth(), view.getHeight()),
                            view.getHeight() / 2f);
                }
            });
        }

        findViewById(R.id.tv_1).setOnClickListener(this);
        findViewById(R.id.tv_2).setOnClickListener(this);
        findViewById(R.id.tv_3).setOnClickListener(this);
    }

    private void changeToImageView(float progress) {
        float scale = 1 - 0.8f * progress;
        mCardView.setScaleX(scale);
        mCardView.setScaleY(scale);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_1:
                ((View) findViewById(R.id.tv_1).getParent()).setSelected(false);
                findViewById(R.id.tv_1).setSelected(true);
                break;
            case R.id.tv_2:
                ((View) findViewById(R.id.tv_2).getParent()).setSelected(false);
                findViewById(R.id.tv_2).setSelected(true);
                break;
            case R.id.tv_3:
                ((View) findViewById(R.id.tv_3).getParent()).setSelected(false);
                findViewById(R.id.tv_3).setSelected(true);
                break;
        }
    }
}
