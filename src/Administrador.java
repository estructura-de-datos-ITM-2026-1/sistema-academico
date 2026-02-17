public class Administrador extends Persona {
    private String facultad;

    public Administrador(String nombre, String apellido, String facultad) {
        super(nombre, apellido);
        this.facultad = facultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public double calcularCostoMatricula(Estudiante estudiante) {
        return 1000000.0;
    }

    public String asignarCurso(Docente docente, String curso) {
        return "El curso fue asignado correctamente";
    }

    public String removerCurso(Docente docente, String curso) {
        return "El curso fue removido correctamente";
    }

}
