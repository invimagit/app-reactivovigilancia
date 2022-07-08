/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.utils;

import java.util.Formatter;

/**
 *
 * @author A S D
 */
public class EncripcionUtils {


     // public static void main(String[] args) {

//        EncripcionUtils.desEncriptar("223356187005175129187192102312117133306296201243407411142149072057096089164159174309501158");
//        //logEJBReactivo.info(EncripcionUtils.desEncriptar("223356187005175129187192102312117133306296201243407411142149072057096089164159174309501158"));
//          //logEJBReactivo.info(EncripcionUtils.desEncriptar("228360187005175129140192102312117133315296201243407411082109072057096089159167174309501158"));
//          //logEJBReactivo.info(EncripcionUtils.encripcion("lunes"));
//      }
     /**
     *
     * @param as_str
     * @return
     */
    public static String desEncriptar(String as_str) {
        Integer i, j;
        String ls_enc = "";
        Integer a[] = {0, 15, 200, 41, 38, 27, 12, 46, -15, -50, 12, 38, 21, 43, 90, 12, 101, 150, 180, 24, 13, 18, 19, 56, 1, 17, 29, -41, 47, 63, 81};
        Integer b[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Integer cx[] = {0, 12, 45, 67, -24, 87, 68, 25, 46, 101, 250, 34, 56, -18, 24, 56, 98, 135, 156, -12, 1, 10, 56, 23, 10, 19, 54, 145, 189, 206, 30};
        Integer d[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Integer li_llave, c1, c2, c3, c4, t1, t2, t3, t4, t5;
        String ls_cadena, ls_cad, cadena1, cadena2, cadena3, cadena4, cadena5;
        String c, cadena, ls_cad2;
        Integer t;
        ls_cad = "/" + as_str.trim();
        cadena1 = "/";
        cadena2 = "/";
        cadena3 = "/";
        cadena4 = "/";
        cadena5 = "/";
        cadena = "/";
        ls_cad2 = "/";
        t1 = 1;
        for (t = 1; t <= 90; t += 3) {
            b[t1] = Integer.valueOf(ls_cad.substring(t, t + 3));
            if (b[t1] > 700) {
                b[t1] = (b[t1] * -1) + 700;
            }
            t1++;
        }

        ls_enc = "/";
        cadena = "/";
        for (t = 1; t <= 30; t++) {
            b[t] = b[t] - cx[t];
        }

        t1 = 1;
        for (t = 30; t >= 1; t--) {
            d[t] = b[t] - a[t1];
            t1++;
        }

        ls_cad2 = "/";
        for (t = 1; t <= 30; t++) {
            c1 = d[t] - a[t];
            c = ((char) c1.intValue()) + "";
            ls_cad2 += c;
        }

        cadena1 = "/" + ls_cad2.substring(19, 19 + 6);
        cadena2 = ls_cad2.substring(13, 13 + 6);
        cadena3 = ls_cad2.substring(25, 25 + 6);
        cadena4 = ls_cad2.substring(1, 1 + 6);
        cadena5 = ls_cad2.substring(7, 7 + 6);
        ls_cad = cadena1 + cadena2 + cadena3 + cadena4 + cadena5;
        cadena = "";
        cadena2 = "/" + cadena2;
        cadena3 = "/" + cadena3;
        cadena4 = "/" + cadena4;
        cadena5 = "/" + cadena5;
        t1 = 6;
        t2 = 6;
        t3 = 6;
        t4 = 6;
        t5 = 6;
        for (t = 30; t >= 2; t--) {
            if (t == 29) {
                c = cadena4.substring(t4, t4 + 1);
                cadena = c + cadena;
                t4--;
                continue;
            }
            if (t == 23) {
                c = cadena3.substring(t3, t3 + 1);
                cadena = c + cadena;
                t3--;
                continue;
            }
            if (t == 19) {
                c = cadena5.substring(t5, t5 + 1);
                cadena = c + cadena;
                t5--;
                continue;
            }
            if (t == 17) {
                c = cadena4.substring(t4, t4 + 1);
                cadena = c + cadena;
                t4--;
                continue;
            }
            if (t == 13) {
                c = cadena3.substring(t3, t3 + 1);
                cadena = c + cadena;
                t3--;
                continue;
            }
            if (t == 11) {
                c = cadena2.substring(t2, t2 + 1);
                cadena = c + cadena;
                t2--;
                continue;
            }
            if (t == 7) {
                c = cadena1.substring(t1, t1 + 1);
                cadena = c + cadena;
                t1--;
                continue;
            }
            if (t % 6 == 0) {
                c = cadena1.substring(t1, t1 + 1);
                cadena = c + cadena;
                t1--;
                continue;
            }
            if (t % 5 == 0) {
                c = cadena2.substring(t2, t2 + 1);
                cadena = c + cadena;
                t2--;
                continue;
            }
            if (t % 4 == 0) {
                c = cadena3.substring(t3, t3 + 1);
                cadena = c + cadena;
                t3--;
                continue;
            }
            if (t % 3 == 0) {
                c = cadena4.substring(t4, t4 + 1);
                cadena = c + cadena;
                t4--;
                continue;
            }
            if (t % 2 == 0) {
                c = cadena5.substring(t5, t5 + 1);
                cadena = c + cadena;
                t5--;
                continue;
            }
        }
        cadena = cadena5.substring(1, 2) + cadena;
        cadena = "/" + cadena;
        ls_enc = "";
        for (t = 1; t <= 30; t++) {
            c = cadena.substring(t, t + 1);
            if (!c.equals(" ")) {
                ls_enc += c;
            }
        }

        return ls_enc;
    }

    /**
     * Funcion para encriptar una cadena de texto segun el algoritmo usado en el invima
     * @param as_str
     * @return
     */
    public static String encripcion(String as_str) {
        Integer i, j;
        String ls_enc = "";
        Integer a[] = {0, 15, 200, 41, 38, 27, 12, 46, -15, -50, 12, 38, 21, 43, 90, 12, 101, 150, 180, 24, 13, 18, 19, 56, 1, 17, 29, -41, 47, 63, 81};
        Integer b[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Integer cx[] = {0, 12, 45, 67, -24, 87, 68, 25, 46, 101, 250, 34, 56, -18, 24, 56, 98, 135, 156, -12, 1, 10, 56, 23, 10, 19, 54, 145, 189, 206, 30};
        Integer li_llave, c1, c2, c3, t1;
        String ls_cadena, ls_cad, cadena1, cadena2, cadena3, cadena4, cadena5;
        String c, cadena;
        Integer t;
        ls_cad = as_str;
        li_llave = ls_cad.length();
        cadena = as_str + spaces(30 - li_llave);
        ls_cad = "/" + cadena;
        cadena1 = "";
        cadena2 = "";
        cadena3 = "";
        cadena4 = "";
        cadena5 = "";
        cadena5 += ls_cad.substring(1, 2);
        for (t = 2; t <= 30; t++) {
            c = ls_cad.substring(t, t + 1);
            if (t % 6 == 0) {
                cadena1 += c;
                continue;
            }
            if (t % 5 == 0) {
                cadena2 += c;
                continue;
            }
            if (t % 4 == 0) {
                cadena3 += c;
                continue;
            }
            if (t % 3 == 0) {
                cadena4 += c;
                continue;
            }
            if (t % 2 == 0) {
                cadena5 += c;
                continue;
            }
            if (t == 7) {
                cadena1 += c;
                continue;
            }
            if (t == 11) {
                cadena2 += c;
                continue;
            }
            if (t == 13) {
                cadena3 += c;
                continue;
            }
            if (t == 17) {
                cadena4 += c;
                continue;
            }
            if (t == 19) {
                cadena5 += c;
                continue;
            }
            if (t == 23) {
                cadena3 += c;
                continue;
            }
            if (t == 29) {
                cadena4 += c;
                continue;
            }
        }

        cadena = "";
        cadena = "/" + cadena4 + cadena5 + cadena2 + cadena1 + cadena3;
        for (t = 1; t <= 30; t++) {
            c = cadena.substring(t, t + 1);
            c1 = ((int) c.charAt(0)) + a[t];
            b[t] = c1;
        }

        t1 = 30;
        for (t = 1; t <= 30; t++) {
            c1 = b[t] + a[t1];
            b[t] = c1;
            t1--;
        }

        ls_enc = "";
        for (t = 1; t <= 30; t++) {
            Formatter fmt = new Formatter();
            c1 = b[t] + cx[t];
            if (c1 < 0) {
                fmt.format("%03d", ((c1 * -1) + 700));
                ls_enc += fmt;//String.format("%s",Integer.toString((c1 * -1) + 700));
            } else {
                fmt.format("%03d", c1);
                ls_enc += fmt;//String.format("%s",Integer.toString(c1));
            }
            fmt.flush();
        }

        return ls_enc;
    }


    /**
     * 
     * @param cantidad
     * @return
     */
    private static String spaces(int cantidad) {
        String espacios = "";
        for (int i = 0; i < cantidad; i++) {
            espacios += " ";
        }
        return espacios;
    }
}
