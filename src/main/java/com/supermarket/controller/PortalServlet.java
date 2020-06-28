package com.supermarket.controller;

import com.alibaba.druid.util.StringUtils;
import com.supermarket.bean.Commodity;
import com.supermarket.bean.Member;
import com.supermarket.bean.OrderItem;
import com.supermarket.bean.User;
import com.supermarket.pojo.CommodityVO;
import com.supermarket.util.IDUtil;
import com.supermarket.service.SupermarketService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


public class PortalServlet extends HttpServlet {

    private String cashierPage = "/WEB-INF/page/cashier.jsp";
    private String managerPage = "/WEB-INF/page/manager.jsp";
    private String commodityPage = "/WEB-INF/page/commodity.jsp";
    private String receiptPage = "/WEB-INF/page/receipt.jsp";
    private String loginPage = "/WEB-INF/page/login.jsp";
    private String errorPage = "/WEB-INF/page/error.jsp";
    private SupermarketService supermarketService;

    @Override
    public void init() throws ServletException {
        supermarketService = new SupermarketService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUri = req.getRequestURI();
        HttpSession session = req.getSession();

        System.out.println(currentUri + " this request method is GET method");
        System.out.println(" This request seesion id is " + session.getId());

        Cookie[] cookies = req.getCookies();
        String cookieValue = "";
        for ( Cookie cookie:cookies ) {
            if ("kfc".equals(cookie.getName())){
                cookieValue = cookie.getValue();
                break;
            }
        }
        if ("/supermarket/welcome".equals(currentUri) || !cookieValue.equals(session.getAttribute("kfc"))){
            String forwardPage = "/WEB-INF/page/login.jsp";
            RequestDispatcher view = req.getRequestDispatcher(forwardPage);
            view.forward(req, resp);
        }else if ("/supermarket/back2cashier".equals(currentUri)) {
            back2cashier(req, resp);
        }else  if ("/supermarket/checkoutByCash".equals(currentUri)) {
            checkoutByCash(req, resp);
        }else  if ("/supermarket/checkoutByMember".equals(currentUri)) {
            checkoutByMember(req, resp);
        }else  if ("/supermarket/getCommodities".equals(currentUri)) {
            getCommodities(req, resp);
        }else  if ("/supermarket/getMembers".equals(currentUri)) {
            getMembers(req, resp);
        }else  if ("/supermarket/queryMember".equals(currentUri)) {
            queryMember(req, resp);
        }else  if ("/supermarket/getMember".equals(currentUri)) {
            getMember(req, resp);
        }else  if ("/supermarket/inputCommodities".equals(currentUri)) {
            inputCommodity(req, resp);
        }
//        if ("/supermarket/welcome".equals(currentUri)){
//            String forwardPage = "/WEB-INF/page/login.jsp";
//            RequestDispatcher view = req.getRequestDispatcher(forwardPage);
//            view.forward(req, resp);
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUri = req.getRequestURI();
        HttpSession session = req.getSession();

        System.out.println(currentUri + " this request method is POST method");
        System.out.println(" This request session id is " + session.getId());

        Cookie[] cookies = req.getCookies();
        String cookieValue = "";
        for ( Cookie cookie:cookies ) {
            if ("kfc".equals(cookie.getName())){
                cookieValue = cookie.getValue();
                break;
            }
        }
        if (!cookieValue.equals(session.getAttribute("kfc"))){
            if ("/supermarket/login".equals(currentUri)) {
                login(req, resp);
            }else {
                String forwardPage = "/WEB-INF/page/login.jsp";
                RequestDispatcher view = req.getRequestDispatcher(forwardPage);
                view.forward(req, resp);
            }
        }else {
            if ("/supermarket/login".equals(currentUri)) {
                login(req, resp);
            }
            if ("/supermarket/addBoughtCommodity".equals(currentUri)) {
                addBoughtCommodity(req, resp);
            }
            if ("/supermarket/back2cashier".equals(currentUri)) {
                back2cashier(req, resp);
            }
            if ("/supermarket/removeBoughtCommodity".equals(currentUri)) {
                removeBoughtCommodity(req, resp);
            }
            if ("/supermarket/checkoutByCash".equals(currentUri)) {
                checkoutByCash(req, resp);
            }
            if ("/supermarket/checkoutByMember".equals(currentUri)) {
                checkoutByMember(req, resp);
            }
            if ("/supermarket/getCommodities".equals(currentUri)) {
                getCommodities(req, resp);
            }
            if ("/supermarket/getMembers".equals(currentUri)) {
                getMembers(req, resp);
            }
            //进入管理员页面（会员管理）
            if ("/supermarket/getMember".equals(currentUri)) {
                getMember(req, resp);
            }
            if ("/supermarket/inputCommodities".equals(currentUri)) {
                inputCommodity(req, resp);
            }
            if ("/supermarket/addMember".equals(currentUri)) {
                addMember(req, resp);
            }
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String role = req.getParameter("role");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String forwardPage = errorPage;
        User user = supermarketService.getUser(username, password);

        if (user != null) {
            Cookie cookie = new Cookie("kfc","sb");
            resp.addCookie(cookie);
            req.getSession().setAttribute("kfc","sb");

            if ("1".equals(role)) {
                forwardPage = managerPage;
            } else if ("2".equals(role)) {
                forwardPage = cashierPage;
            }
        }
        RequestDispatcher view = req.getRequestDispatcher(forwardPage);
        req.setAttribute("shoppingNum", IDUtil.getId());
        view.forward(req, resp);

    }

    /**
     * 添加会员
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    private void addMember(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String points = req.getParameter("points");
        String total = req.getParameter("total");
        String errorMessage;
        if (StringUtils.isEmpty(id)){
            errorMessage = "会员号不能为空！";
            req.setAttribute("errorMessage",errorMessage);
            req.getRequestDispatcher(errorPage).forward(req,resp);
        }
        long memberId = Long.parseLong(id);
        int memberPoints = StringUtils.isEmpty(points)? 0 : Integer.parseInt(points);
        BigDecimal memberTotal = StringUtils.isEmpty(total)? new BigDecimal(0) : new BigDecimal(total);

        Member member = new Member();
        member.setId(memberId);
        member.setPhone(phone);
        member.setPoints(memberPoints);
        member.setName(name);
        member.setTotal(memberTotal);
        errorMessage = supermarketService.addMember(member);

        if (!StringUtils.isEmpty(errorMessage)){
            req.setAttribute("errorMessage",errorMessage);
            req.getRequestDispatcher(errorPage).forward(req,resp);
        }
        //获取所有的会员列表
        List<Member> members = supermarketService.getMembers();
        req.setAttribute("members",members);
        req.getRequestDispatcher(managerPage).forward(req,resp);
    }
    private void queryMember(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //获取所有的会员列表
        List<Member> members = supermarketService.getMembers();
        req.setAttribute("members",members);
        req.getRequestDispatcher(managerPage).forward(req,resp);
    }

    private void getMembers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void getMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void back2cashier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        String forwardPage = "";


        if ("1".equals(role)) {
            forwardPage = "/supermarket/getMembers";
            resp.sendRedirect(forwardPage);
        } else if ("2".equals(role)) {
            forwardPage = cashierPage;
            RequestDispatcher view = req.getRequestDispatcher(forwardPage);
            req.setAttribute("shoppingNum", IDUtil.getId());
            view.forward(req, resp);
        }


    }

    private void addBoughtCommodity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long commodityID = Long.parseLong(req.getParameter("commodityID"));
        String count = req.getParameter("count");
        String shoppingNumStr = req.getParameter("shoppingNum");
        BigDecimal totalCost=new BigDecimal(0);
        int category = 0;
        long shoppingNumber;

        if (shoppingNumStr != null && shoppingNumStr != "") {
            shoppingNumber = Long.parseLong(shoppingNumStr);
            if (shoppingNumber == 0) {
                shoppingNumber = IDUtil.getId();
            }
        } else {
            shoppingNumber = IDUtil.getId();
        }

        req.setAttribute("shoppingNum", shoppingNumber);

        Commodity commodity = supermarketService.getCommodity((commodityID));
        if (commodity == null) {
            String forwardPage = cashierPage;
            RequestDispatcher view = req.getRequestDispatcher(forwardPage);
            view.forward(req, resp);
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setCommodityId(commodityID);
        orderItem.setCommodityName(commodity.getName());
        orderItem.setCount(Integer.parseInt(count));
        //todo 根据是否会员取相应的价格
        orderItem.setPrice(commodity.getPrice());
        //0未结账,1已结账
        orderItem.setIsChecked(0);
        orderItem.setShoppingNumber(shoppingNumber);
        supermarketService.addBoughtCommodity(orderItem);

        List<CommodityVO> orderItems =supermarketService.getBoughtCommodityBySN(shoppingNumber);
        if (orderItems != null && orderItems.size()!=0){
            for (CommodityVO item : orderItems) {
                totalCost= totalCost.add(item.getTotal());
            }
            category = orderItems.size();
        }

        req.setAttribute("commodityList", orderItems);
        req.setAttribute("totalCost", totalCost);
        req.setAttribute("category", category);

        String forwardPage = cashierPage;
        RequestDispatcher view = req.getRequestDispatcher(forwardPage);
        view.forward(req, resp);

    }

    private void removeBoughtCommodity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long commodityID = Long.parseLong(req.getParameter("commodityID"));
        int count = StringUtils.isEmpty(req.getParameter("count")) ? 0 : Integer.parseInt(req.getParameter("count"));
        BigDecimal totalCost=new BigDecimal(0);
        int category = 0;
        long shoppingNumber = Long.parseLong(req.getParameter("shoppingNum"));
        req.setAttribute("shoppingNum", shoppingNumber);

       OrderItem target = supermarketService.getOrderItemBySNandCId(shoppingNumber,commodityID);
        if (target == null || count>target.getCount()) {
            String forwardPage = cashierPage;
            RequestDispatcher view = req.getRequestDispatcher(forwardPage);
            view.forward(req, resp);
        }

        supermarketService.removeBoughtCommodityS(target,count);

        List<CommodityVO> orderItems =supermarketService.getBoughtCommodityBySN(shoppingNumber);
        if (orderItems != null && orderItems.size()!=0){
            for (CommodityVO item : orderItems) {
                totalCost= totalCost.add(item.getTotal());
            }
            category = orderItems.size();
        }

        req.setAttribute("commodityList", orderItems);
        req.setAttribute("totalCost", totalCost);
        req.setAttribute("category", category);

        String forwardPage = cashierPage;
        RequestDispatcher view = req.getRequestDispatcher(forwardPage);
        view.forward(req, resp);
    }

    private void getCommodities(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String forwardPage = errorPage;
        User user = supermarketService.getUser(username, password);

        if (user != null) {
            forwardPage = commodityPage;

        }

        List<Commodity> commodities = supermarketService.getCommodities();
        RequestDispatcher view = req.getRequestDispatcher(forwardPage);
        req.setAttribute("commodities", commodities);
        view.forward(req, resp);
    }

    /**
     * 进货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void inputCommodity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //进货
        Commodity commodity = new Commodity();
        commodity.setId(Long.parseLong(req.getParameter("commodityId")));
        commodity.setName(req.getParameter("name"));
        commodity.setUnits(req.getParameter("units"));
        commodity.setSpecification(req.getParameter("specification"));
        commodity.setStock(Integer.parseInt(req.getParameter("stock")));
        commodity.setPrice(new BigDecimal(req.getParameter("price")));
        commodity.setMemberprice( new BigDecimal(req.getParameter("memberprice")));
        supermarketService.inputCommodity(commodity);

        //重载全部商品
        List<Commodity> commodities = supermarketService.getCommodities();
        req.setAttribute("commodities", commodities);
        String forwardPage = commodityPage;
        req.getRequestDispatcher(forwardPage).forward(req, resp);
    }

    private void checkoutByCash(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void checkoutByMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
