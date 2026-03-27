package co.edu.itm.sistemaacademico.utils;

import java.util.Scanner;

public class ConsoleUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
            }
        }
    }

    public static String leerLinea(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }

}
