package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase encargada de generar un archivo CSV con la información de una lista de
 * cuentas bancarias. Cada registro almacenado en el archivo contendrá el nombre
 * del titular, el número de cuenta y el saldo actual.
 * 
 * @author Franckarlos Barbosa
 * @version 1.0
 */
public class CuentaCsv {

    /** Lista de cuentas bancarias a exportar en formato CSV */
    private List<CuentaBancaria> movimientos;

    /**
     * Constructor de la clase.
     * 
     * @param movimientos Lista de objetos que contienen la información a exportar.
     */

    public CuentaCsv(List<CuentaBancaria> movimientos) {
        this.movimientos = movimientos;
    }

    /**
     * Crea un archivo CSV con la información de las cuentas bancarias.
     *
     * @param nombreArchivo Nombre del archivo CSV a generar.
     */
    public void cuentaCsv(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {

            writer.write("Titular,Numero de Cuenta,Saldo\n");

            for (CuentaBancaria c : movimientos) {
                writer.write(c.getTitular() + "," + c.getNumeroCuenta() + "," + c.getSaldo() + "\n");
            }

            System.out.println("Archivo CSV guardado correctamente: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al guardar el CSV: " + e.getMessage());
        }
    }

    /**
     * Permite reemplazar la lista de cuentas bancarias que serán exportadas a CSV.
     * 
     * @param movimientos Nueva lista de objetos .
     */
    public void setMovimientos(List<CuentaBancaria> movimientos) {
        this.movimientos = movimientos;
    }
}
