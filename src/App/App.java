package App;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.ControlCuentaBancaria;
import Model.CuentaBancaria;
import Model.CuentaAhorros;
import Model.CuentaCorriente;
import Model.CuentaEmpresarial;
import Model.Excepciones.OperacionInvalidaException;
import Model.Excepciones.SaldoInsuficienteException;
import Model.CuentaCsv;
import Controller.ControlCuentaBancaria;

/**
 * Clase principal del sistema bancario. Permite registrar una cuenta bancaria,
 * elegir su tipo y realizar operaciones como consignar, retirar, ver
 * información y consultar los movimientos realizados.
 * 
 * @version 1.0
 * @author Franckarlos Barbosa
 */

public class App {
    /**
     * Método principal que inicia la aplicación bancaria por consola.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     * @throws Exception Puede lanzar excepciones relacionadas con operaciones
     *                   inválidas o errores de entrada.
     */
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        CuentaBancaria cuentaBancaria = null;
        ControlCuentaBancaria control = new ControlCuentaBancaria();

        System.out.println("¡Bienvenido al Sistema Bancario!");
        System.out.println("Ingrese los siguientes datos.");

        int numeroCuenta = 0;
        boolean numeroCuentaValido = false;
        while (!numeroCuentaValido) {
            System.out.println("Numero de cuenta: ");
            try {
                numeroCuenta = scanner.nextInt();
                numeroCuentaValido = true;
            } catch (Exception e) {
                System.out.println("El numero de cuenta no es valido. Inténtelo de nuevo.");
                scanner.nextLine(); // limpia el buffer
            }
        }

        String titular = null;
        boolean titularValido = false;
        scanner.nextLine(); // limpia el buffer
        while (!titularValido) {
            System.out.println("Ingrese el nombre del titular: ");
            try {
                titular = scanner.nextLine();
                titularValido = true;
            } catch (Exception e) {
                System.out.println("El nombre del titular no es valido. Inténtelo de nuevo.");
                scanner.nextLine(); // limpia el buffer
            }
        }

        double saldo = 0;
        boolean saldoValido = false;
        while (!saldoValido) {
            System.out.println("Ingrese el saldo inicial: ");
            try {
                saldo = scanner.nextDouble();
                saldoValido = true;
            } catch (Exception e) {
                System.out.println("El saldo no es valido. Inténtelo de nuevo.");
                scanner.nextLine(); // limpia el buffer
            }
        }
        System.out.println("================================================");
        System.out.println("\nDatos registrados correctamente:");
        System.out.println("Cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: " + saldo);
        System.out.println("================================================");

        String tipoCuenta = null;

        do {
            System.out.println("Ingrese el tipo de cuenta");
            System.out.println("1. Cuenta de ahorros.");
            System.out.println("2. Cuenta Corriente.");
            System.out.println("3. Cuenta Empresarial.");
            tipoCuenta = scanner.next();

            switch (tipoCuenta) {
                case "1":
                    System.out.println("Ha seleccionado una cuenta de ahorros.");
                    cuentaBancaria = new CuentaAhorros(saldo, numeroCuenta, titular);
                    break;
                case "2":
                    System.out.println("Ha seleccionado una cuenta corriente.");
                    cuentaBancaria = new CuentaCorriente(saldo, numeroCuenta, titular);
                    break;
                case "3":
                    System.out.println("Ha seleccionado una cuenta empresarial.");
                    cuentaBancaria = new CuentaEmpresarial(saldo, numeroCuenta, titular, 5);
                    break;
                default:
                    System.out.println("Tipo de cuenta no valido.");
                    break;
            }
        } while (cuentaBancaria == null);

        int op;
        do {
            System.out.println("Que desea realizar:  ");
            System.out.println("1. Ver Informacion.");
            System.out.println("2. Depositar.");
            System.out.println("3. Retirar.");
            System.out.println("4. Ver movimientos.");
            System.out.println("0. Salir.");
            op = scanner.nextInt();

            try {
                if (op != 1 && op != 2 && op != 3 && op != 4 && op != 0) {
                    throw new OperacionInvalidaException("Opcion no valida. Intente de nuevo.");
                }
            } catch (OperacionInvalidaException e) {
                System.out.println(e.getMessage());
            }

            switch (op) {
                case 1:
                    cuentaBancaria.mostrarDatos();
                    break;
                case 2:
                    try {
                        System.out.println("Ingrese el monto a depositar: ");

                        // Validación para evitar error si el usuario escribe letras
                        if (!scanner.hasNextDouble()) {
                            System.out.println("Error: No puedes ingresar letras. Inténtelo de nuevo.");
                            scanner.next(); // limpia el buffer
                            break;
                        }

                        double montoDepositar = scanner.nextDouble();
                        cuentaBancaria.consignar(montoDepositar); // aquí puede lanzar IllegalArgumentException
                        cuentaBancaria.calcularInteres();
                        control.agregarMovimiento(cuentaBancaria);
                        System.out.println("Deposito exitoso. Nuevo saldo: " + cuentaBancaria.getSaldo());

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 3:
                    System.out.println("Ingrese el monto a retirar: ");
                    double montoRetirar = scanner.nextDouble();

                    try {
                        if (cuentaBancaria instanceof CuentaAhorros || cuentaBancaria instanceof CuentaCorriente) {
                            cuentaBancaria.retirar(montoRetirar);
                        }

                        if (cuentaBancaria instanceof CuentaEmpresarial) {
                            if (((CuentaEmpresarial) cuentaBancaria).getNumRetiros() == 0) {
                                System.out.println("No quedan retiros disponibles.");
                                break;
                            } else {
                                cuentaBancaria.retirar(montoRetirar);
                            }
                        }

                        control.agregarMovimiento(cuentaBancaria);
                        System.out.println("Retiro exitoso. Nuevo saldo: " + cuentaBancaria.getSaldo());

                    } catch (SaldoInsuficienteException e) {
                        System.out.println("Incoveniente en el saldo: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 4:
                    control.mostrarMovimientos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema");
                    break;
                default:

            }

        } while (op != 0);

    }
}
