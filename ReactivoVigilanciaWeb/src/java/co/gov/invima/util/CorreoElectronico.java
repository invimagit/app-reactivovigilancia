package co.gov.invima.util;

import co.gov.invima.VO.UsuariosVO;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 * Clase que se encarga del manejo del correo electronico
 *
 *
 *     
 */
public class CorreoElectronico {
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String CHARSET_ISO = "ISO-8859-1";
    private static CorreoElectronico instancia = null;
    
    /**
     * Enviar Correo a Reactivo Validando Autenticacion
     * @param unDestinatario
     * @param unCC
     * @param unAsunto
     * @param unMensaje
     * @throws Exception 
     */
    public void enviarCorreoElectronicoReactivo(String unDestinatario,
                        String unCC, String unAsunto, String unMensaje, UsuariosVO usuarioReactivovigilancia, String Operacion )throws Exception {
        PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
        String autenticacion = pr.getProperty("autenticacion");
        if (autenticacion != null && autenticacion.equals("true")) {
            enviarCorreoAutenticadoReactivo(unDestinatario, unCC, unAsunto, unMensaje,usuarioReactivovigilancia,Operacion);
        }
        else{
            enviarCorreoSinAutReactivo(unDestinatario, unCC, unAsunto, unMensaje,usuarioReactivovigilancia,Operacion);
        }
    }
    
    /**
     * Enviar correo a Reactivo para un usuario Sin Autenticacion
     * @param unDestinatario
     * @param unCC
     * @param unAsunto
     * @param unMensaje
     * @throws Exception 
     */
    private void enviarCorreoSinAutReactivo(String unDestinatario,
                        String unCC, String unAsunto, String unMensaje, UsuariosVO usuarioReactivovigilancia, String Operacion )throws Exception {
        PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
        String unServidorCorreo = pr.getProperty("servidor");
        String unRemitente = pr.getProperty("remitente");
        enviar( unServidorCorreo, unRemitente, unDestinatario,unCC,  unAsunto,  unMensaje  ,usuarioReactivovigilancia,Operacion,0);
    }
    
    /**
     * Enviar correo a Reactivo para un usuario autenticado
     * @param unDestinatario
     * @param unCC
     * @param unAsunto
     * @param unMensaje
     * @throws Exception 
     */
    private void enviarCorreoAutenticadoReactivo(String unDestinatario,
                        String unCC, String unAsunto, String unMensaje, UsuariosVO usuarioReactivovigilancia, String Operacion )throws Exception {
        PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
        String unServidorCorreo = pr.getProperty("servidor");
        String unRemitente = pr.getProperty("remitente");
        String usuario = pr.getProperty("usuario");
        String password = pr.getProperty("password");
        enviar(unServidorCorreo, unRemitente, usuario, password, unDestinatario, unCC, unAsunto, unMensaje,usuarioReactivovigilancia,Operacion,0);
    }

    /**
     * 
     * metodo generico que envia un mensaje por correo electronico, sin autenticar
     *
     * @param unServidorCorreo
     * @param unRemitente
     * @param unDestinatario
     * @param unCC
     * @param unAsunto
     * @param unMensaje
     * @throws Exception
     */
    private void enviar( String unServidorCorreo, String unRemitente, String unDestinatario,
                        String unCC, String unAsunto, String unMensaje,UsuariosVO usuarioReactivovigilancia, String Operacion, int intento ) throws Exception {
        try{

        logCorreo(unServidorCorreo, unRemitente, "Sin Autenticacion", "", unDestinatario, unCC, unAsunto, unMensaje, usuarioReactivovigilancia, Operacion,intento);
    // Establecer las propiedades
        Properties misProp = System.getProperties();

        misProp.put("mail.smtp.host", unServidorCorreo);
        misProp.put("mail.transport.protocol", "smtp");
        misProp.put("mail.smtp.timeout", "4000");
        misProp.put("mail.smtp.connectiontimeout", "4000");
        misProp.put("mail.smtp.auth", "false");

        // Abrir la sesion con el servidor de correos
        Session miSesion = Session.getInstance(misProp, null);

        // COnstruir el mensaje del correo
        MimeMessage miMensaje = new MimeMessage(miSesion);

         miMensaje.setFrom(new InternetAddress(unRemitente));
        miMensaje.setRecipients(RecipientType.TO, InternetAddress.parse(unDestinatario, false));

        if ( !unCC.equals("") ) {
            miMensaje.setRecipients(RecipientType.BCC, InternetAddress.parse(unCC, false));
        }

        miMensaje.setSentDate(new Date());
        miMensaje.setSubject(unAsunto, CHARSET_ISO);
        //miMensaje.setText(unMensaje, CHARSET_ISO);
        miMensaje.setContent(unMensaje, "text/html");
        Transport.send(miMensaje);
        }catch(Exception e){
            logCorreoEnviadosReactivovigilancia.error("ERROR");
            logCorreoEnviadosReactivovigilancia.error(e);
        }
    }
    
