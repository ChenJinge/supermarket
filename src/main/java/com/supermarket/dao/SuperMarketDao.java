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

    public void insertCommodity(Commodity commodity){
        String insert_sql = "insert tb_commodity values(?,?,?,?,?,?)";
        Object[] insert_paramValue = {commodity.getId(),commodity.getName(),commodity.getSpecification(),commodity.getUnits(),commodity.getPrice(),commodity.getStock()};
        super.update(insert_sql,insert_paramValue);
    }

    public void updateCommodity(Commodity commodity){
        String update_sql = "update tb_commodity set name = ?,specification = ?,units = ?,price = ?,stock = ?";
        Object[] update_paramValue = {commodity.getName(),commodity.getSpecification(),commodity.getUnits(),commodity.getPrice(),commodity.getStock()};
        super.update(update_sql,update_paramValue);
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

    public void insertMember(Member member){
        String sql = "insert tb_member values(?,?,?,?,?,?,?)";
        Object[] insert_paramValue = {member.getId(),member.getName(),member.getPhone(),member.getTotal(),member.getRegisterTime(),member.getRegisterTime(),member.getUpdateTime()};
        super.update(sql,insert_paramValue);
    }
    public Member getMember(int memberId) {
        String sql = "select * from tb_member where id = ?";
        Object[] paramValue = {memberId};
        List<Member> members = super.query(sql, paramValue, Member.class);

        if (members.size() > 0) {
            return members.get(0);
        }
        return null;
    }

    public List<Member> getMembers() {
        String sql = "select * from tb_member";
        Object[] paramValue = {};
        List<Member> members = super.query(sql, paramValue, Member.class);
        return members;
    }

    public void insertOrder(Order order) {
        String sql = "insert tb_order values(?,?,?,?,?,?,?)";
        Object[] paramValue = {order.getId(),order.getOrderNumber(),order.getSum(),order.getUserId(),order.getMemberId(),order.getCheckoutType(),order.getCheckoutTime()};
        super.update(sql,paramValue);
    }

    public void insertOrderItem(OrderItem orderItem) {
        String sql = "insert tb_order_item values(?,?,?,?,?,?,?,?)";
        Object[] paramValue = {orderItem.getId(),orderItem.getOrderNumber(),orderItem.getCommodityId(),orderItem.getCommodityName(),orderItem.getPrice(),orderItem.getIsChecked(),orderItem.getIsChecked()};
        super.update(sql,paramValue);
    }

    public Order getOrder(int orderId) {
        return null;
    }

    public List<OrderItem> getOrderItems(int orderId) {
        return null;
    }
}
