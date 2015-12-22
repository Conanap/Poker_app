package com.albicraneproductions.testpoker2;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends FragmentActivity {
    private final int duration = 3500;
    private Thread mSplashThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashThread = new Thread(){
          @Override
            public void run(){
              synchronized (this){
                  try{
                      wait(duration);
                  }catch(InterruptedException e){
                  }finally{
                      finish();
                      Intent intent = new Intent(getBaseContext(),
                              Main.class);
                      startActivity(intent);
                  }
              }
          }
        };
        mSplashThread.start();
    }
}
