    var queryMoneyPayReportListFlag = false; //是否已查询收款日报表
    var queryReceivableMoneyReportListFlag = false; //是否已查询应收款报表
    var queryIncomeBillReportListFlag = false; //是否已查询收入结账报表
    var queryInvoiceBillReportListFlag = false; //是否已查询开票情况报表

	var earnestMoneyRptcityCompanyIdQuery = "";//城市公司查询条件
    var earnestMoneyRptcityProjectDataQuery = "";//项目查询条件
    var earnestMoneyRptDatepickerstartQuery = "";//日期
    
    var moneyPayRptcityCompanyIdQuery = "";//城市公司查询条件
    var moneyPayRptcityProjectDataQuery = "";//项目查询条件
    var moneyPayRptDatepickerreportQuery = "";//日期
    
    var receivableMoneyRptcityCompanyIdQuery = "";//城市公司查询条件
    var receivableMoneyRptcityProjectDataQuery = "";//项目查询条件
    var receivableMoneyRptDatepickeryskbbQuery = "";//日期
    
    var incomeBillRptcityCompanyIdQuery = "";//城市公司查询条件
    var incomeBillRptcityProjectDataQuery = "";//项目查询条件
    var incomeBillRptDatepickersrjzQuery = "";//日期
    
    var invoiceBillRptcityCompanyIdQuery = "";//城市公司查询条件
    var invoiceBillRptcityProjectDataQuery = "";//项目查询条件
    var invoiceBillRptDatepickerkpqkQuery = "";//日期
    var projectDataList = [];//项目数据集
    
    $(function(){
    	//查询诚意金台账报表
    	queryEarnestMoneyReportList(1);
    	
        //查询城市公司
        queryCityCompany();
        //查询项目
        queryProjectData();
    }); 
    
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
    
    //查询城市公司
    function queryCityCompany() {
 	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"所有城市公司",tableName:"city_company"},function(result){
 			addSelectData(result, "earnestMoneyRptcityCompanyIdQuery");
 			addSelectData(result, "moneyPayRptcityCompanyIdQuery");
 			addSelectData(result, "receivableMoneyRptcityCompanyIdQuery");
 			addSelectData(result, "incomeBillRptcityCompanyIdQuery");
 			addSelectData(result, "invoiceBillRptcityCompanyIdQuery");
 			html =[];
			$.each(result,function(){
				if (this.id) {
				    html.push('<option value="'+this.id+'">'+this.name+'</option>');
				}
			});
 		},'json');
    }
    
    //一：查询诚意金台账报表按钮单击
    function queryEarnestMoneyReportListBtn(pageNo) {
    	earnestMoneyRptcityCompanyIdQuery = $("#earnestMoneyRptcityCompanyIdQuery").val();
    	earnestMoneyRptcityProjectDataQuery = $("#earnestMoneyRptcityProjectDataQuery").val();
    	earnestMoneyRptDatepickerstartQuery = $("#datepickerstart").val();
    	//alert(earnestMoneyRptcityCompanyIdQuery);
    	//alert(earnestMoneyRptcityProjectDataQuery);
    	queryEarnestMoneyReportList(pageNo);
    }
    
    //二：查询收款日报表按钮单击
    function queryMoneyPayReportListBtn(pageNo) {
    	moneyPayRptcityCompanyIdQuery = $("#moneyPayRptcityCompanyIdQuery").val();
    	moneyPayRptcityProjectDataQuery = $("#moneyPayRptcityProjectDataQuery").val();
    	moneyPayRptDatepickerreportQuery = $("#datepickerreport").val();
    	//alert(earnestMoneyRptcityCompanyIdQuery);
    	//alert(earnestMoneyRptcityProjectDataQuery);
    	queryMoneyPayReportList(pageNo);
    }
    
    //三：查询查询应收款报表按钮单击
    function queryReceivableMoneyReportListBtn(pageNo) {
    	receivableMoneyRptcityCompanyIdQuery = $("#receivableMoneyRptcityCompanyIdQuery").val();
    	receivableMoneyRptcityProjectDataQuery = $("#receivableMoneyRptcityProjectDataQuery").val();
    	receivableMoneyRptDatepickeryskbbQuery = $("#datepickeryskbb").val();
    	//alert(earnestMoneyRptcityCompanyIdQuery);
    	//alert(earnestMoneyRptcityProjectDataQuery);
    	queryReceivableMoneyReportList(pageNo);
    }
    
    //四：查询收入结账报表按钮单击
    function queryIncomeBillReportListBtn(pageNo) {
    	incomeBillRptcityCompanyIdQuery = $("#incomeBillRptcityCompanyIdQuery").val();
    	incomeBillRptcityProjectDataQuery = $("#incomeBillRptcityProjectDataQuery").val();
    	incomeBillRptDatepickersrjzQuery = $("#datepickersrjz").val();
    	//alert(earnestMoneyRptcityCompanyIdQuery);
    	//alert(earnestMoneyRptcityProjectDataQuery);
    	queryIncomeBillReportList(pageNo);
    }
    
    //五：查询开票情况报表按钮单击
    function queryInvoiceBillReportListBtn(pageNo) {
    	earnestMoneyRptcityCompanyIdQuery = $("#earnestMoneyRptcityCompanyIdQuery").val();
    	earnestMoneyRptcityProjectDataQuery = $("#earnestMoneyRptcityProjectDataQuery").val();
    	invoiceBillRptDatepickerkpqkQuery = $("#datepickerkpqk").val();
    	//alert(earnestMoneyRptcityCompanyIdQuery);
    	//alert(earnestMoneyRptcityProjectDataQuery);
    	queryInvoiceBillReportList(pageNo);
    }
    
    //查询项目
    function queryProjectData(id, t) {
       var whereStr = "";
       if (id != "" && id != null && id != "null" && id != undefined) {
    	   whereStr = " where city_company_id = " + id;
       }
  	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{whereStr:whereStr,allName:"所有项目",tableName:"project"},function(result){
  		    if (t) {
  		    	addSelectData(result, t);
  		    } else {
	  			addSelectData(result, "earnestMoneyRptcityProjectDataQuery");
	  			addSelectData(result, "moneyPayRptcityProjectDataQuery");
	  			addSelectData(result, "receivableMoneyRptcityProjectDataQuery");
	  			addSelectData(result, "incomeBillRptcityProjectDataQuery");
	  			addSelectData(result, "invoiceBillRptcityProjectDataQuery");
  		    }
  		},'json');
     }
    
    //查询项目公司
    function queryProjectCompanyData() {
  	   $.post(so.getRootPath() + '/housingManagement/selectcDimData.shtml',{allName:"",tableName:"project_company"},function(result){
  			addSelectData(result, "project_company_id");
  		},'json');
     }
     
   //一：查询诚意金台账报表
   function queryEarnestMoneyReportList(pageNo) {
	   //alert("qq["+$("#datepickerimessage").val()+"]No["+pageNo+"]");
	   $.post(so.getRootPath() + '/salesReportManagement/earnestMoneyReportList.shtml',{cityCompanyIdQuery:earnestMoneyRptcityCompanyIdQuery,projectDataQuery:earnestMoneyRptcityProjectDataQuery,dateQuery:earnestMoneyRptDatepickerstartQuery,pageNo:pageNo,userId:$("#currentUserId").val()},function(result){
		    //alert(result.totalCount);
		   //result.list=[];
		   $("#earnestMoneyRptListPageDiv").html(result.finacePageHtml);
		   if (result.list && result.list.length > 0) {
			   projectDataList = result.list;
			   var html = [];
			   $.each(result.list,function(idx){
					//html.push('<option value="'+this.id+'">'+this.name+'</option>');
				   //alert(this.id+"<>"+this.name);
				   html.push("<tr>");
				   html.push("<td class='tc'>"+(null == this.project_name ? "" : this.project_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.erp_name ? "" : this.erp_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.pay_time ? "" : this.pay_time)+"</td>");
				   html.push("<td class='tc'>"+(null == this.customer_name ? "" : this.customer_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.code ? "" : this.code)+"</td>");
				   html.push("<td class='tc'>"+(null == this.pay_amount ? "" : this.pay_amount)+"</td>");
				   html.push("<td class='tc'>"+(null == this.convert_house_amount ? "" : this.convert_house_amount)+"</td>");
				   html.push("<td class='tc'>"+(null == this.refund_time ? "" : this.refund_time)+"</td>");
				   html.push("<td class='tc'>"+(null == this.remain_amount ? "" : this.remain_amount)+"</td>");
				   html.push("</tr>");
			   });
			   $("#earnestMoneyRptListTable").html(html.join(""));
			   $('.infomessage').click(function(e){
			       $("#icon_group_list1 i.icon-chevron-up").click();
				   if($("#icon_group_list2 i.icon-chevron-down").length>0){
				       $("#icon_group_list2").click();
				  	   $("#icon_group_list1 i.icon-chevron-up").click();
			       }
			   });
		   } else {
			   $("#earnestMoneyRptListTable").html("<tr><td colspan='9' align='center'>暂无数据！</td></tr>");
		   }
		},'json');
   }
   
   //查询房源关系
   function queryReportDataByTab(t) {
	   if (t == 2) {
		   if (!queryMoneyPayReportListFlag) {
		       //查询收款日报表
			   queryMoneyPayReportList(1);
			   queryMoneyPayReportListFlag = true;
		   }
	   } else if (t == 3) {
		   if (!queryReceivableMoneyReportListFlag) {
		       //查询应收款报表
			   queryReceivableMoneyReportList(1);
			   queryReceivableMoneyReportListFlag = true;
		   }
	   } else if (t == 4) {
		   if (!queryIncomeBillReportListFlag) {
		       //查询收入结账报表
			   queryIncomeBillReportList(1);
			   queryIncomeBillReportListFlag = true;
		   }
	   } else if (t == 5) {
		   if (!queryInvoiceBillReportListFlag) {
		       //查询开票情况报表
			   queryInvoiceBillReportList(1);
		 	   queryInvoiceBillReportListFlag = true;
		   }
	   }
   }
   
   //二：查询收款日报表
   function queryMoneyPayReportList(pageNo) {
	   //alert("qq["+$("#datepickerimessage").val()+"]No["+pageNo+"]");
	   $.post(so.getRootPath() + '/salesReportManagement/moneyPayReportList.shtml',{cityCompanyIdQuery:moneyPayRptcityCompanyIdQuery,projectDataQuery:moneyPayRptcityProjectDataQuery,dateQuery:moneyPayRptDatepickerreportQuery,pageNo:pageNo,userId:$("#currentUserId").val()},function(result){
		    //alert(result.totalCount);
		   //result.list=[];
		   $("#moneyPayRptListPageDiv").html(result.finacePageHtml);
		   if (result.list && result.list.length > 0) {
			   projectDataList = result.list;
			   var html = [];
			   $.each(result.list,function(idx){
					//html.push('<option value="'+this.id+'">'+this.name+'</option>');
				   //alert(this.id+"<>"+this.name);
				   html.push("<tr>");
				   html.push("<td class='tc'>"+(null == this.pay_time ? "" : this.pay_time)+"</td>");
				   html.push("<td class='tc'>"+(null == this.project_name ? "" : this.project_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.building_name ? "" : this.building_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.erp_name ? "" : this.erp_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.receipt_name ? "" : this.receipt_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.money_type_name ? "" : this.money_type_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.pay_type ? "" : this.pay_type)+"</td>");
				   html.push("<td class='tc'>"+(null == this.pay_amount ? "" : this.pay_amount)+"</td>");
				   html.push("<td class='tc'>"+(null == this.pay_amount ? "" : this.pay_amount)+"</td>");
				   html.push("</tr>");
			   });
			   $("#moneyPayRptListTable").html(html.join(""));
			   $('.infomessage').click(function(e){
			       $("#icon_group_list1 i.icon-chevron-up").click();
				   if($("#icon_group_list2 i.icon-chevron-down").length>0){
				       $("#icon_group_list2").click();
				  	   $("#icon_group_list1 i.icon-chevron-up").click();
			       }
			   });
		   } else {
			   $("#moneyPayRptListTable").html("<tr><td colspan='9' align='center'>暂无数据！</td></tr>");
		   }
		},'json');
   }
   
   //三：查询应收款报表
   function queryReceivableMoneyReportList(pageNo) {
	   //alert("qq["+$("#datepickerimessage").val()+"]No["+pageNo+"]");
	   $.post(so.getRootPath() + '/salesReportManagement/receivableMoneyReportList.shtml',{cityCompanyIdQuery:receivableMoneyRptcityCompanyIdQuery,projectDataQuery:receivableMoneyRptcityProjectDataQuery,dateQuery:receivableMoneyRptDatepickeryskbbQuery,pageNo:pageNo,userId:$("#currentUserId").val()},function(result){
		    //alert(result.totalCount);
		   //result.list=[];
		   $("#receivableMoneyRptListPageDiv").html(result.finacePageHtml);
		   if (result.list && result.list.length > 0) {
			   projectDataList = result.list;
			   var html = [];
			   $.each(result.list,function(idx){
					//html.push('<option value="'+this.id+'">'+this.name+'</option>');
				   //alert(this.id+"<>"+this.name);
				   html.push("<tr>");
				   html.push("<td class='tc'>"+(null == this.project_name ? "" : this.project_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.erp_name ? "" : this.erp_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.customer_name ? "" : this.customer_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.receivables_time ? "" : this.receivables_time)+"</td>");
				   html.push("<td class='tc'>"+(null == this.money_type_name ? "" : this.money_type_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.total_price ? "" : this.total_price)+"</td>");
				   html.push("<td class='tc'>"+(null == this.pay_amount ? "" : this.pay_amount)+"</td>");
				   html.push("<td class='tc'>"+(null == this.amount ? "" : this.amount)+"</td>");
				   html.push("<td class='tc'>"+(null == this.overdue_reason_id ? "" : this.overdue_reason_id)+"</td>");
				   html.push("<td class='tc'>"+(null == this.is_recover_available ? "" : this.is_recover_available)+"</td>");
				   html.push("<td class='tc'>"+(null == this.expect_recover_date ? "" : this.expect_recover_date)+"</td>");
				   html.push("<td class='tc'>"+(null == this.overdue_extend_remark ? "" : this.overdue_extend_remark)+"</td>");
				   html.push("<td class='tc'>"+(null == this.overdue_time ? "" : this.overdue_time)+"</td>");
				   html.push("</tr>");
			   });
			   $("#receivableMoneyRptListTable").html(html.join(""));
			   $('.infomessage').click(function(e){
			       $("#icon_group_list1 i.icon-chevron-up").click();
				   if($("#icon_group_list2 i.icon-chevron-down").length>0){
				       $("#icon_group_list2").click();
				  	   $("#icon_group_list1 i.icon-chevron-up").click();
			       }
			   });
		   } else {
			   $("#receivableMoneyRptListTable").html("<tr><td colspan='13' align='center'>暂无数据！</td></tr>");
		   }
		},'json');
   }
   
   //四：查询收入结账报表
   function queryIncomeBillReportList(pageNo) {
	   //alert("qq["+$("#datepickerimessage").val()+"]No["+pageNo+"]");
	   $.post(so.getRootPath() + '/salesReportManagement/incomeBillReportList.shtml',{cityCompanyIdQuery:incomeBillRptcityCompanyIdQuery,projectDataQuery:incomeBillRptcityProjectDataQuery,dateQuery:incomeBillRptDatepickersrjzQuery,pageNo:pageNo,userId:$("#currentUserId").val()},function(result){
		    //alert(result.totalCount);
		   //result.list=[];
		   $("#incomeBillRptListPageDiv").html(result.finacePageHtml);
		   if (result.list && result.list.length > 0) {
			   projectDataList = result.list;
			   var html = [];
			   $.each(result.list,function(idx){
					//html.push('<option value="'+this.id+'">'+this.name+'</option>');
				   //alert(this.id+"<>"+this.name);
				   html.push("<tr>");
				   html.push("<td class='tc'>"+(null == this.project_name ? "" : this.project_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.erp_name ? "" : this.erp_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.customer_name ? "" : this.customer_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.real_size ? "" : this.real_size)+"</td>");
				   html.push("<td class='tc'>"+(null == this.unit_price ? "" : this.unit_price)+"</td>");
				   html.push("<td class='tc'>"+(null == this.total_price ? "" : this.total_price)+"</td>");
				   html.push("<td class='tc'>"+(null == this.deliver_house_invoice_code ? "" : this.deliver_house_invoice_code)+"</td>");
				   html.push("<td class='tc'>"+(null == this.is_paid_added_tax ? "" : this.is_paid_added_tax)+"</td>");
				   html.push("<td class='tc'>"+(null == this.carry_forward_cost_unit_price ? "" : this.carry_forward_cost_unit_price)+"</td>");
				   html.push("</tr>");
			   });
			   $("#incomeBillRptListTable").html(html.join(""));
			   $('.infomessage').click(function(e){
			       $("#icon_group_list1 i.icon-chevron-up").click();
				   if($("#icon_group_list2 i.icon-chevron-down").length>0){
				       $("#icon_group_list2").click();
				  	   $("#icon_group_list1 i.icon-chevron-up").click();
			       }
			   });
		   } else {
			   $("#incomeBillRptListTable").html("<tr><td colspan='9' align='center'>暂无数据！</td></tr>");
		   }
		},'json');
   }
   
   //五：查询开票情况报表
   function queryInvoiceBillReportList(pageNo) {
	   //alert("qq["+$("#datepickerimessage").val()+"]No["+pageNo+"]");
	   $.post(so.getRootPath() + '/salesReportManagement/invoiceBillReportList.shtml',{cityCompanyIdQuery:invoiceBillRptcityCompanyIdQuery,projectDataQuery:invoiceBillRptcityProjectDataQuery,dateQuery:invoiceBillRptDatepickerkpqkQuery,pageNo:pageNo,userId:$("#currentUserId").val()},function(result){
		    //alert(result.totalCount);
		   //result.list=[];
		   $("#invoiceBillRptListPageDiv").html(result.finacePageHtml);
		   if (result.list && result.list.length > 0) {
			   projectDataList = result.list;
			   var html = [];
			   $.each(result.list,function(idx){
					//html.push('<option value="'+this.id+'">'+this.name+'</option>');
				   //alert(this.id+"<>"+this.name);
				   html.push("<tr>");
				   html.push("<td class='tc'>"+(null == this.project_name ? "" : this.project_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.erp_name ? "" : this.erp_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.invoice_time ? "" : this.invoice_time)+"</td>");
				   html.push("<td class='tc'>"+(null == this.type ? "" : this.type)+"</td>");
				   html.push("<td class='tc'>"+(null == this.code ? "" : this.code)+"</td>");
				   html.push("<td class='tc'>"+(null == this.pay_amount ? "" : this.pay_amount)+"</td>");
				   html.push("<td class='tc'>"+(null == this.money_type_name ? "" : this.money_type_name)+"</td>");
				   html.push("<td class='tc'>"+(null == this.is_paid_added_tax ? "" : this.is_paid_added_tax)+"</td>");
				   html.push("<td class='tc'>"+(null == this.isvalid ? "" : this.isvalid)+"</td>");
				   html.push("</tr>");
			   });
			   $("#invoiceBillRptListTable").html(html.join(""));
			   $('.infomessage').click(function(e){
			       $("#icon_group_list1 i.icon-chevron-up").click();
				   if($("#icon_group_list2 i.icon-chevron-down").length>0){
				       $("#icon_group_list2").click();
				  	   $("#icon_group_list1 i.icon-chevron-up").click();
			       }
			   });
		   } else {
			   $("#invoiceBillRptListTable").html("<tr><td colspan='9' align='center'>暂无数据！</td></tr>");
		   }
		},'json');
   }
   
   
   
   //导出报表数据
   function exportReportData(t) {
 	   //alert("导出["+t+"]");
 	   /*$("#userIdExportQuery").val($("#currentUserId").val());
 	   $("#cityCompanyIdExportQuery").val(cityCompanyIdQuery);
 	   $("#projectNameExportQuery").val(projectNameQuery);
 	   $("#projectPhaseExportQuery").val(projectPhaseQuery);
 	   $("#houseTypeIdExportQuery").val(houseTypeIdQuery);
 	   $("#houseBusinessTypeIdExportQuery").val(houseBusinessTypeIdQuery);
 	   $("#houseConsultantExportQuery").val(houseConsultantQuery);
 	   $("#customerNameExportQuery").val(customerNameQuery);
 	   $("#erpNameExportQuery").val(erpNameQuery);
 	   $("#isLargeAmountExportQuery").val(isLargeAmountQuery);*/
 	   
 	   var fileName =  "计费规则" + getNowFormatDate();
 	   
 	   //var load = layer.load();
 	   var url = so.getRootPath() + "/salesReportManagement/exportReportData.shtml";
 	   $.fileDownload(url,{
 	       //data:$("#exportHouseDataForm").serialize(),
 		   data:{userId:$("#currentUserId").val(), reportType:t,
 			  cityCompanyIdQuery:7
 			   /*cityCompanyIdQuery:cityCompanyIdQuery,projectNameQuery:encodeURIComponent(projectNameQuery),
 			   projectPhaseQuery:encodeURIComponent(projectPhaseQuery), houseTypeIdQuery:houseTypeIdQuery,
 			   houseBusinessTypeIdQuery:houseBusinessTypeIdQuery, houseConsultantQuery:encodeURIComponent(houseConsultantQuery),
 			   customerNameQuery:encodeURIComponent(customerNameQuery), erpNameQuery:encodeURIComponent(erpNameQuery), 
 			   isLargeAmountQuery:isLargeAmountQuery*/
 		   },
 	       successCallback: function (url) {
 		       layer.close(load);
 	       },
 	       failCallback: function (html, url) {
 	           layer.close(load);
 	       }
       });
   }
   
    //获取当前时间
	function getNowFormatDate() {
		var day = new Date();
		var Year = 0;
		var Month = 0;
		var Day = 0;
		var CurrentDate = "";
		// 初始化时间
		// Year = day.getYear();//有火狐下2008年显示108的bug
		Year = day.getFullYear();// ie火狐下都可以
		Month = day.getMonth() + 1;
		Day = day.getDate();
		CurrentDate += Year + "-";
		if (Month >= 10) {
			CurrentDate += Month + "-";
		} else {
			CurrentDate += "0" + Month + "-";
		}
		if (Day >= 10) {
			CurrentDate += Day;
		} else {
			CurrentDate += "0" + Day;
		}
	
		return CurrentDate;
	}

   	 	