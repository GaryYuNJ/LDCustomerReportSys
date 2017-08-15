    var cityCurrentDateQuery = "", cityBranchCompanyQuery = "", citySubCompanyQuery = "", statusQuery = "", smsRandomCode = "";
    var areaCurrentDateQuery = "", areaBranchCompanyQuery = "", areaSubBranchCompanyQuery = "";
    $(function(){
    	//查询分公司所属区域
    	//queryDistrict();
    	
	    //查询子公司
	    querySubCompany("");
	    
	    //查询分公司
	    queryBranchCompany("");
 
    	//查询城市公司资金报表
    	querycityFinanceReport();
    	//查询区域公司资金报表
    	queryareaFinanceReport();
    	
    	var curDate = getNowFormatDate();
    	//查询框赋值
    	$("#datepicker").val(curDate);
    	$("#datepickerpk").val(curDate);
    	$("#datepickerimessage").val(curDate);
    	
    	//默认查询短信内容
    	querySmsRecord('init');
    }); 
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

   //查询子公司
   function querySubCompany(type) {
	   //alert("ccc["+id+"]");
	   $.post(so.getRootPath() + '/samedayFinance/selectSubCompany.shtml',{type:type,flag:"all",userId:$("#currentUserId").val()},function(result){
			if(result && result.length){
				//alert("子公司-->"+result.length);
				var html =[];
				$.each(result,function(){
					html.push('<option value="'+this.id+'">'+this.name+'</option>');
				});
				/*$("#subCompanySel").html(html.join(""));
				subCompanyId = result[0].id;
				queryDate = getNowFormatDate();*/
				$("#citySubCompanySel").html(html.join(""));
			}
		},'json');
   }
   
   //查询分公司
   function queryBranchCompany(type) {
	   /*alert("查询分公司");
	   alert(id);
	   alert(type);*/
	   $.post(so.getRootPath() + '/samedayFinance/selectcBranchCompany.shtml',{type:type,flag:"all",userId:$("#currentUserId").val()},function(result){
			if(result && result.length){
				//alert("分公司-->"+result.length);
				var html =[];
				$.each(result,function(){
					html.push('<option value="'+this.id+'">'+this.name+'</option>');
				});
				$("#areaSubCompanySel").html(html.join(""));
				/*if (type != "area") {
				    $("#cityBranchCompanySel").html(html.join(""));
				}*/
				//subCompanyId = result[0].id;
				//queryDate = getNowFormatDate();
				
				//查询子公司
				//querySubCompany(result[0].id, "");
			}
		},'json');
   }  
   
   //查询分公司所属区域
   function queryDistrict() {
	   $.post(so.getRootPath() + '/samedayFinance/selectcDistrict.shtml',{id:1,flag:"all"},function(result){
			if(result && result.length){
				//alert("分公司所属区域-->"+result.length);
				var html =[];
				$.each(result,function(){
					html.push('<option value="'+this.id+'">'+this.name+'</option>');
				});
				//$("#areaBranchCompanySel").html(html.join(""));
				//查询分公司
				//queryBranchCompany(result[0].id);
			}
		},'json');
   }  
 
   //查询短信内容
   function querySmsRecord(type) {
	   //alert($("button[id*='saveSmsRecordBtn'").length);
	   if (parseInt($("#datepickerimessage").val().replace("-", "").replace("-", "")) < parseInt(getNowFormatDate().replace("-", "").replace("-", "")) ) {
		   $("button[id^='saveSmsRecordBtn'").hide();
		   $("button[id^='resetContentBtn'").hide();
		   $("#smsSendBtn").hide();
		   
		   $("#selectCapitalBalanceTemplate").attr("disabled", true).attr("contenteditable", false);
	       $("#selectInvalidCapitalTDiffTemplate").attr("disabled", true).attr("contenteditable", false);
	       $("#selectIncomeDetailTemplate").attr("disabled", true).attr("contenteditable", false);
	       $("#selectCostDetailTemplate").attr("disabled", true).attr("contenteditable", false);
	       $("#selectWorkArrivedBalanceTemplate").attr("disabled", true).attr("contenteditable", false);
	       $("#selectJointVentureTemplate").attr("disabled", true).attr("contenteditable", false);
	   } else {
		   $("button[id^='saveSmsRecordBtn'").show();
		   $("button[id^='resetContentBtn'").show();
		   $("#smsSendBtn").show();
		   
		   $("#selectCapitalBalanceTemplate").attr("disabled", false).attr("contenteditable", true);
	       $("#selectInvalidCapitalTDiffTemplate").attr("disabled", false).attr("contenteditable", true);
	       $("#selectIncomeDetailTemplate").attr("disabled", false).attr("contenteditable", true);
	       $("#selectCostDetailTemplate").attr("disabled", false).attr("contenteditable", true);
	       $("#selectWorkArrivedBalanceTemplate").attr("disabled", false).attr("contenteditable", true);
	       $("#selectJointVentureTemplate").attr("disabled", false).attr("contenteditable", true);
	   }
	   $.post(so.getRootPath() + '/samedayFinance/selectSmsRecord.shtml',{date:$("#datepickerimessage").val()},function(result){
		    //alert(result[0].content1);
		    if (result.length > 0) {
		    	$("#smsId").val(result[0].id);
			    $("#smsContent").val(result[0].content);
			    if (result[0].content1) {
			    	$("#selectCapitalBalanceTemplate").html(result[0].content1);
			    }
			    if (result[0].content2) {
			    	$("#selectInvalidCapitalTDiffTemplate").html(result[0].content2);
			    }
			    if (result[0].content3) {
			    	$("#selectIncomeDetailTemplate").html(result[0].content3);
			    }
			    if (result[0].content4) {
			    	$("#selectCostDetailTemplate").html(result[0].content4);
			    }
			    if (result[0].content5) {
			    	$("#selectWorkArrivedBalanceTemplate").html(result[0].content5);
			    }
			    if (result[0].content6) {
			    	$("#selectJointVentureTemplate").html(result[0].content6);
			    }
		    } else {
		    	if (type == "init") {
		    		resetSmsContent("selectCapitalBalanceTemplate");
		    		resetSmsContent("selectInvalidCapitalTDiffTemplate");
		    		resetSmsContent("selectIncomeDetailTemplate");
		    		resetSmsContent("selectCostDetailTemplate");
		    		resetSmsContent("selectWorkArrivedBalanceTemplate");
		    		resetSmsContent("selectJointVentureTemplate");
		    	} else {
			    	$("#smsId").val("");
			    	$("#selectCapitalBalanceTemplate").html("无短信记录！");
			    	$("#selectInvalidCapitalTDiffTemplate").html("无短信记录！");
			    	$("#selectIncomeDetailTemplate").html("无短信记录！");
			    	$("#selectCostDetailTemplate").html("无短信记录！");
			    	$("#selectWorkArrivedBalanceTemplate").html("无短信记录！");
			    	$("#selectJointVentureTemplate").html("无短信记录！");
		    	}
		    }
		},'json');
   }
   
   //发送短信
   function sendSmsRecord() {
	   //alert(so.getRootPath()+"/common/samedayFinanceSms.jsp?smsRandomCode="+smsRandomCode);
	   //alert($("#currentUserMobile").val());
	   //return;
	   /*if (!so.checkPhone($("#currentUserMobile").val())) {
		   layer.msg("请先绑定手机号！",function(){});
		   return;
	   }*/
	   if (smsRandomCode == "") {
		   layer.msg("请先提交短信内容！",function(){});
		   return;
	   }
	   var smsUrl = "【绿地集团】"+getNowFormatDate()+"日江苏事业部资金情况，请访问："+
	                so.getRootPath()+"/common/samedayFinanceSms.jsp?code="+smsRandomCode;
	   //alert(smsUrl);
	   //return;
	   var param = {};
	   var paramObj = {"content":smsUrl,"receiver":$("#currentUserMobile").val()}
	   param["params"] = JSON.stringify(paramObj);
	   $.post(so.getRootPath() + '/samedayFinance/sendSmsRecord.shtml',param,function(result){
		   if (result.status == "200") {
			   $("#smsId").val(result.id);
			   layer.msg("短信发送成功！",function(){});
	       } else {
	       	   layer.msg("短信发送失败，请稍后再试！",function(){});
	       }
	   },'json');
   }
   
   //重新生成
   function resetSmsContent(t) {
	   //alert(t);
	   $.post(so.getRootPath() + '/samedayFinance/'+t+'.shtml',{},function(result){
		   $("#"+t).html(result.html);
	   },'json');
   }
   
   //新增短信记录
   function addSmsRecord(field, id) {
	   //alert("add["+field+"]["+$("#"+id).html()+"]");
	   //请求参数
	   var smsId = $("#smsId").val();
	   var smsContent = $("#"+id).html();
	   if (smsContent == "") {
		   layer.msg("请输入短信内容！",function(){});
		   return;
	   }
	   var param = {};
	   var paramObj = {"id":smsId,"content":$("#"+id).html(),"fieldName":field}
	   param["params"] = JSON.stringify(paramObj);
	   $.post(so.getRootPath() + '/samedayFinance/insertSmsRecord.shtml',param,function(result){
		   if (result.status == "200") {
			   $("#smsId").val(result.id);
			   layer.msg("短信保存成功！",function(){});
			   smsRandomCode = result.code;
	       	} else {
	       		layer.msg("短信保存失败，请稍后再试！",function(){});
	       	}
	   },'json');
   }
   
   //修改短信记录
   /*function updateSmsRecord() {
	   //alert("update["+subCompanyId+"]");
	   //请求参数
	   var smsId = $("#smsId").val();
	   var smsContent = $("#smsContent").val();
	   var param = {};
	   var paramObj = {"id":smsId,"content":smsContent}
	   param["params"] = JSON.stringify(paramObj);
	   $.post(so.getRootPath() + '/samedayFinance/updateSmsRecord.shtml',param,function(result){
		   alert("res");
	   },'json');
   }*/
   
   //查询城市公司资金报表
   function querycityFinanceReportBtn(pageNo) {
	   cityCurrentDateQuery = $("#datepicker").val();
	   cityBranchCompanyQuery = $("#cityBranchCompanySel").val();
	   citySubCompanyQuery = $("#citySubCompanySel").val();
	   if ($("#citySubCompanySel").val() == "-1") {
		   citySubCompanyQuery = "";
	   }
	   
	   if ($("#isLargeAmountQuery").find("div[class*='switch-off']").length == 0) {
		   statusQuery = "1";
	   } else {
		   statusQuery = "";
	   }
	   
	   querycityFinanceReport(pageNo);
   }
   
   //查询城市公司资金报表
   function querycityFinanceReport(pageNo) {
	   //alert("qq["+$("#datepickerimessage").val()+"]No["+pageNo+"]");
	   $.post(so.getRootPath() + '/financeReport/cityFinanceReportList.shtml',{cityCurrentDateQuery:cityCurrentDateQuery,cityBranchCompanyQuery:cityBranchCompanyQuery,citySubCompanyQuery:citySubCompanyQuery,statusQuery:statusQuery,pageNo:pageNo,userId:$("#currentUserId").val()},function(result){
		    //alert(result.totalCount);
		   //result.list=[];
		   $("#cityFinanceReportPageDiv").html(result.finacePageHtml);
		   if (result.list.length > 0) {
			   var html = [];
			   $.each(result.list,function(){
					//html.push('<option value="'+this.id+'">'+this.name+'</option>');
				   //alert(this.id+"<>"+this.name);
				   html.push("<tr>");
				   html.push("<td>"+(null == this.name ? "-" : this.name)+"</td>");
				   html.push("<td>"+(null == this.lastdayAmount ? "-" : this.lastdayAmount)+"</td>");
				   html.push("<td>"+(null == this.todayAmount ? "-" : this.todayAmount)+"</td>");
				   html.push("<td>"+(null == this.incomeAmount ? "-" : this.incomeAmount)+"</td>");
				   html.push("<td>"+(null == this.costAmount ? "-" : this.costAmount)+"</td>");
				   html.push("<td>"+(null == this.invalidAmount ? "-" : this.invalidAmount)+"</td>");
				   html.push("<td>"+(null == this.availableAmount ? "-" : this.availableAmount)+"</td>");
				   html.push("</tr>");
			   });
			   $("#cityFinanceReportTable").html(html.join(""));
		   } else {
			   $("#cityFinanceReportTable").html("<tr><td colspan='7' align='center'>暂无数据！</td></tr>");
		   }
		},'json');
   }
   
   
   //查询区域公司资金报表
   function queryareaFinanceReportBtn(pageNo) {
	   areaCurrentDateQuery = $("#datepickerpk").val();
	   areaBranchCompanyQuery = $("#areaBranchCompanySel").val();
	   areaSubBranchCompanyQuery = $("#areaSubCompanySel").val();
	   if ($("#areaSubCompanySel").val() == "-1") {
		   areaSubBranchCompanyQuery = "";
	   }
	   queryareaFinanceReport(pageNo);
   }
   
   //查询区域公司资金报表
   function queryareaFinanceReport(pageNo) {
	   //alert("qq["+$("#datepickerimessage").val()+"]No["+pageNo+"]");
	   $.post(so.getRootPath() + '/financeReport/areaFinanceReportList.shtml',{areaCurrentDateQuery:areaCurrentDateQuery,areaBranchCompanyQuery:areaBranchCompanyQuery,areaSubBranchCompanyQuery:areaSubBranchCompanyQuery,pageNo:pageNo,userId:$("#currentUserId").val()},function(result){
		    //alert(result.totalCount);
		   //result.list=[];
		   $("#areaFinanceReportPageDiv").html(result.finacePageHtml);
		   if (result.list.length > 0) {
			   var html = [];
			   $.each(result.list,function(){
					//html.push('<option value="'+this.id+'">'+this.name+'</option>');
				   //alert(this.id+"<>"+this.name);
				   html.push("<tr>");
				   html.push("<td>"+(null == this.name ? "-" : this.name)+"</td>");
				   html.push("<td>"+(null == this.lastdayAmount ? "-" : this.lastdayAmount)+"</td>");
				   html.push("<td>"+(null == this.todayAmount ? "-" : this.todayAmount)+"</td>");
				   html.push("<td>"+(null == this.incomeAmount ? "-" : this.incomeAmount)+"</td>");
				   html.push("<td>"+(null == this.costAmount ? "-" : this.costAmount)+"</td>");
				   html.push("<td>"+(null == this.invalidAmount ? "-" : this.invalidAmount)+"</td>");
				   html.push("<td>"+(null == this.availableAmount ? "-" : this.availableAmount)+"</td>");
				   html.push("</tr>");
			   });
			   $("#areaFinanceReportTable").html(html.join(""));
		   } else {
			   $("#areaFinanceReportTable").html("<tr><td colspan='7' align='center'>暂无数据！</td></tr>");
		   }
		},'json');
   }
   
   //导出城市公司-余额报表
   function exportCityBalanceReportList() {
	   //alert("导出["+cityCurrentDateQuery+"]");
	   //alert(cityCurrentDateQuery);
	   $("#cityUserIdQuery").val($("#currentUserId").val());
	   $("#cityCurrentDateQuery").val(cityCurrentDateQuery);
	   $("#cityBranchCompanyQuery").val(cityBranchCompanyQuery);
	   $("#citySubCompanyQuery").val(citySubCompanyQuery);
	   //$("#exportCityBalanceReportListForm").submit();
	   
	   var load = layer.load();
	   var url = so.getRootPath() + "/financeReport/exportCityBalanceReportList.shtml";
	   $.fileDownload(url,{
	       data:$("#exportCityBalanceReportListForm").serialize(),
	       successCallback: function (url) {
		       layer.close(load);
	       },
	       failCallback: function (html, url) {
	           layer.close(load);
	       }
       });
   }
   
   //导出区域公司-余额报表
   function exportAreaBalanceReportList() {
	   //alert("导出["+cityCurrentDateQuery+"]");
	   //alert(cityCurrentDateQuery);
	   $("#areaUserIdQuery").val($("#currentUserId").val());
	   $("#areaCurrentDateQuery").val(areaCurrentDateQuery);
	   $("#areaBranchCompanyQuery").val(areaBranchCompanyQuery);
	   $("#areaSubBranchCompanyQuery").val(areaSubBranchCompanyQuery);
	   //$("#exportAreaBalanceReportListForm").submit();
	   
	   var load = layer.load();
	   var url = so.getRootPath() + "/financeReport/exportAreaBalanceReportList.shtml";
	   $.fileDownload(url,{
	       data:$("#exportAreaBalanceReportListForm").serialize(),
	       successCallback: function (url) {
		       layer.close(load);
	       },
	       failCallback: function (html, url) {
	           layer.close(load);
	       }
       });
   }
   
   //导出城市公司-资金报表
   function exportCityCapitalReportList() {
	   //alert("导出["+cityCurrentDateQuery+"]");
	   //alert(cityCurrentDateQuery);
	   $("#cityCapitalUserIdQuery").val($("#currentUserId").val());
	   $("#cityCapitalCurrentDateQuery").val(cityCurrentDateQuery);
	   $("#cityCapitalBranchCompanyQuery").val(cityBranchCompanyQuery);
	   $("#cityCapitalSubCompanyQuery").val(citySubCompanyQuery);
	   //$("#exportCityCapitalReportListForm").submit();
	   var load = layer.load();
	   var url = so.getRootPath() + "/financeReport/exportCityCapitalReportList.shtml";
	   $.fileDownload(url,{
	       data:$("#exportCityCapitalReportListForm").serialize(),
	       successCallback: function (url) {
		       layer.close(load);
	       },
	       failCallback: function (html, url) {
	           layer.close(load);
	       }
       });
   }
   
   //导出区域公司-资金报表
   function exportAreaCapitalReportList() {
	   //alert("导出["+cityCurrentDateQuery+"]");
	   //alert(cityCurrentDateQuery);
	   $("#areaCapitalUserIdQuery").val($("#currentUserId").val());
	   $("#areaCapitalCurrentDateQuery").val(areaCurrentDateQuery);
	   $("#areaCapitalBranchCompanyQuery").val(areaBranchCompanyQuery);
	   $("#areaCapitalSubCompanyQuery").val(areaSubBranchCompanyQuery);
	   //$("#exportAreaCapitalReportListForm").submit();
	   
	   var load = layer.load();
	   var url = so.getRootPath() + "/financeReport/exportAreaCapitalReportList.shtml";
	   $.fileDownload(url,{
	       data:$("#exportAreaCapitalReportListForm").serialize(),
	       successCallback: function (url) {
		       layer.close(load);
	       },
	       failCallback: function (html, url) {
	           layer.close(load);
	       }
       });
   }
   