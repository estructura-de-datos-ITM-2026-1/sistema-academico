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

}
```
