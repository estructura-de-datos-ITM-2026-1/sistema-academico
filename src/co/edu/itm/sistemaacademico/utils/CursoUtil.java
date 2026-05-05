package co.edu.itm.sistemaacademico.utils;

import co.edu.itm.sistemaacademico.estructuras.ListaDoblementeEnlazada;
import co.edu.itm.sistemaacademico.estructuras.NodoDoble;
import co.edu.itm.sistemaacademico.models.Curso;
import co.edu.itm.sistemaacademico.models.Horario;

public class CursoUtil {
    public static Curso crearCursoDesdeEntrada() {
        int codigoCurso = ConsoleUtil.leerEntero("Ingrese el código del curso:");
        String nombreCurso = ConsoleUtil.leerLinea("Ingrese el nombre del curso:");
        int creditos = ConsoleUtil.leerEntero("Ingrese los créditos del curso:");
        int cupoMaximo = ConsoleUtil.leerEntero("Ingrese el cupo máximo del curso:");
        Curso curso = new Curso(codigoCurso, nombreCurso, creditos, cupoMaximo);
        agregarHorariosDesdeEntrada(curso);
        return curso;
    }

    public static void agregarHorariosDesdeEntrada(Curso curso) {
        int cantidad = ConsoleUtil.leerEntero("¿Cuántos horarios disponibles tendrá el curso?");
        for (int i = 1; i <= cantidad; i++) {
            System.out.println("--- Horario " + i + " de " + cantidad + " ---");
            String dias = ConsoleUtil.leerLinea("Días (ej. 'Lun(L),Mar(M),Mie(W),Jue(J),Vie(V),Sab(S)'):");
            int horaInicio = ConsoleUtil.leerEntero("Hora de inicio (0-23):");
            int horaFin = ConsoleUtil.leerEntero("Hora de fin (0-23):");
            curso.agregarHorario(new Horario(dias, horaInicio, horaFin));
        }
    }

    public static Horario seleccionarHorarioInteractivo(Curso curso) {
        ListaDoblementeEnlazada horarios = curso.getHorariosDisponibles();
        if (horarios.estaVacia()) {
            return null;
        }
        if (horarios.getTamaño() == 1) {
            return (Horario) horarios.getCabeza().getDato();
        }

        NodoDoble actual = horarios.getCabeza();
        int posicion = 1;
        while (true) {
            System.out.println("\n--- Selección de horario para el curso " + curso.getNombreCurso() + " ---");
            mostrarPosicion(actual, posicion, horarios.getTamaño());
            String opcion = ConsoleUtil.leerLinea(
                    "Opciones: [s] siguiente | [a] anterior | [e] elegir este horario");

            switch (opcion.trim().toLowerCase()) {
                case "s":
                    if (actual.getSiguiente() != null) {
                        actual = actual.getSiguiente();
                        posicion++;
                    } else {
                        System.out.println("Ya estás en el último horario.");
                    }
                    break;
                case "a":
                    if (actual.getAnterior() != null) {
                        actual = actual.getAnterior();
                        posicion--;
                    } else {
                        System.out.println("Ya estás en el primer horario.");
                    }
                    break;
                case "e":
                    return (Horario) actual.getDato();
                default:
                    System.out.println("Opción no válida. Use 's', 'a' o 'e'.");
            }
        }
    }

    private static void mostrarPosicion(NodoDoble actual, int posicion, int total) {
        Horario h = (Horario) actual.getDato();
        System.out.println("Horario " + posicion + " de " + total
                + " | días=" + h.getDias()
                + " " + h.getHoraInicio() + "h-" + h.getHoraFin() + "h");
    }
}
