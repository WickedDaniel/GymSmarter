/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package Modelo.Excepciones;

/**
 *
 * @author WickedDaniel
 */
public class AccesoDenegadoException extends GymSmarterException {

    /**
     * Creates a new instance of <code>AccesoDenegadoException</code> without detail message.
     */
    public AccesoDenegadoException() {
    }


    /**
     * Constructs an instance of <code>AccesoDenegadoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public AccesoDenegadoException(String msg) {
        super(msg);
    }
}
