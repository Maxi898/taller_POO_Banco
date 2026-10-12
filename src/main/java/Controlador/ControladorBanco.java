package controlador;

import Modelo.Cuenta;
import Modelo.CuentaAhorros;
import Modelo.CuentaCorriente;
import Modelo.Fecha;

/**
 * Controlador: maneja las cuentas del banco y las operaciones que
 * pide la vista (crear, consignar, retirar y consultar).
 * Cada método devuelve un mensaje para que la vista lo muestre.
 */
public class ControladorBanco {

    private Cuenta[] cuentas;
    private int cantidad;

    public ControladorBanco() {
        this.cuentas = new Cuenta[50];
        this.cantidad = 0;
    }

    public Cuenta buscarCuenta(String numero) {
        for (int i = 0; i < cantidad; i++) {
            if (cuentas[i].getNumero().equals(numero)) {
                return cuentas[i];
            }
        }
        return null;
    }

    private String agregarCuenta(Cuenta nueva) {
        if (buscarCuenta(nueva.getNumero()) != null) {
            return "Ya existe una cuenta con el número " + nueva.getNumero();
        }
        if (cantidad >= cuentas.length) {
            return "No hay espacio para más cuentas.";
        }
        cuentas[cantidad] = nueva;
        cantidad++;
        return "Cuenta creada:\n" + nueva.obtenerDatos();
    }

    // ---- Crear cuentas (usan los constructores sobrecargados del modelo) ----

    // Ahorros sin saldo inicial: queda en 0
    public String crearCuentaAhorros(String numero, String dueno, int dia, int mes, int anio) {
        Fecha fecha = new Fecha(dia, mes, anio);
        return agregarCuenta(new CuentaAhorros(numero, dueno, fecha));
    }

    // Ahorros con saldo inicial
    public String crearCuentaAhorros(String numero, String dueno, int dia, int mes, int anio, double saldo) {
        Fecha fecha = new Fecha(dia, mes, anio);
        return agregarCuenta(new CuentaAhorros(numero, dueno, fecha, saldo));
    }

    // Corriente con el cupo de sobregiro por defecto (50000)
    public String crearCuentaCorriente(String numero, String dueno, int dia, int mes, int anio, double saldo) {
        Fecha fecha = new Fecha(dia, mes, anio);
        return agregarCuenta(new CuentaCorriente(numero, dueno, fecha, saldo));
    }

    // Corriente con un cupo de sobregiro escogido
    public String crearCuentaCorriente(String numero, String dueno, int dia, int mes, int anio, double saldo, double cupo) {
        Fecha fecha = new Fecha(dia, mes, anio);
        return agregarCuenta(new CuentaCorriente(numero, dueno, fecha, saldo, cupo));
    }

    // ---- Operaciones ----

    public String consignar(String numero, double valor) {
        Cuenta c = buscarCuenta(numero);
        if (c == null) {
            return "No existe la cuenta " + numero;
        }
        if (c.consignar(valor)) {
            return "Consignación exitosa.\n" + c.obtenerDatos(true);
        } else {
            return "El valor a consignar debe ser mayor que 0.";
        }
    }

    // Polimorfismo: retirar() funciona distinto si es Ahorros o Corriente
    public String retirar(String numero, double valor) {
        Cuenta c = buscarCuenta(numero);
        if (c == null) {
            return "No existe la cuenta " + numero;
        }
        if (c.retirar(valor)) {
            return "Retiro exitoso.\n" + c.obtenerDatos(true);
        } else {
            return "Retiro rechazado: fondos insuficientes o valor no válido.\n" + c.obtenerDatos(true);
        }
    }

    // Sobrecarga: obtenerDatos(true) = solo saldo
    public String consultarSaldo(String numero) {
        Cuenta c = buscarCuenta(numero);
        if (c == null) {
            return "No existe la cuenta " + numero;
        }
        return c.obtenerDatos(true);
    }

    // Sobrecarga: obtenerDatos() = todos los datos
    public String consultarDatos(String numero) {
        Cuenta c = buscarCuenta(numero);
        if (c == null) {
            return "No existe la cuenta " + numero;
        }
        return c.obtenerDatos();
    }

    public String listarCuentas() {
        if (cantidad == 0) {
            return "No hay cuentas registradas.";
        }
        String lista = "";
        for (int i = 0; i < cantidad; i++) {
            lista += cuentas[i].obtenerDatos() + "\n";
        }
        return lista;
    }
}