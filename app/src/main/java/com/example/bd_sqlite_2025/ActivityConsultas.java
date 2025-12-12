package com.example.bd_sqlite_2025;

import static com.example.bd_sqlite_2025.R.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import db.EscuelaBD;
import Entities.Alumno;

public class ActivityConsultas extends Activity {

    private RecyclerView recyclerView = findViewById(id.recyclerview_alumnos);
    private CustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private EditText cajaFiltro = findViewById(id.caja_buscar);
    private EscuelaBD bd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        // configurar el RecyclerView
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // inicializar la bd
        bd = EscuelaBD.getAppDatabase(this);

        // cargar alumnos
        cargarAlumnos();
    }//OnCreate

    private void cargarAlumnos() {
        new Thread(() -> {
            List<Alumno> lista = bd.alumnoDAO().mostrarTodos();
            runOnUiThread(() -> {
                if (adapter == null) {
                    adapter = new CustomAdapter(new ArrayList<>(lista));
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.actualizarDatos(new ArrayList<>(lista));
                }//Else
            });
        }).start();
    }//CargarAlumnis

    public void buscarPorNombre(View view) {
        String nombre = cajaFiltro.getText().toString().trim();
        if (nombre.isEmpty()) {
            cargarAlumnos();
            return;
        }//if

        new Thread(() -> {
            List<Alumno> lista = bd.alumnoDAO().mostrarPorNombre(nombre);
            runOnUiThread(() -> {
                adapter.actualizarDatos(new ArrayList<>(lista));
            });
        }).start();
    }//BUscarPorNombre

}//ActivityConsultas

// --- ADAPTADOR ---
class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Alumno> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textview_alumno);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public CustomAdapter(ArrayList<Alumno> dataset) {
        this.localDataSet = dataset != null ? dataset : new ArrayList<>();
    }

    public void actualizarDatos(ArrayList<Alumno> nuevosDatos) {
        this.localDataSet = nuevosDatos != null ? nuevosDatos : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.textview_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Alumno alumno = localDataSet.get(position);
        holder.getTextView().setText(alumno.numControl + " - " + alumno.nombre);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}