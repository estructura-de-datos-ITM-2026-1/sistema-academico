package co.edu.itm.sistemaacademico.archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import co.edu.itm.sistemaacademico.models.Docente;

public class ArchivoDocenteTexto {
    private String nombreArchivo;

    public ArchivoDocenteTexto(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void crearArchivoDocente() {
        File archivo = new File(this.nombreArchivo);
        try {
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al crear el archivo.");
            e.printStackTrace();
        }
    }

    public void guardarDocente(Docente docente) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(this.nombreArchivo, true))) {
            escritor.write(construirLinea(docente));
            escritor.newLine();
        } catch (Exception e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace();
        }
    }

    public boolean eliminarDocente(int identificacion) {
        File archivoOriginal = new File(this.nombreArchivo);
        File archivoTemporal = new File(this.nombreArchivo + ".tmp");
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoOriginal));
                BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemporal))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String idLinea = linea.split(";")[0];
                if (idLinea != null && Integer.parseInt(idLinea) == identificacion) {
                    continue;
                }
                escritor.write(linea);
                escritor.newLine();
            }
            if (!archivoOriginal.delete()) {
                System.out.println("No se pudo eliminar el archivo original.");
                return false;
            }
            if (!archivoTemporal.renameTo(archivoOriginal)) {
                System.out.println("No se pudo renombrar el archivo temporal.");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer el archivo.");
            e.printStackTrace();
            return false;
        }
    }

    private String construirLinea(Docente docente) {
        return docente.getIdentificacion() + ";" + docente.getNombre() + ";" + docente.getApellido()
                + ";" + docente.getNumeroCursos() + ";" + docente.getDireccion();
    }
}
