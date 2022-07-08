/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.service;

import co.gov.invima.dto.ResponseVisitaDTO;
import co.gov.invima.dto.SedeDTO;
import co.gov.invima.dto.service.AlertaServiceDTO;
import co.gov.invima.dto.service.EmpresaServiceDTO;
import co.gov.invima.dto.service.SucursalServiceDTO;
import co.gov.invima.util.PropertiesReader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Chavarro
 */
public class ServiceAlerta {
     private final static Logger logSrvPerfila = Logger.getLogger(ServicePerfilacion.class.getName());
     
     private HttpSession session;
     
     public ServiceAlerta(){
         this.session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
     }
     
     public ResponseVisitaDTO crearTareaAlertaSanitarias(List<SedeDTO> lstSedes){
         ResponseVisitaDTO response = new ResponseVisitaDTO();
         try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String urlServicio = pr.getProperty("URL_SRV_ALERTA_SANITARIAS");
            logSrvPerfila.info("URL_SRV_VISITA: " + urlServicio);
            AlertaServiceDTO alerta = new AlertaServiceDTO();
            alerta.setIp("");
            alerta.setUsuario("dchavarro");
            alerta.setNumeroAsociado((String) session.getAttribute("numeroReporte"));
            alerta.setIdDependencia(Integer.parseInt(pr.getProperty("ID_TIPO_DEPENDENCIA")));
            alerta.setAplicaReactivo(Boolean.TRUE);
            alerta.setAplicaTecno(Boolean.FALSE);
            List<SucursalServiceDTO> lstSucursales = new ArrayList<SucursalServiceDTO>();
            for(SedeDTO item: lstSedes){
                SucursalServiceDTO sucursal = new SucursalServiceDTO();
                sucursal.setIdSede(item.getIdSede() != null ? item.getIdSede():null);
                sucursal.setIdTipoDocumento(Integer.parseInt(pr.getProperty("ID_TIPO_DOCUMENTO")));
                sucursal.setNumeroDocumento(item.getNumeroDocumento() != null ? item.getNumeroDocumento():"");
                sucursal.setDigitoVerificacion("");
                sucursal.setRazonSocial(item.getNombre() != null ? item.getNombre():"");
                sucursal.setNombreComercial("");
                sucursal.setPaginaWeb("");
                sucursal.setIdPais(Integer.parseInt(pr.getProperty("ID_PAIS")));
                sucursal.setIdDepartamento(item.getDepartamento() != null ? Integer.parseInt(item.getDepartamento()):null );
                sucursal.setIdMunicipio(item.getMunicipio() != null ? Integer.parseInt(item.getMunicipio()):null );
                sucursal.setCorreoElectronico("");
                sucursal.setDireccion(item.getDireccion() != null ? item.getDireccion():"");
                sucursal.setTelefono("");
                sucursal.setCelular("");
                lstSucursales.add(sucursal);
            }
            alerta.setSucursales(lstSucursales);
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(urlServicio +  pr.getProperty("END_POINT_ALERTAS_SANITARIAS"));
            Invocation.Builder sol = target.request();

            Gson gson = new Gson();
            String jsonCons = gson.toJson(alerta);
            logSrvPerfila.info(jsonCons);
            System.out.println("co.gov.invima.service.ServiceTarea.crearTarea() jsonCons " + jsonCons);
            Response resp = sol.post(Entity.json(jsonCons));

            response = resp.readEntity(ResponseVisitaDTO.class);
            System.out.println("co.gov.invima.service.ServiceTarea.crearTarea() " + response.getStatusCode());
            if (response.getStatusCode() == HttpStatus.CREATED.value()) {
                logSrvPerfila.info(" responseJson " + response.getObjectResponse());
            }
        } catch (JsonSyntaxException e) {
            logSrvPerfila.error(e);
        }
         
         return response;
     }
     
     
     public ResponseVisitaDTO guardarDatosEmpresa(List<SedeDTO> lstSedes){
          ResponseVisitaDTO response = new ResponseVisitaDTO();
         try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String urlServicio = pr.getProperty("URL_SRV_VISITA_MOCK");
            logSrvPerfila.info("URL_SRV_VISITA_MOCK: " + urlServicio);
            List<EmpresaServiceDTO> lstEmpresas = ServiceAlerta.transformarEmpresaAEmpService(lstSedes, pr.getProperty("invima.usuario"));
            
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(urlServicio + "/empresa-tarea");
            Invocation.Builder sol = target.request();

            Gson gson = new Gson();
            String jsonCons = gson.toJson(lstEmpresas);
            logSrvPerfila.info(jsonCons);
            System.out.println("guardarDatosEmpresa jsonCons " + jsonCons);
            Response resp = sol.post(Entity.json(jsonCons));

            response = resp.readEntity(ResponseVisitaDTO.class);
            System.out.println("guardarDatosEmpresa " + response.getStatusCode());
            if (response.getStatusCode() == HttpStatus.CREATED.value()) {
                logSrvPerfila.info(" responseJson " + response.getObjectResponse());
                System.out.println("responseJson " + response.getObjectResponse());
                String jsonString = gson.toJson(response.getObjectResponse());
                logSrvPerfila.info(jsonString);
            }
        } catch (JsonSyntaxException e) {
            logSrvPerfila.error(e);
        }
         
         return response;
     }
     
     
     public static List<EmpresaServiceDTO> transformarEmpresaAEmpService(final List<SedeDTO> lstSedes, String usuarioCrea){
         List<EmpresaServiceDTO> lstEmpresas = new ArrayList<>();
         
         for(int i=0;i<lstSedes.size();i++){
             EmpresaServiceDTO emp = new EmpresaServiceDTO();
             emp.setIdEmpresa(lstSedes.get(i).getIdEmpresa());
             emp.setIdTarea(lstSedes.get(i).getIdTarea());
             emp.setUsuarioCrea(usuarioCrea);
             lstEmpresas.add(emp);
         }
         
         return lstEmpresas;
         
     }
     

}
