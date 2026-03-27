package co.edu.itm.sistemaacademico.archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import co.edu.itm.sistemaacademico.models.Estudiante;

public class ArchivoEstudianteTexto {
    private String nombreArchivo;

    public ArchivoEstudianteTexto(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void crearArchivoEstudiante() {
        File archivoEstudiantes = new File(this.nombreArchivo);
        try {
            if (archivoEstudiantes.createNewFile()) {
                System.out.println("Archivo creado: " + archivoEstudiantes.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al crear el archivo.");
            e.printStackTrace(); // Imprime la traza del error para diagnóstico
        }
    }

    public void escribirTexto(String texto) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(this.nombreArchivo, true))) {
            escritor.write(texto);
            escritor.newLine(); // Agrega una nueva línea después del texto
            System.out.println("Texto escrito en el archivo: " + texto);
        } catch (Exception e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace(); // Imprime la traza del error para diagnóstico
        }
    }

    public void guardarEstudiante(Estudiante estudiante) {
        String linea = estudiante.getIdentificacion() + ";" + estudiante.getNombre() + ";" + estudiante.getApellido()
                + ";" + estudiante.getDireccion();
        escribirTexto(linea);
    }
}
