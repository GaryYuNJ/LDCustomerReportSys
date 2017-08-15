<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/common/header.jsp"%>
<%@ include file="/common/salereport_nav.jsp"%>
<script src="<c:url value="/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/js/salesreport/projectDataManagement.js" />"></script>
 
<!-- Main bar -->
<div class="mainbar">
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i> 项目数据管理
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
						    <div class="pull-left">项目列表</div>
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
						                <select id="cityCompanyIdQuery" onchange="queryProjectData(this.value)" class='form-control selectfont'></select>
						            </div>
						        </div>  
						        <div class="col-lg-2">
						            <div class="form-group" >
						                <select id="projectDataQuery" class='form-control selectfont'></select>
						            </div>
						        </div>
						        <div class="col-lg-2">
						        	<div class="form-group">
						        	<button onclick="queryProjectDataBtn()" type="button" class="btn btn-primary">查询</button>
						        	</div>
						        </div>   
						    </div>
						    <table class="table table-bordered fytab table-hover">
						        <thead>
						            <tr>
						                <th>项目名称</th>
						                <th>项目ERP编码</th>
						                <th>城市公司</th>
						                <th>项目公司</th>
						                <th>项目起售时间</th>
						                <th>楼栋去化率（%）</th>
						                <th>集中交付率（%）</th>
						                <th>操作</th>			
						            </tr>
						        </thead>
						        <tbody id="projectListTable"></tbody>
						    </table>
						    <div id="projectListPageDiv" class="pagination pull-right"></div>
						</div>
						<div class="pt10"></div>
						<div class="widget-head" >
						    <div class="pull-left">项目详情</div>
						    <div class="widget-icons pull-right">
						        <a href="#" class="wminimize" id="icon_group_list2"><i
						            class="icon-chevron-down"></i></a>
						    </div>
						    <div class="clearfix"></div>
						</div>
						<div class="widget-content widget-contentinfo"    style="display: none;" >
							<ul id="projectSubTab" class="nav nav-tabs nav-tabsfy">
							    <li class="active"><a data-toggle="tab" href="#home" style="border-left:0;border-top:0;">基础信息</a></li>
							    <li><a data-toggle="tab" href="#menu1"style="border-top:0;">项目关系</a></li> 
							    <li onclick="queryEarnestMoneyDetail()"><a data-toggle="tab" href="#menu2"style="border-top:0;">诚意金列表</a></li>
							    <li><a data-toggle="tab" href="#menu3"style="border-top:0;">项目与职位佣金配置</a></li> 
							    <li><a data-toggle="tab" href="#menu4"style="border-top:0;">到账与日结比例配置</a></li> 
							    <li><a data-toggle="tab" href="#menu5"style="border-top:0;">每月员工奖惩记录</a></li> 				    
							</ul>
							<div class="tab-content tab-contentnew" id="resourceFormId">
								<div id="home" class="tab-pane fade in active">
								    <form id="projectBasicForm">
								        <input type="hidden" id="project_id" name="id"/>
								        <input type="hidden" id="project_user_id" name="userId"/>
										<div class="form-group groupheight clearfix">
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">项目名称</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
									                id="project_name" name="name">
									            </div>
									        </div>
									        
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">ERP编码</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
									                id="erp_code" name="erp_code">
									            </div>
									        </div>
									    </div>
									    <div class="form-group groupheight clearfix">
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">城市公司</label>
									            <div class="col-lg-8">
									                 <select id="city_company_id" name="city_company_id" class='form-control selectfont'></select>
									            </div>
									        </div>
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">项目公司</label>
									            <div class="col-lg-8">
									                <select id="project_company_id" name="project_company_id" class='form-control selectfont'></select>
									            </div>
									        </div>
									    </div>  
									    <div class="linehr"></div>
									    <div class="form-group groupheight clearfix">
									    	<div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">起售时间</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
									                name="sale_start_time" id="datepickerstart">
									            </div>
									        </div> 
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">楼栋去化率（%）</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
									                id="saled_ratio" name="saled_ratio" onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' >
									            </div>
									        </div> 
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">集中交付率（%）</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
									                id="accepted_ratio" name="accepted_ratio" onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' >
									            </div>
									        </div>
									    </div> 
										<div class="linehr"></div>
									    <div class="form-group groupheight clearfix">
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">最后更新人</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
									                id="project_update_user" disabled >
									            </div>
									        </div>
									        <div class="col-lg-4">
									            <label class="col-lg-3fy control-label pt7">更新时间</label>
									            <div class="col-lg-8">
									                <input type="text" class="form-control form-controlbg" 
									                   id="datepickermanage" disabled>
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
										<button onclick="saveProjectMsg()" type="button" class="btn btn-primary">全部保存</button>
									</div>	
									
									</form>
								</div>		 
								<div id="menu1" class="tab-pane fade">
									<div class="containernewfy">
										<div class="row">
											<div class="col-lg-2">
										    	<div class="form-group">
										        	<select class='form-control selectfont'  >
														<option>职位1</option>
														<option>职位2</option>
			                   						</select>
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
														 <button class="btn btn-xs btn-warning" title="修改关系" data-toggle='modal' data-target='#myModalProject'><i class="icon-pencil"></i> </button>
													</td>
												</tr> 								  
											</tbody>
										</table>
									</div> 
								</div>
								<div id="menu2" class="tab-pane fade">
									<div class="containernewfy">
										<div class="row">
											<div class="col-lg-2">
										    	<div class="form-group">
										        	<select id="earnest_money_status_query" class='form-control selectfont'  >
														<option value="">全部</option>
														<option value="1">已收款</option>
														<option value="2">已退款</option>
														<option value="3">已转房款</option>
			                   						</select>
										      	</div>
									      	</div>
									      	<div class="col-lg-2">
										    	<div class="form-group">
			                   						<input type="text" class="form-control form-controlbg" 
										        			name="" id="datepicker" placeholder="收款时间">
										      	</div>
									      	</div>
									      	<div class="col-lg-6 btnpd"> 
										        <button onclick="queryEarnestMoneyDetail()" type="button" class="btn btn-primary" style="margin-left:8px;">查询</button>
									        </div> 
									        <div class="col-lg-2 btnpt" >
									            <div class="form-group" style="text-align:right;">
									                <button type="button" onclick="toEditEarnestMoneyDetailMsgPage('add', '')" class="btn btn-primary">添加诚意金</button>
									            </div>
								            </div>
									     </div>
									     <div class="cls"></div>
									     <table class="table table-bordered fytab table-hover">
											<thead>
												<tr>
													<th>收据号</th>
													<th>房源名称</th>
													<th>实收金额</th> 
													<th>退转日期</th> 
													<th>转房款</th>
													<th>结余金额</th>
													<th>状态</th>
													<th>操作</th>			
												</tr>
											</thead>
											<tbody id="earnestMoneyDetailListTable"></tbody>
										</table>
									</div> 
								</div>
								<div id="menu3" class="tab-pane fade">
									<div class="pd10">
										<dl class="commissiondl">
											<dt>配置说明：</dt>
											<dd>优先级：房源优先级>项目优先级>默认优先级</dd>
											<dd>配置优先级由上向下依次降低，默认使用优先级高的配置;</dd>
											<dd>如果没有在项目数据找到对应数据，将读取默认佣金配置;</dd>
										</dl>
										<div class="widget-head widget-headbg">
										    <span>项目、业态细类与职位对应的佣金配置</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize"><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content  widget-contentpd10 clearfix">
											<div class="table-overflow">
												<table class="table" style="text-align:center;">
													<thead>
												      <tr>
												        <th class="col-lg-yj1 tc">业态细类</th>
												        <th class="col-lg-yj1 tc">职位</th>
												        <th class="col-lg-yj1 tc">佣金提点</th>
												        <th class="col-lg-yj1 tc">固定金额</th>
												        <th width="130" class="tc">开始时间</th>
												        <th width="130" class="tc">结束时间</th>
												        <th class="tc">备注</th>
												        <th width="100" class="tc">操作</th>
												      </tr>
												    </thead>
												    <tbody id="fineTab">
												    </tbody>
												</table> 
											</div>
										    <div class="cls pt10"></div>
										    <div class="form-group form-groupnew" style="text-align:center;">
												<button  type="button" class="btn btn-primary" id="addFine">新增</button>
											</div>
										</div>
										<div class="cls pt10"></div>
										<div class="widget-head widget-headbg">
										    <span>项目、业态大类与职位对应的佣金配置</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize"><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content  widget-contentpd10 clearfix">
											<div class="table-overflow">
												<table class="table" style="text-align:center;">
													<thead>
												      <tr>
												        <th class="col-lg-yj1 tc">业态大类</th>
												        <th class="col-lg-yj1 tc">职位</th>
												        <th class="col-lg-yj1 tc">佣金提点</th>
												        <th class="col-lg-yj1 tc">固定金额</th>
												        <th width="130" class="tc">开始时间</th>
												        <th width="130" class="tc">结束时间</th>
												        <th class="tc">备注</th>
												        <th width="100" class="tc">操作</th>
												      </tr>
												    </thead>
												    <tbody id="classTab">
												    </tbody>
												</table> 
											</div>
										    <div class="cls pt10"></div>
										    <div class="form-group form-groupnew" style="text-align:center;">
												<button  type="button" class="btn btn-primary" id="addClass">新增</button>
											</div>
										</div>
										<div class="cls pt10"></div>
										<div class="widget-head widget-headbg">
										    <span>当前项目与职位对应的佣金配置</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize"><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content  widget-contentpd10 clearfix">
											<div class="table-overflow">
												<table class="table" style="text-align:center;">
													<thead>
												      <tr> 
												        <th class="col-lg-yj1 tc">职位</th>
												        <th class="col-lg-yj1 tc">佣金提点</th>
												        <th class="col-lg-yj1 tc">固定金额</th>
												        <th width="130" class="tc">开始时间</th>
												        <th width="130" class="tc">结束时间</th>
												        <th class="tc">备注</th>
												        <th width="100" class="tc">操作</th>
												      </tr>
												    </thead>
												    <tbody id="positionTab">
												    </tbody>
												</table> 
											</div>
										    <div class="cls pt10"></div>
										    <div class="form-group form-groupnew" style="text-align:center;">
												<button  type="button" class="btn btn-primary" id="addPosition">新增</button>
											</div>
										</div>
									</div>	
								</div>
								<div id="menu4" class="tab-pane fade">
									<div class="pd10">
										<dl class="commissiondl">
											<dt>配置说明：</dt>
											<dd>优先级：房源优先级>项目优先级>默认优先级</dd>
											<dd>配置优先级由上向下依次降低，默认使用优先级高的配置;</dd>
											<dd>如果没有在项目数据找到对应数据，将读取默认佣金配置;</dd>
										</dl>
										<div class="widget-head widget-headbg">
										    <span>项目、业态细类对应的资金到账与日结佣金提取比例关系</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize"><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content  widget-contentpd10 clearfix">
											<div class="table-overflow">
												<table class="table" style="text-align:center;">
													<thead>
												      <tr>
												        <th class="col-lg-yj1 tc">业态细类</th>
												        <th class="col-lg-yj3 tc">到账比例（%）</th>
												        <th class="col-lg-yj2 tc">可提比例（%）</th>
												        <th width="130" class="tc">开始时间</th>
												        <th width="130" class="tc">结束时间</th>
												        <th class="tc">备注</th>
												        <th width="100" class="tc">操作</th>
												      </tr>
												    </thead>
												    <tbody id="arrivalTab">
												    </tbody>
												</table> 
											</div>
										    <div class="cls pt10"></div>
										    <div class="form-group form-groupnew" style="text-align:center;">
												<button  type="button" class="btn btn-primary" id="addArrival">新增</button>
											</div>
										</div>
										<div class="cls pt10"></div>
										<div class="widget-head widget-headbg">
										    <span>项目、业态大类对应的资金到账与日结佣金提取比例关系</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize" ><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content  widget-contentpd10 clearfix">
											<div class="table-overflow">
												<table class="table" style="text-align:center;">
													<thead>
												      <tr>
												        <th class="col-lg-yj1 tc">业态大类</th>
												        <th class="col-lg-yj3 tc">到账比例（%）</th>
												        <th class="col-lg-yj2 tc">可提比例（%）</th>
												        <th width="130" class="tc">开始时间</th>
												        <th width="130" class="tc">结束时间</th>
												        <th class="tc">备注</th>
												        <th width="100" class="tc">操作</th>
												      </tr>
												    </thead>
												    <tbody id="arrivalbigTab">
												    </tbody>
												</table> 
											</div>
										    <div class="cls pt10"></div>
										    <div class="form-group form-groupnew" style="text-align:center;">
												<button  type="button" class="btn btn-primary" id="addArrivalbig">新增</button>
											</div>
										</div>
										<div class="cls pt10"></div>
										<div class="widget-head widget-headbg">
										    <span>项目对应的资金到账与日结佣金提取比例关系</span>
										    <span class="widget-icons pull-right">
										        <a href="#" class="wminimize"><i
										            class="icon-chevron-up"></i></a>
										    </span>
										    <div class="clearfix"></div>
										</div>
										<div class="widget-content  widget-contentpd10 clearfix">
											<div class="table-overflow">
												<table class="table" style="text-align:center;">
													<thead>
												      <tr> 
												        <th class="col-lg-yj3 tc">到账比例（%）</th>
												        <th class="col-lg-yj2 tc">可提比例（%）</th>
												        <th width="130" class="tc">开始时间</th>
												        <th width="130" class="tc">结束时间</th>
												        <th class="tc">备注</th>
												        <th width="100" class="tc">操作</th>
												      </tr>
												    </thead>
												    <tbody id="arrivalproTab">
												    </tbody>
												</table> 
											</div>
										    <div class="cls pt10"></div>
										    <div class="form-group form-groupnew" style="text-align:center;">
												<button  type="button" class="btn btn-primary" id="addArrivalpro">新增</button>
											</div>
										</div>
									</div>	
								</div>
								<div id="menu5" class="tab-pane fade">
									<div class="containernewfy">
										<div class="row">
											<div class="col-lg-2 col-lg-3skjl">
									        	<label class="col-lg-3 control-label pt7 connectlab">月份</label>
									            <div class="col-lg-8">
									            	<div class="form-group" >
										                <input type="text" class="form-control form-controlbg" 
										                name="date" id="datepickerreward">
									                </div>
									            </div>
									        </div>
									      	<div class="col-lg-3">
										    	<div class="form-group">
			                   						<input type="text" class="form-control form-controlbg" 
										        			name="" placeholder="姓名">
										      	</div>
									      	</div>
									      	<div class="col-lg-6 col-lg-3skjl">
									      		<button type="submit" class="btn btn-primary" style="margin-left:8px;">查询</button>
									      	</div>
									     </div>
									     <div class="cls"></div>
									     <table class="table table-bordered fytab table-hover">
											<thead>
												<tr>
													<th>职位</th>
													<th>姓名</th>
													<th>手机号</th> 
													<th>奖惩金额</th> 
													<th>当月可提百分比</th>
													<th width=30%>备注</th>
													<th>操作</th>			
												</tr>
											</thead>
											<tbody>
												<tr>
													<td></td>
													<td></td>
													<td></td>
													<td></td>													  
													<td></td>
													<td></td>
													<td class="tc">
														 <button class="btn btn-xs btn-warning" title="详情" data-toggle='modal' data-target='#myModalReward'><i class="icon-pencil"></i> </button>
													</td>
												</tr> 								  
											</tbody>
										</table>
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
 
