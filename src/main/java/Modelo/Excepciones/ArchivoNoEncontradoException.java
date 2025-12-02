/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package Modelo.Excepciones;

/**
 *
 * @author WickedDaniel
 */
public class ArchivoNoEncontradoException extends GymSmarterException {

    /**
     * Creates a new instance of <code>ArchivoNoEncontradoException</code> without detail message.
     */
    public ArchivoNoEncontradoException() {
    }


    /**
     * Constructs an instance of <code>ArchivoNoEncontradoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ArchivoNoEncontradoException(String msg) {
        super(msg);
    }
}
