package com.supermarket.service;


import com.supermarket.bean.Commodity;
import com.supermarket.bean.Member;
import com.supermarket.bean.OrderItem;
import com.supermarket.bean.User;
import com.supermarket.dao.SuperMarketDao;
import com.supermarket.pojo.CommodityVO;
import com.supermarket.util.IDUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class SupermarketService {
    private SuperMarketDao superMarketDao;

    public SupermarketService() {
        this.superMarketDao = new SuperMarketDao();
    }

    public User getUser(String username, String password) {
        return superMarketDao.getUser(username, password);
    }

    //--------------------------进货-----------------------
    public Commodity getCommodity(long id){
        return superMarketDao.getCommodity(id);
    }

    public List<Commodity> getCommodities() {
        return superMarketDao.getCommodities();
    }

    /**
     * 进货
     * @param commodity
     */
    public void inputCommodity(Commodity commodity){
        //如果从未采购过该商品，直接insert
        Commodity commodityExist = this.getCommodity(commodity.getId());
        if (commodityExist==null){
            superMarketDao.insertCommodity(commodity);
        }else {
            //曾经采购过该商品时，更新数量，以及其他属性
            superMarketDao.updateCommodity(commodity);
        }
    }
    //-----------------------收银---------------------

    public void addBoughtCommodity(OrderItem orderItem) {

        OrderItem old = superMarketDao.getOrderItemBySNandCId(orderItem.getShoppingNumber(),orderItem.getCommodityId());
        //如果第一次添加该商品，直接插入,total=price*count
        orderItem.setTotal(orderItem.getPrice().multiply(new BigDecimal(orderItem.getCount())));
        if (old ==null ){
            //todo 添加事务
            superMarketDao.reduceStock(orderItem.getCommodityId(),orderItem.getCount());
            superMarketDao.insertOrderItem(orderItem);
            return;
        }
        //否则，更新数量、总价格即可
        superMarketDao.reduceStock(orderItem.getCommodityId(),orderItem.getCount());
        superMarketDao.addOrderItem(orderItem);
        return;
    }

    /**
     * 根据小票获取所有商品
     * @param shoppingNum
     * @return
     */
    public List<CommodityVO> getBoughtCommodityBySN(long shoppingNum){
        List<CommodityVO> items = superMarketDao.getOrderItemsBySN(shoppingNum);
        return items;
    }

    /**
     * 根据小票号和商品id获取已添加到购物车的商品信息
     * @param shoppingNumber
     * @param commodityId
     * @return
     */
    public OrderItem getOrderItemBySNandCId(long shoppingNumber,long commodityId){
        return superMarketDao.getOrderItemBySNandCId(shoppingNumber,commodityId);
    }

    /**
     * 从购物车中移除或减少商品数量
     * @param item 库中的信息
     * @param count 要减少的数量
     * @return
     */
    public void removeBoughtCommodityS(OrderItem item,int count) {
        if (count <item.getCount()){
            //减少商品数量
            item.setTotal(item.getPrice().multiply(new BigDecimal(count)));
            superMarketDao.reduceOrderItem(item,count);
        }else {
            //移除商品
            superMarketDao.removeOrderItem(item);
        }
        superMarketDao.addStock(item.getCommodityId(),count);
    }

    public List<Commodity> checkout(Integer type, Integer shoppingNum) {
        return null;
    }

    //-----------------------会员管理------------------
    public String addMember(Member member){
        //判断id是否已存在
        Member mem = superMarketDao.getMember(member.getId());
        if (mem !=null){
            return "该会员已存在!";
        }
        //保存member
        long currentTime = System.currentTimeMillis();
        member.setRegisterTime(currentTime);
        member.setUpdateTime(currentTime);
        superMarketDao.insertMember(member);
        return "";
    }
    public Member getMember(String id){
        return superMarketDao.getMember(Long.parseLong(id));
    }
    public List<Member> getMembers() {
        return superMarketDao.getMembers();
    }

    public void updateMember(Member member) {

    }

}
