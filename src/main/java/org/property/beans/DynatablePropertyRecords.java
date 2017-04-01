package org.property.beans;

/**
 * Created by sowmyaparameshwara on 3/29/17.
 */
public class DynatablePropertyRecords {

    private Integer id;

    private String propertyName;

    private String propertyAddress;

    private float propertyPrice;

    private int propertySize;

    private String propertyType;

    private String propertyArea;

    private String ownerName;

    private String ownerEmail;

    public DynatablePropertyRecords(Property property){
        this.id=property.getId();
        this.propertyName=property.getPropertyName();
        this.propertyAddress=property.getPropertyAddress();
        this.propertyPrice=property.getPropertyPrice();
        this.propertySize=property.getPropertySize();
        this.propertyType=property.getPropertyType();
        this.propertyArea=property.getPropertyArea();
        this.ownerName =property.getUser().getName();
        this.ownerEmail =property.getUser().getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public float getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(float propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public int getPropertySize() {
        return propertySize;
    }

    public void setPropertySize(int propertySize) {
        this.propertySize = propertySize;
    }


    public String getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(String propertyArea) {
        this.propertyArea = propertyArea;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }


    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }


}
