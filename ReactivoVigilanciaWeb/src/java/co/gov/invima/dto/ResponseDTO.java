/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto;

/**
 *
 * @author Kubit
 */

public class ResponseDTO {
    
    private Message message;
    private Object objectResponse;
    private Integer statusCode;

    public ResponseDTO() {
        this.message = new Message();
        this.objectResponse = new Object();
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
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
