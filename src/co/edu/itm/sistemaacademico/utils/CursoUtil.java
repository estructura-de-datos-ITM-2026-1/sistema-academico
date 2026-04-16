package co.edu.itm.sistemaacademico.utils;

import co.edu.itm.sistemaacademico.models.Curso;

public class CursoUtil {
    public static Curso crearCursoDesdeEntrada() {
        int codigoCurso = ConsoleUtil.leerEntero("Ingrese el código del curso:");
        String nombreCurso = ConsoleUtil.leerLinea("Ingrese el nombre del curso:");
        int creditos = ConsoleUtil.leerEntero("Ingrese los créditos del curso:");
        int cupoMaximo = ConsoleUtil.leerEntero("Ingrese el cupo máximo del curso:");
        return new Curso(codigoCurso, nombreCurso, creditos, cupoMaximo);
    }
}
