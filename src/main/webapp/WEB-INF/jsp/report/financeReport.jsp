 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/capital_nav.jsp"%>
<%@ include file="/common/finace_script.jsp"%>
<script src="<c:url value="/js/jquery.fileDownload.js" />"></script>
<script src="<c:url value="/js/financeReport.js" />"></script>
<script language="javascript"> 
	function checkLength(which) { 
	    var maxchar=100;  
		iCount = which.value.length; 
	    if(iCount<=maxchar) 
	    { 
	    which.size=iCount+3; 
	    } 
	} 
</script>  
<!-- Main bar -->
<div class="mainbar">
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i>资金列表
		</h2>
		<div class="clearfix"></div>
	</div>
	<!-- Page heading ends -->

	<!-- Matter -->
	<div class="matter">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						 <ul class="nav nav-tabs">
						    <li class="active"><a data-toggle="tab" href="#home" style="border-left:0;border-top:0;">城市公司</a></li>
						    <li><a data-toggle="tab" href="#menu1"style="border-top:0;">区域公司</a></li>
						    <li><a data-toggle="tab" href="#menu2"style="border-top:0;">短信 </a></li>					    
						  </ul>
						 <div class="marginlr">
						  <div class="tab-content">
						    <div id="home" class="tab-pane fade in active">
							     <div class="row">
							    	<div class="col-lg-2 col-lg-zjreport2">
								    	<div class="form-group">
								        	<input type="text" class="form-control form-controlbg" 
							        			name="date" id="datepicker">
								      	</div>
								    </div>
								    <div class="col-lg-2 ">
								      	<div class="form-group ">
											<select id="cityBranchCompanySel" onchange="querySubCompany(this.value)" class='form-control selectfont'>
											      <option value="">全部</option>
											      <option value="0">城市公司</option>
											      <option value="1">合资公司</option>
											</select>
										</div> 
									</div>
									<div class="col-lg-2">
										<div class="form-group">
											<select id="citySubCompanySel" class='form-control selectfont'></select>
										</div> 
									</div>
									<div class="col-lg-3 col-lg-zjreport4">
						                <label class="labelnew">只查未上报</label>
						                <div id="isLargeAmountQuery" class="make-switch" data-on="success" data-off="warning" 
						                    data-on-label="是" data-off-label="否" style="margin:0 10px; min-width:60px !important;" >
						                    <input type="checkbox" name="status">
						                </div>
                                        <button type="button" onclick="querycityFinanceReportBtn(1)" class="btn btn-primary mr10">确认</button>
						            </div>
									<div class="col-lg-3 " > 
										<form class="form-group clearfix">
								      	 	<button type="button" onclick="exportCityCapitalReportList()" class="btn btn-primary fr">导出资金报表</button> 
									      	<button type="button" onclick="exportCityBalanceReportList()" class="btn btn-primary fr mr10">导出余额报表</button>
							      		</form>
							      	</div>
							     </div> 
							    <div class=" cls"></div>
							    <table class="table table-bordered addtab">
									<thead>
										<tr>
											<th>城市公司/合资公司</th>
											<th>昨日余额（万元）</th>
											<th>当日余额（万元）</th>
											<th>收入（万元）</th>
											<th>支出（万元）</th>
											<th>不可用（万元）</th>
											<th>可用（万元）</th>
										</tr>
									</thead>
									<tbody id="cityFinanceReportTable"></tbody>
								</table>
								<div id="cityFinanceReportPageDiv" class="pagination pull-right"></div>
						    </div>
						    
						    <div id="menu1" class="tab-pane fade">
						    	<div class="row">
							    	<div class="col-lg-2">
								    	<div class="form-group">
								        	<input type="text" class="form-control form-controlbg" 
							        			name="date" id="datepickerpk">
								      	</div>
								    </div>
								    <div class="col-lg-2">
								      	<div class="form-group ">
											<select id="areaBranchCompanySel" onchange="queryBranchCompany(this.value)" class='form-control selectfont'>
											    <option value="">全部</option>
											    <option value="0">区域公司</option>
											    <option value="1">合资公司</option>
											</select>
										</div> 
									</div>
									<div class="col-lg-2">
										<div class="form-group">
											<select id="areaSubCompanySel" class='form-control selectfont'></select>
										</div> 
									</div>
									<div class="col-lg-2">
										<button type="button" onclick="queryareaFinanceReportBtn(1)" class="btn btn-primary">确认</button>
									</div>
									<div class="col-lg-4 " > 
										<form class="form-group clearfix">
								      	 	<button type="button" onclick="exportAreaCapitalReportList()" class="btn btn-primary fr">导出资金报表</button> 
									      	<button type="button" onclick="exportAreaBalanceReportList()" class="btn btn-primary fr mr10">导出余额报表</button>
							      		</form>
							      	</div>
							     </div> 
							    <div class=" cls"></div>
							    <table class="table table-bordered addtab">
									<thead>
										<tr>
											<th>区域公司/合资公司</th>
											<th>昨日余额（万元）</th>
											<th>当日余额（万元）</th>
											<th>收入（万元）</th>
											<th>支出（万元）</th>
											<th>不可用（万元）</th>
											<th>可用（万元）</th>
										</tr>
									</thead>
									<tbody id="areaFinanceReportTable"></tbody>
								</table>
								<div id="areaFinanceReportPageDiv" class="pagination pull-right"></div>
						    </div>
						    <div id="menu2" class="tab-pane fade">
							    <form class="form-inline" role="form">
							    	<div class="form-group fl">
							        	<input type="text" class="form-control form-controlbg" 
									        			name="date" id="datepickerimessage">
							      	</div>
							      	<button type="button" onclick="querySmsRecord()" class="btn btn-primary fl" style="margin-left:10px;margin-top:2px;">确认</button>
							      	<button id="smsSendBtn" onclick="sendSmsRecord()" type="button" class="btn btn-success fr">短信发送</button>
							    </form> 
								<div class="cls"></div>
								<div class="linehrsms"></div>
								<form role="form">
								  	<div class="form-group"> 
									  	<label class=" smslabelnew">资金余额</label>   
									    <input id="smsId" type="hidden"/>
									    <div id="selectCapitalBalanceTemplate" name="selectCapitalBalanceTemplate" class="form-control form-controltwo"  contenteditable="true" ></div>
								  	</div>
									<button id="resetContentBtn1" type="button" onclick="resetSmsContent('selectCapitalBalanceTemplate')" class="btn btn-warning ">重新生成 </button> 
									<!-- <button id="editSmsRecordBtn" type="button" onclick="editSmsRecord()" class="btn btn-primary ml5">修改</button> -->
									<button id="saveSmsRecordBtn1" type="button" onclick="addSmsRecord('content1', 'selectCapitalBalanceTemplate')" class="btn btn-primary ml5">提交</button> 
								    <!-- <button id="wechatSendBtn" type="button" disabled="disabled" class="btn btn-primary ml5">微信发送</button> -->
								</form>	
								<div class="linehrsms"></div>
								<form role="form">
								  	<div class="form-group"> 
									  	<label class=" smslabelnew">不可用资金差异</label>   
									    <input type="hidden"/>
									    <div id="selectInvalidCapitalTDiffTemplate" name="selectInvalidCapitalTDiffTemplate" class="form-control form-controltwo"  contenteditable="true"></div>
								  	</div>
									<button id="resetContentBtn2" onclick="resetSmsContent('selectInvalidCapitalTDiffTemplate')" type="button" class="btn btn-warning ">重新生成 </button> 
									<!-- <button type="button" class="btn btn-primary ml5">修改</button>-->
									<button id="saveSmsRecordBtn2" type="button" onclick="addSmsRecord('content2', 'selectInvalidCapitalTDiffTemplate')" class="btn btn-primary ml5">提交</button> 
								</form>	
								<div class="linehrsms"></div>
								<form role="form">
								  	<div class="form-group"> 
									  	<label class=" smslabelnew">收入明细</label>   
									    <input type="hidden"/>
									    <div id="selectIncomeDetailTemplate" name="selectIncomeDetailTemplate" class="form-control form-controltwo"  contenteditable="true"></div>
								  	</div>
									<button id="resetContentBtn3" onclick="resetSmsContent('selectIncomeDetailTemplate')" type="button" class="btn btn-warning ">重新生成 </button> 
									<!-- <button type="button" class="btn btn-primary ml5">修改</button>-->
									<button id="saveSmsRecordBtn3" type="button" onclick="addSmsRecord('content3', 'selectIncomeDetailTemplate')" class="btn btn-primary ml5">提交</button> 
								</form>	
								<div class="linehrsms"></div>
								<form role="form">
								  	<div class="form-group"> 
									  	<label class="smslabelnew">支出明细</label>   
									    <input type="hidden"/>
									    <div id="selectCostDetailTemplate" name="selectCostDetailTemplate" class="form-control form-controltwo" contenteditable="true"></div>
								  	</div>
									<button id="resetContentBtn4" onclick="resetSmsContent('selectCostDetailTemplate')" type="button" class="btn btn-warning ">重新生成 </button> 
									<!-- <button type="button" class="btn btn-primary ml5">修改</button>-->
									<button id="saveSmsRecordBtn4" type="button" onclick="addSmsRecord('content4', 'selectCostDetailTemplate')" class="btn btn-primary ml5">提交</button> 
								</form>	
								<div class="linehrsms"></div>
								<form role="form">
								  	<div class="form-group"> 
									  	<label class="smslabelnew">工抵余额</label>   
									    <input type="hidden"/>
									    <div id="selectWorkArrivedBalanceTemplate" name="selectWorkArrivedBalanceTemplate" class="form-control form-controltwo"  contenteditable="true"></div>
								  	</div>
									<button id="resetContentBtn5" onclick="resetSmsContent('selectWorkArrivedBalanceTemplate')" type="button" class="btn btn-warning ">重新生成 </button> 
									<!-- <button type="button" class="btn btn-primary ml5">修改</button>-->
									<button id="saveSmsRecordBtn5" type="button" onclick="addSmsRecord('content5', 'selectWorkArrivedBalanceTemplate')" class="btn btn-primary ml5">提交</button> 
								</form>	
								<div class="linehrsms"></div>
								<form role="form">
								  	<div class="form-group"> 
									  	<label class="smslabelnew">合资公司</label>   
									    <input type="hidden"/>
									    <div id="selectJointVentureTemplate" name="selectJointVentureTemplate" class="form-control form-controltwo"  contenteditable="true"></div>
								  	</div>
									<button id="resetContentBtn6" onclick="resetSmsContent('selectJointVentureTemplate')" type="button" class="btn btn-warning ">重新生成 </button> 
									<!-- <button type="button" class="btn btn-primary ml5">修改</button>-->
									<button id="saveSmsRecordBtn6" type="button" onclick="addSmsRecord('content6', 'selectJointVentureTemplate')" class="btn btn-primary ml5">提交</button> 
								</form>	
						    </div>
						    </div> 
						  </div>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>

  
	
	 
    
    <!-- 导出城市公司-余额报表 -->
    <form action="exportCityBalanceReportList.shtml" id="exportCityBalanceReportListForm" method="post">
        <input type="hidden" id="cityUserIdQuery" name="cityUserIdQuery"/>
        <input type="hidden" id="cityCurrentDateQuery" name="cityCurrentDateQuery"/>
        <input type="hidden" id="cityBranchCompanyQuery" name="cityBranchCompanyQuery"/>
        <input type="hidden" id="citySubCompanyQuery" name="citySubCompanyQuery"/>
    </form>
    
    <!-- 导出区域公司-余额报表 -->
    <form action="exportAreaBalanceReportList.shtml" id="exportAreaBalanceReportListForm" method="post">
        <input type="hidden" id="areaUserIdQuery" name="areaUserIdQuery"/>
        <input type="hidden" id="areaCurrentDateQuery" name="areaCurrentDateQuery"/>
        <input type="hidden" id="areaBranchCompanyQuery" name="areaBranchCompanyQuery"/>
        <input type="hidden" id="areaSubBranchCompanyQuery" name="areaSubBranchCompanyQuery"/>
    </form>
    
    <!-- 导出城市公司-资金报表 -->
    <form action="exportCityCapitalReportList.shtml" id="exportCityCapitalReportListForm" method="post">
        <input type="hidden" id="cityCapitalUserIdQuery" name="cityUserIdQuery"/>
        <input type="hidden" id="cityCapitalCurrentDateQuery" name="cityCurrentDateQuery"/>
        <input type="hidden" id="cityCapitalBranchCompanyQuery" name="cityBranchCompanyQuery"/>
        <input type="hidden" id="cityCapitalSubCompanyQuery" name="citySubCompanyQuery"/>
    </form>
    
    <!-- 导出区域公司-资金报表 -->
    <form action="exportAreaCapitalReportList.shtml" id="exportAreaCapitalReportListForm" method="post">
        <input type="hidden" id="areaCapitalUserIdQuery" name="areaUserIdQuery"/>
        <input type="hidden" id="areaCapitalCurrentDateQuery" name="areaCurrentDateQuery"/>
        <input type="hidden" id="areaCapitalBranchCompanyQuery" name="areaBranchCompanyQuery"/>
        <input type="hidden" id="areaCapitalSubCompanyQuery" name="areaSubCompanyQuery"/>
    </form>
    
<%@ include file="/common/finace_footer.jsp"%>

	 
</script>