/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

import java.util.ArrayList;

/**
 *
 * @author aless
 */
public class Budget {
    ArrayList<Cpu> arrayCpu;
    ArrayList<Ram> arrayRam;
    ArrayList<HardDisk> arrayHD;
    double totale;

    public Budget(){
        
    }
    

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }
    
    public void addCpu(Cpu cpu){
        this.arrayCpu.add(cpu);
    }
    
    public void removeCpu(Cpu cpu){
        this.arrayCpu.remove(cpu);
    }
    
    public void addRam(Ram ram){
        this.arrayRam.add(ram);
    }
    
    public void removeRam(Ram ram){
        this.arrayRam.remove(ram);
    }
    
    public void addHD(HardDisk hardDisk){
        this.arrayHD.add(hardDisk);
    }
    
    public void removeHD(HardDisk hardDisk){
        this.arrayHD.remove(hardDisk);
    }
}
