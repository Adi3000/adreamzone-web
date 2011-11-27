<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/common/common.css" />
<link rel="stylesheet" type="text/css" href="/css/home/home.css" />
<title>A-Dream-Zone</title>
</head>
<body>
	<div id="wallpaper">
		<%@include file="/jsp/home/title.xhtml"%>
		<div id="bodyPart">
			<div class="floatLeft">
				<%@include file="/jsp/home/mainPart.jsp"%>
			</div>
			<div class="floatRight">
				<%@include file="/jsp/home/newsWatcher.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>