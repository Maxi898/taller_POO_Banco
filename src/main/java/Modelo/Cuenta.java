/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Daniel Alejandro
 */
public abstract class Cuenta {
    private String numero;
    private String nombreDueño;
    private Fecha fechaApertura;
    private double saldo;
    
    public Cuenta (String numero, String nombreDueño, Fecha fechaApertura, double saldoInicial){
        this.numero=numero;
        this.nombreDueño=nombreDueño;
        this.fechaApertura = fechaApertura;
        this.saldo = saldoInicial;
    }
    
    public Cuenta (String numero, String nombreDueño, Fecha fechaApertura){
        this(numero, nombreDueño, fechaApertura, 0.0);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombreDueño() {
        return nombreDueño;
    }

    public void setNombreDueño(String nombreDueño) {
        this.nombreDueño = nombreDueño;
    }

    public Fecha getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Fecha fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public boolean consignar (double valor) {
        if (valor > 0){
            this.saldo +=valor;
            return true;
        }
        return false;
    }
    
    public abstract boolean retirar (double valor);
      
    
    
    public String obtenerDatos(){
        return "Cuenta #:" + numero + "| Dueño: " + nombreDueño + "| Apertura: " + fechaApertura.toString() + "| Saldo: " + saldo;
    }
    
    public String obtenerDatos (boolean soloSaldo) { 
        if (soloSaldo){
            
            return "Cuenta #" + numero + "| Saldo: " + saldo;
        }
        return obtenerDatos();
    }
}
