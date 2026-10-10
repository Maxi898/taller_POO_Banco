/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Daniel Alejandro
 */
public class CuentaAhorros extends Cuenta {
    public CuentaAhorros (String numero, String nombreDueño, Fecha fechaApertura, double saldoInicial){
        super (numero, nombreDueño, fechaApertura, saldoInicial);
    }
    
    public CuentaAhorros (String numero, String nombreDueño, Fecha fechaApertura){
        super (numero, nombreDueño, fechaApertura);
    }
    
    @Override
    public boolean retirar (double valor){
        if (valor>0 && valor <=getSaldo()){
            setSaldo(getSaldo()-valor);
            return true;
        }
        return false;
    }
    @Override
    public String obtenerDatos(){
        return "[Ahorros] " + super.obtenerDatos();
    }
 
}

