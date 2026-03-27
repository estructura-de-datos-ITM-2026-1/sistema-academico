package co.edu.itm.sistemaacademico.models;

import java.io.BufferedReader;
import java.io.FileReader;

import co.edu.itm.sistemaacademico.archivos.ArchivoEstudianteTexto;
import co.edu.itm.sistemaacademico.estructuras.ListaEnlazada;
import co.edu.itm.sistemaacademico.estructuras.Nodo;

public class SistemaAcademico {
    private ListaEnlazada estudiantes;
    private ListaEnlazada docentes;
    private ListaEnlazada cursos;
    private ArchivoEstudianteTexto archivoEstudiante;

    public SistemaAcademico() {
        this.estudiantes = new ListaEnlazada();
        this.docentes = new ListaEnlazada();
        this.cursos = new ListaEnlazada();
        this.archivoEstudiante = new ArchivoEstudianteTexto("estudiantes.txt");
        this.archivoEstudiante.crearArchivoEstudiante();
    }

    // Metodos para crear estudiantes - Create
    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.agregarElementoAlFinal(estudiante);
        this.archivoEstudiante.guardarEstudiante(estudiante);
    }

    public void agregarEstudiante(Estudiante estudiante, boolean alInicio) {
        if (alInicio) {
            this.estudiantes.agregarElementoAlInicio(estudiante);
        } else {
            this.estudiantes.agregarElementoAlFinal(estudiante);
        }
        this.archivoEstudiante.guardarEstudiante(estudiante);
    }

    // Metodo para listar estudiantes - Read
    public void listarEstudiantes() {
        Nodo nodoActual = this.estudiantes.getCabeza();
        while (nodoActual != null) {
            Estudiante estudiante = (Estudiante) nodoActual.getDato();
            System.out.println("DATOS DEL ESTUDIANTE:");
            estudiante.mostrarInformacion();
            nodoActual = nodoActual.getSiguiente();
        }
    }

    // Metodo para buscar un estudiante por su identificacion - Read
    public Estudiante buscarEstudiantePorIdentificacion(String identificacion) {
        Nodo nodoActual = this.estudiantes.getCabeza();
        while (nodoActual != null) {
            Estudiante estudiante = (Estudiante) nodoActual.getDato();
            if (estudiante.getIdentificacion().equals(identificacion)) {
                return estudiante;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return null; // Retorna null si no se encuentra el estudiante
    }

    // Metodo para actualizar la dirección de un estudiante - Update
    public void actualizarDireccionEstudiante(String identificacion, String nuevaDireccion) {
        Estudiante estudiante = buscarEstudiantePorIdentificacion(identificacion);
        if (estudiante != null) {
            estudiante.setDireccion(nuevaDireccion);
        } else {
            System.out.println("Estudiante con identificación " + identificacion + " no encontrado.");
        }
    }

    // CRUD para docentes
    public void agregarDocente(Docente docente) {
        this.docentes.agregarElementoAlFinal(docente);
    }

    public void listarDocentes() {
        Nodo nodoActual = this.docentes.getCabeza();
        while (nodoActual != null) {
            Docente docente = (Docente) nodoActual.getDato();
            System.out.println("DATOS DEL DOCENTE:");
            docente.mostrarInformacion();
            nodoActual = nodoActual.getSiguiente();
        }
    }

    public Docente buscarDocentePorIdentificacion(String identificacion) {
        Nodo nodoActual = this.docentes.getCabeza();
        while (nodoActual != null) {
            Docente docente = (Docente) nodoActual.getDato();
            if (docente.getIdentificacion().equals(identificacion)) {
                return docente;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return null; // Retorna null si no se encuentra el docente
    }

    // Aca va el metodo eliminar docente - Delete

    // CRUD para cursos
    public void agregarCurso(Curso curso) {
        this.cursos.agregarElementoAlFinal(curso);
    }

    public void listarCursos() {
        Nodo nodoActual = this.cursos.getCabeza();
        while (nodoActual != null) {
            Curso curso = (Curso) nodoActual.getDato();
            System.out.println("DATOS DEL CURSO:");
            curso.mostrarInformacion();
            nodoActual = nodoActual.getSiguiente();
        }
    }

    public Curso buscarCursoPorCodigo(int codigoCurso) {
        Nodo nodoActual = this.cursos.getCabeza();
        while (nodoActual != null) {
            Curso curso = (Curso) nodoActual.getDato();
            if (curso.getCodigoCurso() == codigoCurso) {
                return curso;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return null; // Retorna null si no se encuentra el curso
    }
}