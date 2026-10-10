/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Daniel Alejandro
 */
public class CuentaCorriente extends Cuenta{
    private double cupoSobregiro;
    
    public CuentaCorriente (String numero, String nombreDueño, Fecha fechaApertura, double saldoInicial, double cupoSobregiro){
        super(numero, nombreDueño, fechaApertura, saldoInicial);
        this.cupoSobregiro = cupoSobregiro;
    }
    
    public CuentaCorriente (String numero, String nombreDueño, Fecha fechaApertura, double saldoInicial){
        this(numero, nombreDueño, fechaApertura, saldoInicial, 50000.0);
    }

    public double getCupoSobregiro() {
        return cupoSobregiro;
    }

    public void setCupoSobregiro(double cupoSobregiro) {
        this.cupoSobregiro = cupoSobregiro;
    }
    
   @Override
   public boolean retirar (double valor){
       if (valor > 0 && valor <=(getSaldo()+this.cupoSobregiro)){
           setSaldo(getSaldo()-valor);
           return true;
       }
       return false;
   }
   
   @Override 
   public String obtenerDatos(){
       return "[Corriente] " + super.obtenerDatos()+ "| Sobregiro Max: " + cupoSobregiro;
   }
}
