/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.banco;
import Modelo.*;
/**
 *
 * @author Daniel Alejandro
 */
public class Banco {
public static void main(String[] args) {
        System.out.println("=== COMPROBACIÓN DEL SISTEMA BANCARIO ===");

        // 1. Composición con Fecha
        Fecha fechaApertura = new Fecha(10, 10, 2026);

        // 2. Comprobación de CuentaAhorros (Sobrecarga de constructores y obtención de datos)
        System.out.println("\n--- 1. Prueba Cuenta de Ahorros ---");
        // Constructor completo: saldo inicial $100.000
        CuentaAhorros ahorros = new CuentaAhorros("AH-001", "Carlos Pérez", fechaApertura, 100000.0);
        
        // Sobrecarga de método: todos los datos vs solo saldo
        System.out.println("Todos los datos: " + ahorros.obtenerDatos());
        System.out.println("Solo saldo: " + ahorros.obtenerDatos(true));

        // Consignar
        ahorros.consignar(50000.0);
        System.out.println("Saldo tras consignar $50.000 (esperado: $150.000): $" + ahorros.getSaldo());

        // Polimorfismo en retiro de ahorros: retiro permitido (<= saldo)
        boolean retiroExitoso = ahorros.retirar(40000.0);
        System.out.println("Retiro de $40.000 permitido: " + retiroExitoso + " | Saldo restante: $" + ahorros.getSaldo());

        // Polimorfismo en retiro de ahorros: intento de sobregiro (debe fallar)
        boolean retiroFallido = ahorros.retirar(200000.0);
        System.out.println("Intento de retirar $200.000 en ahorros permitido (debe ser false): " + retiroFallido);


        // 3. Comprobación de CuentaCorriente (Polimorfismo con sobregiro)
        System.out.println("\n--- 2. Prueba Cuenta Corriente ---");
        // Constructor completo: saldo $50.000, cupo de sobregiro $200.000
        CuentaCorriente corriente = new CuentaCorriente("CC-999", "Ana Gómez", fechaApertura, 50000.0, 200000.0);
        System.out.println(corriente.obtenerDatos());

        // Polimorfismo: retiro que entra en sobregiro (saldo restante negativo hasta el cupo)
        // Saldo disponible total = 50.000 + 200.000 = 250.000
        boolean retiroSobregiro = corriente.retirar(120000.0);
        System.out.println("Retiro de $120.000 (usa sobregiro): " + retiroSobregiro);
        System.out.println("Saldo esperado (-$70.000): $" + corriente.getSaldo());

        // Intento de retiro que supera el límite de sobregiro
        // Saldo actual: -70.000. Máximo retiro restante permitido: 130.000
        boolean excedeCupo = corriente.retirar(150000.0);
        System.out.println("Intento de retiro que excede cupo permitido (debe ser false): " + excedeCupo);
        System.out.println("Saldo final corriente: $" + corriente.getSaldo());

        System.out.println("\n=== TODAS LAS PRUEBAS FINALIZADAS ===");
    }
}
