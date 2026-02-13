public class Asignatura {
    private String nombre;
    private String codigo;
    private int creditos;
    private int numeroGrupos;
    private String horario;

    // Atributo estatico para el numero maximo de estudiantes por grupo
    private static final int MAXIMO_NUMERO_ESTUDIANTES = 30;

    // Constructores
    public Asignatura() {
    }

    public Asignatura(String nombre, String codigo, int creditos) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getNumeroGrupos() {
        return numeroGrupos;
    }

    public String getHorario() {
        return horario;
    }

    public static int getMaximoNumeroEstudiantes() {
        return MAXIMO_NUMERO_ESTUDIANTES;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroGrupos(int numeroGrupos) {
        this.numeroGrupos = numeroGrupos;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

}
