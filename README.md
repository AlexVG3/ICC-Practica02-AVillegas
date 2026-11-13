# Práctica 02 — Introducción a las Ciencias de la Computación

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/Status-Completado-success?style=for-the-badge)

Repositorio correspondiente a la Práctica 02 del curso Introducción a las Ciencias de la Computación (ICC). El objetivo de la práctica es ejercitar el uso de instrucciones condicionales, así como la creación y el uso de objetos, mediante una clase para crear y manejar fechas y un simulador de inversión bancaria que la utiliza.

---

## Estructura del Directorio

```text
AVillegas/
└── practica02/
    └── src/
        └── icc/
            ├── Fecha.java
            └── TiempoDeInversion.java
```

---

## Descripción de los Programas

### 1. Fecha.java
Clase que representa una fecha del calendario gregoriano mediante día, mes y año, validando que la fecha exista (considerando años bisiestos).
* Conceptos aplicados: Constructores sobrecargados y llamada entre constructores con `this(...)`, métodos modificadores y de acceso, métodos estáticos y privados auxiliares, instrucciones condicionales `if` / `if-else`, métodos `equals` y `toString`, y métodos que devuelven objetos.
* Comportamiento:
  * Crea fechas sin parámetros (1/1/1), con tres enteros (día, mes, año) o con día, año y nombre del mes.
  * Obtiene la diferencia en días entre dos fechas y suma o resta días a una fecha (por ejemplo, 3/9/2013 con 5 da 8/9/2013).
  * Determina si dos fechas son iguales y calcula los días transcurridos en el año (30/12/2013 da 364).
  * Convierte entre número y nombre de mes, y representa la fecha en el formato `dd/mes/aaaa` (por ejemplo, `03/Septiembre/2013`).

### 2. TiempoDeInversion.java
Programa interactivo por consola que simula una inversión bancaria usando la clase `Fecha`.
* Conceptos aplicados: Uso del paquete `java.util.Scanner`, captura de entradas (`nextLine()`, `nextInt()`, `nextDouble()`), creación y uso de objetos `Fecha`, constantes (`final`), conversión explícita de tipos e iteración con `do ... while`.
* Comportamiento: Da la bienvenida, solicita el nombre del inversionista, el monto a invertir y las fechas de inicio y fin de la inversión; muestra cuántos días dura la inversión y su rendimiento al 12% anual, y pregunta si se desea hacer otra simulación.

---

## Requisitos

* Java Development Kit (JDK): Versión 8 o superior.
* Consola o terminal de comandos.

---

## Compilación y Ejecución

Las clases no declaran la sentencia `package icc;`, por lo que se compilan y ejecutan desde la carpeta `icc/`:

### Compilación

```bash
cd AVillegas/practica02/src/icc
javac *.java
```

### Ejecución

Para ejecutar el simulador de inversión:
```bash
java TiempoDeInversion
```

> Nota: `Fecha.java` no tiene método `main`; es la clase que utiliza `TiempoDeInversion` para manejar las fechas.

---

## Autor

* AVillegas
