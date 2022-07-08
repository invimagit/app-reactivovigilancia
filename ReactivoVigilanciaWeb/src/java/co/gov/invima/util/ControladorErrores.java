/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.util;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author jgutierrez
 */
@WebServlet("/ControladorErrores")
public class ControladorErrores extends HttpServlet 
{
    private final Logger logBeanWebReactivo = Logger.getLogger(ControladorErrores.class);
    private String nombreUsuario;
    private String idUsuario;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  // Method to handle GET method request.
  //*************************************************************************
  //*************************************************************************
  //*************************************************************************
  public void procesarPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
      String error = "";
      Throwable throwable = null;
      Integer statusCode = new Integer(0);
      String servletName = "";
      String errorDetectado = "";
      String errorEncontrado = "";
      String errorTecnico = "";
      String requestUri = "";
      HttpSession sesion = request.getSession();
      
      try
      {
          logBeanWebReactivo.info ("Procesando error en el servlet de control");
          //logBeanWebReactivo.info ("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
          //logBeanWebReactivo.info ("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
          //*****************************************************************************************
          //*****************************************************************************************
          //*****************************************************************************************
          //logBeanWebReactivo.info ("Estado de la sesión actual: " + sesion);
          this.nombreUsuario = "anonimo";
          this.idUsuario = "1";
           //*****************************************************************************************
          //*****************************************************************************************
          if ((String)request.getParameter("error") != null)
          {
              errorDetectado = (String)request.getParameter("error");
              //logBeanWebReactivo.info ("**********************");
              //logBeanWebReactivo.info ("**********************");
              //logBeanWebReactivo.info ("Entrando por configuracion de error: " + errorDetectado);
              
              //logBeanWebReactivo.info ("**********************");
              //logBeanWebReactivo.info ("**********************");
              //logBeanWebReactivo.info ("DATOS PREAUDITORIA");
              //logBeanWebReactivo.info ("logBeanWebReactivo: " + this.logBeanWebReactivo);
              //logBeanWebReactivo.info ("ID USUARIO: " + this.idUsuario);
              //logBeanWebReactivo.info ("NOMBRE USUARIO: " + this.nombreUsuario);
              //logBeanWebReactivo.info ("**********************");
              //logBeanWebReactivo.info ("**********************");
              
              
              if (errorDetectado.equals("sqlinjection"))
              {
                  //logBeanWebReactivo.info ("Reportando error de seguridad");
                  errorEncontrado = errorDetectado;
                  //logBeanWebReactivo.info ("Error específico = " + errorEncontrado);
                  //******************************************************
                  //******************************************************
                  //******************************************************
                  //******************************************************
                  statusCode = 0;
                  errorDetectado = (String)request.getAttribute("mensajeErrorS");
                  sesion.setAttribute("mensajeErrorPE",errorDetectado);
                  sesion.setAttribute("codigoErrorPE",statusCode);
                  //******************************************************
                  //******************************************************
                  //******************************************************
                  logBeanWebReactivo.info ("***********************************************************************************************");
                  logBeanWebReactivo.info ("***********************************************************************************************");
                  logBeanWebReactivo.info ("***********************************************************************************************");
                  logBeanWebReactivo.info ("Fecha de ocurrencia del ataque: " + new java.util.Date());
                  logBeanWebReactivo.info ("Tipo de ataque: XSS o SQL Injection");
                  logBeanWebReactivo.info ("Código de Error Web: " + statusCode);
                  logBeanWebReactivo.info ("Error de Seguridad del Sistema de Información: " + errorDetectado + " desde la dirección IP: " + request.getRemoteAddr());
                  logBeanWebReactivo.info ("***********************************************************************************************");
                  logBeanWebReactivo.info ("***********************************************************************************************");
                  logBeanWebReactivo.info ("***********************************************************************************************");
                  //******************************************************
                  //******************************************************
                  //******************************************************
                  response.sendRedirect("/ReactivoVigilanciaWeb/faces/paginaError.xhtml");
              }
          }
          else
          {
                logBeanWebReactivo.info ("Entrando excepcion de recurso no existente");
                throwable = (Throwable)request.getAttribute("javax.servlet.error.exception");
                statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
                servletName = (String)request.getAttribute("javax.servlet.error.servlet_name");
                
                logBeanWebReactivo.info ("throwable = " + throwable);
                logBeanWebReactivo.info ("statusCode = " + statusCode);
                logBeanWebReactivo.info ("servletName = " + servletName);
                
                errorDetectado = obtenerErrorWebHTTP(statusCode.intValue());
                errorEncontrado = errorDetectado;
                logBeanWebReactivo.info ("Error detectado: " + errorEncontrado);
                //******************************************************
                //******************************************************
                //logBeanWebReactivo.info ("Error de Seguridad del Sistema de Información");
                //******************************************************
                //******************************************************
                if (servletName == null)
                {
                   servletName = "Unknown";
                }
                //******************************************************
                //******************************************************
                requestUri = (String)request.getAttribute("javax.servlet.error.request_uri");
                //******************************************************
                //******************************************************
                //******************************************************
                //******************************************************
                if (throwable != null)
                {
                        errorTecnico = throwable.getLocalizedMessage();
                }
                else
                {
                        errorTecnico = "Page not found";
                }
                //******************************************************
                //******************************************************
                //******************************************************
                //******************************************************
                if (requestUri == null)
                {
                   requestUri = "/ReactivoVigilanciaWeb/faces/index.xhtml";
                }
                else
                {
                   sesion.setAttribute("mensajeErrorPE",errorDetectado);
                   sesion.setAttribute("codigoErrorPE",statusCode);
                   sesion.setAttribute("errorTecnico",errorTecnico);
                   requestUri = "/ReactivoVigilanciaWeb/faces/paginaCapturaError.xhtml";
                }
                
                response.sendRedirect(requestUri);
          }
          //*****************************************************************************************
          //*****************************************************************************************
          //*****************************************************************************************
      }
      
