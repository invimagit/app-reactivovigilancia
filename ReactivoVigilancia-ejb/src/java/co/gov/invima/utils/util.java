package co.gov.invima.utils;

import java.util.Date;

public class util {
    
    public static int generarConsecutivoSiguiente(String consecutivo_anterior) {
        return Integer.parseInt(generarConsecutivoSiguienteGenerico(consecutivo_anterior,""));
    }
    
    public static String generarConsecutivoSiguienteGenerico(String consecutivo_anterior,String separador_fecha) {
        String consecutivo = consecutivo_anterior.replace("COL", "");
        int consecutivo_interno = 0;
        Date fecha = Fecha.crearFecha();
        int fecha_actual_yyMM = Integer.parseInt(Fecha.formateaFecha(fecha, "yyMM"));
        int año_actual_yy = Integer.parseInt(Fecha.formateaFecha(fecha, "yy"));
        
        //Verificar que el consecutivo maneje el formato yyMM00000 ej. 200400001
       if(consecutivo.length() > 6){
           if(consecutivo.startsWith((año_actual_yy+"")) ){
                //extraer consecutivo interno
                consecutivo_interno = Integer.parseInt(consecutivo.substring(4));
                consecutivo_interno++;
            }
        }

       //Formatear consecutivo interno
        String consecutivo_interno_formateado = consecutivo_interno+"";
        if (consecutivo_interno_formateado.length() < 5) {
            for (int j = 0; consecutivo_interno_formateado.length() < 5; j++) {
                consecutivo_interno_formateado = "0" + consecutivo_interno_formateado;
            }
        }
        
        String consecutivo_externo = (fecha_actual_yyMM+separador_fecha+consecutivo_interno_formateado);

        return consecutivo_externo;
    }
}
