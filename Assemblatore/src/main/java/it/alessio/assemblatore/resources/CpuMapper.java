/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 *
 * @author aless
 */
@DynamoDBTable(tableName = "CpuTableTest")
public class CpuMapper {
    private String idCpu;
    private double price;
    private String brandCpu;
    private double frequency;
    private String uuid;
    
    @DynamoDBHashKey(attributeName = "Uuid")
    @DynamoDBAutoGeneratedKey
    public String getUuid() {return uuid;}
    public void setUuid(String uuid) {this.uuid = uuid;}
    
    @DynamoDBAttribute(attributeName = "IdCpu")
    public String getIdCpu() {return idCpu;}
    public void setIdCpu(String idCpu) {this.idCpu = idCpu;}

    @DynamoDBAttribute(attributeName = "Price")
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    @DynamoDBAttribute(attributeName = "BrandCpu")
    public String getBrandCpu() {return brandCpu;}
    public void setBrandCpu(String brandCpu) {this.brandCpu = brandCpu;}

    @DynamoDBAttribute(attributeName = "Frequency")
    public double getFrequency() {return frequency;}
    public void setFrequency(double frequency) {this.frequency = frequency;}
    
}
