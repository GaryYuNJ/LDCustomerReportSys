 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/common/header.jsp"%>
<%@ include file="/common/salereport_nav.jsp"%>
<%@ include file="/common/finace_script.jsp"%>
<script src="<c:url value="/js/ajaxfileupload.js" />"></script>
<script src="<c:url value="/js/jquery.fileDownload.js" />"></script>
<script src="<c:url value="/js/salesreport/housingManagement.js" />"></script>
<!-- Main bar -->
<div class="mainbar">
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i> 房源管理
		</h2>
		<div class="clearfix"></div>
	</div>
	<!-- Page heading ends -->

	<!-- Matter -->
	<div class="matter">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget widgetnewsale">
						<div class="widget-head">
						    <div class="pull-left">房源列表</div>
						    <div class="widget-icons pull-right">
						        <a href="#" class="wminimize" id="icon_group_list1"><i
						            class="icon-chevron-up"></i></a>
						    </div>
						    <div class="clearfix"></div>
						</div>
						<div class="widget-content clearfix">
						    <div class="row">
						        <div class="col-lg-2">
						            <div class="form-group" >
						                <select id="cityCompanyIdQuery" class='form-control selectfont'></select>
						            </div>
						        </div>
						        <div class="col-lg-2">
						            <div class="form-group" >
						                  <input id="projectNameQuery" type="text" class="form-control form-controlbg" name="" placeholder="项目名称模糊查询" >
						            </div>
						        </div>
						        <div class="col-lg-2">
						            <div class="form-group" >
						                <input id="projectPhaseQuery" type="text" class="form-control form-controlbg" name="" placeholder="分期名称模糊查询" >
						            </div>
						        </div>
						        <div class="col-lg-2">
						            <div class="form-group" >
						                <select id="houseTypeIdQuery" class='form-control selectfont'></select>
						            </div>
						        </div>
						        <div class="col-lg-2">
						            <div class="form-group" >
						                <select id="houseBusinessTypeIdQuery" class='form-control selectfont'></select>
						            </div>
						        </div>
						        <div class="col-lg-2">
						            <div class="form-group" style="text-align:right;">
						                <a id="fileUploadHref" href="javascript:;" class="blue-btn op-btn  a-upload fileUploadHref " ><i class="i-upload"></i>
						                <input type="file" name="uploadFile" id="uploadFile" attr="1" title="上传截图">导入房源</a>
						            </div>
						        </div>
						        <div class="cls "></div>
						        <div class="col-lg-2">
						            <div class="form-group" >
						                <input id="houseConsultantQuery" type="text" class="form-control form-controlbg" name="" placeholder="置业顾问名字" >
						            </div>
						        </div>
						        <div class="col-lg-2">
						            <div class="form-group" >
						                <input id="customerNameQuery" type="text" class="form-control form-controlbg" name="" placeholder="客户名字" >
						            </div>
						        </div>
						        <div class="col-lg-2">
						            <div class="form-group" >
						                <input id="erpNameQuery" type="text" class="form-control form-controlbg" name="" placeholder="ERP房源名称" >
						            </div>
						        </div>
						        <div class="col-lg-4">
						            
						                <label class=" labelnew" >
						                    	只看大单定制&nbsp;&nbsp;&nbsp;&nbsp;
						                </label>
						                <div id="isLargeAmountQuery" class="make-switch" data-on="success" data-off="warning" 
						                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
						                    <input type="checkbox" checked name="status">
						                </div> 
						                <button onclick="queryHouseListBtn(1)" type="button" class="btn btn-primary">查询</button>
						           
						        </div>
						        <div class="col-lg-2">
						            <div class="form-group" style="text-align:right;">
						                <button onclick="exportHouseData()" type="button" class="btn btn-primary">导出报表</button>
						            </div>
						        </div>
						    </div>
						    <div class="cls "></div>
						    <table class="table table-bordered fytab table-hover">
						        <thead>
						            <tr>
						                <th>项目名称</th>
						                <th>分期</th>
						                <th>楼栋</th>
						                <th>ERP房源名称</th>
						                <th>类型</th>
						                <th>置业顾问</th>
						                <th>客户</th>
						                <th>业态大类</th>
						                <th>状态</th>
						                <th>操作</th>			
						            </tr>
						        </thead>
						        <tbody id="houseListTable"></tbody>
						    </table>
						    <div id="houseListPageDiv" class="pagination pull-right"></div>
						</div>
						<div class="pt10"></div>
						<div class="widget-head">
						    <div class="pull-left">房源详情</div>
						    <div class="widget-icons pull-right">
						        <a href="#" class="wminimize" id="icon_group_list2"><i
						            class="icon-chevron-down"></i></a>
						    </div>
						    <div class="clearfix"></div>
						</div>
						<div class="widget-content widget-contentinfo" style="display: none;">
							<ul id="houseSubTab" class="nav nav-tabs nav-tabsfymoney">
							    <li class="active"><a data-toggle="tab" href="#home" style="border-left:0;border-top:0;">基础信息</a></li>
							    <li onclick="queryUserHouseRel()"><a data-toggle="tab" href="#menu1"style="border-top:0;">房源关系</a></li>
							    <li onclick="queryEarnestMoney()"><a data-toggle="tab" href="#menu2"style="border-top:0;">诚意金 </a></li>
							    <li onclick="queryContract()"><a data-toggle="tab" href="#menu3"style="border-top:0;">合同信息</a></li>
							    <li onclick="queryMoneyPayDetail()"><a data-toggle="tab" href="#menu4"style="border-top:0;">收款记录 </a></li>
							    <li onclick="queryReceiptInvoiceData()"><a data-toggle="tab" href="#menu5"style="border-top:0;">收据发票</a></li>
							    <li><a data-toggle="tab" href="#menu6"style="border-top:0;">房源佣金配置 </a></li>
							    <li><a data-toggle="tab" href="#menu7"style="border-top:0;">日结佣金</a></li> 
							    <li><a data-toggle="tab" href="#menu8"style="border-top:0;">季度奖金</a></li> 
							    <li><a data-toggle="tab" href="#menu9"style="border-top:0;">年度奖金</a></li>
							    <li><a data-toggle="tab" href="#menu10"style="border-top:0;">交房奖金</a></li>
							       					    
							</ul>
							<input id="house_main_id" type="hidden"/>	
							<div class="tab-content tab-contentnew">
								<div id="home" class="tab-pane fade in active">
								    <form id="houseMsgForm">	
								    <input id="house_user_id" name="userId" type="hidden"/>		
								    <input id="house_id" name="house_id" type="hidden"/>
								    <input id="is_large_amount" name="is_large_amount" type="hidden"/>
								    <input id="is_carry_forward" name="is_carry_forward" type="hidden"/>
								    <input id="is_change_name" name="is_change_name" type="hidden"/>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">项目名称</label>
								            <div class="col-lg-8">
								                <select id="project_id" name="project_id" class='form-control selectfont'></select>
								            </div>
								        </div>
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">分期</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg" 
								               id="project_phase" name="project_phase" placeholder="spa房源ID" >
								            </div>
								        </div>
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">楼栋</label>
								            <div class="col-lg-8">
								                <select id="building_id" name="building_id" class='form-control selectfont'></select>
								            </div>
								        </div>
								    </div>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">spa房源ID</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg" 
								               id="erp_code" name="erp_code" placeholder="spa房源ID" >
								            </div>
								        </div>
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">spa房源名称</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg" 
								                id="erp_name" name="erp_name" placeholder="spa房源名称" >
								            </div>
								        </div>
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">销售凭证</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg" 
								                id="erp_sale_code" name="erp_sale_code" placeholder="销售凭证" >
								            </div>
								        </div>
								    </div>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">是否大单定制</label>
								            <div class="col-lg-8" style="line-height:0;">
								                <div id="is_large_amount_div" class="make-switch" data-on="success" data-off="warning" 
								                    data-on-label="是" data-off-label="否" >
								                    <input type="checkbox">
								                </div>
								            </div>
								        </div>
								    </div>
								    <div class="linehr"></div>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">业务大类</label>
								            <div class="col-lg-8">
								                <select id="business_type_id" name="business_type_id" class='form-control selectfont'></select>
								            </div>
								        </div>
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">业务细类</label>
								            <div class="col-lg-8">
								                <select id="business_sub_type_id" name="business_sub_type_id" class='form-control selectfont'></select>
								            </div>
								        </div>
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">销售类型</label>
								            <div class="col-lg-8">
								                <select id="sale_type_id" name="sale_type_id" class='form-control selectfont'></select>
								            </div>
								        </div>
								    </div>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">房源类型</label>
								            <div class="col-lg-8">
								                <select id="type_id" name="type_id" class='form-control selectfont'></select>
								            </div>
								        </div>
								        <div class="col-lg-4"> 
								            <label class="col-lg-3fy control-label pt7">预售面积</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg" 
								                onkeyup="countContractTotalPrice();so.clearToNum(this)" id="pre_size" name="pre_size" onkeyup='' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  placeholder="预售面积" >
								            </div>
								        </div>	 
								    </div>
								    <div class="linehr"></div>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">状态</label>
								            <div class="col-lg-8">
								                <select id="status_id" name="status_id" class='form-control selectfont'></select>
								            </div>
								        </div>
								    </div>
								    <div class="linehr"></div>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">是否结转成本</label>
								            <div class="col-lg-8">
								                <div id="is_carry_forward_div" class="make-switch" data-on="success" data-off="warning" 
								                    data-on-label="是" data-off-label="否" >
								                    <input type="checkbox" checked>
								                </div>
								            </div>
								        </div>
								        <div class="col-lg-4"> 
								            <label class="col-lg-3fy control-label pt7">结转收入金额</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
								                id="carry_forward_income" name="carry_forward_income" >
								            </div>
								        </div>	
								        <div class="col-lg-4"> 
								            <label class="col-lg-3fy control-label pt7">结转成本单价</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
								                id="carry_forward_cost_unit_price" name="carry_forward_cost_unit_price" >
								            </div>
								        </div>	
								    </div>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">实测面积</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
								                id="real_size" name="real_size" >
								            </div>
								        </div>
								    </div>
								    <div class="linehr"></div>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">客户编码</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg" 
								                id="customer_code" name="customer_code" >
								            </div>
								        </div>
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">客户名称</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg" 
								                id="customer_name" name="customer_name" >
								            </div>
								        </div>
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">客户手机号</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg" 
								                id="customer_mobile" name="customer_mobile" >
								            </div>
								        </div>
								    </div>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-8">
								            <label class="col-lg-3fynew control-label pt7">客户身份证</label>
								            <div class="col-lg-8fy">
								                <input type="text" class="form-control form-controlbg" 
								                id="customer_number" name="customer_number" >
								            </div>
								        </div>
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">是否更名</label>
								            <div class="col-lg-8">
								                <div id="is_change_name_div" class="make-switch" data-on="success" data-off="warning" 
								                    data-on-label="是" data-off-label="否" >
								                    <input type="checkbox" checked>
								                </div>
								            </div>
								        </div>
								    </div>
								    <div class="linehr"></div>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">最后更新人</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg" 
								                id="house_update_user_id" name="" disabled >
								            </div>
								        </div>
								        <div class="col-lg-4">
								            <label class="col-lg-3fy control-label pt7">更新时间</label>
								            <div class="col-lg-8">
								                <input type="text" class="form-control form-controlbg" 
								                id="house_update_time" name="date"  id="datepicker" disabled  style="background-color: rgb(238, 238, 238);">
								            </div>
								        </div>	 
								    </div>
								    <div class="form-group groupheight clearfix">
								        <div class="col-lg-12">
								            <label class="col-lg-3fynewbz control-label pt7">备注</label>
								            <div class="col-lg-13">
								                <textarea id="remark" name="remark" class="form-control form-controlbg" rows="5" placeholder="多行输入"></textarea>
								            </div>
								        </div>  
								    </div>	
									<div class="cls pt5"></div>
									<div class="form-group" style="text-align:center;">
										<button onclick="saveHouseMsg()" type="button" class="btn btn-primary">全部保存</button>
									</div>
									</form>
								</div>
								<div id="menu1" class="tab-pane fade">
									<div class="containernewfy">
										<div class="row">
											<div class="col-lg-2">
										    	<div class="form-group">
										        	<select id="appointmentsQuery" class='form-control selectfont'  ></select>
										      	</div>
									      	</div>
									      	<div class="col-lg-2">
										    	<div class="form-group">
			                   						<input type="text" class="form-control form-controlbg" 
										        			name="" placeholder="姓名">
										      	</div>
									      	</div>
									      	<div class="col-lg-6 btnpd"> 
									      		<label class=" control-label pt7 connectlab">只显示有关系的&nbsp;&nbsp;&nbsp;&nbsp;</label>
									      		<div  class="make-switch" data-on="success" data-off="warning" 
													data-on-label="有" data-off-label="无" >
													<input type="checkbox" checked name="status">
												</div>
										        <button type="submit" class="btn btn-primary" style="margin-left:8px;">查询</button>	 
									        </div> 
									     </div>
									     <div class="cls"></div>
									     <table class="table table-bordered fytab table-hover">
											<thead>
												<tr>
													<th>职位</th>
													<th>姓名</th>
													<th width="15%">手机号</th> 
													<th width="10%">是否有关系</th> 
													<th>关系起始时间</th>
													<th>关系终止时间</th>
													<th>操作</th>			
												</tr>
											</thead>
											<tbody>
												<tr>
													<td></td>
													<td></td>
													<td></td>
													<td class="tc">
														<span class="label label-success">是</span>
													</td>
													<td></td>
													<td></td>
													<td class="tc">
														 <button class="btn btn-xs btn-warning" title="修改关系" data-toggle='modal' data-target='#myModalEditRelation'><i class="icon-pencil"></i> </button>
													</td>
												</tr> 								  
											</tbody>
										</table>
									</div>
								</div>
								<div id="menu2" class="tab-pane fade">
								    <form id="earnestMoneyForm">	
								    <input id="earnest_money_main_id" name="id" type="hidden"/>
								    <input id="earnest_money_user_id" name="userId" type="hidden"/>		
								    <input id="earnest_money_house_id" name="house_id" type="hidden"/>
									<div class=" ">
									    <div class="form-group groupheight clearfix">
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">收款方式</label>
									            <div class="col-lg-8">
									                <select id="pay_type" name="pay_type" class='form-control selectfont'>
									                    <option value="1">现金</option>
									                    <option value="2">pos</option>
									                    <option value="3">银行转账</option>					
									                </select>
									            </div>
									        </div>
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">收款时间</label>
									            <div class="col-lg-8">
									                 <input type="text" class="form-control form-controlbg" 
								        			name="pay_time"   id="datepickerpay">
									            </div>
									        </div>
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">状态</label>
									            <div class="col-lg-8">
									                <select id="earnest_money_status" name="status" class='form-control selectfont'>
									                    <option value="1">已收款</option>
									                    <option value="2">已退款</option>
									                    <option value="3">已转房款</option>					
									                </select>
									            </div>
									        </div>
									    </div> 
									    <div class="linehr"></div>
									    <div class="form-group groupheight clearfix">
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">pos金额</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
								        			id="pos_amount" name="pos_amount">
									            </div>
									        </div>
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">现金金额</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
								        			id="cash_amount" name="cash_amount">
									            </div>
									        </div>
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">银行转账金额</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
								        			id="bank_transfer_amount" name="bank_transfer_amount">
									            </div>
									        </div>
									    </div>
									    <div class="form-group groupheight clearfix">
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">银行票据金额</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
								        			id="bank_bill_amount" name="bank_bill_amount">
									            </div>
									        </div>
									        <div class="col-lg-4"> 
									            <label class="col-lg-3fy control-label pt7">银行票据</label>
									            <div class="col-lg-8">
									                <select id="bank_bill_type" name="bank_bill_type" class='form-control selectfont'>
									                    <option value="1">本票</option>
									                    <option value="2">支票</option>	
									                    <option value="3">汇票</option>					
									                </select>
									            </div>
									        </div>
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">入账行</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
								        			id="bank_name" name="bank_name">
									            </div>
									        </div>	 
									    </div>
									    <div class="linehr"></div>
									    <div class="form-group groupheight clearfix">
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">换据金额</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
								        			id="receipt_amount" name="receipt_amount">
									            </div>
									        </div>
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">结余金额</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
								        			id="remain_amount" name="remain_amount">
									            </div>
									        </div>
									    </div>
									    <div class="form-group groupheight clearfix">
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">退款金额</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
								        			id="refund_amount" name="refund_amount">
									            </div>
									        </div>
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">退款支票号</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
								        			id="refund_check_number" name="refund_check_number">
									            </div>
									        </div>
									    </div>
									    <div class="linehr"></div>
									    <div class="form-group groupheight clearfix"> 
									        <div class="col-lg-4"> 
									            <label class="col-lg-3fy control-label pt7">转房款金额</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
									                id="convert_house_amount" name="convert_house_amount">
									            </div>
									        </div>	
									    </div> 
									    <div class="linehr"></div>
									    <div class="form-group groupheight clearfix">
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">最后更新人</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
									                id="earnest_money_update_user_id" name="" disabled >
									            </div>
									        </div>
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">更新时间</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
									                name="date"   id="datepickercyj" disabled>
									            </div>
									        </div>	 
									    </div>
									    <div class="form-group groupheight clearfix">
									        <div class="col-lg-12">
									            <label class="col-lg-3fynewbz control-label pt7">备注</label>
									            <div class="col-lg-13">
									                <textarea id="earnest_money_remark" name="remark" class="form-control form-controlbg" rows="5" placeholder="多行输入"></textarea>
									            </div>
									        </div>  
									    </div>	
									</div>
									<div class="cls pt5"></div>
									<div class="form-group" style="text-align:center;">
										<button onclick="saveEarnestMoney()" type="button" class="btn btn-primary">全部保存</button>
									</div>
								    </form>
								</div>
								<div id="menu3" class="tab-pane fade">
									<div class="pd10">
										<div class="widget-head widget-headbg">
										    <span>基础信息</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize" id="icon_group_list3"><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content clearfix widget-contentpd10">
										    <form id="houseContractMsgForm">
										    <input id="house_contract_user_id" name="userId" type="hidden"/>
										    <input id="house_contract_house_id" name="house_id" type="hidden"/>
										    <input id="house_contract_id" name="id" type="hidden"/>
											<div class="form-group groupheight clearfix">
												<div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">合同编号</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg" 
										                id="contract_code" name="contract_code" >
										            </div>
										        </div>
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">购买方式</label>
										            <div class="col-lg-8">
										            	<select id="buy_method_id" name="buy_method_id" class='form-control selectfont'></select>  
										            </div>
										        </div>  
										    </div>
											<div class="form-group groupheight clearfix">
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">合同单价</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
										                onkeyup="countContractTotalPrice()" id="unit_price" name="unit_price" >
										            </div>
										        </div>
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">签约时间</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg" 
										                name="sign_date"  id="datepickerqytime">
										            </div>
										        </div>
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">合同签订时间</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg" 
										                name="verification_date"  id="datepickerhtqytime">
										            </div>
										        </div>
										    </div>
										    <div class="form-group groupheight clearfix">
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">违约金比率</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
										                id="late_fee_ratio" name="late_fee_ratio" >
										            </div>
										        </div> 
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">总价</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg"  onkeyup='setContractTotalPrice();so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
										                id="total_price" name="total_price">
										            </div>
										        </div> 
										    </div>
										    <div class="linehr"></div>
										    <div class="form-group groupheight clearfix">
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">最后更新人</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg" 
										                id="contract_basic_update_user_id" name="update_user" disabled >
										            </div>
										        </div>
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">更新时间</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg" 
										                name="update_time"  id="datepickerlastrefresh" disabled>
										            </div>
										        </div>	 
										    </div>
										    <div class="form-group groupheight clearfix">
										        <div class="col-lg-12">
										            <label class="control-label pt7 col-lg-3fynewbz col-lg-3fynewbz2">备注</label>
										            <div class=" col-lg-13new">
										                <textarea id="contract_basic_remark" name="remark" class="form-control form-controlbg" rows="5" placeholder="多行输入"></textarea>
										            </div>
										        </div>  
										    </div>	
											<div class="cls pt5"></div>
											<div class="form-group" style="text-align:center;margin-bottom:0;">
												<button onclick="saveHouseContractMsg()" type="button" class="btn btn-primary">保存</button>
											</div>	
											</form>
										</div>
										<div class="cls pt10"></div>
										<div class="widget-head widget-headbg">
										    <span>按揭信息</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize" id="icon_group_list4"><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content clearfix widget-contentpd10">
										    <form id="houseMortgageMsgForm">
										    <input id="house_mortgage_user_id" name="userId" type="hidden"/>
										    <input id="house_mortgage_house_id" name="house_id" type="hidden"/>
										    <input id="house_mortgage_contract_id" name="contract_id" type="hidden"/>
										    <input id="house_mortgage_id" name="id" type="hidden"/>
											<div class="form-group groupheight clearfix">
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">按揭类型</label>
										            <div class="col-lg-8">
										            	<select id="mortgage_type_id" name="mortgage_type_id" class='form-control selectfont'></select>  
										            </div>
										        </div>
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">首付比例</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
										               id="downpayments_ratio" name="downpayments_ratio" >
										            </div>
										        </div>
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">首付金额</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
										                id="downpayments_amount" name="downpayments_amount" >
										            </div>
										        </div>
										    </div>
											<div class="form-group groupheight clearfix">
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">公积金贷款金额</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
										                id="public_fund_loan_amount" name="public_fund_loan_amount" >
										            </div>
										        </div>
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">商业贷款金额</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
										                id="business_loan_amount" name="business_loan_amount" >
										            </div>
										        </div>
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">贷款行</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg" 
										                id="loan_bank" name="loan_bank" >
										            </div>
										        </div>
										    </div>
										    <div class="form-group groupheight clearfix">
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">抵款单位</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg" 
										                id="mortgage_department" name="mortgage_department" >
										            </div>
										        </div> 
										    </div>
										    <div class="linehr"></div>
										    <div class="form-group groupheight clearfix">
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">最后更新人</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg" 
										                id="house_mortgage_update_user_id" name="" disabled >
										            </div>
										        </div>
										        <div class="col-lg-4">
										            <label class="col-lg-4 control-label pt7">更新时间</label>
										            <div class="col-lg-8">
										                <input type="text" class="form-control form-controlbg" 
										                id="datepickerlast" disabled>
										            </div>
										        </div>	 
										    </div>
										    <div class="form-group groupheight clearfix">
										        <div class="col-lg-12">
										            <label class="col-lg-3fynewbz control-label pt7 col-lg-3fynewbz2">备注</label>
										            <div class=" col-lg-13new">
										                <textarea id="house_mortgage_basic_remark" name="remark" class="form-control form-controlbg" rows="5" placeholder="多行输入"></textarea>
										            </div>
										        </div>  
										    </div>	
											<div class="cls pt5"></div>
											<div class="form-group" style="text-align:center;margin-bottom:0;">
												<button onclick="saveHouseMortgageMsg()" type="button" class="btn btn-primary">保存</button>
											</div>	
											</form>
										</div> 
										<div class="cls pt10"></div>
										<div class="widget-head widget-headbg">
										    <span>合同金额信息</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize" id="icon_group_list5"><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content  widget-contentpd10 clearfix">
											<table class="table table-bordered fytab table-hover">
										        <thead>
										            <tr>
										                <th>金额类型</th>
										                <th>应收金额</th>
										                <th>应收款时间</th>
										                <th>已收款</th>
										                <th>状态</th>
										                <th>是否逾期</th>
										                <th>备注</th>
										                <th>操作</th>			
										            </tr>
										        </thead>
										        <tbody id="contractMoneyDataListTable"></tbody>
										    </table>
										    <div class="cls pt10"></div>
										    <div class="form-group form-groupnew" style="text-align:center;">
												<button onclick="toSaveHouseContractMoneyMsgPage('add', '');" type="button" class="btn btn-primary">添加</button>
											</div>
										</div>
									</div>
								</div>
								<div id="menu4" class="tab-pane fade">
									 <div class="skjlhd">
										<div class="row">	 
											<div class="col-lg-3  col-lg-3skjl">
												<label class="col-lg-4 control-label pt7 connectlab">合同总金额</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
									                id="money_pay_detail_contract_total" name="" disabled  value="">
									            </div>
									        </div>
									        <div class="col-lg-3  col-lg-3skjl" >
									            <label class="col-lg-3 control-label pt7 connectlab">已收款</label>
									            <div class="col-lg-9">
									                <input type="text" class="form-control form-controlbg" 
									                id="money_pay_detail_sure_total" name="" disabled value="">
									            </div>
									        </div>
									        <div class="col-lg-4  col-lg-3skjl">
									            <label class="col-lg-3 control-label pt7 connectlab">ERP总收款</label>
									            <div class="col-lg-9">
									                <input type="text" class="form-control form-controlbg" 
									                id="money_pay_detail_erp_total" name="" disabled value="">
									            </div>
									        </div> 
									        <div class="col-lg-2 btnpt" >
									            <div class="form-group" style="text-align:right;">
									                <button type="button" onclick="toEditMoneyPayDetailMsgPage('add', '')" class="btn btn-primary">添加收款记录</button>
									            </div>
									        </div> 
									     </div>
									  </div>       
								      <div class="containernewfy">
										    <table class="table table-bordered fytab table-hover">
										        <thead>
										            <tr>
										                <th>类型</th>
										                <th>应收总金额</th>
										                <th>应收款时间</th>
										                <th>收到金额</th>
										                <th>收款时间</th>
										                <th>收款方式</th>  
										                <th>是否逾期</th>
										                <th>操作</th>			
										            </tr>
										        </thead>
										        <tbody id="moneyPayDetailListTable"></tbody>
										    </table>
								      </div>    
								</div>
								<div id="menu5" class="tab-pane fade">
									<div class="skjlhd">
										<div class=" row">	 
											<div class="col-lg-3 col-lg-3skjl">
												<label class="col-lg-2 control-label pt7 connectlab">类型</label>
									            <div class="col-lg-10">
									            	<select class='form-control selectfont'>
									                    <option value="">全部</option>
									                    <option value="1">收据</option>
									                    <option value="2">普票</option>
									                    <option value="3">专票</option>	
									                </select>
									                
									            </div>
									        </div>
								            <div class="col-lg-7 btnpt">
								            	<div class="form-group" >
									                <button type="button" class="btn btn-primary">查询</button>
									            </div>
								            </div>                
									        <div class="col-lg-2">
									            <div class="form-group" style="text-align:right;">
									                <button onclick="toEditReceiptInvoiceDataMsgPage('add', '')" type="button" class="btn btn-primary">添加收据/发票</button>
									            </div>
									        </div> 
										</div>
									</div>
									        
							        <div class="containernewfy">
									    <table class="table table-bordered fytab table-hover">
									        <thead>
									            <tr>
									                <th>票据类型</th>
									                <th>开票时间</th>
									                <th>票据抬头</th>
									                <th>票据号</th>
									                <th>金额</th>
									                <th>税率（%）</th> 
									                <th>税额</th> 
									                <th>状态</th>
									                <th>原收据号</th>
									                <th>操作</th>			
									            </tr>
									        </thead>
									        <tbody id="receiptInvoiceListTable"></tbody>
									    </table>
							        </div>    
										
								
								</div>
								<div id="menu6" class="tab-pane fade">
									<div class="pd10">
										<div class="widget-head widget-headbg">
										    <span>佣金计算方式</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize"><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content  widget-contentpd10 clearfix">
										 
								            <label class=" fl control-label pt7 labelnew">佣金计算方式</label>
								            <div class="ml20  fl">
								                <select class="form-control selectfont">
								                	<option value="1">固定金额</option>
								                	<option value="2">按佣金提点</option>
								                		<option value="3">不计佣金</option>  
								               	</select>
								            </div>
								            <div class="ml20 fl">
												<button  type="button" class="btn btn-primary">保存</button>
											 </div>
									      
									        
										</div>
										<div class="cls pt10"></div>
										<div class="widget-head widget-headbg">
										    <span>资金到账与日结佣金提取比例关系</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize"><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content  widget-contentpd10 clearfix">
											<div class=" ">
												<table class="table" style="text-align:center;">
													<thead>
												      <tr>
												        <th class="tc" width="27%">收款起始比例（%）</th>
												        <th class="tc"  width="27%">收款截至比例（%）</th>
												        <th class="tc">日结可提取比例（%）</th>
												        <th width="15%" class="tc">操作</th>
												      </tr>
												    </thead>
												    <tbody id="fymoneyTab">
												    </tbody>
												</table> 
											</div>
										    <div class="cls pt10"></div>
										    <div class="form-group form-groupnew" style="text-align:center;">
												<button  type="button" class="btn btn-primary" id="addFymoney">新增</button>
											</div>
										</div>
										<div class="cls pt10"></div>
										<div class="widget-head widget-headbg">
										    <span>当前房源与职位对应的固定金额/佣金提点</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize"><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content  widget-contentpd10 clearfix">
											<div class=" ">
												<table class="table" style="text-align:center;">
													<thead>
												      <tr> 
												        <th class=" tc" width="27%">职位</th>
												        <th class="tc"  width="27%">佣金提点</th>
												        <th class="tc">固定金额</th>
												        <th width="15%" class="tc">操作</th>
												      </tr>
												    </thead>
												    <tbody id="fyptzTab">
												    </tbody>
												</table> 
											</div>
										    <div class="cls pt10"></div>
										    <div class="form-group form-groupnew" style="text-align:center;">
												<button  type="button" class="btn btn-primary" id="addFyptz">新增</button>
											</div>
										</div>
									</div>	
								</div>
								<div id="menu7" class="tab-pane fade">日结佣金</div>
								<div id="menu8" class="tab-pane fade">季度奖金</div>
								<div id="menu9" class="tab-pane fade">年度奖金</div>
								<div id="menu10" class="tab-pane fade">交房奖金</div>
							</div>
						</div> 
					
					</div>
				</div>
			</div>
			</div>
		</div>
