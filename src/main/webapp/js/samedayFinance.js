    var firstCompanyDataList = [];    
    var viewFilePath = "";
    $(function(){
    	//获取查看文件路径
    	queryFilePath();
    });
    
    function initAjaxFileUpload(len) {
    	 $("#fileUploadHref_"+len).on("change","input[type='file']",function(){
    			//alert("ccc");
    			var index = $(this).attr("attr");
    		    var filePath=$(this).val();
    		    var fileType = "fileRow"+(parseInt(index)+1);
    		    if (index == "9") {
    		    	fileType = "fileRow9END";
    		    }
    		    if(isImgTypeShoreLine(filePath)) {
    		        var arr=filePath.split('\\');
    		        var fileName=arr[arr.length-1];
    		        uploaApplyFile('uploadFile_'+len, fileName, len);
    		    }else{
    		    	layer.msg("只支持JPG、GIF、BMP、PNG格式的文件！",function(){});
    		    }
    		})	
    }
    
    function uploaApplyFile(fileNameId, fileName, len){
    	//alert("ok")
    	$.ajaxFileUpload({
    		url: encodeURI(encodeURI(so.getRootPath() + "/financeReport/uploadApplyFile.shtml?picTypeId="+fileNameId)),// 用于文件上传的服务器端请求地址
    		secureuri : false, // 一般设置为false
    		fileElementId : fileNameId, // 文件上传空间的id属性
    		dataType : 'test', // 返回值类型,一般设置为json
    		success : function(data) // 服务器成功响应处理函数
    		{
    			if (data == "file2big")
    			{
    				layer.msg("图片不能大于100K！",function(){});
    				return false;
    			}
    			else
    			{
    				layer.msg("上传成功！",function(){});
    				//alert(data);
    				$("#showImg").attr("src", data);
    				$("#bankfile_"+len).val(data);
    				$("#viewBalanceImgBtn_"+len).attr("onclick", "viewFinancePic('"+data+"')");
    			}
    		}
        });
    }

    
    function isImgTypeShoreLine(filePath) 
    {	
    	var pathLen = filePath.length;
    	var lastStr = filePath.substr(pathLen-4).toLowerCase();
    	if(".png" == lastStr || ".gif" == lastStr || ".bmp" == lastStr
    	    || ".jpg" == lastStr || ".jpeg" == lastStr)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
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
	
   //获取查看文件路径
   function queryFilePath() {
	   $.post(so.getRootPath() + '/samedayFinance/selectViewFilePath.shtml',{},function(result){
			if(result){
				viewFilePath = result.name;
			}
		},'json');
   }
   
   //查询数据是否已上报
   function queryBalanceProcess() {
	   //alert("cb");
	   //return;
	   $.post(so.getRootPath() + '/samedayFinance/selectBalanceProcess.shtml',{subCompanyId:subCompanyId,status:1},function(result){
		    //alert("rrr");
			if(result && result.length > 0){
				setBalanceProcessBtn(1);
			} else {
				setBalanceProcessBtn(0);
			}
		},'json');  
   }
   
   //设置查询数据按钮
   function setBalanceProcessBtn(t) {
	    if (showType != "historyFinance") {
			if(t == 1){
			   //alert("yy");
			   $("#balanceRejectBtn").attr('disabled', false);
			   $("#balanceReportBtn").attr('disabled', true);
			   $("#home :input").attr('disabled', true);
			   $("#home :input[type='file']").parent().replaceWith("<button type='button' class='btn btn-primary ' disabled>上传截图</button>");
			   $("#menu1 :input").attr('disabled', true);
			   $("#menu2 :input").attr('disabled', true);
			   $("#menu3 :input").attr('disabled', true);
			   $("#gongdiAmount").attr('disabled', true);
			   $("button[name='subCompanyBtn']").attr('disabled', true);
			   //$("#subCompanySel").attr('disabled', true);
			} else {
			   //alert("nn");
			   $("#balanceRejectBtn").attr('disabled', true);
			   $("#balanceReportBtn").attr('disabled', false);
			   $("button[name='subCompanyBtn']").attr('disabled', false);
			   
			   $("#home :input").attr('disabled', false);
			   $("#menu1 :input").attr('disabled', false);
			   $("#menu2 :input").attr('disabled', false);
			   $("#menu3 :input").attr('disabled', false);
			   $("#gongdiAmount").attr('disabled', false);
			   //$("#subCompanySel").attr('disabled', true);
			}
	    }
   }

   //查询子公司
   function querySubComp() {
	   $.post(so.getRootPath() + '/samedayFinance/selectSubCompany.shtml',{userId:$("#currentUserId").val()},function(result){
			if(result && result.length){
				//alert("子公司-->"+result.length);
				var html =[];
				$.each(result,function(){
					html.push('<option value="'+this.id+'">'+this.name+'</option>');
				});
				$("#subCompanySel").html(html.join(""));
				subCompanyId = result[0].id;
				queryDate = getNowFormatDate();
			}
			
			subCompanyId = $("#subCompanySel").val();
			queryDate = $("#datepicker").val();
			
		    $.post(so.getRootPath() + '/samedayFinance/querySubCompanyAll.shtml',{id:subCompanyId,date:queryDate,userId:$("#currentUserId").val()},function(result){
		    	firstCompanyDataList = result;
		        //昨日总余额
			    querySubCompLastDayBalance(result.subCompLastDayBalanceList);
		        //银行余额
		        querySubCompBalance(result.subCompBalanceList);
		        
				//查询收入类型
			    queryIncomeType();
			    
			    if(result.subCompLastDayGongdiBalanceList && result.subCompLastDayGongdiBalanceList.length > 0){
			    	$("#lastdayGongdiBalance").val(result.subCompLastDayGongdiBalanceList[0].amount);
			    } else {
			    	$("#lastdayGongdiBalance").val("");
			    }
			    if(result) {
			    	$("#todayGongdiBalance").val(result.gongdiBalance == null || result.gongdiBalance == "0" ? "" : result.gongdiBalance);
			    	$("#gongdiAmount").val(result.gongdiBalance == null || result.gongdiBalance == "0" ? "" : result.gongdiBalance);
			    } else {
			    	$("#todayGongdiBalance").val("");
			    	$("#gongdiAmount").val("");
			    }
			},'json');
		    
		},'json');
   }
   
   //初始化子公司下面所有信息
   function querySubCompanyMsgFirst() {
       //alert("res["+result.subCompBalanceList.length+"]");
       //今日收入
       queryIncome(firstCompanyDataList.subCompIncomeList);
       //今日支出
       queryCost(firstCompanyDataList.subCompCostList);
       //不可用资金
       queryInvalidCapital(firstCompanyDataList.subCompInvalidCapitalList);
       
       //查询数据是否已上报
   	   queryBalanceProcess();
   }
   
    //查询子公司下的所有信息
	function querySubCompanyMsg() {
		subCompanyId = $("#subCompanySel").val();
		queryDate = $("#datepicker").val();
		//alert(queryDate);
		//查询子公司所有信息
		querySubCompanyAll();
		//查询子公司总余额
		//querySubCompTotalBalance();
		/*//查询子公司昨日总余额
		querySubCompLastDayBalance();
		//查询子公司银行余额
		querySubCompBalance();
		//查询子公司今日收入
		queryIncome();
		//查询子公司今日支出
		queryCost();
		//查询子公司不可用资金
		queryInvalidCapital();*/
	}
   
   //查询收入类型
   function queryIncomeType() {
	   $.post(so.getRootPath() + '/samedayFinance/selectIncomeType.shtml',{id:subCompanyId},function(result){
			if(result && result.length){
				//alert("子公司-->"+result.length);
				//$("#incomebox").text(result[0].name);
				var html =[];
				//html.push("<select class='form-control selectfont'>");
				$.each(result,function(){
					html.push('<option value="'+this.id+'">'+this.name+'</option>');
				});
				//html.push("</select>");
				incomeTypeListHtml = html.join("");
			}
			
			//查询子公司今日收入
			//queryIncome();
			//查询支出类型
			queryCostType();
		},'json');
   }
   
   //查询支出类型
   function queryCostType() {
	   $.post(so.getRootPath() + '/samedayFinance/selectCostType.shtml',{id:subCompanyId},function(result){
			if(result && result.length){
				//alert("子公司-->"+result.length);
				var html =[];
				$.each(result,function(){
					html.push('<option value="'+this.id+'">'+this.name+'</option>');
				});
				costTypeListHtml = html.join("");
			}
			
			//查询子公司今日支出
			//queryCost();
			
			//查询不可用资金类型
			queryInvalidCapitalType();
		},'json');
   }
 
   //查询不可用资金类型
   function queryInvalidCapitalType() {
	   $.post(so.getRootPath() + '/samedayFinance/selectInvalidCapitalType.shtml',{id:subCompanyId},function(result){
			if(result && result.length){
				//alert("子公司-->"+result.length);
				var html =[];
				$.each(result,function(){
					html.push('<option value="'+this.id+'">'+this.name+'</option>');
				});
				invalidCapitalTypeListHtml = html.join(""); 
			}
			
			//查询子公司不可用资金
			//queryInvalidCapital();
			
			//初始化子公司下面所有信息
			querySubCompanyMsgFirst();
		},'json');
   }
   
   //查询银行列表
   function queryBank() {
	   $.post(so.getRootPath() + '/samedayFinance/selectBank.shtml',{id:subCompanyId},function(result){
			if(result && result.length){
				//alert("子公司-->"+result.length);
				var html =[];
				$.each(result,function(){
					bankList.push(this.name);
				});
				//alert(bankList.length);
			}
		},'json');
   }
 
   //查询子公司所有信息
   function querySubCompanyAll() {
	   $.post(so.getRootPath() + '/samedayFinance/querySubCompanyAll.shtml',{id:subCompanyId,date:queryDate,userId:$("#currentUserId").val()},function(result){
	       //alert("res["+result.subCompBalanceList.length+"]");
	       //昨日总余额
		   querySubCompLastDayBalance(result.subCompLastDayBalanceList);
	       //银行余额
	       querySubCompBalance(result.subCompBalanceList);
	       //今日收入
	       queryIncome(result.subCompIncomeList);
	       //今日支出
	       queryCost(result.subCompCostList);
	       //不可用资金
	       queryInvalidCapital(result.subCompInvalidCapitalList);
	       
	       if(result.subCompLastDayGongdiBalanceList && result.subCompLastDayGongdiBalanceList.length > 0){
		    	$("#lastdayGongdiBalance").val(result.subCompLastDayGongdiBalanceList[0].amount);
		   } else {
		    	$("#lastdayGongdiBalance").val("");
		   }
		   if(result) {
		    	$("#todayGongdiBalance").val(result.gongdiBalance == null || result.gongdiBalance == "0" ? "" : result.gongdiBalance);
		    	$("#gongdiAmount").val(result.gongdiBalance == null || result.gongdiBalance == "0" ? "" : result.gongdiBalance);
		   } else {
		    	$("#todayGongdiBalance").val("");
		    	$("#gongdiAmount").val("");
		   }
		   
		   //查询数据是否已上报
	   	   queryBalanceProcess();
	   },'json');
   }
   
   //查询子公司总余额
   function querySubCompTotalBalance() {
	   $("#todayAmount").val("");
	   $("#lastdayAmount").val("");
	   $("#incomeAmount").val("");
	   $("#costAmount").val("");
	   $("#invalidAmount").val("");
	   $.post(so.getRootPath() + '/samedayFinance/selectSubCompTotalBalanceByCond.shtml',{id:subCompanyId,date:queryDate},function(result){
			//alert("res");
			//alert(result.todayAmount);
			$("#todayAmount").val(result.todayAmount);
			$("#lastdayAmount").val(result.lastdayAmount);
			$("#incomeAmount").val(result.incomeAmount);
			$("#costAmount").val(result.costAmount);
			$("#invalidAmount").val(result.invalidAmount);
		},'json');
   }
   
   //查询子公司昨日总余额
   function querySubCompLastDayBalance(result) {
	   //alert("zz["+subCompanyId+"]["+queryDate+"]");
	   $("#lastdayAmount").val("");
	   if(result && result.length > 0){
			//alert(result[0].amount);
			//昨日总余额（万元）
			$("#lastdayAmount").val(result[0].amount);
	   }
   }
   
   //查询子公司银行余额
   function querySubCompBalance(result) {
        $("#bankTab").empty();
	    $("#todayAmount").val("");
		if(result && result.length > 0){
			//alert(result.length);
			var html =[];
			var amount = 0;
			$.each(result,function(){
				amount += this.amount;
			    var _len = $("#bankTab tr").length;  
			    var uploadHtml = "";
                var deleteHtml = "";
                if (showType !="historyFinance") {
                	//uploadHtml = "<button type='button' class='btn btn-primary ' disabled>上传截图</button>";
                	uploadHtml = '<a id="fileUploadHref_'+_len+'" href="javascript:;" class="blue-btn op-btn a-upload fileUploadHref  "><i class="i-upload"   ></i>'
                                +'<input type="file" name="uploadFile_'+_len+'" id="uploadFile_'+_len+'" attr="1" title="上传截图" '+(showType=="historyFinance"?"disabled":"")+' >上传截图</a>';
                	
                	deleteHtml = "<button type='button' class='btn btn-danger' "+(showType=="historyFinance"?"disabled":"")+" onclick=\'deltr("+_len+")\'>删除</button>";
                }                  
	            $("#bankTab").append("<tr id="+_len+" align='center'>"
                         +"<td><div class='form-group form-groupnew'><input type='hidden' id='bankfile_"+_len+"' value='"+this.filepath+"' name='bankfile'/><input type='text' name='bankname' "+(showType=="historyFinance"?"readonly":"")+" class='form-control tcfont autocomplete' onmouseover='this.title=this.value'  value='"+this.name+"'/></div></td>"
                         +"<td><div class='form-group form-groupnew'><input type='text' name='balance'  "+(showType=="historyFinance"?"readonly":"")+"  class='form-control tcfont' onmouseover='this.title=this.value' value='"+this.amount+"'  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  /></div></td>"
                        	+"<td><div class='form-group form-groupnew'><input type='text' name='remarks'  "+(showType=="historyFinance"?"readonly":"")+"  class='form-control tcfont' onmouseover='this.title=this.value' value='"+this.descs+"' /></div></td>"
                         +"<td><form class='form-inline' role='form'   >"
                         +uploadHtml
                         +'<button id="viewBalanceImgBtn_'+_len+'" type="button" class="btn btn-primary"   onclick="viewFinancePic(\''+this.filepath+'\')">查看截图</button>'
                         +deleteHtml
                         +"</form></td>"
                     	+"</tr>");
	            if (showType != "historyFinance") {
		            $( ".autocomplete" ).autocomplete({
		            	source:bankList
		         	 });
		            initAjaxFileUpload(_len);
	            }
			});
			//今日总余额（万元）
			$("#todayAmount").val(amount);
		}
   }
   
   //查看截图
   function viewFinancePic(pic) {
	   //alert("v["+pic+"]");
	   //alert(viewFilePath + "/" + pic);
	   $("#showImg").attr("src", viewFilePath + pic);   
	   $("#myModalView").modal("show");
   } 
   
   //查询子公司今日收入
   function queryIncome(result) {
	    $("#incomeTab").empty();
	    $("#incomeAmount").val("");
		if(result && result.length > 0){
			var html =[];
			var amount = 0;
			$.each(result,function(){
			   amount += this.amount;
			   var _lenicome = $("#incomeTab tr").length;    
			   var deleteHtml = "";
			   if (showType !="historyFinance") {
                   deleteHtml = "<button type='button' "+(showType=="historyFinance"?"disabled":"")+" class='btn btn-danger'onclick=\'deltrincome("+_lenicome+")\'>删除</button>";
               }          
	           $("#incomeTab").append("<tr id="+_lenicome+" align='center'>"
				        		    +"<td><div class='form-group  form-groupnew'><select  "+(showType=="historyFinance"?"disabled":"")+"  id='incomeSel_"+_lenicome+"' class='form-control selectfont'  >"
			                        //+"<select class='form-control selectfont'><option>工抵</option><option>往来款</option><option>其他</option>"
			                        +incomeTypeListHtml
			                        +"</select></div></td>"
	        		                +"<td><div class='form-group form-groupnew'><input type='text'  "+(showType=="historyFinance"?"readonly":"")+"  name='incomeName' class='form-control tcfont' onmouseover='this.title=this.value'  value='"+this.name+"' /></div></td>"
	                   				+"<td><div class='form-group form-groupnew'><input type='text' "+(showType=="historyFinance"?"readonly":"")+"  name='balance' class='form-control tcfont' onmouseover='this.title=this.value'  value='"+this.amount+"'  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  /></div></td>"
	                  				+"<td><div class='form-group form-groupnew'><input type='text' "+(showType=="historyFinance"?"readonly":"")+"  name='remarks' class='form-control tcfont' onmouseover='this.title=this.value'  value='"+this.descs+"' /></div></td>"
	                  				+"<td><form class='form-inline' role='form'>"
	                  				+deleteHtml
	                                +"</form></td>"
	                            	+"</tr>");
			   $("#incomeSel_"+_lenicome).val(this.incomeTypeId);
			});
			//总收入（万元）
			$("#incomeAmount").val(amount);
		}
   }
   
   //查询子公司今日支出
   function queryCost(result) {
	   $("#defrayTab").empty();
	   $("#costAmount").val("");
	   if(result && result.length > 0){
			var html =[];
			var amount = 0;
			$.each(result,function(){
			   amount += this.amount;
			   var _lenicome = $("#defrayTab tr").length;    
			   var deleteHtml = "";
			   if (showType !="historyFinance") {
                   deleteHtml = "<button type='button' "+(showType=="historyFinance"?"disabled":"")+" class='btn btn-danger'onclick=\'deltrdefray("+_lenicome+")\'>删除</button>";
               }
	           $("#defrayTab").append("<tr id="+_lenicome+" align='center'>"
				        		    +"<td><div class='form-group  form-groupnew'><select  "+(showType=="historyFinance"?"disabled":"")+"  id='costSel_"+_lenicome+"' class='form-control selectfont'  >"
			                        //+"<select class='form-control selectfont'><option>工抵</option><option>往来款</option><option>其他</option>"
			                        +costTypeListHtml
			                        +"</select></div></td>"
	        		                +"<td><div class='form-group form-groupnew'><input type='text' "+(showType=="historyFinance"?"readonly":"")+"  name='costName' class='form-control tcfont' onmouseover='this.title=this.value'  value='"+this.name+"' /></div></td>"
	                   				+"<td><div class='form-group form-groupnew'><input type='text' "+(showType=="historyFinance"?"readonly":"")+"  name='balance' class='form-control tcfont' onmouseover='this.title=this.value'  value='"+this.amount+"' onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);'  /></div></td>"
	                  				+"<td><div class='form-group form-groupnew'><input type='text' "+(showType=="historyFinance"?"readonly":"")+"  name='remarks' class='form-control tcfont' onmouseover='this.title=this.value'  value='"+this.descs+"' /></div></td>"
	                  				+"<td><form class='form-inline' role='form'>"
	                  				+deleteHtml
	                                +"</form></td>"
	                            	+"</tr>");
			   $("#costSel_"+_lenicome).val(this.costTypeId);
			});
			//总支出（万元）
			$("#costAmount").val(amount);
		}
   }
   
   //查询子公司不可用资金
   function queryInvalidCapital(result) {
	    $("#unavailableTab").empty();
	    $("#invalidAmount").val("");
		if(result && result.length > 0){
			var html =[];
			var amount = 0;
			$.each(result,function(){
			   amount += this.amount;
			   var _lenunavailable = $("#unavailableTab tr").length;    
			   var deleteHtml = "";
			   if (showType !="historyFinance") {
                   deleteHtml = "<button type='button' "+(showType=="historyFinance"?"disabled":"")+" class='btn btn-danger'onclick=\'deltrunavailable("+_lenunavailable+")\'>删除</button>";
               }  
	           $("#unavailableTab").append("<tr id="+_lenunavailable+" align='center'>"
	        		   				+"<td><div class='form-group form-groupnew'><select "+(showType=="historyFinance"?"disabled":"")+"  id='invalidSel_"+_lenunavailable+"' class='form-control selectfont'  >"
	        		   				+invalidCapitalTypeListHtml
	        		   				+"</select></div></td>" 
	                   				+"<td><div class='form-group form-groupnew'><input type='text' "+(showType=="historyFinance"?"readonly":"")+"  name='balance' class='form-control tcfont' onmouseover='this.title=this.value'  value='"+this.amount+"'  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' /></div></td>"
	                   				+"<td><div class='form-group form-groupnew'><input type='text' "+(showType=="historyFinance"?"readonly":"")+"  name='changeAmount' class='form-control tcfont' onmouseover='this.title=this.value'  value='"+this.changeAmount+"'  onkeyup='so.clearToNum(this)' onafterpaste='so.clearToNum(this)' onblur='so.clearToNum(this);' /></div></td>"
	                  				+"<td><div class='form-group form-groupnew'><input type='text' "+(showType=="historyFinance"?"readonly":"")+"  name='remarks' class='form-control tcfont' onmouseover='this.title=this.value'  value='"+this.descs+"' /></div></td>" 
	                  				+"<td><form class='form-inline' role='form'>"
	                  				+deleteHtml
	                                +"</form></td>"
	                            	+"</tr>");
			   $("#invalidSel_"+_lenunavailable).val(this.invalidCapitalTypeId);
			});
			//总不可用资金（万元）
			$("#invalidAmount").val(amount);
		}
		//alert(toDayBalance);
		//alert(lastDayBalance + incomeBalance - costBalance);
		//余额是否匹配
		balanceDiff();
   }
   
   //余额是否匹配
   function balanceDiff() {
	   var todayAmountTemp = $("#todayAmount").val() == "" ? 0 : parseFloat($("#todayAmount").val());
	   var lastdayAmountTemp = $("#lastdayAmount").val() == "" ? 0 : parseFloat($("#lastdayAmount").val());
	   var incomeAmountTemp = $("#incomeAmount").val() == "" ? 0 : parseFloat($("#incomeAmount").val());
	   var costAmountTemp = $("#costAmount").val() == "" ? 0 : parseFloat($("#costAmount").val());
	    //余额是否匹配
		if (todayAmountTemp == (lastdayAmountTemp + incomeAmountTemp - costAmountTemp)) {
			//alert("a1")
			//if (showType == "historyFinance") {
		    $("#balanceDiffImg").attr("src", "../img/yes_grey_btn.png")	
			//} else {
				//$("#balanceDiffImg").attr("src", "../img/yes.png")
			//}
		} else {
			//alert("a2");
			//if (showType == "historyFinance") {
		    $("#balanceDiffImg").attr("src", "../img/no_grey_btn.png")
			//} else {
			    //$("#balanceDiffImg").attr("src", "../img/no.png")
			//}
		}
   }
   
   //保存子公司银行余额
   function insertSubCompBalance() {
	   //bankTab
	   //alert("save");
	   var balanceTable = $("#bankTab").find("tr");
	   var balanceArray = [];
	   var amount = 0;
	   $.each(balanceTable,function(i, o){
		   //alert($(this).find("input[name='bankfile']").val())
		   balanceArray[i] = {
			   "filepath":$(this).find("input[name='bankfile']").val(),
			   "descs":$(this).find("input[name='remarks']").val(),
		       "name":$(this).find("input[name='bankname']").val(),
		       "amount":$(this).find("input[name='balance']").val()
		   }
		   amount += parseFloat($(this).find("input[name='balance']").val());
	   });
	   $("#todayAmount").val(amount);
	   //alert(amount);
	   //alert(JSON.stringify(params));
	   //请求参数
	   var param = {};
	   var paramObj = {"companyId":subCompanyId,"list":balanceArray}
	   param["params"] = JSON.stringify(paramObj);
	   
	   var confirmWin = layer.confirm("是否确认保存？",function(){
		   $.post(so.getRootPath() + '/samedayFinance/insertSubCompBalance.shtml',param,function(result){
			   if (result.status == "200") {
				   layer.msg("保存成功！",function(){});
				   //余额是否匹配
				   balanceDiff();
				   layer.close(confirmWin);
			   } else {
				   layer.msg("保存失败，请确认信息是否填写完整，并稍后再试！",function(){});
			   }
		   },'json');
	   });
   }
   
   //保存子公司今日收入
   function insertIncome() {
	   var balanceTable = $("#incomeTab").find("tr");
	   var balanceArray = [];
	   var amount = 0;
	   $.each(balanceTable,function(i, o){
		   /*alert($(this).find("input[name='incomeName']").val()+"<>"+$(this).find("input[name='balance']").val()+
				   "<>"+$(this).find("input[name='remarks']").val()+"<>"+$(this).find("select").val());*/
		   balanceArray[i] = {
		       "name":$(this).find("input[name='incomeName']").val(),
		       "amount":$(this).find("input[name='balance']").val(),
		       "descs":$(this).find("input[name='remarks']").val(),
		       "incomeTypeId":$(this).find("select").val()
		   }
		   amount += parseFloat($(this).find("input[name='balance']").val());
	   });
	   $("#incomeAmount").val(amount);
	   //return;
	   //alert(JSON.stringify(params));
	   //请求参数
	   var param = {};
	   var paramObj = {"companyId":subCompanyId,"list":balanceArray}
	   param["params"] = JSON.stringify(paramObj);
	   
	   var confirmWin = layer.confirm("是否确认保存？",function(){
		   $.post(so.getRootPath() + '/samedayFinance/inertIncome.shtml',param,function(result){
			   if (result.status == "200") {
				   layer.msg("保存成功！",function(){});
				   //余额是否匹配
				   //balanceDiff();
				   querySubCompanyMsg();
	           } else {
	        		layer.msg("保存失败，请确认信息是否填写完整，并稍后再试！",function(){});
	           }
		   },'json');
		   layer.close(confirmWin);
	   });
   }
   
   //保存子公司今日支出
   function insertCost() {
	   var balanceTable = $("#defrayTab").find("tr");
	   var balanceArray = [];
	   var amount = 0;
	   $.each(balanceTable,function(i, o){
		   /*alert($(this).find("input[name='incomeName']").val()+"<>"+$(this).find("input[name='balance']").val()+
				   "<>"+$(this).find("input[name='remarks']").val()+"<>"+$(this).find("select").val());*/
		   balanceArray[i] = {
		       "name":$(this).find("input[name='costName']").val(),
		       "amount":$(this).find("input[name='balance']").val(),
		       "descs":$(this).find("input[name='remarks']").val(),
		       "costTypeId":$(this).find("select").val()
		   }
		   amount += parseFloat($(this).find("input[name='balance']").val());
	   });
	   $("#costAmount").val(amount);
	   //return;
	   //alert(JSON.stringify(params));
	   //请求参数
	   var param = {};
	   var paramObj = {"companyId":subCompanyId,"list":balanceArray}
	   param["params"] = JSON.stringify(paramObj);
	   
	   var confirmWin = layer.confirm("是否确认保存？",function(){
		   $.post(so.getRootPath() + '/samedayFinance/insertCost.shtml',param,function(result){
			   if (result.status == "200") {
				   layer.msg("保存成功！",function(){});
				   
				   //余额是否匹配
				   //balanceDiff();
				   querySubCompanyMsg();
	        	} else {
	        		layer.msg("保存失败，请确认信息是否填写完整，并稍后再试！",function(){});
	        	}
		   },'json');
		   layer.close(confirmWin);
	   });
   }
   
   //保存子公司不可用资金
   function insertInvalidCapital() {
	   //alert("save["+subCompanyId+"]");
	   var balanceTable = $("#unavailableTab").find("tr");
	   var balanceArray = [];
	   var amount = 0;
	   $.each(balanceTable,function(i, o){
		   /*alert($(this).find("input[name='balance']").val()+"<>"+$(this).find("input[name='remarks']").val()+
				   "<>"+$(this).find("select").val());*/
		   balanceArray[i] = {
		       "amount":$(this).find("input[name='balance']").val(),
		       "changeAmount":$(this).find("input[name='changeAmount']").val(),
		       "descs":$(this).find("input[name='remarks']").val(),
		       "invalidCapitalTypeId":$(this).find("select").val()
		   }
		   amount += parseFloat($(this).find("input[name='balance']").val());
	   });
	   $("#invalidAmount").val(amount);
	   //return;
	   //alert(JSON.stringify(params));
	   //请求参数
	   var param = {};
	   var paramObj = {"companyId":subCompanyId,"list":balanceArray}
	   param["params"] = JSON.stringify(paramObj);
	   
	   var confirmWin = layer.confirm("是否确认保存？",function(){
		   $.post(so.getRootPath() + '/samedayFinance/insertInvalidCapital.shtml',param,function(result){
			   if (result.status == "200") {
				   layer.msg("保存成功！",function(){});
	           } else {
	        	   layer.msg("保存失败，请确认信息是否填写完整，并稍后再试！",function(){});
	           }
		   },'json');
		   layer.close(confirmWin);
	   });
   }
   
   //数据上报或撤销
   function saveBalanceProcess(status) {
	   var str = "上报";
	   if (status == -1) {
		   str = "撤回";
	   }
	   var confirmWin = layer.confirm("是否确认"+str+"？",function(){
		   $.post(so.getRootPath() + '/samedayFinance/insertBalanceProcess.shtml',{subCompanyId:subCompanyId,status:status},function(result){
			   if (result.status == "200") {
				   //setBalanceProcessBtn(status);
				   querySubCompanyMsg();
				   layer.msg(str + "成功！",function(){});
	           } else {
	        	   layer.msg(str + "失败，请确认信息是否填写完整，并稍后再试！",function(){});
	           }
		   },'json');
		   layer.close(confirmWin);
	   });
   }
   
   //保存工抵余额
   function insertGongdiBalance(status) {
	   if ($("#gongdiAmount").val() == "") {
		   layer.msg("请输入工抵余额！",function(){});
		   return;
	   }
	   var confirmWin = layer.confirm("是否确认保存？",function(){
		   $.post(so.getRootPath() + '/samedayFinance/insertGongdiBalance.shtml',{subCompanyId:subCompanyId,amount:$("#gongdiAmount").val()},function(result){
			   if (result.status == "200") {
				   querySubCompanyMsg();
				   layer.msg("保存成功！",function(){});
	           } else {
	        	   layer.msg("保存失败，请确认信息是否填写完整，并稍后再试！",function(){});
	           }
		   },'json');
		   layer.close(confirmWin);
	   });
   }
   
   