package Model.Excepciones;

/**
 * Excepción personalizada que se lanza cuando se intenta realizar una opcion
 * invalida en una cuenta bancaria.
 * 
 * @author Franckarlos Barbosa
 * @version 1.0
 */
public class OperacionInvalidaException extends Exception {
    /**
     * Crea una nueva excepción con un mensaje descriptivo.
     *
     * @param mensaje Descripción del motivo por el cual la operación es inválida.
     */

    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }

}
