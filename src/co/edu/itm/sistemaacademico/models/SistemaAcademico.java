package co.edu.itm.sistemaacademico.models;

import co.edu.itm.sistemaacademico.estructuras.ListaEnlazada;
import co.edu.itm.sistemaacademico.estructuras.Nodo;

public class SistemaAcademico {
    private ListaEnlazada estudiantes;

    public SistemaAcademico() {
        this.estudiantes = new ListaEnlazada();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.agregarElementoAlFinal(estudiante);
    }

    public void agregarEstudiante(Estudiante estudiante, boolean alInicio) {
        if (alInicio) {
            this.estudiantes.agregarElementoAlInicio(estudiante);
        } else {
            this.estudiantes.agregarElementoAlFinal(estudiante);
        }
    }

    public void agregarEstudiante(Estudiante estudiante, int posicion) {
        this.estudiantes.agregarElementoEnPosicion(estudiante, posicion);
    }

    public int getCantidadEstudiantes() {
        return this.estudiantes.getTamaño();
    }

    public void listarEstudiantes() {
        Nodo nodoActual = this.estudiantes.getCabeza(); // Se crea una variable nodoActual que se inicializa con la
                                                        // cabeza de la lista
        while (nodoActual != null) {
            Estudiante estudiante = (Estudiante) nodoActual.getDato(); // Se obtiene el estudiante del nodo actual y se
                                                                       // imprime su información
            System.out.println("Nombre: " + estudiante.getNombre() + " " + estudiante.getApellido()
                    + ", Identificación: " + estudiante.getIdentificacion());
            nodoActual = nodoActual.getSiguiente();
        }
    }

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

    public String actualizarDireccionEstudiante(String identificacion, String nuevaDireccion) {
        Estudiante estudiante = buscarEstudiantePorIdentificacion(identificacion);
        estudiante.setDireccion(nuevaDireccion);
        return "La dirección del estudiante con identificación " + identificacion + " ha sido actualizada a: "
                + nuevaDireccion;
    }

    // Metodo actualizar telefono estudiante

}