package co.edu.itm.sistemaacademico.models;

import co.edu.itm.sistemaacademico.estructuras.ListaEnlazada;

public class SistemaAcademico {
    private ListaEnlazada estudiantes;

    public SistemaAcademico() {
        this.estudiantes = new ListaEnlazada();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.agregarElementoAlFinal(estudiante);
    }

    public int getCantidadEstudiantes() {
        return this.estudiantes.getTamaño();
    }
}
