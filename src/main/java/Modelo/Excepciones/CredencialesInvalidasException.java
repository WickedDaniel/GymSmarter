/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package Modelo.Excepciones;

/**
 *
 * @author WickedDaniel
 */
public class CredencialesInvalidasException extends GymSmarterException {

    /**
     * Creates a new instance of <code>CredencialesInvalidasException</code> without detail message.
     */
    public CredencialesInvalidasException() {
    }


    /**
     * Constructs an instance of <code>CredencialesInvalidasException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CredencialesInvalidasException(String msg) {
        super(msg);
    }
}
