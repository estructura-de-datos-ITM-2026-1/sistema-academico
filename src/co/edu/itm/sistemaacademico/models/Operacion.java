package co.edu.itm.sistemaacademico.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Operacion {
    private String tipo;
    private String descripcion;
    private LocalDateTime timestamp;
    private Object datosAntes;

    public Operacion(String tipo, String descripcion, Object datosAntes) {
        // El tipo puede ser "AGREGAR_ESTUDIANTE", "ELIMINAR_ESTUDIANTE",
        // "AGREGAR_CURSO", etc.
        this.tipo = tipo;
        this.descripcion = descripcion; // Descripción detallada de la operación, por ejemplo: "Agregó el estudiante
                                        // Juan Pérez con ID 12345"
        this.timestamp = LocalDateTime.now(); // Marca de tiempo de la operación
        this.datosAntes = datosAntes; // Puede ser un objeto que represente el estado antes de la operación, por
                                      // ejemplo, el estudiante antes de ser eliminado
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Object getDatosAntes() {
        return datosAntes;
    }

    public void mostrarInformacion() {
        // [14/04/2026 10:32:05] AGREGAR_ESTUDIANTE — Se agregó al estudiante Juan Pérez
        // (123456)
        // https://keepcoding.io/blog/java-time-format-datetimeformatter-como-se-usa/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("[" + timestamp.format(formatter) + "] " + tipo + " — " + descripcion);
    }
}
