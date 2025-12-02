/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package Modelo.Excepciones;

/**
 *
 * @author WickedDaniel
 */
public class MetaNoEncontradaException extends GymSmarterException {

    /**
     * Creates a new instance of <code>MetaNoEncontradaException</code> without detail message.
     */
    public MetaNoEncontradaException() {
    }


    /**
     * Constructs an instance of <code>MetaNoEncontradaException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public MetaNoEncontradaException(String msg) {
        super(msg);
    }
}
