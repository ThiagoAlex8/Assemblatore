/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

/**
 *
 * @author aless
 */
public class Cpu 
{
    private String idCpu;
    private double price;
    private String brandCpu;
    private double frequency;
    
    public Cpu() 
    {
    }
    
    public Cpu(String idCpu)
    {
     this.idCpu = idCpu;   
    }
    
    public Cpu(String idCpu, double price){
        this.idCpu = idCpu;
        this.price = price;
    }

    public Cpu(String idCpu, double price, String brandCpu) {
        this.idCpu = idCpu;
        this.price = price;
        this.brandCpu = brandCpu;
    }

    public Cpu(String idCpu, double price, double frequency) {
        this.idCpu = idCpu;
        this.price = price;
        this.frequency = frequency;
    }

    public Cpu(String idCpu, String brandCpu, double frequency) {
        this.idCpu = idCpu;
        this.brandCpu = brandCpu;
        this.frequency = frequency;
    }

    public Cpu(String idCpu, double price, String brandCpu, double frequency) {
        this.idCpu = idCpu;
        this.price = price;
        this.brandCpu = brandCpu;
        this.frequency = frequency;
    }


    //Getters and setters

    public String getIdCpu() {
        return idCpu;
    }

    public void setIdCpu(String idCpu) {
        this.idCpu = idCpu;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrandCpu() {
        return brandCpu;
    }

    public void setBrandCpu(String brandCpu) {
        this.brandCpu = brandCpu;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Cpu{" + "idCpu=" + idCpu + ", price=" + price + ", brandCpu=" + brandCpu + ", frequency=" + frequency + "}";
    }
    
       
}