<div class="modal fade" id="myModalProject" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog modal-dialognew">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					项目与员工关系
				</h4>
			</div>
			 
			<div class="modal-body"> 
			    <div class="form-group groupheight clearfix"> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">员工姓名</label>
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





<div class="modal fade" id="myModalReward" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog modal-dialognew">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					员工每月奖惩记录
				</h4>
			</div>
			 
			<div class="modal-body"> 
			    <div class="form-group groupheight clearfix"> 
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7 ">员工姓名</label>
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
			        <div class="col-lg-5 col-lg-4new">
			            <label class="col-lg-6 control-label pt7 ">职位</label>
			            <div class="col-lg-6 col-lg-3skjl">
			                 <select  name=" " class="form-control selectfont"><option>职业顾问</option></select>
			            </div>
			        </div>	
			        
			    </div> 
			    <div class="form-group groupheight clearfix">
			        <div class="col-lg-4 col-lg-4new">
			            <label class="col-lg-5 control-label pt7">奖罚金额</label>
			            <div class="col-lg-7">
			                <select  name="" class="form-control selectfont">
			                    <option value="1">奖罚金额</option> 				
			                </select>						        			
			            </div>
			        </div>  
			        <div class="col-lg-5 col-lg-4new">
			            <label class="col-lg-6 control-label pt7">当月可提百分比（%）</label>
			            <div class="col-lg-6 col-lg-3skjl">
			                <input type="text" class="form-control form-controlbg" 
			                name=""  >					        			
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
			        <div class="col-lg-5 col-lg-4new">
			            <label class="col-lg-6 control-label pt7">更新时间</label>
			            <div class="col-lg-6 col-lg-3skjl">
			                <input type="text" class="form-control form-controlbg" 
			                name=""  id="datepickerrewardlast"   disabled>
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



