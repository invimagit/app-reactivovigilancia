/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto;

/**
 *
 * @author Chavarro
 */
public class ResponseVisitaDTO {
     private String message;
     private Object objectResponse;
     private Integer statusCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObjectResponse() {
        return objectResponse;
    }

    public void setObjectResponse(Object objectResponse) {
        this.objectResponse = objectResponse;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
     
     
}
