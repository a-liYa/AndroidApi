package com.aliya.android.api27;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.aliya.android.api27.widget.card.CardView;

public class MainActivity extends AppCompatActivity {

    CardView mCardView;
    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

    private void changeToImageView(float progress) {
        float scale = 1 - 0.8f * progress;
        mCardView.setScaleX(scale);
        mCardView.setScaleY(scale);
    }

}
