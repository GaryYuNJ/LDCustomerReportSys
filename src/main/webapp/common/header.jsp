<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<!-- Title and other stuffs -->
<title>绿地销售财务报表系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="">
<!-- Stylesheets -->
<link href="<c:url value="/style/bootstrap.min.css" />" rel="stylesheet">
<!-- Font awesome icon -->
<link rel="stylesheet" href="<c:url value="/style/font-awesome.css" />">
<!-- Date picker -->
<link rel="stylesheet" href="<c:url value="/style/bootstrap-datetimepicker.min.css" />">
<!-- Bootstrap toggle -->
<link href="<c:url value="/style/style.css" />" rel="stylesheet">
<link href="<c:url value="/js/themes/default/style.min.css" />" rel="stylesheet">
<link href="<c:url value="/style/bootstrap-table.min.css" />" rel="stylesheet">
<link href="<c:url value="/style/bootstrap-switch.css" />" rel="stylesheet">
<link href="<c:url value="/style/bootstrapValidator.min.css" />" rel="stylesheet">
<link href="<c:url value="/style/fileinput.min.css" />" rel="stylesheet">
<link href="<c:url value="/style/bootstrap-select.min.css" />" rel="stylesheet">
<link href="<c:url value="/style/extend_top.css" />" rel="stylesheet">
<link href="<c:url value="/style/jquery-ui.min.css" />" rel="stylesheet">
<link href="<c:url value="/style/newstyle.css" />" rel="stylesheet">

<!-- HTML5 Support for IE -->
<!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->
<!-- Favicon -->
<link rel="shortcut icon" href="<c:url value="/img/favicon/favicon.png" />">
<c:set var="rootUri" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
var rootUri="${rootUri}";
</script>
</head>

<body>
	<!-- Header starts -->
	<header>
		<div class="navbar navbar-inverse navbar-fixed-top  "  role="navigation">
		      <div class="container" >
		      	<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<div class="login-header navbar-brand" style="width: 280px;margin-top: 3px;margin-left: 2px;"></div>
				</div>
			     <div  class="navbar-collapse collapse">
			          <ul class="nav navbar-nav">
						<li class="dropdown">
							<a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="dropdown-toggle" href="<c:url value="/user/userInfo" />">
								个人中心<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/user/userInfo.shtml" />">个人资料</a></li>
								<li><a href="<c:url value="/user/updateSelf.shtml" />">资料修改</a></li>
								<li><a href="<c:url value="/user/updatePswd.shtml" />">密码修改</a></li>
								<li><a href="<c:url value="/role/mypermission.shtml" />">我的权限</a></li>
							</ul>
						</li>	  
						<shiro:hasPermission  name="/member/list.shtml">
						<li class="dropdown">
							<a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="dropdown-toggle" href="<c:url value="/user/userInfo" />">
								用户中心<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/member/list.shtml" />">用户列表</a></li>
								<li><a href="<c:url value="/member/online.shtml" />">在线用户</a></li>
							</ul>
						</li>	
						</shiro:hasPermission>
						<shiro:hasPermission  name="/permission/index.shtml">
						<li class="dropdown">
							<a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="dropdown-toggle" href="<c:url value="/user/userInfo" />">
								权限管理<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/role/index.shtml" />">角色列表</a></li>
								
								<li><a href="<c:url value="/role/allocation.shtml" />">角色分配</a></li>
								<li><a href="<c:url value="/permission/index.shtml" />">权限列表</a></li>
								<li><a href="<c:url value="/permission/allocation.shtml" />">权限分配</a></li>
							</ul>
						</li>	
						</shiro:hasPermission>
						<li class="dropdown">
							<a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="dropdown-toggle" href="<c:url value="/user/userInfo" />">
								资金报表<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
							    <shiro:hasPermission  name="/samedayFinance/samedayFinance.shtml">
									<li><a href="<c:url value="/samedayFinance/samedayFinance.shtml" />">当日资金</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission  name="/historyFinance/historyFinance.shtml">
									<li><a href="<c:url value="/historyFinance/historyFinance.shtml" />">历史资金</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission  name="/financeReport/financeReport.shtml">
									<li><a href="<c:url value="/financeReport/financeReport.shtml" />">资金报表</a></li>
								</shiro:hasPermission>
							</ul>
						</li>
						<shiro:hasPermission  name="/housingManagement/housingManagement.shtml"> 
						<li class="dropdown">
							<a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="dropdown-toggle" href="<c:url value="/user/userInfo" />">
								销售报表<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
							    <shiro:hasPermission  name="/housingManagement/housingManagement.shtml">
									<li><a href="<c:url value="/housingManagement/housingManagement.shtml" />">房源管理</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission  name="/projectDataManagement/projectDataManagement.shtml">
									<li><a href="<c:url value="/projectDataManagement/projectDataManagement.shtml" />">项目数据管理</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission  name="/salesReportManagement/salesReportManagement.shtml">
									<li><a href="<c:url value="/salesReportManagement/salesReportManagement.shtml" />">销售报表管理</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission  name="/defaultCommissionConfiguration/defaultCommissionConfiguration.shtml">
								    <li><a href="<c:url value="/defaultCommissionConfiguration/defaultCommissionConfiguration.shtml" />">默认佣金配置</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission  name="/commissionReportManagement/commissionReportManagement.shtml">
								    <li><a href="<c:url value="/commissionReportManagement/commissionReportManagement.shtml" />">佣金报表管理</a></li>
								</shiro:hasPermission>
							</ul>
						</li>	
						</shiro:hasPermission>		
			          </ul>
			          
			           <ul class="nav navbar-nav  pull-right">
						<li class="dropdown" style="color:#fff;">
							<a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="dropdown-toggle qqlogin">
								<shiro:principal property="nickname"/>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu extend-top" >
								<li><a href="javascript:void(0);" onclick="logout();">退出登录</a></li>
							</ul>
						</li>	
			          </ul>
			    </div>
		  	</div>
		</div>
        <input type="hidden" id="currentUserId" name="currentUserId" value='<shiro:principal property="id"/>'/>
        <input type="hidden" id="currentUserMobile" name="currentUserMobile" value='<shiro:principal property="mobile"/>'/>
	</header>
	<!-- Header ends -->
