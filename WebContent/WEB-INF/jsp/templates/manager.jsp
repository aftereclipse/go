<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><decorator:title default="后台" /></title>
<link rel="stylesheet" type="text/css" href="common/css/bootstrap.min.css" />
<style type="text/css">
	body{padding-top:60px;}
</style>
<link rel="stylesheet" type="text/css" href="common/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" type="text/css" href="common/css/manager.css" />
<script type="text/javascript" src="common/js/jquery.min.js"></script>
<script type="text/javascript" src="common/js/bootstrap.min.js"></script>
<script type="text/javascript" src="common/js/manager.js"></script>
</head>
<body>
<c:set var="currentPage" value="${requestScope.currentPage}"></c:set>
<c:set var="countPerPage" value="${requestScope.countPerPage}"></c:set>
<c:set var="pager" value="${requestScope.pager}"></c:set>
<c:set var="maxPagerShowLength" value="${requestScope.maxPageShowLength}"></c:set>
		
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="brand" href="manager/index">导航管理后台</a>
      <div class="nav-collapse collapse">
        <p class="navbar-text pull-right">
			<a href="global/logout" class="btn btn-small">
				欢迎您，${sessionScope.loginUser }
				<span class="empty-sep">|</span> 
				退出系统
			</a>
		</p>
        <ul id="globalNav" class="nav">
          <li><a href="manager/index">首页</a></li>
          <li><a href="manager/admin/list">管理员</a></li>
          <li><a href="manager/category/list">标签</a></li>
           <li><a href="manager/link/list">链接</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>
<div class="container">
	<decorator:body />
	
	<%@ include file="../include/footer.jsp" %>
</div>
</body>
</html>