</div>


<div class="modal fade" id="myModalContract" tabindex="-1" role="dialog" aria-labelledby="contractMoneyTitle" aria-hidden="true" >
	<div class="modal-dialog modal-dialognew">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="contractMoneyTitle">
					合同金额详情
				</h4>
			</div>
			<form id="contractMoneyEditForm">
		    <input id="contract_money_id" name="id" type="hidden"/>
		    <input id="contract_money_user_id" name="userId" type="hidden"/>
		    <input id="contract_money_house_id" name="house_id" type="hidden"/>
		    <input id="is_overdue" name="is_overdue" type="hidden"/>
		    <input id="is_recover_available" name="is_recover_available" type="hidden"/>
			<div class="modal-body"> 
			    <div class="form-group groupheight clearfix"> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">金额类型</label>
			            <div class="col-lg-7">
			                <select id="contract_money_type_id" name="money_type_id" class="form-control selectfont"></select>
			            </div>
			        </div>	  
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">合同金额</label>
			            <div class="col-lg-7">
			                <input id="contract_money_amount" name="amount" onkeyup="$('#contract_money_sure_amount').val(this.value);so.clearToNum(this);" onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'   type="text" class="form-control form-controlbg" value="">		
			            </div>
			        </div>	
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">应收款时间</label>
			            <div class="col-lg-7">
			                 <input type="text" class="form-control form-controlbg" 
			                name="receivables_time"   id="datepickerhtget">
			            </div>
			        </div>  
			    </div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">状态</label>
			            <div class="col-lg-7">
			                <select id="contract_money_status" name="status" class="form-control selectfont">
			                    <option value="1">未收款</option>
			                    <option value="2">部分收款</option>	
			                    <option value="3">全额收款</option>					
			                </select>						        			
			            </div>
			        </div>    
			    </div> 
			    <div class="linehrmodal"></div>
			    <div class="form-group groupheight clearfix">  	 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">已收款</label>
			            <div class="col-lg-7">
			                <input id="contract_money_pay_amount" type="text" class="form-control form-controlbg" value="" readonly>								        			
			            </div>
			        </div> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">应收款</label>
			            <div class="col-lg-7">
			                <input id="contract_money_sure_amount" type="text" class="form-control form-controlbg" value="" readonly>		 
			            </div>
			        </div>   		       
			    </div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">是否逾期</label>
			            <div class="col-lg-7">
			                <div id="is_overdue_div" class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox">
			                </div> 
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">逾期原因</label>
			            <div class="col-lg-7">
			                <select id="overdue_reason_id" name="overdue_reason_id" class="form-control selectfont"></select> 
			            </div>
			        </div> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">其他原因</label>
			            <div class="col-lg-7">
			                <input id="overdue_extend_remark" name="overdue_extend_remark" type="text" class="form-control form-controlbg" value="" > 
			            </div>
			        </div>  
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">是否能收回</label>
			            <div class="col-lg-7">
			                <div id="is_recover_available_div" class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox">
			                </div> 
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">预计回收日期</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name="expect_recover_date"   id="datepickerhtpay">
			            </div>
			        </div> 
			    </div>
			    <div class="linehrmodal"></div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">最后更新人</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			               id="contract_money_show_user_id" name="" disabled >
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">更新时间</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name=""   id="datepickerhtpaylast" disabled>
			            </div>
			        </div>	 
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-12 col-lg-4new">
			            <label class="col-lg-2 control-label pt7 col-lg-2modal">备注</label>
			            <div class="col-lg-10">
			                <textarea id="contract_money_remark" name="remark" class="form-control form-controlbg" rows="5" placeholder="多行输入"></textarea>
			            </div>
			        </div>  
			    </div>			
			</div>
			<div class="modal-footer" style="text-align: center;">
				<button onclick="saveHouseContractMoneyMsg()" type="button" class="btn btn-primary">
					全部保存
				</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div> 	



