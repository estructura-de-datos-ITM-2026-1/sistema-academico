package co.edu.itm.sistemaacademico.models;

import java.time.LocalDate;

public class Docente extends Persona {
    private LocalDate fechaVinculacion;
    private int numeroCursos;

    // Constructor con parámetros
    public Docente(String nombre, String apellido, String identificacion, int numeroCursos, String direccion) {
        super(nombre, apellido, identificacion, direccion);
        this.fechaVinculacion = LocalDate.now();
        this.numeroCursos = numeroCursos;
    }

    // Getters
    public LocalDate getFechaVinculacion() {
        return fechaVinculacion;
    }

    public int getNumeroCursos() {
        return numeroCursos;
    }

    // Setters

    public void setFechaVinculacion(LocalDate fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public void setNumeroCursos(int numeroCursos) {
        this.numeroCursos = numeroCursos;
    }

    public void verDetalleCurso(String curso) {
        System.out.println("El docente " + getNombre() + " está viendo el detalle del curso: " + curso);
    }

}