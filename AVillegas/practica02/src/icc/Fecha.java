
/**
 * Representa una fecha del calendario gregoriano mediante dia, mes y anio.
 * Solo acepta fechas validas (anio mayor o igual a 1, mes entre 1 y 12 y dia
 * acorde al mes, considerando anios bisiestos); si se intenta crear una fecha
 * invalida, se asigna el valor de fabrica 1/1/1.
 *
 * Permite crear fechas a partir de numeros o del nombre del mes, sumar o
 * restar dias, calcular los dias transcurridos en el anio, obtener la
 * diferencia en dias entre dos fechas y compararlas. Internamente usa 1/1/1
 * como fecha de referencia (valor absoluto 1) para los calculos.
 *
 * @author Alex Villegas
 */
public class Fecha {

    /** Dia del mes */
    private int dd;
    /** Numero de mes (1 a 12) */
    private int mm;
    /** Anio (mayor o igual a 1) */
    private int yy;


    /**
     * Crea una fecha con los valores de fabrica (1/1/1).
     */
    public Fecha(){
        this.dd = 1;
        this.mm = 1;
        this.yy = 1;
    }


    /**
     * Crea una fecha a partir de dia, mes y anio. Si la fecha no es valida
     * se avisa en pantalla y se asigna 1/1/1.
     * @param day el dia del mes
     * @param month el numero de mes (1 a 12)
     * @param year el anio (mayor o igual a 1)
     */
    public Fecha(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.dd = day;
            this.mm = month;
            this.yy = year;
        } else {
            System.out.println("Fecha invalida se reasigno a 1/1/1");
            this.dd = 1;
            this.mm = 1;
            this.yy = 1;
        }
    }

    /**
     * Crea una fecha a partir de dia, anio y nombre del mes. Si la fecha no
     * es valida se avisa en pantalla y se asigna 1/1/1.
     * @param day el dia del mes
     * @param year el anio (mayor o igual a 1)
     * @param month el nombre del mes, sin importar mayusculas ("marzo", "Marzo", "MARZO")
     */
    public Fecha(int day, int year, String month) {
        this(day, getMonthNumber(month), year);
    }



    /**
     * Devuelve el numero de mes que corresponde a un nombre de mes. No
     * importan las mayusculas ni los espacios al inicio o al final.
     * @param monthName el nombre del mes, por ejemplo "marzo" o "Marzo"
     * @return el numero de mes (1 a 12), o 0 si el nombre no es un mes
     */
    public static int getMonthNumber(String monthName) {
        int monthNumber = 0;
        String texto = monthName.trim();

        if (texto.length() > 0) {
            // "marzo" o "MARZO" -> "Marzo"
            String nombre = texto.substring(0, 1).toUpperCase()
                    + texto.substring(1).toLowerCase();

            if (nombre.equals("Enero")) {
                monthNumber = 1;
            } else if (nombre.equals("Febrero")) {
                monthNumber = 2;
            } else if (nombre.equals("Marzo")) {
                monthNumber = 3;
            } else if (nombre.equals("Abril")) {
                monthNumber = 4;
            } else if (nombre.equals("Mayo")) {
                monthNumber = 5;
            } else if (nombre.equals("Junio")) {
                monthNumber = 6;
            } else if (nombre.equals("Julio")) {
                monthNumber = 7;
            } else if (nombre.equals("Agosto")) {
                monthNumber = 8;
            } else if (nombre.equals("Septiembre")) {
                monthNumber = 9;
            } else if (nombre.equals("Octubre")) {
                monthNumber = 10;
            } else if (nombre.equals("Noviembre")) {
                monthNumber = 11;
            } else if (nombre.equals("Diciembre")) {
                monthNumber = 12;
            }
        }

        return monthNumber;
    }

    /**
     * Indica si un dia, mes y anio forman una fecha valida del calendario.
     * @param day el dia del mes
     * @param month el numero de mes
     * @param year el anio
     * @return true si el anio es mayor o igual a 1, el mes esta entre 1 y 12
     *         y el dia existe en ese mes; false en otro caso
     */
    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        int maxDays = getDaysForMonth(month, isYearLeap(year));
        if (day < 1 || day > maxDays) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve el nombre de un mes a partir de su numero.
     * @param monthNumber el numero de mes (1 a 12)
     * @return el nombre del mes, por ejemplo "Marzo", o "Desconocido" si el
     *         numero no esta entre 1 y 12
     */
    public static String getMonthName(int monthNumber) {
        String monthName = "";
        if (monthNumber == 1) {
            monthName = "Enero";
        } else if (monthNumber == 2) {
            monthName = "Febrero";
        } else if (monthNumber == 3) {
            monthName = "Marzo";
        } else if (monthNumber == 4) {
            monthName = "Abril";
        } else if (monthNumber == 5) {
            monthName = "Mayo";
        } else if (monthNumber == 6) {
            monthName = "Junio";
        } else if (monthNumber == 7) {
            monthName = "Julio";
        } else if (monthNumber == 8) {
            monthName = "Agosto";
        } else if (monthNumber == 9) {
            monthName = "Septiembre";
        } else if (monthNumber == 10) {
            monthName = "Octubre";
        } else if (monthNumber == 11) {
            monthName = "Noviembre";
        } else if (monthNumber == 12) {
            monthName = "Diciembre";
        } else {
            monthName = "Desconocido";
        }

        return monthName;
    }


    /**
     * Metodo auxiliar para obtener el numero de dias de un mes.
     * @param month el numero de mes
     * @param isLeap true si el anio es bisiesto, false si no lo es
     * @return numero de dias que tiene ese mes (0 si el mes no existe)
     */
    private static int getDaysForMonth(int month, boolean isLeap){
        int monthDays = 0;

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            monthDays = 31;
        }
        else if (month == 4 || month == 6 || month == 9 || month == 11) {
            monthDays = 30;
        }
        else if (month == 2) {
            if (isLeap) {
                monthDays = 29;
            } else {
                monthDays = 28;
            }
        }
        return  monthDays;
    }

    /**
     * Indica si un anio es bisiesto: divisible entre 4 pero no entre 100,
     * o divisible entre 400.
     * @param year el anio a revisar
     * @return true si el anio es bisiesto, false si no lo es
     */
    public static  boolean isYearLeap(int year){
        boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        return leap;
    }


    /**
     * Metodo auxiliar dta (date to absolute): convierte esta fecha en un
     * numero de dias contando desde 1/1/1, que vale 1.
     * @return el valor absoluto de esta fecha (1/1/1 = 1, 2/1/1 = 2, ...)
     */
    private int dta(){
        int currentYear = 1;
        int goalYear = this.getYear();
        boolean goalYearIsLeap = isYearLeap(goalYear);

        int totalDays = 0;

        while (currentYear < goalYear) {
            boolean currentYearIsLeap = isYearLeap(currentYear);
            if (currentYearIsLeap) {totalDays += 366;} else {totalDays += 365;}
            currentYear ++;
        }

        int currentMonth = 1;
        int goalMonth = this.getMonth();
        while (currentMonth < goalMonth) {
            totalDays += getDaysForMonth(currentMonth, goalYearIsLeap);
            currentMonth++ ;
        }

        totalDays += this.getDay();


        return totalDays;
    }


    /**
     * Metodo auxiliar atd (absolute to date): convierte un numero de dias
     * contados desde 1/1/1 en la fecha que le corresponde. Es la operacion
     * inversa de dta.
     * @param AbsoluteValue el numero de dias, donde 1 corresponde a 1/1/1
     * @return la Fecha que corresponde a ese valor absoluto
     */
    private static Fecha atd(int AbsoluteValue){
        int remainingDays = AbsoluteValue;
        int year = 1;
        int daysInCurrentYear = 365;
        while (remainingDays > daysInCurrentYear) {
            remainingDays -= daysInCurrentYear;
            year++;
            if (isYearLeap(year)) {daysInCurrentYear = 366;} else {daysInCurrentYear = 365;}
        }
        boolean isLeap = isYearLeap(year);

        int month = 1;
        int daysInCurrentMonth = getDaysForMonth(month, isLeap);
        while (remainingDays > daysInCurrentMonth) {
            remainingDays -= daysInCurrentMonth;
            month ++;
            daysInCurrentMonth = getDaysForMonth(month, isLeap);
        }
        int day = remainingDays;


        return new Fecha(day, month, year);
    }

    /**
     * Indica si esta fecha y otra son la misma fecha (mismo dia, mes y anio).
     * @param otherDate la fecha con la que se compara
     * @return true si ambas fechas son iguales, false en otro caso
     */
    public boolean equalsDate(Fecha otherDate) {
        return this.dd == otherDate.dd && this.mm == otherDate.mm && this.yy == otherDate.yy;
    }

    /**
     * Calcula la diferencia en dias desde esta fecha hasta otra.
     * @param otherDate la fecha hasta la que se cuenta
     * @return los dias que hay de esta fecha a otherDate (negativo si
     *         otherDate es anterior a esta fecha)
     */
    public int differenceDays(Fecha otherDate) {
        return otherDate.dta() - this.dta();
    }

    /**
     * Calcula la fecha que resulta de sumar (o restar) dias a esta fecha.
     * Esta fecha no se modifica. Por ejemplo, 3/9/2013 con 5 da 8/9/2013 y
     * con -2 da 1/9/2013.
     * @param days los dias a sumar; si es negativo se restan
     * @return una nueva Fecha con el resultado
     */
    public Fecha plusDays(int days) {
        return atd(this.dta() + days);
    }

    /**
     * Calcula el numero de dias que han pasado en el anio hasta esta fecha,
     * contandola. Por ejemplo, 30/12/2013 da 364.
     * @return dias transcurridos (del 1 al 365, o al 366 si el anio es bisiesto)
     */
    public int daysElapsed() {
        int totalDays = 0;
        boolean leap = isYearLeap(this.yy);
        int currentMonth = 1;

        while (currentMonth < this.mm) {
            totalDays += getDaysForMonth(currentMonth, leap);
            currentMonth++;
        }

        totalDays += this.dd;
        return totalDays;
    }

    /**
     * Devuelve la fecha en el formato dd/mes/aaaa, donde dd es el dia con
     * dos digitos, mes es el nombre del mes y aaaa es el anio con cuatro
     * digitos. Por ejemplo: 03/Septiembre/2013.
     * @return la fecha como cadena en formato dd/mes/aaaa
     */
    public String toString() {
        String dayStr = "" + this.dd;
        if (this.dd < 10) {
            dayStr = "0" + this.dd;
        }

        String yearStr = "" + this.yy;
        if (this.yy < 10) {
            yearStr = "000" + this.yy;
        } else if (this.yy < 100) {
            yearStr = "00" + this.yy;
        } else if (this.yy < 1000) {
            yearStr = "0" + this.yy;
        }

        return dayStr + "/" + getMonthName(this.mm) + "/" + yearStr;
    }

    /**
     * Asigna el dia de esta fecha. Si con ese dia la fecha no seria valida,
     * no se hace ningun cambio.
     * @param day el nuevo dia del mes
     */
    public void setDay(int day) {
        if (isValidDate(day, this.mm, this.yy)) {
            this.dd = day;
        }
    }

    /**
     * Asigna el mes de esta fecha a partir de su numero. Si con ese mes la
     * fecha no seria valida (por ejemplo, dia 31 en un mes de 30 dias), no
     * se hace ningun cambio.
     * @param month el nuevo numero de mes (1 a 12)
     */
    public void setMonth(int month) {
        if (isValidDate(this.dd, month, this.yy)) {
            this.mm = month;
        }
    }

    /**
     * Asigna el mes de esta fecha a partir de su nombre. Si el nombre no es
     * un mes, o con ese mes la fecha no seria valida, no se hace ningun cambio.
     * @param month el nombre del nuevo mes, sin importar mayusculas
     */
    public void setMonth(String month) {
        int monthNumber = getMonthNumber(month);
        if (isValidDate(this.dd, monthNumber, this.yy)) {
            this.mm = monthNumber;
        }
    }

    /**
     * Asigna el anio de esta fecha. Si con ese anio la fecha no seria valida
     * (por ejemplo, 29 de febrero en un anio no bisiesto), no se hace ningun
     * cambio.
     * @param year el nuevo anio (mayor o igual a 1)
     */
    public void setYear(int year) {
        if (isValidDate(this.dd, this.mm, year)) {
            this.yy = year;
        }
    }

    /**
     * Devuelve el dia de esta fecha.
     * @return el dia del mes
     */
    public int getDay(){
        return dd;
    }

    /**
     * Devuelve el mes de esta fecha.
     * @return el numero de mes (1 a 12)
     */
    public int getMonth(){
        return mm;
    }

    /**
     * Devuelve el anio de esta fecha.
     * @return el anio
     */
    public int getYear(){
        return yy;
    }

}
