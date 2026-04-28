package co.edu.itm.sistemaacademico.models;

import co.edu.itm.sistemaacademico.estructuras.Cola;
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
    private int cupoMaximo;
    private Cola colaEspera;

    public Curso() {
        this.estudiantesMatriculados = new ListaEnlazada();
        this.cupoMaximo = 0;
        this.colaEspera = new Cola();
    }

    public Curso(int codigoCurso, String nombreCurso, int creditos, int cupoMaximo) {
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.creditos = creditos;
        this.cupoMaximo = cupoMaximo;
        this.estudiantesMatriculados = new ListaEnlazada();
        this.colaEspera = new Cola();
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

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantesMatriculados.agregarElementoAlFinal(estudiante);
    }

    // 2c — Verificación de cupo
    public boolean estaLleno() {
        return estudiantesMatriculados.getTamaño() >= cupoMaximo;
    }

    public int getCuposDisponibles() {
        return cupoMaximo - estudiantesMatriculados.getTamaño();
    }

    // 2d — Operaciones sobre la cola de espera
    public void agregarAColaEspera(Estudiante estudiante) {
        colaEspera.enqueue(estudiante);
    }

    public Estudiante siguienteEnCola() {
        return (Estudiante) colaEspera.dequeue();
    }

    public void listarColaEspera() {
        if (colaEspera.isEmpty()) {
            System.out.println("No hay estudiantes en cola de espera para el curso " + nombreCurso + ".");
            return;
        }
        System.out.println("Cola de espera del curso " + nombreCurso + " (" + colaEspera.getTamaño() + " estudiante(s)):");
        Cola copia = new Cola();
        while (!colaEspera.isEmpty()) {
            Estudiante estudiante = (Estudiante) colaEspera.dequeue();
            System.out.println("- " + estudiante.getNombre() + " " + estudiante.getApellido()
                    + " (ID: " + estudiante.getIdentificacion() + ")");
            copia.enqueue(estudiante);
        }
        // Restaurar la cola original
        while (!copia.isEmpty()) {
            colaEspera.enqueue(copia.dequeue());
        }
    }

    // 2e — Eliminación de un estudiante matriculado (necesario para cancelarCurso)
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

    // 2f — mostrarInformacion actualizado con cupo y cola
    public void mostrarInformacion() {
        System.out.println("Codigo del curso: " + codigoCurso);
        System.out.println("Nombre del curso: " + nombreCurso);
        System.out.println("Creditos: " + creditos);
        System.out.println("Cupo máximo: " + cupoMaximo);
        System.out.println("Cupos disponibles: " + getCuposDisponibles());
        System.out.println("Estudiantes en cola de espera: " + colaEspera.getTamaño());
    }

}