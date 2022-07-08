/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Kubit
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sede {
    String municipio;
    String direccion;
    String departamento;
    String nombre;
    String numeroDocumento;
    String rol;
    int id;
    boolean seleccionado;    
}
