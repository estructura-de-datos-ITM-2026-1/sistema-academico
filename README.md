# Sistema academico
Un colegio requiere un sistema de información que permita administrar la información académica de los estudiantes, profesores y asignaturas ofertadas. El sistema debe permitir manejar la información personal y de contacto de profesores y estudiantes. La información personal incluye el nombre, número de identificación y fecha de nacimiento (mes, día y año); y la información de contacto incluye la dirección de residencia, teléfono y correo electrónico. Adicionalmente, para cada docente se registra la fecha de vinculación al colegio y el número de cursos dictados en el periodo actual; máximo un docente puede tener 5 cursos durante un periodo académico. Por su parte, de cada estudiante se registra la fecha de matrícula, el número de cursos actualmente matriculado, y el costo total de matrícula. Por su parte, a las asignaturas se les asigna un código de identificación, y se almacena el horario (día y hora), así como el cupo máximo de estudiantes.


```java
public class App {
    public static void main(String[] args) throws Exception {
        NodoArbol raiz = null;
        raiz = insertarRecursivo(raiz, 8);
        raiz = insertarRecursivo(raiz, 3);
        raiz = insertarRecursivo(raiz, 10);
        raiz = insertarRecursivo(raiz, 6);
        raiz = insertarRecursivo(raiz, 1);
        raiz = insertarRecursivo(raiz, 14);
        raiz = insertarRecursivo(raiz, 9);
    }

    public static NodoArbol insertarRecursivo(NodoArbol nodo, int dato) {
        if (nodo == null) { // Caso base: se llegó a una posición vacía
            return new Nodo(dato); // Se crea el nuevo nodo en esa posición
        }
        int actual = (int) nodo.getDato();
        if (dato < actual) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), dato));
        } else if (dato > actual) { // El nuevo dato es mayor: va al subárbol derecho
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), dato));
        }
        return nodo; // Retorna el nodo sin cambios si el dato ya existe
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
        System.out.println(nodo.getDato());
        recorridoInorden(nodo.getDerecho()); // Recorre después el subárbol derecho (identificaciones mayores)
    }

    private NodoArbol buscarRecursivo(NodoArbol nodo, int dato) {
        if (nodo == null) { // Caso base: se llegó a un nodo vacío, no se encontró
            return null;
        }
        Docente actual = (Docente) nodo.getDato(); // Cast del dato del nodo actual a Docente
        if (dato == actual.getDato()) { // Se encontró el nodo con la identificación buscada
            return nodo;
        } else if (dato < actual.getDato()) { // La clave buscada es menor: continúa en el subárbol
                                                                  // izquierdo
            return buscarRecursivo(nodo.getIzquierdo(), dato);
        } else { // La clave buscada es mayor: continúa en el subárbol derecho
            return buscarRecursivo(nodo.getDerecho(), dato);
        }
    }

    private NodoArbol eliminarRecursivo(NodoArbol nodo, int dato) {
        if (nodo == null) { // Caso base: se llegó a un nodo vacío, no hay nada que eliminar
            return null;
        }
        if (identificacion < nodo.getDato()) { // El nodo a eliminar está en el subárbol izquierdo
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), identificacion));
        } else if (identificacion > nodo.getDato()) { // El nodo a eliminar está en el subárbol derecho
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), identificacion));
        } else {
            // Se encontró el nodo a eliminar; se evalúa cuál de los tres casos aplica
            if (nodo.getIzquierdo() == null) { // Caso 1: sin hijos y 2a: sin hijo izquierdo, se reemplaza con el hijo derecho o null
                return nodo.getDerecho();
            } // Caso 2b: sin hijo derecho, se reemplaza con el hijo izquierdo

            // Caso 3: nodo con dos hijos
            
        }
        return nodo; 
    }

}



```