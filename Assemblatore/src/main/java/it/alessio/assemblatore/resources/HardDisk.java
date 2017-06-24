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
public class HardDisk {
    private String idHD;
    private double price;
    private String brandHD;
    private int size;
    
    public HardDisk()
    {    
    }
    
    public HardDisk(String idHD, double price, String brandHD, int size) {
        this.idHD = idHD;
        this.price = price;
        this.brandHD = brandHD;
        this.size = size;
    }

    public HardDisk(String idHD, String brandHD) {
        this.idHD = idHD;
        this.brandHD = brandHD;
    }

    public HardDisk(String idHD, int size) {
        this.idHD = idHD;
        this.size = size;
    }

    public HardDisk(String idHD, double price) {
        this.idHD = idHD;
        this.price = price;
    }
    
    
    //Getters and setters
    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrandHD() {
        return brandHD;
    }

    public void setBrandHD(String brandHD) {
        this.brandHD = brandHD;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    @Override
    public String toString() {
        return "HardDisk{" + "idHD=" + idHD + ", price=" + price + ", brandHD=" + brandHD + ", size=" + size + '}';
    }
    
}
