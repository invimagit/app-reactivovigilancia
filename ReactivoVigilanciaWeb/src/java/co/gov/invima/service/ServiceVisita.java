/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.service;

import co.gov.invima.dto.DepartamentoDTO;
import co.gov.invima.dto.MunicipioDTO;
import co.gov.invima.dto.ResponseVisitaDTO;
import co.gov.invima.dto.RolDTO;
import co.gov.invima.util.PropertiesReader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Chavarro
 */
public class ServiceVisita {

    private final static Logger logSrvVis = Logger.getLogger(ServiceVisita.class.getName());
    private final String BASE_ERROR_GUARDAR_ANTECEDENTE = "Error Guardando Antecedente: ";
    private final String BASE_ERROR_GUARDAR_EMPRESA_NO_INSCRITA = "Error Guardando Empresa No Inscrita: ";
    private final String BASE_ERROR_CREAR_TAREA = "Error Creando Tarea: ";
    private final String BASE_ERROR_GUARDAR_DATOS_EMPRESAS = "Error Guardando los datos de las Empresas: ";
    private final String ERROR_RESPUESTA_SERVICIO = "Falló el servicio, consulte con el administrador \n";
    private final String ERROR_JSON = "Consulte con el administrador... \n Fallo mapeo en la entrada o respuesta del servicio";
    private final String ERROR_IDENTIFICACION = "El numero de identificacion ya existe en la base de datos \n";
    private HttpSession session;

    public ServiceVisita() {
        super();
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public List<RolDTO> obtenerRoles() {
        ResponseVisitaDTO response = new ResponseVisitaDTO();
        List<RolDTO> retorno = new ArrayList<>();
        try {
            System.out.println("Entró a obtenerRoles()");
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String urlServicio = pr.getProperty("URL_SRV_MAESTRO");
            logSrvVis.info("URL_SRV_MAESTRO: " + urlServicio);
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(urlServicio + "/maestro/listarTodos/TipoRolDAO");
            Invocation.Builder sol = target.request();
            Response resp = sol.get();

            Gson gson = new Gson();
            response = resp.readEntity(ResponseVisitaDTO.class);
            System.out.println("obtenerRoles: " + response.getStatusCode());
            if (response.getStatusCode() == HttpStatus.OK.value()) {
                logSrvVis.info(" responseJson " + response.getObjectResponse());
                System.out.println("responseJson " + response.getObjectResponse());
                String jsonString = gson.toJson(response.getObjectResponse());
                logSrvVis.info(jsonString);
                List<RolDTO> roles = gson.fromJson(jsonString, ArrayList.class);
                logSrvVis.info("Rol: " + roles);
                retorno = roles;
            }
        } catch (JsonSyntaxException e) {
            logSrvVis.error(e);
        }
        return retorno;
    }

    public List<DepartamentoDTO> obtenerDepartamentos() {
        ResponseVisitaDTO response = new ResponseVisitaDTO();
        List<DepartamentoDTO> retorno = new ArrayList<>();
        try {
            System.out.println("Entró a obtenerDepartamentos()");
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String urlServicio = pr.getProperty("URL_SRV_MAESTRO");
            logSrvVis.info("URL_SRV_MAESTRO: " + urlServicio);
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(urlServicio + "/maestro/listarTodos/DepartamentoDAO");
            Invocation.Builder sol = target.request();
            Response resp = sol.get();

            Gson gson = new Gson();
            response = resp.readEntity(ResponseVisitaDTO.class);
            System.out.println("obtenerDepartamentos: " + response.getStatusCode());
            if (response.getStatusCode() == HttpStatus.OK.value()) {
                logSrvVis.info(" responseJson " + response.getObjectResponse());
                System.out.println("responseJson " + response.getObjectResponse());
                String jsonString = gson.toJson(response.getObjectResponse());
                logSrvVis.info(jsonString);
                List<DepartamentoDTO> deptos = gson.fromJson(jsonString, ArrayList.class);
                logSrvVis.info("Departamentos: " + deptos);
                retorno = deptos;
            }
        } catch (JsonSyntaxException e) {
            logSrvVis.error(e);
        }
        return retorno;
    }

    public List<MunicipioDTO> obtenerMunicipios(String idDepartamento) {
        ResponseVisitaDTO response = new ResponseVisitaDTO();
        List<MunicipioDTO> retorno = new ArrayList<>();
        try {
            System.out.println("Entró a obtenerMunicipios()");
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String urlServicio = pr.getProperty("URL_SRV_MAESTRO");
            logSrvVis.info("URL_SRV_MAESTRO: " + urlServicio);
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(urlServicio + "/maestro/buscarPorAtributo/MunicipioDAO/idDepartamento/" + idDepartamento);
            Invocation.Builder sol = target.request();
            Response resp = sol.get();

            Gson gson = new Gson();
            response = resp.readEntity(ResponseVisitaDTO.class);
            System.out.println("obtenerMunicipios: " + response.getStatusCode());
            if (response.getStatusCode() == HttpStatus.OK.value()) {
                logSrvVis.info(" responseJson " + response.getObjectResponse());
                System.out.println("responseJson " + response.getObjectResponse());
                String jsonString = gson.toJson(response.getObjectResponse());
                logSrvVis.info(jsonString);
                List<MunicipioDTO> municipios = gson.fromJson(jsonString, ArrayList.class);
                logSrvVis.info("Municipios: " + municipios);
                retorno = municipios;
            }
        } catch (JsonSyntaxException e) {
            logSrvVis.error(e);
        }
        return retorno;
    }
}
