package com.movil.aplicacion.selectorfechahora;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AppointmentActivity extends AppCompatActivity implements View.OnClickListener {
    Button bfecha,bhora;
    EditText efecha,ehora;
    String   str_efecha, str_ehora;
    String url = "http://192.168.1.12/appointment.php";

    private  int dia,mes,ano,hora,minutos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        bfecha=(Button)findViewById(R.id.bfecha);
        bhora=(Button)findViewById(R.id.bhora);
        efecha=(EditText)findViewById(R.id.efecha);
        ehora=(EditText)findViewById(R.id.ehora);
        bfecha.setOnClickListener(this);
        bhora.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==bfecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    efecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }
        if (v==bhora){
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);
            int tipo_hora = c.get(Calendar.AM_PM);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    ehora.setText(hourOfDay+":"+minute + tipo_hora);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
    }

    
    public void ScheduleAppointment(View view){
        final ProgressDialog progressDialog  = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espera...");
        if (efecha.getText().toString().equals("") ||  ehora.getText().toString().equals("") ){
            Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.show();
            str_efecha = efecha.getText().toString().trim();
            str_ehora = ehora.getText().toString().trim();
            String email = getIntent().getStringExtra("email");

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    efecha.setText("");
                    ehora.setText("");

                    Toast.makeText(AppointmentActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(AppointmentActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("fecha", str_efecha);
                    params.put("hora", str_ehora);
                    params.put("email", email);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(AppointmentActivity.this);
            requestQueue.add(request);
        }
    }
}


