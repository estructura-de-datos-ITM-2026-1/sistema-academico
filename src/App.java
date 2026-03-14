import java.util.Scanner;

import co.edu.itm.sistemaacademico.models.Estudiante;
import co.edu.itm.sistemaacademico.models.SistemaAcademico;

public class App {
    private static final String MENU = """
            Elija una opción:
            1. Agregar estudiante al final de la lista
            2. Agregar estudiante al inicio de la lista
            3. Listar estudiantes
            4. Buscar estudiante por identificación
            5. Actualizar dirección de estudiante
            6. Salir
            """;
    private static final Scanner scanner = new Scanner(System.in);

    public static Estudiante crearEstudianteDesdeEntrada() {
        System.out.println("Ingrese el nombre del estudiante:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del estudiante:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese la identificación del estudiante:");
        String identificacion = scanner.nextLine();
        System.out.println("Ingrese la dirección del estudiante:");
        String direccion = scanner.nextLine();
        return new Estudiante(nombre, apellido, identificacion, direccion);
    }

    public static void main(String[] args) throws Exception {

        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        System.out.println("¡Bienvenido al sistema académico!");

        while (true) {

            System.out.println("\n" + MENU);
            int opcion = Integer.parseInt(scanner.nextLine());

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
                    System.out.println("Lista de estudiantes:");
                    sistemaAcademico.listarEstudiantes();
                    break;
                case 4:
                    System.out.println("Ingrese la identificación del estudiante:");
                    String identificacion = scanner.nextLine();
                    Estudiante estudianteBuscado = sistemaAcademico.buscarEstudiantePorIdentificacion(identificacion);
                    if (estudianteBuscado != null) {
                        System.out.println("Estudiante encontrado:");
                        estudianteBuscado.mostrarInformacion();
                    } else {
                        System.out.println("Estudiante con identificación " + identificacion + " no encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("Ingrese la identificación del estudiante:");
                    String idActualizar = scanner.nextLine();
                    System.out.println("Ingrese la nueva dirección del estudiante:");
                    String nuevaDireccion = scanner.nextLine();
                    sistemaAcademico.actualizarDireccionEstudiante(idActualizar, nuevaDireccion);
                    break;
                case 6:
                    System.out.println("Saliendo del sistema académico...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }

        }
    }
}