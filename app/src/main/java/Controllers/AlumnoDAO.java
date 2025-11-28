package Controllers;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Entities.Alumno;

@Dao
public interface AlumnoDAO {

    //- - - A L T A S - - -
    @Insert
    public void agregarAlumno(Alumno alumno);

    // - - - B A J A S - - -
    @Delete
    public void eliminarAlumno(Alumno alumno); //eliminar por instancia de objeto

    @Query("DELETE FROM alumno WHERE numControl=:nc")
    public void eliminarAlumnoPorNumControl(String nc);

    // - - - C A M B I O S - - -
    @Update
    public void actualizarAlumno(Alumno alumno);

    //@Query("UPDATE alumno SET nombre=:n, primerAp=:pa WHERE numControl=:nc")
    @Query("UPDATE alumno SET nombre=:n WHERE numControl=:nc")
    public void actualizarAlumno(String n, String nc);

    // - - - C O N S U L T A S - - -
    @Query("SELECT * FROM alumno")
    public List<Alumno> mostrarTodos();

    // *** CORRECCIÓN APLICADA AQUÍ ***
    @Query("SELECT * FROM alumno WHERE nombre = :n")
    public List<Alumno> mostrarPorNombre(String n);

}//public interface