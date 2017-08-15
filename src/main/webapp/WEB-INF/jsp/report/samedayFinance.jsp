 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/capital_nav.jsp"%>
<%@ include file="/common/finace_script.jsp"%>
<script src="<c:url value="/js/ajaxfileupload.js" />"></script>
<style>
  .ui-autocomplete {
    max-height: 160px;
    overflow-y: auto;
    /* 防止水平滚动条 */
    overflow-x: hidden;
    
  }
</style>
<script>
	var subCompanyId = "";//当前子公司id
	var queryDate = "";//当前查询日期
	var showType = "samedayFinance";
	//收入类型
	var incomeTypeListHtml = "";
	//支出类型
	var costTypeListHtml = "";
	//不可用资金类型
	var invalidCapitalTypeListHtml = "";
	//银行列表
	var bankList = [];
</script>	
<script src="<c:url value="/js/samedayFinance.js" />"></script>
<script>
    $(document).ready(function(){
    	//查询银行列表
    	// queryBank();
        //增加<tr/>
        //银行余额table
        $("#addBank").click(function(){
            var _len = $("#bankTab tr").length; 
            
           $("#bankTab").append("<tr id="+_len+" align='center'>"
                                +"<td><div class='form-group form-groupnew'><input type='hidden' id='bankfile_"+_len+"' name='bankfile' class=''/><input type='text' name='bankname'  onmouseover='this.title=this.value'  class='form-control tcfont autocomplete'  /></div></td>"
                                +"<td><div class='form-group form-groupnew'><input type='text' name='balance'  onmouseover='this.title=this.value'   class='form-control tcfont' onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' /></div></td>"
                               	+"<td><div class='form-group form-groupnew'><input type='text' name='remarks'  onmouseover='this.title=this.value'   class='form-control tcfont'  /></div></td>"
                                +"<td><form class='form-inline' role='form'  >"
                                +'<a id="fileUploadHref_'+_len+'" href="javascript:;" class="blue-btn op-btn  a-upload fileUploadHref " ><i class="i-upload"></i>'
                                +'<input type="file" name="uploadFile_'+_len+'" id="uploadFile_'+_len+'" attr="1" title="上传截图">上传截图</a>'
                                +'<button id="viewBalanceImgBtn_'+_len+'" type="button" class="btn btn-primary mt05">查看截图</button>'
                                +"<button type='button' class='btn btn-danger mt05'onclick=\'deltr("+_len+")\'>删除</button>"
                                +"</form></td>"
                            	+"</tr>");  
           //$( ".autocomplete" ).autocomplete({
        	//   source:bankList
        	// });
           initAjaxFileUpload(_len);
       	}) 
        
        //今日收入table
        $("#addIcome").click(function(){
            var _lenicome = $("#incomeTab tr").length;        
           $("#incomeTab").append("<tr id="+_lenicome+" align='center'>"
			        		    +"<td><div class='form-group form-groupnew'><select id='incomeSel_"+_lenicome+"' class='form-control selectfont'  >"
			                    +incomeTypeListHtml
			                    +"</select></div></td>"
        		                +"<td><div class='form-group form-groupnew'><input type='text' name='incomeName'  onmouseover='this.title=this.value' class='form-control tcfont'  /></div></td>"
                   				+"<td><div class='form-group form-groupnew'><input type='text' name='balance'  onmouseover='this.title=this.value' class='form-control tcfont'  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  /></div></td>"
                  				+"<td><div class='form-group form-groupnew'><input type='text' name='remarks'  onmouseover='this.title=this.value' class='form-control tcfont'  /></div></td>"
                  				+"<td><form class='form-inline' role='form'>"
                                +"<button type='button' class='btn btn-danger'onclick=\'deltrincome("+_lenicome+")\'>删除</button>"
                                +"</form></td>"
                            	+"</tr>");   
           
        	 
        }) 
      //今日支出table
        $("#addDefray").click(function(){
            var _lendefray = $("#defrayTab tr").length;        
           $("#defrayTab").append("<tr id="+_lendefray+" align='center'>"
			        		    +"<td><div class='form-group form-groupnew'><select id='costSel_"+_lendefray+"' class='form-control selectfont' >"
			                    +costTypeListHtml
			                    +"</select></div></td>"
        		                +"<td><div class='form-group form-groupnew'><input type='text' name='costName' onmouseover='this.title=this.value' class='form-control tcfont'  /></div></td>"
                   				+"<td><div class='form-group form-groupnew'><input type='text' name='balance'  onmouseover='this.title=this.value'  class='form-control tcfont'  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  /></div></td>"
                  				+"<td><div class='form-group form-groupnew'><input type='text' name='remarks'  onmouseover='this.title=this.value' class='form-control tcfont'  /></div></td>"
                  				+"<td><form class='form-inline' role='form'>"
                                +"<button type='button' class='btn btn-danger'onclick=\'deltrdefray("+_lendefray+")\'>删除</button>"
                                +"</form></td>"
                            	+"</tr>");   
           
        }) 
      //不可用资金table
        $("#addUnavailable").click(function(){
            var _lenunavailable = $("#unavailableTab tr").length;        
            $("#unavailableTab").append("<tr id="+_lenunavailable+" align='center'>"
            		+"<td><div class='form-group form-groupnew'><select id='invalidSel_"+_lenunavailable+"' class='form-control selectfont'  >"
                    +invalidCapitalTypeListHtml
                    +"</select></div></td>"
       				+"<td><div class='form-group form-groupnew'><input type='text' name='balance' onmouseover='this.title=this.value'  class='form-control tcfont'  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  /></div></td>"
       				+"<td><div class='form-group form-groupnew'><input type='text' name='changeAmount' onmouseover='this.title=this.value'  class='form-control tcfont'  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  /></div></td>"
      				+"<td><div class='form-group form-groupnew'><input type='text' name='remarks' onmouseover='this.title=this.value'  class='form-control tcfont'  /></div></td>"
      				
      				+"<td><form class='form-inline' role='form'>"
                    +"<button type='button' class='btn btn-danger'onclick=\'deltrunavailable("+_lenunavailable+")\'>删除</button>"
                    +"</form></td>"
                	+"</tr>"); 
        }) 
        
    })

   //删除<tr/>
   var deltr =function(index)
   {
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#bankTab tr").length; 
	       $("#bankTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   }
   var deltrincome =function(indexcome)
   {
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _lenicome = $("#incomeTab tr").length;
	       $("#incomeTab tr[id='"+indexcome+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   }
   var deltrdefray =function(indexdefray)
   {
	   var confirmWin = layer.confirm("是否确认删除？",function(){
		   var _lendefray = $("#defrayTab tr").length;
	       $("#defrayTab tr[id='"+indexdefray+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   }
   var deltrunavailable =function(indexunavailable)
   {
	   var confirmWin = layer.confirm("是否确认删除？",function(){
		   var _lenunavailable = $("#bankTab tr").length;
	       $("#unavailableTab tr[id='"+indexunavailable+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   }
   
   $(function(){
	   $("#datepicker").val(getNowFormatDate()).css({"background-color":"#EEEEEE"});;
	   //查询子公司
	   querySubComp();
	   //查询子公司总余额
	   //querySubCompTotalBalance();
	   //查询子公司银行余额
	   //querySubCompBalance();
	   //查询子公司今日收入
	   //queryIncome();
	   
	   //查询收入类型
	   //queryIncomeType();
	   //查询支出类型
	   //queryCostType();
	   //查询不可用资金类型
	   //queryInvalidCapitalType();
	   
   });
</script> 

<!-- Main bar -->
<div class="mainbar" >
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i> 当日资金
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
								        	<input type="text" class="form-control form-controlbg" 
								        		disabled="disabled"	name="date" id="datepicker">
								      	</div>
								      	<div class="form-group" style="margin-right:10px;">
											<select id="subCompanySel" onchange="querySubCompanyMsg()" class='form-control selectfont' style="min-width:170px;"></select>
										</div> 
								        <!-- <button type="button" onclick="querySubCompanyMsg()" class="btn btn-primary">确认</button>-->
							       
							            <shiro:hasPermission  name="/samedayFinance/balanceReport.shtml">
							                <button id="balanceReportBtn" type="button" onclick="saveBalanceProcess(1)" class="btn btn-primary ml20">数据上报</button>
							            </shiro:hasPermission>
							            
							            <shiro:hasPermission  name="/samedayFinance/balanceRejct.shtml">
							                <button id="balanceRejectBtn" type="button" onclick="saveBalanceProcess(-1)" class="btn btn-primary ml10">撤回重填</button>
							            </shiro:hasPermission>
							    </form>	　
						        <div class="cls pt20"></div>
						        <div class="row">
								    <div class="col-lg-3 col-lg-3-bot">
								     	<label class=" labelnew" >
											今日总余额（万元）&nbsp;&nbsp;&nbsp;&nbsp;
										</label> 
										<input id="todayAmount" type="text" disabled="disabled" class="form-control form-controlbg form-controlnew tcfont">	 
								    </div>
								    <div class="col-lg-3 col-lg-3-bot"> 
								       	<label class=" labelnew" >
											总收入（万元）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</label>
										<input id="incomeAmount" type="text" disabled="disabled" class="form-control form-controlbg form-controlnew tcfont"> 
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
										<input id="costAmount" type="text" disabled="disabled" class="form-control form-controlbg form-controlnew tcfont">  
								    </div>	    
								  </div>
								  <div class="cls pt15"></div>
								  <div class="row">
								    <div class="col-lg-3 col-lg-3-bot">	 
								     	<label class=" labelnew" >
											总不可用资金（万元）
										</label> 
										<input id="invalidAmount" type="text" disabled="disabled" class="form-control form-controlbg form-controlnew tcfont">	 
								    </div>
								    <div class="col-lg-3 col-lg-3-bot">
								       	<label class=" labelnew" >
											昨日总余额（万元）
										</label>
										<input id="lastdayAmount" type="text" disabled="disabled" class="form-control form-controlbg form-controlnew tcfont">  
								    </div>
								    <div class="col-lg-3 col-lg-3-bot">
								       	<label class=" labelnew" >
											昨日工抵余额&nbsp;&nbsp;
										</label>
										<input id="lastdayGongdiBalance" type="text" disabled="disabled" class="form-control form-controlbg form-controlnew tcfont"> 			 
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
								     <button name="subCompanyBtn" type="button" class="btn btn-warning ml10" id="addBank">新增</button>
								     <button name="subCompanyBtn" type="button" class="btn btn-primary ml5" onclick="insertSubCompBalance()">提交</button>
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
								     <button name="subCompanyBtn" type="button" class="btn btn-warning ml10" id="addIcome">新增</button>
								     <button name="subCompanyBtn" type="button" class="btn btn-primary ml5" onclick="insertIncome()">提交</button>
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
								     <button name="subCompanyBtn" type="button" class="btn btn-warning ml10" id="addDefray">新增</button>
								     <button name="subCompanyBtn" type="button" class="btn btn-primary ml5" onclick="insertCost()">提交</button>
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
								     <button name="subCompanyBtn" type="button" class="btn btn-warning ml10" id="addUnavailable">新增</button>
								     <button name="subCompanyBtn" type="button" class="btn btn-primary ml5" onclick="insertInvalidCapital()">提交</button>
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
								          <input type='text' id="gongdiAmount" name='amount' onmouseover='this.title=this.value'  size="10" onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  class='form-control tcfont ml10' style="width:200px;display:inline;"  /> 
								     </div>
								     <div class="cls pt15"></div>
								     <button name="subCompanyBtn" type="button" class="btn btn-primary ml86" onclick="insertGongdiBalance()">提交</button>
								     <div class="cls pt15"></div>
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
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div> 	 


<%@ include file="/common/finace_footer.jsp"%>
 