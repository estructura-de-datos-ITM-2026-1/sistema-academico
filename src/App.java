import co.edu.itm.sistemaacademico.models.Curso;
import co.edu.itm.sistemaacademico.models.Estudiante;
import co.edu.itm.sistemaacademico.models.SistemaAcademico;
import co.edu.itm.sistemaacademico.utils.ConsoleUtil;
import co.edu.itm.sistemaacademico.utils.CursoUtil;
import co.edu.itm.sistemaacademico.utils.EstudianteUtil;

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
            9. Eliminar estudiante
            10. Ver historial de operaciones
            11. Deshacer última operación
            12. Salir
            """;

    public static void main(String[] args) throws Exception {

        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        sistemaAcademico.cargarEstudiantes(); // Carga los estudiantes desde el archivo al iniciar el programa

        System.out.println("¡Bienvenido al sistema académico!");

        while (true) {

            System.out.println("\n" + MENU);
            int opcion = ConsoleUtil.leerEntero("Ingrese su opción: ");

            switch (opcion) {
                case 1:
                    Estudiante estudiante = EstudianteUtil.crearEstudianteDesdeEntrada();
                    sistemaAcademico.agregarEstudiante(estudiante);
                    System.out
                            .println("Estudiante agregado: " + estudiante.getNombre() + " " + estudiante.getApellido());

                    break;
                case 2:
                    Curso curso = CursoUtil.crearCursoDesdeEntrada();
                    sistemaAcademico.agregarCurso(curso);
                    System.out.println("Curso agregado: " + curso.getNombreCurso());
                    break;
                case 3:
                    String identificacion = ConsoleUtil.leerLinea("Ingrese la identificación del estudiante:");
                    Estudiante estudianteBuscado = sistemaAcademico.buscarEstudiantePorIdentificacion(identificacion);
                    if (estudianteBuscado != null) {
                        System.out.println("Estudiante encontrado:");
                        estudianteBuscado.mostrarInformacion();
                    } else {
                        System.out.println("Estudiante con identificación " + identificacion + " no encontrado.");
                    }
                    break;
                case 4:
                    String idActualizar = ConsoleUtil.leerLinea("Ingrese la identificación del estudiante:");
                    String nuevaDireccion = ConsoleUtil.leerLinea("Ingrese la nueva dirección del estudiante:");
                    sistemaAcademico.actualizarDireccionEstudiante(idActualizar, nuevaDireccion);
                    break;
                case 5:
                    System.out.println("Lista de estudiantes:");
                    sistemaAcademico.listarEstudiantes();
                    break;
                case 6:
                    String idMatricular = ConsoleUtil.leerLinea("Ingrese la identificación del estudiante:");
                    Estudiante estudianteMatricular = sistemaAcademico.buscarEstudiantePorIdentificacion(idMatricular);
                    if (estudianteMatricular == null) {
                        System.out.println("Estudiante con identificación " + idMatricular + " no encontrado.");
                        break;
                    }
                    int codigoMatricular = ConsoleUtil.leerEntero("Ingrese el código del curso:");
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
                    String idListar = ConsoleUtil.leerLinea("Ingrese la identificación del estudiante:");
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
                    String idEliminar = ConsoleUtil.leerLinea("Ingrese la identificación del estudiante:");
                    sistemaAcademico.EliminarEstudiantePorIdentificacion(idEliminar);
                    break;
                case 10:
                    sistemaAcademico.mostrarHistorial();
                    break;
                case 11:
                    sistemaAcademico.deshacerUltimaOperacion();
                    break;
                case 12:
                    System.out.println("Saliendo del sistema académico...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }

        }
    }
}