import java.time.LocalDate;

public class Docente {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    private String correo;
    private LocalDate fechaVinculacion;
    private int numeroCursos;

    // Constructor vacío
    public Docente() {
    }

    // Constructor con parámetros
    public Docente(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaVinculacion = LocalDate.now(); // Asignar la fecha actual como fecha de vinculación
    }

    // Getters

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDate getFechaVinculacion() {
        return fechaVinculacion;
    }

    public int getNumeroCursos() {
        return numeroCursos;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNumeroCursos(byte numeroCursos) {
        this.numeroCursos = numeroCursos;
    }

}
