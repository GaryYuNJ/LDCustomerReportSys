<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    	<!-- Main content starts -->
		<div class="content">
			<!-- Sidebar -->
			<div class="sidebar">
				<!--- Sidebar navigation -->
				<!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
				<ul id="nav">
				    <shiro:hasPermission  name="/samedayFinance/samedayFinance.shtml">
						<li class="has_sub">
							<a href="<c:url value="/samedayFinance/samedayFinance.shtml" />"><i class="icon-jpy"></i> 当日资金 </a>
						</li>
					</shiro:hasPermission>
					<shiro:hasPermission  name="/historyFinance/historyFinance.shtml">
						<li class="has_sub">
							<a href="<c:url value="/historyFinance/historyFinance.shtml" />"><i class="icon-jpy"></i> 历史资金 </a>
						</li>
					</shiro:hasPermission>
					<shiro:hasPermission  name="/financeReport/financeReport.shtml">
						<li class="has_sub">
							<a href="<c:url value="/financeReport/financeReport.shtml" />"><i class="icon-jpy"></i> 资金报表 </a>
						</li>
					</shiro:hasPermission>
				</ul>
				 
					 
			</div>
			<!-- Sidebar ends -->