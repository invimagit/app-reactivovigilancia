/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Chavarro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalServiceDTO {

    private Integer idSede;
    private Integer idTipoDocumento;
    private String numeroDocumento;
    private String digitoVerificacion;
    private String razonSocial;
    private String nombreComercial;
    private String paginaWeb;
    private Integer idPais;
    private Integer idDepartamento;
    private Integer idMunicipio;
    private String correoElectronico;
    private String direccion;
    private String telefono;
    private String celular;
}
