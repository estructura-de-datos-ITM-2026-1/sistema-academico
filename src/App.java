import java.util.Scanner;

import co.edu.itm.sistemaacademico.models.Estudiante;
import co.edu.itm.sistemaacademico.models.SistemaAcademico;

public class App {
    private static final String MENU = """
            Elija una opción:
            1. Agregar estudiante al final de la lista
            2. Agregar estudiante al inicio de la lista
            3. Agregar estudiante en una posición específica
            4. Buscar estudiante por identificación
            5. Actualizar dirección de estudiante
            6. Listar estudiantes
            7. Ver cantidad de estudiantes
            8. Salir
            """;
    private static final Scanner scanner = new Scanner(System.in);

    public static Estudiante crearEstudianteDesdeEntrada() {
        System.out.println("Ingrese el nombre del estudiante:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del estudiante:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese la identificación del estudiante:");
        String identificacion = scanner.nextLine();

        return new Estudiante(nombre, apellido, identificacion);
    }

    public static void main(String[] args) throws Exception {

        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        System.out.println("¡Bienvenido al sistema académico!");

        while (true) {

            System.out.println("\n" + MENU);
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de leer el número

            switch (opcion) {
                case 1:
                    Estudiante estudiante = crearEstudianteDesdeEntrada();
                    sistemaAcademico.agregarEstudiante(estudiante);
                    System.out
                            .println("Estudiante agregado: " + estudiante.getNombre() + " " + estudiante.getApellido());
                    break;
                case 2:
                    Estudiante estudianteInicio = crearEstudianteDesdeEntrada();
                    sistemaAcademico.agregarEstudiante(estudianteInicio, true);
                    System.out.println("Estudiante agregado al inicio: " + estudianteInicio.getNombre() + " "
                            + estudianteInicio.getApellido());
                    break;
                case 3:
                    Estudiante estudiantePosicion = crearEstudianteDesdeEntrada();
                    System.out.println("Ingrese la posición donde desea agregar el estudiante:");
                    int posicion = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea después de leer el número
                    sistemaAcademico.agregarEstudiante(estudiantePosicion, posicion - 1); // Restar 1 para ajustar a
                                                                                          // índice basado en cero
                    System.out.println("Estudiante agregado en la posición " + posicion + ": "
                            + estudiantePosicion.getNombre() + " " + estudiantePosicion.getApellido());
                    break;
                case 4:
                    System.out.println("Ingrese la identificación del estudiante:");
                    String identificacion = scanner.nextLine();
                    Estudiante estudianteBuscado = sistemaAcademico.buscarEstudiantePorIdentificacion(identificacion);
                    if (estudianteBuscado != null) {
                        System.out.println("Estudiante encontrado: " + estudianteBuscado.getNombre() + " "
                                + estudianteBuscado.getApellido());
                    } else {
                        System.out.println("No se encontró un estudiante con la identificación: " + identificacion);
                    }
                    break;
                case 5:
                    System.out.println("Ingrese la identificación del estudiante:");
                    String identificacionActualizar = scanner.nextLine();
                    System.out.println("Ingrese la nueva dirección del estudiante:");
                    String nuevaDireccion = scanner.nextLine();
                    String resultado = sistemaAcademico.actualizarDireccionEstudiante(identificacionActualizar,
                            nuevaDireccion);
                    System.out.println(resultado);
                    break;
                case 6:
                    System.out.println("Lista de estudiantes:");
                    sistemaAcademico.listarEstudiantes();
                    break;
                case 7:
                    int cantidadEstudiantes = sistemaAcademico.getCantidadEstudiantes();
                    System.out.println("Cantidad de estudiantes: " + cantidadEstudiantes);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }

        }
    }
}