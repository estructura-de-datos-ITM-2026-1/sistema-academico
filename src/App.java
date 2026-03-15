import java.util.Scanner;

import co.edu.itm.sistemaacademico.models.Curso;
import co.edu.itm.sistemaacademico.models.Estudiante;
import co.edu.itm.sistemaacademico.models.SistemaAcademico;

public class App {
    private static final String MENU = """
            Elija una opción:
            1. Agregar estudiante
            2. Crear curso
            3. Buscar estudiante por identificación
            4. Actualizar dirección de estudiante
            5. Listar estudiantes
            6. Matricular estudiante en curso
            7. Listar cursos matriculados por estudiante
            8. Listar cursos
            9. Salir
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
                    System.out.println("Ingrese el código del curso: ");
                    int codigoCurso = Integer.parseInt(scanner.nextLine());
                    System.out.println("Ingrese el nombre del curso: ");
                    String nombreCurso = scanner.nextLine();
                    System.out.println("Ingrese los créditos del curso: ");
                    int creditos = Integer.parseInt(scanner.nextLine());
                    Curso curso = new Curso(codigoCurso, nombreCurso, creditos);
                    sistemaAcademico.agregarCurso(curso);
                    System.out.println("Curso agregado: " + curso.getNombreCurso());
                    break;
                case 3:
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
                case 4:
                    System.out.println("Ingrese la identificación del estudiante:");
                    String idActualizar = scanner.nextLine();
                    System.out.println("Ingrese la nueva dirección del estudiante:");
                    String nuevaDireccion = scanner.nextLine();
                    sistemaAcademico.actualizarDireccionEstudiante(idActualizar, nuevaDireccion);
                    break;
                case 5:
                    System.out.println("Lista de estudiantes:");
                    sistemaAcademico.listarEstudiantes();
                    break;
                case 6:
                    System.out.println("Ingrese la identificación del estudiante:");
                    String idMatricular = scanner.nextLine();
                    Estudiante estudianteMatricular = sistemaAcademico.buscarEstudiantePorIdentificacion(idMatricular);
                    if (estudianteMatricular == null) {
                        System.out.println("Estudiante con identificación " + idMatricular + " no encontrado.");
                        break;
                    }
                    System.out.println("Ingrese el código del curso:");
                    int codigoMatricular = Integer.parseInt(scanner.nextLine());
                    Curso cursoMatricular = sistemaAcademico.buscarCursoPorCodigo(codigoMatricular);
                    if (cursoMatricular == null) {
                        System.out.println("Curso con código " + codigoMatricular + " no encontrado.");
                        break;
                    }
                    estudianteMatricular.matricularCurso(cursoMatricular);
                    System.out.println("Estudiante " + estudianteMatricular.getNombre() + " matriculado en el curso "
                            + cursoMatricular.getNombreCurso());
                    break;
                case 7:
                    System.out.println("Ingrese la identificación del estudiante:");
                    String idListar = scanner.nextLine();
                    Estudiante estudianteListar = sistemaAcademico.buscarEstudiantePorIdentificacion(idListar);
                    if (estudianteListar == null) {
                        System.out.println("Estudiante con identificación " + idListar + " no encontrado.");
                        break;
                    }
                    estudianteListar.listarCursosMatriculados();
                    break;
                case 8:
                    System.out.println("Lista de cursos:");
                    sistemaAcademico.listarCursos();
                    break;
                case 9:
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