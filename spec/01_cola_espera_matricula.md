# Spec 01 — Cola de Espera para Matrícula en Cursos

## Descripción

Implementar una cola de espera en los cursos usando una **cola (queue)** como estructura de datos. Cuando un curso alcanza su cupo máximo, los estudiantes que intenten matricularse ingresan a una cola FIFO. Al cancelar una matrícula, el primer estudiante de la cola es matriculado automáticamente.

---

## Componentes nuevos

### `estructuras/Cola.java`

Estructura de datos cola construida sobre el `Nodo.java` existente. Modela el comportamiento FIFO (First In, First Out).

**Operaciones:**
- `enqueue(Object dato)` — agrega un elemento al final de la cola
- `dequeue()` — extrae y retorna el elemento del frente de la cola
- `peek()` — consulta el frente sin extraerlo
- `isEmpty()` — retorna `true` si la cola está vacía
- `getTamaño()` — retorna la cantidad de elementos en espera

---

## Componentes modificados

### `models/Curso.java` — Impacto alto

Es el componente más afectado. Necesita cupo máximo y la cola de espera como nuevos conceptos.

**Atributos nuevos:**

| Atributo | Tipo | Descripción |
|---|---|---|
| `cupoMaximo` | `int` | Número máximo de estudiantes que pueden matricularse |
| `colaEspera` | `Cola` | Estudiantes en espera de un cupo disponible |

**Cambios en constructores:**
- `Curso()` — inicializar `colaEspera = new Cola()` y `cupoMaximo = 0`
- `Curso(int, String, int)` — agregar parámetro `int cupoMaximo`, inicializar `colaEspera`

**Métodos nuevos:**

| Método | Descripción |
|---|---|
| `estaLleno()` | Retorna `true` si `estudiantesMatriculados.getTamaño() >= cupoMaximo` |
| `getCuposDisponibles()` | Retorna `cupoMaximo - estudiantesMatriculados.getTamaño()` |
| `agregarAColaEspera(Estudiante)` | Llama `colaEspera.enqueue(estudiante)` |
| `siguienteEnCola()` | Llama `colaEspera.dequeue()` y retorna el `Estudiante` o `null` |
| `listarColaEspera()` | Recorre e imprime los estudiantes en espera sin modificar la cola |

**Cambio en `mostrarInformacion()`:**
- Agregar líneas que muestren `cupoMaximo`, cupos disponibles y tamaño de la cola de espera.

---

### `models/Estudiante.java` — Impacto medio

**`matricularCurso(Curso curso)` (línea 35) — cambio de lógica:**

Actualmente agrega directamente sin verificar cupo. La nueva lógica es:
```
si curso.estaLleno()
    curso.agregarAColaEspera(this)
    informar que fue puesto en cola de espera
si no
    cursosMatriculados.agregarElementoAlFinal(curso)
    curso.agregarEstudiante(this)
```

**`cancelarCurso(Curso curso)` (línea 42) — implementar desde cero:**

Actualmente está vacío. La nueva lógica es:
```
eliminar curso de cursosMatriculados
curso.eliminarEstudiante(this)
si cola de espera no está vacía
    siguiente = curso.siguienteEnCola()
    siguiente.matricularCurso(curso)   // ocupa el cupo liberado
    informar que el siguiente fue matriculado automáticamente
```

---

### `models/SistemaAcademico.java` — Impacto medio

**Métodos nuevos:**

| Método | Descripción |
|---|---|
| `listarColaEsperaCurso(int codigoCurso)` | Busca el curso y llama `curso.listarColaEspera()` |
| `cancelarCursoEstudiante(String idEstudiante, int codigoCurso)` | Busca ambos, llama `estudiante.cancelarCurso(curso)` y registra en historial |

**Tipos de operación nuevos para el historial (`Operacion`):**

| Tipo | Cuándo se registra |
|---|---|
| `MATRICULAR_CURSO` | Estudiante matriculado exitosamente en un curso |
| `ENCOLAR_ESTUDIANTE` | Estudiante puesto en cola de espera por cupo lleno |
| `CANCELAR_CURSO` | Estudiante cancela su matrícula en un curso |

Los `push` de `MATRICULAR_CURSO` y `ENCOLAR_ESTUDIANTE` se agregan dentro de `matricularCurso()` en `Estudiante`, pero dado que la pila vive en `SistemaAcademico`, la alternativa más limpia es mover la lógica de matrícula a `SistemaAcademico` como `matricularEstudianteEnCurso(String idEstudiante, int codigoCurso)` — acción que ya estaba propuesta como paso 5 opcional del spec 00.

---

### `utils/CursoUtil.java` — Impacto bajo

Agregar la lectura del cupo máximo al crear un curso:
```java
int cupoMaximo = ConsoleUtil.leerEntero("Ingrese el cupo máximo del curso:");
return new Curso(codigoCurso, nombreCurso, creditos, cupoMaximo);
```

---

### `App.java` — Impacto bajo

**Opciones nuevas en el menú:**

```
13. Ver cola de espera de un curso
14. Cancelar matrícula de estudiante en curso
```

**Cambio en opción 6 (Matricular estudiante en curso):**
- El mensaje de confirmación debe diferenciar si el estudiante fue matriculado directamente o puesto en cola de espera, según el resultado de `matricularCurso()`.

---

## Componentes sin cambios

| Componente | Razón |
|---|---|
| `estructuras/Nodo.java` | La `Cola` lo reutiliza sin modificaciones |
| `estructuras/ListaEnlazada.java` | No tiene relación con la cola de espera |
| `estructuras/Pila.java` | Ya implementada, no se toca |
| `models/Operacion.java` | Solo se usan tipos nuevos de string, no cambia la clase |
| `models/Persona.java`, `Docente.java` | Modelos de datos sin relación |
| `archivos/ArchivoEstudianteTexto.java` | La cola es en memoria |
| `utils/ConsoleUtil.java`, `EstudianteUtil.java` | Sin relación con la cola |

---

## Orden de implementación

```
1. Crear   estructuras/Cola.java
2. Modificar  models/Curso.java
           2a. Agregar atributos cupoMaximo y colaEspera
           2b. Actualizar constructores
           2c. Agregar estaLleno(), getCuposDisponibles()
           2d. Agregar agregarAColaEspera(), siguienteEnCola(), listarColaEspera()
           2e. Agregar eliminarEstudiante() (necesario para cancelarCurso)
           2f. Actualizar mostrarInformacion()
3. Modificar  models/Estudiante.java
           3a. Actualizar matricularCurso() con verificación de cupo
           3b. Implementar cancelarCurso()
4. Modificar  models/SistemaAcademico.java
           4a. Agregar listarColaEsperaCurso()
           4b. Agregar cancelarCursoEstudiante() con registro en historial
           4c. Registrar MATRICULAR_CURSO y ENCOLAR_ESTUDIANTE en historial
5. Modificar  utils/CursoUtil.java  (leer cupoMaximo)
6. Modificar  App.java  (opciones 13 y 14, actualizar mensaje opción 6)
```
