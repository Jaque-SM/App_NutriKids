package com.example.nutrikids;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrikids.modelo.Cardapio;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PacienteActivity extends AppCompatActivity  {


    private String texto;
    private FirebaseFirestore bd;
    private  FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private String user_id;
    private String email;
    private TextView cafe;
    private TextView lancheManha;
    private TextView almoco;
    private TextView lancheTarde;
    private TextView janta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        cafe=findViewById(R.id.cafe_manha);
        lancheManha=findViewById(R.id.lanche_manha);
        almoco=findViewById(R.id.almoco);
        lancheTarde=findViewById(R.id.lanche_tarde);
        janta=findViewById(R.id.jantar);

        bd = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        currentUser =  mAuth.getCurrentUser();

        if(currentUser != null) {
            user_id = currentUser.getUid(); //Do what you need to do with the id
            email = currentUser.getEmail();
        }

        Log.d(texto, "user_id: "+user_id);
        Log.d(texto, "email: "+email);

        DocumentReference docRef = bd.collection("Cardapios").document(email);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot!=null) {
                    Cardapio card = documentSnapshot.toObject(Cardapio.class);
                    if(card != null){
                        Log.d(texto, "Card: "+card.toString());
                        cafe.setText(" Café da Manha: \n"+
                                card.getCafe_manha());
                        lancheManha.setText("Lanche: \n"+card.getLanche_manha());
                        almoco.setText("Almoco: \n"+card.getAlmoco());
                        lancheTarde.setText("Lanche Tarde: \n"+card.getLanche_tarde());
                        janta.setText("Jantar: \n"+card.getJantar());

                    }
                }

            }
        });

    }

    public void logaut(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i=new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        //Não fazer nada quando o botão voltar ser pressionado, para o usuário utilizar o botão logout
    }


}