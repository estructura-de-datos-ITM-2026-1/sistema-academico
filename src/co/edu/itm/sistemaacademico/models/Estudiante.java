package co.edu.itm.sistemaacademico.models;

import co.edu.itm.sistemaacademico.estructuras.ListaEnlazada;
import co.edu.itm.sistemaacademico.estructuras.Nodo;

public class Estudiante extends Persona {

    private float promedio;
    private int semestre;
    private ListaEnlazada cursosMatriculados;

    public Estudiante(String nombre, String apellido, String identificacion, String direccion) {
        super(nombre, apellido, identificacion, direccion);
        this.promedio = 0.0f;
        this.semestre = 1;
        this.cursosMatriculados = new ListaEnlazada();
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

    public boolean matricularCurso(Curso curso) {
        cursosMatriculados.agregarElementoAlFinal(curso);
        curso.agregarEstudiante(this);
        return true;
    }

    public void cancelarCurso(Curso curso) {
        // Eliminar el curso de la lista del estudiante
        Nodo nodoActual = cursosMatriculados.getCabeza();
        if (nodoActual == null) {
            return;
        }
        if (nodoActual.getDato() == curso) {
            cursosMatriculados.eliminarElementoAlInicio();
        } else {
            while (nodoActual.getSiguiente() != null) {
                if (nodoActual.getSiguiente().getDato() == curso) {
                    nodoActual.setSiguiente(nodoActual.getSiguiente().getSiguiente());
                    break;
                }
                nodoActual = nodoActual.getSiguiente();
            }
        }
        // Eliminar el estudiante de la lista del curso
        curso.eliminarEstudiante(this);
    }

    public void listarCursosMatriculados() {
        System.out.println("Cursos matriculados por " + getNombre() + " " + getApellido() + ":");
        Nodo nodoActual = cursosMatriculados.getCabeza();
        while (nodoActual != null) {
            Curso curso = (Curso) nodoActual.getDato();
            System.out.println("- " + curso.getNombreCurso());
            nodoActual = nodoActual.getSiguiente();
        }
    }

}
