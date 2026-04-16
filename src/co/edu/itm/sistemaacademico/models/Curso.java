package co.edu.itm.sistemaacademico.models;

import co.edu.itm.sistemaacademico.estructuras.ListaEnlazada;
import co.edu.itm.sistemaacademico.estructuras.Nodo;

public class Curso {
    private int codigoCurso;
    private String nombreCurso;
    private int creditos;
    private String horario;
    private String aula;
    private Docente docente;
    private ListaEnlazada estudiantesMatriculados;

    public Curso() {
        this.estudiantesMatriculados = new ListaEnlazada();
    }

    public Curso(int codigoCurso, String nombreCurso, int creditos) {
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.creditos = creditos;
        this.estudiantesMatriculados = new ListaEnlazada();
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public ListaEnlazada getEstudiantesMatriculados() {
        return estudiantesMatriculados;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantesMatriculados.agregarElementoAlFinal(estudiante);
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        if (estudiantesMatriculados.getCabeza() == null) {
            return;
        }
        if (estudiantesMatriculados.getCabeza().getDato() == estudiante) {
            estudiantesMatriculados.eliminarElementoAlInicio();
            return;
        }
        Nodo nodoActual = estudiantesMatriculados.getCabeza();
        while (nodoActual.getSiguiente() != null) {
            if (nodoActual.getSiguiente().getDato() == estudiante) {
                nodoActual.setSiguiente(nodoActual.getSiguiente().getSiguiente());
                return;
            }
            nodoActual = nodoActual.getSiguiente();
        }
    }

    public void mostrarInformacion() {
        System.out.println("Codigo del curso: " + codigoCurso);
        System.out.println("Nombre del curso: " + nombreCurso);
        System.out.println("Creditos: " + creditos);
    }

}