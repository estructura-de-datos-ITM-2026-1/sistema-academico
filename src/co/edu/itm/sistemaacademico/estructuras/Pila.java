package co.edu.itm.sistemaacademico.estructuras;

public class Pila {
    private Nodo tope;
    private int tamaño;

    public Pila() {
        this.tope = null;
        this.tamaño = 0;
    }

    // Empuja un dato al tope de la pila
    public void push(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.setSiguiente(this.tope);
        this.tope = nuevoNodo;
        this.tamaño++;
    }

    // Extrae y retorna el dato del tope de la pila
    public Object pop() {
        if (isEmpty()) {
            return null;
        }
        Object dato = this.tope.getDato();
        this.tope = this.tope.getSiguiente();
        this.tamaño--;
        return dato;
    }

    // Consulta el dato del tope sin extraerlo
    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return this.tope.getDato();
    }

    public boolean isEmpty() {
        return this.tope == null;
    }

    public int getTamaño() {
        return tamaño;
    }
}
