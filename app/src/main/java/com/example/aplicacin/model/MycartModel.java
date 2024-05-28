package com.example.aplicacin.model;

import java.io.Serializable;

public class MycartModel implements Serializable {

    String Cantidades;
    String Totalprice;
    String currentDate;
    String currentTime;
    String productName;
    String productPrice;
    String documentId;

    public MycartModel() {
    }

    public MycartModel(String cantidades, String totalprice, String currentDate, String currentTime, String productName, String productPrice) {
        Cantidades = cantidades;
        Totalprice = totalprice;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getCantidades() {
        return Cantidades;
    }

    public void setCantidades(String cantidades) {
        Cantidades = cantidades;
    }

    public String getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(String totalprice) {
        Totalprice = totalprice;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
