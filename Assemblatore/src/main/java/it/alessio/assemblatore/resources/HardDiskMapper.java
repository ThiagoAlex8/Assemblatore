/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 *
 * @author aless
 */
@DynamoDBTable(tableName = "HDTableTest")
public class HardDiskMapper {
    private String idHD;
    private double price;
    private String brandHD;
    private int size;
    private String uuid;

    @DynamoDBHashKey(attributeName = "Uuid")
    @DynamoDBAutoGeneratedKey
    public String getUuid() {return uuid;}
    public void setUuid(String uuid) {this.uuid = uuid;}

    @DynamoDBAttribute(attributeName = "IdHD")
    public String getIdHD() {return idHD;}
    public void setIdHD(String idHD) {this.idHD = idHD;}

    @DynamoDBAttribute(attributeName = "Price")
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    @DynamoDBAttribute(attributeName = "BrandHD")
    public String getBrandHD() {return brandHD;}
    public void setBrandHD(String brandHD) {this.brandHD = brandHD;}

    @DynamoDBAttribute(attributeName = "Size")
    public int getSize() {return size;}
    public void setSize(int size) {this.size = size;}
    
    
    
}
