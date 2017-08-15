<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/common/header.jsp"%>
<%@ include file="/common/capital_nav.jsp"%>
<script src="<c:url value="/js/jquery.min.js" />"></script>
<script>
	var subCompanyId = "";//当前子公司id
	var queryDate = "";//当前查询日期
	var showType = "historyFinance";
	//收入类型
	var incomeTypeListHtml = "";
	//支出类型
	var costTypeListHtml = "";
	//不可用资金类型
	var invalidCapitalTypeListHtml = "";
</script>	
<script src="<c:url value="/js/samedayFinance.js" />"></script>
<script>
	$(function(){
	    $("#datepicker").val(getNowFormatDate());
	    var formatDate = {
				changeMonth: true,
		        changeYear: true,
		        dateFormat: "yy-mm-dd",
		        monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
		        monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'],  
		        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		        dayNamesMin: ['日','一','二','三','四','五','六'],
		        onSelect: querySubCompanyMsg
		}
	    $("#datepicker").datepicker(formatDate);
	    //查询子公司
	    querySubComp();
    });
</script> 
<!-- Main bar -->
<div class="mainbar">
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i> 历史资金
		</h2>
		<div class="clearfix"></div>
	</div>
	<!-- Page heading ends -->

	<!-- Matter -->
	<div class="matter">
		<div class="container ">
			<div class="row">
				<div class="col-md-12">
					<div class="widget widgetnew">
						 <form method="post" action=""  class="form-inline">
							<div class="well wellnew">
								<form class="form-inline containernew">
									 
								    	<div class="form-group" style="margin-right:10px;">
								        	<input type="text" class="form-control form-controlbg" name="date" id="datepicker">
								      	</div>
							       
								      	<div class="form-group" style="margin-right:10px;">
											<select id="subCompanySel" onchange="querySubCompanyMsg()" class='form-control selectfont' style="min-width:170px;"></select>
										</div> 
								        <!-- <button type="button" onclick="querySubCompanyMsg()" class="btn btn-primary">确认</button> -->
							       
							    </form>	         
						        <div class="cls pt20"></div>
						        <div class="row">
								    <div class="col-lg-3 col-lg-3-bot">
								     	<label class=" labelnew" >
											今日总余额（万元）&nbsp;&nbsp;&nbsp;&nbsp;
										</label> 
										<input id="todayAmount" type="text" class="form-control form-controlbg form-controlnew tcfont" disabled="disabled" > 
								    </div>
								    <div class="col-lg-3 col-lg-3-bot"> 
								       	<label class=" labelnew" >
											总收入（万元）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</label>
										<input id="incomeAmount" type="text" class="form-control form-controlbg form-controlnew tcfont" disabled="disabled"> 	 
								    </div>
								    <div class="col-lg-3 col-lg-3-bot">
								       	<label class=" labelnew" >
											今日工抵余额&nbsp;&nbsp;
										</label>
										<input id="todayGongdiBalance" type="text" disabled="disabled" class="form-control form-controlbg form-controlnew tcfont"> 
								    </div>
								    <div class="col-lg-3 ">  
								       	<label class=" labelnew" >
											总支出（万元） 
										</label>
										<input id="costAmount" type="text" class="form-control form-controlbg form-controlnew tcfont" disabled="disabled">  
								    </div>
								  </div>
								  <div class="cls pt15"></div>
								  <div class="row">
								    <div class="col-lg-3 col-lg-3-bot">	 
								     	<label class=" labelnew" >
											总不可用资金（万元）
										</label> 
										<input id="invalidAmount" type="text" class="form-control form-controlbg form-controlnew tcfont" disabled="disabled"> 
								    </div>
								    <div class="col-lg-3 col-lg-3-bot"> 
								       	<label class=" labelnew" >
											昨日总余额（万元）
										</label>
										<input id="lastdayAmount" type="text" class="form-control form-controlbg form-controlnew tcfont" disabled="disabled"> 	 
								    </div>
								    <div class="col-lg-3 col-lg-3-bot">
								       	<label class=" labelnew" >
											昨日工抵余额&nbsp;&nbsp;
										</label>
										<input id="lastdayGongdiBalance" type="text" disabled="disabled" class="form-control form-controlbg form-controlnew tcfont" > 			 
								    </div>
								    <div class="col-lg-3 sdpt10 col-lg-3-bot">
								       	<label class=" labelnew" >
											余额是否匹配&nbsp;&nbsp;&nbsp;&nbsp;
										</label>
										<img id="balanceDiffImg"/>  
								    </div>
								  </div>
								  <div class="cls pt20"></div>
								   
					        </div>
					      </form>
					      <ul class="nav nav-tabs">
						    <li class="active"><a data-toggle="tab" href="#home" style="border-left:0;">银行余额</a></li>
						    <li><a data-toggle="tab" href="#menu1">今日收入</a></li>
						    <li><a data-toggle="tab" href="#menu2">今日支出 </a></li>
						    <li><a data-toggle="tab" href="#menu3">不可用资金</a></li>
						    <li><a data-toggle="tab" href="#menu4">工抵余额</a></li>
						  </ul>
						
						  <div class="tab-content tab-contentnew">
						    <div id="home" class="tab-pane fade in active">
							    <div class="container containernew">
							      <div class="panel panel-default  ">
							      	<div class="listnew">
								        <table class="table">
								        	<thead>
									            <th class="col-xs-2   col-lg-1new ">银行名称</th>
									            <th class="col-xs-2   col-lg-1new">余额（万元） </th>
									            <th class="col-xs-3new   col-lg-7new">备注 </th>
									            <th class="col-xs-5new  col-lg-3new">&nbsp;</th>
								            </thead>
								            <tbody id="bankTab"></tbody> 
								        </table>
								     </div>
								     <div class="cls pt15"></div>
								     <!-- <button type="button" class="btn btn-warning ml10" disabled="disabled" id="addBank">新增</button>
								     <button type="button" class="btn btn-primary ml5" disabled="disabled">提交</button> -->
								     <div class="cls pt15"></div>
								   </div>
							   </div>
						    </div>
						    <div id="menu1" class="tab-pane fade">
						    	<div class="container containernew">
							      <div class="panel panel-default  ">
							      	<div class="listnew">
								        <table class="table">
								        	<thead>
								        	    <th class="col-xs-2  col-lg-1new col-lg-1new_two">类型 </th>
									            <th class="col-xs-2   col-lg-1new ">收入名称</th>
									            <th class="col-xs-2   col-lg-1new">余额（万元） </th>
									            <th class="col-xs-4new   col-lg-8new">备注 </th>
									            <th class="col-xs-2 col-lg-1new">&nbsp;</th>
								            </thead>
								            <tbody id="incomeTab"></tbody> 
								        </table>
								     </div>
								     <div class="cls pt15"></div>
								     <!-- <button type="button" class="btn btn-warning ml10" disabled="disabled" id="addIcome">新增</button>
								     <button type="button" class="btn btn-primary ml5" disabled="disabled">提交</button> -->
								     <div class="cls pt15"></div>
								   </div>
							   </div>
						    </div>
						    <div id="menu2" class="tab-pane fade">
						       <div class="container containernew">
							      <div class="panel panel-default  ">
							      	<div class="listnew">
								        <table class="table">
								        	<thead>
								        	    <th class="col-xs-2  col-lg-1new col-lg-1new_two">类型 </th>
									            <th class="col-xs-2   col-lg-1new ">支出名称</th>
									            <th class="col-xs-2   col-lg-1new">余额（万元） </th>
									            <th class="col-xs-4new   col-lg-8new">备注 </th>
									            <th class="col-xs-2 col-lg-1new">&nbsp;</th>
								            </thead>
								            <tbody id="defrayTab"></tbody> 
								        </table>
								     </div>
								     <div class="cls pt15"></div>
								     <!-- <button type="button" class="btn btn-warning ml10" disabled="disabled" id="addDefray">新增</button>
								     <button type="button" class="btn btn-primary ml5" disabled="disabled">提交</button>-->
								     <div class="cls pt15"></div>
								   </div>
							   </div>
						    </div>
						    <div id="menu3" class="tab-pane fade">
						       <div class="container containernew">
							      <div class="panel panel-default  ">
							      	<div class="listnew">
								        <table class="table">
								        	<thead>
									            <th class="col-xs-2   col-lg-1new col-lg-1new_three">名称</th>
									            <th class="col-xs-2   col-lg-1new">余额（万元） </th>
									            <th class="col-xs-2  col-lg-1new_two">与昨日差异（万元） </th>
									            <th class="col-xs-4new   col-lg-8new col-lg-8new_two">备注 </th>
									            <th class="col-xs-2 col-lg-1new">&nbsp;</th>
								            </thead>
								            <tbody id="unavailableTab"></tbody> 
								        </table>
								     </div>
								     <div class="cls pt15"></div>
								     <!-- <button type="button" class="btn btn-warning ml10" disabled="disabled" id="addUnavailable">新增</button>
								     <button type="button" class="btn btn-primary ml5" disabled="disabled">提交</button>-->
								     <div class="cls pt15"></div>
								   </div>
							   </div>
						    </div>
						    <div id="menu4" class="tab-pane fade">
						       <div class="container containernew">
							      <div class="panel panel-default  ">
							      	<div class="cls pt20"></div>
							       	<div class="clearfix">
								          <span class="fl ml20 mt7">金额(万元)</span> 
								          <input type='text' id="gongdiAmount" name='amount' size="10" onmouseover='this.title=this.value'  class='form-control tcfont ml10' readonly style="width:200px;display:inline;"  /> 
								     </div>
								     <div class="cls pt25"></div>
								      
								   </div>
							   </div>
						    </div>
						    
						    
						    
						    
						  </div>  
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModalView" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					查看截图
				</h4>
			</div>
			<div class="modal-body" style="min-height:300px;">
				<img id="showImg" width="100%" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div> 	 


<%@ include file="/common/script.jsp"%>
<%@ include file="/common/footer.html"%>
 
 