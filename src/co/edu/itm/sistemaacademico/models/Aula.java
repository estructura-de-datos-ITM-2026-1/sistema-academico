import co.edu.itm.sistemaacademico.estructuras.ListaEnlazada;

public class Aula {
    private String codigo;
    private String ubicacion;
    private ListaEnlazada grupos;
    private  boolean tieneAireAcondicionado;
    private boolean esBLearning;


    public Aula() {
        this.grupos = new ListaEnlazada();
    }

    public Aula(String codigo, String ubicacion, String grupos, boolean tieneAireAcondicionado, boolean esBLearning) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.esBLearning = esBLearning;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String grupos) {
        this.grupos = grupos;
    }

    public boolean isTieneAireAcondicionado() {
        return tieneAireAcondicionado;
    }

    public void setTieneAireAcondicionado(boolean tieneAireAcondicionado) {
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    public boolean isEsBLearning() {
        return esBLearning;
    }

    public void setEsBLearning(boolean esBLearning) {
        this.esBLearning = esBLearning;
    }

    public String reservarAula() {
        return "Aula reservada";
    }

    
    
}
