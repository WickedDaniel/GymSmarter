/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package Modelo.Excepciones;

/**
 *
 * @author WickedDaniel
 */
public class UsuarioNoEncontradoException extends GymSmarterException {

    /**
     * Creates a new instance of <code>UsuarioNoEncontradoException</code> without detail message.
     */
    public UsuarioNoEncontradoException() {
    }


    /**
     * Constructs an instance of <code>UsuarioNoEncontradoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UsuarioNoEncontradoException(String msg) {
        super(msg);
    }
}
