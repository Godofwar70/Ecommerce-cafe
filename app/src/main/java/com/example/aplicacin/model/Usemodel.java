package com.example.aplicacin.model;

public class Usemodel {

    String apellido_t;
    int cedula_t;
    String ciudad;
    String correo_t;
    String direccion_t;
    String genero;
    String nombre_t;
    String pass_t;
    String provincia;
    int telefono_t;

    public Usemodel() {
    }

    public Usemodel(String apellido_t, int cedula_t, String ciudad, String correo_t, String direccion_t, String genero, String nombre_t, String pass_t, String provincia, int telefono_t) {
        this.apellido_t = apellido_t;
        this.cedula_t = cedula_t;
        this.ciudad = ciudad;
        this.correo_t = correo_t;
        this.direccion_t = direccion_t;
        this.genero = genero;
        this.nombre_t = nombre_t;
        this.pass_t = pass_t;
        this.provincia = provincia;
        this.telefono_t = telefono_t;
    }

    public String getApellido_t() {
        return apellido_t;
    }

    public void setApellido_t(String apellido_t) {
        this.apellido_t = apellido_t;
    }

    public int getCedula_t() {
        return cedula_t;
    }

    public void setCedula_t(int cedula_t) {
        this.cedula_t = cedula_t;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo_t() {
        return correo_t;
    }

    public void setCorreo_t(String correo_t) {
        this.correo_t = correo_t;
    }

    public String getDireccion_t() {
        return direccion_t;
    }

    public void setDireccion_t(String direccion_t) {
        this.direccion_t = direccion_t;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre_t() {
        return nombre_t;
    }

    public void setNombre_t(String nombre_t) {
        this.nombre_t = nombre_t;
    }

    public String getPass_t() {
        return pass_t;
    }

    public void setPass_t(String pass_t) {
        this.pass_t = pass_t;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getTelefono_t() {
        return telefono_t;
    }

    public void setTelefono_t(int telefono_t) {
        this.telefono_t = telefono_t;
    }
}