    /**
     * 
     * metodo que envia un mensaje por correo electronico, cuando se requere autenticacion
     *
     * @param unServidorCorreo
     * @param unRemitente
     * @param password
     * @param unDestinatario
     * @param unCC
     * @param unAsunto
     * @param unMensaje
     * @throws Exception
     */
    private void enviar( String unServidorCorreo, String unRemitente, String usuario, String password, String unDestinatario,
                        String unCC, String unAsunto, String unMensaje,UsuariosVO usuarioReactivovigilancia, String Operacion, int intento ) throws Exception {

        
        logCorreo(unServidorCorreo, unRemitente, "Sin Autenticacion", "", unDestinatario, unCC, unAsunto, unMensaje, usuarioReactivovigilancia, Operacion,intento);
    // Establecer las propiedades
        Properties misProp = System.getProperties();

        misProp.setProperty("mail.smtp.host", unServidorCorreo);
        misProp.setProperty("mail.transport.protocol", "smtp");
        misProp.setProperty("mail.smtp.timeout", "4000");
        misProp.setProperty("mail.smtp.connectiontimeout", "4000");
        misProp.setProperty("mail.smtp.user", usuario);
        misProp.setProperty("mail.smtp.auth", "true");

        // Abrir la sesion con el servidor de correos
        Session miSesion = Session.getInstance(misProp);
        // Construir el mensaje del correo
        MimeMessage miMensaje = new MimeMessage(miSesion);

        miMensaje.setFrom(new InternetAddress(unRemitente));
        miMensaje.setRecipients(RecipientType.TO, InternetAddress.parse(unDestinatario, false));

        if ( !unCC.equals("") ) {
            miMensaje.setRecipients(RecipientType.BCC, InternetAddress.parse(unCC, false));
        }

        miMensaje.setSentDate(new Date());
        miMensaje.setSubject(unAsunto, CHARSET_ISO);
        miMensaje.setContent(unMensaje, "text/html");
        //miMensaje.setText(unMensaje, "ISO-8859-1","html");
        Transport t = miSesion.getTransport("smtp");
        t.connect(unServidorCorreo,usuario,password);
        t.sendMessage(miMensaje,miMensaje.getAllRecipients());
        t.close();
    }

    public synchronized static final CorreoElectronico getInstance( ) {
        if ( instancia==null ) {
            instancia = new CorreoElectronico();
        }

        return instancia;
    }
    
    private final static Logger logCorreoEnviadosReactivovigilancia = Logger.getLogger(CorreoElectronico.class);
    public  void logCorreo(String unServidorCorreo, String unRemitente, String usuario, String password, String unDestinatario,
            String unCC, String unAsunto, String unMensaje, UsuariosVO usuarioReactivovigilancia, String Operacion, int intento) {
        String log = "";
        log += "****************************************\n";
        log += "****************************************\n";
        log += "****************************************\n";
        log += "****************************************  Enviando correo\n";
        log += "Intento:" + intento + "\n";
        log += "Fecha:" + new Date() + "\n";
        log += "De: " + unRemitente + "\n";
        log += "Para: " + unDestinatario + "\n";
        log += "CC: " + unCC + "\n";
        log += "ServidorCorreo: " + unServidorCorreo + "\n";
        log += "usuario SMTP: " + usuario + "\n";
        log += "unAsunto: " + unAsunto + "\n";
        log += "unMensaje: " + unMensaje + "\n";
        try {
            log += "Usuario Tecnovigilancia: " + usuarioReactivovigilancia.getInfo() + "\n";
        } catch (Exception e) {
            log += "Usuario Reactivovigilancia: INVALIDO" + "\n";
        }
        log += "Operacion: " + Operacion + "\n";

        logCorreoEnviadosReactivovigilancia.info(log);
    }
}
