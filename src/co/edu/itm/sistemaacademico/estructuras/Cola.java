package co.edu.itm.sistemaacademico.estructuras;

public class Cola {
    private Nodo frente;
    private Nodo fin;
    private int tamaño;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    // Agrega un elemento al final de la cola
    public void enqueue(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (isEmpty()) {
            this.frente = nuevoNodo;
            this.fin = nuevoNodo;
        } else {
            this.fin.setSiguiente(nuevoNodo);
            this.fin = nuevoNodo;
        }
        this.tamaño++;
    }

    // Extrae y retorna el elemento del frente de la cola
    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }
        Object dato = this.frente.getDato();
        this.frente = this.frente.getSiguiente();
        if (this.frente == null) {
            this.fin = null;
        }
        this.tamaño--;
        return dato;
    }

    // Consulta el frente sin extraerlo
    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return this.frente.getDato();
    }

    public boolean isEmpty() {
        return this.frente == null;
    }

    public int getTamaño() {
        return tamaño;
    }
}
