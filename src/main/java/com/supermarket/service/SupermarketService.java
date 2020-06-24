package com.supermarket.service;


import com.supermarket.bean.Commodity;
import com.supermarket.bean.Member;
import com.supermarket.bean.User;
import com.supermarket.dao.SuperMarketDao;
import com.supermarket.pojo.CommodityVO;

import java.util.List;

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
    public CommodityVO getCommodityVO(int id){
        return null;
    }
    public List<CommodityVO> addBoughtCommodity(Long shoppingNum, CommodityVO commodity) {
        return null;
    }

    public List<Commodity> removeBoughtCommodity(Integer shoppingNum, Commodity commodity) {
        return null;
    }

    public List<Commodity> checkout(Integer type, Integer shoppingNum) {
        return null;
    }

    //-----------------------会员管理------------------
    public void addMember(){

    }
    public Member getMember(){
        return null;
    }
    public List<Member> getMembers() {
        return null;
    }

    public void updateMember(Member member) {

    }

}
