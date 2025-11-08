

package com.demo.foodwebapplication.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class cart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private long cart_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user; // Each cart belongs to one user


    private float total_price;

    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<cart_item> cartItems; // One cart can have multiple items

    public cart() {
    }

    public cart(long cart_id, com.demo.foodwebapplication.model.user user, float total_price,
            List<cart_item> cartItems) {
        this.cart_id = cart_id;
        this.user = user;
        this.total_price = total_price;
        this.cartItems = cartItems;
    }

    public long getCart_id() {
        return cart_id;
    }

    public void setCart_id(long cart_id) {
        this.cart_id = cart_id;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public List<cart_item> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<cart_item> cartItems) {
        this.cartItems = cartItems;
    }







    




    



    
}
