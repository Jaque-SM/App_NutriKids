package com.example.nutrikids;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hand=new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrar_login();
            }
        },2000);

    }

    public void mostrar_login(){
        Intent tent= new Intent(SplashActivity.this, MainActivity.class);
        startActivity(tent);
        finish();
    }
}