<div class="modal fade" id="myModalAddRecord" tabindex="-1" role="dialog" aria-labelledby="moneyPayDetailTitle" aria-hidden="true" >
	<div class="modal-dialog modal-dialognew">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="moneyPayDetailTitle">
					收款详情
				</h4>
			</div>
			<form id="moneyPayDetailEditForm">
			<input id="money_pay_detail_id" name="id" type="hidden"/>
		    <input id="money_pay_detail_user_id" name="userId" type="hidden"/>
		    <input id="money_pay_detail_is_late_fee_calculate" name="is_late_fee_calculate" type="hidden"/>
		    <input id="money_pay_detail_is_overdue" name="is_overdue" type="hidden"/>
		    <input id="money_pay_detail_is_dimission_calculate" name="is_dimission_calculate" type="hidden"/>
			<div class="modal-body"> 
			    <div class="form-group groupheight clearfix"> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">金额类型</label>
			            <div class="col-lg-7">
			                <select id="money_pay_detail_money_type_id" name="money_type_id" onchange="setTotalAmountByMoneyType(this.value)" class="form-control selectfont"></select>
			            </div>
			        </div>	  
			        <div name="money_pay_detail_add_div" class="col-lg-8 col-lg-4new">
			            <label class="col-lg-3 control-label pt7 ">对应的合同金额记录</label>
			            <div class="col-lg-4">
			                <select id="money_pay_detail_contract_money_id" name="contract_money_id" class="form-control selectfont"></select>
			            </div>
			        </div>	
			        
			        <div name="money_pay_detail_edit_div" class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">总应收金额</label>
			            <div class="col-lg-7">
			                 <input id="money_pay_detail_receivables_amount" type="text" class="form-control form-controlbg" disabled>
			            </div>
			        </div>	 
			        <div name="money_pay_detail_edit_div" class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7 ">应收款时间</label>
			            <div class="col-lg-8">
			                 <input type="text" class="form-control form-controlbg" 
			                id="money_pay_detail_receivables_time" disabled>	
			            </div>
			        </div>	
			             
			    </div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">收到金额</label>
			            <div class="col-lg-7">
			                <input id="money_pay_detail_pay_amount" name="pay_amount" onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  type="text" class="form-control form-controlbg" value="">								        			
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">收款日期</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name="pay_time" id="datepickerskdate">	
			            </div>
			        </div>	        
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">收款方式</label>
			            <div class="col-lg-8">
		                	<select id="money_pay_detail_pay_type" name="pay_type" class='form-control selectfont'>
			                    <option value="1">现金</option>
			                    <option value="2">pos</option>
			                    <option value="3">银行转账</option>					
			                </select>
			            </div>
			        </div>         
			    </div> 
			    <div class="form-group groupheight clearfix">  	 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">入账行</label>
			            <div class="col-lg-7">
			                <input id="money_pay_detail_bank_name" name="bank_name" type="text" class="form-control form-controlbg" value="">								        			
			            </div>
			        </div>           
			    </div> 
			    <div class="linehrmodal"></div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">银行票据</label>
			            <div class="col-lg-7">
		                	<select id="money_pay_detail_bank_bill_type" name="bank_bill_type" class='form-control selectfont'>
			                    <option value="1">本票</option>
			                    <option value="2">支票</option>	
			                    <option value="3">汇票</option>					
			                </select>					        			
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">银行票据号</label>
			            <div class="col-lg-7">
			                <input id="money_pay_detail_bank_bill_number" name="bank_bill_number" type="text" class="form-control form-controlbg" value="">		
			            </div>
			        </div>	        
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">出票单位</label>
			            <div class="col-lg-8">
			                 <input id="money_pay_detail_receipt_company" name="receipt_company" type="text" class="form-control form-controlbg" value="">		
			            </div>
			        </div>         
			    </div> 
			    <div class="form-group groupheight clearfix">  
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">收据姓名</label>
			            <div class="col-lg-7">
			                 <input id="money_pay_detail_receipt_name" name="receipt_name" type="text" class="form-control form-controlbg" value="">		
			            </div>
			        </div>
			    </div>
			    <div class="linehrmodal"></div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">ERP收款日期</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name="date" id="datepickerskdaterep">	
			            </div>
			        </div>	 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">ERP收入金额</label>
			            <div class="col-lg-7">
			                <input id="money_pay_detail_erp_paid_amount"  name="erp_paid_amount" onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  type="text" class="form-control form-controlbg" value="">		
			            </div>
			        </div>	    
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">ERP违约金率</label>
			            <div class="col-lg-7">
			                <input id="money_pay_detail_erp_late_fee_ratio" name="erp_late_fee_ratio" onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  type="text" class="form-control form-controlbg" value="0" readonly="readonly" >
			            </div>
			        </div>  
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">ERP违约金</label>
			            <div class="col-lg-7">
			               <input id="money_pay_detail_erp_late_fee" name="erp_late_fee" onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  type="text" class="form-control form-controlbg" >
			            </div>
			        </div>	   
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">合同逾期违金</label>
			            <div class="col-lg-7">
			                <input id="money_pay_detail_late_fee" name="late_fee" onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  type="text" class="form-control form-controlbg" disabled >
			            </div>
			        </div>  
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-12 col-lg-4new">
			            <label class="col-lg-2 control-label pt7 col-lg-2modal">逾期原因</label>
			            <div class="col-lg-10">
			               <input id="money_pay_detail_overdue_reason_id" name="overdue_reason_id" type="text" class="form-control form-controlbg" >
			            </div>
			        </div>	      
			    </div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-5 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">是否已计算违约金</label>
			            <div class="col-lg-7">
			                <div id="money_pay_detail_is_late_fee_calculate_div" class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox" checked>
			                </div> 
			            </div>
			        </div>
			        <div class="col-lg-5 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">是否已逾期</label>
			            <div class="col-lg-7">
			                <div id="money_pay_detail_is_overdue_div" class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox" checked>
			                </div> 
			            </div>
			        </div>  
			    </div>
			    <div class="linehrmodal"></div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-5 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">佣金是否已计算</label>
			            <div class="col-lg-7">
			                <div id="money_pay_detail_is_dimission_calculate_div" class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox" checked>
			                </div> 
			            </div>
			        </div>
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">最后更新人</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			               id="money_pay_detail_update_user_id" name="" disabled >
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">更新时间</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name=""   id="datepickercyjsecond" disabled>
			            </div>
			        </div>	 
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-12 col-lg-4new">
			            <label class="col-lg-2 control-label pt7 col-lg-2modal">备注</label>
			            <div class="col-lg-10">
			                <textarea id="money_pay_detail_remark" name="remark" class="form-control form-controlbg" rows="5" placeholder="多行输入"></textarea>
			            </div>
			        </div>  
			    </div>			
			</div>
			<div class="modal-footer" style="text-align: center;">
				<button onclick="saveMoneyPayDetailMsg()" type="button" class="btn btn-primary">
					全部保存
				</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div> 

