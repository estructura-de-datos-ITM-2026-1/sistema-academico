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

    public void agregarElementoAlFinal(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
            this.cola = nuevoNodo; // En caso de la primera inserción, la cola también apunta al nuevo nodo
            this.tamaño++;
            return;
        }
        // Se elimina el recorrido para encontrar el último nodo, ya que ahora tenemos
        // una referencia directa a la cola
        this.cola.setSiguiente(nuevoNodo); // Se conecta el nuevo nodo al final de la lista
        this.cola = nuevoNodo; // Actualizar la referencia de la cola
        this.tamaño++; // Incrementar el tamaño de la lista
    }

    public boolean agregarElementoEnListaVacia(Nodo nuevoNodo) {
        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
            this.cola = nuevoNodo; // En caso de la primera inserción, la cola también apunta al nuevo nodo
            this.tamaño++;
            return true;
        }
        return false;
    }

    // Una bonificacion +0.5
    public void agregarElementoAlInicio(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (agregarElementoEnListaVacia(nuevoNodo)) {
            return;
        }
        nuevoNodo.setSiguiente(this.cabeza); // El nuevo nodo apunta al nodo que actualmente es la cabeza
        this.cabeza = nuevoNodo; // La cabeza ahora apunta al nuevo nodo
        this.tamaño++; // Incrementar el tamaño de la lista
    }

    // Una bonificacion +0.5
    public void agregarElementoEnPosicion(Object dato, int posicion) {
        // Si la posicion es igual a cero, se agrega al inicio
        if (posicion == 0) {
            agregarElementoAlInicio(dato);
            return;
        }
        // Si la posicion es igual al tamaño de la lista, se agrega al final
        if (posicion == this.tamaño) {
            agregarElementoAlFinal(dato);
            return;
        }
        Nodo nuevoNodo = new Nodo(dato);
        // Se crea una variable nodoActual que se inicializa con la cabeza de la lista,
        // y se recorre la lista hasta llegar a la posición anterior a la posición donde
        // se desea insertar el nuevo nodo
        Nodo nodoActual = this.cabeza;
        for (int i = 0; i < posicion - 1; i++) {
            nodoActual = nodoActual.getSiguiente(); // Se actualiza nodoActual para que apunte al siguiente nodo en cada
                                                    // iteración
        }
        nuevoNodo.setSiguiente(nodoActual.getSiguiente()); // El nuevo nodo apunta al nodo que actualmente está en la
                                                           // posición donde se desea insertar
        nodoActual.setSiguiente(nuevoNodo); // El nodo anterior a la posición donde se desea insertar ahora apunta al
                                            // nuevo nodo
        this.tamaño++; // Incrementar el tamaño de la lista
    }

    public Object obtenerElementoEnPosicion(int posicion) {
        Nodo nodoActual = this.cabeza; // Se crea una variable nodoActual que se inicializa con la cabeza de la lista
        for (int i = 0; i < posicion; i++) { // Se recorre la lista hasta llegar a la posición deseada
            nodoActual = nodoActual.getSiguiente(); // Se actualiza nodoActual para que apunte al siguiente nodo en cada
                                                    // iteración
        }
        return nodoActual.getDato(); // Se retorna el dato del nodo que se encuentra en la posición deseada
    }

    public int getTamaño() {
        return tamaño;
    }

    public void EliminarElementoAlFinal() {
        if (this.cabeza == null) {
            return; // La lista está vacía, no hay nada que eliminar
        }
        if (this.cabeza.getSiguiente() == null) {
            this.cabeza = null; // Solo hay un nodo en la lista, se elimina la cabeza
            this.cola = null; // La cola también debe ser nula
            this.tamaño--;
            return;
        }
        Nodo nodoActual = this.cabeza;
        while (nodoActual.getSiguiente() != this.cola) { // Se recorre la lista hasta llegar al nodo anterior a la cola
            nodoActual = nodoActual.getSiguiente();
        }
        nodoActual.setSiguiente(null); // El nodo anterior a la cola ahora apunta a null, eliminando la referencia a la
                                       // cola
        this.cola = nodoActual; // Actualizar la referencia de la cola al nuevo último nodo
        this.tamaño--; // Decrementar el tamaño de la lista
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

    public Nodo getCabeza() {
        return cabeza;
    }

    public Nodo getCola() {
        return cola;
    }
}