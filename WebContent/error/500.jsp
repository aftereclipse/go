<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="java.io.*" %>
<%@ page import="cn.go.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<title>错误</title>

服务器异常，请稍候再试
<%
	//获取页面异常详细堆栈
	StringWriter sw=new StringWriter();
	PrintWriter pw=new PrintWriter(sw);
	exception.printStackTrace(pw);

	//发送简单文本邮件
	MailTool.getInstance().sendSimpleText("500", sw.toString());
%>