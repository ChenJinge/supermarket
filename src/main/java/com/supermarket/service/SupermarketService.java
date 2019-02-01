package com.supermarket.service;


import com.supermarket.bean.Commodity;
import com.supermarket.bean.Member;
import com.supermarket.bean.User;
import com.supermarket.dao.SuperMarketDao;

import java.util.List;

public class SupermarketService {
    private SuperMarketDao superMarketDao;

    public SupermarketService() {
        this.superMarketDao = new SuperMarketDao();
    }

    public User getUser(String username, String password) {
        return superMarketDao.getUser(username, password);
    }

    public Commodity getCommodity(int commodityId) {
        return null;
    }

    public List<Commodity> getCommodities() {
        return superMarketDao.getCommodities();
    }

    public List<Commodity> addBoughtCommodity(Integer shoppingNum, Commodity commodity) {
        return null;
    }

    public List<Commodity> removeBoughtCommodity(Integer shoppingNum, Commodity commodity) {
        return null;
    }

    public void inputCommodity(Commodity commodity){
        superMarketDao.insertCommodity(commodity);
    }
    public List<Commodity> checkout(Integer type, Integer shoppingNum) {
        return null;
    }

    public Member getVIP(){
        return null;
    }
    public List<Member> getVIPs() {
        return null;
    }

    public void updateVIP(Member vip) {

    }

}
