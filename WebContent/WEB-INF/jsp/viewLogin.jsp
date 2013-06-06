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



<title>管理员登录</title>
    
<h1>管理员登录</h1>

<c:if test="${not empty requestScope.tip }">
<p class="tip">${requestScope.tip }</p>
</c:if>

<form action="global/login" method="post">
	<p>
		<label for="uname">用户名</label>
		<input type="text" id="uname" name="uname" value="${requestScope.uname }" autocomplete="off" />
	</p>
	<p>
		<label for="passwd">密码</label>
		<input type="password" id="passwd" name="passwd" autocomplete="off" />
	</p>
	<p>
		<input type="submit" value="登录"/>
		<input type="reset" value="取消"/>
	</p>
</form>

<script type="text/javascript">
$(function(){
	if($('#uname').val()==''){
		$('#uname').focus();
	}
	else if($('#passwd').val()==''){
		$('#passwd').focus();
	}
});
</script>
