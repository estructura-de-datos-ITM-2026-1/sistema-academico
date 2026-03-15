package co.edu.itm.sistemaacademico.models;

public class Curso {
    private int codigoCurso;
    private String nombreCurso;
    private int creditos;
    private String horario;
    private String periodo;
    private String aula;
    private Docente docente;
    // 1. Crear lista enlazada para estudiantes

    public Curso() {
    }

    public Curso(int codigoCurso, String nombreCurso, int creditos) {
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.creditos = creditos;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

}