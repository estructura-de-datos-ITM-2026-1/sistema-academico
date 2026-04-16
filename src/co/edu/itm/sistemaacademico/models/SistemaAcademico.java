package co.edu.itm.sistemaacademico.models;

import java.io.BufferedReader;
import java.io.FileReader;

import co.edu.itm.sistemaacademico.archivos.ArchivoEstudianteTexto;
import co.edu.itm.sistemaacademico.estructuras.ListaEnlazada;
import co.edu.itm.sistemaacademico.estructuras.Nodo;
import co.edu.itm.sistemaacademico.estructuras.Pila;

public class SistemaAcademico {
    private ListaEnlazada estudiantes;
    private ListaEnlazada docentes;
    private ListaEnlazada cursos;
    private ArchivoEstudianteTexto archivoEstudiante;
    private Pila historial;

    public SistemaAcademico() {
        this.estudiantes = new ListaEnlazada();
        this.docentes = new ListaEnlazada();
        this.cursos = new ListaEnlazada();
        this.archivoEstudiante = new ArchivoEstudianteTexto("estudiantes.txt");
        this.archivoEstudiante.crearArchivoEstudiante();
        this.historial = new Pila();
    }

    // Metodos para crear estudiantes - Create
    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.agregarElementoAlFinal(estudiante);
        this.archivoEstudiante.guardarEstudiante(estudiante);
        this.historial.push(new Operacion(
                "AGREGAR_ESTUDIANTE",
                "Se agregó al estudiante " + estudiante.getNombre() + " " + estudiante.getApellido()
                        + " (ID: " + estudiante.getIdentificacion() + ")",
                null));
    }

    public void agregarEstudiante(Estudiante estudiante, boolean alInicio) {
        if (alInicio) {
            this.estudiantes.agregarElementoAlInicio(estudiante);
        } else {
            this.estudiantes.agregarElementoAlFinal(estudiante);
        }
        this.archivoEstudiante.guardarEstudiante(estudiante);
        this.historial.push(new Operacion(
                "AGREGAR_ESTUDIANTE",
                "Se agregó al estudiante " + estudiante.getNombre() + " " + estudiante.getApellido()
                        + " (ID: " + estudiante.getIdentificacion() + ")",
                null));
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
    public boolean actualizarDireccionEstudiante(String identificacion, String nuevaDireccion) {
        Estudiante estudiante = buscarEstudiantePorIdentificacion(identificacion);
        if (estudiante != null) {
            String direccionAnterior = estudiante.getDireccion();
            estudiante.setDireccion(nuevaDireccion);
            this.archivoEstudiante.actualizarEstudiante(estudiante);
            this.historial.push(new Operacion(
                    "ACTUALIZAR_DIRECCION",
                    "Se actualizó la dirección de " + estudiante.getNombre() + " " + estudiante.getApellido()
                            + " (ID: " + identificacion + ") de \"" + direccionAnterior + "\" a \"" + nuevaDireccion
                            + "\"",
                    new Object[] { estudiante, direccionAnterior }));
            System.out.println("Dirección actualizada correctamente.");
            return true;
        } else {
            System.out.println("Estudiante con identificación " + identificacion + " no encontrado.");
            return false;
        }
    }

    public void EliminarEstudiantePorIdentificacion(String identificacion) {
        if (this.estudiantes.getCabeza() == null) {
            return;
        }
        Estudiante estudianteAEliminar = buscarEstudiantePorIdentificacion(identificacion);
        if (estudianteAEliminar == null) {
            return;
        }
        if ((Estudiante) this.estudiantes.getCabeza().getDato() == estudianteAEliminar) {
            this.estudiantes.eliminarElementoAlInicio();
            this.historial.push(new Operacion(
                    "ELIMINAR_ESTUDIANTE",
                    "Se eliminó al estudiante " + estudianteAEliminar.getNombre() + " "
                            + estudianteAEliminar.getApellido() + " (ID: " + identificacion + ")",
                    estudianteAEliminar));
            return;
        }
        Nodo nodoActual = this.estudiantes.getCabeza();
        while (nodoActual.getSiguiente() != null) {
            if ((Estudiante) nodoActual.getSiguiente().getDato() == estudianteAEliminar) {
                nodoActual.setSiguiente(nodoActual.getSiguiente().getSiguiente());
                this.historial.push(new Operacion(
                        "ELIMINAR_ESTUDIANTE",
                        "Se eliminó al estudiante " + estudianteAEliminar.getNombre() + " "
                                + estudianteAEliminar.getApellido() + " (ID: " + identificacion + ")",
                        estudianteAEliminar));
                return;
            }
            nodoActual = nodoActual.getSiguiente();
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
        this.historial.push(new Operacion(
                "AGREGAR_CURSO",
                "Se agregó el curso \"" + curso.getNombreCurso() + "\" (código: " + curso.getCodigoCurso() + ")",
                null));
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

    public void cancelarCursoEstudiante(String idEstudiante, int codigoCurso) {
        Estudiante estudiante = buscarEstudiantePorIdentificacion(idEstudiante);
        if (estudiante == null) {
            System.out.println("Estudiante con identificación " + idEstudiante + " no encontrado.");
            return;
        }
        Curso curso = buscarCursoPorCodigo(codigoCurso);
        if (curso == null) {
            System.out.println("Curso con código " + codigoCurso + " no encontrado.");
            return;
        }
        estudiante.cancelarCurso(curso);
        this.historial.push(new Operacion(
                "CANCELAR_CURSO",
                "El estudiante " + estudiante.getNombre() + " " + estudiante.getApellido()
                        + " (ID: " + idEstudiante + ") canceló su matrícula en \"" + curso.getNombreCurso() + "\"",
                null));
        System.out.println("El estudiante " + estudiante.getNombre() + " " + estudiante.getApellido()
                + " canceló su matrícula en el curso " + curso.getNombreCurso() + ".");
    }

    public void matricularEstudianteEnCurso(String idEstudiante, int codigoCurso) {
        Estudiante estudiante = buscarEstudiantePorIdentificacion(idEstudiante);
        if (estudiante == null) {
            System.out.println("Estudiante con identificación " + idEstudiante + " no encontrado.");
            return;
        }
        Curso curso = buscarCursoPorCodigo(codigoCurso);
        if (curso == null) {
            System.out.println("Curso con código " + codigoCurso + " no encontrado.");
            return;
        }
        estudiante.matricularCurso(curso);
    }

    // Historial de operaciones
    public void mostrarHistorial() {
        if (this.historial.isEmpty()) {
            System.out.println("El historial de operaciones está vacío.");
            return;
        }
        System.out.println("=== HISTORIAL DE OPERACIONES (" + this.historial.getTamaño() + ") ===");
        Pila copia = new Pila();
        while (!this.historial.isEmpty()) {
            Operacion op = (Operacion) this.historial.pop();
            op.mostrarInformacion();
            copia.push(op);
        }
        while (!copia.isEmpty()) {
            this.historial.push(copia.pop());
        }
    }

    public void cargarEstudiantes() {
        try (BufferedReader lector = new BufferedReader(new FileReader("estudiantes.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length != 4) {
                    continue;
                }
                String identificacion = datos[0];
                String nombre = datos[1];
                String apellido = datos[2];
                String direccion = datos[3];
                Estudiante estudiante = new Estudiante(nombre, apellido, identificacion, direccion);
                this.estudiantes.agregarElementoAlFinal(estudiante);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer el archivo. NO SE CARGARON LOS ESTUDIANTES.");
            e.printStackTrace(); // Imprime la traza del error para diagnóstico
        }
    }
}