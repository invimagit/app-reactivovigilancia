/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.reportes;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author jgutierrezme
 */
@ViewScoped
@ManagedBean(name="formateadorDatos")
public class FormateadorDatos implements Serializable 
{
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
    private final static Logger logBeanWebReactivo = Logger.getLogger(FormateadorDatos.class.getName());
    private java.util.Date fechaSistema;
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
    @PostConstruct
    public void init()
    {
        this.setFechaSistema(new java.util.Date());
    }
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
    public String formatearFechaReporte (java.util.Date fechaOriginal)
    {
        String fechaFormateada = "";
        
        try
        {
            fechaFormateada = String.valueOf(fechaOriginal.getYear()+1900) + "-" +
                              String.valueOf(agregarCero(fechaOriginal.getMonth()+1)) + "-" +
                              String.valueOf(agregarCero(fechaOriginal.getDate()));
            //logBeanWebReactivo.info ("FECHA FORMATEADA = " + fechaFormateada);
            
        }
        
        catch (Exception error)
        {
            java.util.Date fechaActual = new java.util.Date();
            fechaFormateada = String.valueOf(fechaActual.getYear()+1900) + "-" +
                              String.valueOf(agregarCero(fechaActual.getMonth()+1)) + "-" +
                              String.valueOf(agregarCero(fechaActual.getDate()));
            //logBeanWebReactivo.info ("FECHA FORMATEADA = " + fechaFormateada);
            //logBeanWebReactivo.info("co.gov.invima.util.Utilerias.formatearFechaReporte(): " + error.getLocalizedMessage());
            error.printStackTrace();
            return (fechaFormateada);
        }
        
        return (fechaFormateada);
    }
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
    private String agregarCero (int valor)
    {
        String dato = "";
        
        if (valor < 10)
        {
            dato = "0" + String.valueOf(valor);
        }
        else
        {
            dato = String.valueOf(valor);
        }
        
        return (dato);
    }
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
    /**
     * @return the fechaSistema
     */
    public java.util.Date getFechaSistema() {
        return fechaSistema;
    }

    /**
     * @param fechaSistema the fechaSistema to set
     */
    public void setFechaSistema(java.util.Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
    //**************************************************************************
}
