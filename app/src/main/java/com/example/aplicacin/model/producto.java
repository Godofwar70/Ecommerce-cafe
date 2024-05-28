package com.example.aplicacin.model;

import java.io.Serializable;

public class producto implements Serializable {

    String Descripcion;
    float Estrella;
    String Producto;
    String image_Url;
    float price;
    String type;

    public producto() {
    }

    public producto(String descripcion, float estrella, String producto, String image_Url, float price, String type) {
        Descripcion = descripcion;
        Estrella = estrella;
        Producto = producto;
        this.image_Url = image_Url;
        this.price = price;
        this.type = type;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public float getEstrella() {
        return Estrella;
    }

    public void setEstrella(float estrella) {
        Estrella = estrella;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public String getImage_Url() {
        return image_Url;
    }

    public void setImage_Url(String image_Url) {
        this.image_Url = image_Url;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
