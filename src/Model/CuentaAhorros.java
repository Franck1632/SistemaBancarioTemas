package Model;

/**
 * Representa una cuenta de ahorros dentro del sistema bancario.
 * Esta cuenta genera intereses y no permite realizar retiros
 * superiores al saldo disponible.
 * 
 * @author Franckarlos Barbosa
 * @version 1.0
 */

public class CuentaAhorros extends CuentaBancaria {

    /**
     * Constructor para crear una cuenta de ahorros.
     *
     * @param saldo        Saldo inicial de la cuenta.
     * @param numeroCuenta Número único asociado a la cuenta.
     * @param titular      Nombre del titular de la cuenta.
     */
    public CuentaAhorros(double saldo, int numeroCuenta, String titular) {
        super(saldo, numeroCuenta, titular);

    }

    /**
     * Retira dinero de la cuenta de ahorros, siempre y cuando
     * el monto sea válido y no exceda el saldo disponible.
     *
     * @param montoRetirar Monto que se desea retirar.
     * @throws Model.Excepciones.SaldoInsuficienteException
     *                                                      Si el saldo es
     *                                                      insuficiente para
     *                                                      realizar el retiro.
     * @throws IllegalArgumentException
     *                                                      Si el monto es menor o
     *                                                      igual a cero.
     */
    @Override
    public void retirar(double montoRetirar)
            throws Model.Excepciones.SaldoInsuficienteException, IllegalArgumentException {
        if (montoRetirar <= 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        if (saldo - montoRetirar < 0) {
            throw new Model.Excepciones.SaldoInsuficienteException("Salgo isuficiente para realizar la operacion");
        }
        saldo -= montoRetirar;
    }

    /**
     * Calcula y aplica el interés correspondiente a la cuenta de ahorros.
     * En este caso, el interés es del 10% sobre el saldo actual.
     */
    @Override
    public void calcularInteres() {
        saldo = saldo + saldo * 0.1;
    }

    /**
     * Crea una copia de la cuenta de ahorros.
     *
     * @return Una nueva instancia de CuentaAhorros con los mismos valores.
     * @throws CloneNotSupportedException Si ocurre un error al clonar.
     */
    @Override
    public CuentaAhorros clone() throws CloneNotSupportedException {
        return new CuentaAhorros(saldo, numeroCuenta, titular);
    }

}
