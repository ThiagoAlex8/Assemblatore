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
public class Ram {
    private String idRam;
    private double price;
    private String brandRam;
    private int size;

    public Ram()
    {
    }
    
    public Ram(String idRam) {
        this.idRam = idRam;
    }

    public Ram(String idRam, double price, String brandRam, int size) {
        this.idRam = idRam;
        this.price = price;
        this.brandRam = brandRam;
        this.size = size;
    }

    public Ram(String idRam, double price) {
        this.idRam = idRam;
        this.price = price;
    }

    public Ram(String idRam, String brandRam) {
        this.idRam = idRam;
        this.brandRam = brandRam;
    }
    

    //Getters and setters
    public String getIdRam() {
        return idRam;
    }

    public void setIdRam(String idRam) {
        this.idRam = idRam;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrandRam() {
        return brandRam;
    }

    public void setBrandRam(String brandRam) {
        this.brandRam = brandRam;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Ram{" + "idRam=" + idRam + ", price=" + price + ", brandRam=" + brandRam + ", size=" + size + '}';
    }
    
    
    
}
