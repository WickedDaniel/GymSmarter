/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package Modelo.Excepciones;

/**
 *
 * @author WickedDaniel
 */
public class UsuarioYaExisteException extends GymSmarterException {

    /**
     * Creates a new instance of <code>UsuarioYaExisteException</code> without detail message.
     */
    public UsuarioYaExisteException() {
    }


    /**
     * Constructs an instance of <code>UsuarioYaExisteException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UsuarioYaExisteException(String msg) {
        super(msg);
    }
}
