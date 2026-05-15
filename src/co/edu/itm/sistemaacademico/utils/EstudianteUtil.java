package co.edu.itm.sistemaacademico.utils;

import co.edu.itm.sistemaacademico.models.Estudiante;

public class EstudianteUtil {
    public static Estudiante crearEstudianteDesdeEntrada() {
        String nombre = ConsoleUtil.leerLinea("Ingrese el nombre del estudiante:");
        String apellido = ConsoleUtil.leerLinea("Ingrese el apellido del estudiante:");
        int identificacion = ConsoleUtil.leerEntero("Ingrese la identificación del estudiante:");
        String direccion = ConsoleUtil.leerLinea("Ingrese la dirección del estudiante:");
        return new Estudiante(nombre, apellido, identificacion, direccion);
    }
}
