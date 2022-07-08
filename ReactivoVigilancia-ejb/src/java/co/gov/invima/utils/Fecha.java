/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.utils;

/**
 *
 * @author Diana Silva
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase que maneja fechas
 *
 *
 */
public class Fecha {

    /**
     * Metodo que compara dos string con fechas dd/MM/yyyy
     *
     *
     * @param fecha1 String con la fecha (dd/MM/yyyy)
     * @param fecha2 String con la fecha (dd/MM/yyyy)
     *
     * @return 0 si fecha1 = fecha2, menor que 0 si fecha1 es menor que fecha2, mayor que 0 si fecha1 es mayor que fecha2
     */
    public static int comparaFecha( String fecha1, String fecha2 ) {
        int retorno = 0;
        Date f1 = crearFecha(fecha1);
        Date f2 = crearFecha(fecha2);
        retorno = f1.compareTo(f2);

        return retorno;
    }

    /**
     * Metodo que crea una fecha actual con hora = 0, minutos = 0
     * @return Date con con hora = 0, minutos = 0
     */
    public static Date crearFecha( ) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String fechaCortePeriodo = new String(formatter.format(new Date()));
        int dia = Integer.parseInt(fechaCortePeriodo.substring(0, 2));
        int mes = Integer.parseInt(fechaCortePeriodo.substring(3, 5));
        int anio = Integer.parseInt(fechaCortePeriodo.substring(6, 10));
        GregorianCalendar gc = new GregorianCalendar(anio, mes-1, dia);

        return gc.getTime();
    }

    /**
     * Metodo que retorna una fecha dado una cadena con año, mes y dia
     *
     *
     * @param fecha Date con la fecha dada dd/mm/aaaa
     *
     * @return fecha con el año mes dia especificado
     */
    public static Date crearFecha( String fecha ) {
        int dia = Integer.parseInt(fecha.substring(0, 2));
        int mes = Integer.parseInt(fecha.substring(3, 5));
        int anio = Integer.parseInt(fecha.substring(6, 10));
        GregorianCalendar gc = new GregorianCalendar(anio, mes-1, dia);

        return gc.getTime();
    }

    /**
     * Metodo que retorna una fecha dado año, mes y dia
     *
     *
     * @param anio
     * @param mes
     * @param dia
     *
     * @return fecha con el año mes dia especificado
     */
    public static Date crearFecha( int anio, int mes, int dia ) {
        GregorianCalendar gc = new GregorianCalendar(anio, mes-1, dia);

        return gc.getTime();
    }

    /**
     * Metodo que formatea una fecha
     *
     *
     * @param fecha
     * @param formato
     *
     * @return String con la fecha formateada
     */
    public static String formateaFecha( Date fecha, String formato ) {
        String fechaFormateada = "";
        SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
        fechaFormateada = formatoFecha.format(fecha);

        return fechaFormateada;
    }
}