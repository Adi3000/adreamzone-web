<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="core"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>A-Dream-Zone</title>
	</head>
	<body>
		<div>
			<core:view>
				<h1><html:outputText value="Hello World avec JSF" /></h1>
				<span>La même chose avec du HTML :<br /></span> 
				<h1>Hello World avec JSF !</h1>
			</core:view>
		</div>
	</body>
</html>