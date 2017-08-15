    $(document).ready(function(){
		 
	 //增加一行
	 	//业态细类
	 	$("#addFine").click(function(){
	        var _len = $("#fineTab tr").length;        
	        $("#fineTab").append("<tr id="+_len+" align='center'>"
			    +"<td><select class='form-control selectfont'><option>业态细类</option></select></td>"
	            +"<td><select class='form-control selectfont'><option>职位</option></select></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickermanastart"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickermanaend"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
				+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
				+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'fine("+_len+")\'>删除</button></td>" 
	         	+"</tr>");
	        $( "#datepickermanastart"+_len+"").datepicker(so.formatDate );
	        $( "#datepickermanaend"+_len+"").datepicker(so.formatDate );    
	     }) 
	     //业态大类
	     $("#addClass").click(function(){
	        var _len = $("#classTab tr").length;        
	        $("#classTab").append("<tr id="+_len+" align='center'>"
			    +"<td><select class='form-control selectfont'><option>业态大类</option></select></td>"
	            +"<td><select class='form-control selectfont'><option>职位</option></select></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickerclsstart"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickerclsend"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
				+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
				+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'classes("+_len+")\'>删除</button></td>" 
	         	+"</tr>");
	        $( "#datepickerclsstart"+_len+"").datepicker(so.formatDate );
	        $( "#datepickerclsend"+_len+"").datepicker(so.formatDate );    
	     }) 
	     //职位
	     $("#addPosition").click(function(){
	        var _len = $("#positionTab tr").length;        
	        $("#positionTab").append("<tr id="+_len+" align='center'>"
                +"<td><select class='form-control selectfont'><option>职位</option></select></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickerzwstart"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickerzwend"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
   				+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
				+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'position("+_len+")\'>删除</button></td>" 
             	+"</tr>");
	        $( "#datepickerzwstart"+_len+"").datepicker(so.formatDate );
	        $( "#datepickerzwend"+_len+"").datepicker(so.formatDate );    
	     }) 
	     
	   //业态细类-资金到账
	 	$("#addArrival").click(function(){
	        var _len = $("#arrivalTab tr").length;        
	        $("#arrivalTab").append("<tr id="+_len+" align='center'>"
    		    +"<td><select class='form-control selectfont'><option>业态细类</option></select></td>"               
				+"<td><input type='text' class='form-control form-controlbg' style='width:45%;float:left;'>"
				+"<span style='width:10%;float:left;margin-top:7px;'>~</span><input type='text' class='form-control form-controlbg'style='width:45%;float:right;'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrstart"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrend"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
   				+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
				+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'arrival("+_len+")\'>删除</button></td>" 
             	+"</tr>");
	        $( "#datepickerarrstart"+_len+"").datepicker(so.formatDate );
	        $( "#datepickerarrend"+_len+"").datepicker(so.formatDate );    
	     }) 
	   //业态大类-资金到账
	 	$("#addArrivalbig").click(function(){
	        var _len = $("#arrivalbigTab tr").length;        
	        $("#arrivalbigTab").append("<tr id="+_len+" align='center'>"
    		    +"<td><select class='form-control selectfont'><option>业态大类</option></select></td>"               
    		    +"<td><input type='text' class='form-control form-controlbg' style='width:45%;float:left;'>"
				+"<span style='width:10%;float:left;margin-top:7px;'>~</span><input type='text' class='form-control form-controlbg'style='width:45%;float:right;'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrbigstart"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrbigend"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
   				+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
				+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'arrivalbig("+_len+")\'>删除</button></td>" 
             	+"</tr>");
	        $( "#datepickerarrbigstart"+_len+"").datepicker(so.formatDate );
	        $( "#datepickerarrbigend"+_len+"").datepicker(so.formatDate );    
	     }) 
	   //项目-资金到账
	 	$("#addArrivalpro").click(function(){
	        var _len = $("#arrivalproTab tr").length;        
	        $("#arrivalproTab").append("<tr id="+_len+" align='center'>"		   
   				+"<td><input type='text' class='form-control form-controlbg' style='width:45%;float:left;'>"
				+"<span style='width:10%;float:left;margin-top:7px;'>~</span><input type='text' class='form-control form-controlbg'style='width:45%;float:right;'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrprostart"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrproend"+_len+"'></td>"
				+"<td><input type='text' class='form-control form-controlbg'></td>"
   				+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
				+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'arrivalpro("+_len+")\'>删除</button></td>" 
             	+"</tr>");
	        $( "#datepickerarrprostart"+_len+"").datepicker(so.formatDate );
	        $( "#datepickerarrproend"+_len+"").datepicker(so.formatDate );    
	     }) 
	     
	     
	 })

	 //删除一行
	 //职位对应-业态细类
	 var fine =function(index)
	 {
    	var confirmWin = layer.confirm("是否确认删除？",function(){
    		var _len = $("#fineTab tr").length;
    		$("#fineTab tr[id='"+index+"']").remove();//删除当前行
    		layer.close(confirmWin);
		});
	  }
	 //职位对应-业态大类
	 var classes=function(index)
	 {
	 	var confirmWin = layer.confirm("是否确认删除？",function(){
	 	    var _len = $("#classTab tr").length;
	 	    $("#classTab tr[id='"+index+"']").remove();//删除当前行
	 	    layer.close(confirmWin);
	 	});
	  }
	 //职位对应-职位
	 var position=function(index)
	 {
		 var confirmWin = layer.confirm("是否确认删除？",function(){
			 var _len = $("#positionTab tr").length;
	 	     $("#positionTab tr[id='"+index+"']").remove();//删除当前行
	 	     layer.close(confirmWin);
	     });
	  }
	  //资金到账-业态细类
	 var arrival=function(index)
	 {
		 var confirmWin = layer.confirm("是否确认删除？",function(){
			 var _len = $("#arrivalTab tr").length;
	 	     $("#arrivalTab tr[id='"+index+"']").remove();//删除当前行
	 	     layer.close(confirmWin);
	     });
	 }
	  //资金到账-业态大类
	 var arrivalbig=function(index)
	 {
		 var confirmWin = layer.confirm("是否确认删除？",function(){
			 var _len = $("#arrivalbigTab tr").length;
	 	     $("#arrivalbigTab tr[id='"+index+"']").remove();//删除当前行
	 	     layer.close(confirmWin);
	     });
	 }
	 //资金到账-项目
	 var arrivalpro=function(index)
	 {
		 var confirmWin = layer.confirm("是否确认删除？",function(){
			 var _len = $("#arrivalproTab tr").length;
	 	     $("#arrivalproTab tr[id='"+index+"']").remove();//删除当前行
	 	     layer.close(confirmWin);
	     });
	 }


	var cityCompanyIdQuery = "";//城市公司查询条件
    var projectDataQuery = "";//项目查询条件
    var projectDataList = [];//项目数据集
    var earnestMoneyDetailList = [];//诚意金列表
    
    $(function(){
    	//查询项目列表
    	queryProjectList();
    	
        //查询城市公司
        queryCityCompany();
        //查询项目列表
        queryProjectData();
        //查询项目公司
        queryProjectCompanyData();
    }); 
    
    //查询城市公司
    function queryCityCompany() {
 	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"所有城市公司",tableName:"city_company"},function(result){
 			addSelectData(result, "cityCompanyIdQuery");
 			html =[];
			$.each(result,function(){
				if (this.id) {
				    html.push('<option value="'+this.id+'">'+this.name+'</option>');
				}
			});
			$("#city_company_id").html(html.join(""));
 		},'json');
    }
    
    //查询项目列表按钮单击
    function queryProjectDataBtn(pageNo) {
    	cityCompanyIdQuery = $("#cityCompanyIdQuery").val();
    	projectDataQuery = $("#projectDataQuery").val();
    	//alert(cityCompanyIdQuery);
    	//alert(projectDataQuery);
    	queryProjectList(pageNo);
    }
    
    //查询项目列表
    function queryProjectData(id) {
       var whereStr = "";
       if (id != "" && id != null && id != "null" && id != undefined) {
    	   whereStr = " where city_company_id = " + id;
       }
  	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{whereStr:whereStr,allName:"所有项目",tableName:"project"},function(result){
  			addSelectData(result, "projectDataQuery");
  		},'json');
     }
    
    //查询项目公司
    function queryProjectCompanyData() {
  	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"project_company"},function(result){
  			addSelectData(result, "project_company_id");
  		},'json');
     }
    
    //查询房源
    function queryHouseData() {
  	   $.post(so.getRootPath() + '/housingManagement/selectcDimDataAll.shtml',{project_id:$("#project_id").val()},function(result){
  			addSelectData(result, "house_id");
  		},'json');
     }
    
    //下拉框数据添加
    function addSelectData(result, id) {
    	if(result && result.length > 0){
			var html =[];
			$.each(result,function(){
				html.push('<option value="'+this.id+'">'+this.name+'</option>');
			});
			$("#" + id).html(html.join(""));
		}
    }
    
   //查询项目列表
   function queryProjectList(pageNo) {
	   //alert("qq["+$("#datepickerimessage").val()+"]No["+pageNo+"]");
	   $.post(so.getRootPath() + '/projectDataManagement/projectList.shtml',{cityCompanyIdQuery:cityCompanyIdQuery,projectDataQuery:projectDataQuery,pageNo:pageNo,userId:$("#currentUserId").val()},function(result){
		    //alert(result.totalCount);
		   //result.list=[];
		   $("#projectListPageDiv").html(result.finacePageHtml);
		   if (result.list && result.list.length > 0) {
			   projectDataList = result.list;
			   var html = [];
			   $.each(result.list,function(idx){
					//html.push('<option value="'+this.id+'">'+this.name+'</option>');
				   //alert(this.id+"<>"+this.name);
				   html.push("<tr>");
				   html.push("<td class='tc'>"+(null == this.name ? "-" : this.name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.erp_code ? "-" : this.erp_code)+"</td>");
				   html.push("<td class='tc'>"+(null == this.city_company_name ? "-" : this.city_company_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.project_company_name ? "-" : this.project_company_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.sale_start_time ? "-" : this.sale_start_time)+"</td>");
				   html.push("<td class='tc'>"+(null == this.saled_ratio ? "-" : this.saled_ratio)+"</td>");
				   html.push("<td class='tc'>"+(null == this.accepted_ratio ? "-" : this.accepted_ratio)+"</td>");
				   html.push('<td class="tc"><button onclick="showProjectBasicMsgDetail('+idx+')" class="btn btn-xs btn-warning infomessage"  title="详情"><i class="icon-pencil"></i> </button></td>');
				   html.push("</tr>");
			   });
			   $("#projectListTable").html(html.join(""));
			   $('.infomessage').click(function(e){
			       $("#icon_group_list1 i.icon-chevron-up").click();
				   if($("#icon_group_list2 i.icon-chevron-down").length>0){
				       $("#icon_group_list2").click();
				  	   $("#icon_group_list1 i.icon-chevron-up").click();
			       }
			   });
		   } else {
			   $("#projectListTable").html("<tr><td colspan='10' align='center'>暂无数据！</td></tr>");
		   }
		},'json');
   }
   
   //展示项目详细信息
   function showProjectBasicMsgDetail(idx) {
	   $("#projectSubTab").find("li").attr("class", "");
	   $("#projectSubTab").find("li:first").find("a").trigger("click");
	   
	   //alert(projectDataList[idx].city_company_id);
	   $("#project_id").val(projectDataList[idx].id);
	   $("#project_name").val(projectDataList[idx].name);
	   $("#erp_code").val(projectDataList[idx].erp_code);
	   $("#city_company_id").val(projectDataList[idx].city_company_id);
	   $("#project_company_id").val(projectDataList[idx].project_company_id);
	   $("#datepickerstart").val(projectDataList[idx].sale_start_time);
	   $("#saled_ratio").val(projectDataList[idx].saled_ratio);
	   $("#accepted_ratio").val(projectDataList[idx].accepted_ratio);
	   $("#project_update_user").val(projectDataList[idx].update_user_name);
	   $("#datepickermanage").val(projectDataList[idx].update_time);
	   $("#remark").val(projectDataList[idx].remark);
	   
	   //查询房源
       queryHouseData();
   }
   
   //保存项目信息
   function saveProjectMsg() {
	 // alert($("#datepickerstart").val());return;
	 if ($("#project_id").val() == "") {
	     layer.msg("请选择要修改的项目！",function(){});
		 return;
	 }
	 var confirmWin = layer.confirm("是否确认保存？",function(){
	 	 $("#project_user_id").val($("#currentUserId").val());
	 	 //alert($("#is_large_amount").val()+"<>"+$("#is_carry_forward").val()+"<>"+$("#is_change_name").val());
	 	 var options = {
	         url: so.getRootPath() + '/projectDataManagement/updateProjectMsgById.shtml',
	         type: 'post',
	         dataType: 'json',
	         data: $("#projectBasicForm").serialize(),
	         success:  function (result) {
	        	if (result.status == "200") {
	        		layer.msg("保存成功！",function(){});
		         	queryProjectList(1);
	        	} else {
	        		layer.msg("保存失败，请确认信息是否填写完整，并稍后再试！",function(){});
	            }
	         },
	         error:function(){
	         	layer.msg("操作失败,请联系系统管理员！",function(){});
	         }
	     };
	     $.ajax(options);
	 });
   }

   //查询诚意金-tab页单击
   function queryEarnestMoneyDetail() {
	  if ($("#project_id").val() == "") {
		  return;
	  }
	  $.post(so.getRootPath() + '/housingManagement/selectcEarnestMoneyByProject.shtml',{project_id:$("#project_id").val(),status:$("#earnest_money_status_query").val(),pay_time:$("#datepicker").val()},function(result){
		  if (result && result.length > 0) {
			   earnestMoneyDetailList = result;
			   var html = [];
			   var totalAmount = 0;
			   var erpTotalAmount = 0;
			   $.each(result,function(idx){
				   //alert(this.id+"<>"+this.erp_name);
				   var statusStr = "";
				   if (this.status == "1") {
					  statusStr = "已收款";
				   } else if (this.status == "2") {
					  statusStr = "已退款";
				   } else if (this.status == "3") {
					  statusStr = "已转房款";
				   }
				   totalAmount += this.pay_amount;
				   erpTotalAmount += this.erp_paid_amount;
				   html.push("<tr>");
				   html.push("<td class='tc'>"+(null == this.code ? "" : this.code)+"</td>");
				   html.push("<td class='tc'>"+(null == this.erp_name ? "" : this.erp_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.pay_amount ? "" : this.pay_amount)+"</td>");
				   html.push("<td class='tc'>"+(null == this.refund_time ? "" : this.refund_time)+"</td>");
				   html.push("<td class='tc'>"+(null == this.convert_house_amount ? "" : this.convert_house_amount)+"</td>");
				   html.push("<td class='tc'>"+(null == this.remain_amount ? "" : this.remain_amount)+"</td>");
				   html.push("<td class='tc'>"+(statusStr)+"</td>");
				   html.push('<td class="tc"><button onclick=\'toEditEarnestMoneyDetailMsgPage("edit", '+idx+')\' class="btn btn-xs btn-warning infomessage"  title="详情"><i class="icon-pencil"></i> </button> &nbsp; <button onclick=\'toEditEarnestMoneyDetailMsgPage("del", '+idx+')\' class="btn btn-xs btn-danger" title="删除"><i class="icon-remove"></i> </button>  </td>');
				   html.push("</tr>");
			   });
			   $("#earnestMoneyDetailListTable").html(html.join(""));
			   
			   //展示对应的诚意金下拉框
			   setEarnestMoneyDetailSelect(result);
			   
			   //合同总金额(合同总金额从合同中获取（总价字段），不用汇总诚意金)
			   //if ($("#unit_price").val() != "" && $("#pre_size").val() != "") {
			   $("#money_pay_detail_contract_total").val($("#total_price").val());
			   //}
			   //已收款
			   $("#money_pay_detail_sure_total").val(totalAmount);
			   //ERP总收款
			   $("#money_pay_detail_erp_total").val(erpTotalAmount);
		   } else {
			   $("#earnestMoneyDetailListTable").html("<tr><td colspan='8' align='center'>暂无数据！</td></tr>");
		   }
   	  },'json');
   }
   
   //诚意金页面跳转
   function toEditEarnestMoneyDetailMsgPage(t, idx) {
 	  //alert("tt");
 	  //saveReceiptInvoiceDataMsg();
 	  clearForm("earnestMoneyForm", []);//诚意金
 	  $("#money_pay_detail_erp_late_fee_ratio").val("0");
 	  if ($("#project_id").val() == "") {
           layer.msg("请选择项目！",function(){});
 		  return;
 	  }
 	  if (t == "add") {
 		  $("#earnestMoneyDetailTitle").html("添加诚意金");
 		  $("#earnestMoneyModal").modal("show");
 		  $("#earnest_money_main_id").val("");
 	  } else if (t == "del"){
           var confirmWin = layer.confirm("是否确认删除？",function(){
           $.post(so.getRootPath() + '/housingManagement/deleteEarnestMoneyDetailData.shtml',{id:earnestMoneyDetailList[idx].id},function(result){
         	    if (result.status == "200") {
               	    queryEarnestMoneyDetail();
              		layer.msg("删除成功！",function(){});
 	        	} else {
 	        		layer.msg("删除失败，并稍后再试！",function(){});
 	        	}
        	   },'json');
 	      });
 	  } else if (t == "edit") {
 		    $("#earnestMoneyDetailTitle").html("修改诚意金");
 		    $("#earnestMoneyModal").modal("show");
 		    //alert("edit["+earnestMoneyDetailList[idx].id+"]");
 		    $("#earnest_money_main_id").val(earnestMoneyDetailList[idx].id);
 		    $("#house_id").val(earnestMoneyDetailList[idx].house_id); 		   
 		    //alert("s1");//yrfyrfyrf
 		    $("#pay_type").val(earnestMoneyDetailList[idx].pay_type);
		    $("#datepickerpay").val(earnestMoneyDetailList[idx].pay_time);
		    $("#earnest_money_status").val(earnestMoneyDetailList[idx].status);
		    $("#pos_amount").val(earnestMoneyDetailList[idx].pos_amount);
		    $("#cash_amount").val(earnestMoneyDetailList[idx].cash_amount);
		    $("#bank_transfer_amount").val(earnestMoneyDetailList[idx].bank_transfer_amount);
		    $("#bank_bill_amount").val(earnestMoneyDetailList[idx].bank_bill_amount);
		    $("#bank_bill_type").val(earnestMoneyDetailList[idx].bank_bill_type);
		    $("#bank_name").val(earnestMoneyDetailList[idx].bank_name);
		    $("#receipt_amount").val(earnestMoneyDetailList[idx].receipt_amount);
		    $("#remain_amount").val(earnestMoneyDetailList[idx].remain_amount);
		    $("#refund_amount").val(earnestMoneyDetailList[idx].refund_amount);
		    $("#refund_check_number").val(earnestMoneyDetailList[idx].refund_check_number);
		    $("#convert_house_amount").val(earnestMoneyDetailList[idx].convert_house_amount);
		    $("#earnest_money_update_user_id").val(earnestMoneyDetailList[idx].update_user_name);
		    $("#datepickercyj").val(earnestMoneyDetailList[idx].update_time);
		    $("#earnest_money_remark").val(earnestMoneyDetailList[idx].remark);
		    $("#earnest_money_main_id").val(earnestMoneyDetailList[idx].id);
 	  }
   }
   
   //保存诚意金
   function saveEarnestMoney() {
	     var url = "updateEarnestMoneyById";
		 if ($("#earnest_money_main_id").val() == "") {
			 url = "insertEarnestMoneyById";
		 }
		 $("#earnest_money_user_id").val($("#currentUserId").val());
	     $("#earnest_money_project_id").val($("#project_id").val());
		 //alert($("#is_large_amount").val()+"<>"+$("#is_carry_forward").val()+"<>"+$("#is_change_name").val());
	     if ($("#project_id").val() == "") {
		     layer.msg("请选择项目！",function(){});
			 return;
		 }
	     if ($("#datepickerpay").val() == "") {
		     layer.msg("请选择收款时间！",function(){});
			 return;
		 }
	     if ($("#pos_amount").val() == "") {
		     layer.msg("请输入pos金额！",function(){});
			 return;
		 }
	     if ($("#cash_amount").val() == "") {
		     layer.msg("请输入现金金额！",function(){});
			 return;
		 }
	     if ($("#bank_transfer_amount").val() == "") {
		     layer.msg("请输入银行转账金额！",function(){});
			 return;
		 }
	     if ($("#bank_bill_amount").val() == "") {
		     layer.msg("请输入银行票据金额！",function(){});
			 return;
		 }
	     if ($("#receipt_amount").val() == "") {
		     layer.msg("请输入换据金额！",function(){});
			 return;
		 }
	     if ($("#remain_amount").val() == "") {
		     layer.msg("请输入结余金额！",function(){});
			 return;
		 }
	     if ($("#refund_amount").val() == "") {
		     layer.msg("请输入退款金额！",function(){});
			 return;
		 }
	     if ($("#convert_house_amount").val() == "") {
		     layer.msg("请输入转房款金额！",function(){});
			 return;
		 }
	     var confirmWin = layer.confirm("是否确认保存？",function(){
			 var options = {
		        url: so.getRootPath() + '/housingManagement/'+url+'.shtml',
		        type: 'post',
		        dataType: 'json',
		        data: $("#earnestMoneyForm").serialize(),
		        success:  function (result) {
		        	//alert(data.id);
		        	if (result.status == "200") {
		        		$("#earnestMoneyModal").modal("hide");
			        	queryEarnestMoneyDetail();
			        	layer.msg("保存成功！",function(){});
		        	} else {
		        		layer.msg("保存失败，请确认信息是否填写完整，并稍后再试！",function(){});
		        	}
		        },
		        error:function(){
		        	layer.msg("操作失败，请联系系统管理员！",function(){});
		        }
		    };
		    $.ajax(options);
	    });
   }
   	 	
   
   
   //清除表单元素
   function clearForm(formId, cbxArr) {
 	  $("#"+formId+" input").each(function(){  
          $(this).val('');
      });
 	 $("#"+formId+" textarea").each(function(){  
          $(this).val('');
      });
 	 $("#"+formId+" select").each(function(){
      	$(this).find("option:first").prop("selected", 'selected');
      });
 	 if (cbxArr != "" && cbxArr != null) {
 		 for (i in cbxArr) {
 	         $("#"+cbxArr[i]).find('>div').attr('class', 'switch-off switch-animate');			 
 		 }
 	 } 
   }
   