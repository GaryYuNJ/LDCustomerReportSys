/*js*/
$(function() {
	var formatDate = {
			changeMonth: true,
	        changeYear: true,
	        dateFormat: "yy-mm-dd",
	        monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
	        monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'],  
	        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
	        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
	        dayNamesMin: ['日','一','二','三','四','五','六']
	}
	var formatMaxDate = {
			changeMonth: true,
	        changeYear: true,
	        dateFormat: "yy-mm-dd",
	        monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
	        monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'],  
	        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
	        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
	        dayNamesMin: ['日','一','二','三','四','五','六'], 
	        maxDate:new Date()
	}
	var formatDateMonth = {
			changeMonth: true,
	        changeYear: true,
	        dateFormat: "yy-mm",
	        monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
	        monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'],  
	         
	}
    $( "#datepicker" ).datepicker( formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
	$( "#datepickermanage" ).datepicker( formatDate).attr("readonly", "readonly");
    $( "#datepickerimessage" ).datepicker( formatMaxDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercity" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerpay" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercyj" ).datepicker(formatDate).attr("readonly", "readonly");
    $( "#datepickerpk" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickersjkp" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickersjkpadd" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerkptime" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerstart" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerreport" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickersrjz" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerkpqk" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickeryskbb" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerskdate" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"}); 
    $( "#datepickercyjsecond" ).datepicker(formatDate).attr("readonly", "readonly"); 
    $( "#datepickercyjsecondlast" ).datepicker(formatDate).attr("readonly", "readonly"); 
    $( "#datepickercyjthird" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});  
    $( "#datepickercyjforth" ).datepicker(formatDate).attr("readonly", "readonly");   
    $( "#datepickercyjfive" ).datepicker(formatDate).attr("readonly", "readonly");  
    $( "#datepickerskdaterep" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});  
    $( "#datepickerskdatereptwo" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});  
    $( "#datepickerskdatetwo" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});  
    $( "#datepickerskdateall" ).datepicker(formatDate).attr("readonly", "readonly");  
    $( "#datepickerlast" ).datepicker(formatDate).attr("readonly", "readonly");  
    $( "#datepickerlastrefresh" ).datepicker(formatDate).attr("readonly", "readonly"); 
    $( "#datepickerqytime" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});  
    $( "#datepickerhtqytime" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});    
    $( "#datepickerhtget" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"}); 
    $( "#datepickerhtpay" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});  
    $( "#datepickerhtpaylast" ).datepicker(formatDate).attr("readonly", "readonly"); 
    $( "#datepickerrelationstart" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerrelationend" ).datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerrelationdisabled" ).datepicker(formatDate).attr("readonly", "readonly"); 
    $( "#datepickermanastart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickermanaend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerclsstart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerclsend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerzwstart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerzwend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrstart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrbigstart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrbigend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrprostart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrproend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerreward").datepicker(formatDateMonth).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerrewardlast").datepicker(formatDate).attr("readonly", "readonly");
    $( "#datepickerpossalestart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerpossaleend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerposfinestart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerposfineend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerposclassstart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerposclassend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerpospozstart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerpospozend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrvialdaystart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrvialdayend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrvialbigdaystart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrvialbigdayend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrvialprodaystart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickerarrvialprodayend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommisposstart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommisposend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommisnorstart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommisnorend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommiscalfystart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommiscalfyend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommiscalxsstart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommiscalxsend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommiscalxlstart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommiscalxlend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommiscaldlstart").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
    $( "#datepickercommiscaldlend").datepicker(formatDate).attr("readonly", "readonly").css({"background-color":"#FFFFFF"});
  });