# Ámbito de variables

El ámbito de una variable representa su tiempo de vida y visibilidad. Existen varios tipos de ámbitos de variables.

## Variables locales

Las variables locales se definen dentro de un método, donde pueden o no ser definidas dentro de bloques if-else, iteradores o try-catch. 
El ámbito de una variable local depende de la ubicación de su declaración dentro del método. Generalmente se puede definir que su ámbito termina al final del bloque donde fue declarada.

## Parámetros de métodos

El ámbito de los parámetros de un método siempre será mayor o igual que el ámbito de una variable local. Los parámetros son accesibles en todo el método.

## Variables de instancias u objetos

Una variable de instancia se declara dentro de una clase, fuera de todos los métodos. De esta manera se vuelve accesible en todos los métodos de la instancia que hayan sido definidos en la clase.
El ámbito de una variable de instancia es mayor que el de una variable local o un parámetro de método.

## Variable de clase

La variable de clase tiene el mayor ámbito de todos. La variable de clase pertenece a la clase y se comparte con todas las instancias de la clase. Para declarar una variable de clase se utiliza el keyword *static*.

## Solapamiento de ámbito de variables

Es posible declarar una nueva variable utilizando el mismo nombre de otra siempre y cuando se lo realice en un ámbito diferente o menor en el caso de una variable local. 

# Ciclo de vida de un objeto

Java no permite realizar el manejo de memoria al programador como otros lenguajes (C, C++), por lo que posee una herramienta llamada *Recolector de basura* (Garbage collector) que se encarga de buscar objetos sin referencias y eliminarlos de la memoria.
El ciclo de vida de un objeto empieza cuando es creado y dura hasta que termine su ámbito o ya no sea referenciado por alguna variable.

## Creación de un objeto

Un objeto se crea utilizando el keyword *new*. Es posible inicializar un objeto sin asignarlo a una variable, pero eso lo haría un candidato a ser marcado para recolección por el recolector de basura.

```java
class Person {}

// Creación de un nuevo objeto sin asignarlo a una variable.
public static void main() {
  new Person();
}

// Creación de un nuevo objeto asignándolo a una variable.
public static void main() {
  Person person = new Person();
}
``` 

## Accesibilidad de un objeto

Una vez que un objeto es creado, puede ser accedido utilizando la variable que lo referencia. Seguirá siendo accesible hasta que su ámbito termine o su variable de referencia sea reasignada, sea a **null** u otra instancia.

### Inaccesibilidad de un objeto

Un objeto se vuelve inaccesible cuando su ámbito termina o sea dereferenciado mediante una reasignación.

## Recolección de basura

El recolector de basura es una herramienta de la *JVM* que se ejecuta en un hilo de baja prioridad, la cual se encarga de marcar objetos dereferenciados para ser liberados de la memoria.
De esta manera el manejo de memoria se realiza de forma automática y transparente para el programador.

