package com.supermarket.dao;


import com.supermarket.bean.*;

import java.util.List;

public class SuperMarketDao extends BaseDao {
    public User getUser(String username, String password) {
        String sql = "select * from tb_user where username = ? and password = ?";
        Object[] paramValue = {username, password};
        List<User> users = super.query(sql, paramValue, User.class);

        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public Commodity getCommodity(int commodityId) {
        String sql = "select * from tb_commodity where id = ?";
        Object[] paramValue = {commodityId};
        List<Commodity> commodities = super.query(sql, paramValue, Commodity.class);

        if (commodities.size() > 0) {
            return commodities.get(0);
        }
        return null;
    }

    public List<Commodity> getCommodities() {
        String sql = "select * from tb_commodity";
        Object[] paramValue = {};
        List<Commodity> commodities = super.query(sql, paramValue, Commodity.class);
        return commodities;
    }

    public Member getVIP(int memberId) {
        String sql = "select * from tb_member where id = ?";
        Object[] paramValue = {memberId};
        List<Member> members = super.query(sql, paramValue, Member.class);

        if (members.size() > 0) {
            return members.get(0);
        }
        return null;
    }

    public List<Member> getVIPs() {
        String sql = "select * from tb_member";
        Object[] paramValue = {};
        List<Member> members = super.query(sql, paramValue, Member.class);
        return members;
    }

    public void insertOrder(Order order) {

    }

    public void insertOrderItem(OrderItem orderItem) {

    }

    public Order getOrder(int orderId) {
        return null;
    }

    public List<OrderItem> getOrderItems(int orderId) {
        return null;
    }
}
