
import java.util.Scanner;

/**
 * Simulador de inversion: solicita el nombre del inversionista, el monto a
 * invertir y las fechas de inicio y fin de la inversion. Muestra cuantos dias
 * dura la inversion y el rendimiento a una tasa anual del 12% (interes simple,
 * proporcional a los dias invertidos sobre un anio de 365 dias). Al terminar
 * pregunta si se desea hacer otra simulacion.
 *
 * @author Alex Villegas
 */
public class TiempoDeInversion {

    /** Tasa de rendimiento anual (12%) */
    private static final double TASA_ANUAL = 0.12;

    /** Dias del anio usados para prorratear la tasa anual */
    private static final int DIAS_ANIO = 365;

    /**
     * Lee un numero entero del teclado.
     * @param sc el Scanner con el que se lee
     * @param mensaje el texto que se muestra al usuario
     * @return el entero capturado
     */
    private static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        int valor = sc.nextInt();
        sc.nextLine(); // descarta lo que reste de la linea
        return valor;
    }

    /**
     * Lee un monto positivo del teclado; repite la pregunta mientras el
     * monto no sea mayor que cero.
     * @param sc el Scanner con el que se lee
     * @return el monto capturado
     */
    private static double leerMonto(Scanner sc) {
        double monto = sc.nextDouble();
        sc.nextLine();
        while (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero.");
            System.out.print("Monto a invertir: ");
            monto = sc.nextDouble();
            sc.nextLine();
        }
        return monto;
    }

    /**
     * Lee un mes del teclado, ya sea como numero ("3") o como nombre
     * ("marzo", "Marzo", "MARZO"). Los nombres se convierten a numero con
     * Fecha.getMonthNumber.
     * @param sc el Scanner con el que se lee
     * @return el numero de mes (0 si el nombre escrito no es un mes)
     */
    private static int leerMes(Scanner sc) {
        System.out.print("  Mes (numero 1-12 o nombre): ");
        String texto = sc.nextLine().trim();
        int mes = 0;
        if (texto.length() > 0) {
            char primero = texto.charAt(0);
            if (primero >= '0' && primero <= '9') {
                // numero de mes: maximo 2 digitos y solo digitos
                boolean soloDigitos = texto.length() <= 2;
                int i = 0;
                while (soloDigitos && i < texto.length()) {
                    char c = texto.charAt(i);
                    if (c >= '0' && c <= '9') {
                        mes = mes * 10 + (c - '0');
                    } else {
                        soloDigitos = false;
                        mes = 0;
                    }
                    i++;
                }
            } else {
                // nombre de mes; getMonthNumber acepta cualquier combinacion de mayusculas
                mes = Fecha.getMonthNumber(texto);
            }
        }
        return mes;
    }

    /**
     * Lee una fecha (dia, mes y anio) del teclado; repite hasta que la fecha
     * capturada sea valida en el calendario.
     * @param sc el Scanner con el que se lee
     * @return la Fecha capturada
     */
    private static Fecha leerFecha(Scanner sc) {
        int dia = 0;
        int mes = 0;
        int anio = 0;
        boolean valida = false;
        do {
            dia = leerEntero(sc, "  Dia (numero): ");
            mes = leerMes(sc);
            anio = leerEntero(sc, "  Anio: ");
            valida = Fecha.isValidDate(dia, mes, anio);
            if (!valida) {
                System.out.println("Esa fecha no existe, intenta de nuevo.");
            }
        } while (!valida);
        return new Fecha(dia, mes, anio);
    }

    /**
     * Trunca un valor a dos decimales (usando conversion explicita a long).
     * @param valor el valor a truncar
     * @return el valor con dos decimales
     */
    private static double truncar(double valor) {
        return (long) (valor * 100) / 100.0;
    }

    /**
     * Ejecuta el simulador de inversion: da la bienvenida, pide el nombre y,
     * por cada simulacion, el monto y las fechas de inicio y fin; muestra los
     * dias de inversion y el rendimiento al 12% anual, y repite mientras el
     * usuario responda "si".
     * @param args argumentos de linea de comandos (no se usan)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // (a) bienvenida y solicitud del nombre
        System.out.println("Bienvenido al simulador de inversiones.");
        System.out.print("Por favor, escribe tu nombre: ");

        // (b) recabar nombre
        String nombre = sc.nextLine().trim();

        boolean otra = false;
        do {
            // (c) saludo y pregunta del monto
            System.out.println("\nHola, " + nombre + ".");
            System.out.print("Monto a invertir: ");

            // (d) recabar monto
            double monto = leerMonto(sc);

            // (e) y (f) fecha de inicio
            System.out.println("Fecha de inicio de la inversion:");
            Fecha inicio = leerFecha(sc);

            // (g) y (h) fecha de fin (no puede ser anterior al inicio)
            int dias = 0;
            Fecha fin = null;
            do {
                System.out.println("Fecha de fin de la inversion:");
                fin = leerFecha(sc);
                dias = inicio.differenceDays(fin);
                if (dias < 0) {
                    System.out.println("La fecha de fin no puede ser anterior a la de inicio.");
                }
            } while (dias < 0);

            // (i) dias de inversion y rendimiento al 12%
            double rendimiento = monto * TASA_ANUAL * dias / DIAS_ANIO;
            System.out.println("\nInversion del " + inicio + " al " + fin);
            System.out.println("Dias de inversion: " + dias);
            System.out.println("Rendimiento al 12% anual: $" + truncar(rendimiento));
            System.out.println("Monto final: $" + truncar(monto + rendimiento));

            // (j) otra simulacion?
            System.out.print("\nDeseas hacer otra simulacion? (si/no): ");
            String respuesta = sc.nextLine().trim().toLowerCase();
            otra = respuesta.equals("si");
        } while (otra);

        System.out.println("Gracias por usar el simulador, " + nombre + ". Hasta luego.");
    }
}
