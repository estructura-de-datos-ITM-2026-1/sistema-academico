package co.edu.itm.sistemaacademico.estructuras;

public class ListaEnlazada {
    private Nodo cabeza;
    private Nodo cola;
    private int tamaño;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cola = null; // Inicializar la cola
        this.tamaño = 0;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public Nodo getCola() {
        return cola;
    }

    public void setCola(Nodo cola) {
        this.cola = cola;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
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

    public void eliminarElementoAlInicio() {
        if (this.cabeza == null) {
            return; // La lista está vacía, no hay nada que eliminar
        }
        this.cabeza = this.cabeza.getSiguiente(); // La cabeza ahora apunta al siguiente nodo, eliminando la referencia
                                                  // al nodo anterior
                                                  // nodo anterior
        this.tamaño--; // Decrementar el tamaño de la lista
    }

}