<div class="modal fade" id="myModalRecord" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog modal-dialognew">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					合同金额收款详情
				</h4>
			</div>
			<div class="modal-body"> 
			    <div class="form-group groupheight clearfix"> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">金额类型</label>
			            <div class="col-lg-7">
			                <select class="form-control selectfont">
			                    <option>定金</option>
			                    <option>一次性付款</option>	
			                    <option>首付款</option>
			                    <option>公积金按揭款</option>	
			                    <option>商业按揭款</option> 				
			                </select>
			            </div>
			        </div>	  
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">总应收金额</label>
			            <div class="col-lg-7">
			                 <input type="text" class="form-control form-controlbg" value="" disabled>
			            </div>
			        </div>	 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7 ">应收款时间</label>
			            <div class="col-lg-8">
			                 <input type="text" class="form-control form-controlbg" 
			                name="date" id="datepickerskdateall" disabled>	
			            </div>
			        </div>	      
			    </div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">收到金额</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" value="">								        			
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">收款日期</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name="date" id="datepickerskdatetwo">	
			            </div>
			        </div>	        
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">收款方式</label>
			            <div class="col-lg-8">
			                <select class="form-control selectfont">
			                    <option>现金</option>
			                    <option>pos</option>
			                    <option>银行转账</option>					
		                	</select>
			            </div>
			        </div>         
			    </div> 
			    <div class="form-group groupheight clearfix">  	 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">入账行</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" value="">								        			
			            </div>
			        </div>           
			    </div> 
			    <div class="linehrmodal"></div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">银行票据</label>
			            <div class="col-lg-7">
			                <select class="form-control selectfont">
			                    <option>本票</option>
			                    <option>支票</option>
			                    <option>汇票</option>			
		                	</select>						        			
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">银行票据号</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" value="">		
			            </div>
			        </div>	        
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">出票单位</label>
			            <div class="col-lg-8">
			                 <input type="text" class="form-control form-controlbg" value="">		
			            </div>
			        </div>         
			    </div> 
			    <div class="form-group groupheight clearfix">  
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">收据姓名</label>
			            <div class="col-lg-7">
			                 <input type="text" class="form-control form-controlbg" value="">		
			            </div>
			        </div>         
			    </div> 
			    <div class="linehrmodal"></div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">ERP收款日期</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name="date" id="datepickerskdatereptwo">	
			            </div>
			        </div>	   
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">ERP违约金率</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" disabled >
			            </div>
			        </div>  
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">ERP违约金</label>
			            <div class="col-lg-7">
			               <input type="text" class="form-control form-controlbg" >
			            </div>
			        </div>	   
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">合同逾期违金</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" disabled >
			            </div>
			        </div>  
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-12 col-lg-4new">
			            <label class="col-lg-2 control-label pt7 col-lg-2modal">逾期原因</label>
			            <div class="col-lg-10">
			               <input type="text" class="form-control form-controlbg" >
			            </div>
			        </div>	      
			    </div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-5 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">是否已计算违约金</label>
			            <div class="col-lg-7">
			                <div  class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox" checked>
			                </div> 
			            </div>
			        </div>
			        <div class="col-lg-5 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">是否已逾期</label>
			            <div class="col-lg-7">
			                <div  class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox" checked>
			                </div> 
			            </div>
			        </div>  
			    </div>
			    <div class="linehrmodal"></div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-5 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">佣金是否已计算</label>
			            <div class="col-lg-7">
			                <div  class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox" checked>
			                </div> 
			            </div>
			        </div>
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">最后更新人</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name="" disabled >
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">更新时间</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name=""   id="datepickercyjsecondlast" disabled>
			            </div>
			        </div>	 
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-12 col-lg-4new">
			            <label class="col-lg-2 control-label pt7 col-lg-2modal">备注</label>
			            <div class="col-lg-10">
			                <textarea class="form-control form-controlbg" rows="5" placeholder="多行输入"></textarea>
			            </div>
			        </div>  
			    </div>			
			</div>
			<div class="modal-footer" style="text-align: center;">
				<button type="button" class="btn btn-primary" data-dismiss="modal">
					全部保存
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div> 

