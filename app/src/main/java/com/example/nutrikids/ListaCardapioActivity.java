package com.example.nutrikids;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrikids.modelo.Cardapio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class ListaCardapioActivity extends AppCompatActivity {

    private FirebaseFirestore bd;
    static FirebaseAuth mAuth;
    static FirebaseUser currentUser;

    static FirebaseFirestore ava;

    private String testar;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela4_nutri_cardapio1_);

        bd = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        currentUser =  mAuth.getCurrentUser();

        String user_id = currentUser.getUid();
        String email = currentUser.getEmail();


        DocumentReference docRef = bd.collection("User").document(user_id).collection("Cardapios").document(user_id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Cardapio cardapio = documentSnapshot.toObject(Cardapio.class);
                //Log.d("cardapio: ",cardapio);
            }
        });


    }

    public void remover(View view){
        startActivity(new Intent(this, RemoveCardapioActivity.class));
    }

    public void AddCardapio(View view) {
        startActivity(new Intent(this, CadastroCardapioActivity.class));

    }


    public void deslogaut(View view) {
        mAuth.signOut();
        finish();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        //Não fazer nada quando o botão voltar ser pressionado, para o usuário utilizar o botão logout
    }
}