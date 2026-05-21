package co.edu.itm.sistemaacademico.estructuras;

import co.edu.itm.sistemaacademico.models.Docente;

public class ArbolDocentes {
    private NodoArbol raiz; // Nodo raíz del árbol; null cuando el árbol está vacío
    private int tamaño; // Cantidad de docentes almacenados en el árbol

    public ArbolDocentes() {
        this.raiz = null; // El árbol inicia sin ningún nodo
        this.tamaño = 0; // Contador de nodos en cero
    }

    public NodoArbol getRaiz() {
        return raiz; // Expone la raíz para permitir recorridos externos (ej. recorridoInorden)
    }

    public int getTamaño() {
        return tamaño; // Retorna el contador interno de nodos
    }

    public void insertar(Object dato) {
        Docente docente = (Docente) dato; // Cast explícito de Object a Docente
        raiz = insertarRecursivo(raiz, docente); // Delega la inserción al método recursivo y actualiza la raíz
        tamaño++; // Incrementa el contador de nodos
    }

    private NodoArbol insertarRecursivo(NodoArbol nodo, Docente docente) {
        if (nodo == null) { // Caso base: se llegó a una posición vacía
            return new NodoArbol(docente); // Se crea el nuevo nodo en esa posición
        }
        Docente actual = (Docente) nodo.getDato(); // Cast del dato del nodo actual a Docente
        if (docente.getIdentificacion() < actual.getIdentificacion()) { // La nueva identificación es menor: va al
                                                                        // subárbol izquierdo
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), docente));
        } else if (docente.getIdentificacion() > actual.getIdentificacion()) { // La nueva identificación es mayor: va
                                                                               // al subárbol derecho
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), docente));
        }
        return nodo; // Retorna el nodo sin cambios si la identificación ya existe
    }

    public Object buscar(int identificacion) {
        NodoArbol resultado = buscarRecursivo(raiz, identificacion); // Inicia la búsqueda desde la raíz
        return resultado == null ? null : resultado.getDato(); // Retorna el dato si se encontró, null si no
    }

    private NodoArbol buscarRecursivo(NodoArbol nodo, int identificacion) {
        if (nodo == null) { // Caso base: se llegó a un nodo vacío, no se encontró
            return null;
        }
        Docente actual = (Docente) nodo.getDato(); // Cast del dato del nodo actual a Docente
        if (identificacion == actual.getIdentificacion()) { // Se encontró el nodo con la identificación buscada
            return nodo;
        } else if (identificacion < actual.getIdentificacion()) { // La clave buscada es menor: continúa en el subárbol
                                                                  // izquierdo
            return buscarRecursivo(nodo.getIzquierdo(), identificacion);
        } else { // La clave buscada es mayor: continúa en el subárbol derecho
            return buscarRecursivo(nodo.getDerecho(), identificacion);
        }
    }

    public void eliminar(int identificacion) {
        if (buscar(identificacion) != null) { // Solo elimina si el docente existe en el árbol
            raiz = eliminarRecursivo(raiz, identificacion); // Delega la eliminación al método recursivo y actualiza la
                                                            // raíz
            tamaño--; // Decrementa el contador de nodos
        }
    }

    private NodoArbol eliminarRecursivo(NodoArbol nodo, int identificacion) {
        if (nodo == null) { // Caso base: se llegó a un nodo vacío, no hay nada que eliminar
            return null;
        }
        Docente actual = (Docente) nodo.getDato(); // Cast del dato del nodo actual a Docente
        if (identificacion < actual.getIdentificacion()) { // El nodo a eliminar está en el subárbol izquierdo
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), identificacion));
        } else if (identificacion > actual.getIdentificacion()) { // El nodo a eliminar está en el subárbol derecho
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), identificacion));
        } else {
            // Se encontró el nodo a eliminar; se evalúa cuál de los tres casos aplica
            if (nodo.getIzquierdo() == null) { // Caso 1 y 2a: sin hijo izquierdo, se reemplaza con el hijo derecho
                return nodo.getDerecho();
            } else if (nodo.getDerecho() == null) { // Caso 2b: sin hijo derecho, se reemplaza con el hijo izquierdo
                return nodo.getIzquierdo();
            }
            // Caso 3: nodo con dos hijos
            NodoArbol sucesor = minimoNodo(nodo.getDerecho()); // Obtiene el sucesor inorden (mínimo del subárbol
                                                               // derecho)
            nodo.setDato(sucesor.getDato()); // Reemplaza el dato del nodo actual con el del sucesor
            Docente sucesorDocente = (Docente) sucesor.getDato(); // Cast del sucesor para extraer su identificación
            nodo.setDerecho(eliminarRecursivo( // Elimina el sucesor del subárbol derecho para evitar duplicado
                    nodo.getDerecho(), sucesorDocente.getIdentificacion()));
        }
        return nodo; // Retorna el nodo (posiblemente modificado) al nivel superior
    }

    private NodoArbol minimoNodo(NodoArbol nodo) {
        while (nodo.getIzquierdo() != null) { // Desciende hacia la izquierda hasta llegar al nodo sin hijo izquierdo
            nodo = nodo.getIzquierdo(); // Avanza al hijo izquierdo en cada iteración
        }
        return nodo; // El nodo más a la izquierda es el de menor identificación
    }

    public void recorridoInorden(NodoArbol nodo) {
        if (nodo == null) { // Caso base: nodo vacío, no hay nada que imprimir
            return;
        }
        recorridoInorden(nodo.getIzquierdo()); // Recorre primero el subárbol izquierdo (identificaciones menores)
        Docente docente = (Docente) nodo.getDato(); // Cast del dato del nodo actual a Docente
        System.out.println("DATOS DEL DOCENTE:"); // Encabezado de separación entre registros
        docente.mostrarInformacion(); // Imprime los datos del docente usando su propio método
        recorridoInorden(nodo.getDerecho()); // Recorre después el subárbol derecho (identificaciones mayores)
    }
}
