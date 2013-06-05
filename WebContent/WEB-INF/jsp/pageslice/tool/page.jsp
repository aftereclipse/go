<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<%-- 用途说明：分页栏显示生成工具(用于所有需要分页的页面，协助生成分页工具栏)  --%>

	<!-- 请求分页地址及请求条件带&不带countPerPage参数 -->
	<c:set var="url" value="${param.url }" />
	<c:set var="query" value="${param.query }" />
	<c:set var="maxPagerShowLength" value="${requestScope.maxPageShowLength }"/>
	
	<!-- 分页 -->
	<div class="pull-right middle-vertical">
		<span class="muted">第 ${currentPage } - ${pager.totalPage } 页，共${pager.totalCount }条 / 每页
			<c:forEach var="c" items="10,20,50,100">
	    		<c:choose>
					<c:when test="${countPerPage == c }">
	    			${countPerPage }
					</c:when>
					<c:otherwise>
			    		<a href="${url }?page=1&countPerPage=${c }${query}">${c }</a>
					</c:otherwise>
				</c:choose>
	    	</c:forEach>
    	</span>
    	条
	</div>
    <div class="pagination pagination-small">
	    <ul>
	    	<%-- 首页 --%>
	    	<c:choose>
	    		<c:when test="${currentPage == 1 }">
	    			<li class="disabled"><span>首页</span></li>
	    		</c:when>
	    		<c:otherwise>
	    			<li><a href="${url }?page=1&countPerPage=${countPerPage }${query }">首页</a></li>
	    		</c:otherwise>
	    	</c:choose>
	    	<%-- 上一页 --%>
	    	<c:choose>
	    		<c:when test="${currentPage <= 1 }">
	    			<li class="disabled"><span>上一页</span></li>
	    		</c:when>
	    		<c:otherwise>
	    			<li><a href="${url }?page=${pager.currentPage - 1 }&countPerPage=${countPerPage }${query }">上一页</a></li>
	    		</c:otherwise>
	    	</c:choose>
	    	<%-- 分页区块 --%>
		    <c:choose>
				<c:when test="${pager.totalPage == 1}">
					<li class="active"><span>${1 }</span></li>
				</c:when>
				<c:when test="${(pager.totalPage > maxPagerShowLength) && (pager.currentPage + maxPagerShowLength-1 <= pager.totalPage) && pager.currentPage <= maxPagerShowLength}">
					<c:forEach var="p" begin="1"
						end="${maxPagerShowLength }" step="1">
						<c:choose>
							<c:when test="${p==pager.currentPage }">
								<li class="active"><span>${p }</span></li>
							</c:when>
							<c:otherwise>
								<li><a href="${url }?page=${p }&countPerPage=${countPerPage }${query }">${p }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:when test="${(pager.totalPage > maxPagerShowLength) && (pager.currentPage + maxPagerShowLength-1 <= pager.totalPage)}">
					<c:forEach var="p" begin="${pager.currentPage}"
						end="${pager.currentPage + maxPagerShowLength-1}" step="1">
						<c:choose>
							<c:when test="${p==pager.currentPage }">
								<li class="active"><span>${p }</span></li>
							</c:when>
							<c:otherwise>
								<li><a href="${url }?page=${p}&countPerPage=${countPerPage }${query }" >${p }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:when test="${(pager.totalPage > maxPagerShowLength) && (pager.currentPage + maxPagerShowLength-1 > pager.totalPage)}">
					<c:forEach var="p" begin="${pager.totalPage-maxPagerShowLength + 1}"
						end="${ pager.totalPage}" step="1">
						<c:choose>
							<c:when test="${p==pager.currentPage }">
								<li class="active"><span>${p }</span></li>
							</c:when>
							<c:otherwise>
								<li><a href="${url }?page=${p}&countPerPage=${countPerPage }${query }" >${p }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="p" begin="1" end="${pager.totalPage }" step="1">
						<c:choose>
							<c:when test="${p==pager.currentPage }">
								<li class="active"><span>${p }</span></li>
							</c:when>
							<c:otherwise>
								<li><a href="${url }?page=${p}&countPerPage=${countPerPage }${query }">${p }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:otherwise>
			</c:choose> 
			<%-- 下一页 --%>
			<c:choose>
	    		<c:when test="${currentPage >= pager.totalPage }">
	    			<li class="disabled"><span>下一页</span></li>
	    		</c:when>
	    		<c:otherwise>
	    			<li><a href="${url }?page=${pager.currentPage + 1 }&countPerPage=${countPerPage }${query }">下一页</a></li>
	    		</c:otherwise>
	    	</c:choose>
	    	<%-- 尾页 --%>
			<c:choose>
	    		<c:when test="${currentPage == pager.totalPage }">
	    			<li class="disabled"><span>尾页</span></li>
	    		</c:when>
	    		<c:otherwise>
	    			<li><a href="${url }?page=${pager.totalPage }&countPerPage=${countPerPage }${query }">尾页</a></li>
	    		</c:otherwise>
	    	</c:choose>
	    </ul>
    </div>