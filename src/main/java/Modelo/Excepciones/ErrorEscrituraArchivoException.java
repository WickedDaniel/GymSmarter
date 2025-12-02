/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package Modelo.Excepciones;

/**
 *
 * @author WickedDaniel
 */
public class ErrorEscrituraArchivoException extends GymSmarterException {

    /**
     * Creates a new instance of <code>ErrorEscrituraArchivoException</code> without detail message.
     */
    public ErrorEscrituraArchivoException() {
    }


    /**
     * Constructs an instance of <code>ErrorEscrituraArchivoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ErrorEscrituraArchivoException(String msg) {
        super(msg);
    }
}
