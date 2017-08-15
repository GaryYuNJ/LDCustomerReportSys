	$(document).ready(function(){
		//房源佣金配置-资金到账
		$("#addFymoney").click(function(){
	       var _len = $("#fymoneyTab tr").length;        
	       $("#fymoneyTab").append("<tr id="+_len+" align='center'>"
				+"<td><div class='yjcol-lg-pd'><input type='text' class='form-control form-controlbg'></div></td>"
				+"<td><div class='yjcol-lg-pd'><input type='text' class='form-control form-controlbg'></div></td>"
				+"<td><div class='yjcol-lg-pd'><input type='text' class='form-control form-controlbg'></div></td>"
				+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
				+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'fymoney("+_len+")\'>删除</button></td>" 
	        	+"</tr>");
	      
	    }) 
	    //房源佣金配置-房源与职位
	    $("#addFyptz").click(function(){
	       var _len = $("#fyptzTab tr").length;        
	       $("#fyptzTab").append("<tr id="+_len+" align='center'>"
				+"<td><div class='yjcol-lg-pd'><select class='form-control selectfont'><option>案场经理</option></select></div></td>"
				+"<td><div class='yjcol-lg-pd'><input type='text' class='form-control form-controlbg'></div></td>"
				+"<td><div class='yjcol-lg-pd'><input type='text' class='form-control form-controlbg'></div></td>"
				+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
				+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'fyptz("+_len+")\'>删除</button></td>" 
	        	+"</tr>");
	      
	    }) 
		
	})	
	//删除一行
	//房源佣金配置-资金到账
	var fymoney =function(index)
	{
		var confirmWin = layer.confirm("是否确认删除？",function(){
			var _len = $("#fymoneyTab tr").length;
		    $("#fymoneyTab tr[id='"+index+"']").remove();//删除当前行
		    layer.close(confirmWin);
	    });
	}
	//房源佣金配置-房源职位
	var fyptz =function(index)
	{
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#fyptzTab tr").length;
	       $("#fyptzTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
	    });
	}

	var cityCompanyIdQuery = "";//城市公司id
    var projectNameQuery = "";//项目名称
    var projectPhaseQuery = "";//分期名称
    var houseTypeIdQuery = "";//房屋类型id
    var houseBusinessTypeIdQuery = "";//业态大类id
    var houseConsultantQuery = "";//置业顾问名字
    var customerNameQuery = "";//客户名字
    var erpNameQuery = "";//erp房源名称
    var isLargeAmountQuery = "1";//是否只看大单定制
    var houseDataList = [];//房源数据集
    var contractMoneyDataList = [];//合同金额信息
    var moneyPayDetailList = [];//收款记录数据集
    var receiptInvoiceDataList = [];//收据发票数据集
    
    var queryUserHouseRelFlag = false; //是否已查询房源关系
    var queryEarnestMoneyFlag = false; //是否已查询诚意金
    var queryContractFlag = false; //是否已查询合同
    var queryMoneyPayDetailFlag = false; //是否已查询收款记录
    var queryReceiptInvoiceFlag = false; //是否已查询收据发票
    
    var contractMoneyTypeList = [];//全部金额类型
    $(function(){
    	//查询房源列表
    	queryHouseList();
    	
    	
        //查询城市公司
        queryCityCompany();
        //查询房屋类型
        queryHouseType();
        //查询业态大类
        queryHouseBusinessType();
        //查询业态细类
        queryHouseBusinessSubType();
        //查询销售类型
        queryHouseSaleType();
        //查询房源状态
        queryHouseStatus();
        //查询项目列表
        queryProjectData();
        //查询楼栋列表
        queryBuildingData();
        
        //合同tab页
        queryHouseBuyMethodData();//购买方式
        queryHouseMortgageTypeData();//按揭类型
        queryContractMoneyTypeData();//金额类型
        queryMoneyOverdueReasonData();//查询合同金额逾期原因
        
        initAjaxFileUpload();
    }); 
    
    //初始化导入控件
    function initAjaxFileUpload() {
   	    $("#fileUploadHref").on("change","input[type='file']",function(){
   		    var filePath=$(this).val();
   		    if(isExcelFile(filePath)) {
   		        var arr=filePath.split('\\');
   		        var fileName=arr[arr.length-1];
   		        uploaApplyFile('uploadFile', fileName);
   		    }else{
   		    	layer.msg("请选择Excel文件！",function(){});
   		    }
       });	
   }
    
   function uploaApplyFile(fileNameId, fileName){
    	//alert("ok")
	    var load = layer.load();
    	$.ajaxFileUpload({
    		url: encodeURI(encodeURI(so.getRootPath() + "/housingManagement/addHouseData.shtml?picTypeId="+fileNameId)),// 用于文件上传的服务器端请求地址
    		secureuri : false, // 一般设置为false
    		fileElementId : fileNameId, // 文件上传空间的id属性
    		dataType : 'test', // 返回值类型,一般设置为json
    		success : function(data) // 服务器成功响应处理函数
    		{
    			layer.close(load);
    			/*if (data == "file2big")
    			{
    				layer.msg("图片不能大于100K！",function(){});
    				return false;
    			}*/
    			var result = eval("("+data+")");
    			if (result.status == "200") {
		        	layer.msg("房源导入成功！",function(){});
	    			//查询房源列表
	    	    	queryHouseList(1);
	        	} else {
	        		layer.msg("房源导入失败，请确认信息是否填写完整，并稍后再试！",function(){});
	        	}
    		},
    		error : function(data) {
    			layer.msg("房源导入失败，请确认信息是否填写完整，并稍后再试！",function(){});
    			layer.close(load);
    		}
        });
    }
    
   function isExcelFile(filePath) 
   {	
    	var pathLen = filePath.length;
    	var lastStr = filePath.substr(pathLen-4).toLowerCase();
    	var lastStr1 = filePath.substr(pathLen-5).toLowerCase();
    	if(".xls" == lastStr || ".xlsx" == lastStr1)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    //查询城市公司
    function queryCityCompany() {
 	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"全部城市公司",tableName:"city_company"},function(result){
 			addSelectData(result, "cityCompanyIdQuery");
 		},'json');
    }
    
    //查询房屋类型
    function queryHouseType() {
 	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"全部类型",tableName:"house_type"},function(result){
 			addSelectData(result, "houseTypeIdQuery");
 			html =[];
			$.each(result,function(){
				if (this.id) {
				    html.push('<option value="'+this.id+'">'+this.name+'</option>');
				}
			});
			$("#type_id").html(html.join(""));
 		},'json');
    }
    
    //查询业态大类
    function queryHouseBusinessType() {
 	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"全部业态大类",tableName:"house_business_type"},function(result){
			addSelectData(result, "houseBusinessTypeIdQuery");
			html =[];
			$.each(result,function(){
				if (this.id) {
				    html.push('<option value="'+this.id+'">'+this.name+'</option>');
				}
			});
			$("#business_type_id").html(html.join(""));
 		},'json');
    }
    
    //查询业态细类
    function queryHouseBusinessSubType() {
 	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"house_business_sub_type"},function(result){
 			addSelectData(result, "business_sub_type_id");
 		},'json');
    }
    
    //查询销售类型
    function queryHouseSaleType() {
 	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"house_sale_type"},function(result){
 			addSelectData(result, "sale_type_id");
 		},'json');
    }
    
    //查询房源状态
    function queryHouseStatus() {
  	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"house_status"},function(result){
  			addSelectData(result, "status_id");
  		},'json');
     }
    
    //查询项目列表
    function queryProjectData() {
  	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"project"},function(result){
  			addSelectData(result, "project_id");
  		},'json');
     }
    
    //查询职位列表
    function queryAppointmentsData() {
  	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"全部",tableName:"u_appointments"},function(result){
  			addSelectData(result, "appointmentsQuery");
  		},'json');
     }
    
    //查询楼栋列表
    function queryBuildingData() {
   	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"building"},function(result){
   			addSelectData(result, "building_id");
   		},'json');
    }
    
    //查询购买方式
    function queryHouseBuyMethodData() {
   	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"house_buy_method"},function(result){
   			addSelectData(result, "buy_method_id");
   		},'json');
    }
    
    //查询按揭类型
    function queryHouseMortgageTypeData() {
   	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"house_mortgage_type"},function(result){
   			addSelectData(result, "mortgage_type_id");
   		},'json');
    }
    
    //查询金额类型
    function queryContractMoneyTypeData() {
   	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"money_type"},function(result){
   			addSelectData(result, "contract_money_type_id");
   			addSelectData(result, "receipt_invoice_money_type_id");
   			//addSelectData(result, "money_pay_detail_money_type_id");
   			//yrf2
   			contractMoneyTypeList = result;
   		},'json');
    }
    
    //查询合同金额逾期原因
    function queryMoneyOverdueReasonData() {
   	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"overdue_reason"},function(result){
   			addSelectData(result, "overdue_reason_id");
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
    
    //查询房源列表-按钮单击查询
    function queryHouseListBtn(pageNo) {
    	cityCompanyIdQuery = $("#cityCompanyIdQuery").val();
    	projectNameQuery = $("#projectNameQuery").val();
    	projectPhaseQuery = $("#projectPhaseQuery").val();
    	houseTypeIdQuery = $("#houseTypeIdQuery").val();
    	houseBusinessTypeIdQuery = $("#houseBusinessTypeIdQuery").val();
    	houseConsultantQuery = $("#houseConsultantQuery").val();
    	customerNameQuery = $("#customerNameQuery").val();
    	erpNameQuery = $("#erpNameQuery").val();
    	if ($("#isLargeAmountQuery").find("div[class*='switch-off']").length == 0) {
    		isLargeAmountQuery = "1";
    	} else {
    		isLargeAmountQuery = "0";
    	}
    	queryHouseList(pageNo);
    }
    
    //查询房源列表
    function queryHouseList(pageNo) {
	   //alert("qq["+$("#datepickerimessage").val()+"]No["+pageNo+"]");
	   $.post(so.getRootPath() + '/housingManagement/houseList.shtml',{cityCompanyIdQuery:cityCompanyIdQuery,projectNameQuery:projectNameQuery,
		   projectPhaseQuery:projectPhaseQuery,houseTypeIdQuery:houseTypeIdQuery,houseBusinessTypeIdQuery:houseBusinessTypeIdQuery,
		   houseConsultantQuery:houseConsultantQuery,customerNameQuery:customerNameQuery,erpNameQuery:erpNameQuery,
		   isLargeAmountQuery:isLargeAmountQuery,pageNo:pageNo,userId:$("#currentUserId").val()},function(result){
		    //alert(result.totalCount);
		   //result.list=[];
		   $("#houseListPageDiv").html(result.finacePageHtml);
		   if (result.list && result.list.length > 0) {
			   houseDataList = result.list;
			   var html = [];
			   $.each(result.list,function(idx){
					//html.push('<option value="'+this.id+'">'+this.name+'</option>');
				   //alert(this.id+"<>"+this.erp_name);
				   html.push("<tr>");
				   html.push("<td class='tc'>"+(null == this.projectName ? "-" : this.projectName)+"</td>");
				   html.push("<td class='tc'>"+(null == this.projectPhase ? "-" : this.projectPhase)+"</td>");
				   html.push("<td class='tc'>"+(null == this.buildName ? "-" : this.buildName)+"</td>");
				   html.push("<td class='tc'>"+(null == this.erpName ? "-" : this.erpName)+"</td>");
				   html.push("<td class='tc'>"+(null == this.houseTypeName ? "-" : this.houseTypeName)+"</td>");
				   html.push("<td class='tc'>"+(null == this.houseConsultant ? "-" : this.houseConsultant)+"</td>");
				   html.push("<td class='tc'>"+(null == this.customerName ? "-" : this.customerName)+"</td>");
				   html.push("<td class='tc'>"+(null == this.houseBusinessTypeName ? "-" : this.houseBusinessTypeName)+"</td>");
				   html.push("<td class='tc'>"+(null == this.houseStatusName ? "-" : this.houseStatusName)+"</td>");
				   html.push('<td class="tc"><button onclick="showHouseBasicMsgDetail('+idx+')" class="btn btn-xs btn-warning infomessage"  title="详情"><i class="icon-pencil"></i> </button></td>');
				   html.push("</tr>");
			   });
			   $("#houseListTable").html(html.join(""));
			   $('.infomessage').click(function(e){
			       $("#icon_group_list1 i.icon-chevron-up").click();
				   if($("#icon_group_list2 i.icon-chevron-down").length>0){
				       $("#icon_group_list2").click();
				  	   $("#icon_group_list1 i.icon-chevron-up").click();
			       }
			   });
		   } else {
			   $("#houseListTable").html("<tr><td colspan='10' align='center'>暂无数据！</td></tr>");
		   }
		},'json');
   }
    
   //展示房源详细信息
   function showHouseBasicMsgDetail(idx) {
	   //查询项目列表
  	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"project"},function(result){
 			addSelectData(result, "project_id");
 			
 	  	   //查询楼栋列表
 	   	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"building"},function(result){
 	  		   addSelectData(result, "building_id");
 	  			
 	  		   queryUserHouseRelFlag = false; //是否已查询房源关系
 	  		   queryEarnestMoneyFlag = false; //是否已查询诚意金
 	  		   queryContractFlag = false; //是否已查询合同
 	  		   queryMoneyPayDetailFlag = false; //是否已查询收款记录
 	  		   queryReceiptInvoiceFlag = false; //是否已查询收据发票
 	  		   moneyPayDetailList = []; //清空收款记录
 	  		   
 	  		   //alert($("#houseSubTab").find("li").length);
 	  		   $("#houseSubTab").find("li").attr("class", "");
 	  		   //$("#houseSubTab").find("li:first").attr("class", "active");
 	  		   $("#houseSubTab").find("li:first").find("a").trigger("click");
 	  		   
 	  		   //alert(houseDataList[idx].project_id);
 	  		   $("#house_main_id").val(houseDataList[idx].id);
 	  		   $("#project_id").val(houseDataList[idx].project_id);
 	  		   $("#project_phase").val(houseDataList[idx].project_phase);
 	  		   $("#building_id").val(houseDataList[idx].building_id);
 	  		   $("#erp_code").val(houseDataList[idx].erp_code);
 	  		   $("#erp_name").val(houseDataList[idx].erp_name);
 	  		   $("#erp_sale_code").val(houseDataList[idx].erp_sale_code);
 	  		   $("#business_type_id").val(houseDataList[idx].business_type_id);
 	  		   $("#business_sub_type_id").val(houseDataList[idx].business_sub_type_id);
 	  		   $("#sale_type_id").val(houseDataList[idx].sale_type_id);
 	  		   $("#type_id").val(houseDataList[idx].type_id);
 	  		   $("#pre_size").val(houseDataList[idx].pre_size);
 	  		   $("#status_id").val(houseDataList[idx].status_id);
 	  		   $("#carry_forward_income").val(houseDataList[idx].carry_forward_income);
 	  		   $("#carry_forward_cost_unit_price").val(houseDataList[idx].carry_forward_cost_unit_price);
 	  		   $("#real_size").val(houseDataList[idx].real_size);
 	  		   $("#customer_code").val(houseDataList[idx].customer_code);
 	  		   $("#customer_name").val(houseDataList[idx].customer_name);
 	  		   $("#customer_mobile").val(houseDataList[idx].customer_mobile);
 	  		   $("#customer_number").val(houseDataList[idx].customer_number);
 	  		   $("#house_update_user_id").val(houseDataList[idx].update_user_name);
 	  		   $("#house_update_time").val(houseDataList[idx].update_time);
 	  		   $("#building_id").val(houseDataList[idx].building_id);
 	  		   $("#remark").val(houseDataList[idx].remark);
 	  		   //是否大单定制
 	  		   /*alert(houseDataList[idx].is_large_amount);
 	  		   alert(houseDataList[idx].is_carry_forward);
 	  		   alert(houseDataList[idx].is_change_name);*/
 	  		   if (houseDataList[idx].is_large_amount == "1") {
 	  			   $("#is_large_amount_div").find('>div').attr('class', 'switch-on switch-animate');
 	  		   } else {
 	  		       $("#is_large_amount_div").find('>div').attr('class', 'switch-off switch-animate');
 	  		   }
 	  		   //是否结转成本
 	  		   if (houseDataList[idx].is_carry_forward == "1") {
 	  			   $("#is_carry_forward_div").find('>div').attr('class', 'switch-on switch-animate');
 	  		   } else {
 	  		       $("#is_carry_forward_div").find('>div').attr('class', 'switch-off switch-animate');
 	  		   }
 	  		   //是否更名
 	  		   if (houseDataList[idx].is_change_name == "1") {
 	  			   $("#is_change_name_div").find('>div').attr('class', 'switch-on switch-animate');
 	  		   } else {
 	  		       $("#is_change_name_div").find('>div').attr('class', 'switch-off switch-animate');
 	  		   }
 	  		   
 	  		   //alert("s1");
 	  		   //合同和收款记录需要预先查询出来，其它tab页依赖此数据
 	  		   //查询合同tab页单击
 	  	       queryContract();
 	  	   },'json');
 	   },'json');
  	   
	   
   }
   
    
  //保存房源信息
  function saveHouseMsg() {
	 $("#house_user_id").val($("#currentUserId").val());
	 $("#house_id").val($("#house_main_id").val());
	 if ($("#is_large_amount_div").find("div[class*='switch-off']").length == 0) {
  		$("#is_large_amount").val("1");
  	 } else {
  		$("#is_large_amount").val("0");
  	 }
	 if ($("#is_carry_forward_div").find("div[class*='switch-off']").length == 0) {
  		$("#is_carry_forward").val("1");
  	 } else {
  		$("#is_carry_forward").val("0");
  	 }
	 if ($("#is_change_name_div").find("div[class*='switch-off']").length == 0) {
  		$("#is_change_name").val("1");
  	 } else {
  		$("#is_change_name").val("0");
  	 }
	 //alert($("#is_large_amount").val()+"<>"+$("#is_carry_forward").val()+"<>"+$("#is_change_name").val());
	 if ($("#house_main_id").val() == "") {
	     layer.msg("请选择房源！",function(){});
		 return;
	 }
	 var confirmWin = layer.confirm("是否确认保存？",function(){
		 var options = {
	        url: so.getRootPath() + '/housingManagement/updateHouseMsg.shtml',
	        type: 'post',
	        dataType: 'json',
	        data: $("#houseMsgForm").serialize(),
	        success:  function (result) {
	        	if (result.status == "200") {
	        		layer.msg("保存成功！",function(){});
		        	queryHouseList(1);
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
  
  //查询房源关系
  function queryUserHouseRel() {
	  if (!queryUserHouseRelFlag) {
	      //查询职位列表
		  queryAppointmentsData();
		  //alert("查询房源");		
		  queryUserHouseRelFlag = true;
	  }
	  /* else {
		  alert("不用查询")
	  }*/
  }
  
  //查询诚意金
  function queryEarnestMoney() {
	  if (!queryEarnestMoneyFlag) {
		  queryEarnestMoneyFlag = true;
		  $.post(so.getRootPath() + '/housingManagement/selectcEarnestMoneyById.shtml',{house_id:$("#house_main_id").val()},function(result){
	   			if(result && result.length > 0){
	   			    //alert(result[0].id);
	   			    $("#pay_type").val(result[0].pay_type);
	   			    $("#datepickerpay").val(result[0].pay_time);
	   			    $("#earnest_money_status").val(result[0].status);
	   			    $("#pos_amount").val(result[0].pos_amount);
	   			    $("#cash_amount").val(result[0].cash_amount);
	   			    $("#bank_transfer_amount").val(result[0].bank_transfer_amount);
	   			    $("#bank_bill_amount").val(result[0].bank_bill_amount);
	   			    $("#bank_bill_type").val(result[0].bank_bill_type);
	   			    $("#bank_name").val(result[0].bank_name);
	   			    $("#receipt_amount").val(result[0].receipt_amount);
	   			    $("#remain_amount").val(result[0].remain_amount);
	   			    $("#refund_amount").val(result[0].refund_amount);
	   			    $("#refund_check_number").val(result[0].refund_check_number);
	   			    $("#convert_house_amount").val(result[0].convert_house_amount);
	   			    $("#earnest_money_update_user_id").val(result[0].update_user_name);
	   			    $("#datepickercyj").val(result[0].update_time);
	   			    $("#earnest_money_remark").val(result[0].remark);
	   			    $("#earnest_money_main_id").val(result[0].id);
	   			}
	   	  },'json');
	  }
  }
  
  //保存诚意金
  function saveEarnestMoney() {
	 var url = "updateEarnestMoneyById";
	 if ($("#earnest_money_main_id").val() == "") {
		 url = "insertEarnestMoneyById";
	 }
	 $("#earnest_money_user_id").val($("#currentUserId").val());
     $("#earnest_money_house_id").val($("#house_main_id").val());
	 //alert($("#is_large_amount").val()+"<>"+$("#is_carry_forward").val()+"<>"+$("#is_change_name").val());
     if ($("#house_main_id").val() == "") {
	     layer.msg("请选择房源！",function(){});
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
	        		$("#earnest_money_main_id").val(result.id);
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
  
  
  //查询合同tab页单击
  function queryContract() {
	  //alert("查询合同1")
	  if (!queryContractFlag) {
		  //alert("查询合同2")
		  queryContractFlag = true;
		  queryHouseContractMsg();//根据房源ID获查询合同基础信息
	  }
  }
  
  //根据房源ID获查询合同基础信息
  function queryHouseContractMsg() {
	  //清除表单元素
	  clearForm("houseContractMsgForm", "");//合同
	  clearForm("houseMortgageMsgForm", "");//按揭
	  $.post(so.getRootPath() + '/housingManagement/selectHouseContractMsg.shtml',{house_id:$("#house_main_id").val()},function(result){
   			if(result && result.length > 0){
   				$("#house_contract_id").val(result[0].id);
   				//alert(result[0].contract_code);
   			    $("#contract_code").val(result[0].contract_code);
   			    $("#buy_method_id").val(result[0].buy_method_id);
			    $("#unit_price").val(result[0].unit_price);
			    $("#datepickerqytime").val(result[0].sign_date);
   			    $("#datepickerhtqytime").val(result[0].verification_date);
			    $("#late_fee_ratio").val(result[0].late_fee_ratio);
			    $("#total_price").val(result[0].total_price);
   			    $("#contract_basic_update_user_id").val(result[0].update_user_name);
   			    $("#datepickerlastrefresh").val(result[0].update_time);
   			    $("#contract_basic_remark").val(result[0].remark);
   			    
   			    $("#house_mortgage_contract_id").val(result[0].id);
   			    //根据合同id查询按揭信息
   			    queryHouseMortgageMsg(result[0].id);
   			}
   			
   			queryHouseContractMoneyList();//根据房源ID查询合同金额信息
  		    //查询收款记录-tab页单击
    	    queryMoneyPayDetail();
   	  },'json');
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
  
  //根据房源ID查询按揭信息
  function queryHouseMortgageMsg(id) {
	  //清除表单元素
	  clearForm("houseMortgageMsgForm", "");
	  $.post(so.getRootPath() + '/housingManagement/selectHouseMortgageDataMsg.shtml',{contract_id:id},function(result){
   			if(result && result.length > 0){
   			    //alert(result[0].contract_code);
   				$("#house_mortgage_id").val(result[0].id);
   			    $("#mortgage_type_id").val(result[0].mortgage_type_id);
   			    $("#downpayments_ratio").val(result[0].downpayments_ratio);
			    $("#downpayments_amount").val(result[0].downpayments_amount);
			    $("#public_fund_loan_amount").val(result[0].public_fund_loan_amount);
   			    $("#business_loan_amount").val(result[0].business_loan_amount);
			    $("#loan_bank").val(result[0].loan_bank);
			    $("#mortgage_department").val(result[0].mortgage_department);
   			    $("#house_mortgage_update_user_id").val(result[0].update_user_name);
   			    $("#datepickerlast").val(result[0].update_time);
   			    $("#house_mortgage_basic_remark").val(result[0].remark);
   			}
   	  },'json');
  }
  
  //保存合同基础信息
  function saveHouseContractMsg() {
	 $("#house_contract_user_id").val($("#currentUserId").val());
     $("#house_contract_house_id").val($("#house_main_id").val());
	 //alert($("#is_large_amount").val()+"<>"+$("#is_carry_forward").val()+"<>"+$("#is_change_name").val());
     if ($("#house_main_id").val() == "") {
	     layer.msg("请选择房源！",function(){});
		 return;
	 }
     if ($("#buy_method_id").val() == "") {
	     layer.msg("请选择购买方式！",function(){});
		 return;
	 }
     if ($("#unit_price").val() == "") {
	     layer.msg("请输入合同单价！",function(){});
		 return;
	 }
     if ($("#datepickerqytime").val() == "") {
	     layer.msg("请选择签约时间！",function(){});
		 return;
	 }
     if ($("#datepickerhtqytime").val() == "") {
	     layer.msg("请选择合同签订时间！",function(){});
		 return;
	 }
     if ($("#late_fee_ratio").val() == "") {
	     layer.msg("请输入违约金比率！",function(){});
		 return;
	 }
     var confirmWin = layer.confirm("是否确认保存？",function(){
		 var options = {
	        url: so.getRootPath() + '/housingManagement/updateHouseContractMsgById.shtml',
	        type: 'post',
	        dataType: 'json',
	        data: $("#houseContractMsgForm").serialize(),
	        success:  function (result) {
	        	if (result.status == "200") {
	        		$("#house_contract_id").val(result.id);
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
  
  //保存按揭信息
  function saveHouseMortgageMsg() {
	 $("#house_mortgage_contract_id").val($("#house_contract_id").val());
	 $("#house_mortgage_user_id").val($("#currentUserId").val());
     $("#house_mortgage_house_id").val($("#house_main_id").val());
	 //alert($("#is_large_amount").val()+"<>"+$("#is_carry_forward").val()+"<>"+$("#is_change_name").val());
     if ($("#house_main_id").val() == "") {
	     layer.msg("请选择房源！",function(){});
		 return;
	 }
     if ($("#house_contract_id").val() == "") {
	     layer.msg("请先保存合同数据！",function(){});
		 return;
	 }
     if ($("#downpayments_ratio").val() == "") {
	     layer.msg("请输入首付比例！",function(){});
		 return;
	 }
     if ($("#downpayments_amount").val() == "") {
	     layer.msg("请输入首付金额！",function(){});
		 return;
	 }
     if ($("#public_fund_loan_amount").val() == "") {
	     layer.msg("请输入公积金贷款金额！",function(){});
		 return;
	 }
     if ($("#business_loan_amount").val() == "") {
	     layer.msg("请输入商业贷款金额！",function(){});
		 return;
	 }
     var confirmWin = layer.confirm("是否确认保存？",function(){
		 var options = {
	        url: so.getRootPath() + '/housingManagement/updateHouseMortgageDataById.shtml',
	        type: 'post',
	        dataType: 'json',
	        data: $("#houseMortgageMsgForm").serialize(),
	        success:  function (result) {
	        	if (result.status == "200") {
	        		$("#house_mortgage_id").val(result.id);
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
  
  //查询合同金额信息
  function queryHouseContractMoneyList() {
	   //alert("query");
	   contractMoneyDataList = [];
	   //alert("qq["+$("#datepickerimessage").val()+"]No["+pageNo+"]");
	   $.post(so.getRootPath() + '/housingManagement/selectHouseContractMoneyMsg.shtml',{house_id:$("#house_main_id").val()},function(result){
		   //result = [];
		   if (result && result.length > 0) {
			   //alert("result.length["+result.length+"]");
			   contractMoneyDataList = result;
			   var html = [];
			   $.each(result,function(idx){
				   //alert(this.id+"<>"+this.erp_name);
				   var statusStr = "";
				   if (this.status == "1") {
					   statusStr = "未收款";
				   } else if (this.status == "2") {
					   statusStr = "部分收款";
				   } else if (this.status == "3") {
					   statusStr = "全额收款";
				   }
				   html.push("<tr>");
				   html.push("<td class='tc'>"+(null == this.money_type_name ? "-" : this.money_type_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.amount ? "-" : this.amount)+"</td>");
				   html.push("<td class='tc'>"+(null == this.receivables_time ? "-" : this.receivables_time)+"</td>");
				   html.push("<td class='tc'>"+(null == this.pay_amount ? "-" : this.pay_amount)+"</td>");
				   html.push("<td class='tc'>"+statusStr+"</td>");
				   html.push("<td class='tc'>"+("1" == this.is_overdue ? "是" : "否")+"</td>");
				   html.push("<td class='tc'>"+(null == this.remark ? "-" : this.remark)+"</td>");
				   html.push('<td class="tc"><button onclick="toSaveHouseContractMoneyMsgPage(\'edit\','+idx+')" class="btn btn-xs btn-warning infomessage"  title="详情"><i class="icon-pencil"></i> </button> &nbsp; <button onclick=\'toSaveHouseContractMoneyMsgPage("del", '+idx+')\' class="btn btn-xs btn-danger" title="删除"><i class="icon-remove"></i> </button>  </td>');
				   html.push("</tr>");
			   });
			   $("#contractMoneyDataListTable").html(html.join(""));
			   
			   //展示对应的合同金额记录下拉框
			   setMoneyPayDetailContractMoneySelect(result);
		   } else {
			   $("#contractMoneyDataListTable").html("<tr><td colspan='8' align='center'>暂无数据！</td></tr>");
		   }
		},'json');
   }
  
  //到编辑合同金额页面
  function toSaveHouseContractMoneyMsgPage(t, idx) {
	  clearForm("contractMoneyEditForm", ["is_overdue_div","is_recover_available_div"]);//合同金额详
	  if ($("#house_main_id").val() == "") {
		     layer.msg("请选择房源！",function(){});
			 return;
	  }
	  if (t == "add") {
		  $("#contractMoneyTitle").html("添加合同金额");
		  $("#myModalContract").modal("show");
		  $("#contract_money_id").val("");
		  
		  var totalAmount = 0;
		  for (var i in moneyPayDetailList) {
		      totalAmount += moneyPayDetailList[i].pay_amount;
		  }
		  $("#contract_money_pay_amount").val(totalAmount);//已收款
		  //alert("add");
	  } else if (t == "del"){
          var confirmWin = layer.confirm("是否确认删除？",function(){
              $.post(so.getRootPath() + '/housingManagement/deleteHouseContractMoneyById.shtml',{id:contractMoneyDataList[idx].id},function(result){
            	    if (result.status == "200") {
            	    	queryHouseContractMoneyList();
                 		layer.msg("删除成功！",function(){});
	  	        	} else {
	  	        		layer.msg("删除失败，并稍后再试！",function(){});
	  	        	}
           	   },'json');
    	      });
      } else if (t == "edit") {
		   //alert(contractMoneyDataList[idx].money_type_id);
    	   $("#contractMoneyTitle").html("修改合同金额");
    	   $("#myModalContract").modal("show");
		   $("#contract_money_id").val(contractMoneyDataList[idx].id);
		   $("#contract_money_type_id").val(contractMoneyDataList[idx].money_type_id);
		   $("#contract_money_amount").val(contractMoneyDataList[idx].amount);
		   
		   $("#contract_money_sure_amount").val(contractMoneyDataList[idx].amount);//应收款
		   var totalAmount = 0;
		   for (var i in moneyPayDetailList) {
			   totalAmount += moneyPayDetailList[i].pay_amount;
		   }
		   $("#contract_money_pay_amount").val(totalAmount);//已收款
		   
		   $("#datepickerhtget").val(contractMoneyDataList[idx].receivables_time);
		   $("#contract_money_status").val(contractMoneyDataList[idx].status);
		   
		   
		   $("#overdue_reason_id").val(contractMoneyDataList[idx].overdue_reason_id);
		   $("#overdue_extend_remark").val(contractMoneyDataList[idx].overdue_extend_remark);
		   $("#datepickerhtpay").val(contractMoneyDataList[idx].expect_recover_date);
		 
		   $("#contract_money_show_user_id").val(contractMoneyDataList[idx].update_user_name);
		   $("#datepickerhtpaylast").val(contractMoneyDataList[idx].update_time);
		   $("#contract_money_remark").val(contractMoneyDataList[idx].remark);
		   //是否逾期
		   if (contractMoneyDataList[idx].is_overdue == "1") {
			   $("#is_overdue_div").find('>div').attr('class', 'switch-on switch-animate');
		   } else {
		       $("#is_overdue_div").find('>div').attr('class', 'switch-off switch-animate');
		   }
		   //是否能收回
		   if (contractMoneyDataList[idx].is_recover_available == "1") {
			   $("#is_recover_available_div").find('>div').attr('class', 'switch-on switch-animate');
		   } else {
		       $("#is_recover_available_div").find('>div').attr('class', 'switch-off switch-animate');
		   }
	  }
  }
  
  //保存合同金额信息
  function saveHouseContractMoneyMsg() {
     if ($("#contract_money_amount").val() == "") {
	     layer.msg("请输入合同金额！",function(){});
		 return;
	 }
     if ($("#datepickerhtget").val() == "") {
	     layer.msg("请选择应收款时间！",function(){});
		 return;
	 }
     if ($("#datepickerhtpay").val() == "") {
	     layer.msg("请选择预计回收日期！",function(){});
		 return;
	 }
	 //是否逾期
     if ($("#is_overdue_div").find("div[class*='switch-off']").length == 0) {
  		$("#is_overdue").val("1");
  	 } else {
  		$("#is_overdue").val("0");
  	 }
     //是否能收回
	 if ($("#is_recover_available_div").find("div[class*='switch-off']").length == 0) {
  		$("#is_recover_available").val("1");
  	 } else {
  		$("#is_recover_available").val("0");
  	 }
	 $("#contract_money_user_id").val($("#currentUserId").val());
     $("#contract_money_house_id").val($("#house_main_id").val());
	 //alert($("#is_large_amount").val()+"<>"+$("#is_carry_forward").val()+"<>"+$("#is_change_name").val());
     //alert("a1");
     var confirmWin = layer.confirm("是否确认保存？",function(){
		 var options = {
	        url: so.getRootPath() + '/housingManagement/updateHouseContractMoneyById.shtml',
	        type: 'post',
	        dataType: 'json',
	        data: $("#contractMoneyEditForm").serialize(),
	        success:  function (result) {
	        	if (result.status == "200") {
	        		$("#myModalContract").modal("hide");
		        	$("#house_mortgage_id").val(result.id);
		        	layer.msg("保存成功！",function(){});
		        	//重新查询合同金额信息
		        	queryHouseContractMoneyList();
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
  
  //查询收款记录-tab页单击
  function queryMoneyPayDetail() {
	  //alert("查询收款记录1")
	  if (!queryMoneyPayDetailFlag) {
		  //alert("查询收款记录2")
		  queryMoneyPayDetailFlag = true;
		  $("#money_pay_detail_contract_total").val($("#total_price").val());//合同总金额
		  $("#money_pay_detail_sure_total").val("");//已收款
		  $("#money_pay_detail_erp_total").val("");//ERP总收款
		  $.post(so.getRootPath() + '/housingManagement/selectMoneyPayDetailById.shtml',{house_id:$("#house_main_id").val()},function(result){
			  if (result && result.length > 0) {
				  moneyPayDetailList = result;
				   var html = [];
				   var totalAmount = 0;
				   var erpTotalAmount = 0;
				   $.each(result,function(idx){
					   //alert(this.id+"<>"+this.erp_name);
					   var payTypeStr = "";
					   if (this.pay_type == "1") {
						   payTypeStr = "现金";
					   } else if (this.pay_type == "2") {
						   payTypeStr = "pos";
					   } else if (this.pay_type == "3") {
						   payTypeStr = "银行转账";
					   }
					   totalAmount += this.pay_amount;
					   erpTotalAmount += this.erp_paid_amount;
					   html.push("<tr>");
					   html.push("<td class='tc'>"+(null == this.name ? "-" : this.name)+"</td>");
					   html.push("<td class='tc'>"+(null == this.amount ? "-" : this.amount)+"</td>");
					   html.push("<td class='tc'>"+(null == this.receivables_time ? "-" : this.receivables_time)+"</td>");
					   html.push("<td class='tc'>"+(null == this.pay_amount ? "-" : this.pay_amount)+"</td>");
					   html.push("<td class='tc'>"+(null == this.pay_time ? "-" : this.pay_time)+"</td>");
					   html.push("<td class='tc'>"+(payTypeStr)+"</td>");
					   html.push("<td class='tc'>"+("1" == this.is_overdue ? "是" : "否")+"</td>");
					   html.push('<td class="tc"><button onclick=\'toEditMoneyPayDetailMsgPage("edit", '+idx+')\' class="btn btn-xs btn-warning infomessage"  title="详情"><i class="icon-pencil"></i> </button> &nbsp; <button onclick=\'toEditMoneyPayDetailMsgPage("del", '+idx+')\' class="btn btn-xs btn-danger" title="删除"><i class="icon-remove"></i> </button>  </td>');
					   html.push("</tr>");
				   });
				   $("#moneyPayDetailListTable").html(html.join(""));
				   
				   //展示对应的收款记录下拉框
				   setMoneyPayDetailSelect(result);
				   
				   //合同总金额(合同总金额从合同中获取（总价字段），不用汇总收款记录)
				   //if ($("#unit_price").val() != "" && $("#pre_size").val() != "") {
				   $("#money_pay_detail_contract_total").val($("#total_price").val());
				   //}
				   //已收款
				   $("#money_pay_detail_sure_total").val(totalAmount);
				   //ERP总收款
				   $("#money_pay_detail_erp_total").val(erpTotalAmount);
			   } else {
				   $("#moneyPayDetailListTable").html("<tr><td colspan='8' align='center'>暂无数据！</td></tr>");
			   }
	   	  },'json');
	  }
  }
  
  //收款记录页面跳转
  function toEditMoneyPayDetailMsgPage(t, idx) {
	  //alert("tt");
	  //saveReceiptInvoiceDataMsg();
	  clearForm("moneyPayDetailEditForm", ["money_pay_detail_is_late_fee_calculate_div","money_pay_detail_is_overdue_div", "money_pay_detail_is_dimission_calculate_div"]);//收款记录
	  $("#money_pay_detail_erp_late_fee_ratio").val("0");
	  if ($("#house_main_id").val() == "") {
          layer.msg("请选择房源！",function(){});
		  return;
	  }
	  if (t == "add") {
		  $("#moneyPayDetailTitle").html("添加收款记录");
		  $("#myModalAddRecord").modal("show");
		  $("#money_pay_detail_id").val("");
		  $("div[name='money_pay_detail_add_div'").show();
		  $("div[name='money_pay_detail_edit_div'").hide();
		  $("div[name='money_pay_detail_edit_div'").hide();
	  } else if (t == "del"){
          var confirmWin = layer.confirm("是否确认删除？",function(){
          $.post(so.getRootPath() + '/housingManagement/deleteMoneyPayDetailData.shtml',{id:moneyPayDetailList[idx].id},function(result){
        	    if (result.status == "200") {
        	    	queryMoneyPayDetailFlag = false;
              	    queryMoneyPayDetail();
             		layer.msg("删除成功！",function(){});
	        	} else {
	        		layer.msg("删除失败，并稍后再试！",function(){});
	        	}
       	   },'json');
	      });
	  } else if (t == "edit") {
		  $("#moneyPayDetailTitle").html("修改收款记录");
		  $("#myModalAddRecord").modal("show");
		  
		  $("div[name='money_pay_detail_add_div'").hide();
		  $("div[name='money_pay_detail_edit_div'").show();
		  $("div[name='money_pay_detail_edit_div'").show();
		  
		   //alert("eeeeeeeeeeeeee")
		   $("#myModalAddRecord").modal("show");
		   //alert("edit["+moneyPayDetailList[idx].id+"]");
		   $("#money_pay_detail_id").val(moneyPayDetailList[idx].id);
		   $("#money_pay_detail_money_type_id").val(moneyPayDetailList[idx].money_type_id);
		   $("#money_pay_detail_contract_money_id").val(moneyPayDetailList[idx].contract_money_id);
		   
		   //alert("s1");//yrfyrfyrf
		   setTotalAmountByMoneyType(moneyPayDetailList[idx].money_type_id);
		   
		   $("#money_pay_detail_pay_amount").val(moneyPayDetailList[idx].pay_amount);
		   $("#datepickerskdate").val(moneyPayDetailList[idx].pay_time);
		   $("#money_pay_detail_pay_type").val(moneyPayDetailList[idx].pay_type);
		   $("#money_pay_detail_bank_name").val(moneyPayDetailList[idx].bank_name);
		   $("#money_pay_detail_bank_bill_type").val(moneyPayDetailList[idx].bank_bill_type);
		   $("#money_pay_detail_bank_bill_number").val(moneyPayDetailList[idx].bank_bill_number);
		   $("#money_pay_detail_receipt_company").val(moneyPayDetailList[idx].receipt_company);
		   $("#money_pay_detail_receipt_name").val(moneyPayDetailList[idx].receipt_name);
		   //$("#aaaaaa").val(moneyPayDetailList[idx].status);
		   $("#money_pay_detail_erp_paid_amount").val(moneyPayDetailList[idx].erp_paid_amount);
		   $("#money_pay_detail_erp_late_fee_ratio").val(moneyPayDetailList[idx].erp_late_fee_ratio);
		   $("#money_pay_detail_erp_late_fee").val(moneyPayDetailList[idx].erp_late_fee);
		   $("#money_pay_detail_late_fee").val(moneyPayDetailList[idx].late_fee);
		   
		   $("#money_pay_detail_overdue_reason_id").val(moneyPayDetailList[idx].overdue_reason_id);
		 
		   $("#money_pay_detail_update_user_id").val(moneyPayDetailList[idx].update_user_name);
		   $("#datepickercyjsecond").val(moneyPayDetailList[idx].update_time);
		   $("#money_pay_detail_remark").val(moneyPayDetailList[idx].remark);
		   //是否已计算违约金
		   if (moneyPayDetailList[idx].is_late_fee_calculate == "1") {
			   $("#money_pay_detail_is_late_fee_calculate_div").find('>div').attr('class', 'switch-on switch-animate');
		   } else {
		       $("#money_pay_detail_is_late_fee_calculate_div").find('>div').attr('class', 'switch-off switch-animate');
		   }
		   //是否已逾期
		   if (moneyPayDetailList[idx].is_overdue == "1") {
			   $("#money_pay_detail_is_overdue_div").find('>div').attr('class', 'switch-on switch-animate');
		   } else {
		       $("#money_pay_detail_is_overdue_div").find('>div').attr('class', 'switch-off switch-animate');
		   }
		   //佣金是否已计算
		   if (moneyPayDetailList[idx].is_dimission_calculate == "1") {
			   $("#money_pay_detail_is_dimission_calculate_div").find('>div').attr('class', 'switch-on switch-animate');
		   } else {
		       $("#money_pay_detail_is_dimission_calculate_div").find('>div').attr('class', 'switch-off switch-animate');
		   }
	  }
  }
  
  //保存收款记录信息
  function saveMoneyPayDetailMsg() {
	 //alert("sa["+$("#money_pay_detail_id").val()+"]");
	 //return;
     if ($("#money_pay_detail_money_type_id").val() == "") {
	     layer.msg("请选择金额类型！",function(){});
		 return;
	 }
     if ($("#money_pay_detail_contract_money_id").val() == "") {
	     layer.msg("请选择对应的合同金额记录！",function(){});
		 return;
	 }
     if ($("#money_pay_detail_pay_amount").val() == "") {
	     layer.msg("请输入收到金额！",function(){});
		 return;
	 }
     if ($("#datepickerskdate").val() == "") {
	     layer.msg("请选择收款日期！",function(){});
		 return;
	 }
     if ($("#money_pay_detail_erp_late_fee_ratio").val() == "") {
	     layer.msg("请输入ERP违约金率！",function(){});
		 return;
	 }
	 var url = "insertMoneyPayDetailData";
	 if ($("#money_pay_detail_id").val() != "") {
		 url = "updateMoneyPayDetailData";
		 //alert($("#money_pay_detail_id").val());
	 }
	 if ($("#money_pay_detail_is_late_fee_calculate_div").find("div[class*='switch-off']").length == 0) {
  		$("#money_pay_detail_is_late_fee_calculate").val("1");
  	 } else {
  		$("#money_pay_detail_is_late_fee_calculate").val("0");
  	 }
	 if ($("#money_pay_detail_is_overdue_div").find("div[class*='switch-off']").length == 0) {
  		$("#money_pay_detail_is_overdue").val("1");
  	 } else {
  		$("#money_pay_detail_is_overdue").val("0");
  	 }
	 if ($("#money_pay_detail_is_dimission_calculate_div").find("div[class*='switch-off']").length == 0) {
  		$("#money_pay_detail_is_dimission_calculate").val("1");
  	 } else {
  		$("#money_pay_detail_is_dimission_calculate").val("0");
  	 }
	 $("#money_pay_detail_user_id").val($("#currentUserId").val());
	 //alert($("#is_large_amount").val()+"<>"+$("#is_carry_forward").val()+"<>"+$("#is_change_name").val());
	 var confirmWin = layer.confirm("是否确认保存？",function(){
		 var options = {
	        url: so.getRootPath() + '/housingManagement/'+url+'.shtml',
	        type: 'post',
	        dataType: 'json',
	        data: $("#moneyPayDetailEditForm").serialize(),
	        success:  function (result) {
	        	if (result.status == "200") {
	        		$("#myModalAddRecord").modal("hide");
		        	queryMoneyPayDetailFlag = false;
		      	    queryMoneyPayDetail();
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
  

  //展示对应的合同金额记录
  function setMoneyPayDetailContractMoneySelect(data) {
	  var html = [];
      $.each(data,function(){
	      html.push('<option value="'+this.id+'">'+this.money_type_name + "-" + this.amount +'</option>');
	  });
	  $("#money_pay_detail_contract_money_id").html(html.join(""));
	  //yrf3
	  html =[];
      $.each(contractMoneyTypeList,function(){
    	  for (i in data) {
    		  if (data[i].money_type_id == this.id) {
    			  html.push('<option value="'+this.id+'">'+this.name+'</option>');
    			  break;
    		  }  
    	  }
	  });
	  $("#money_pay_detail_money_type_id").html(html.join(""));
  }
  
  //修改收款记录时统计某种金额类型的总应收金额,应收款时间
  function setTotalAmountByMoneyType(t) {
	  //alert("gg");//yrfyrfyrf
	  var res = [];
	  var amount = 0;
	  var time = "";
      $.each(contractMoneyDataList,function(i,o){
		  if (this.money_type_id == t) {
			  amount += this.amount;
			  time = this.receivables_time;
		  }  
	  });
      //alert(amount);
      //alert(contractMoneyDataList[0].receivables_time);
      /*res.push(amount);
      res.push(contractMoneyDataList[0].receivables_time);
	  return res;*/
	  $("#money_pay_detail_receivables_amount").val(amount);
	  $("#money_pay_detail_receivables_time").val(time);
  }
  
  //展示对应的收款记录下拉框
  function setMoneyPayDetailSelect(data) {
	  //alert("set");
	  var html =[];
      $.each(data,function(){
	      html.push('<option value="'+this.id+'">'+this.name + "-" + this.pay_amount +'</option>');
	  });
	  $("#receipt_invoice_pay_detail_id").html(html.join(""));
  }
  
  //查询收据发票-tab页单击
  function queryReceiptInvoiceData() {
	  if (!queryReceiptInvoiceFlag) {
		  if (moneyPayDetailList.length == 0) {
			  //alert("qqq")
			  //查询收款记录
			  queryMoneyPayDetail();
		  }
		  queryReceiptInvoiceFlag = true;
		  $.post(so.getRootPath() + '/housingManagement/selectReceiptInvoiceDataById.shtml',{house_id:$("#house_main_id").val()},function(result){
			  if (result && result.length > 0) {
				  receiptInvoiceDataList = result;
				   var html = [];
				   $.each(result,function(idx){
					   //alert(this.id+"<>"+this.erp_name);
					   var typeStr = "";
					   if (this.type == "1") {
						   typeStr = "收据";
					   } else if (this.type == "2") {
						   typeStr = "普票";
					   } else if (this.type == "3") {
						   typeStr = "专票";
					   }  
					   html.push("<tr>");
					   html.push("<td class='tc'>"+typeStr+"</td>");
					   html.push("<td class='tc'>"+(null == this.invoice_time ? "-" : this.invoice_time)+"</td>");
					   html.push("<td class='tc'>"+(null == this.name ? "-" : this.name)+"</td>");
					   html.push("<td class='tc'>"+(null == this.code ? "-" : this.code)+"</td>");
					   html.push("<td class='tc'>"+(null == this.amount ? "-" : this.amount)+"</td>");
					   html.push("<td class='tc'>"+(null == this.invoice_tax_rate ? "-" : this.invoice_tax_rate)+"</td>");
					   html.push("<td class='tc'>"+(null == this.invoice_tax ? "-" : this.invoice_tax)+"</td>");
					   html.push("<td class='tc'>"+("1" == this.isvalid ? "有效" : "无效")+"</td>");
					   html.push("<td class='tc'>"+(null == this.old_code ? "-" : this.old_code)+"</td>");
					   html.push('<td class="tc"><button onclick=\'toEditReceiptInvoiceDataMsgPage("edit", '+idx+')\' class="btn btn-xs btn-warning infomessage"  title="详情"><i class="icon-pencil"></i> </button> &nbsp; <button onclick=\'toEditReceiptInvoiceDataMsgPage("del", '+idx+')\' class="btn btn-xs btn-danger" title="删除"><i class="icon-remove"></i> </button>  </td>');
					   html.push("</tr>");
				   });
				   $("#receiptInvoiceListTable").html(html.join(""));
			   } else {
				   $("#receiptInvoiceListTable").html("<tr><td colspan='10' align='center'>暂无数据！</td></tr>");
			   }
	   	  },'json');
	  }
  }
  
  //收据发票页面跳转
  function toEditReceiptInvoiceDataMsgPage(t, idx) {
	  //saveReceiptInvoiceDataMsg();
	  clearForm("receiptInvoiceEditForm", ["receipt_invoice_is_paid_added_tax_div","receipt_invoice_is_paid_sales_tax_div"]);//收款记录
	  if ($("#house_main_id").val() == "") {
          layer.msg("请选择房源！",function(){});
		  return;
	  }
	  if (t == "add") {
		  $("#receiptInvoiceTitle").html("添加收据/发票");
		  $("#myModalAddInvoice").modal("show");
		  //alert("addy1");
		  $("#receipt_invoice_id").val("");
	  } else if (t == "del"){
          var confirmWin = layer.confirm("是否确认删除？",function(){
          $.post(so.getRootPath() + '/housingManagement/deleteReceiptInvoiceData.shtml',{id:receiptInvoiceDataList[idx].id},function(result){
        	  if (result.status == "200") {
        		  queryReceiptInvoiceFlag = false;
                  queryReceiptInvoiceData();
           		  layer.msg("删除成功！",function(){});
	          } else {
	        	  layer.msg("删除失败，请稍后再试！",function(){});
	          }
       	   },'json');
	      });
	  } else if (t == "edit") {
		   $("#receiptInvoiceTitle").html("修改收据/发票");
		   $("#myModalAddInvoice").modal("show");
		   //alert(contractMoneyDataList[idx].money_type_id);
		   $("#receipt_invoice_id").val(receiptInvoiceDataList[idx].id);
		   $("#receipt_invoice_type").val(receiptInvoiceDataList[idx].type);
		   $("#receipt_invoice_money_type_id").val(receiptInvoiceDataList[idx].money_type_id);
		   $("#receipt_invoice_pay_detail_id").val(receiptInvoiceDataList[idx].pay_detail_id);
		   $("#receipt_invoice_code").val(receiptInvoiceDataList[idx].code);
		   $("#receipt_invoice_name").val(receiptInvoiceDataList[idx].name);
		   $("#receipt_invoice_receipt_company").val(receiptInvoiceDataList[idx].receipt_company);
		   $("#receipt_invoice_amount").val(receiptInvoiceDataList[idx].amount);
		   $("#datepickersjkpadd").val(receiptInvoiceDataList[idx].invoice_time);
		   $("#receipt_invoice_isvalid").val(receiptInvoiceDataList[idx].isvalid);
		   $("#receipt_invoice_invoice_tax_rate").val(receiptInvoiceDataList[idx].invoice_tax_rate);
		   $("#receipt_invoice_invoice_tax").val(receiptInvoiceDataList[idx].invoice_tax);
		   $("#receipt_invoice_old_code").val(receiptInvoiceDataList[idx].old_code);
		   $("#receipt_invoice_update_user").val(receiptInvoiceDataList[idx].update_user_name);
		   $("#datepickercyjfive").val(receiptInvoiceDataList[idx].update_time);
		   $("#receipt_invoice_remark").val(receiptInvoiceDataList[idx].remark);
		 
		   //是否已全额预缴增值税
		   if (receiptInvoiceDataList[idx].is_paid_added_tax == "1") {
			   $("#receipt_invoice_is_paid_added_tax_div").find('>div').attr('class', 'switch-on switch-animate');
		   } else {
		       $("#receipt_invoice_is_paid_added_tax_div").find('>div').attr('class', 'switch-off switch-animate');
		   }
		   //是否已缴营业税
		   if (receiptInvoiceDataList[idx].is_paid_sales_tax == "1") {
			   $("#receipt_invoice_is_paid_sales_tax_div").find('>div').attr('class', 'switch-on switch-animate');
		   } else {
		       $("#receipt_invoice_is_paid_sales_tax_div").find('>div').attr('class', 'switch-off switch-animate');
		   }
	  }
  }
  
  //保存收据发票信息
  function saveReceiptInvoiceDataMsg() {
     if ($("#receipt_invoice_pay_detail_id").val() == "") {
	     layer.msg("请选择对应的收款记录！",function(){});
		 return;
	 }
     if ($("#receipt_invoice_amount").val() == "") {
	     layer.msg("请输入金额！",function(){});
		 return;
	 }
     if ($("#datepickersjkpadd").val() == "") {
	     layer.msg("请选择开票时间！",function(){});
		 return;
	 }
	 var url = "insertReceiptInvoiceData";
	 if ($("#receipt_invoice_id").val() != "") {
		 url = "updateReceiptInvoiceData";
	 }
	 if ($("#receipt_invoice_is_paid_added_tax_div").find("div[class*='switch-off']").length == 0) {
  		$("#receipt_invoice_is_paid_added_tax").val("1");
  	 } else {
  		$("#receipt_invoice_is_paid_added_tax").val("0");
  	 }
	 if ($("#receipt_invoice_is_paid_sales_tax_div").find("div[class*='switch-off']").length == 0) {
  		$("#receipt_invoice_is_paid_sales_tax").val("1");
  	 } else {
  		$("#receipt_invoice_is_paid_sales_tax").val("0");
  	 }
	 $("#receipt_invoice_user_id").val($("#currentUserId").val());
	 //alert($("#is_large_amount").val()+"<>"+$("#is_carry_forward").val()+"<>"+$("#is_change_name").val());
	 var confirmWin = layer.confirm("是否确认保存？",function(){
		 var options = {
	        url: so.getRootPath() + '/housingManagement/'+url+'.shtml',
	        type: 'post',
	        dataType: 'json',
	        data: $("#receiptInvoiceEditForm").serialize(),
	        success:  function (result) {
	        	if (result.status == "200") {
	        		$("#myModalAddInvoice").modal("hide");
		        	queryReceiptInvoiceFlag = false;
		        	queryReceiptInvoiceData();
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
  
  //导出房源报表
  function exportHouseData() {
	   //alert("导出["+projectPhaseQuery+"]");
	   $("#userIdExportQuery").val($("#currentUserId").val());
	   $("#cityCompanyIdExportQuery").val(cityCompanyIdQuery);
	   $("#projectNameExportQuery").val(projectNameQuery);
	   $("#projectPhaseExportQuery").val(projectPhaseQuery);
	   $("#houseTypeIdExportQuery").val(houseTypeIdQuery);
	   $("#houseBusinessTypeIdExportQuery").val(houseBusinessTypeIdQuery);
	   $("#houseConsultantExportQuery").val(houseConsultantQuery);
	   $("#customerNameExportQuery").val(customerNameQuery);
	   $("#erpNameExportQuery").val(erpNameQuery);
	   $("#isLargeAmountExportQuery").val(isLargeAmountQuery);
	   
	   var load = layer.load();
	   var url = so.getRootPath() + "/housingManagement/exportHouseData.shtml";
	   $.fileDownload(url,{
	       //data:$("#exportHouseDataForm").serialize(),
		   data:{userId:$("#currentUserId").val(), type:"export",
			   cityCompanyIdQuery:cityCompanyIdQuery,projectNameQuery:encodeURIComponent(projectNameQuery),
			   projectPhaseQuery:encodeURIComponent(projectPhaseQuery), houseTypeIdQuery:houseTypeIdQuery,
			   houseBusinessTypeIdQuery:houseBusinessTypeIdQuery, houseConsultantQuery:encodeURIComponent(houseConsultantQuery),
			   customerNameQuery:encodeURIComponent(customerNameQuery), erpNameQuery:encodeURIComponent(erpNameQuery), 
			   isLargeAmountQuery:isLargeAmountQuery
		   },
	       successCallback: function (url) {
		       layer.close(load);
	       },
	       failCallback: function (html, url) {
	           layer.close(load);
	       }
      });
  }
  
  //计算合同总价(不入库)
  function countContractTotalPrice() {
	  if ($("#unit_price").val() != "" && $("#pre_size").val() != "") {
	      $("#total_price").val($("#unit_price").val() * $("#pre_size").val());		  
	      //$("#money_pay_detail_contract_total").val($("#unit_price").val() * $("#pre_size").val());
	  }
  }
  
  //设置合同总价(不入库)
  function setContractTotalPrice() {
	  $("#money_pay_detail_contract_total").val($("#total_price").val());
  }
  