import co.edu.itm.sistemaacademico.models.Curso;
import co.edu.itm.sistemaacademico.models.Docente;
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
            12. Ver cola de espera de un curso
            13. Cancelar matrícula de estudiante en curso
            14. Agregar docente
            15. Listar docentes
            16. Buscar docente por identificación
            17. Eliminar docente
            18. Asignar docente a curso
            19. Salir
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
                    int identificacion = ConsoleUtil.leerEntero("Ingrese la identificación del estudiante:");
                    Estudiante estudianteBuscado = sistemaAcademico.buscarEstudiantePorIdentificacion(identificacion);
                    if (estudianteBuscado != null) {
                        System.out.println("Estudiante encontrado:");
                        estudianteBuscado.mostrarInformacion();
                    } else {
                        System.out.println("Estudiante con identificación " + identificacion + " no encontrado.");
                    }
                    break;
                case 4:
                    int idActualizar = ConsoleUtil.leerEntero("Ingrese la identificación del estudiante:");
                    String nuevaDireccion = ConsoleUtil.leerLinea("Ingrese la nueva dirección del estudiante:");
                    sistemaAcademico.actualizarDireccionEstudiante(idActualizar, nuevaDireccion);
                    break;
                case 5:
                    System.out.println("Lista de estudiantes:");
                    sistemaAcademico.listarEstudiantes();
                    break;
                case 6:
                    int idMatricular = ConsoleUtil.leerEntero("Ingrese la identificación del estudiante:");
                    int codigoMatricular = ConsoleUtil.leerEntero("Ingrese el código del curso:");
                    sistemaAcademico.matricularEstudianteEnCurso(idMatricular, codigoMatricular);
                    break;
                case 7:
                    int idListar = ConsoleUtil.leerEntero("Ingrese la identificación del estudiante:");
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
                    int idEliminar = ConsoleUtil.leerEntero("Ingrese la identificación del estudiante:");
                    sistemaAcademico.EliminarEstudiantePorIdentificacion(idEliminar);
                    break;
                case 10:
                    sistemaAcademico.mostrarHistorial();
                    break;
                case 11:
                    sistemaAcademico.deshacerUltimaOperacion();
                    break;
                case 12:
                    int codigoColaEspera = ConsoleUtil.leerEntero("Ingrese el código del curso:");
                    sistemaAcademico.listarColaEsperaCurso(codigoColaEspera);
                    break;
                case 13:
                    int idCancelar = ConsoleUtil.leerEntero("Ingrese la identificación del estudiante:");
                    int codigoCancelar = ConsoleUtil.leerEntero("Ingrese el código del curso:");
                    sistemaAcademico.cancelarCursoEstudiante(idCancelar, codigoCancelar);
                    break;
                case 14:
                    String nombreDocente = ConsoleUtil.leerLinea("Ingrese el nombre del docente:");
                    String apellidoDocente = ConsoleUtil.leerLinea("Ingrese el apellido del docente:");
                    int idDocente = ConsoleUtil.leerEntero("Ingrese la identificación del docente:");
                    int numeroCursos = ConsoleUtil.leerEntero("Ingrese el número de cursos del docente:");
                    String direccionDocente = ConsoleUtil.leerLinea("Ingrese la dirección del docente:");
                    sistemaAcademico.agregarDocente(new Docente(nombreDocente, apellidoDocente, idDocente, numeroCursos, direccionDocente));
                    System.out.println("Docente agregado: " + nombreDocente + " " + apellidoDocente);
                    break;
                case 15:
                    System.out.println("Lista de docentes:");
                    sistemaAcademico.listarDocentes();
                    break;
                case 16:
                    int idBuscarDocente = ConsoleUtil.leerEntero("Ingrese la identificación del docente:");
                    Docente docenteBuscado = sistemaAcademico.buscarDocentePorIdentificacion(idBuscarDocente);
                    if (docenteBuscado != null) {
                        System.out.println("Docente encontrado:");
                        docenteBuscado.mostrarInformacion();
                    } else {
                        System.out.println("Docente con identificación " + idBuscarDocente + " no encontrado.");
                    }
                    break;
                case 17:
                    int idEliminarDocente = ConsoleUtil.leerEntero("Ingrese la identificación del docente:");
                    sistemaAcademico.eliminarDocente(idEliminarDocente);
                    break;
                case 18:
                    int codigoAsignar = ConsoleUtil.leerEntero("Ingrese el código del curso:");
                    int idDocenteAsignar = ConsoleUtil.leerEntero("Ingrese la identificación del docente:");
                    sistemaAcademico.asignarDocenteACurso(codigoAsignar, idDocenteAsignar);
                    break;
                case 19:
                    System.out.println("Saliendo del sistema académico...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }

        }
    }
}