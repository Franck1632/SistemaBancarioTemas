package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.CuentaBancaria;
import Model.CuentaCsv;

/**
 * Controlador encargado de gestionar los movimientos de una cuenta bancaria.
 * Permite almacenarlos, mostrarlos y exportarlos a un archivo CSV.
 * 
 * @author Franckarlos Barbosa
 *  @version 1.0
 */
public class ControlCuentaBancaria {

    /** Lista que almacena los movimientos realizados por la cuenta. */
    private List<CuentaBancaria> movimientos = new ArrayList<>();
    /** Objeto encargado de generar el archivo CSV con los movimientos. */
    private CuentaCsv cuentaCsv = new CuentaCsv(movimientos);

    /**
     * Muestra todos los movimientos registrados en consola.
     */
    public void mostrarMovimientos() {
        for (CuentaBancaria c : movimientos) {
            System.out.println(c);
        }

    }

    /**
     * Agrega un nuevo movimiento a la lista, genera una copia del estado actual de
     * la cuenta y actualiza el archivo CSV.
     *
     * @param movimiento El movimiento a registrar.
     */

    public void agregarMovimiento(CuentaBancaria movimiento) {
        CuentaBancaria mov = null;

        try {
            mov = movimiento.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        movimientos.add(mov); // aquí sí estás agregando a la lista
        cuentaCsv.setMovimientos(movimientos);
        cuentaCsv.cuentaCsv("src\\movimientos.csv");

    }

    /**
     * Devuelve la lista completa de movimientos registrados.
     *
     * @return Lista de movimientos.
     */
    public List<CuentaBancaria> getMovimientos() {
        return movimientos;
    }

}
