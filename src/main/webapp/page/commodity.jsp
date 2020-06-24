<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>进货管理</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/component/jquery-3.3.1.min.js">
</script>
<script type="text/javascript">
    function to_add_commodities() {
        $("#commodity_fm").attr('action', '/supermarket/inputCommodities');
        $("#commodity_fm").submit();
    }
    function cancle() {
        $("#commodity_id").val("");
        $("#commodity_name").val("");
        $("#stock").val("");
        $("#specification").val("");
        $("#units").val("");
        $("#price").val("");
        $("#memberprice").val("");
    }
</script>
<body>
<div align="center">
    <h1>进货管理</h1>
    <hr>
</div>
<div align="right">
    登录时间 ：<label><fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd HH:mm:ss"/></label>
    <hr>
</div>
<form id="commodity_fm" method="post">
    <div id="add_commodity" align="center">
        <label>商品条码</label><input type="text" id="commodity_id" name="commodityId"/>
        <label>商品名称</label><input type="text" id="commodity_name" name="name"/>
        <br>
        <label>规格等级</label><input type="text" id="specification" name="specification"/>
        <label>单&#12288&#12288位</label><input type="text" id="units" name="units"/>
        <br>
        <label>售&#12288&#12288价</label><input type="text" id="price" name="price"/>
        <label>会员&#12288价</label><input type="text" id="memberprice" name="memberprice"/>
        <br>
        <label>数&#12288&#12288量</label><input type="text" id="stock" name="stock"/>
        <br>
        <input type="button" id="add_btn" value="入库" onclick="to_add_commodities()"/>
        <input type="button" id="cancel_btn" value="取消" onclick="cancle()"/>
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
            <th>会员价</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${commodities}" var="item">
            <tr>
                <td align="center">${item.id}</td>
                <td align="center">${item.name}</td>
                <td align="center">${item.specification}</td>
                <td align="center">${item.units}</td>
                <td align="center">${item.stock}</td>
                <td align="center">${item.price}</td>
                <td align="center">${item.memberprice}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <br>
</form>
</body>
</html>
