package com.example.bd_sqlite_2025;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import db.EscuelaBD;

public class ActivityBajas extends Activity {

    EditText cajaNumControl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        cajaNumControl = findViewById(R.id.caja_baja_numcontrol);
    }//onXraete

    public void eliminarAlumno(View v){
        final String nc = cajaNumControl.getText().toString().trim();

        if (nc.isEmpty()) {
            Toast.makeText(this, "Ingrese el Número de Control para eliminar.", Toast.LENGTH_SHORT).show();
            return;
        }//if

        final EscuelaBD bd = EscuelaBD.getAppDatabase(getApplicationContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                int filasEliminadas = 0;
                try {
                    bd.alumnoDAO().eliminarAlumnoPorNumControl(nc);

                    Log.i("MENSAJE=>", "Baja solicitada para NC: " + nc);

                } catch (Exception e) {
                    Log.e("DB_ERROR", "Falla al eliminar: " + e.getMessage());
                }//Catch

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ActivityBajas.this, "Baja procesada para NC: " + nc,
                                Toast.LENGTH_LONG).show();

                        // Limpiar la caja después de la operación
                        cajaNumControl.setText("");
                    }//run
                });
            }//run
        }).start();
    }//eliminar
}//public class