<div class="modal fade" id="myModalAddInvoice" tabindex="-1" role="dialog" aria-labelledby="receiptInvoiceTitle" aria-hidden="true" >
	<div class="modal-dialog modal-dialognew">
		<div class="modal-content">
		    <form id="receiptInvoiceEditForm">
		    <input id="receipt_invoice_id" name="id" type="hidden"/>
		    <input id="receipt_invoice_user_id" name="userId" type="hidden"/>
		    <input id="receipt_invoice_is_paid_added_tax" name="is_paid_added_tax" type="hidden"/>
		    <input id="receipt_invoice_is_paid_sales_tax" name="is_paid_sales_tax" type="hidden"/>
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="receiptInvoiceTitle">
					添加收据/发票
				</h4>
			</div>
			<div class="modal-body"> 
			    <div class="form-group groupheight clearfix"> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">票据类型</label>
			            <div class="col-lg-7">
			                <select id="receipt_invoice_type" name="type" class="form-control selectfont">
			                    <option value="1">收据</option>
			                    <option value="2">普票</option>	
			                    <option value="3">专票</option>					
			                </select>
			            </div>
			        </div>	  
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7 ">金额类型</label>
			            <div class="col-lg-8">
			                <select id="receipt_invoice_money_type_id" name="money_type_id" class="form-control selectfont"></select>
			            </div>
			        </div>	
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">对应的收款记录</label>
			            <div class="col-lg-7">
			                <select id="receipt_invoice_pay_detail_id" name="pay_detail_id" class='form-control selectfont'></select>
			            </div>
			        </div>  
			    </div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">收据号/发票号</label>
			            <div class="col-lg-7">
			                <input id="receipt_invoice_code" name="code" type="text" class="form-control form-controlbg" value="">								        			
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">抬头</label>
			            <div class="col-lg-8">
			                <input id="receipt_invoice_name" name="name" type="text" class="form-control form-controlbg" value="" >	 
			            </div>
			        </div>	        
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">出票单位</label>
			            <div class="col-lg-7">
			                 <input id="receipt_invoice_receipt_company" name="receipt_company" type="text" class="form-control form-controlbg" value="">
			            </div>
			        </div>         
			    </div> 
			    <div class="form-group groupheight clearfix">  	 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">金额</label>
			            <div class="col-lg-7">
			                <input id="receipt_invoice_amount" name="amount" onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  type="text" class="form-control form-controlbg" value="">								        			
			            </div>
			        </div> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">开票时间</label>
			            <div class="col-lg-8">
			                <input type="text" class="form-control form-controlbg" 
			                name="invoice_time" id="datepickersjkpadd">	 
			            </div>
			        </div>   
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">状态</label>
			            <div class="col-lg-7">
			                <select id="receipt_invoice_isvalid" name="isvalid" class='form-control selectfont'>
			                    <option value="1">有效</option>	
			                    <option value="0">无效</option>			
			                </select>
			            </div>
			        </div>		       
			    </div> 
			    <div class="linehrmodal"></div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-6 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">是否已全额预缴增值税</label>
			            <div class="col-lg-7">
			                <div  id="receipt_invoice_is_paid_added_tax_div"  class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox">
			                </div> 
			            </div>
			        </div>
			        <div class="col-lg-6 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">是否已缴营业税</label>
			            <div class="col-lg-8">
			                <div  id="receipt_invoice_is_paid_sales_tax_div"  class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox">
			                </div> 
			    
			            </div>
			        </div>  
			    </div>   
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">开票税率（%）</label>
			            <div class="col-lg-7">
			                <input id="receipt_invoice_invoice_tax_rate" name="invoice_tax_rate" onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  type="text" class="form-control form-controlbg" value="">
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">税额</label>
			            <div class="col-lg-8">
			                <input id="receipt_invoice_invoice_tax" name="invoice_tax" onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  type="text" class="form-control form-controlbg" value="">
			            </div>
			        </div>  
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">原收据号</label>
			            <div class="col-lg-7">
			                <input id="receipt_invoice_old_code" name="old_code" type="text" class="form-control form-controlbg"  value="">
			            </div>
			        </div>
			    </div>
			    
			    <div class="linehrmodal"></div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">最后更新人</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                 id="receipt_invoice_update_user" disabled >
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">更新时间</label>
			            <div class="col-lg-8">
			                <input type="text" class="form-control form-controlbg" 
			                id="datepickercyjfive" disabled>
			            </div>
			        </div>	 
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-12 col-lg-4new">
			            <label class="col-lg-2 control-label pt7 col-lg-2modal">备注</label>
			            <div class="col-lg-10">
			                <textarea id="receipt_invoice_remark" name="remark" class="form-control form-controlbg" rows="5" placeholder="多行输入"></textarea>
			            </div>
			        </div>  
			    </div>			
			</div>
			<div class="modal-footer" style="text-align: center;">
				<button onclick="saveReceiptInvoiceDataMsg()" type="button" class="btn btn-primary">
					保存
				</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div> 



