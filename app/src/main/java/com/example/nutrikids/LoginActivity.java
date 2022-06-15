package com.example.nutrikids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private ProgressBar load;
    private FirebaseAuth user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_nutri);

        user = FirebaseAuth.getInstance();


        load = findViewById(R.id.loading);
        email = findViewById(R.id.camp_login);
        senha = findViewById(R.id.camp_senha);

    }


    public void TelaResponsavel(View view) {
        String loginEmail = email.getText().toString();
        String loginSenha = senha.getText().toString();

        if (!TextUtils.isEmpty(loginEmail) || !TextUtils.isEmpty(loginSenha)) {
            load.setVisibility(View.VISIBLE);
            user.signInWithEmailAndPassword(loginEmail, loginSenha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                              Intent tent = new Intent(LoginActivity.this, ListaCardapioActivity.class);
                                startActivity(tent);
                                load.setVisibility(View.INVISIBLE);
                                //abrir tela principal
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error no Login " + error, Toast.LENGTH_SHORT).show();
                                load.setVisibility(View.INVISIBLE);

                            }
                        }
                    });
        }


    }
    public void CriarConta(View view){
        Intent tent=new Intent(this, Cadastro_Activity.class);
        startActivity(tent);

    }



}

