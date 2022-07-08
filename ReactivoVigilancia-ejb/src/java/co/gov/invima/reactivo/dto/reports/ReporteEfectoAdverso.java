/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.reactivo.dto.reports;

import java.io.Serializable;

/**
 *
 * @author jgutierrezme
 */
public class ReporteEfectoAdverso implements Serializable
{
        //***************************************************************
        //***************************************************************
        //***************************************************************
        //***************************************************************
        private String reporte;
        private String descrip_efecto;
        private String fecha_ingreso;
        private String fech_evento;
        private String efecto_indeseado;
        private String fech_reporte;
        private String cdg_problema;
        private String clasificacion;
        private String institucion;
        private String identificacion;
        private String direccion;
        private String naturaleza;
        private String complejidad;
        private String cod_depart;
        private String cod_mun;
        private String email;
        private String telefono;
        private String nombre_paciente;
        private String tipidentifi;
        private String identifi;
        private String edad;
        private String edad_en;
        private String genero;
        private String direcc_paciente;
        private String telefo_paciente;
        private String nombre_reactivo;
        private String nroregsan;
        private String fecha_venci;
        private String lote;
        private String procedencia;
        private String cadena_frio;
        private String temperatura;
        private String certi_analisis;
        private String distri_usuario;
        private String condici_almacen;
        private String area_funciona;
        private String otra_area;
        private String causa_efecto;
        private String causa_probable;
        private String notifico_importador;
        private String notifico_fabricante;
        private String notifico_comercializador;
        private String notifico_distribuidor;
        private String fechaNotificacion;
        private String enviadoDistImportador;
        private String gestionRiesgo;
        private String analisisEfecto;
        private String herramientaAnalisis;
        private String otra_herramienta;
        private String descripcionCausa;
        private String acciones;
        private String descripcion_acciones;
       //***************************************************************
       //***************************************************************
       //***************************************************************
        private String codCausaProbable;
        private String terminoCausa;
        private String descripcionCausaProb;
    //***************************************************************
    //***************************************************************
    //***************************************************************
    //***************************************************************
    //***************************************************************
    //***************************************************************
    /**
     * @return the reporte
     */
    public String getReporte() {
        return reporte;
    }

    /**
     * @param reporte the reporte to set
     */
    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    /**
     * @return the descrip_efecto
     */
    public String getDescrip_efecto() {
        return descrip_efecto;
    }

    /**
     * @param descrip_efecto the descrip_efecto to set
     */
    public void setDescrip_efecto(String descrip_efecto) {
        this.descrip_efecto = descrip_efecto;
    }

    /**
     * @return the fecha_ingreso
     */
    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the fech_evento
     */
    public String getFech_evento() {
        return fech_evento;
    }

    /**
     * @param fech_evento the fech_evento to set
     */
    public void setFech_evento(String fech_evento) {
        this.fech_evento = fech_evento;
    }

    /**
     * @return the efecto_indeseado
     */
    public String getEfecto_indeseado() {
        return efecto_indeseado;
    }

    /**
     * @param efecto_indeseado the efecto_indeseado to set
     */
    public void setEfecto_indeseado(String efecto_indeseado) {
        this.efecto_indeseado = efecto_indeseado;
    }

    /**
     * @return the fech_reporte
     */
    public String getFech_reporte() {
        return fech_reporte;
    }

    /**
     * @param fech_reporte the fech_reporte to set
     */
    public void setFech_reporte(String fech_reporte) {
        this.fech_reporte = fech_reporte;
    }

    /**
     * @return the cdg_problema
     */
    public String getCdg_problema() {
        return cdg_problema;
    }

    /**
     * @param cdg_problema the cdg_problema to set
     */
    public void setCdg_problema(String cdg_problema) {
        this.cdg_problema = cdg_problema;
    }

    /**
     * @return the clasificacion
     */
    public String getClasificacion() {
        return clasificacion;
    }

    /**
     * @param clasificacion the clasificacion to set
     */
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * @return the institucion
     */
    public String getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the naturaleza
     */
    public String getNaturaleza() {
        return naturaleza;
    }

    /**
     * @param naturaleza the naturaleza to set
     */
    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    /**
     * @return the complejidad
     */
    public String getComplejidad() {
        return complejidad;
    }

    /**
     * @param complejidad the complejidad to set
     */
    public void setComplejidad(String complejidad) {
        this.complejidad = complejidad;
    }

    /**
     * @return the cod_depart
     */
    public String getCod_depart() {
        return cod_depart;
    }

    /**
     * @param cod_depart the cod_depart to set
     */
    public void setCod_depart(String cod_depart) {
        this.cod_depart = cod_depart;
    }

    /**
     * @return the cod_mun
     */
    public String getCod_mun() {
        return cod_mun;
    }

