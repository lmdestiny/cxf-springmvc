<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>根据车次查询</title>
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
 
</script>
</head>
<body>
<h>除车号外其余全为必填项</h>
<form action="${pageContext.request.contextPath}/ticket/findBycc" method="post">
	车次：<input name="plate"  placeholder="车次" /><br/>
	起始站：<input name="startC"/><br/>
	终点站：<input name="endC"/><br/>
 	发车时间：<input type="datetime-local" name="startT"/><br/>
	到达时间：<input type="datetime-local" name="endT"/>
	<input type="submit" value="提交">
</form>
</body>
</html>