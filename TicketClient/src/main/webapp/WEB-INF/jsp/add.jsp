<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加车次页面</title>
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
  a =0;	
function addrow(){
	a =a+1;
	var form = $('form');
	var addtr = $("<p>区间"+a+"</p>"+
	"软卧数：<input name="+"entitys["+a+"].countT['软卧']"+" /><br/>"+
	"硬卧数：<input name="+"entitys["+a+"].countT['硬卧']"+" /><br/>"+
	"硬座数：<input name="+"entitys["+a+"].countT['硬座']"+" /><br/>"+
	"开始城市：<input name='entitys["+a+"].startC' /><br/>"+
	"结束城市：<input name='entitys["+a+"].endC' /><br/>"+
 	"开始时间：<input type='datetime-local' name='entitys["+a+"].startT' /><br/>"+
	"结束时间：<input type='datetime-local' name='entitys["+a+"].endT' /><br/>");
	      addtr.appendTo(form);     
	}
	function submit(){
		$("form").submit();
	}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/ticket/add" method="post">
	车次：<input name="plate"  placeholder="车次" /><br/>
	<p >添加区间</p>
 	软卧数：<input name="entitys[0].countT['软卧']" /><br/>
	硬卧数：<input name="entitys[0].countT['硬卧']" /><br/>
	硬座数：<input name="entitys[0].countT['硬座']" /><br/>
	开始城市：<input name="entitys[0].startC"/><br/>
	结束城市：<input name="entitys[0].endC"/><br/>
 	开始时间：<input type="datetime-local" name="entitys[0].startT"/><br/>
	结束时间：<input type="datetime-local" name="entitys[0].endT"/>
</form>
	<input type="button" value="添加区间" onclick="addrow();"/>
	<input type="button" value="提交" onclick="submit();"><br/>
</body>
</html>