package com.supermarket.controller;

import com.supermarket.bean.Commodity;
import com.supermarket.bean.User;
import com.supermarket.pojo.IDUtil;
import com.supermarket.service.SupermarketService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        System.out.println(currentUri + " this request method is GET method");

        if ("/supermarket/login".equals(currentUri)) {
            login(req, resp);
        }
        if ("/supermarket/addCommodity".equals(currentUri)) {
            addCommodity(req, resp);
        }
        if ("/supermarket/back2cashier".equals(currentUri)) {
            back2cashier(req, resp);
        }
        if ("/supermarket/removeCommodity".equals(currentUri)) {
            removeCommodity(req, resp);
        }
        if ("/supermarket/checkoutByCash".equals(currentUri)) {
            checkoutByCash(req, resp);
        }
        if ("/supermarket/checkoutByVIP".equals(currentUri)) {
            checkoutByVIP(req, resp);
        }
        if ("/supermarket/getCommodities".equals(currentUri)) {
            getCommodities(req, resp);
        }
        if ("/supermarket/getVIPs".equals(currentUri)) {
            getVIPs(req, resp);
        }
        if ("/supermarket/getVIP".equals(currentUri)) {
            getVIP(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUri = req.getRequestURI();
        System.out.println(currentUri + " this request method is POST method");

        if ("/supermarket/login".equals(currentUri)) {
            login(req, resp);
        }
        if ("/supermarket/addCommodity".equals(currentUri)) {
            addCommodity(req, resp);
        }
        if ("/supermarket/back2cashier".equals(currentUri)) {
            back2cashier(req, resp);
        }
        if ("/supermarket/removeCommodity".equals(currentUri)) {
            removeCommodity(req, resp);
        }
        if ("/supermarket/checkoutByCash".equals(currentUri)) {
            checkoutByCash(req, resp);
        }
        if ("/supermarket/checkoutByVIP".equals(currentUri)) {
            checkoutByVIP(req, resp);
        }
        if ("/supermarket/getCommodities".equals(currentUri)) {
            getCommodities(req, resp);
        }
        if ("/supermarket/getVIPs".equals(currentUri)) {
            getVIPs(req, resp);
        }
        if ("/supermarket/getVIP".equals(currentUri)) {
            getVIP(req, resp);
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

    private void back2cashier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        String forwardPage = "";


        if ("1".equals(role)) {
            forwardPage = "/supermarket/getVIPs";
            resp.sendRedirect(forwardPage);
        } else if ("2".equals(role)) {
            forwardPage = cashierPage;
            RequestDispatcher view = req.getRequestDispatcher(forwardPage);
            req.setAttribute("shoppingNum", IDUtil.getId());
            view.forward(req, resp);
        }


    }

    private void addCommodity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commodityID = req.getParameter("commodityID");
        String count = req.getParameter("count");
        String shoppingNumStr = req.getParameter("shoppingNum");
        Double totalCost = 0.0;
        int category = 0;
        int shoppingNumber = 0;

        if (shoppingNumStr != null && shoppingNumStr != "") {
            shoppingNumber = Integer.parseInt(shoppingNumStr);
            if (shoppingNumber == 0) {
                shoppingNumber = IDUtil.getId();
            }
        } else {
            shoppingNumber = IDUtil.getId();
        }

        req.setAttribute("shoppingNum", shoppingNumber);

        Commodity commodity = supermarketService.getCommodity(Integer.parseInt(commodityID));
        if (commodity == null) {
            String forwardPage = cashierPage;
            RequestDispatcher view = req.getRequestDispatcher(forwardPage);
            view.forward(req, resp);
        }

        commodity.setCount(Integer.parseInt(count));

        List<Commodity> commodityList;
        commodityList = supermarketService.addBoughtCommodity(shoppingNumber, commodity);

        for (Commodity item : commodityList) {
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

    private void removeCommodity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void checkoutByCash(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void checkoutByVIP(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void getVIPs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void getCommodities(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void getVIP(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
