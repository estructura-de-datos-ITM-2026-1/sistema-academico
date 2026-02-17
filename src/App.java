import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {
        Estudiante estudiante = new Estudiante("Santiago", "Suarez", LocalDate.of(2000, 5, 15), "Calle 123", "555-1234",
                "santiago.suarez@example.com", 8.5f, 3);
        System.out.println("Nombre del estudiante: " + estudiante.getNombre());
    }
}