<div class="modal fade" id="myModalInvoice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog modal-dialognew">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					发票/收据详情
				</h4>
			</div>
			<div class="modal-body"> 
			    <div class="form-group groupheight clearfix"> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">票据类型</label>
			            <div class="col-lg-7">
			                <select class="form-control selectfont">
			                    <option>收据</option>
			                    <option>普票</option>	
			                    <option>专票</option>					
			                </select>
			            </div>
			        </div>	  
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7 ">金额类型</label>
			            <div class="col-lg-8">
			                <select class="form-control selectfont">
			                    <option>诚意金</option>
			                    <option>定金</option>	
			                    <option>一次性付款</option>	
			                    <option>首付款</option>	
			                    <option>公积金按揭款</option>	
			                    <option>商业按揭款</option>	
			                    <option>交房发票</option>	
			                    <option>违约金</option>	
			                    <option>面积差</option>				
			                </select>
			            </div>
			        </div>	
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">对应的收款记录</label>
			            <div class="col-lg-7">
			                <select class='form-control selectfont'>
			                    <option></option>				
			                </select>
			            </div>
			        </div>  
			    </div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">收据号/发票号</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" value="">								        			
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">抬头</label>
			            <div class="col-lg-8">
			                <input type="text" class="form-control form-controlbg" value="" >	 
			            </div>
			        </div>	        
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">出票单位</label>
			            <div class="col-lg-7">
			                 <input type="text" class="form-control form-controlbg" value="">
			            </div>
			        </div>         
			    </div> 
			    <div class="form-group groupheight clearfix">  	 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">金额</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" value="">								        			
			            </div>
			        </div> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">开票时间</label>
			            <div class="col-lg-8">
			                <input type="text" class="form-control form-controlbg" 
			                name="date" id="datepickersjkp">	 
			            </div>
			        </div>   
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">状态</label>
			            <div class="col-lg-7">
			                <select class='form-control selectfont'>
			                    <option>已换票</option>	
			                    <option>有效</option>			
			                </select>
			            </div>
			        </div>		       
			    </div> 
			    <div class="linehrmodal"></div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-6 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">是否已全额预缴增值税</label>
			            <div class="col-lg-7">
			                <div  class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox">
			                </div> 
			            </div>
			        </div>
			        <div class="col-lg-6 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">是否已缴营业税</label>
			            <div class="col-lg-8">
			                <div  class="make-switch" data-on="success" data-off="warning" 
			                    data-on-label="是" data-off-label="否" style="margin-right:10px;" >
			                    <input type="checkbox">
			                </div> 
			    
			            </div>
			        </div>  
			    </div>   
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">开票税率（%）</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" value="">
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">税额</label>
			            <div class="col-lg-8">
			                <input type="text" class="form-control form-controlbg" value="">
			            </div>
			        </div>  
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">原收据号</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg"  value="">
			            </div>
			        </div>
			    </div>
			    
			    <div class="linehrmodal"></div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">最后更新人</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name="" disabled >
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-4 control-label pt7">更新时间</label>
			            <div class="col-lg-8">
			                <input type="text" class="form-control form-controlbg" 
			                name=""   id="datepickercyjforth" disabled>
			            </div>
			        </div>	 
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-12 col-lg-4new">
			            <label class="col-lg-2 control-label pt7 col-lg-2modal">备注</label>
			            <div class="col-lg-10">
			                <textarea class="form-control form-controlbg" rows="5" placeholder="多行输入"></textarea>
			            </div>
			        </div>  
			    </div>			
			</div>
			<div class="modal-footer" style="text-align: center;">
				<button type="button" class="btn btn-primary" data-dismiss="modal">
					保存
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div> 	 


