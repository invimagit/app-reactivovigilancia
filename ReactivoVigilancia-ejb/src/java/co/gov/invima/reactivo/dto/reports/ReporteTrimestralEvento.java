/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.reactivo.dto.reports;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jgutierrezme
 */
public class ReporteTrimestralEvento implements Serializable
{
        //**************************************************************************
        //**************************************************************************
        //**************************************************************************
        //**************************************************************************
        //**************************************************************************
        private String reporte;
        private String institucion;
        private String departamento;
        private String ciudad;
        private String direccion;
        private String identificacion;
        private String complejidad;
        private String naturaleza;
        private String nombre_paciente;
        private String tipidentifi;
        private String identifi;
        private String genero;
        private String edad;
        private String edad_en;
        private String nombre_reactivo;
        private String nroregsan;
        private String expediente;
        private String codigoUnicoReactivo;
        private String codigoTipoDiagnostico;
        private String lote;
        private String referencia;
        private String fecha_venci;
        private String procedencia;
        private String cadena_frio;
        private String temperatura;
        private String categoriaReactivo;
        private String condici_almacen;
        private String indRealizaRecepcion;
        private String certi_analisis;
        private String fabricante;
        private String importador;
        private String area_funciona;
        private String fecha_ingreso;
        private String descrip_efecto;
        private String desenlace;
        private String otro_desenlace;
        private String gestion_riesgo;
        private String analisis_efecto;
        private String herramienta_analisis;
        private String otra_herramienta;
        private String causa_efecto;
        private String causa_probable;
        private String descripcion_causa;
        private String codigoCausa;
        private String acciones;
        private String descripcion_acciones;
        private String enviado_distri_import;
        private String fecha_notificacion;
        private String fechaEnvioReactivo;
        private String nombres_apellidos;
        private String profesion;
        private String organizacion;
        private String direccion_reportante;
        private String telefono;
        private String cod_depart;
        private String cod_mun;
        private String email;
        private String fecha_notificacion_reportante;
        private String divulgacion;
        private String cdg_tiporeportante;
        private String estadoReporte;
        private String fechaGestionEnte;
        private String gestionEnte;
        private String estadoGestionEnte;
        private String fechaGestionInvima;
        private String observacionInvima;
        private boolean enEdicion=false;
        private Date fechaGestionEnte_temp;
        private Date fechaGestionInvima_temp;

    public boolean isEnEdicion() {
        return enEdicion;
    }

    public void setEnEdicion(boolean enEdicion) {
        this.enEdicion = enEdicion;
    }
        //**************************************************************************
        //**************************************************************************
        //**************************************************************************
        //**************************************************************************
        //**************************************************************************
        //**************************************************************************
        private String codigoCausaProbable;
        private String terminoCausa;
        private String descripcionCausa;

        
        public Date getFechaGestionEnte_temp() {
            return fechaGestionEnte_temp;
        }

    public void setFechaGestionEnte_temp(Date fechaGestionEnte_temp) {
        this.fechaGestionEnte_temp = fechaGestionEnte_temp;
    }

    public Date getFechaGestionInvima_temp() {
        return fechaGestionInvima_temp;
    }

    /**
     * @return the reporte
     */
    public void setFechaGestionInvima_temp(Date fechaGestionInvima_temp) {
        this.fechaGestionInvima_temp = fechaGestionInvima_temp;
    }

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
         * @return the departamento
         */
        public String getDepartamento() {
            return departamento;
        }

        /**
         * @param departamento the departamento to set
         */
        public void setDepartamento(String departamento) {
            this.departamento = departamento;
        }

        /**
         * @return the ciudad
         */
        public String getCiudad() {
            return ciudad;
        }

        /**
         * @param ciudad the ciudad to set
         */
        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
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
         * @return the expediente
         */
        public String getExpediente() {
            return expediente;
        }

        /**
         * @param expediente the expediente to set
         */
        public void setExpediente(String expediente) {
            this.expediente = expediente;
        }

        /**
         * @return the codigoUnicoReactivo
         */
        public String getCodigoUnicoReactivo() {
            return codigoUnicoReactivo;
        }

        /**
         * @param codigoUnicoReactivo the codigoUnicoReactivo to set
         */
        public void setCodigoUnicoReactivo(String codigoUnicoReactivo) {
            this.codigoUnicoReactivo = codigoUnicoReactivo;
        }

        /**
         * @return the codigoTipoDiagnostico
         */
        public String getCodigoTipoDiagnostico() {
            return codigoTipoDiagnostico;
        }

