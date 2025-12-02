/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package Modelo.Excepciones;

/**
 *
 * @author WickedDaniel
 */
public class DispositivoNoEncontradoException extends GymSmarterException {

    /**
     * Creates a new instance of <code>DispositivoNoEncontradoException</code> without detail message.
     */
    public DispositivoNoEncontradoException() {
    }


    /**
     * Constructs an instance of <code>DispositivoNoEncontradoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DispositivoNoEncontradoException(String msg) {
        super(msg);
    }
}