    /**
     * @param cod_mun the cod_mun to set
     */
    public void setCod_mun(String cod_mun) {
        this.cod_mun = cod_mun;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the nombre_paciente
     */
    public String getNombre_paciente() {
        return nombre_paciente;
    }

    /**
     * @param nombre_paciente the nombre_paciente to set
     */
    public void setNombre_paciente(String nombre_paciente) {
        this.nombre_paciente = nombre_paciente;
    }

    /**
     * @return the tipidentifi
     */
    public String getTipidentifi() {
        return tipidentifi;
    }

    /**
     * @param tipidentifi the tipidentifi to set
     */
    public void setTipidentifi(String tipidentifi) {
        this.tipidentifi = tipidentifi;
    }

    /**
     * @return the identifi
     */
    public String getIdentifi() {
        return identifi;
    }

    /**
     * @param identifi the identifi to set
     */
    public void setIdentifi(String identifi) {
        this.identifi = identifi;
    }

    /**
     * @return the edad
     */
    public String getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(String edad) {
        this.edad = edad;
    }

    /**
     * @return the edad_en
     */
    public String getEdad_en() {
        return edad_en;
    }

    /**
     * @param edad_en the edad_en to set
     */
    public void setEdad_en(String edad_en) {
        this.edad_en = edad_en;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the direcc_paciente
     */
    public String getDirecc_paciente() {
        return direcc_paciente;
    }

    /**
     * @param direcc_paciente the direcc_paciente to set
     */
    public void setDirecc_paciente(String direcc_paciente) {
        this.direcc_paciente = direcc_paciente;
    }

    /**
     * @return the telefo_paciente
     */
    public String getTelefo_paciente() {
        return telefo_paciente;
    }

    /**
     * @param telefo_paciente the telefo_paciente to set
     */
    public void setTelefo_paciente(String telefo_paciente) {
        this.telefo_paciente = telefo_paciente;
    }

    /**
     * @return the nombre_reactivo
     */
    public String getNombre_reactivo() {
        return nombre_reactivo;
    }

    /**
     * @param nombre_reactivo the nombre_reactivo to set
     */
    public void setNombre_reactivo(String nombre_reactivo) {
        this.nombre_reactivo = nombre_reactivo;
    }

    /**
     * @return the nroregsan
     */
    public String getNroregsan() {
        return nroregsan;
    }

    /**
     * @param nroregsan the nroregsan to set
     */
    public void setNroregsan(String nroregsan) {
        this.nroregsan = nroregsan;
    }

    /**
     * @return the fecha_venci
     */
    public String getFecha_venci() {
        return fecha_venci;
    }

    /**
     * @param fecha_venci the fecha_venci to set
     */
    public void setFecha_venci(String fecha_venci) {
        this.fecha_venci = fecha_venci;
    }

    /**
     * @return the lote
     */
    public String getLote() {
        return lote;
    }

    /**
     * @param lote the lote to set
     */
    public void setLote(String lote) {
        this.lote = lote;
    }

    /**
     * @return the procedencia
     */
    public String getProcedencia() {
        return procedencia;
    }

    /**
     * @param procedencia the procedencia to set
     */
    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    /**
     * @return the cadena_frio
     */
    public String getCadena_frio() {
        return cadena_frio;
    }

    /**
     * @param cadena_frio the cadena_frio to set
     */
    public void setCadena_frio(String cadena_frio) {
        this.cadena_frio = cadena_frio;
    }

    /**
     * @return the temperatura
     */
    public String getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @return the certi_analisis
     */
    public String getCerti_analisis() {
        return certi_analisis;
    }

    /**
     * @param certi_analisis the certi_analisis to set
     */
    public void setCerti_analisis(String certi_analisis) {
        this.certi_analisis = certi_analisis;
    }

    /**
     * @return the distri_usuario
     */
    public String getDistri_usuario() {
        return distri_usuario;
    }

    /**
     * @param distri_usuario the distri_usuario to set
     */
    public void setDistri_usuario(String distri_usuario) {
        this.distri_usuario = distri_usuario;
    }

    /**
     * @return the condici_almacen
     */
    public String getCondici_almacen() {
        return condici_almacen;
    }

    /**
     * @param condici_almacen the condici_almacen to set
     */
    public void setCondici_almacen(String condici_almacen) {
        this.condici_almacen = condici_almacen;
    }

    /**
     * @return the area_funciona
     */
    public String getArea_funciona() {
        return area_funciona;
    }

    /**
     * @param area_funciona the area_funciona to set
     */
    public void setArea_funciona(String area_funciona) {
        this.area_funciona = area_funciona;
    }

    /**
     * @return the otra_area
     */
    public String getOtra_area() {
        return otra_area;
    }

    /**
     * @param otra_area the otra_area to set
     */
    public void setOtra_area(String otra_area) {
        this.otra_area = otra_area;
    }

    /**
     * @return the causa_efecto
     */
    public String getCausa_efecto() {
        return causa_efecto;
    }

    /**
     * @param causa_efecto the causa_efecto to set
     */
    public void setCausa_efecto(String causa_efecto) {
        this.causa_efecto = causa_efecto;
    }

    /**
     * @return the causa_probable
     */
    public String getCausa_probable() {
        return causa_probable;
    }

    /**
     * @param causa_probable the causa_probable to set
     */
    public void setCausa_probable(String causa_probable) {
        this.causa_probable = causa_probable;
    }

    /**
     * @return the notifico_importador
     */
    public String getNotifico_importador() {
        return notifico_importador;
    }

    /**
     * @param notifico_importador the notifico_importador to set
     */
    public void setNotifico_importador(String notifico_importador) {
        this.notifico_importador = notifico_importador;
    }

    /**
     * @return the notifico_fabricante
     */
    public String getNotifico_fabricante() {
        return notifico_fabricante;
    }

    /**
     * @param notifico_fabricante the notifico_fabricante to set
     */
    public void setNotifico_fabricante(String notifico_fabricante) {
        this.notifico_fabricante = notifico_fabricante;
    }

    /**
     * @return the notifico_comercializador
     */
    public String getNotifico_comercializador() {
        return notifico_comercializador;
    }

    /**
     * @param notifico_comercializador the notifico_comercializador to set
     */
    public void setNotifico_comercializador(String notifico_comercializador) {
        this.notifico_comercializador = notifico_comercializador;
    }

    /**
     * @return the notifico_distribuidor
     */
    public String getNotifico_distribuidor() {
        return notifico_distribuidor;
    }

    /**
     * @param notifico_distribuidor the notifico_distribuidor to set
     */
    public void setNotifico_distribuidor(String notifico_distribuidor) {
        this.notifico_distribuidor = notifico_distribuidor;
    }

    /**
     * @return the fechaNotificacion
     */
    public String getFechaNotificacion() {
        return fechaNotificacion;
    }

    /**
     * @param fechaNotificacion the fechaNotificacion to set
     */
    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    /**
     * @return the enviadoDistImportador
     */
    public String getEnviadoDistImportador() {
        return enviadoDistImportador;
    }

    /**
     * @param enviadoDistImportador the enviadoDistImportador to set
     */
    public void setEnviadoDistImportador(String enviadoDistImportador) {
        this.enviadoDistImportador = enviadoDistImportador;
    }

    /**
     * @return the gestionRiesgo
     */
    public String getGestionRiesgo() {
        return gestionRiesgo;
    }

    /**
     * @param gestionRiesgo the gestionRiesgo to set
     */
    public void setGestionRiesgo(String gestionRiesgo) {
        this.gestionRiesgo = gestionRiesgo;
    }

    /**
     * @return the analisisEfecto
     */
    public String getAnalisisEfecto() {
        return analisisEfecto;
    }

    /**
     * @param analisisEfecto the analisisEfecto to set
     */
    public void setAnalisisEfecto(String analisisEfecto) {
        this.analisisEfecto = analisisEfecto;
    }

    /**
     * @return the herramientaAnalisis
     */
    public String getHerramientaAnalisis() {
        return herramientaAnalisis;
    }

    /**
     * @param herramientaAnalisis the herramientaAnalisis to set
     */
    public void setHerramientaAnalisis(String herramientaAnalisis) {
        this.herramientaAnalisis = herramientaAnalisis;
    }

    /**
     * @return the otra_herramienta
     */
    public String getOtra_herramienta() {
        return otra_herramienta;
    }

    /**
     * @param otra_herramienta the otra_herramienta to set
     */
    public void setOtra_herramienta(String otra_herramienta) {
        this.otra_herramienta = otra_herramienta;
    }

    /**
     * @return the descripcionCausa
     */
    public String getDescripcionCausa() {
        return descripcionCausa;
    }

    /**
     * @param descripcionCausa the descripcionCausa to set
     */
    public void setDescripcionCausa(String descripcionCausa) {
        this.descripcionCausa = descripcionCausa;
    }

    /**
     * @return the acciones
     */
    public String getAcciones() {
        return acciones;
    }

    /**
     * @param acciones the acciones to set
     */
    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    /**
     * @return the descripcion_acciones
     */
    public String getDescripcion_acciones() {
        return descripcion_acciones;
    }

    /**
     * @param descripcion_acciones the descripcion_acciones to set
     */
    public void setDescripcion_acciones(String descripcion_acciones) {
        this.descripcion_acciones = descripcion_acciones;
    }
    //***************************************************************
    //***************************************************************
    //***************************************************************
    
    //***************************************************************
    //***************************************************************
    //***************************************************************
    //***************************************************************
    //***************************************************************
    //***************************************************************

    /**
     * @return the codCausaProbable
     */
    public String getCodCausaProbable() {
        return codCausaProbable;
    }

    /**
     * @param codCausaProbable the codCausaProbable to set
     */
    public void setCodCausaProbable(String codCausaProbable) {
        this.codCausaProbable = codCausaProbable;
    }

    /**
     * @return the terminoCausa
     */
    public String getTerminoCausa() {
        return terminoCausa;
    }

    /**
     * @param terminoCausa the terminoCausa to set
     */
    public void setTerminoCausa(String terminoCausa) {
        this.terminoCausa = terminoCausa;
    }

    /**
     * @return the descripcionCausaProb
     */
    public String getDescripcionCausaProb() {
        return descripcionCausaProb;
    }

    /**
     * @param descripcionCausaProb the descripcionCausaProb to set
     */
    public void setDescripcionCausaProb(String descripcionCausaProb) {
        this.descripcionCausaProb = descripcionCausaProb;
    }
}
