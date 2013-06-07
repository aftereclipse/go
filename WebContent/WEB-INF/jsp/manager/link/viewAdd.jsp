<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title> 创建新链接 </title>


<ul class="breadcrumb">
	<li><a href="manager/index">首页</a> <span class="divider">/</span></li>
	<li><a href="manager/link/list">链接</a> <span class="divider">/</span></li>
	<li class="active">创建链接</li>
</ul>


<c:if test="${not empty  requestScope.tip }">
	<c:choose>
		<c:when test="${fn:startsWith(requestScope.tip,'ok') }">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
			    <strong>操作提示：</strong> ${fn:substring(requestScope.tip,2,fn:length(requestScope.tip)) }
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert ">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
			    <strong>操作提示：</strong> ${requestScope.tip }
			</div>
		</c:otherwise>
	</c:choose>
</c:if>

<div class="row-fluid ">
	<h5 class="offset1"><i class="icon-plus"></i> 创建新链接</h5>
</div>
<div class="row-fluid ">
	<div class="span7 offset1">		
	<form  class ="well"action = "manager/link/add" method = "post">
		<div class="row-fluid">
			<label class="span2" for="name">链接名称</label>
			<input type = "text" class="span4" name = "title" id="title" value="${requestScope.title }" autocomplete = "off" />
			<span class="text-error">*</span>
		</div>
		<div class="row-fluid">
			<label class="span2" for="name">链接内容</label>
			<input type = "text" class="span4" name = "content" id="content" value="${requestScope.content }" autocomplete = "off" />
			<span class="text-error">*</span>
		</div>
		<div class="row-fluid">
			<label class="span2" for="name">链接地址</label>
			<input type = "text" class="span4" name = "link" id="link" value="${requestScope.link }" autocomplete = "off" />
			<span class="text-error">*</span>
		</div>
		<div class="row-fluid">
		<c:forEach var="category" items="${requestScope.dataList }" varStatus="status">
		<label class="checkbox">
		   ${category.name }
		<input type ="checkbox" class="span4"  name="${category.id}"  id="${category.id}"  value="${category.id}"  autocomplete="off"></input>
		</label>
		</c:forEach>
		</div>
		<div class="row-fluid">
			<input type="submit" value="创建" class="btn btn-primary"/>
		</div>
	</form>
	</div>
	<div class="alert alert-warning span4">
		<p><span class="text-error">*</span> 内容为必填</p>
	</div>
</div>

<script type="text/javascript">
$(function(){
	if($('#title').val()=='')
		$('#title').focus();
});
</script>