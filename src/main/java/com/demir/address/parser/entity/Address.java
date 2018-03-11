package com.demir.address.parser.entity;

import java.io.Serializable;

/**
 * User: murat.demir
 */
public class Address implements Serializable {

    private String street;
    private String no;

    private Address() {
    }

    public static Address build() {
        return new Address();
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getNo() {
        return no;
    }

    public Address setNo(String no) {
        this.no = no;
        return this;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", no='" + no + '\'' +
                '}';
    }
}
