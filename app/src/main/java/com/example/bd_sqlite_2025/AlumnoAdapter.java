package com.example.bd_sqlite_2025;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Entities.Alumno;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder> {

    private List<Alumno> alumnos;
    private List<Alumno> listaAlumnosOriginal;
    public AlumnoAdapter(List<Alumno> alumnos) {
        this.listaAlumnosOriginal = new ArrayList<>(alumnos);
        this.alumnos = alumnos;
    }//AlumnoAdapter

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, parent, false);
        return new AlumnoViewHolder(view);
    }//alumno

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        Alumno alumno = alumnos.get(position);

        holder.tvNumControl.setText(alumno.numControl);
        holder.tvNombre.setText(alumno.nombre);
        holder.tvApellido1.setText(alumno.apellido1);
        holder.tvApellido2.setText(alumno.apellido2);
    }//onBindView

    @Override
    public int getItemCount() {
        return alumnos.size();
    }//FetItem
    public void filtrar(String texto) {
        alumnos.clear();
        if (texto.isEmpty()) {
            alumnos.addAll(listaAlumnosOriginal);

        } else {
            texto = texto.toLowerCase();
            for (Alumno alumno : listaAlumnosOriginal) {
                if (alumno.nombre.toLowerCase().startsWith(texto)) {
                    alumnos.add(alumno);
                }//if

            }//for
        }//slee
        notifyDataSetChanged();
    }//filrtra

    public static class AlumnoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumControl;
        TextView tvNombre;
        TextView tvApellido1;
        TextView tvApellido2;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumControl = itemView.findViewById(R.id.text_num_control);
            tvNombre = itemView.findViewById(R.id.text_nombre);
            tvApellido1 = itemView.findViewById(R.id.text_apellido1);
            tvApellido2 = itemView.findViewById(R.id.text_apellido2);
        }//alumno

    }//static class

}//public class