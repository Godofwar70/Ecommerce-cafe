package com.example.aplicacin.model;

public class category {

    String Descripcion;
    String Descuento;
    float  Estrella;
    String Producto;
    String image_Url;
    String type;

    public category() {
    }

    public category(String descripcion, String descuento, float estrella, String producto, String image_Url, String type) {
        Descripcion = descripcion;
        Descuento = descuento;
        Estrella = estrella;
        Producto = producto;
        this.image_Url = image_Url;
        this.type = type;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getDescuento() {
        return Descuento;
    }

    public void setDescuento(String descuento) {
        Descuento = descuento;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
