package com.kdanmobile.fontinspector;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ControlBarView controlBarView;
    private TextView[] tvs = {null, null, null};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controlBarView = (ControlBarView) findViewById(R.id.controlBarView);

        tvs[0] = (TextView) findViewById(R.id.tv_1);
        tvs[1] = (TextView) findViewById(R.id.tv_2);
        tvs[2] = (TextView) findViewById(R.id.tv_3);

        controlBarView.setOnUpdateListener(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });

        controlBarView.setSBValue(16);

        update();
    }

    private void update() {
        TextView tv = tvs[0];
        float size = controlBarView.getSBValue();
        tv.setTextSize(size);

        int type = 0;
        if (controlBarView.isCB1Checked()) type |= Typeface.BOLD;
        if (controlBarView.isCB2Checked()) type |= Typeface.ITALIC;
        tv.setTypeface(null, type);
    }
}