      catch (Exception errorServlet)
      {
          //****************************************************************************
          //****************************************************************************
          //****************************************************************************
          errorTecnico = errorServlet.getLocalizedMessage();
          errorDetectado = obtenerErrorWebHTTP(statusCode.intValue());
          errorEncontrado = errorDetectado;
          statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
          logBeanWebReactivo.info ("Error detectado: " + errorEncontrado);
          //****************************************************************************
          //****************************************************************************
          logBeanWebReactivo.info ("------------------------------------------------------------------");
          logBeanWebReactivo.info ("------------------------------------------------------------------");
          logBeanWebReactivo.info ("Error de seguridad = " + errorTecnico);
          logBeanWebReactivo.info ("------------------------------------------------------------------");
          logBeanWebReactivo.info ("------------------------------------------------------------------");
          errorServlet.printStackTrace();
          //****************************************************************************
          //****************************************************************************
          //****************************************************************************
          sesion.setAttribute("mensajeErrorPE",errorDetectado);
          sesion.setAttribute("codigoErrorPE",statusCode);
          sesion.setAttribute("errorTecnico",errorTecnico);
          requestUri = "/ReactivoVigilanciaWeb/faces/paginaCapturaError.xhtml";
          response.sendRedirect(requestUri);
          //****************************************************************************
          //****************************************************************************
          //****************************************************************************
      }
      
  }
  //*************************************************************************
  //*************************************************************************
  //*************************************************************************
  public String obtenerErrorWebHTTP(int codigoError)
  {
      String mensajeError = "";
      
      switch (codigoError)
      {
        case 100:  mensajeError = "Continue:  El navegador puede continuar realizando su petición (se utiliza para indicar que la primera parte de la petición del navegador se ha recibido correctamente).";  break;
        case 101:  mensajeError = "Switching Protocols:  El servidor acepta el cambio de protocolo propuesto por el navegador (puede ser por ejemplo un cambio de HTTP 1.0 a HTTP 1.1).";  break;
        case 102:  mensajeError = "Processing (WebDAV)";  break;
        case 103:  mensajeError = "Checkpoint";  break;
        //case 200:  mensajeError = "OK:  Respuesta estándar para peticiones correctas.";  break;
        case 201:  mensajeError = "Created:  La petición ha sido completada y ha resultado en la creación de un nuevo recurso.";  break;
        case 202:  mensajeError = "Accepted:  La petición ha sido aceptada para procesamiento, pero este no ha sido completado. La petición eventualmente pudiere no ser satisfecha, ya que podría ser no permitida o prohibida cuando el procesamiento tenga lugar.";  break;
        case 203:  mensajeError = "Non-Authoritative Information (desde HTTP/1.1)";  break;
        case 204:  mensajeError = "No Content:  La petición se ha completado con éxito pero su respuesta no tiene ningún contenido (la respuesta sí que puede incluir información en sus cabeceras HTTP).";  break;
        case 205:  mensajeError = "Reset Content:  La petición se ha completado con éxito, pero su respuesta no tiene contenidos y además, el navegador tiene que inicializar la página desde la que se realizó la petición (este código es útil por ejemplo para páginas con formularios cuyo contenido debe borrarse después de que el usuario lo envíe).";  break;
        case 206:  mensajeError = "Partial Content:";  break;
        case 207:  mensajeError = "Multi-Status (Multi-Status, WebDAV):  El cuerpo del mensaje que sigue es un mensaje XML y puede contener algún número de códigos de respuesta separados, dependiendo de cuántas sub-peticiones sean hechas.";  break;
        case 208:  mensajeError = "Already Reported (WebDAV)";  break;
        case 300:  mensajeError = "Multiple Choices:  Indica opciones múltiples para el URI que el cliente podría seguir. Esto podría ser utilizado, por ejemplo, para presentar distintas opciones de formato para video, listar archivos con distintas extensiones o word sense disambiguation.";  break;
        case 301:  mensajeError = "Moved Permanently:  Esta y todas las peticiones futuras deberían ser dirigidas a la URI dada.";  break;
        case 302:  mensajeError = "Found:  Este es el código de redirección más popular, pero también un ejemplo de las prácticas de la industria contradiciendo el estándar. La especificación HTTP/1.0 (RFC 1945) requería que el cliente realizara una redirección temporal (la frase descriptiva original fue 'Moved Temporarily'), pero los navegadores populares lo implementaron como 303 See Other. Por tanto, HTTP/1.1 añadió códigos de estado 303 y 307 para eliminar la ambigüedad entre ambos comportamientos. Sin embargo, la mayoría de aplicaciones web y bibliotecas de desarrollo aún utilizan el código de respuesta 302 como si fuera el 303.";  break;
        case 303:  mensajeError = "See Other (desde HTTP/1.1):  La respuesta a la petición puede ser encontrada bajo otra URI utilizando el método GET.";  break;
        case 304:  mensajeError = "Not Modified:  Indica que la petición a la URL no ha sido modificada desde que fue requerida por última vez. Típicamente, el cliente HTTP provee un encabezado como If-Modified-Since para indicar una fecha y hora contra la cual el servidor pueda comparar. El uso de este encabezado ahorra ancho de banda y reprocesamiento tanto del servidor como del cliente.";  break;
        case 305:  mensajeError = "Use Proxy (desde HTTP/1.1):  Muchos clientes HTTP (como Mozilla3 e Internet Explorer) no se apegan al estándar al procesar respuestas con este código, principalmente por motivos de seguridad.";  break;
        case 306:  mensajeError = "Switch Proxy:  Este código se utilizaba en las versiones antiguas de HTTP pero ya no se usa (aunque está reservado para usos futuros).";  break;
        case 307:  mensajeError = "Temporary Redirect (desde HTTP/1.1):  Se trata de una redirección que debería haber sido hecha con otra URI, sin embargo aún puede ser procesada con la URI proporcionada. En contraste con el código 303, el método de la petición no debería ser cambiado cuando el cliente repita la solicitud. Por ejemplo, una solicitud POST tiene que ser repetida utilizando otra petición POST.";  break;
        case 308:  mensajeError = "Permanent Redirect";  break;
        case 400:  mensajeError = "Bad Request:      La solicitud contiene sintaxis errónea y no debería repetirse.";  break;
        case 401:  mensajeError = "Unauthorized:  Similar al 403 Forbidden, pero específicamente para su uso cuando la autentificación es posible pero ha fallado o aún no ha sido provista. Vea autentificación HTTP básica y Digest access authentication.";  break;
        case 402:  mensajeError = "Payment Required:  La intención original era que este código pudiese ser usado como parte de alguna forma o esquema de Dinero electrónico o micropagos, pero eso no sucedió, y este código nunca se utilizó.";  break;
        case 403:  mensajeError = "Forbidden:  La solicitud fue legal, pero el servidor rehúsa responderla dado que el cliente no tiene los privilegios para hacerla. En contraste a una respuesta 401 No autorizado, la autenticación no haría la diferencia.";  break;
        case 404:  mensajeError = "Not Found:  Recurso no encontrado. Se utiliza cuando el servidor web no encuentra la página o recurso solicitado.";  break;
        case 405:  mensajeError = "Method Not Allowed:  Una petición fue hecha a una URI utilizando un método de solicitud no soportado por dicha URI; por ejemplo, cuando se utiliza GET en una forma que requiere que los datos sean presentados vía POST, o utilizando PUT en un recurso de solo lectura.";  break;
        case 406:  mensajeError = "Not Acceptable:  El servidor no es capaz de devolver los datos en ninguno de los formatos aceptados por el cliente, indicados por éste en la cabecera 'Accept' de la petición.";  break;
        case 407:  mensajeError = "Proxy Authentication Required";  break;
        case 408:  mensajeError = "Request Timeout:  El cliente falló al continuar la petición";  break;
        case 409:  mensajeError = "Conflict:  Indica que la solicitud no pudo ser procesada debido a un conflicto con el estado actual del recurso que esta identifica.";  break;
        case 410:  mensajeError = "Gone:  Indica que el recurso solicitado ya no está disponible y no lo estará de nuevo. Debería ser utilizado cuando un recurso ha sido quitado de forma permanente.  Si un cliente recibe este código no debería volver a solicitar el recurso en el futuro. Por ejemplo un buscador lo eliminará de sus índices y lo hará más rápidamente que utilizando un código 404.";  break;
        case 411:  mensajeError = "Length Required:  El servidor rechaza la petición del navegador porque no incluye la cabecera Content-Length adecuada.";  break;
        case 412:  mensajeError = "Precondition Failed:  El servidor no es capaz de cumplir con algunas de las condiciones impuestas por el navegador en su petición.";  break;
        case 413:  mensajeError = "Request Entity Too Large:  La petición del navegador es demasiado grande y por ese motivo el servidor no la procesa2";  break;
        case 414:  mensajeError = "Request-URI Too Long:  La URI de la petición del navegador es demasiado grande y por ese motivo el servidor no la procesa (esta condición se produce en muy raras ocasiones y casi siempre porque el navegador envía como GET una petición que debería ser POST).";  break;
        case 415:  mensajeError = "Unsupported Media Type:  La petición del navegador tiene un formato que no entiende el servidor y por eso no se procesa.";  break;
        case 416:  mensajeError = "Requested Range Not Satisfiable:  El cliente ha preguntado por una parte de un archivo, pero el servidor no puede proporcionar esa parte, por ejemplo, si el cliente preguntó por una parte de un archivo que está más allá de los límites del fin del archivo.";  break;
        case 417:  mensajeError = "Expectation Failed:  La petición del navegador no se procesa porque el servidor no es capaz de cumplir con los requerimientos de la cabecera Expect de la petición.";  break;
        case 418:  mensajeError = "I'm a teapot:  Soy una tetera.";  break;
        case 422:  mensajeError = "Unprocessable Entity (WebDAV";  break;
        case 423:  mensajeError = "Locked (WebDAV";  break;
        case 424:  mensajeError = "Failed Dependency (WebDAV) (RFC 4918):  La solicitud falló debido a una falla en la solicitud previa.";  break;
        case 425:  mensajeError = "Unassigned:  Definido en los drafts de WebDav Advanced Collections, pero no está presente en 'Web Distributed Authoring and Versioning (WebDAV) Ordered Collections Protocol' (RFC 3648).";  break;
        case 426:  mensajeError = "Upgrade Required (RFC 7231):  El cliente debería cambiarse a TLS/1.0.";  break;
        case 428:  mensajeError = "Precondition Required:  El servidor requiere que la petición del navegador sea condicional (este tipo de peticiones evitan los problemas producidos al modificar con PUT un recurso que ha sido modificado por otra parte).";  break;
        case 429:  mensajeError = "Too Many Requests: Hay muchas conexiones desde esta dirección de internet.";  break;
        case 431:  mensajeError = "Request Header Fileds Too Large):  El servidor no puede procesar la petición porque una de las cabeceras de la petición es demasiado grande. Este error también se produce cuando la suma del tamaño de todas las peticiones es demasiado grande.";  break;
        case 449:  mensajeError = "Una extensión de Microsoft: La petición debería ser reintentada después de hacer la acción apropiada.";  break;
        case 451:  mensajeError = "Unavailable for Legal Reasons:  El contenido ha sido eliminado como consecuencia de una orden judicial o sentencia emitida por un tribunal.";  break;
        case 500:  mensajeError = "Internal Server: Error Es un código comúnmente emitido por aplicaciones empotradas en servidores web, mismas que generan contenido dinámicamente, por ejemplo aplicaciones montadas en IIS o Tomcat, cuando se encuentran con situaciones de error ajenas a la naturaleza del servidor web.";  break;
        case 501:  mensajeError = "Not Implemented: El servidor no soporta alguna funcionalidad necesaria para responder a la solicitud del navegador (como por ejemplo el método utilizado para la petición).";  break;
        case 502:  mensajeError = "Bad Gateway: El servidor está actuando de proxy o gateway y ha recibido una respuesta inválida del otro servidor, por lo que no puede responder adecuadamente a la petición del navegador.";  break;
        case 503:  mensajeError = "Service Unavailable: El servidor no puede responder a la petición del navegador porque está congestionado o está realizando tareas de mantenimiento.";  break;
        case 504:  mensajeError = "Gateway Timeout: El servidor está actuando de proxy o gateway y no ha recibido a tiempo una respuesta del otro servidor, por lo que no puede responder adecuadamente a la petición del navegador.";  break;
        case 505:  mensajeError = "HTTP Version Not Supported: El servidor no soporta o no quiere soportar la versión del protocolo HTTP utilizada en la petición del navegador.";  break;
        case 506:  mensajeError = "Variant Also Negotiates (RFC 2295): El servidor ha detectado una referencia circular al procesar la parte de la negociación del contenido de la petición.";  break;
        case 507:  mensajeError = "Insufficient Storage (WebDAV)";  break;
        case 508:  mensajeError = "Loop Detected (WebDAV): La petición no se puede procesar porque el servidor ha encontrado un bucle infinito al intentar procesarla.";  break;
        case 509:  mensajeError = "Bandwidth Limit Exceeded: Límite de ancho de banda excedido. Este código de estatus, a pesar de ser utilizado por muchos servidores, no es oficial.";  break;
        case 510:  mensajeError = "Not Extended (RFC 2774): La petición del navegador debe añadir más extensiones para que el servidor pueda procesarla.";  break;
        case 511:  mensajeError = "Network Authentication Required:  El navegador debe autenticarse para poder realizar peticiones (se utiliza por ejemplo con los portales cautivos que te obligan a autenticarte antes de empezar a navegar).";  break;
      }
      
      return (mensajeError);
  }
  //*************************************************************************
  //*************************************************************************
  //*************************************************************************
  // Method to handle POST method request.
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
     //logBeanWebReactivo.info ("***********************************************");
     //logBeanWebReactivo.info ("***********************************************");
     //logBeanWebReactivo.info ("Procesando error por el POST");
     procesarPeticion(request, response);
     //logBeanWebReactivo.info ("***********************************************");
     //logBeanWebReactivo.info ("***********************************************");
  } 
  //*************************************************************************
  //*************************************************************************
  //*************************************************************************
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
     //logBeanWebReactivo.info ("***********************************************");
     //logBeanWebReactivo.info ("***********************************************");
     //logBeanWebReactivo.info ("Procesando error por el GET");
     procesarPeticion(request, response);
     //logBeanWebReactivo.info ("***********************************************");
     //logBeanWebReactivo.info ("***********************************************");
  } 
  //*************************************************************************
  //*************************************************************************
  //*************************************************************************

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

  
  
}
