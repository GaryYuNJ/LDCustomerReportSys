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
				    <shiro:hasPermission  name="/housingManagement/housingManagement.shtml">
						<li class="has_sub">
							<a href="<c:url value="/housingManagement/housingManagement.shtml" />"><i class="icon-bar-chart"></i> 房源管理 </a>
						</li>
					</shiro:hasPermission>
					<shiro:hasPermission  name="/projectDataManagement/projectDataManagement.shtml">
						<li class="has_sub">
							<a href="<c:url value="/projectDataManagement/projectDataManagement.shtml" />"><i class="icon-bar-chart"></i> 项目数据管理 </a>
						</li>
					</shiro:hasPermission>
					<shiro:hasPermission  name="/salesReportManagement/salesReportManagement.shtml">
						<li class="has_sub">
							<a href="<c:url value="/salesReportManagement/salesReportManagement.shtml" />"><i class="icon-bar-chart"></i> 销售报表管理 </a>
						</li>
                    </shiro:hasPermission>						
					<shiro:hasPermission  name="/defaultCommissionConfiguration/defaultCommissionConfiguration.shtml">
						<li class="has_sub">
							<a href="<c:url value="/defaultCommissionConfiguration/defaultCommissionConfiguration.shtml" />"><i class="icon-bar-chart"></i> 佣金配置管理 </a>
						</li>
					</shiro:hasPermission>
					<shiro:hasPermission  name="/commissionReportManagement/commissionReportManagement.shtml">
						<li class="has_sub">
							<a href="<c:url value="/commissionReportManagement/commissionReportManagement.shtml" />"><i class="icon-bar-chart"></i> 佣金报表管理 </a>
						</li>
					</shiro:hasPermission>
				</ul>
				 
					 
			</div>
			<!-- Sidebar ends -->