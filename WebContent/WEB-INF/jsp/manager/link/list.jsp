<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<title>所有链接</title>

<ul class="breadcrumb">
	<li><a href="manager/index">首页</a> <span class="divider">/</span></li>
	<li class="active">所有链接</li>
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

<p>
	<a href="manager/link/viewAdd" class="btn btn-primary"><i class="icon-plus icon-white"></i> 创建新链接</a>
</p>


<div class="alert alert-info">
	<h5 class="empty-bottom">可选查询条件</h5>
	<form action="manager/link/list" method="get" class="form-search">
		<input type="text" name="title" id="title" value="${requestScope.title }"  class="span2" autocomplete="off" placeHolder="链接名称"/>
		<input type="text" name="content" id="content" value="${requestScope.content }"  class="span2" autocomplete="off" placeHolder="链接内容"/>
		<input type="text" name="link" id="link" value="${requestScope.link }"  class="span2" autocomplete="off" placeHolder="链接地址"/>
		<button class="btn " type="submit">
			<i class="icon-search"></i>  查询
		</button>
		<input type="hidden" name="countPerPage" value="${countPerPage }"/>
		<a href="manager/link/list" class="btn ">重置</a>
	</form>
</div>

<c:choose>
	<c:when test="${empty requestScope.dataList }">
	<div class="alert ">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong>未找到任何链接</strong> 
		<p class="muted">
			您需要现在就去"创建"一个嘛? 
		</p>
		<p class="muted">
			<a href="manager/link/viewAdd" class="btn btn-primary"><i class="icon-plus icon-white"></i> 创建新链接</a>
		</p>
	</div>
	</c:when>
	<c:otherwise>
		
		<%-- 分页 --%>
		<c:set var="query" value="&name=${requestScope.name }"/>
		<c:import url="../../pageslice/tool/page.jsp">
			<c:param name="url" value="manager/link/list"></c:param>
			<c:param name="query" value="${query }"></c:param>
		</c:import>
	
		<table class="table table-striped table-hover">
			<tr>
				<th class="muted">编号</th>
				<th class="muted">链接名称</th>
				<th class="muted">链接内容</th>
				<th class="muted">链接地址</th>
				<th class="muted">点击数</th>
				<th class="muted">创建时间</th>
				<th class="muted">操作</th>
			</tr>
			<c:forEach var="category" items="${requestScope.dataList }" varStatus="status">
			<tr>
				<td>${(currentPage-1) * countPerPage + status.index + 1 }</td>
				<td>${category.title }</td>
				<td>${category.content }</td>
				<td>${category.link }</td>
				<td>${category.click }</td>
				<td>${category.timetag }</td>
				<td>
					<a href="manager/link/viewModify?id=${category.id}"><i class="icon-edit"></i> 编辑</a>
					<a href="#confirmDialog"  data-toggle="modal" data-target="#confirmDialog" url="manager/link/delete?id=${category.id}" tip="确认要删除链接【${category.title}】嘛？" class="confirm-trigger"><i class="icon-trash"></i> 删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		
		<%-- 分页 --%>
		<c:set var="query" value="&name=${requestScope.title }"/>
		<c:import url="../../pageslice/tool/page.jsp">
			<c:param name="url" value="manager/link/list"></c:param>
			<c:param name="query" value="${query }"></c:param>
		</c:import>
		
	</c:otherwise>
</c:choose>

<div id="confirmDialog" class="modal hide fade">
    <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	    <h5>删除确认</h5>
    </div>
    <div class="modal-body"></div>
    <div class="modal-footer">
	    <a href="javascript:void(0);" class="btn btn-primary confirm-ok-trigger" data-dismiss="modal" aria-hidden="true">确认并删除</a>
	    <a href="#" class="btn close" data-dismiss="modal" aria-hidden="true">取消</a>
    </div>
</div>

<script type="text/javascript">
$(function(){
	
});
</script>