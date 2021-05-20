package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);
        final EditText nombreT     = ( EditText) findViewById(R.id.nombreRegistro);
        final EditText apellidoT   = ( EditText) findViewById(R.id.apellidoRegistro);
        final EditText munisipioT  = ( EditText) findViewById(R.id.munisipioRegistro);
        final EditText emailT      = ( EditText) findViewById(R.id.emailRegistro);
        final EditText direccionT  = ( EditText) findViewById(R.id.direccionRegistro);
        final EditText contraseñaT = ( EditText) findViewById(R.id.contraseñaRegistro);
        Button   btnRegistro= (Button) findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreT.getText().toString();
                String apellido = apellidoT.getText().toString();
                String munisipio = munisipioT.getText().toString();
                String email = emailT .getText().toString();
                String direccion = direccionT.getText().toString();
                String contraseña = contraseñaT.getText().toString();
                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                };
                //Registrousuarios r = new Registrousuarios(nombre,apellido,munisipio,email,direccion,contraseña);
            }
        });
    }
}