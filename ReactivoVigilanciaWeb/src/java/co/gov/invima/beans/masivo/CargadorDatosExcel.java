/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans.masivo;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
import java.io.InputStream;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/**
 *
 * @author jgutierrezme
 */
public class CargadorDatosExcel 
{
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    private final static Logger logBeanWebReactivo = Logger.getLogger(CargadorDatosExcel.class.getName());
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public java.util.ArrayList<String> obtenerDatosArchivoCargue (InputStream flujoArchivo)
    {
        java.util.ArrayList<String> datosExcel = new java.util.ArrayList<String>();
        //************************************************************************************
        //************************************************************************************
        //************************************************************************************
        String dato = "";
        String linea = "";
        double dato2 = 0.0;
        boolean dato3 = false;
        XSSFCell cell = null;
        int contadorFilas = 2;
        int contadorLineas = 0;
        int contadorColumnas = 0;
        //************************************************************************************
        //************************************************************************************
        //************************************************************************************
        try
        {
            Workbook workbook = new XSSFWorkbook(flujoArchivo);
            Sheet firstSheet = workbook.getSheetAt(0);
            String datoCargada = "";
            java.util.Date fecha = null;
            
            //logBeanWebReactivo.info ("------------------------------------------------------------------->");
            //logBeanWebReactivo.info ("------------------------------------------------------------------->");
            //logBeanWebReactivo.info ("INICIANDO PROCESAMIENTO DEL ARCHIVO DE EXCEL DE REACTIVO VIGILANCIA " + flujoArchivo);
            //*********************************************************************************
            //*********************************************************************************
            //*********************************************************************************
            //logBeanWebReactivo.info ("************************************************************************");
            //logBeanWebReactivo.info ("************************************************************************");
            //logBeanWebReactivo.info ("************************************************************************");
            contadorColumnas = 0;
            contadorLineas = 1;
            contadorFilas = 2;
            //logBeanWebReactivo.info ("************************************************************************");
            //logBeanWebReactivo.info ("************************************************************************");
            //logBeanWebReactivo.info ("************************************************************************");
            //logBeanWebReactivo.info ("PROCESANDO RESTANTES FILAS A PARTIR DE LA FILA = " + contadorFilas);
            //logBeanWebReactivo.info ("************************************************************************");
            //logBeanWebReactivo.info ("************************************************************************");
            boolean esNulo = false;
            Row nextRow = null;
            Iterator<Cell> cellIterator = null;
            //*********************************************************************************
            //*********************************************************************************
            do
            {
                try
                {
                    //**********************************************************************
                    //**********************************************************************
                    //**********************************************************************
                    //logBeanWebReactivo.info ("Trayendo fila = " + contadorFilas);
                    
                    nextRow = firstSheet.getRow(contadorFilas);
                    
                    if (nextRow != null)
                    {
                        cellIterator = nextRow.cellIterator();
                        //System.out.print("Procesando linea = " + contadorLineas + " - ");
                        contadorColumnas = 0;

                        while (cellIterator.hasNext()) 
                        {
                                cell = (XSSFCell)cellIterator.next();

                                datoCargada = "";
                                
                                switch (cell.getCellType()) 
                                {
                                    case XSSFCell.CELL_TYPE_STRING:
                                    {
                                        dato = cell.getStringCellValue();
                                        datoCargada = dato;
                                        //System.out.print(dato);
                                        //logBeanWebReactivo.info ("DATO DE TIPO STRING: " + datoCargada + " EN LA COLUMNA: " + contadorColumnas);
                                        break;
                                    }

                                    case XSSFCell.CELL_TYPE_NUMERIC:
                                    {
                                        dato2 = cell.getNumericCellValue();

                                        if (HSSFDateUtil.isCellDateFormatted(cell))
                                        {
                                            fecha = cell.getDateCellValue();
                                            //logBeanWebReactivo.info ("Encontre fecha = " + fecha);
                                            datoCargada = generarFormatoFechaGrilla(fecha);
                                        }
                                        else
                                        {
                                            long varA = new Double(dato2).longValue();
                                            datoCargada = String.valueOf(varA);
                                        }

                                        //System.out.print(dato2);
                                        //logBeanWebReactivo.info ("DATO DE TIPO NUMERICO: " + datoCargada + " EN LA COLUMNA: " + contadorColumnas);
                                        break;
                                    }

                                    case XSSFCell.CELL_TYPE_BOOLEAN:
                                    {
                                        dato3 = cell.getBooleanCellValue();
                                        //System.out.print(dato3);
                                        datoCargada = String.valueOf(dato3);
                                        //logBeanWebReactivo.info ("DATO DE TIPO BOOLEANO: " + datoCargada + " EN LA COLUMNA: " + contadorColumnas);
                                        break;
                                    }

                                    //default:
                                    // {
                                    //    dato = "NO DISPONIBLE";
                                    //    datoCargada = dato;
                                    //    break;
                                    //}

                                }//Fin del switch

                            linea += datoCargada + ";";
                            datosExcel.add(String.valueOf(datoCargada.trim()));
                            //System.out.print(" - ");
                            contadorColumnas++;
                        }

                        //logBeanWebReactivo.info ("TOTAL DE COLUMNAS DE LA FILA " + contadorLineas + ": " + contadorColumnas);
                        //logBeanWebReactivo.info(linea);
                        contadorLineas++;
                        //**********************************************************************
                        //**********************************************************************
                        //**********************************************************************
                        contadorFilas++;
                   
                    }//Fin del if de obtención de datos de la fila
                    else
                    {
                        esNulo = true;
                        //logBeanWebReactivo.info ("-------------------------------------------------------");
                        //logBeanWebReactivo.info ("-------------------------------------------------------");
                        //logBeanWebReactivo.info ("YA NO HAY MAS FILAS PARA PROCESAR EN EL ARCHIVO");
                        //logBeanWebReactivo.info ("CONDICION DE SALIDA = " + esNulo);
                        //logBeanWebReactivo.info ("-------------------------------------------------------");
                        //logBeanWebReactivo.info ("-------------------------------------------------------");
                    }
                    
                }//Fin del try interno de procesamiento de filas a partir de la 3
                
                catch (Exception errorFila)
                {
                    errorFila.printStackTrace();
                    esNulo = true;
                }
                
            } while (esNulo != true);
                    
            //Siguientes filas
            //logBeanWebReactivo.info ("TOTAL DE FILAS PROCESADAS = " + (contadorFilas-2));
            //logBeanWebReactivo.info ("FINALIZANDO PROCESAMIENTO DEL ARCHIVO");
            //logBeanWebReactivo.info ("------------------------------------------------------------------->");
            //logBeanWebReactivo.info ("------------------------------------------------------------------->");
            //**************************************************************************************************
            //**************************************************************************************************
            //**************************************************************************************************
            //logBeanWebReactivo.info ("Cerrando archivo");
            workbook.close();
            //**************************************************************************************************
            //**************************************************************************************************
            //**************************************************************************************************
            if (flujoArchivo != null)
            {
                //logBeanWebReactivo.info ("Cerrando flujo de archivo");
                flujoArchivo.close();
            }
            //**************************************************************************************************
            //**************************************************************************************************
            //**************************************************************************************************
        }//Fin del try principal
        
        catch (Exception errorCargue)
        {
            //logBeanWebReactivo.info ("Error en el cargue = " + errorCargue.getMessage());
            errorCargue.printStackTrace();
            return (null);
        }
        
        return (datosExcel);
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public java.util.ArrayList<String> obtenerDatosArchivoCargueExcelAntiguo (InputStream flujoArchivo)
    {
        java.util.ArrayList<String> datosExcel = new java.util.ArrayList<String>();
        //************************************************************************************
        //************************************************************************************
        //************************************************************************************
        String dato = "";
        String linea = "";
        double dato2 = 0.0;
        boolean dato3 = false;
        HSSFCell cell = null;
        int contadorFilas = 2;
        int contadorLineas = 0;
        int contadorColumnas = 0;
        //************************************************************************************
        //************************************************************************************
        //************************************************************************************
        try
        {
            Workbook workbook = new HSSFWorkbook(flujoArchivo);
            Sheet firstSheet = workbook.getSheetAt(0);
            String datoCargada = "";
            java.util.Date fecha = null;
            
            //logBeanWebTecno.info  ("------------------------------------------------------------------->");
            //logBeanWebTecno.info  ("------------------------------------------------------------------->");
            //logBeanWebTecno.info  ("INICIANDO PROCESAMIENTO DEL ARCHIVO DE EXCEL ANTIGUO " + flujoArchivo);
            //*********************************************************************************
            //*********************************************************************************
            //*********************************************************************************
            //logBeanWebTecno.info  ("************************************************************************");
            //logBeanWebTecno.info  ("************************************************************************");
            //logBeanWebTecno.info  ("************************************************************************");
            contadorColumnas = 0;
            contadorLineas = 1;
            contadorFilas = 2;
            //logBeanWebTecno.info  ("************************************************************************");
            //logBeanWebTecno.info  ("************************************************************************");
            //logBeanWebTecno.info  ("************************************************************************");
            //logBeanWebTecno.info  ("PROCESANDO RESTANTES FILAS A PARTIR DE LA FILA = " + contadorFilas);
            //logBeanWebTecno.info  ("************************************************************************");
            //logBeanWebTecno.info  ("************************************************************************");
            boolean esNulo = false;
            Row nextRow = null;
            Iterator<Cell> cellIterator = null;
            //*********************************************************************************
            //*********************************************************************************
            do
            {
                try
                {
                    //**********************************************************************
                    //**********************************************************************
                    //**********************************************************************
                    //logBeanWebTecno.info  ("Trayendo fila = " + contadorFilas);
                    
                    nextRow = firstSheet.getRow(contadorFilas);
                    
                    if (nextRow != null)
                    {
                        cellIterator = nextRow.cellIterator();
                        //System.out.print("Procesando linea = " + contadorLineas + " - ");
                        contadorColumnas = 0;

                        while (cellIterator.hasNext()) 
                        {
                                cell = (HSSFCell)cellIterator.next();

                                switch (cell.getCellType()) 
                                {
                                    case HSSFCell.CELL_TYPE_STRING:
                                    {
                                        dato = cell.getStringCellValue();
                                        datoCargada = dato;
                                        //System.out.print(dato);
                                        break;
                                    }

                                    case HSSFCell.CELL_TYPE_NUMERIC:
                                    {
                                        dato2 = cell.getNumericCellValue();

                                        if (HSSFDateUtil.isCellDateFormatted(cell))
                                        {
                                            fecha = cell.getDateCellValue();
                                            //logBeanWebTecno.info  ("Encontre fecha = " + fecha);
                                            datoCargada = generarFormatoFechaGrilla(fecha);
                                        }
                                        else
                                        {
                                            long varA = new Double(dato2).longValue();
                                            datoCargada = String.valueOf(varA);
                                        }

                                        //System.out.print(dato2);
                                        break;
                                    }

                                    case HSSFCell.CELL_TYPE_BOOLEAN:
                                    {
                                        dato3 = cell.getBooleanCellValue();
                                        //System.out.print(dato3);
                                        datoCargada = String.valueOf(dato3);
                                        break;
                                    }


                                    //default:
                                    // {
                                    //    dato = "NO DISPONIBLE";
                                    //    datoCargada = dato;
                                    //    break;
                                    //}

                                }//Fin del switch

                            linea += datoCargada + ";";
                            datosExcel.add(String.valueOf(datoCargada.trim()));
                            //System.out.print(" - ");
                            contadorColumnas++;
                        }

                        //logBeanWebReactivo.info  ("TOTAL DE COLUMNAS DE LA FILA " + contadorLineas + ": " + contadorColumnas);
                        //logBeanWebTecno.info (linea);
                        contadorLineas++;
                        //**********************************************************************
                        //**********************************************************************
                        //**********************************************************************
                        contadorFilas++;
                   
                    }//Fin del if de obtención de datos de la fila
                    else
                    {
                        esNulo = true;
                        //logBeanWebTecno.info  ("-------------------------------------------------------");
                        //logBeanWebTecno.info  ("-------------------------------------------------------");
                        //logBeanWebTecno.info  ("YA NO HAY MAS FILAS PARA PROCESAR EN EL ARCHIVO DE EXCEL ANTIGUO");
                        //logBeanWebTecno.info  ("CONDICION DE SALIDA = " + esNulo);
                        //logBeanWebTecno.info  ("-------------------------------------------------------");
                        //logBeanWebTecno.info  ("-------------------------------------------------------");
                    }
                    
                }//Fin del try interno de procesamiento de filas a partir de la 3
                
                catch (Exception errorFila)
                {
                    errorFila.printStackTrace();
                    esNulo = true;
                }
                
            } while (esNulo != true);
                    
            //logBeanWebTecno.info  ("TOTAL DE FILAS PROCESADAS = " + (contadorFilas-2));
            //logBeanWebTecno.info  ("FINALIZANDO PROCESAMIENTO DEL ARCHIVO");
            //logBeanWebTecno.info  ("------------------------------------------------------------------->");
            //logBeanWebTecno.info  ("------------------------------------------------------------------->");
            //**************************************************************************************************
            //**************************************************************************************************
            //**************************************************************************************************
            //logBeanWebTecno.info  ("Cerrando archivo");
            workbook.close();
            //**************************************************************************************************
            //**************************************************************************************************
            //**************************************************************************************************
            if (flujoArchivo != null)
            {
                //logBeanWebTecno.info  ("Cerrando flujo de archivo");
                flujoArchivo.close();
            }
            //**************************************************************************************************
            //**************************************************************************************************
            //**************************************************************************************************
        }//Fin del try principal
        
        catch (Exception errorCargue)
        {
            //logBeanWebTecno.info  ("Error en el cargue = " + errorCargue.getMessage());
            errorCargue.printStackTrace();
            return (null);
        }
        
        return (datosExcel);
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    private String generarFormatoFechaGrilla (java.util.Date fechaExcel)
    {
        String fechaCadena = "";
        //logBeanWebReactivo.info ("Fecha traida del Excel = " + fechaExcel.toLocaleString());
        int dia = 0;
        int mes = 0;
        String valorDia = "";
        String valorMes = "";
        String valorYear = "";
        
        dia = fechaExcel.getDate();
        if (dia < 10)
        {
            valorDia = "0" + String.valueOf(dia);
        }
        else
        {
            valorDia = String.valueOf(dia);
        }
        
        mes = fechaExcel.getMonth();
        if (mes < 10)
        {
            valorMes = "0" + String.valueOf(mes);
            //valorMes = "0" + String.valueOf(mes+1);
        }
        else
        {
            valorMes = String.valueOf(mes);
            //valorMes = String.valueOf(mes+1);
        }
        
        valorYear = String.valueOf(fechaExcel.getYear()+1900);
        
        fechaCadena = valorDia + "/" + valorMes + "/" + valorYear;                
        //logBeanWebReactivo.info ("Fecha construida = " + fechaCadena);
        
        return (fechaCadena);
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    /*
    private String arregloNumero (int dia)
    {
        String valor = "";
        
        if (dia < 10)
        {
            valor = "0" + String.valueOf(dia);
        }
        else
        {
            String.valueOf(dia);
        }
        
        return (valor);
    }
    */
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
}//Fin de la clase

/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
