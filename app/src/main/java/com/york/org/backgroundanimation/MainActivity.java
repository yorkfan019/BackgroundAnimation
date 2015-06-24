package com.york.org.backgroundanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity implements View.OnClickListener{

    ImageView startCircle, startedCircle, cover;
    Button start, stop;
    Animation animation, animationReverse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        animation = AnimationUtils.loadAnimation(this, R.anim.move_anim);
        animationReverse = AnimationUtils.loadAnimation(this, R.anim.move_anim_reverse);
        animation.setFillAfter(true);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    public void initView(){
        startCircle = (ImageView) findViewById(R.id.start_circle);
        startedCircle = (ImageView) findViewById(R.id.started_circle);
        cover = (ImageView) findViewById(R.id.cover);

        start = (Button) findViewById(R.id.btn_start);
        stop = (Button) findViewById(R.id.btn_stop);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                cover.setVisibility(View.VISIBLE);
                startedCircle.setVisibility(View.VISIBLE);
                startCircle.setVisibility(View.GONE);
                startedCircle.startAnimation(animation);
                break;
            case R.id.btn_stop:
                cover.setVisibility(View.GONE);
                startedCircle.setVisibility(View.GONE);
                startCircle.setVisibility(View.VISIBLE);
                startedCircle.startAnimation(animationReverse);
                break;
        }
    }
}
