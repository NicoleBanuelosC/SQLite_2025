package Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Alumno {
   @PrimaryKey
   @NonNull
   public String numControl;

   @NonNull
   @ColumnInfo(name = "Nombre")
   public String nombre;

    public Alumno(String nc, String n) {
    }

    @NonNull
    public String getNumControl() { return numControl; }

    public void setNumControl(@NonNull String numControl) { this.numControl = numControl; }

    @NonNull
    public String getNombre() { return nombre; }

    public void setNombre(@NonNull String nombre) { this.nombre = nombre; }

}//public class
