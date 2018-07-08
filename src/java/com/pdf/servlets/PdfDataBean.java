/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdf.servlets;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 *
 * @author  Kona
 */
@DynamoDBTable(tableName = "VIL_PDF_DATA")
public class PdfDataBean {
    
    
    private Long mobileNumber;
    
    @DynamoDBHashKey(attributeName = "MOBILE_NUMBER")
    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    private String cifNumber;
    private String custAadhaar;
    private String custName;
    private String subType;
    private String custFatherName;
    private String gender;
    //Iso 8601 string format
    private String dob;
    private String nationality;
    private String subStatus;
    private int noOfConn;
    private String simNumber;
    private String tariffPlan;
    private String imsNumber;
    @DynamoDBAttribute(attributeName="TARIFF_PLAN")
    public String getTariffPlan() {
        return tariffPlan;
    }

    public void setTariffPlan(String tariffPlan) {
        this.tariffPlan = tariffPlan;
    }
    @DynamoDBAttribute(attributeName="IMSI_NUMBER")
    public String getImsNumber() {
        return imsNumber;
    }

    public void setImsNumber(String imsNumber) {
        this.imsNumber = imsNumber;
    }
    @DynamoDBAttribute(attributeName="CIF_NUMBER")
    public String getCifNumber() {
        return cifNumber;
    }

    public void setCifNumber(String cifNumber) {
        this.cifNumber = cifNumber;
    }
    @DynamoDBAttribute(attributeName="CUST_AADHAAR")
    public String getCustAadhaar() {
        return custAadhaar;
    }

    public void setCustAadhaar(String custAadhaar) {
        this.custAadhaar = custAadhaar;
    }
    @DynamoDBAttribute(attributeName="CUST_NAME")
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
    @DynamoDBAttribute(attributeName="SUBSCRIBER_TYPE")
    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
    @DynamoDBAttribute(attributeName="CUST_FATHERNAME")
    public String getCustFatherName() {
        return custFatherName;
    }

    public void setCustFatherName(String custFatherName) {
        this.custFatherName = custFatherName;
    }
@DynamoDBAttribute(attributeName="GENDER")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
@DynamoDBAttribute(attributeName="DOB")
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
@DynamoDBAttribute(attributeName="NATIONALITY")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
@DynamoDBAttribute(attributeName="SUBSCRIBER_STATUS")
    public String getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(String subStatus) {
        this.subStatus = subStatus;
    }
@DynamoDBAttribute(attributeName="NO_OF_CONNECTIONS")
    public int getNoOfConn() {
        return noOfConn;
    }

    public void setNoOfConn(int noOfConn) {
        this.noOfConn = noOfConn;
    }
@DynamoDBAttribute(attributeName="SIM_NUMBER")
    public String getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }
    
    
}
