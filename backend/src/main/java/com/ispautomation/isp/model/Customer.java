package com.ispautomation.isp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/*
 * Entity → tells Spring this class = database table
 */
@Entity
public class Customer {

    /*
     * Primary key
     * Auto-increment ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*
     * Customer name
     */
    private String name;

    /*
     * Customer email
     */
    private String email;

    /*
     * Customer phone
     */
    private String phone;

    // ===== Constructors =====

    public Customer() {
    }

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // ===== Getters & Setters =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}