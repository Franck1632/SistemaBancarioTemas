package Model;

/**
 * Clase abstracta que representa una cuanta bancaria
 * 
 * @author Franckarlos Barbosa
 * @version 1.0
 */
public abstract class CuentaBancaria {

    protected double saldo;
    protected int numeroCuenta;
    protected String titular;

    /**
     * Constructor que inicializa los datos básicos de la cuenta bancaria.
     * 
     * @param saldo        Saldo inicial de la cuenta.
     * @param numeroCuenta Número único asociado a la cuenta.
     * @param titular      Nombre del titular de la cuenta.
     */
    public CuentaBancaria(double saldo, int numeroCuenta, String titular) {
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
    }

    /**
     * Muestra en consola la información básica de la cuenta bancaria,
     * incluyendo número de cuenta, titular y saldo actual.
     */
    public void mostrarDatos() {
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: " + saldo);
    }

    /**
     * Deposita una cantidad de dinero en la cuenta.
     *
     * @param montoDepositar Monto a consignar.
     * @throws IllegalArgumentException Si el monto es menor o igual a cero.
     */
    public void consignar(double montoDepositar) throws IllegalArgumentException {
        if (montoDepositar <= 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        saldo += montoDepositar;
    }

    /**
     * Retira una cantidad de dinero de la cuenta.
     *
     * @param montoRetirar Monto a retirar.
     * @throws Model.Excepciones.SaldoInsuficienteException
     *                                                      Si el saldo no es
     *                                                      suficiente para realizar
     *                                                      el retiro.
     * @throws IllegalArgumentException
     *                                                      Si el monto es inválido
     *                                                      (menor o igual a cero).
     */
    public abstract void retirar(double montoRetirar)
            throws Model.Excepciones.SaldoInsuficienteException, IllegalArgumentException;

    /**
     * Calcula y aplica el interés correspondiente al tipo de cuenta.
     */
    public abstract void calcularInteres();

    /**
     * Devuelve una representación en texto de la cuenta bancaria.
     *
     * @return Cadena con los datos principales de la cuenta.
     */
    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "saldo=" + saldo +
                ", numeroCuenta=" + numeroCuenta +
                ", titular='" + titular + '\'' +
                '}';
    }

    /**
     * Crea y devuelve una copia de la cuenta bancaria.
     *
     * @return Una nueva instancia clonada de la cuenta bancaria.
     * @throws CloneNotSupportedException Si ocurre un error al clonar.
     */
    @Override
    public abstract CuentaBancaria clone() throws CloneNotSupportedException;

    public double getSaldo() {
        return saldo;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

}
