package co.edu.itm.sistemaacademico.models;

import java.time.LocalDate;

public class Estudiante extends Persona {

    private float promedio;
    private int semestre;

    public Estudiante(String nombre, String apellido, LocalDate fechaNacimiento, String direccion, String telefono,
            String correo, float promedio, int semestre) {
        super(nombre, apellido, fechaNacimiento, direccion, telefono, correo);
        this.promedio = promedio;
        this.semestre = semestre;
    }

    public Estudiante(String nombre, String apellido) {
        super(nombre, apellido);
    }

    public void matricularCurso(String curso) {
        System.out.println("El estudiante " + getNombre() + " se ha matriculado en el curso: " + curso);
    }

    public void cancelarCurso() {

    }

}
