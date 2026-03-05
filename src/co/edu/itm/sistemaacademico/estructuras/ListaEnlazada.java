package co.edu.itm.sistemaacademico.estructuras;

public class ListaEnlazada {
    private Nodo cabeza;
    private Nodo cola;
    private int tamaño;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cola = null;
        this.tamaño = 0;
    }

    public void agregarElementoAlFinal(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
            this.cola = nuevoNodo;
            this.tamaño++;
            return;
        }
        this.cola.setSiguiente(nuevoNodo);
        this.cola = nuevoNodo;
        this.tamaño++;
    }

    public void agregarElementoAlInicio(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
            this.cola = nuevoNodo;
            this.tamaño++;
            return;
        }
        nuevoNodo.setSiguiente(this.cabeza);
        this.cabeza = nuevoNodo;
        this.tamaño++;
    }

    public void agregarElementoEnPosicion(Object dato, int posicion) {
        if (posicion == 0) {
            agregarElementoAlInicio(dato);
            return;
        }
        if (posicion == this.tamaño) {
            agregarElementoAlFinal(dato);
            return;
        }
        Nodo nuevoNodo = new Nodo(dato);
        Nodo nodoActual = this.cabeza;
        for (int i = 0; i < posicion - 1; i++) {
            nodoActual = nodoActual.getSiguiente();
        }
        nuevoNodo.setSiguiente(nodoActual.getSiguiente());
        nodoActual.setSiguiente(nuevoNodo);
        this.tamaño++;
    }

    public int getTamaño() {
        return tamaño;
    }
}
