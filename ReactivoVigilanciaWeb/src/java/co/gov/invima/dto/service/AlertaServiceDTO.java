/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto.service;

import java.util.List;
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
public class AlertaServiceDTO {
    private String ip;
    private String usuario;
    private String numeroAsociado;
    private Integer idDependencia;
    private boolean aplicaTecno;
    private boolean aplicaReactivo;
    private List<SucursalServiceDTO> sucursales;
}
