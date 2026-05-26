package co.edu.itm.sistemaacademico.models;

import java.io.BufferedReader;
import java.io.FileReader;

import co.edu.itm.sistemaacademico.archivos.ArchivoDocenteTexto;
import co.edu.itm.sistemaacademico.archivos.ArchivoEstudianteTexto;
import co.edu.itm.sistemaacademico.estructuras.ArbolDocentes;
import co.edu.itm.sistemaacademico.estructuras.ListaEnlazada;
import co.edu.itm.sistemaacademico.estructuras.Nodo;
import co.edu.itm.sistemaacademico.estructuras.Pila;
import co.edu.itm.sistemaacademico.utils.CursoUtil;

public class SistemaAcademico {
    private ListaEnlazada estudiantes;
    private ArbolDocentes docentes;
    private ListaEnlazada cursos;
    private ArchivoEstudianteTexto archivoEstudiante;
    private ArchivoDocenteTexto archivoDocente;
    private Pila historial;

    public SistemaAcademico() {
        this.estudiantes = new ListaEnlazada();
        this.docentes = new ArbolDocentes();
        this.cursos = new ListaEnlazada();
        this.archivoEstudiante = new ArchivoEstudianteTexto("estudiantes.txt");
        this.archivoEstudiante.crearArchivoEstudiante();
        this.archivoDocente = new ArchivoDocenteTexto("docentes.txt");
        this.archivoDocente.crearArchivoDocente();
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
    public Estudiante buscarEstudiantePorIdentificacion(int identificacion) {
        Nodo nodoActual = this.estudiantes.getCabeza();
        while (nodoActual != null) {
            Estudiante estudiante = (Estudiante) nodoActual.getDato();
            if (estudiante.getIdentificacion() == identificacion) {
                return estudiante;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return null; // Retorna null si no se encuentra el estudiante
    }

    // Metodo para actualizar la dirección de un estudiante - Update
    public boolean actualizarDireccionEstudiante(int identificacion, String nuevaDireccion) {
        Estudiante estudiante = buscarEstudiantePorIdentificacion(identificacion);
        if (estudiante != null) {
            String direccionAnterior = estudiante.getDireccion();
            estudiante.setDireccion(nuevaDireccion);
            this.archivoEstudiante.actualizarEstudiante(estudiante);
            this.historial.push(new Operacion(
                    "ACTUALIZAR_DIRECCION",
                    "Se actualizó la dirección de " + estudiante.getNombre() + " " + estudiante.getApellido()
                            + " (ID: " + identificacion + ") de \"" + direccionAnterior + "\" a \"" + nuevaDireccion + "\"",
                    new Object[] { estudiante, direccionAnterior }));
            System.out.println("Dirección actualizada correctamente.");
            return true;
        } else {
            System.out.println("Estudiante con identificación " + identificacion + " no encontrado.");
            return false;
        }
    }

    public void EliminarEstudiantePorIdentificacion(int identificacion) {
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
        this.docentes.insertar(docente);
        this.archivoDocente.guardarDocente(docente);
        this.historial.push(new Operacion(
                "AGREGAR_DOCENTE",
                "Se agregó al docente " + docente.getNombre() + " " + docente.getApellido()
                        + " (ID: " + docente.getIdentificacion() + ")",
                null));
    }

    public void listarDocentes() {
        this.docentes.recorridoInorden(this.docentes.getRaiz());
    }

    public Docente buscarDocentePorIdentificacion(int identificacion) {
        return (Docente) this.docentes.buscar(identificacion);
    }

    public void eliminarDocente(int identificacion) {
        Docente docente = buscarDocentePorIdentificacion(identificacion);
        if (docente == null) {
            System.out.println("Docente con identificación " + identificacion + " no encontrado.");
            return;
        }
        this.docentes.eliminar(identificacion);
        this.archivoDocente.eliminarDocente(identificacion);
        this.historial.push(new Operacion(
                "ELIMINAR_DOCENTE",
                "Se eliminó al docente " + docente.getNombre() + " " + docente.getApellido()
                        + " (ID: " + identificacion + ")",
                docente));
    }

    public void asignarDocenteACurso(int codigoCurso, int idDocente) {
        Curso curso = buscarCursoPorCodigo(codigoCurso);
        if (curso == null) {
            System.out.println("Curso con código " + codigoCurso + " no encontrado.");
            return;
        }
        Docente docente = buscarDocentePorIdentificacion(idDocente);
        if (docente == null) {
            System.out.println("Docente con identificación " + idDocente + " no encontrado.");
            return;
        }
        curso.setDocente(docente);
        this.historial.push(new Operacion(
                "ASIGNAR_DOCENTE",
                "Se asignó al docente " + docente.getNombre() + " " + docente.getApellido()
                        + " (ID: " + idDocente + ") al curso \"" + curso.getNombreCurso() + "\"",
                null));
        System.out.println("Docente " + docente.getNombre() + " " + docente.getApellido()
                + " asignado al curso " + curso.getNombreCurso() + ".");
    }

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

    // 4a — Cola de espera
    public void listarColaEsperaCurso(int codigoCurso) {
        Curso curso = buscarCursoPorCodigo(codigoCurso);
        if (curso == null) {
            System.out.println("Curso con código " + codigoCurso + " no encontrado.");
            return;
        }
        curso.listarColaEspera();
    }

    // 4b — Cancelar matrícula con propagación automática desde la cola
    public void cancelarCursoEstudiante(int idEstudiante, int codigoCurso) {
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

    // 4b/4c — Matricular con registro diferenciado en historial
    public void matricularEstudianteEnCurso(int idEstudiante, int codigoCurso) {
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
        Horario horarioElegido = null;
        if (!curso.estaLleno()) {
            horarioElegido = CursoUtil.seleccionarHorarioInteractivo(curso);
        }
        boolean matriculado = estudiante.matricularCurso(curso, horarioElegido);
        if (matriculado) {
            String horarioTxt = horarioElegido == null
                    ? ""
                    : " | horario días=" + horarioElegido.getDias()
                            + " " + horarioElegido.getHoraInicio() + "h-" + horarioElegido.getHoraFin() + "h";
            this.historial.push(new Operacion(
                    "MATRICULAR_CURSO",
                    "El estudiante " + estudiante.getNombre() + " " + estudiante.getApellido()
                            + " (ID: " + idEstudiante + ") se matriculó en \"" + curso.getNombreCurso() + "\""
                            + horarioTxt,
                    null));
            System.out.println("Estudiante " + estudiante.getNombre() + " " + estudiante.getApellido()
                    + " matriculado en el curso " + curso.getNombreCurso() + horarioTxt + ".");
        } else {
            this.historial.push(new Operacion(
                    "ENCOLAR_ESTUDIANTE",
                    "El estudiante " + estudiante.getNombre() + " " + estudiante.getApellido()
                            + " (ID: " + idEstudiante + ") fue puesto en cola de espera en \""
                            + curso.getNombreCurso() + "\"",
                    null));
        }
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

    public void deshacerUltimaOperacion() {
        if (this.historial.isEmpty()) {
            System.out.println("No hay operaciones para deshacer.");
            return;
        }
        Operacion ultima = (Operacion) this.historial.pop();
        switch (ultima.getTipo()) {
            case "AGREGAR_ESTUDIANTE":
                System.out.println("No se puede deshacer automáticamente: " + ultima.getDescripcion());
                break;
            case "ELIMINAR_ESTUDIANTE":
                Estudiante estudianteRestaurado = (Estudiante) ultima.getDatosAntes();
                this.estudiantes.agregarElementoAlFinal(estudianteRestaurado);
                this.archivoEstudiante.guardarEstudiante(estudianteRestaurado);
                System.out.println("Operación deshecha: se restauró al estudiante "
                        + estudianteRestaurado.getNombre() + " " + estudianteRestaurado.getApellido());
                break;
            case "ACTUALIZAR_DIRECCION":
                Object[] datos = (Object[]) ultima.getDatosAntes();
                Estudiante estudianteActualizar = (Estudiante) datos[0];
                String direccionAnterior = (String) datos[1];
                estudianteActualizar.setDireccion(direccionAnterior);
                this.archivoEstudiante.actualizarEstudiante(estudianteActualizar);
                System.out.println("Operación deshecha: se restauró la dirección de "
                        + estudianteActualizar.getNombre() + " " + estudianteActualizar.getApellido()
                        + " a \"" + direccionAnterior + "\"");
                break;
            case "AGREGAR_CURSO":
            case "MATRICULAR_CURSO":
            case "ENCOLAR_ESTUDIANTE":
            case "CANCELAR_CURSO":
                System.out.println("No se puede deshacer automáticamente: " + ultima.getDescripcion());
                break;
            default:
                System.out.println("Tipo de operación desconocido: " + ultima.getTipo());
                break;
        }
    }

    public void cargarDocentes() {
        try (BufferedReader lector = new BufferedReader(new FileReader("docentes.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length != 5) {
                    continue;
                }
                int identificacion = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido = datos[2];
                int numeroCursos = Integer.parseInt(datos[3]);
                String direccion = datos[4];
                this.docentes.insertar(new Docente(nombre, apellido, identificacion, numeroCursos, direccion));
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer el archivo. NO SE CARGARON LOS DOCENTES.");
            e.printStackTrace();
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
                int identificacion = Integer.parseInt(datos[0]);
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