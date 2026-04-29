package co.edu.itm.sistemaacademico.estructuras;

public class ListaDoblementeEnlazada {
    private NodoDoble cabeza;
    private NodoDoble cola;
    private int tamaño;

    public ListaDoblementeEnlazada() {
        this.cabeza = null;
        this.cola = null;
        this.tamaño = 0;
    }

    public NodoDoble getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoDoble cabeza) {
        this.cabeza = cabeza;
    }

    public NodoDoble getCola() {
        return cola;
    }

    public void setCola(NodoDoble cola) {
        this.cola = cola;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public boolean estaVacia() {
        return this.cabeza == null;
    }

    public void agregarAlFinal(Object dato) {
        NodoDoble nuevoNodo = new NodoDoble(dato);
        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
            this.cola = nuevoNodo;
            this.tamaño++;
            return;
        }
        nuevoNodo.setAnterior(this.cola);
        this.cola.setSiguiente(nuevoNodo);
        this.cola = nuevoNodo;
        this.tamaño++;
    }

    public void agregarAlInicio(Object dato) {
        NodoDoble nuevoNodo = new NodoDoble(dato);
        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
            this.cola = nuevoNodo;
            this.tamaño++;
            return;
        }
        nuevoNodo.setSiguiente(this.cabeza);
        this.cabeza.setAnterior(nuevoNodo);
        this.cabeza = nuevoNodo;
        this.tamaño++;
    }

    public void eliminarAlInicio() {
        if (this.cabeza == null) {
            return;
        }
        if (this.cabeza == this.cola) {
            this.cabeza = null;
            this.cola = null;
            this.tamaño--;
            return;
        }
        this.cabeza = this.cabeza.getSiguiente();
        this.cabeza.setAnterior(null);
        this.tamaño--;
    }

    public void eliminarAlFinal() {
        if (this.cola == null) {
            return;
        }
        if (this.cabeza == this.cola) {
            this.cabeza = null;
            this.cola = null;
            this.tamaño--;
            return;
        }
        this.cola = this.cola.getAnterior();
        this.cola.setSiguiente(null);
        this.tamaño--;
    }

    public NodoDoble obtenerPrimero() {
        return this.cabeza;
    }

    public NodoDoble obtenerUltimo() {
        return this.cola;
    }

    public void recorrerAdelante() {
        if (this.cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        NodoDoble actual = this.cabeza;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public void recorrerAtras() {
        if (this.cola == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        NodoDoble actual = this.cola;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getAnterior();
        }
    }
}
