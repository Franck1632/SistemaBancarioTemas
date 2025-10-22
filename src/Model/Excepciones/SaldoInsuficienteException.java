package Model.Excepciones;
/**
 * Excepción personalizada que se lanza cuando el saldo disponible
 * no es suficiente para completar la operación solicitada.
 * 
 * @author Franckarlos Barbosa
 * @version 1.0
 */
public class SaldoInsuficienteException extends Exception {
        /**
     * Crea una nueva excepción con un mensaje descriptivo.
     *
     * @param mensaje Descripción del error.
     */

    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }

}
