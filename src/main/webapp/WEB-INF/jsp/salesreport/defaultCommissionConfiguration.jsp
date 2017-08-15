 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/common/header.jsp"%>
<%@ include file="/common/salereport_nav.jsp"%>
<script src="<c:url value="/js/jquery.min.js" />"></script>
<script src="<c:url value="/js/salesreport/defaultCommissionConfiguration.js" />"></script>
 
<!-- Main bar -->
<div class="mainbar" >
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i> 佣金配置配置
		</h2>
		<div class="clearfix"></div>
	</div>
	<!-- Page heading ends -->

	<!-- Matter -->
	<div class="matter">
		<div class="container ">
			<div class="row">
				<div class="col-md-12">
					 <div class="widget widgetnewsale" style="border:1px solid #c9c9c9;"> 
						 <ul class="nav nav-tabs nav-tabssale">
						    <li class="active"><a data-toggle="tab" href="#home" style="border-left:0;border-top:0;">职位佣金配置</a></li>
						    <li><a data-toggle="tab" href="#menu1"style="border-top:0;">到账与日结比例配置</a></li>
						    <li><a data-toggle="tab" href="#menu2"style="border-top:0;">佣金计算方法配置</a></li>	
						    <li><a data-toggle="tab" href="#menu3"style="border-top:0;">提佣办法配置 </a></li>			    
						 </ul> 
					 	<div class="tab-content tab-contentnew">
						    <div id="home" class="tab-pane fade in active">
						    	<div class="pd10">
									<dl class="commissiondl">
										<dt>配置说明：</dt>
										<dd>优先级：房源优先级>项目优先级>默认优先级</dd>
										<dd>配置优先级由上向下依次降低，默认使用优先级高的配置;</dd>
										<dd>如果没有在项目数据找到对应数据，将读取默认佣金配置;</dd>
									</dl>
									<div class="widget-head widget-headbg">
									    <span>销售类型与职位对应的佣金配置</span>
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
											        <th class="col-lg-yj1 tc">销售类型</th>
											        <th class="col-lg-yj1 tc">职位</th>
											        <th class="col-lg-yj1 tc">佣金提点</th>
											        <th class="col-lg-yj1 tc">固定金额</th>
											        <th width="130" class="tc">开始时间</th>
											        <th width="130" class="tc">结束时间</th>
											        <th class="tc">备注</th>
											        <th width="100" class="tc">操作</th>
											      </tr>
											    </thead>
											    <tbody id="posSaleTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addPosSale">新增</button>
										</div>
									</div>
									<div class="cls pt10"></div>
									<div class="widget-head widget-headbg">
									    <span>业态细类与职位对应的佣金配置</span>
									    <span class="widget-icons pull-right">
									        <a href="#" class="wminimize"  ><i
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
											    <tbody id="posFineTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addPosFine">新增</button>
										</div>
									</div>
									<div class="cls pt10"></div>
									<div class="widget-head widget-headbg">
									    <span>业态大类与职位对应的佣金配置</span>
									    <span class="widget-icons pull-right">
									        <a href="#" class="wminimize"  ><i
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
											    <tbody id="classPosTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addPosClass">新增</button>
										</div>
									</div>
									<div class="cls pt10"></div>
									<div class="widget-head widget-headbg">
									    <span>默认职位对应的佣金配置</span>
									    <span class="widget-icons pull-right">
									        <a href="#" class="wminimize"  ><i
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
											    <tbody id="positionPTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addPosP">新增</button>
										</div>
									</div>
								</div>			
						    </div>
						    <div id="menu1" class="tab-pane fade">
						    	<div class="pd10">
									<dl class="commissiondl">
										<dt>配置说明：</dt>
										<dd>优先级：房源优先级>项目优先级>默认优先级</dd>
										<dd>配置优先级由上向下依次降低，默认使用优先级高的配置;</dd>
										<dd>如果没有在项目数据找到对应数据，将读取默认佣金配置;</dd>
									</dl>
									<div class="widget-head widget-headbg">
									    <span>业态细类对应的资金到账与日结佣金提取比例关系</span>
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
											    <tbody id="arrivalDayTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addDayArrival">新增</button>
										</div>
									</div>
									<div class="cls pt10"></div>
									<div class="widget-head widget-headbg">
									    <span>业态大类对应的资金到账与日结佣金提取比例关系</span>
									    <span class="widget-icons pull-right">
									        <a href="#" class="wminimize"  ><i
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
											    <tbody id="arrivalbigDayTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addArrivalbigDay">新增</button>
										</div>
									</div>
									<div class="cls pt10"></div>
									<div class="widget-head widget-headbg">
									    <span>默认资金到账与日结佣金提取比例关系</span>
									    <span class="widget-icons pull-right">
									        <a href="#" class="wminimize"  ><i
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
											    <tbody id="arrivalproDayTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addArrivalproDay">新增</button>
										</div>
									</div>
								</div>
						    </div>
						    <div id="menu2" class="tab-pane fade">
						    	<div class="pd10">
									<dl class="commissiondl">
										<dt>配置说明：</dt>
										<dd>优先级：房源优先级>项目优先级>默认优先级</dd>
										<dd>配置优先级由上向下依次降低，默认使用优先级高的配置;</dd>
										<dd>如果没有在项目数据找到对应数据，将读取默认佣金配置;</dd>
									</dl>
									<div class="widget-head widget-headbg">
									    <span>房源类型与佣金计算方式关系</span>
									    <span class="widget-icons pull-right">
									        <a href="#" class="wminimize"  ><i
									            class="icon-chevron-up"></i></a>
									    </span>
									    <div class="clearfix"></div>
									</div>
									<div class="widget-content  widget-contentpd10 clearfix">
										<div class="table-overflow">
											<table class="table" style="text-align:center;">
												<thead>
											      <tr>
											        <th class="col-lg-yj3 tc">房源类型</th>
											       	<th class="col-lg-yj3 tc">佣金计算方式</th>
											        <th width="130" class="tc">开始时间</th>
											        <th width="130" class="tc">结束时间</th>
											        <th class="tc">备注</th>
											        <th width="100" class="tc">操作</th>
											      </tr>
											    </thead>
											    <tbody id="commisNorCalfyTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addCommisCalfy">新增</button>
										</div>
									</div>
									<div class="cls pt10"></div>
									<div class="widget-head widget-headbg">
									    <span>销售类型与佣金计算方式关系</span>
									    <span class="widget-icons pull-right">
									        <a href="#" class="wminimize"  ><i
									            class="icon-chevron-up"></i></a>
									    </span>
									    <div class="clearfix"></div>
									</div>
									<div class="widget-content  widget-contentpd10 clearfix">
										<div class="table-overflow">
											<table class="table" style="text-align:center;">
												<thead>
											      <tr>
											        <th class="col-lg-yj3 tc">销售类型</th>
											       	<th class="col-lg-yj3 tc">佣金计算方式</th>
											        <th width="130" class="tc">开始时间</th>
											        <th width="130" class="tc">结束时间</th>
											        <th class="tc">备注</th>
											        <th width="100" class="tc">操作</th>
											      </tr>
											    </thead>
											    <tbody id="commisNorCalxsTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addCommisCalxs">新增</button>
										</div>
									</div>
									<div class="cls pt10"></div>
									<div class="widget-head widget-headbg">
									    <span>业态细类与佣金计算方式关系</span>
									    <span class="widget-icons pull-right">
									        <a href="#" class="wminimize"  ><i
									            class="icon-chevron-up"></i></a>
									    </span>
									    <div class="clearfix"></div>
									</div>
									<div class="widget-content  widget-contentpd10 clearfix">
										<div class="table-overflow">
											<table class="table" style="text-align:center;">
												<thead>
											      <tr>
											        <th class="col-lg-yj3 tc">业态细类</th>
											       	<th class="col-lg-yj3 tc">佣金计算方式</th>
											        <th width="130" class="tc">开始时间</th>
											        <th width="130" class="tc">结束时间</th>
											        <th class="tc">备注</th>
											        <th width="100" class="tc">操作</th>
											      </tr>
											    </thead>
											    <tbody id="commisNorCalxlTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addCommisCalxl">新增</button>
										</div>
									</div>
									<div class="cls pt10"></div>
									<div class="widget-head widget-headbg">
									    <span>业态大类与佣金计算方式关系</span>
									    <span class="widget-icons pull-right">
									        <a href="#" class="wminimize"  ><i
									            class="icon-chevron-up"></i></a>
									    </span>
									    <div class="clearfix"></div>
									</div>
									<div class="widget-content  widget-contentpd10 clearfix">
										<div class="table-overflow">
											<table class="table" style="text-align:center;">
												<thead>
											      <tr>
											        <th class="col-lg-yj3 tc">业态大类</th>
											       	<th class="col-lg-yj3 tc">佣金计算方式</th>
											        <th width="130" class="tc">开始时间</th>
											        <th width="130" class="tc">结束时间</th>
											        <th class="tc">备注</th>
											        <th width="100" class="tc">操作</th>
											      </tr>
											    </thead>
											    <tbody id="commisNorCaldlTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addCommisCaldl">新增</button>
										</div>
									</div>	 
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
									    <span>职位对应的提佣办法</span>
									    <span class="widget-icons pull-right">
									        <a href="#" class="wminimize"  ><i
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
											        <th class="col-lg-yj3 tc">佣金类型</th>
											        <th class="col-lg-yj1 tc">占比（%）</th>
											        <th width="130" class="tc">开始时间</th>
											        <th width="130" class="tc">结束时间</th>
											        <th class="tc">备注</th>
											        <th width="100" class="tc">操作</th>
											      </tr>
											    </thead>
											    <tbody id="commisPosTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addCommisPos">新增</button>
										</div>
									</div>
									<div class="cls pt10"></div>
									<div class="widget-head widget-headbg">
									    <span>默认提佣办法</span>
									    <span class="widget-icons pull-right">
									        <a href="#" class="wminimize"  ><i
									            class="icon-chevron-up"></i></a>
									    </span>
									    <div class="clearfix"></div>
									</div>
									<div class="widget-content  widget-contentpd10 clearfix">
										<div class="table-overflow">
											<table class="table" style="text-align:center;">
												<thead>
											      <tr>
											        <th class="col-lg-yj3 tc">佣金类型</th>
											        <th class="col-lg-yj1 tc">占比（%）</th>
											        <th width="130" class="tc">开始时间</th>
											        <th width="130" class="tc">结束时间</th>
											        <th class="tc">备注</th>
											        <th width="100" class="tc">操作</th>
											      </tr>
											    </thead>
											    <tbody id="commisNorTab">
											    </tbody>
											</table> 
										</div>
									    <div class="cls pt10"></div>
									    <div class="form-group form-groupnew" style="text-align:center;">
											<button  type="button" class="btn btn-primary" id="addCommisNor">新增</button>
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
</div>

 
  


<%@ include file="/common/script.jsp"%>
<%@ include file="/common/footer.html"%>
 