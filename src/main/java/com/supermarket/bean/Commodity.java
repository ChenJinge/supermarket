package com.supermarket.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品
 */
public class Commodity implements Serializable {
    private long id;
    private String name;
    private String specification;
    private String units;
    private BigDecimal price;
    private BigDecimal memberprice;
    private int stock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public BigDecimal getMemberprice() {
        return memberprice;
    }

    public void setMemberprice(BigDecimal memberprice) {
        this.memberprice = memberprice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
