package Model;

/**
 * Representa una cuenta corriente dentro del sistema bancario.
 * Descuenta una comisión fija por manejo de cuenta en cada cálculo de
 * intereses.
 * 
 * Permite retirar dinero siempre que el monto sea válido y el saldo
 * disponible no sea superado.
 * 
 * @author Franckarlos
 * @version 1.0
 */
public class CuentaCorriente extends CuentaBancaria {
    /**
     * Constructor que inicializa una cuenta corriente con los datos básicos.
     *
     * @param saldo        Saldo inicial de la cuenta.
     * @param numeroCuenta Número único asociado a la cuenta.
     * @param titular      Nombre del titular de la cuenta.
     */
    public CuentaCorriente(double saldo, int numeroCuenta, String titular) {
        super(saldo, numeroCuenta, titular);
    }

    /**
     * Retira una cantidad de dinero de la cuenta corriente siempre y cuando
     * el monto sea positivo y no exceda el saldo disponible.
     *
     * @param montoRetirar Monto que se desea retirar.
     * @throws Model.Excepciones.SaldoInsuficienteException
     *                                                      Si el saldo es
     *                                                      insuficiente para cubrir
     *                                                      el retiro.
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
     * Aplica el cálculo de intereses para la cuenta corriente.
     */
    @Override
    public void calcularInteres() {
        saldo -= 2000;
    }

    /**
     * Crea y devuelve una copia de la cuenta corriente.
     *
     * @return Una nueva instancia de CuentaCorriente con los mismos valores.
     * @throws CloneNotSupportedException Si ocurre un error al clonar.
     */
    @Override
    public CuentaCorriente clone() throws CloneNotSupportedException {
        return new CuentaCorriente(saldo, numeroCuenta, titular);
    }

}
