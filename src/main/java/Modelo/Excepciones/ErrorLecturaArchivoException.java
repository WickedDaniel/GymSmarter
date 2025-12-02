/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package Modelo.Excepciones;

/**
 *
 * @author WickedDaniel
 */
public class ErrorLecturaArchivoException extends GymSmarterException {

    /**
     * Creates a new instance of <code>ErrorLecturaArchivoException</code> without detail message.
     */
    public ErrorLecturaArchivoException() {
    }


    /**
     * Constructs an instance of <code>ErrorLecturaArchivoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ErrorLecturaArchivoException(String msg) {
        super(msg);
    }
}