<div class="modal fade" id="myModalEditRelation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog modal-dialognew">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					房源与员工关系
				</h4>
			</div>
			 
			<div class="modal-body"> 
			    <div class="form-group groupheight clearfix"> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">姓名</label>
			            <div class="col-lg-7">
			                <select  name=" " class="form-control selectfont" disabled>
			                	<option>定金</option>
			                	<option>一次性付款</option>
			                	<option>首付款</option>
			                	<option>公积金按揭款</option>
			                	<option>商业按揭款</option>
			                </select>
			            </div>
			        </div>	  
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">职位</label>
			            <div class="col-lg-7">
			                 <select  name=" " class="form-control selectfont"><option>职业顾问</option></select>
			            </div>
			        </div>	
			        
			    </div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">关系类型</label>
			            <div class="col-lg-7">
			                <select  name="" class="form-control selectfont">
			                    <option value="1">原配</option>
			                    <option value="2">转岗在分配</option>	
			                    <option value="3">离职在分配</option>					
			                </select>						        			
			            </div>
			        </div>    
			    </div>
			    <div class="form-group groupheight clearfix"> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">关系起始时间</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name="" id="datepickerrelationstart">
			            </div>
			        </div>	  
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">关系终止时间</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name="" id="datepickerrelationend">
			            </div>
			        </div>	
			        
			    </div>  
			    
			    <div class="linehrmodal"></div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">最后更新人</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name="" disabled >
			            </div>
			        </div>
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">更新时间</label>
			            <div class="col-lg-7">
			                <input type="text" class="form-control form-controlbg" 
			                name=""  id="datepickerrelationdisabled"   disabled>
			            </div>
			        </div>	 
			    </div>
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-12 col-lg-4new">
			            <label class="col-lg-2 control-label pt7 col-lg-2modal">备注</label>
			            <div class="col-lg-10">
			                <textarea  name="remark" class="form-control form-controlbg" rows="5" placeholder="多行输入"></textarea>
			            </div>
			        </div>  
			    </div>			
			</div>
			<div class="modal-footer" style="text-align: center;">
				<button type="button" class="btn btn-primary" data-dismiss="modal">
					保存
				</button>
			</div>
			 
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div> 	

<%@ include file="/common/finace_footer.jsp"%>
<script src="<c:url value="/js/custom.js" />"></script>
	 
</script>



<!-- 导出房源报表 -->
<form action="exportHouseData.shtml" id="exportHouseDataForm" method="post">
    <input type="hidden" id="userIdExportQuery" name="userId"/>
    <input type="hidden" id="cityCompanyIdExportQuery" name="cityCompanyIdQuery"/>
    <input type="hidden" id="projectNameExportQuery" name="projectNameQuery"/>
    <input type="hidden" id="projectPhaseExportQuery" name="projectPhaseQuery"/>
    <input type="hidden" id="houseTypeIdExportQuery" name="houseTypeIdQuery"/>
    <input type="hidden" id="houseBusinessTypeIdExportQuery" name="houseBusinessTypeIdQuery"/>
    <input type="hidden" id="houseConsultantExportQuery" name="houseConsultantQuery"/>
    <input type="hidden" id="customerNameExportQuery" name="customerNameQuery"/>
    <input type="hidden" id="erpNameExportQuery" name="erpNameQuery"/>
    <input type="hidden" id="isLargeAmountExportQuery" name="isLargeAmountQuery"/>
</form>
    
    