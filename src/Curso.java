public class Curso {
    private int codigoCurso;
    private String periodo;
    private String aula;
    private Docente docente;
    private Asignatura asignatura;

    public Curso() {
    }

    public Curso(int codigoCurso, String periodo, String aula, Asignatura asignatura) {
        this.codigoCurso = codigoCurso;
        this.periodo = periodo;
        this.aula = aula;
        this.asignatura = asignatura;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
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

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

}
