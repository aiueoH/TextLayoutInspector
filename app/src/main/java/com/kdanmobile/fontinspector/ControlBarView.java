package com.kdanmobile.fontinspector;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Wei on 2017/6/20.
 */

public class ControlBarView extends FrameLayout {

    private TextView tvSize;
    private SeekBar sb;
    private CheckBox cb1, cb2;
    private Runnable onUpdateListener;

    public ControlBarView(Context context) {
        super(context);
        init();
    }

    public ControlBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ControlBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (isInEditMode()) {}
        LayoutInflater.from(getContext()).inflate(R.layout.view_control_bar, this);
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onUpdate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                onUpdate();
            }
        };

        sb = (SeekBar) findViewById(R.id.seekBar1);
        tvSize = (TextView)  findViewById(R.id.tv_size);
        cb1 = (CheckBox) findViewById(R.id.cb_a1);
        cb2 = (CheckBox) findViewById(R.id.cb_a2);


        sb.setOnSeekBarChangeListener(onSeekBarChangeListener);
        cb1.setOnCheckedChangeListener(onCheckedChangeListener);
        cb2.setOnCheckedChangeListener(onCheckedChangeListener);
        update();
    }

    public int getSBValue() {
        return sb.getProgress();
    }

    public void setSBValue(int v) {
        sb.setProgress(v);
        update();
    }

    public boolean isCB1Checked() {
        return cb1.isChecked();
    }

    public void setCB1Checked(boolean b) {
        cb1.setChecked(b);
    }

    public boolean isCB2Checked() {
        return cb2.isChecked();
    }

    public void setCB2Checked(boolean b) {
        cb2.setChecked(b);
    }

    public void setOnUpdateListener(Runnable onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }

    private void onUpdate() {
        update();
        if (onUpdateListener != null) onUpdateListener.run();
    }

    private void update() {
        tvSize.setText(String.valueOf(sb.getProgress()));
    }
}
