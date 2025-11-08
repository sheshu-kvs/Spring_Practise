package com.demo.foodwebapplication.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;   // each order belongs to one user

    private float total_price;
    private Date order_date;

    private String status;

    public orders() {}

    public orders(Date order_date, long order_id, String status,  float total_price, user user) {
        this.order_date = order_date;
        this.order_id = order_id;
        this.status = status;
        this.total_price = total_price;
        this.user = user;
    }


    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public  float  getTotal_price() {
        return total_price;
    }

    public void setTotal_price( float  total_price) {
        this.total_price = total_price;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}