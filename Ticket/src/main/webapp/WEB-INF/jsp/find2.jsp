<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>根据站点查询</title>
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
 
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/ticket/findByzd" method="post">
	区间开始城市：<input name="startC"/><br/>
	区间到达城市：<input name="endC"/><br/>
 	区间发车时间：<input type="datetime-local" name="startT"/><br/>
	区间到达时间：<input type="datetime-local" name="endT"/>
	<input type="submit" value="提交">
</form>
</body>
</html>