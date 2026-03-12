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
}