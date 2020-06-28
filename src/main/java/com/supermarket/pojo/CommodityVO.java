package com.supermarket.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: Jake.Chen
 * @Date: 2/2/2019 10:31
 * @Description:
 */
public class CommodityVO implements Serializable {

    private long id;
    private String name;
    private String specification;
    private String units;
    private BigDecimal price;
    private BigDecimal memberprice;
    private int stock;
    private long shoppingNumber;
    private int count;
    private BigDecimal total;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMemberprice() {
        return memberprice;
    }

    public void setMemberprice(BigDecimal memberprice) {
        this.memberprice = memberprice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public long getShoppingNumber() {
        return shoppingNumber;
    }

    public void setShoppingNumber(long shoppingNumber) {
        this.shoppingNumber = shoppingNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
