/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package Modelo.Excepciones;

/**
 *
 * @author WickedDaniel
 */
public class GymSmarterException extends Exception {

    /**
     * Creates a new instance of <code>GymSmarterException</code> without detail message.
     */
    public GymSmarterException() {
    }


    /**
     * Constructs an instance of <code>GymSmarterException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public GymSmarterException(String msg) {
        super(msg);
    }
}
