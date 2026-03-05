import java.util.Scanner;

import co.edu.itm.sistemaacademico.models.Estudiante;
import co.edu.itm.sistemaacademico.models.SistemaAcademico;

public class App {
    private static final String MENU = """
            Elija una opción:
            1. Agregar estudiante
            2. Eliminar estudiante
            3. Listar estudiantes
            4. Ver cantidad de estudiantes
            5. Salir
            """;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        System.out.println("¡Bienvenido al sistema académico!");

        while (true) {
            System.out.println(MENU);
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de leer el número

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del estudiante:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el apellido del estudiante:");
                    String apellido = scanner.nextLine();
                    Estudiante estudiante = new Estudiante(nombre, apellido);
                    sistemaAcademico.agregarEstudiante(estudiante);
                    System.out
                            .println("Estudiante agregado: " + estudiante.getNombre() + " " + estudiante.getApellido());
                    break;
                case 2:
                    System.out.println("Opción no implementada aún.");
                    break;
                case 3:
                    System.out.println("Opción no implementada aún.");
                    break;
                case 4:
                    System.out.println("Cantidad de estudiantes: " + sistemaAcademico.getCantidadEstudiantes());
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }

        }
    }
}
