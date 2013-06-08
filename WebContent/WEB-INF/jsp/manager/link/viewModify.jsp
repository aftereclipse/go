<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title> 编辑链接 </title>

<ul class="breadcrumb">
	<li><a href="manager/index">首页</a> <span class="divider">/</span></li>
	<li><a href="manager/link/list">链接</a> <span class="divider">/</span></li>
	<li class="active">编辑链接</li>
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
	<h5 class="offset1"><i class="icon-edit"></i> 编辑链接</h5>
</div>
<div class="row-fluid ">
	<div class="span7 offset1">		
	<form action = "manager/link/modify" method = "post">
		<div class="row-fluid">
			<label class="span2" for="name">链接名称</label>
			<input type = "text" class="span4" name = "title" id="title" value="${requestScope.model.title }" autocomplete = "off" />
			<span class="text-error">*</span>
		</div>
		<div class="row-fluid">
			<label class="span2" for="name">链接名称</label>
			<input type = "text" class="span4" name = "content" id="content" value="${requestScope.model.content }" autocomplete = "off" />
			<span class="text-error">*</span>
		</div>
		<div class="row-fluid">
			<label class="span2" for="name">链接名称</label>
			<input type = "text" class="span4" name = "link" id="link" value="${requestScope.model.link }" autocomplete = "off" />
			<span class="text-error">*</span>
		</div>
		<div class="row-fluid">
			<input type="hidden" name="id" value="${requestScope.model.id }"/>
			<input type="submit" value="保存" class="btn btn-primary"/>
		</div>
	</form>
	</div>
	<div class="alert alert-warning span4">
		<p><span class="text-error">*</span> 必填</p>
	</div>
</div>

<script type="text/javascript">
$(function(){
	if($('#title').val()=='')
		$('#title').focus();
});
</script>