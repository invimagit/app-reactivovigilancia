/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.beans;

//import co.gov.invima.delegate.ReporteDelegate;
//import co.gov.invima.dto.*;
import co.gov.invima.BO.ReactivoBO;
import co.gov.invima.VO.CiudadesVO;
import co.gov.invima.VO.DepartamentoVO;
import co.gov.invima.VO.PaisesVO;
import co.gov.invima.entities.ReactivoReporteUsuario;
import co.gov.invima.sesion.ReactivoRedPersonaFacadeLocal;
import co.gov.invima.sesion.ReactivoReporteUsuarioFacade;
import co.gov.invima.util.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import org.apache.log4j.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.apache.log4j.Priority;


/**
 * Clase que mapea los atributos en el reporte de usuarios
 * @author GRUPO ASD
 */
@Named("crearReporteUsuarioManagedBean")
@SessionScoped
public class CrearReporteUsuarioManagedBean implements Serializable {
    private static final long serialVersionUID = -3899161666749451918L;
    private static final Logger logBeanWebTecno = Logger.getLogger(CrearReporteUsuarioManagedBean.class.getName());
   
    private String error = "";

  
    private String mensaje = "";
    private ArrayList arregloMun = new ArrayList();
    private ArrayList arregloDpto = new ArrayList();
    private ArrayList arregloMun1 = new ArrayList();
    private ArrayList arregloDpto1 = new ArrayList();
    private ArrayList arregloProf = new ArrayList();
    private ArrayList arregloDesenlace = new ArrayList();
    private ArrayList arregloPais = new ArrayList();
    private String nombreRep="";
    private Character sexo;
    private int edad;
    private Character edadEn;
    private String direccionRep="";
    private String telefono;
    private String pais;
    private String codDepart;
    private String codMun;
    private String email;
    private String nombreDm;
    private String nombreComercial;
    private String regSan;
    private String lote;
    private String referencia;
    private String nombreFabricante;
    private String nombreDistribuidor;
    private Date  fechaEvento;
    private Date fechaNotificacion;
    private String fechaNoti;
    private int eventoDeteccion;
    private String descripcionEvento;
    private int codigo;
    private String mensajeCorreo ="";
    private boolean visibleConfirmacion = false;
    private boolean mostrarConfirmacion = false;
    private boolean visible =  false;
    private boolean habilitado = false;
    private Boolean activoDpto =  false;
    private boolean obligatorio1= false;
    private boolean obligatorio2= false;
    private boolean obligatorio3= false;
    private boolean obligatorio4= false;
    private boolean obligatorio5= false;
    private boolean obligatorio6= false;
    private boolean obligatorio7= false;
    private boolean obligatorio8= false;
    private boolean obligatorio9= false;
    private boolean obligatorio10= false;
    private boolean obligatorio11= false;
    private boolean obligatorio12= false;
    private boolean obligatorio13= false;
    private boolean obligatorio14= false;
    private boolean obligatorio15= false;
    private boolean obligatorio16= false;
    private boolean obligatorio17= false;
    private boolean obligatorio18= false;
    private boolean obligatorio19= false;
    private boolean obligatorio20= false;
    private boolean mostrarAsteriscos = false;
    private boolean mostrarErrorMail = false;
    private String mensajeConfirma="";  
    private boolean mostrarError = false;
    private String  mensajeError = "";
    private TimeZone zona;
    private String mensajes;
    private ReactivoBO bO;
    private ReactivoReporteUsuario reactivoReporteUsuario;
    @EJB
    private ReactivoReporteUsuarioFacade reactivoReporteUsuarioFacade;

    public ReactivoReporteUsuario getReactivoReporteUsuario() {
        return reactivoReporteUsuario;
    }

    public void setReactivoReporteUsuario(ReactivoReporteUsuario reactivoReporteUsuario) {
        this.reactivoReporteUsuario = reactivoReporteUsuario;
    }

    
    public TimeZone getZona() {
        return zona;
    }

