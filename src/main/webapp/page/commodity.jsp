<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cashier</title>
</head>
<style type="tetx/css">
    body {font-size:50px;}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/component/jquery-3.3.1.min.js"></script>
<body>
<div align="center">

    <h1>进货管理</h1>
    <hr>

</div>

<div align="right">
    登录时间 ：<label><fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd HH:mm:ss"/></label>
    <hr>
</div>

<form action="" id="cashier_fm" >
    <input type="hidden" id="shopping_num_txt" name="shoppingNum" value = "${shoppingNum}">
    <div>
        输入商品条码：<input type="text" id="commodity_id_txt" name="commodityID"> 数量：<input type="text" id="commodity_count_txt" name="count">
        <input type="button" id="add_btn" value="入库商品" onclick="">
        <input type="button" id="delete_btn" value="删除商品" onclick="">
    </div>
    <br>
    <hr>

    <table width="80%" border="1px" cellpadding="0" cellspacing="0" align="center">
        <thead>
        <tr>
            <th>商品条码</th>
            <th>商品名称</th>
            <th>规格等级</th>
            <th>单位</th>
            <th>当前库存</th>
            <th>零售价</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${commodities}" var="item">
            <tr>
                <td align="center">${item.commodityid}</td>
                <td align="center">${item.commodityname}</td>
                <td align="center">${item.specification}</td>
                <td align="center">${item.units}</td>
                <td align="center">${item.stock}</td>
                <td align="center">${item.price}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <br>

</form>
</body>
</html>
