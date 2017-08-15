 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/common/header.jsp"%>
<%@ include file="/common/salereport_nav.jsp"%>
<%@ include file="/common/finace_script.jsp"%>
<script src="<c:url value="/js/jquery.fileDownload.js" />"></script>
<script src="<c:url value="/js/salesreport/salesReportManagement.js" />"></script>

<!-- Main bar -->
<div class="mainbar" >
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i> 销售报表管理
		</h2>
		<div class="clearfix"></div>
	</div>
	<!-- Page heading ends -->

	<!-- Matter -->
	<div class="matter">
		<div class="container ">
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						 <ul class="nav nav-tabs nav-tabssale">
						    <li onclick="queryReportDataByTab(1)" class="active"><a data-toggle="tab" href="#home" style="border-left:0;border-top:0;">诚意金台账</a></li>
						    <li onclick="queryReportDataByTab(2)"><a data-toggle="tab" href="#menu1"style="border-top:0;">收款日报表</a></li>
						    <li onclick="queryReportDataByTab(3)"><a data-toggle="tab" href="#menu2"style="border-top:0;">应收款报表 </a></li>	
						    <li onclick="queryReportDataByTab(4)"><a data-toggle="tab" href="#menu3"style="border-top:0;">收入结账报表 </a></li>	
						    <li onclick="queryReportDataByTab(5)"><a data-toggle="tab" href="#menu4"style="border-top:0;">开票情况报表</a></li>					    
						 </ul>
						 <div class="marginlr">
						 	<div class="tab-content">
							    <div id="home" class="tab-pane fade in active">
									<div class="row">
								        <div class="col-lg-2">
								            <div class="form-group" >
								                <select id="earnestMoneyRptcityCompanyIdQuery" onchange="queryProjectData(this.value, 'earnestMoneyRptcityProjectDataQuery')" class='form-control selectfont'></select>
								            </div>
								        </div>  
								        <div class="col-lg-2">
								            <div class="form-group" >
								                <select id="earnestMoneyRptcityProjectDataQuery" class='form-control selectfont'></select>
								            </div>
								        </div>
								        <div class="col-lg-3 col-lg-3skjl">
								        	<label class="col-lg-2 control-label pt7 connectlab">日期</label>
								            <div class="col-lg-10">
								            	<div class="form-group" >
									                <input type="text" class="form-control form-controlbg" 
									                name="date" id="datepickerstart">
								                </div>
								            </div>
								        </div>
								        <div class="col-lg-3" style="padding-right:0;">
								        	<div class="form-group">
								        		<button onclick="queryEarnestMoneyReportListBtn(1)"  type="button" class="btn btn-primary">查询</button>
								        		<button onclick="exportReportData(1)" type="button" class="btn btn-primary">导出</button>
								        		
								        	</div>
								        </div>  
								    </div>
								    <table class="table table-bordered fytab table-hover">
								        <thead>
								            <tr>
								                <th>项目名称</th>
								                <th>ERP房源名称</th>
								                <th>收款时间</th>
								                <th>姓名</th>
								                <th>收据号</th>
								                <th>实收金额</th>
								                <th>转房款</th>
								                <th>退转日期</th>
								                <th>结余金额</th>			
								            </tr>
								        </thead>
								        <tbody id="earnestMoneyRptListTable"></tbody>
								    </table>	
								    <div id="earnestMoneyRptListPageDiv" class="pagination pull-right"></div>
							    </div> 
							    <div id="menu1" class="tab-pane fade">
							    	 <div class="row">
								        <div class="col-lg-2">
								            <div class="form-group" >
								                <select id="moneyPayRptcityCompanyIdQuery" onchange="queryProjectData(this.value, 'moneyPayRptcityProjectDataQuery')" class='form-control selectfont'></select>
								            </div>
								        </div>  
								        <div class="col-lg-2">
								            <div class="form-group" >
								                <select id="moneyPayRptcityProjectDataQuery" class='form-control selectfont'></select>
								            </div>
								        </div>
								        <div class="col-lg-3 col-lg-3skjl">
								        	<label class="col-lg-2 control-label pt7 connectlab">日期</label>
								            <div class="col-lg-10">
								            	<div class="form-group" >
									                <input type="text" class="form-control form-controlbg" 
									                name="date" id="datepickerreport">
								                </div>
								            </div>
								        </div>
								        <div class="col-lg-3" style="padding-right:0;">
								        	<div class="form-group">
								        		<button onclick="queryMoneyPayReportListBtn(1)" type="button" class="btn btn-primary">查询</button>
								        		<button onclick="exportReportData(2)" type="button" class="btn btn-primary">导出</button>
								        		
								        	</div>
								        </div>  
								    </div>
								    <table class="table table-bordered fytab table-hover">
								        <thead>
								            <tr>
								                <th>日期</th>
								                <th>项目名称</th>
								                <th>楼栋</th>
								                <th>ERP房源名称</th>
								                <th>收据姓名</th>
								                <th>金额类型</th>
								                <th>收款方式</th>
								                <th>实收款</th>
								                <th>已收款</th>			
								            </tr>
								        </thead>
								        <tbody id="moneyPayRptListTable"></tbody>
								    </table>   
								    <div id="moneyPayRptListPageDiv" class="pagination pull-right"></div>
							    </div>
							    <div id="menu2" class="tab-pane fade"> 
									<div class="row">
								        <div class="col-lg-2 col-lg-2ysbb">
								            <div class="form-group" >
								                <select id="receivableMoneyRptcityCompanyIdQuery" onchange="queryProjectData(this.value, 'receivableMoneyRptcityProjectDataQuery')" class='form-control selectfont'></select>
								            </div>
								        </div>
								        
								        <div class="col-lg-2 col-lg-2sale">
								            <div class="form-group" >
								                <select id="receivableMoneyRptcityProjectDataQuery" class='form-control selectfont'></select>
								            </div>
								        </div>
								        
								        <div class="col-lg-2 col-lg-2yuqi" style="padding-right:0;margin-top:3px;">
							                <label class=" labelnew" >
							                    	只看逾期&nbsp;&nbsp;
							                </label>
							                <div  class="make-switch" data-on="success" data-off="warning" 
							                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
							                    <input type="checkbox" checked name="status">
							                </div> 
								        </div>
								        <div class="col-lg-2 col-lg-3skjl col-lg-2ysbb">
								        	<label class="col-lg-3 control-label pt7 connectlab">日期</label>
								            <div class="col-lg-9">
								            	<div class="form-group" >
									                <input type="text" class="form-control form-controlbg" 
									                name="date" id="datepickeryskbb">
								                </div>
								            </div>
								        </div>
								        <div class="col-lg-3 col-lg-3skjl ">
								        	<label class="col-lg-4 control-label pt7 connectlab">逾期时段</label>
								            <div class="col-lg-8 ">
								            	<div class="form-group" >
									                <select class='form-control selectfont'>
									                    <option>所有</option>
									                    <option>1-3个月</option>
									                    <option>3-6个月</option>
									                    <option>6-12个月</option>
									                    <option>1-2年</option>
									                    <option>2-3年</option>
									                    <option>3年以上</option>				
									                </select>
									            </div>
								            </div>
								        </div>
								        <div class="col-lg-2 col-lg-2ysbb">
								            <div class="form-group" >
								            	<button onclick="queryReceivableMoneyReportListBtn(1)" type="button" class="btn btn-primary">查询</button>
								                <button onclick="exportReportData(3)" type="button" class="btn btn-primary">导出</button>
								            </div>
								        </div>
								    </div>
									<table class="table table-bordered fytab table-hover">
								        <thead>
								            <tr>
								                <th>项目名称</th>
								                <th>ERP房源名称</th>
								                <th>客户姓名</th>
								                <th>应收日期</th>
								                <th>资金类型</th>
								                <th>合同金额</th>
								                <th>已收款</th>
								                <th>应收款</th>
								                <th>逾期原因</th>
								                <th>是否能收回</th>
								                <th>预计收回时间</th>
								                <th>其他原因</th>
								                <th>逾期时段</th>			
								            </tr>
								        </thead>
								        <tbody id="receivableMoneyRptListTable"></tbody>
								    </table>
									<div id="receivableMoneyRptListPageDiv" class="pagination pull-right"></div>
									
									
										
							    </div>
							    <div id="menu3" class="tab-pane fade"> 
									<div class="row">
								        <div class="col-lg-2">
								            <div class="form-group" >
								                <select id="incomeBillRptcityCompanyIdQuery" onchange="queryProjectData(this.value, 'incomeBillRptcityProjectDataQuery')" class='form-control selectfont'></select>
								            </div>
								        </div>  
								        <div class="col-lg-2">
								            <div class="form-group" >
								                <select id="incomeBillRptcityProjectDataQuery" class='form-control selectfont'></select>
								            </div>
								        </div>
								        <div class="col-lg-3 col-lg-3skjl">
								        	<label class="col-lg-2 control-label pt7 connectlab">日期</label>
								            <div class="col-lg-10">
								            	<div class="form-group" >
									                <input type="text" class="form-control form-controlbg" 
									                name="date" id="datepickersrjz">
								                </div>
								            </div>
								        </div>
								        <div class="col-lg-3" style="padding-right:0;">
								        	<div class="form-group">
								        		<button onclick="queryIncomeBillReportListBtn(1)" type="button" class="btn btn-primary">查询</button>
								        		<button onclick="exportReportData(4)" type="button" class="btn btn-primary">导出</button>
								        		
								        	</div>
								        </div>  
								    </div>
								    <table class="table table-bordered fytab table-hover">
								        <thead>
								            <tr>
								                <th>项目名称</th>
								                <th>ERP房源名称</th>
								                <th>客户姓名</th>
								                <th>实测面积</th>
								                <th>房源单价</th>
								                <th>房源总金额</th>
								                <th>是否已开交房发票</th>
								                <th>是否已全额预缴增值税</th>
								                <th>成本单价</th>			
								            </tr>
								        </thead>
								        <tbody id="incomeBillRptListTable"></tbody>
								    </table>		
								    <div id="incomeBillRptListPageDiv" class="pagination pull-right"></div>
							    </div>
							    <div id="menu4" class="tab-pane fade"> 
									<div class="row">
								        <div class="col-lg-2">
								            <div class="form-group" >
								                <select id="invoiceBillRptcityCompanyIdQuery" onchange="queryProjectData(this.value, 'invoiceBillRptcityProjectDataQuery')" class='form-control selectfont'></select>
								            </div>
								        </div>  
								        <div class="col-lg-2">
								            <div class="form-group" >
								                <select id="invoiceBillRptcityProjectDataQuery" class='form-control selectfont'></select>
								            </div>
								        </div>
								        <div class="col-lg-3 col-lg-3skjl">
								        	<label class="col-lg-2 control-label pt7 connectlab">日期</label>
								            <div class="col-lg-10">
								            	<div class="form-group" >
									                <input type="text" class="form-control form-controlbg" 
									                name="date" id="datepickerkpqk">
								                </div>
								            </div>
								        </div>
								        <div class="col-lg-3" style="padding-right:0;">
								        	<div class="form-group">
								        		<button onclick="queryInvoiceBillReportListBtn(1)" type="button" class="btn btn-primary">查询</button>
								        		<button onclick="exportReportData(5)" type="button" class="btn btn-primary">导出</button>
								        		
								        	</div>
								        </div>  
								    </div>
								    <table class="table table-bordered fytab table-hover">
								        <thead>
								        	<tr>
								                <th>项目名称</th>
								                <th>ERP房源名称</th>
								                <th>开票日期</th>
								                <th>票据类型</th>
								                <th>票据号</th>
								                <th>金额</th>
								                <th>金额类型</th>
								                <th>是否已预缴增值税</th>
								                <th>状态</th>			
								            </tr>
								            
								        </thead>
								        <tbody id="invoiceBillRptListTable"></tbody>
								    </table>
									<div id="invoiceBillRptListPageDiv" class="pagination pull-right"></div>
										
							    </div>
						   	</div> 
						 </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
 


<%@ include file="/common/finace_footer.jsp"%>
<script src="<c:url value="/js/custom.js" />"></script>
 