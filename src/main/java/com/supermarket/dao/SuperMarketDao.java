package com.supermarket.dao;


import com.supermarket.bean.*;
import com.supermarket.pojo.CommodityVO;
import com.supermarket.util.JDBCUtil;

import java.util.List;

public class SuperMarketDao extends BaseDao {

    //-------------------用户管理-----------------------
    public User getUser(String username, String password) {
        String sql = "select * from tb_user where username = ? and password = ?";
        Object[] paramValue = {username, password};
        List<User> users = super.query(sql, paramValue, User.class);

        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public void addCustomizeSession(CustomizeSession customizeSession){
        String sql ="insert into tb_customize_session(kfc,create_time) values(?,?)";
        Object[] insert_paramValue = {customizeSession.getKfc(),customizeSession.getCreateTime()};
        super.update(sql,insert_paramValue);
    }

    public void updateCustomizeSession(CustomizeSession customizeSession){
        String update_sql = "update tb_customize_session set create_time = ? where id = ?";
        Object[] update_paramValue = {customizeSession.getCreateTime(),customizeSession.getId()};
        super.update(update_sql,update_paramValue);
    }

    public CustomizeSession getCustomizeSession(String kfc){
        //创建时间>当前时间-过期时间
        String sql = "select * from tb_customize_session where kfc = ? and create_time> ?";
        Object[] paramValue = {kfc,System.currentTimeMillis()-JDBCUtil.expireTime};
        List<CustomizeSession> CustomizeSessions = super.query(sql, paramValue, CustomizeSession.class);

        if (CustomizeSessions.size() > 0) {
            return CustomizeSessions.get(0);
        }
        return null;
    }

    //-------------------进货管理-----------------------
    public void insertCommodity(Commodity commodity){
        String insert_sql = "insert into tb_commodity(name,specification,units,price,memberprice,stock) values(?,?,?,?,?,?)";
        Object[] insert_paramValue = {commodity.getName(),commodity.getSpecification(),commodity.getUnits(),commodity.getPrice(),commodity.getMemberprice(),commodity.getStock()};
        super.update(insert_sql,insert_paramValue);
    }

    public void updateCommodity(Commodity commodity){
        String update_sql = "update tb_commodity set name = ?,specification = ?,units = ?,price = ?,memberprice=?,stock = stock + ? where id = ?";
        Object[] update_paramValue = {commodity.getName(),commodity.getSpecification(),commodity.getUnits(),commodity.getPrice(),commodity.getMemberprice(),commodity.getStock(),commodity.getId()};
        super.update(update_sql,update_paramValue);
    }

    public void reduceStock(long id,int stock){
        String update_sql = "update tb_commodity set stock = stock - ? where id = ?";
        Object[] update_paramValue = {stock,id};
        super.update(update_sql,update_paramValue);
    }

    public void addStock(long id,int stock){
        String update_sql = "update tb_commodity set stock = stock + ? where id = ?";
        Object[] update_paramValue = {stock,id};
        super.update(update_sql,update_paramValue);
    }
    public Commodity getCommodity(long commodityId) {
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

    //-------------------会员管理-----------------------
    public void insertMember(Member member){
        String sql = "insert into tb_member values(?,?,?,?,?,?,?)";
        Object[] insert_paramValue = {member.getId(),member.getName(),member.getPhone(),member.getPoints(),member.getTotal(),member.getRegisterTime(),member.getUpdateTime()};
        super.update(sql,insert_paramValue);
    }
    public Member getMember(long memberId) {
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

    //-------------------结账管理-----------------------
    public void insertOrder(Order order) {
        String sql = "insert tb_order values(?,?,?,?,?,?,?)";
        Object[] paramValue = {order.getId(),order.getOrderNumber(),order.getSum(),order.getUserId(),order.getMemberId(),order.getCheckoutType(),order.getCheckoutTime()};
        super.update(sql,paramValue);
    }

    public Order getOrder(int orderId) {
        return null;
    }

    public List<OrderItem> getOrderItemsByOrderId(long orderId) {
        String sql = "select * from tb_order_item where order_id =?";
        Object[] paramValue = {orderId};
        final List<OrderItem> orderItems = super.query(sql, paramValue, OrderItem.class);
        return orderItems;
    }

    //----------------------添加删除商品----------------------
    public void insertOrderItem(OrderItem orderItem) {
        String sql = "insert into tb_order_item(order_id,shopping_number,commodity_id,commodity_name,price,count,total,is_checked) values(?,?,?,?,?,?,?,?)";
        Object[] paramValue = {orderItem.getOrderId(),orderItem.getShoppingNumber(),
                orderItem.getCommodityId(),orderItem.getCommodityName(),orderItem.getPrice(),
                orderItem.getCount(),orderItem.getTotal(),orderItem.getIsChecked()
        };
        super.update(sql,paramValue);
    }

    public OrderItem getOrderItemBySNandCId(long shoppingNumber,long commodityId){
        String sql = "select * from tb_order_item where shopping_number =? and commodity_id=? and is_checked=0";
        Object[] paramValue = {shoppingNumber,commodityId};
        final List<OrderItem> orderItems = super.query(sql, paramValue, OrderItem.class);
        if (orderItems.size()>0){
            return orderItems.get(0);
        }
        return null;
    }

    public void addOrderItem(OrderItem orderItem){
        String sql ="update tb_order_item set count=count+?,total=total+? where shopping_number=? and commodity_id=?";
        Object[] paramValues = {
                orderItem.getCount(),orderItem.getTotal(),orderItem.getShoppingNumber(),orderItem.getCommodityId()
        };
        super.update(sql,paramValues);
    }

    public List<CommodityVO> getOrderItemsBySN(long shoppingNumber){
        String sql = "select c.id,c.name,c.specification,c.units,c.price,c.memberprice,c.stock,i.count,i.total " +
                "from tb_commodity c " +
                "inner join tb_order_item i " +
                "on c.id = i.commodity_id " +
                "where i.shopping_number =?";
        Object[] paramValue = {shoppingNumber};
        final List<CommodityVO> orderItems = super.query(sql, paramValue, CommodityVO.class);
        return orderItems;
    }

    public void reduceOrderItem(OrderItem orderItem,int count){
        String sql ="update tb_order_item set count=count-?,total=total-? where shopping_number=? and commodity_id=? and is_checked=0";
        Object[] paramValues = {
                count,orderItem.getTotal(),orderItem.getShoppingNumber(),orderItem.getCommodityId()
        };
        super.update(sql,paramValues);
    }

    public void removeOrderItem(OrderItem orderItem){
        String sql ="delete from tb_order_item where shopping_number=? and commodity_id=? and is_checked=0";
        Object[] paramValues = {
                orderItem.getShoppingNumber(),orderItem.getCommodityId()
        };
        super.update(sql,paramValues);
    }
}
