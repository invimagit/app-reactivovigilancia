/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.util;

/**
 * Calcula el dígito de verificación del NIT
 * @author mgualdrond
 */
public class CalcularDigitoVerificacionNIT {
    
    /**
     * Números primos hasta 10 digitos del documento
     */
    private static final int[] primos = {3,7,13,17,19,23,29,37,41,43,47};
    
    public static int obtenerDV(long numeroDocumento){
        int resultado=-1;
        try {
            String tempNum = Long.toString(numeroDocumento);
            int[] nitAlReves = new int[tempNum.length()];
            int j=0;
            for(int i=nitAlReves.length-1;i>=0;i--){
                nitAlReves[i]= tempNum.charAt(j)-'0';
                j++;
            }
            int sumaProd=0;
            for (int i = 0; i < nitAlReves.length; i++) {
                sumaProd+=nitAlReves[i]* primos[i];
            }
            int residuo = sumaProd % 11;
            if(residuo==0 || residuo==1){
                resultado=residuo;
            }else{
                resultado=11-residuo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return resultado;
        }
    }
    
}
