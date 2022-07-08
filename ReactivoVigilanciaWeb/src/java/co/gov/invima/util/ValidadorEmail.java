/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.util;

/**
 *
 * @author Diana Silva
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Clase que valida una direccion de correo electronico
 *
 *
 */
public class ValidadorEmail implements Validator {

    /**
     * Metodo que lanza el mensaje de validacion
     *
     *
     * @param detail detalle del mensaje
     *
     * @throws ValidatorException
     */
    private void throwValidatorException(String detail) throws ValidatorException {
        FacesMessage message = new FacesMessage();

        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        message.setSummary("Correo Electrónico inválido");
        message.setDetail(detail);

        throw new ValidatorException(message);
    }

    /**
     * Valida el campo email de cualquier formulario.
     *
     * @param email valor a validar
     * @return boolean En función del resultado, retorna true si se cumple o
     * false si la comprobación es incorrecta
     */
    public boolean validaEmail(String email) {

        //Set the email pattern string
        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

        //Match the given string with the pattern
        Matcher m = p.matcher(email);

        //check whether match is found 
        boolean matchFound = m.matches();

        if (matchFound) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que valida si el dato es un email valido
     *
     *
     * @param facesContext
     * @param uiComponent
     * @param o dato a validar
     *
     * @throws ValidatorException
     */
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String valor = o.toString();
        if (!validaEmail(valor)) {
            throwValidatorException("El valor ingresado no es una direccion de correo válida");
        }
    }
}
