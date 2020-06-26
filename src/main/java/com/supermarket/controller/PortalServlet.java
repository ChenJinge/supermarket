package com.supermarket.controller;

import com.alibaba.druid.util.StringUtils;
import com.supermarket.bean.Commodity;
import com.supermarket.bean.Member;
import com.supermarket.bean.User;
import com.supermarket.pojo.CommodityVO;
import com.supermarket.util.IDUtil;
import com.supermarket.service.SupermarketService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;


public class PortalServlet extends HttpServlet {

    private String cashierPage = "/page/cashier.jsp";
    private String managerPage = "/page/manager.jsp";
    private String commodityPage = "/page/commodity.jsp";
    private String receiptPage = "/page/receipt.jsp";
    private String loginPage = "/page/login.jsp";
    private String errorPage = "/page/error.jsp";
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
        if ("/supermarket/getMember".equals(currentUri)) {
            getMember(req, resp);
        }
        if ("/supermarket/inputCommodities".equals(currentUri)) {
            inputCommodity(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUri = req.getRequestURI();
        HttpSession session = req.getSession();

        System.out.println(currentUri + " this request method is POST method");
        System.out.println(" This request seesion id is " + session.getId());

        if ("/supermarket/login".equals(currentUri)) {
            login(req, resp);
            return;
        }
        if ("/supermarket/addBoughtCommodity".equals(currentUri)) {
            addBoughtCommodity(req, resp);
            return;
        }
        if ("/supermarket/back2cashier".equals(currentUri)) {
            back2cashier(req, resp);
            return;
        }
        if ("/supermarket/removeBoughtCommodity".equals(currentUri)) {
            removeBoughtCommodity(req, resp);
            return;
        }
        if ("/supermarket/checkoutByCash".equals(currentUri)) {
            checkoutByCash(req, resp);
            return;
        }
        if ("/supermarket/checkoutByMember".equals(currentUri)) {
            checkoutByMember(req, resp);
            return;
        }
        if ("/supermarket/getCommodities".equals(currentUri)) {
            getCommodities(req, resp);
            return;
        }
        if ("/supermarket/getMembers".equals(currentUri)) {
            getMembers(req, resp);
            return;
        }
        if ("/supermarket/getMember".equals(currentUri)) {
            getMember(req, resp);
            return;
        }
        if ("/supermarket/inputCommodities".equals(currentUri)) {
            inputCommodity(req, resp);
            return;
        }
        if ("/supermarket/addMember".equals(currentUri)) {
            addMember(req, resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String role = req.getParameter("role");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String forwardPage = errorPage;
        User user = supermarketService.getUser(username, password);

        if (user != null) {
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
        String commodityID = req.getParameter("commodityID");
        String count = req.getParameter("count");
        String shoppingNumStr = req.getParameter("shoppingNum");
        Double totalCost = 0.0;
        int category = 0;
        long shoppingNumber = 0;

        if (shoppingNumStr != null && shoppingNumStr != "") {
            shoppingNumber = Long.parseLong(shoppingNumStr);
            if (shoppingNumber == 0) {
                shoppingNumber = IDUtil.getId();
            }
        } else {
            shoppingNumber = IDUtil.getId();
        }

        req.setAttribute("shoppingNum", shoppingNumber);

        CommodityVO commodity = supermarketService.getCommodityVO(Integer.parseInt(commodityID));
        if (commodity == null) {
            String forwardPage = cashierPage;
            RequestDispatcher view = req.getRequestDispatcher(forwardPage);
            view.forward(req, resp);
        }

        commodity.setCount(Integer.parseInt(count));

        List<CommodityVO> commodityList;
        commodityList = supermarketService.addBoughtCommodity(shoppingNumber, commodity);

        for (CommodityVO item : commodityList) {
            totalCost += item.getTotalPrice();
        }

        category = commodityList.size();

        req.setAttribute("commodityList", commodityList);
        req.setAttribute("totalCost", totalCost);
        req.setAttribute("category", category);

        String forwardPage = cashierPage;
        RequestDispatcher view = req.getRequestDispatcher(forwardPage);
        view.forward(req, resp);

    }

    private void removeBoughtCommodity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
