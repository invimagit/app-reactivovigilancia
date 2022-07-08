    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.utils;

/**
 *
 * @author jgutierrezme
 */
public class ManejadorDatos 
{
    //***********************************************************
    //***********************************************************
    //***********************************************************
    public String validarDatoCampoBaseDatos (Object valor, String tipo)
    {
        String dato = "";
        String campo = "";
        
        //logEJBReactivo.info ("Valor del dato traido de la base de datos = " + valor);
        
        if (valor != null)
        {
            if (tipo.equals("numero"))
            {
                if (valor != null)
                {
                    campo = valor.toString();

                    if (campo.length() > 0)
                    {
                        dato = campo;
                    }
                    else
                    {
                        dato = "0";
                    }
                }
            }
            else
            if (tipo.equals("cadena"))
            {
                if (valor != null)
                {
                    campo = valor.toString();

                    if (campo.length() > 0)
                    {
                        dato = campo;
                    }
                    else
                    {
                        dato = "SIN REGISTRO";
                    }
                }
            }
        }
        else
        {
            dato = "0";
        }
        //logEJBReactivo.info  ("DATO A ENVIAR = " + dato);
        return (dato);
    }
    //***********************************************************
    //***********************************************************
    //***********************************************************
    public java.util.Date validarCapturaFechaBaseDatos (Object valor)
    {
        java.util.Date fecha = null;

        if (valor != null)
        {
            fecha = (java.util.Date)valor;
        }
        else
        {
            fecha = new java.util.Date();
        }

        return (fecha);
    }
    //***********************************************************
    //***********************************************************
    //***********************************************************
    //***********************************************************
    //***********************************************************
    //***********************************************************
}
