package com.example.bd_sqlite_2025;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import db.EscuelaBD;
import Entities.Alumno;

public class ActivityConsultas extends Activity {

    private RecyclerView recyclerView;
    private AlumnoAdapter adapter;
    EditText cajaFiltro;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        recyclerView = findViewById(R.id.recyclerview_alumnos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cajaFiltro = findViewById(R.id.caja_buscar);
        cajaFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                adapter.filtrar(s.toString());
            }//after

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        cargarAlumnos();
    }//onCreate

    private void cargarAlumnos() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EscuelaBD bd = EscuelaBD.getAppDatabase(getApplicationContext());
                    final List<Alumno> listaAlumnos = bd.alumnoDAO().mostrarTodos();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new AlumnoAdapter(listaAlumnos);
                            recyclerView.setAdapter(adapter);
                        }//run
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }//Catch
            }//run
        }).start();
    }//cargar
}//public class