    public void setZona(TimeZone zona) {
        this.zona = zona;
    }
    

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public boolean isMostrarError() {
        return mostrarError;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public void setMostrarError(boolean mostrarError) {
        this.mostrarError = mostrarError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public String getMensajeConfirma() {
        return mensajeConfirma;
    }

    public void setMensajeConfirma(String mensajeConfirma) {
        this.mensajeConfirma = mensajeConfirma;
    }
    

    public String getFechaNoti() {
        return fechaNoti;
    }

    public void setFechaNoti(String fechaNoti) {
        this.fechaNoti = fechaNoti;
    }

    
    public boolean isMostrarAsteriscos() {
        return mostrarAsteriscos;
    }

    public void setMostrarAsteriscos(boolean mostrarAsteriscos) {
        this.mostrarAsteriscos = mostrarAsteriscos;
    }

    public boolean isMostrarErrorMail() {
        return mostrarErrorMail;
    }

    public void setMostrarErrorMail(boolean mostrarErrorMail) {
        this.mostrarErrorMail = mostrarErrorMail;
    }
    
   
    public boolean isObligatorio1() {
        return obligatorio1;
    }

    public void setObligatorio1(boolean obligatorio1) {
        this.obligatorio1 = obligatorio1;
    }

    public boolean isObligatorio10() {
        return obligatorio10;
    }

    public void setObligatorio10(boolean obligatorio10) {
        this.obligatorio10 = obligatorio10;
    }

    public boolean isObligatorio11() {
        return obligatorio11;
    }

    public void setObligatorio11(boolean obligatorio11) {
        this.obligatorio11 = obligatorio11;
    }

    public boolean isObligatorio12() {
        return obligatorio12;
    }

    public void setObligatorio12(boolean obligatorio12) {
        this.obligatorio12 = obligatorio12;
    }

    public boolean isObligatorio13() {
        return obligatorio13;
    }

    public void setObligatorio13(boolean obligatorio13) {
        this.obligatorio13 = obligatorio13;
    }

    public boolean isObligatorio14() {
        return obligatorio14;
    }

    public void setObligatorio14(boolean obligatorio14) {
        this.obligatorio14 = obligatorio14;
    }

    public boolean isObligatorio15() {
        return obligatorio15;
    }

    public void setObligatorio15(boolean obligatorio15) {
        this.obligatorio15 = obligatorio15;
    }

    public boolean isObligatorio16() {
        return obligatorio16;
    }

    public void setObligatorio16(boolean obligatorio16) {
        this.obligatorio16 = obligatorio16;
    }

    public boolean isObligatorio17() {
        return obligatorio17;
    }

    public void setObligatorio17(boolean obligatorio17) {
        this.obligatorio17 = obligatorio17;
    }

    public boolean isObligatorio18() {
        return obligatorio18;
    }

    public void setObligatorio18(boolean obligatorio18) {
        this.obligatorio18 = obligatorio18;
    }

    public boolean isObligatorio19() {
        return obligatorio19;
    }

    public void setObligatorio19(boolean obligatorio19) {
        this.obligatorio19 = obligatorio19;
    }

    public boolean isObligatorio2() {
        return obligatorio2;
    }

    public void setObligatorio2(boolean obligatorio2) {
        this.obligatorio2 = obligatorio2;
    }

    public boolean isObligatorio20() {
        return obligatorio20;
    }

    public void setObligatorio20(boolean obligatorio20) {
        this.obligatorio20 = obligatorio20;
    }


    public boolean isObligatorio3() {
        return obligatorio3;
    }

    public void setObligatorio3(boolean obligatorio3) {
        this.obligatorio3 = obligatorio3;
    }
  
    public boolean isObligatorio4() {
        return obligatorio4;
    }

    public void setObligatorio4(boolean obligatorio4) {
        this.obligatorio4 = obligatorio4;
    }

   
    public boolean isObligatorio5() {
        return obligatorio5;
    }

    public void setObligatorio5(boolean obligatorio5) {
        this.obligatorio5 = obligatorio5;
    }

    public boolean isObligatorio6() {
        return obligatorio6;
    }

    public void setObligatorio6(boolean obligatorio6) {
        this.obligatorio6 = obligatorio6;
    }

    public boolean isObligatorio7() {
        return obligatorio7;
    }

    public void setObligatorio7(boolean obligatorio7) {
        this.obligatorio7 = obligatorio7;
    }

    public boolean isObligatorio8() {
        return obligatorio8;
    }

    public void setObligatorio8(boolean obligatorio8) {
        this.obligatorio8 = obligatorio8;
    }

    public boolean isObligatorio9() {
        return obligatorio9;
    }

    public void setObligatorio9(boolean obligatorio9) {
        this.obligatorio9 = obligatorio9;
    }

    
    public boolean isMostrarConfirmacion() {
        return mostrarConfirmacion;
    }

    public void setMostrarConfirmacion(boolean mostrarConfirmacion) {
        this.mostrarConfirmacion = mostrarConfirmacion;
    }

    public boolean isVisibleConfirmacion() {
        return visibleConfirmacion;
    }

    public void setVisibleConfirmacion(boolean visibleConfirmacion) {
        this.visibleConfirmacion = visibleConfirmacion;
    }

   
    public ArrayList getArregloDpto() {
        return arregloDpto;
    }

    public void setArregloDpto(ArrayList arregloDpto) {
        this.arregloDpto = arregloDpto;
    }

    public ArrayList getArregloDpto1() {
        return arregloDpto1;
    }

    public void setArregloDpto1(ArrayList arregloDpto1) {
        this.arregloDpto1 = arregloDpto1;
    }

    public ArrayList getArregloMun1() {
        return arregloMun1;
    }

    public void setArregloMun1(ArrayList arregloMun1) {
        this.arregloMun1 = arregloMun1;
    }

   
    public ArrayList getArregloDesenlace() {
        return arregloDesenlace;
    }

    public void setArregloDesenlace(ArrayList arregloDesenlace) {
        this.arregloDesenlace = arregloDesenlace;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getMensajeCorreo() {
        return mensajeCorreo;
    }

    public void setMensajeCorreo(String mensajeCorreo) {
        this.mensajeCorreo = mensajeCorreo;
    }

    
   
    public ArrayList getArregloMun() {
        return arregloMun;
    }

    public void setArregloMun(ArrayList arregloMun) {
        this.arregloMun = arregloMun;
    }

    public ArrayList getArregloProf() {
        return arregloProf;
    }

    public void setArregloProf(ArrayList arregloProf) {
        this.arregloProf = arregloProf;
    }

    public ArrayList getArregloPais() {
        return arregloPais;
    }

    public void setArregloPais(ArrayList arregloPais) {
        this.arregloPais = arregloPais;
    }

   
    public Boolean getActivoDpto() {
        return activoDpto;
    }

    public String getCodDepart() {
        return codDepart;
    }

    public void setCodDepart(String codDepart) {
        this.codDepart = codDepart;
    }

    public String getCodMun() {
        return codMun;
    }

    public void setCodMun(String codMun) {
        this.codMun = codMun;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public String getDireccionRep() {
        return direccionRep;
    }

    public void setDireccionRep(String direccionRep) {
        this.direccionRep = direccionRep;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Character getEdadEn() {
        return edadEn;
    }

    public void setEdadEn(Character edadEn) {
        this.edadEn = edadEn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEventoDeteccion() {
        return eventoDeteccion;
    }

    public void setEventoDeteccion(int eventoDeteccion) {
        this.eventoDeteccion = eventoDeteccion;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreDistribuidor() {
        return nombreDistribuidor;
    }

    public void setNombreDistribuidor(String nombreDistribuidor) {
        this.nombreDistribuidor = nombreDistribuidor;
    }

    public String getNombreDm() {
        return nombreDm;
    }

    public void setNombreDm(String nombreDm) {
        this.nombreDm = nombreDm;
    }

    public String getNombreFabricante() {
        return nombreFabricante;
    }

    public void setNombreFabricante(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }

    public String getNombreRep() {
        return nombreRep;
    }

    public void setNombreRep(String nombreRep) {
        this.nombreRep = nombreRep;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getRegSan() {
        return regSan;
    }

    public void setRegSan(String regSan) {
        this.regSan = regSan;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setActivoDpto(Boolean activoDpto) {
        this.activoDpto = activoDpto;
    }
    
    public CrearReporteUsuarioManagedBean() {
        logBeanWebTecno.log(Priority.INFO, "llenarComboPais() ");
        visible = false;
        fechaNoti= Fecha.formateaFecha(new Date(), "dd/MM/yyyy hh:mm a");
        bO = new ReactivoBO();
        llenarComboPais();
        llenarComboDpto();
        //llenarCombo("0");
        //llenarComboProfesion();
        
        //llenarComboCausa();
        //llenarListaDesenlace();
        zona= java.util.TimeZone.getDefault();
    }
    public void cerrarAsteriscos() {
        mostrarAsteriscos= false;
    }
     public void cerrarError() {
        mostrarError= false;
      }
     
     
    private void llenarComboPais(){
        /*arregloPais = new ArrayList();
        ArrayList a = ReporteDelegate.obtenerListaPaises();
        arregloPais.add(new SelectItem("-1","Seleccione"));
        for(int i =0; i<a.size();i++){
            PaisVO paises = (PaisVO)a.get(i);
            arregloPais.add(new SelectItem(paises.getCdg_pais(),paises.getPais()));
        }*/
        logBeanWebTecno.log(Priority.INFO, "llenarComboPais() ");
        try {
            logBeanWebTecno.log(Priority.INFO, "llenarComboPais() -2");
            List<PaisesVO> pvos = bO.obtenerPaises();
            logBeanWebTecno.log(Priority.INFO, "llenarComboPais(), pvos.size() "+pvos.size());
            if (pvos != null && !pvos.isEmpty()) {
                arregloPais = new ArrayList();
                for (PaisesVO paisesVO : pvos) {
                    arregloPais.add(new SelectItem(paisesVO.getCdgPais(), paisesVO.getPais()));
                }
            }
            logBeanWebTecno.log(Priority.INFO, "llenarComboPais(), arregloPais.size() "+arregloPais.size());
        } catch (Exception e) {
            logBeanWebTecno.log(Priority.ERROR, "Error al obtener Pais: ", e);
        } finally {
            /*try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/crearReporteUsuario.xhtml");
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(CrearReporteUsuarioManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }

    }
    
    /**
     * 
     * @param idDpto 
     */
    public void llenarCombo(String idDpto){
        /*
        arregloMun = new ArrayList();
        ArrayList a = ReporteDelegate.obtenerCombo(idDpto);
        arregloMun.add(new SelectItem("-1","Seleccione"));
        for(int i =0; i<a.size();i++){
            MunicipioVO mun = (MunicipioVO)a.get(i);
            arregloMun.add(new SelectItem(mun.getCodigo(),mun.getDescripcion()));
        }
        */
        logBeanWebTecno.log(Priority.INFO, "llenarCombo(String idDpto)");
        String valor = idDpto;
        if (!valor.isEmpty()) {
            arregloMun = new ArrayList<>();
            for (DepartamentoVO dvo : lstDptos) {
                if (dvo.getCodDepart().equals(valor)) {
                    for (CiudadesVO cvo : dvo.getCiudadesVOs()) {
                        arregloMun.add(new SelectItem(cvo.getCodMun(), cvo.getCiudad()));
                    }
                    break;
                }
            }
            logBeanWebTecno.log(Priority.INFO, "llenarCombo(String idDpto), arregloMun.size() = "+arregloMun.size());
        }
    }
    /**
     * 
     */
    List<DepartamentoVO> lstDptos ;
    private void llenarComboDpto(){
        arregloDpto = null;
        try {
            lstDptos = bO.obtenerDptos();
            if (lstDptos != null && !lstDptos.isEmpty()) {
                arregloDpto = new ArrayList<>();
                for (DepartamentoVO departamentoVO : lstDptos) {
                    arregloDpto.add(new SelectItem(departamentoVO.getCodDepart(), departamentoVO.getDescripcion()));
                }
            }
        } catch (Exception e) {
            logBeanWebTecno.log(Priority.ERROR, "Error al obtener lista de Dpto", e);
        } finally {
        }
    }
    
    private void llenarCombo1(String idDpto){
        /*arregloMun1 = new ArrayList();
        ArrayList a = ReporteDelegate.obtenerCombo1(idDpto);
        arregloMun1.add(new SelectItem("-1","Seleccione"));
        for(int i =0; i<a.size();i++){
            MunicipioVO mun = (MunicipioVO)a.get(i);
            arregloMun1.add(new SelectItem(mun.getCodigo(),mun.getDescripcion()));
        }*/
        llenarCombo(idDpto);
        arregloMun1 = arregloMun;
    }
    /**
     * 
     */
    private void llenarComboDpto1(){
        /*
        ArrayList a = ReporteDelegate.obtenerComboDpto1();
        arregloDpto1.add(new SelectItem("-1","Seleccione"));
        for(int i =0; i<a.size();i++){
            DepartamentoVO dep = (DepartamentoVO)a.get(i);
            arregloDpto1.add(new SelectItem(dep.getCodigo(),dep.getDescripcion()));
        }*/
        llenarComboDpto();
        arregloDpto1 = arregloDpto;

    }  
     
    public String abrirPopup() {
        visible = true;
        return "crearReporteUsuario";
    }
     
    public void cerrarPopup() {
        visible = false;
    }
      
    public String abrirPopupConfirma() {
        visibleConfirmacion = true;
        return "crearReporteUsuario";
    }
     
    public void cerrarPopupConfirma() {
        visibleConfirmacion = false;
    }
    
    public String cerrarConfirmacion() {
        mostrarConfirmacion = false;
        limpiarCampos();
        return "crearReporteUsuario";
    }
     
      /**
     * 
     */
    /*
     private void llenarListaDesenlace(){
        ArrayList a = ReporteDelegate.obtenerListaDesenlace();
        for(int i =0; i<a.size();i++){
            DesenlaceVO des = (DesenlaceVO)a.get(i);
            arregloDesenlace.add(new SelectItem(des.getCdg_desenlace(),des.getDescripcion()));
        }

    }*/
     
      public void onChangePais(ValueChangeEvent e){
          if(e.getNewValue()!=null){
            String id = e.getNewValue().toString();
            activoDpto = id.equals("CO");
          }
    }
    /**
     * 
     * @param e 
     */
    public void onChangeDpto(ValueChangeEvent e){
        if(e.getNewValue()!=null){
            String idDpto = e.getNewValue().toString();
            llenarCombo(idDpto);
        }
    }
    /**
     * 
     * @param e 
     */
    public void onChangeDpto1(ValueChangeEvent e){
        if(e.getNewValue()!=null){
            String idDpto = e.getNewValue().toString();
            llenarCombo1(idDpto);
        }
    }
    
    /**
     * 
     * @param e 
     */
    public void cambioReporte(ValueChangeEvent e){
        if (e.getNewValue().toString().compareTo("1")==0)
        {
            habilitado = true;
        }
    }
    
    /**
     * 
     * @return 
     */
    public String grabar(){
        
        try{
            mostrarConfirmacion= false;
            visibleConfirmacion=false;
            error="";
            mensaje="";
            //if(validar()){
                //Date fechaEventoDate = Fecha.crearFecha(fechaEvento);
              Date fechaNotifDate = Fecha.crearFecha(fechaNoti);
             
              long codigo=0;
              codigo=reactivoReporteUsuarioFacade.get_siguiente_id();
                
                ReactivoReporteUsuario reporte = new ReactivoReporteUsuario();
                reporte.setId(codigo);
                reporte.setDeteccion(eventoDeteccion+"");
                reporte.setDepartamento(codDepart);
                reporte.setCiudad(codMun);
                reporte.setDescripcionEvento(descripcionEvento);
                reporte.setDireccion(direccionRep);
                reporte.setEdad(edad+"");
                reporte.setEdadEn(edadEn+"");
                reporte.setCorreo(email);
                reporte.setFechaEvento(fechaEvento);
                reporte.setFechaElaboracion(fechaNotifDate);
                reporte.setLoteReactivo(lote);
                reporte.setNombreComercialReactivo(nombreComercial);
                reporte.setImportadorReactivo(nombreDistribuidor);
                reporte.setNombreReactivo(nombreDm);
                reporte.setFabricanteReactivo(nombreFabricante);
                reporte.setNombre(nombreRep);
                reporte.setRegistroReactivo(regSan);
                reporte.setPais(pais);
                reporte.setReferenciaReactivo(referencia);
                reporte.setSexo(sexo+"");
                reporte.setTelefono(telefono);
                
                reactivoReporteUsuarioFacade.create(reporte);
                                
                mensajeCorreo =  " Su reporte ha sido ingresado al Sistema de Información del Programa Nacional de Reactivovigilancia, a continuación se presenta un resumen del trámite efectuado:"+
                      "<br><b>Fecha y hora del ingreso:</b>"+fechaNoti+"<br><b>Código asignado:</b>"  +reporte.getId()+"<br><b>Reactivo:</b>"+reporte.getNombreComercialReactivo();
                
                mensajeConfirma=  " Su reporte ha sido ingresado al Sistema de Información del Programa Nacional de Reactivovigilancia, a continuación se presenta un resumen del trámite efectuado:"+
                      "<br><b>Fecha y hora del ingreso:</b>"+fechaNoti+"<br><b>Código asignado:</b>"  +reporte.getId()+"<br><b>Reactivo:</b>"+reporte.getNombreComercialReactivo();
                
                //mostrarConfirmacion = true;
                FacesContext.getCurrentInstance().addMessage("panelContenedor", new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "", null));
                //CorreoElectronico correo = CorreoElectronico.getInstance();
                //correo.enviarCorreoElectronicoTecno(reporte.getCorreo(),"" , "Creación de Reporte ", HtmlValidator.remplazarTildesEnTexto(mensajeCorreo));
            }
            
        //}
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        return "crearReporteUsuario";
    }
    
    public void confirmarObligatoriedad(){
        String resp="";
        String resp1="";
        mostrarErrorMail = false;
        
        resp= revisarErrores();
        
        if(resp.compareTo("si")==0){
            resp1=confirmarObligatorios();
            if(resp1.compareTo("si")==0){
                visibleConfirmacion = true;
                FacesContext.getCurrentInstance().addMessage("panelContenedor", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "", null));
            }
        }
    }
         public String revisarErrores(){
            ValidadorEmail f = new ValidadorEmail();
            String respuesta="si";
            mensajeError="";
            boolean errorFecha = false;
            boolean errorMail = false;
            if(fechaEvento!=null){
                if(fechaEvento.after(new Date())){
                        errorFecha = true;
                        mensajeError= "- La fecha del  evento no puede ser mayor a la fecha actual <br>" ;
                        respuesta= "no";
                }
            }
            if(email.compareTo("")!=0){
                if(!f.validaEmail(email)){
                        errorMail=true;
                        mensajeError= "- El formato del correo no es válido<br>" +mensajeError;
                        respuesta="no";
                }
            }
            if(errorFecha || errorMail)
                mostrarError=true;
            return respuesta;
    }   
       
       public String confirmarObligatorios(){
        
        ValidadorEmail f = new ValidadorEmail();
        String respuesta="si";
        try{
                 
                if(!f.validaEmail(email)){
                    mostrarErrorMail=true;
                    respuesta="no";
                }
                if(nombreRep.compareTo("")==0){
                    obligatorio1= true;
                    mostrarAsteriscos=true;
                    respuesta="no";
                } 
                else obligatorio1=false;
                
                if(sexo==0){
                    obligatorio2= true;
                    mostrarAsteriscos=true;
                    respuesta="no";
                } 
                else obligatorio2=false;
                
                if(edad==0){
                    obligatorio3= true;
                    mostrarAsteriscos=true;
                    respuesta="no";
                } 
                else obligatorio3=false;
                
                if(edadEn==0){
                    obligatorio4= true;
                    mostrarAsteriscos=true;
                    respuesta="no";
                } 
                else obligatorio4=false;
                
                if(direccionRep.compareTo("")==0){
                    obligatorio5= true;
                      mostrarAsteriscos=true;
                    respuesta="no";
                } 
                else obligatorio5=false;

                if(telefono.compareTo("")==0){
                    obligatorio6= true;
                      mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio6=false;

                if(pais.compareTo("-1")==0){
                    obligatorio7= true;
                      mostrarAsteriscos=true;
                    respuesta="no";
                } 
                else obligatorio7=false;
                
                if( codDepart.compareTo("-1")==0 && pais.compareTo("CO")==0){
                    obligatorio8= true;
                    mostrarAsteriscos=true;
                    respuesta="no";
                }
                else{
                    obligatorio8=false;
                    
                }
                
                if(pais.compareTo("CO")==0 && codDepart.compareTo("-1")!=0 && codMun.compareTo("-1")==0 ){
                    obligatorio9= true;
                    mostrarAsteriscos=true;
                    respuesta="no";
                } 
                else{
                    obligatorio9=false;
                    if(pais.compareTo("CO")!=0)
                        codDepart="";
                }
                
                if(email.compareTo("")==0){
                    obligatorio10= true;
                    mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio10=false;
                
                if(nombreDm.compareTo("")==0){
                    obligatorio11= true;
                      mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio11=false;
                
                if(nombreComercial.compareTo("")==0){
                    obligatorio12= true;
                    mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio12=false;
                
                if(regSan.compareTo("")==0){
                    obligatorio13= true;
                    mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio13=false;
                
                if(lote.compareTo("")==0 ){
                    obligatorio14= true;
                      mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio14=false;
                
                if(referencia.compareTo("")==0){
                    obligatorio15= true;
                      mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio15=false;
                
                if(nombreFabricante.compareTo("")==0){
                    obligatorio16= true;
                      mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio16=false;
                
                if(nombreDistribuidor.compareTo("")==0){
                    obligatorio17= true;
                      mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio17=false;
                
                if(fechaEvento==null){
                    obligatorio18= true;
                      mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio18=false;
               
                if(eventoDeteccion==0){
                    obligatorio19= true;
                      mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio19=false;
                
                if(descripcionEvento.compareTo("")==0){
                    obligatorio20= true;
                      mostrarAsteriscos=true;
                    respuesta="no";
                }
                else obligatorio20=false;
                

        }
        catch(Exception e)
        {
            logBeanWebTecno.info ("CrearReporteUsuarioManageBean.confirmarObligatorios(), " + e.getStackTrace()[0]);
        }
        
        return respuesta;
    }
    public void onChangeReporteN(ValueChangeEvent e){
        if(e.getNewValue()!=null){
            String id = e.getNewValue().toString();
            if(id.equals("1")){
                habilitado=false;
            }else{
                habilitado=true;
            }
        }
    }
    
    public void limpiarCampos(){
        mensaje = "";
        arregloMun = new ArrayList();
        arregloDpto = new ArrayList();
        arregloMun1 = new ArrayList();
        arregloDpto1 = new ArrayList();
        arregloProf = new ArrayList();
        //arregloCausa = new ArrayList();
        //arregloDesenlace = new ArrayList();
        arregloPais = new ArrayList();
        visible = false;
        fechaNoti= Fecha.formateaFecha(new Date(), "dd/MM/yyyy hh:mm a");
        llenarComboPais();
        //llenarComboDpto();
        //llenarComboDpto1();
        //llenarComboProfesion();
        //llenarComboCausa();
        //llenarListaDesenlace();
        nombreRep="";
        sexo =' ';
        edad=0;
        edadEn=' ';
        direccionRep="";
        telefono="";
        pais="";
        codDepart="";
        codMun="";
        email="";
        nombreDm="";
        nombreComercial="";
        regSan="";
        lote="";
        referencia="";
        nombreFabricante="";
        nombreDistribuidor="";
        fechaEvento= null;
        fechaNotificacion=new Date();
        eventoDeteccion=0;
        descripcionEvento="";
        codigo=0;
        mensajeCorreo ="";
        visibleConfirmacion = false;
        mostrarConfirmacion = false;
        visible =  false;
        habilitado = false;
        activoDpto =  false;
        obligatorio1= false;
        obligatorio2= false;
        obligatorio3= false;
        obligatorio4= false;
        obligatorio5= false;
        obligatorio6= false;
        obligatorio7= false;
        obligatorio8= false;
        obligatorio9= false;
        obligatorio10= false;
        obligatorio11= false;
        obligatorio12= false;
        obligatorio13= false;
        obligatorio14= false;
        obligatorio15= false;
        obligatorio16= false;
        obligatorio17= false;
        obligatorio18= false;
        obligatorio19= false;
        obligatorio20= false;
        mostrarAsteriscos = false;
        mostrarErrorMail = false;
        mensajeConfirma="";  
        mostrarError = false;
        mensajeError = "";
    }
    
    public void cerrarSesion() {
        String url = "paginaAuxiliar.xhtml";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect(url);
        } catch (IOException ex) {
            logBeanWebTecno.log(Priority.ERROR, "Error de Entrada Salida Session", ex);
        } catch (Exception ex) {
            logBeanWebTecno.log(Priority.ERROR, "Error de Session", ex);
        }

    }
    
    public void btnCancelar() {
        
        try {
            //ec.redirect(url);
        } catch (Exception e) {
            //Logger.getLogger(InscribirRedManagedBean.class.getName()).log(Priority.ERROR, "Error al refdireccionar pagina", e);
        }
    }
}
