package com.sofka.bingo.utility;

/**
 * Clase para el manejo de las respuestas de las API
 *
 * @version 1.0.0 2022-03-20
 * @author  Bruno Alejandro Ortiz Velasquez <baov1995@gmail.com>
 * @since 1.0.0
 */
public class Response {
    /**
     * Indica de si existe un error o no en la respuesta del API
     */
    public Boolean error;

    /**
     * Mensaje del API cuando es utilizada
     */
    public String message;

    /**
     * Información del API cuando es necesario
     */
    public Object data;

    /**
     * Constructor de la clase
     *
     * @author Bruno Alejandro Ortiz Velasquez <baov1995@gmail.com>
     * @since 1.0.0
     */
    public Response() {
        error = false;
        message = "";
        data = null;
    }

    /**
     * Restaura a ceros la respuesta del API
     *
     * @author Bruno Alejandro Ortiz Velasquez <baov1995@gmail.com>
     * @since 1.0.0
     */
    public void restart() {
        error = false;
        message = "";
        data = null;
    }
}
