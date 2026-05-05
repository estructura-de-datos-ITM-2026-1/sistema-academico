package co.edu.itm.sistemaacademico.models;

import co.edu.itm.sistemaacademico.estructuras.ListaEnlazada;
import co.edu.itm.sistemaacademico.estructuras.Nodo;

public class Estudiante extends Persona {

    private float promedio;
    private int semestre;
    private ListaEnlazada cursosMatriculados;
    private ListaEnlazada horariosSeleccionados;

    public Estudiante(String nombre, String apellido, String identificacion, String direccion) {
        super(nombre, apellido, identificacion, direccion);
        this.promedio = 0.0f;
        this.semestre = 1;
        this.cursosMatriculados = new ListaEnlazada();
        this.horariosSeleccionados = new ListaEnlazada();
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

    public boolean matricularCurso(Curso curso, Horario horarioElegido) {
        if (curso.estaLleno()) {
            curso.agregarAColaEspera(this);
            System.out.println("El curso " + curso.getNombreCurso() + " está lleno. "
                    + getNombre() + " " + getApellido() + " fue agregado a la cola de espera.");
            return false;
        }
        cursosMatriculados.agregarElementoAlFinal(curso);
        horariosSeleccionados.agregarElementoAlFinal(horarioElegido);
        curso.agregarEstudiante(this);
        return true;
    }

    public void cancelarCurso(Curso curso) {
        // Eliminar el curso (y su horario asociado) de la lista del estudiante
        Nodo nodoCurso = cursosMatriculados.getCabeza();
        Nodo nodoHorario = horariosSeleccionados.getCabeza();
        if (nodoCurso == null) {
            return;
        }
        Horario horarioEliminado = null;
        if (nodoCurso.getDato() == curso) {
            horarioEliminado = (Horario) nodoHorario.getDato();
            cursosMatriculados.eliminarElementoAlInicio();
            horariosSeleccionados.eliminarElementoAlInicio();
        } else {
            Nodo prevHorario = nodoHorario;
            while (nodoCurso.getSiguiente() != null) {
                if (nodoCurso.getSiguiente().getDato() == curso) {
                    horarioEliminado = (Horario) prevHorario.getSiguiente().getDato();
                    nodoCurso.setSiguiente(nodoCurso.getSiguiente().getSiguiente());
                    prevHorario.setSiguiente(prevHorario.getSiguiente().getSiguiente());
                    break;
                }
                nodoCurso = nodoCurso.getSiguiente();
                prevHorario = prevHorario.getSiguiente();
            }
        }
        // Eliminar el estudiante de la lista del curso
        curso.eliminarEstudiante(this);
        // Matricular automáticamente al siguiente en la cola de espera
        Estudiante siguiente = curso.siguienteEnCola();
        if (siguiente != null) {
            siguiente.matricularCurso(curso, horarioEliminado);
            System.out.println("El estudiante " + siguiente.getNombre() + " " + siguiente.getApellido()
                    + " fue matriculado automáticamente en " + curso.getNombreCurso()
                    + " desde la cola de espera.");
        }
    }

    public void listarCursosMatriculados() {
        System.out.println("Cursos matriculados por " + getNombre() + " " + getApellido() + ":");
        Nodo nodoCurso = cursosMatriculados.getCabeza();
        Nodo nodoHorario = horariosSeleccionados.getCabeza();
        while (nodoCurso != null) {
            Curso curso = (Curso) nodoCurso.getDato();
            Horario horario = (Horario) (nodoHorario != null ? nodoHorario.getDato() : null);
            String horarioTxt = horario == null
                    ? "sin horario"
                    : "días=" + horario.getDias() + " " + horario.getHoraInicio() + "h-" + horario.getHoraFin() + "h";
            System.out.println("- " + curso.getNombreCurso() + " | " + horarioTxt);
            nodoCurso = nodoCurso.getSiguiente();
            if (nodoHorario != null) {
                nodoHorario = nodoHorario.getSiguiente();
            }
        }
    }

}
