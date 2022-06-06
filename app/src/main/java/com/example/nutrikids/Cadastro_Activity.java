package com.example.nutrikids;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Cadastro_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

<<<<<<< HEAD:app/src/main/java/com/example/nutrikids/Cadastro_Activity.java
=======
    public void loginNutri(View view){
        Intent intent = new Intent(getApplicationContext(),TelaLoginActivity2_nutri.class);
        startActivity(intent);
    }
>>>>>>> 2e59b4772032de5cf74c0393032bc44cc170a5b9:app/src/main/java/com/example/nutrikids/Login_Activity.java

}