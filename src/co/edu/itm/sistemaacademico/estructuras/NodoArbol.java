package co.edu.itm.sistemaacademico.estructuras;

public class NodoArbol {
    private Object dato;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol() {
        this.dato = null;
        this.izquierdo = null;
        this.derecho = null;
    }

    public NodoArbol(Object dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }
}
