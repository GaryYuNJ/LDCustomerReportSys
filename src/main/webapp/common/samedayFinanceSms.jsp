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
<script src="<c:url value="/js/jquery.js" />"></script>
<script type="text/javascript">
function querySmsRecord() {
	var code = getQueryString("code"); 
	$.post(getRootPath() + "/samedayFinance/selectSmsRecord.shtml",{code:code},function(result){
	    //alert(result[0].content1);
	    if (result.length > 0) {
	    	$("#smsTitleDiv").html((result[0].date == null ? "<h3>&nbsp;&nbsp;" : "<h3>" + parseDate(result[0].date)+"&nbsp;&nbsp;") + "江苏事业部资金情况报表<h3>");
	    	
		    if (result[0].content1) {
		    	$("#selectCapitalBalanceTemplate").html(result[0].content1);
		    }
		    if (result[0].content2) {
		    	$("#selectInvalidCapitalTDiffTemplate").html(result[0].content2);
		    }
		    if (result[0].content3) {
		    	$("#selectIncomeDetailTemplate").html(result[0].content3);
		    }
		    if (result[0].content4) {
		    	$("#selectCostDetailTemplate").html(result[0].content4);
		    }
		    if (result[0].content5) {
		    	$("#selectWorkArrivedBalanceTemplate").html(result[0].content5);
		    }
		    if (result[0].content6) {
		    	$("#selectJointVentureTemplate").html(result[0].content6);
		    }
	    } else {
	    	$("#selectCapitalBalanceTemplate").html("无短信记录！");
	    	$("#selectInvalidCapitalTDiffTemplate").html("无短信记录！");
	    	$("#selectIncomeDetailTemplate").html("无短信记录！");
	    	$("#selectCostDetailTemplate").html("无短信记录！");
	    	$("#selectWorkArrivedBalanceTemplate").html("无短信记录！");
	    	$("#selectJointVentureTemplate").html("无短信记录！");
	    	
	    	$("#smsTitleDiv").html("<h3>江苏事业部资金情况报表<h3>");
	    }
	},'json');
}

function getQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
}

function parseDate(timestamp){ 
	var date = new Date(timestamp);
	var Y = date.getFullYear() + '-';
	var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	var D = (date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate());
	return Y+M+D;
}

function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
</script>
</head>

<body onload="querySmsRecord()">
<div id="smsTitleDiv" style="margin-top:10px;text-align:center;"></div>
<div class="margin20" style="margin-top:5px;">
	<form role="form">
	  	<div class="form-group"> 
		  	<label class="smslabelnew">资金余额</label>   
		    <input type="hidden"/>
		    <div id="selectCapitalBalanceTemplate" class="form-control form-controltwo"></div>
	  	</div>
	</form>	
	<div class="linehrsms"></div>
	<form role="form">
	  	<div class="form-group"> 
		  	<label class="smslabelnew">不可用资金差异</label>   
		    <input type="hidden"/>
		    <div id="selectInvalidCapitalTDiffTemplate" class="form-control form-controltwo" ></div>
	  	</div>
	</form>	
	<div class="linehrsms"></div>
	<form role="form">
	  	<div class="form-group"> 
		  	<label class="smslabelnew">收入明细</label>   
		    <input type="hidden"/>
		    <div id="selectIncomeDetailTemplate" class="form-control form-controltwo"></div>
	  	</div> 
	</form>	
	<div class="linehrsms"></div>
	<form role="form">
	  	<div class="form-group"> 
		  	<label class="smslabelnew">支出明细</label>   
		    <input type="hidden"/>
		    <div id="selectCostDetailTemplate" class="form-control form-controltwo"></div>
	  	</div>	
	</form>	
	<div class="linehrsms"></div>
	<form role="form">
	  	<div class="form-group"> 
		  	<label class="smslabelnew">工抵余额</label>   
		    <input type="hidden"/>
		    <div id="selectWorkArrivedBalanceTemplate" class="form-control form-controltwo"></div>
	  	</div>
	</form>	
	<div class="linehrsms"></div>
	<form role="form">
	  	<div class="form-group"> 
		  	<label class="smslabelnew">合资公司</label>   
		    <input type="hidden"/>
		    <div id="selectJointVentureTemplate" class="form-control form-controltwo"></div>
	  	</div> 
	</form>	
	
</div>
</body>
</html>
			 