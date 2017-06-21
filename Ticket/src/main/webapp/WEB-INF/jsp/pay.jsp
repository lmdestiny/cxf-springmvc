<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<title>选择操作</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/ticket/topay" method="post">
<table border="1">
	<tr>
		<td>起点</td>
		<td>终点</td>
		<td>开始时间</td>
		<td>到达时间</td>
	</tr>
	<%int i=0; %>
	<c:forEach items="${t.entitys}" var="e">
	<%i++; if(i%2!=0 && i%3!=0){%>
	<tr>
		<td>${e.startC}</td>
		<td>${e.endC }</td>
		<td><fmt:formatDate value="${e.endT}" pattern="yyyy-MM-dd HH:mm"/></td>
		<td><fmt:formatDate value="${e.endT}" pattern="yyyy-MM-dd HH:mm"/></td>
		</tr>
		<%}%>
	</c:forEach>
	
</table>

<p>请选择座位类型</p>
	<select name="type" >
		<option value="软卧">软卧</option>
		<option value="硬卧">硬卧</option>
		<option value="硬座">硬座</option>
	</select>
	<p>请选择票数</p>
	<input type="text" name="count"/>
	<p>选择起始位置</p>
	<input type="hidden" name="plate" value="${t.plate}">
	<input name="startC"/>
	<input name="endC"/>
	<br/>
	<input type="submit" value="确定"/>
</form>
</body>
</html>