<div class="modal fade" id="earnestMoneyModal" tabindex="-1" role="dialog" aria-labelledby="earnestMoneyDetailTitle" aria-hidden="true" >
	<div class="modal-dialog modal-dialognew">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="earnestMoneyDetailTitle"></h4>
			</div>
			
			
			 <form id="earnestMoneyForm">	
			    <input id="earnest_money_main_id" name="id" type="hidden"/>
			    <input id="earnest_money_user_id" name="userId" type="hidden"/>		
			    <input id="earnest_money_project_id" name="project_id" type="hidden"/>
				<div class=" ">
					<div class='clearfix pt15'></div>
					<div class="form-group groupheight clearfix" style='margin-left:15px;'>
					<div class="col-lg-4"  >
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl" > 房源编码</label>
				            <div class="col-lg-8">
				                <select id="house_id" name="house_id" class='form-control selectfont'>
				                </select>
				            </div>
				        </div>
				    </div>
				    <div class="form-group groupheight clearfix" style='margin-left:15px;'>
				    	
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">收款方式</label>
				            <div class="col-lg-8">
				                <select id="pay_type" name="pay_type" class='form-control selectfont'>
				                    <option value="1">现金</option>
				                    <option value="2">pos</option>
				                    <option value="3">银行转账</option>					
				                </select>
				            </div>
				        </div>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">收款时间</label>
				            <div class="col-lg-8">
				                 <input type="text" class="form-control form-controlbg" 
			        			name="pay_time"   id="datepickerpay">
				            </div>
				        </div>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">状态</label>
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
				    <div class="form-group groupheight clearfix" style='margin-left:15px;'>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">pos金额</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
			        			id="pos_amount" name="pos_amount">
				            </div>
				        </div>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">现金金额</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
			        			id="cash_amount" name="cash_amount">
				            </div>
				        </div>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">银行转账金额</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
			        			id="bank_transfer_amount" name="bank_transfer_amount">
				            </div>
				        </div>
				    </div>
				    <div class="form-group groupheight clearfix" style='margin-left:15px;'>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">银行票据金额</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
			        			id="bank_bill_amount" name="bank_bill_amount">
				            </div>
				        </div>
				        <div class="col-lg-4"> 
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">银行票据</label>
				            <div class="col-lg-8">
				                <select id="bank_bill_type" name="bank_bill_type" class='form-control selectfont'>
				                    <option value="1">本票</option>
				                    <option value="2">支票</option>	
				                    <option value="3">汇票</option>					
				                </select>
				            </div>
				        </div>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">入账行</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg" 
			        			id="bank_name" name="bank_name">
				            </div>
				        </div>	 
				    </div>
				    <div class="linehr"></div>
				    <div class="form-group groupheight clearfix" style='margin-left:15px;'>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">换据金额</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
			        			id="receipt_amount" name="receipt_amount">
				            </div>
				        </div>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">结余金额</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
			        			id="remain_amount" name="remain_amount">
				            </div>
				        </div>
				    </div>
				    <div class="form-group groupheight clearfix" style='margin-left:15px;'>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">退款金额</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
			        			id="refund_amount" name="refund_amount">
				            </div>
				        </div>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">退款支票号</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg" 
			        			id="refund_check_number" name="refund_check_number">
				            </div>
				        </div>
				    </div>
				    <div class="linehr"></div>
				    <div class="form-group groupheight clearfix" style='margin-left:15px;'>  
				        <div class="col-lg-4"> 
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">转房款金额</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg"  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' 
				                id="convert_house_amount" name="convert_house_amount">
				            </div>
				        </div>	
				    </div> 
				    <div class="linehr"></div>
				    <div class="form-group groupheight clearfix" style='margin-left:15px;'>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">最后更新人</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg" 
				                id="earnest_money_update_user_id" name="" disabled >
				            </div>
				        </div>
				        <div class="col-lg-4">
				            <label class="col-lg-3new control-label pt7 col-lg-3skjl">更新时间</label>
				            <div class="col-lg-8">
				                <input type="text" class="form-control form-controlbg" 
				                name="date"   id="datepickercyj" disabled>
				            </div>
				        </div>	 
				    </div>
				    <div class="form-group groupheight clearfix" style='margin-left:15px;'>
				        <div class="col-lg-12">
				            <label class="col-lg-3fycy control-label pt7 col-lg-3skjl">备注</label>
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
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<%@ include file="/common/script.jsp"%>
<%@ include file="/common/footer.html"%>

	 
</script> 