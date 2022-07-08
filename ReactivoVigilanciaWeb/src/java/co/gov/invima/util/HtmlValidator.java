/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.util;

/**
 *
 * @author asd
 */
public class HtmlValidator {
    private static final String[] valoresTildesRemplazar ={"á", "é", "í", "ó", "ú", "Á", "É", "Í", "Ó", "Ú", "ñ", "Ñ"};
    private static final String[] valoresTildesRemplazo ={"&aacute;","&eacute;", "&iacute;", "&oacute;", "&uacute;","&Aacute;","&Eacute;", "&Iacute;", "&Oacute;", "&Uacute;", "&ntilde;", "&Ntilde;"};
    
    public static String remplazarTildesEnTexto(String texto){
        String textoReplace = texto;
        for (int i = 0; i < valoresTildesRemplazar.length; i++) {
            textoReplace = textoReplace.replaceAll(valoresTildesRemplazar[i], valoresTildesRemplazo[i]);
        }
        return textoReplace;
    }
}
