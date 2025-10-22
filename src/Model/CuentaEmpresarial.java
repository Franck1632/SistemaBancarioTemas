package Model;

/**
 * Representa una cuenta empresarial dentro del sistema bancario.
 * Este tipo de cuenta permite realizar un número limitado de retiros
 * y no genera intereses adicionales. Cada retiro descuenta del saldo
 * y además disminuye el contador de retiros disponibles.
 * 
 * @author
 * @version 1.0
 */
public class CuentaEmpresarial extends CuentaBancaria {
    private int numRetiros;

    /**
     * Constructor que inicializa una cuenta empresarial con los datos básicos
     * y el número máximo de retiros disponibles.
     *
     * @param saldo        Saldo inicial de la cuenta empresarial.
     * @param numeroCuenta Número único asociado a la cuenta.
     * @param titular      Nombre del titular de la cuenta (empresa o
     *                     representante).
     * @param numRetiros   Cantidad máxima de retiros permitidos.
     */
    public CuentaEmpresarial(double saldo, int numeroCuenta, String titular, int numRetiros) {
        super(saldo, numeroCuenta, titular);
        this.numRetiros = numRetiros;
    }

    /**
     * Retira una cantidad de dinero de la cuenta empresarial,
     * siempre que el monto sea válido, exista saldo suficiente
     * y queden retiros disponibles.
     *
     * @param montoRetirar Monto que se desea retirar.
     * @throws Model.Excepciones.SaldoInsuficienteException
     *                                                      Si el saldo es
     *                                                      insuficiente para cubrir
     *                                                      el retiro.
     * @throws IllegalArgumentException
     *                                                      Si el monto es inválido
     *                                                      (menor o igual a cero).
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
        numRetiros--;
    }

    /**
     * Método para el cálculo de intereses en la cuenta empresarial.
     * En este tipo de cuenta no se genera interés, por lo que no realiza cambios.
     */

    @Override
    public void calcularInteres() {

    }

    public int getNumRetiros() {
        return numRetiros;
    }

    /**
     * Crea y devuelve una copia de la cuenta empresarial actual.
     *
     * @return Una nueva instancia de CuentaEmpresarial con los mismos valores.
     * @throws CloneNotSupportedException Si ocurre un error al clonar.
     */
    @Override
    public CuentaEmpresarial clone() throws CloneNotSupportedException {
        return new CuentaEmpresarial(saldo, numeroCuenta, titular, numRetiros);
    }

}
