 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/common/header.jsp"%>
<%@ include file="/common/salereport_nav.jsp"%>
<script src="<c:url value="/js/jquery.min.js" />"></script>
 
<!-- Main bar -->
<div class="mainbar" >
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i>佣金报表管理
		</h2>
		<div class="clearfix"></div>
	</div>
	<!-- Page heading ends -->

	<!-- Matter -->
	<div class="matter">
		<div class="container ">
			<div class="row">
				<div class="col-md-12">
					<div class="widget widgetnewsale"></div>
				</div>
			</div>
		</div>
	</div>
</div>

 
  


<%@ include file="/common/script.jsp"%>
<%@ include file="/common/footer.html"%>
 