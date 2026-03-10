package co.edu.itm.sistemaacademico.models;

public class Estudiante extends Persona {

    private float promedio;
    private int semestre;

    public Estudiante(String nombre, String apellido, String identificacion) {
        super(nombre, apellido, identificacion);
        this.promedio = 0.0f;
        this.semestre = 1;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void matricularCurso(String curso) {
        System.out.println("El estudiante " + getNombre() + " se ha matriculado en el curso: " + curso);
    }

    public void cancelarCurso() {

    }

}
