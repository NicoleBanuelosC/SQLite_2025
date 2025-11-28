package com.example.bd_sqlite_2025;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.Nullable;

import Entities.Alumno;
import db.EscuelaBD;

public class ActivityAltas extends Activity {

    EditText cajaNumControl, cajaNombre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        cajaNumControl = findViewById(R.id.caja_num_control);
        cajaNombre = findViewById(R.id.caja_nombre);
    }//onCreate

    public void agregarAlumno(View v){
        String nc = cajaNumControl.getText().toString();
        String n = cajaNombre.getText().toString();

        Alumno alumno = new Alumno(nc, n);
        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());

        //Looper Y Handler

        //Hilo normal
        new Thread(new Runnable() {
            @Override
            public void run() {
                bd.alumnoDAO().agregarAlumno(alumno); //OPERACION SOBRE LA BASE DE DATOS
                Log.i("Mensaje: ", "Inserción correcta");

                //OPCION 1)
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Inserción correcta", Toast.LENGTH_SHORT).show();
                    }
                });

                //NO CORRE PORQUE COMO ESTA DENTRO DEL RUNNABLE
                //Toast.makeText(getBaseContext(), "Inserción correcta", Toast.LENGTH_SHORT).show();

            }//run
        }).start();

    }//agregarAlumno

}//public class
