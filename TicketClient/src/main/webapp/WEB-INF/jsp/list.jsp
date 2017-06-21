<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加车次页面</title>
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
 
</script>
</head>
<body>

<table border="1px" align="center">
<tr>
	<td>车次</td>
	<td>起点</td>
	<td>终点</td>
	<td>发车时间</td>
	<td>到达时间</td>
	<td>操作</td>
</tr>
<c:forEach items="${list}"  var="e">
<tr>
	<td>${e.plate}</td>
	<td>${e.entitys[0].startC}</td>
	<c:forEach items="${e.entitys}" var="e2" begin="${fn:length(e.entitys)-1}">
		<td>${e2.endC}</td>
	</c:forEach>
	<td><fmt:formatDate value="${e.entitys[0].startT}" pattern="yyyy-MM-dd HH:mm"/> </td>
	<c:forEach items="${e.entitys}" var="e3" begin="${fn:length(e.entitys)-1}">
		<td><fmt:formatDate value="${e3.endT}" pattern="yyyy-MM-dd HH:mm"/></td>
	</c:forEach>
	<td><a href="${pageContext.request.contextPath}/ticket/pay/${e.plate}">订票</a></td>
</tr>
</c:forEach>
</table>

</body>
</html>