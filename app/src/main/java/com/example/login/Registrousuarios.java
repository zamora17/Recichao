package com.example.login;


import android.app.VoiceInteractor;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Registrousuarios extends StringRequest {
    private static final String ruta = "";
    private Map<String, String> Parametros;
    public Registrousuarios(String nombre, String apellido, String munisipio, String email, String direccion, String contraseña, Response.Listener<String> listener)
    { super(Request.Method.POST,ruta,listener,  null);
    Parametros = new HashMap<>();
        Parametros.put("nombre",nombre+"");
        Parametros.put("apellido",apellido+"");
        Parametros.put("munisipio",munisipio+"");
        Parametros.put("email",email+"");
        Parametros.put("direccion",direccion+"");
        Parametros.put("contraseña",contraseña+"");
}

    @Override
    protected Map<String, String> getParams()  {
        return Parametros;
    }
}