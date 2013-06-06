<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title> 编辑标签 </title>


<ul class="breadcrumb">
	<li><a href="manager/index">首页</a> <span class="divider">/</span></li>
	<li><a href="manager/category/list">标签</a> <span class="divider">/</span></li>
	<li class="active">编辑标签</li>
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
	<h5 class="offset1"><i class="icon-edit"></i> 编辑标签</h5>
</div>
<div class="row-fluid ">
	<div class="span7 offset1">		
	<form action = "manager/category/modify" method = "post">
		<div class="row-fluid">
			<label class="span2" for="name">标签名称</label>
			<input type = "text" class="span4" name = "name" id="name" value="${requestScope.model.name }" autocomplete = "off" />
			<span class="text-error">*</span>
		</div>
		<div class="row-fluid">
			<input type="hidden" name="id" value="${requestScope.model.id }"/>
			<input type="submit" value="保存" class="btn btn-primary"/>
			<span class="sep">|</span>
			<a href="manager/category/list">返回浏览标签</a>
		</div>
	</form>
	</div>
	<div class="alert alert-warning span4">
		<p><span class="text-error">*</span> 标签名称必填</p>
	</div>
</div>

<script type="text/javascript">
$(function(){
	if($('#name').val()=='')
		$('#name').focus();
});
</script>