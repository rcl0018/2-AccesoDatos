package com.example.pruebaHibernate;

import java.sql.Date;

public class order {
    private int id;
    private Date fecha;
    private int cantidad;
    private Double importe;
    private Customer customer;
    private Producto product;

    public order(){
    }

    public order(Date fecha, int cantidad, Double importe, Customer customer, Producto producto) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.importe = importe;
        this.customer = customer;
        this.product = producto;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Double getImporte() {
        return importe;
    }
    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Producto getProduct() {
        return product;
    }

    public void setProduct(Producto product) {
        this.product = product;
    }
   


    
}
