package com.movil.aplicacion.selectorfechahora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText nombre, apellido, municipio, email, direccion, password;
    String str_nombre, str_apellido, str_municipio, str_email, str_direccion, str_password;
    String url = "http://192.168.1.12/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nombre     =   findViewById(R.id.nombreRegistro);
        apellido   =  findViewById(R.id.apellidoRegistro);
        municipio  =  findViewById(R.id.municipioRegistro);
        email      =  findViewById(R.id.emailRegistro);
        direccion  =  findViewById(R.id.direccionRegistro);
        password =  findViewById(R.id.passwordRegistro);
    }

    public  void Register(View view){
        final ProgressDialog progressDialog  = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espera...");

        if (nombre.getText().toString().equals("") ||
            apellido.getText().toString().equals("") ||
            email.getText().toString().equals("") ||
            municipio.getText().toString().equals("") ||
            direccion.getText().toString().equals("") ||
            password.getText().toString().equals("")
        ){
            Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.show();
            str_nombre = nombre.getText().toString().trim();
            str_apellido = apellido.getText().toString().trim();
            str_municipio = municipio.getText().toString().trim();
            str_email = email.getText().toString().trim();
            str_direccion = direccion.getText().toString().trim();
            str_password = password.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    nombre.setText("");
                    apellido.setText("");
                    municipio.setText("");
                    email.setText("");
                    direccion.setText("");
                    password.setText("");

                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("nombre", str_nombre);
                    params.put("apellido", str_apellido);
                    params.put("municipio", str_municipio);
                    params.put("email", str_email);
                    params.put("direccion", str_direccion);
                    params.put("password", str_password);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            requestQueue.add(request);
        }

    }
}