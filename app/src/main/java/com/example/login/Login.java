package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.movil.aplicacion.selectorfechahora.MainActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView registro = (TextView) findViewById(R.id.Registrologin);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent registro = new Intent ( Login.this,Registro.class);
               Login.this.startActivity(registro);
               fileList();
            }
        });

        Button   btnlogin= (Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnlogin = new Intent ( Login.this,MainActivity.class);
                Login.this.startActivity(btnlogin);
                fileList();
            }
        });
    }
}