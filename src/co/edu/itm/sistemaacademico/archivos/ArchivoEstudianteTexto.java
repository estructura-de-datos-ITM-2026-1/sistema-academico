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
        }
    }

    public void guardarEstudiante(Estudiante estudiante) {
        escribirTexto(construirLinea(estudiante));
    }

    public boolean actualizarEstudiante(Estudiante estudianteActualizado) {
        String nuevaLinea = construirLinea(estudianteActualizado);

        File archivoOriginal = new File(this.nombreArchivo);
        File archivoTemporal = new File(this.nombreArchivo + ".tmp");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoOriginal));
                BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemporal))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String identificacion = linea.split(";")[0]; // Extrae la identificación de la línea
                if (identificacion != null && identificacion.equals(estudianteActualizado.getIdentificacion())) {

                    escritor.write(nuevaLinea);
                    escritor.newLine();

                    continue;
                }
                escritor.write(linea);
                escritor.newLine();
            }
            archivoOriginal.delete();
            archivoTemporal.renameTo(archivoOriginal);
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer el archivo");
            e.printStackTrace(); // Imprime la traza del error para diagnóstico
            return false;
        }
    }

    public boolean eliminarEstudiante(String identificacionAEliminar) {
        File archivoOriginal = new File(this.nombreArchivo);
        File archivoTemporal = new File(this.nombreArchivo + ".tmp");
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoOriginal));
                BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemporal))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String identificacion = linea.split(";")[0]; // Extrae la identificación de la línea
                if (identificacion != null && identificacion.equals(identificacionAEliminar)) {
                    continue;
                }
                escritor.write(linea);
                escritor.newLine();
            }
            archivoOriginal.delete();
            archivoTemporal.renameTo(archivoOriginal);
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer el archivo");
            e.printStackTrace(); // Imprime la traza del error para diagnóstico
            return false;
        }
    }

    private String construirLinea(Estudiante estudiante) {
        return estudiante.getIdentificacion() + ";" + estudiante.getNombre() + ";" + estudiante.getApellido()
                + ";" + estudiante.getDireccion();
    }

    public void leerTexto() {
        try (BufferedReader lector = new BufferedReader(new FileReader(this.nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer el archivo.");
        }
    }
}
