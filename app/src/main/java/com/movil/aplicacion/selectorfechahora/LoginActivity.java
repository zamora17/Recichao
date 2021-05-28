package com.movil.aplicacion.selectorfechahora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    String  str_email,   str_password;
    String url = "http://192.168.1.12/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView registro = (TextView) findViewById(R.id.Registrologin);

        email      =  findViewById(R.id.emailLogin);
        password =  findViewById(R.id.passwordLogin);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent registro = new Intent ( LoginActivity.this, RegisterActivity.class);
               LoginActivity.this.startActivity(registro);
               fileList();
            }
        });
    }
    public void Login(View view){
        if(email.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
        }else{
            final ProgressDialog progressDialog  = new ProgressDialog(this);
            progressDialog.setMessage("Verficando datos...");

            progressDialog.show();
            str_email = email.getText().toString().trim();
            str_password = password.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    if (response.equalsIgnoreCase("Bienvenido")){
                        password.setText("");
                        Intent appointmentIntent = new Intent(LoginActivity.this, AppointmentActivity.class);
                        appointmentIntent.putExtra("email", email.getText().toString() );
                        LoginActivity.this.startActivity(appointmentIntent);
                        Toast.makeText(LoginActivity.this, response + " " + email.getText().toString(), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", str_email);
                    params.put("password", str_password);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(request);
        }
    }
}