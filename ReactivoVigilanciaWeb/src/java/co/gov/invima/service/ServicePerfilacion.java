/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.service;

import co.gov.invima.dto.ParametrosSolicitarVisitaDTO;
import co.gov.invima.dto.ResponseDTO;
import co.gov.invima.dto.SedeDTO;
import co.gov.invima.dto.service.ConsultarSedesRqService;
import co.gov.invima.dto.service.ConsultarSedesRsService;
import co.gov.invima.dto.service.SolicitarVisitaRqService;
import co.gov.invima.dto.service.SolicitarVisitaRsService;
import co.gov.invima.util.PropertiesReader;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Chavarro
 */
public class ServicePerfilacion {

    private final static Logger logSrvPerfila = Logger.getLogger(ServicePerfilacion.class.getName());

   
    public ServicePerfilacion(){
        super();
    }
        
    public static void main(String[] args){
        
       
    }
    public List<SedeDTO> consultarEstablecimientos(final ConsultarSedesRqService pConsultarSedesRq,  ResponseDTO response) {
        List<SedeDTO> lstSedes = new ArrayList<>();
        lstSedes.clear();
        try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String urlServicio = pr.getProperty("URL_SRV_ALERTA_SANITARIAS");
            logSrvPerfila.info("URL_SRV_ALERTA_SANITARIAS: " + urlServicio);
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(urlServicio + "/reactivoVigilancia/consultarSedes");
            Invocation.Builder sol = target.request();

            Gson gson = new Gson();
            String jsonCons = gson.toJson(pConsultarSedesRq);
            logSrvPerfila.info(jsonCons);
            Response resp = sol.post(Entity.json(jsonCons));

            response = resp.readEntity(ResponseDTO.class);
            if (response.getStatusCode() == HttpStatus.OK.value()) {
                
                logSrvPerfila.info(" responseJson " + response.getObjectResponse());
                String jsonString = gson.toJson(response.getObjectResponse());
                logSrvPerfila.info(jsonString);
                ConsultarSedesRsService consultarSedes = gson.fromJson(jsonString, ConsultarSedesRsService.class);
                logSrvPerfila.info("getSedes: " + consultarSedes.getSedes().size());
                lstSedes = ServicePerfilacion.transformarSedesASedes(consultarSedes);
                for(SedeDTO sedeLoop: lstSedes){
                    System.out.println("IdEmpresa: " + sedeLoop.getIdEmpresa());
                }
            }
        } catch (JsonSyntaxException e) {
            logSrvPerfila.error(e);
        }
        return lstSedes;
    }
    
    public static List<SedeDTO> transformarSedesASedes(final ConsultarSedesRsService consultarSedes) {
        List<SedeDTO> lstSedes = new ArrayList<>();
        for(int i=0;i<consultarSedes.getSedes().size();i++){
            logSrvPerfila.info("getSede: " + consultarSedes.getSedes().get(i).getSede());
            if(consultarSedes.getSedes().get(i).getSede()!= null){
                SedeDTO sede = new SedeDTO();
                sede.setNombre(consultarSedes.getSedes().get(i).getSede().getNombre() != null ? consultarSedes.getSedes().get(i).getSede().getNombre():"");
                sede.setMunicipio(consultarSedes.getSedes().get(i).getSede().getMunicipio() != null ? consultarSedes.getSedes().get(i).getSede().getMunicipio():"");
                sede.setIdEmpresa(consultarSedes.getSedes().get(i).getSede().getIdEmpresa() != null ? consultarSedes.getSedes().get(i).getSede().getIdEmpresa():null);
                sede.setDireccion(consultarSedes.getSedes().get(i).getSede().getDireccion() != null ? consultarSedes.getSedes().get(i).getSede().getDireccion():"");
                sede.setDepartamento(consultarSedes.getSedes().get(i).getSede().getDepartamento()!= null ? consultarSedes.getSedes().get(i).getSede().getDepartamento():"");
                sede.setNumeroDocumento(consultarSedes.getSedes().get(i).getSede().getNumeroDocumento()!= null ? consultarSedes.getSedes().get(i).getSede().getNumeroDocumento():"");
                sede.setIdSede(consultarSedes.getSedes().get(i).getSede().getIdSede() != null ? consultarSedes.getSedes().get(i).getSede().getIdSede():null);
                lstSedes.add(sede);
            }
        }
        logSrvPerfila.info("lstSedes: " + lstSedes.size());
        return lstSedes;
    }

}