        /**
         * @param codigoTipoDiagnostico the codigoTipoDiagnostico to set
         */
        public void setCodigoTipoDiagnostico(String codigoTipoDiagnostico) {
            this.codigoTipoDiagnostico = codigoTipoDiagnostico;
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
         * @return the referencia
         */
        public String getReferencia() {
            return referencia;
        }

        /**
         * @param referencia the referencia to set
         */
        public void setReferencia(String referencia) {
            this.referencia = referencia;
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
         * @return the categoriaReactivo
         */
        public String getCategoriaReactivo() {
            return categoriaReactivo;
        }

        /**
         * @param categoriaReactivo the categoriaReactivo to set
         */
        public void setCategoriaReactivo(String categoriaReactivo) {
            this.categoriaReactivo = categoriaReactivo;
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
         * @return the indRealizaRecepcion
         */
        public String getIndRealizaRecepcion() {
            return indRealizaRecepcion;
        }

        /**
         * @param indRealizaRecepcion the indRealizaRecepcion to set
         */
        public void setIndRealizaRecepcion(String indRealizaRecepcion) {
            this.indRealizaRecepcion = indRealizaRecepcion;
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
         * @return the fabricante
         */
        public String getFabricante() {
            return fabricante;
        }

        /**
         * @param fabricante the fabricante to set
         */
        public void setFabricante(String fabricante) {
            this.fabricante = fabricante;
        }

        /**
         * @return the importador
         */
        public String getImportador() {
            return importador;
        }

        /**
         * @param importador the importador to set
         */
        public void setImportador(String importador) {
            this.importador = importador;
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
         * @return the desenlace
         */
        public String getDesenlace() {
            return desenlace;
        }

        /**
         * @param desenlace the desenlace to set
         */
        public void setDesenlace(String desenlace) {
            this.desenlace = desenlace;
        }

        /**
         * @return the otro_desenlace
         */
        public String getOtro_desenlace() {
            return otro_desenlace;
        }

        /**
         * @param otro_desenlace the otro_desenlace to set
         */
        public void setOtro_desenlace(String otro_desenlace) {
            this.otro_desenlace = otro_desenlace;
        }

        /**
         * @return the gestion_riesgo
         */
        public String getGestion_riesgo() {
            return gestion_riesgo;
        }

        /**
         * @param gestion_riesgo the gestion_riesgo to set
         */
        public void setGestion_riesgo(String gestion_riesgo) {
            this.gestion_riesgo = gestion_riesgo;
        }

        /**
         * @return the analisis_efecto
         */
        public String getAnalisis_efecto() {
            return analisis_efecto;
        }

        /**
         * @param analisis_efecto the analisis_efecto to set
         */
        public void setAnalisis_efecto(String analisis_efecto) {
            this.analisis_efecto = analisis_efecto;
        }

        /**
         * @return the herramienta_analisis
         */
        public String getHerramienta_analisis() {
            return herramienta_analisis;
        }

        /**
         * @param herramienta_analisis the herramienta_analisis to set
         */
        public void setHerramienta_analisis(String herramienta_analisis) {
            this.herramienta_analisis = herramienta_analisis;
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
         * @return the descripcion_causa
         */
        public String getDescripcion_causa() {
            return descripcion_causa;
        }

        /**
         * @param descripcion_causa the descripcion_causa to set
         */
        public void setDescripcion_causa(String descripcion_causa) {
            this.descripcion_causa = descripcion_causa;
        }

        /**
         * @return the codigoCausa
         */
        public String getCodigoCausa() {
            return codigoCausa;
        }

        /**
         * @param codigoCausa the codigoCausa to set
         */
        public void setCodigoCausa(String codigoCausa) {
            this.codigoCausa = codigoCausa;
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

        /**
         * @return the enviado_distri_import
         */
        public String getEnviado_distri_import() {
            return enviado_distri_import;
        }

        /**
         * @param enviado_distri_import the enviado_distri_import to set
         */
        public void setEnviado_distri_import(String enviado_distri_import) {
            this.enviado_distri_import = enviado_distri_import;
        }

        /**
         * @return the fecha_notificacion
         */
        public String getFecha_notificacion() {
            return fecha_notificacion;
        }

        /**
         * @param fecha_notificacion the fecha_notificacion to set
         */
        public void setFecha_notificacion(String fecha_notificacion) {
            this.fecha_notificacion = fecha_notificacion;
        }

        /**
         * @return the fechaEnvioReactivo
         */
        public String getFechaEnvioReactivo() {
            return fechaEnvioReactivo;
        }

        /**
         * @param fechaEnvioReactivo the fechaEnvioReactivo to set
         */
        public void setFechaEnvioReactivo(String fechaEnvioReactivo) {
            this.fechaEnvioReactivo = fechaEnvioReactivo;
        }

        /**
         * @return the nombres_apellidos
         */
        public String getNombres_apellidos() {
            return nombres_apellidos;
        }

        /**
         * @param nombres_apellidos the nombres_apellidos to set
         */
        public void setNombres_apellidos(String nombres_apellidos) {
            this.nombres_apellidos = nombres_apellidos;
        }

        /**
         * @return the profesion
         */
        public String getProfesion() {
            return profesion;
        }

        /**
         * @param profesion the profesion to set
         */
        public void setProfesion(String profesion) {
            this.profesion = profesion;
        }

        /**
         * @return the organizacion
         */
        public String getOrganizacion() {
            return organizacion;
        }

        /**
         * @param organizacion the organizacion to set
         */
        public void setOrganizacion(String organizacion) {
            this.organizacion = organizacion;
        }

        /**
         * @return the direccion_reportante
         */
        public String getDireccion_reportante() {
            return direccion_reportante;
        }

        /**
         * @param direccion_reportante the direccion_reportante to set
         */
        public void setDireccion_reportante(String direccion_reportante) {
            this.direccion_reportante = direccion_reportante;
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
         * @return the fecha_notificacion_reportante
         */
        public String getFecha_notificacion_reportante() {
            return fecha_notificacion_reportante;
        }

        /**
         * @param fecha_notificacion_reportante the fecha_notificacion_reportante to set
         */
        public void setFecha_notificacion_reportante(String fecha_notificacion_reportante) {
            this.fecha_notificacion_reportante = fecha_notificacion_reportante;
        }

        /**
         * @return the divulgacion
         */
        public String getDivulgacion() {
            return divulgacion;
        }

        /**
         * @param divulgacion the divulgacion to set
         */
        public void setDivulgacion(String divulgacion) {
            this.divulgacion = divulgacion;
        }

        /**
         * @return the cdg_tiporeportante
         */
        public String getCdg_tiporeportante() {
            return cdg_tiporeportante;
        }

        /**
         * @param cdg_tiporeportante the cdg_tiporeportante to set
         */
        public void setCdg_tiporeportante(String cdg_tiporeportante) {
            this.cdg_tiporeportante = cdg_tiporeportante;
        }

        /**
         * @return the estadoReporte
         */
        public String getEstadoReporte() {
            return estadoReporte;
        }

        /**
         * @param estadoReporte the estadoReporte to set
         */
        public void setEstadoReporte(String estadoReporte) {
            this.estadoReporte = estadoReporte;
        }

        /**
         * @return the fechaGestionEnte
         */
        public String getFechaGestionEnte() {
            return fechaGestionEnte;
        }

        /**
         * @param fechaGestionEnte the fechaGestionEnte to set
         */
        public void setFechaGestionEnte(String fechaGestionEnte) {
            this.fechaGestionEnte = fechaGestionEnte;
        }

        /**
         * @return the gestionEnte
         */
        public String getGestionEnte() {
            return gestionEnte;
        }

        /**
         * @param gestionEnte the gestionEnte to set
         */
        public void setGestionEnte(String gestionEnte) {
            this.gestionEnte = gestionEnte;
        }

        /**
         * @return the estadoGestionEnte
         */
        public String getEstadoGestionEnte() {
            return estadoGestionEnte;
        }

        /**
         * @param estadoGestionEnte the estadoGestionEnte to set
         */
        public void setEstadoGestionEnte(String estadoGestionEnte) {
            this.estadoGestionEnte = estadoGestionEnte;
        }

        /**
         * @return the fechaGestionInvima
         */
        public String getFechaGestionInvima() {
            return fechaGestionInvima;
        }

        /**
         * @param fechaGestionInvima the fechaGestionInvima to set
         */
        public void setFechaGestionInvima(String fechaGestionInvima) {
            this.fechaGestionInvima = fechaGestionInvima;
        }

        /**
         * @return the observacionInvima
         */
        public String getObservacionInvima() {
            return observacionInvima;
        }

        /**
         * @param observacionInvima the observacionInvima to set
         */
        public void setObservacionInvima(String observacionInvima) {
            this.observacionInvima = observacionInvima;
        }
        
        /**
         * @return the codigoCausaProbable
         */
        public String getCodigoCausaProbable() {
            return codigoCausaProbable;
        }

        /**
         * @param codigoCausaProbable the codigoCausaProbable to set
         */
        public void setCodigoCausaProbable(String codigoCausaProbable) {
            this.codigoCausaProbable = codigoCausaProbable;
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
}
