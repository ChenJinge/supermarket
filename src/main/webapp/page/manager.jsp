<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>manager</title>
</head>

<body>
<div align="center">

    <h1>会员管理</h1>
    <hr>

</div>
<!--startprint-->
<table align="center" width="80%" border="1px" cellpadding="0" cellspacing="0">
    <thead>
        <tr>
        <tr>
            <th>会员卡号</th>
            <th>姓名</th>
            <th>电话</th>
            <th>积分</th>
            <th>余额</th>
        </tr>
        </tr>
    </thead>

    <tbody>
    <c:forEach items="${vipList}" var="item">
        <tr>
            <td align="center">${item.vipid}</td>
            <td align="center">${item.name}</td>
            <td align="center">${item.phone}</td>
            <td align="center">${item.points}</td>
            <td align="center">${item.total}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<hr>
</body>
</html>
