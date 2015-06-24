package com.york.org.backgroundanimation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener{

    ImageView startCircle, cover;
    RelativeLayout startedCircle;
    TextView startingText;
    Button start, stop;
    Animation animation, animationReverse;
    Boolean STOP=true;
    MyHandler myHandler = null;
    int count=-1;
    String string = "启动中";
    Boolean started =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        myHandler = new MyHandler();
        animation = AnimationUtils.loadAnimation(this, R.anim.move_anim);
        animationReverse = AnimationUtils.loadAnimation(this, R.anim.move_anim_reverse);
        animation.setFillAfter(true);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    public void initView(){
        startCircle = (ImageView) findViewById(R.id.start_circle);
        startedCircle = (RelativeLayout) findViewById(R.id.started_circle);
        cover = (ImageView) findViewById(R.id.cover);
        startingText =(TextView) findViewById(R.id.tv_starting);

        start = (Button) findViewById(R.id.btn_start);
        stop = (Button) findViewById(R.id.btn_stop);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                if(!started){
                    started = true;
                    cover.setVisibility(View.VISIBLE);
                    startedCircle.setVisibility(View.VISIBLE);
                    startCircle.setVisibility(View.GONE);
                    startedCircle.startAnimation(animation);
                    count=1;
                    string = "启动中";
                    startingText.setText(string);
                    STOP =false;
                    Thread mThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(!STOP){
                                try{
                                    Thread.sleep(500);
                                    while(count>4){
                                        count = 1;
                                        string="启动中";

                                    }
                                    Message msg = new Message();
                                    msg.what = 0x01;
                                    myHandler.sendMessage(msg);
                                    string = string+".";
                                    count++;
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    mThread.start();
                }
                break;
            case R.id.btn_stop:
                if(started){
                    STOP = true;
                    cover.setVisibility(View.GONE);
                    startedCircle.setVisibility(View.GONE);
                    startCircle.setVisibility(View.VISIBLE);
                    startedCircle.startAnimation(animationReverse);
                    started = false;
                }
                break;
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x01:
                    startingText.setText(string);
                    break;
            }

        }
    }
}
