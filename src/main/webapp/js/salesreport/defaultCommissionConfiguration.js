 $(document).ready(function(){
	//新增一行
	//职位佣金配置-销售类型
	$("#addPosSale").click(function(){
       var _len = $("#posSaleTab tr").length;        
       $("#posSaleTab").append("<tr id="+_len+" align='center'>"
		    +"<td><select class='form-control selectfont'><option>销售类型</option></select></td>"
	        +"<td><select class='form-control selectfont'><option>职位</option></select></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerpossalestart"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerpossaleend"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'posSale("+_len+")\'>删除</button></td>" 
	    	+"</tr>");
       $( "#datepickerpossalestart"+_len+"").datepicker(so.formatDate );
       $( "#datepickerpossaleend"+_len+"").datepicker(so.formatDate );    
    }) 
	//职位佣金配置-业态细类
	$("#addPosFine").click(function(){
       var _len = $("#posFineTab tr").length;        
       $("#posFineTab").append("<tr id="+_len+" align='center'>"
		    +"<td><select class='form-control selectfont'><option>业态细类</option></select></td>"
            +"<td><select class='form-control selectfont'><option>职位</option></select></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerposfinestart"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerposfineend"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'posFine("+_len+")\'>删除</button></td>" 
        	+"</tr>");
       $( "#datepickerposfinestart"+_len+"").datepicker(so.formatDate );
       $( "#datepickerposfineend"+_len+"").datepicker(so.formatDate );    
    }) 
    //职位佣金配置-业态大类
    $("#addPosClass").click(function(){
       var _len = $("#classPosTab tr").length;        
       $("#classPosTab").append("<tr id="+_len+" align='center'>"
		    +"<td><select class='form-control selectfont'><option>业态大类</option></select></td>"
            +"<td><select class='form-control selectfont'><option>职位</option></select></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerposclassstart"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerposclassend"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'classPos("+_len+")\'>删除</button></td>" 
        	+"</tr>");
       $( "#datepickerposclassstart"+_len+"").datepicker(so.formatDate );
       $( "#datepickerposclassend"+_len+"").datepicker(so.formatDate );    
    }) 
    //职位佣金配置-职位
    $("#addPosP").click(function(){
       var _len = $("#positionPTab tr").length;        
       $("#positionPTab").append("<tr id="+_len+" align='center'>"
            +"<td><select class='form-control selectfont'><option>职位</option></select></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerpospozstart"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerpospozend"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'positionP("+_len+")\'>删除</button></td>" 
        	+"</tr>");
       $( "#datepickerpospozstart"+_len+"").datepicker(so.formatDate );
       $( "#datepickerpospozend"+_len+"").datepicker(so.formatDate );    
    }) 
    
    
        
    //到账与日结比例配置-业态细类
 	$("#addDayArrival").click(function(){
        var _len = $("#arrivalDayTab tr").length;        
        $("#arrivalDayTab").append("<tr id="+_len+" align='center'>"
		    +"<td><select class='form-control selectfont'><option>业态细类</option></select></td>"               
			+"<td><input type='text' class='form-control form-controlbg' style='width:45%;float:left;'>"
			+"<span style='width:10%;float:left;margin-top:7px;'>~</span><input type='text' class='form-control form-controlbg'style='width:45%;float:right;'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrvialdaystart"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrvialdayend"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'arrivalDay("+_len+")\'>删除</button></td>" 
         	+"</tr>");
        $( "#datepickerarrvialdaystart"+_len+"").datepicker(so.formatDate );
        $( "#datepickerarrvialdayend"+_len+"").datepicker(so.formatDate );    
     }) 
   //到账与日结比例配置-业态大类
 	$("#addArrivalbigDay").click(function(){
        var _len = $("#arrivalbigDayTab tr").length;        
        $("#arrivalbigDayTab").append("<tr id="+_len+" align='center'>"
		    +"<td><select class='form-control selectfont'><option>业态大类</option></select></td>"               
		    +"<td><input type='text' class='form-control form-controlbg' style='width:45%;float:left;'>"
			+"<span style='width:10%;float:left;margin-top:7px;'>~</span><input type='text' class='form-control form-controlbg'style='width:45%;float:right;'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrvialbigdaystart"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrvialbigdayend"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'arrivalbigDay("+_len+")\'>删除</button></td>" 
         	+"</tr>");
        $( "#datepickerarrvialbigdaystart"+_len+"").datepicker(so.formatDate );
        $( "#datepickerarrvialbigdayend"+_len+"").datepicker(so.formatDate );    
     }) 
   //到账与日结比例配置-默认
 	$("#addArrivalproDay").click(function(){
        var _len = $("#arrivalproDayTab tr").length;        
        $("#arrivalproDayTab").append("<tr id="+_len+" align='center'>"		   
			+"<td><input type='text' class='form-control form-controlbg' style='width:45%;float:left;'>"
			+"<span style='width:10%;float:left;margin-top:7px;'>~</span><input type='text' class='form-control form-controlbg'style='width:45%;float:right;'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrvialprodaystart"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg' id='datepickerarrvialprodayend"+_len+"'></td>"
			+"<td><input type='text' class='form-control form-controlbg'></td>"
			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'arrivalproDayTab("+_len+")\'>删除</button></td>" 
         	+"</tr>");
        $( "#datepickerarrvialprodaystart"+_len+"").datepicker(so.formatDate );
        $( "#datepickerarrvialprodayend"+_len+"").datepicker(so.formatDate );    
     })
     
     
     
   //佣金计算方式配置-房源类型
  	$("#addCommisCalfy").click(function(){
         var _len = $("#commisNorCalfyTab tr").length;        
         $("#commisNorCalfyTab").append("<tr id="+_len+" align='center'>" 
  	        +"<td><select class='form-control selectfont'><option>房源类型</option></select></td>"
  	      +"<td><select class='form-control selectfont'><option>佣金计算方式</option></select></td>"
  			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommiscalfystart"+_len+"'></td>"
  			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommiscalfyend"+_len+"'></td>"
  			+"<td><input type='text' class='form-control form-controlbg'></td>"
  			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
  			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'commisNorCal("+_len+")\'>删除</button></td>" 
  	    	+"</tr>");
         $( "#datepickercommiscalfystart"+_len+"").datepicker(so.formatDate );
         $( "#datepickercommiscalfyend"+_len+"").datepicker(so.formatDate );    
      }) 
    //佣金计算方式配置-销售类型
  	$("#addCommisCalxs").click(function(){
         var _len = $("#commisNorCalxsTab tr").length;        
         $("#commisNorCalxsTab").append("<tr id="+_len+" align='center'>" 
  	        +"<td><select class='form-control selectfont'><option>销售类型</option></select></td>"
  	      +"<td><select class='form-control selectfont'><option>佣金计算方式</option></select></td>"
  			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommiscalxsstart"+_len+"'></td>"
  			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommiscalxsend"+_len+"'></td>"
  			+"<td><input type='text' class='form-control form-controlbg'></td>"
  			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
  			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'commisNorCalxs("+_len+")\'>删除</button></td>" 
  	    	+"</tr>");
         $( "#datepickercommiscalxsstart"+_len+"").datepicker(so.formatDate );
         $( "#datepickercommiscalxsend"+_len+"").datepicker(so.formatDate );    
      }) 
     //佣金计算方式配置-业态细类
  	 $("#addCommisCalxl").click(function(){
         var _len = $("#commisNorCalxlTab tr").length;        
         $("#commisNorCalxlTab").append("<tr id="+_len+" align='center'>" 
  	        +"<td><select class='form-control selectfont'><option>业态细类</option></select></td>"
  	      +"<td><select class='form-control selectfont'><option>佣金计算方式</option></select></td>"
  			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommiscalxlstart"+_len+"'></td>"
  			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommiscalxlend"+_len+"'></td>"
  			+"<td><input type='text' class='form-control form-controlbg'></td>"
  			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
  			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'commisNorCalxl("+_len+")\'>删除</button></td>" 
  	    	+"</tr>");
         $( "#datepickercommiscalxlstart"+_len+"").datepicker(so.formatDate );
         $( "#datepickercommiscalxlend"+_len+"").datepicker(so.formatDate );    
       }) 
     //佣金计算方式配置-业态大类
  	 $("#addCommisCaldl").click(function(){
         var _len = $("#commisNorCaldlTab tr").length;        
         $("#commisNorCaldlTab").append("<tr id="+_len+" align='center'>" 
  	        +"<td><select class='form-control selectfont'><option>业态大类</option></select></td>"
  	      +"<td><select class='form-control selectfont'><option>佣金计算方式</option></select></td>"
  			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommiscaldlstart"+_len+"'></td>"
  			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommiscaldlend"+_len+"'></td>"
  			+"<td><input type='text' class='form-control form-controlbg'></td>"
  			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
  			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'commisNorCaldl("+_len+")\'>删除</button></td>" 
  	    	+"</tr>");
         $( "#datepickercommiscaldlstart"+_len+"").datepicker(so.formatDate );
         $( "#datepickercommiscaldlend"+_len+"").datepicker(so.formatDate );    
       }) 
      
     
   
   //提佣办法配置-职位对应
 	$("#addCommisPos").click(function(){
        var _len = $("#commisPosTab tr").length;        
        $("#commisPosTab").append("<tr id="+_len+" align='center'>"
 		    +"<td><select class='form-control selectfont'><option>职位</option></select></td>"
 	        +"<td><select class='form-control selectfont'><option>佣金类型</option></select></td>"
 			+"<td><input type='text' class='form-control form-controlbg'></td>"
 			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommisposstart"+_len+"'></td>"
 			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommisposend"+_len+"'></td>"
 			+"<td><input type='text' class='form-control form-controlbg'></td>"
 			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
 			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'commisPos("+_len+")\'>删除</button></td>" 
 	    	+"</tr>");
        $( "#datepickercommisposstart"+_len+"").datepicker(so.formatDate );
        $( "#datepickercommisposend"+_len+"").datepicker(so.formatDate );    
     }) 
   //提佣办法配置-默认
  	$("#addCommisNor").click(function(){
         var _len = $("#commisNorTab tr").length;        
         $("#commisNorTab").append("<tr id="+_len+" align='center'>" 
  	        +"<td><select class='form-control selectfont'><option>佣金类型</option></select></td>"
  			+"<td><input type='text' class='form-control form-controlbg'></td>"
  			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommisnorstart"+_len+"'></td>"
  			+"<td><input type='text' class='form-control form-controlbg' id='datepickercommisnorend"+_len+"'></td>"
  			+"<td><input type='text' class='form-control form-controlbg'></td>"
  			+"<td style='vertical-align:middle;'><button class='btn btn-xs  btn-primary' title='保存'>保存</button>&nbsp;&nbsp;"
  			+"<button class='btn  btn-xs  btn-danger' title='删除' onclick=\'commisNor("+_len+")\'>删除</button></td>" 
  	    	+"</tr>");
         $( "#datepickercommisnorstart"+_len+"").datepicker(so.formatDate );
         $( "#datepickercommisnorend"+_len+"").datepicker(so.formatDate );    
      }) 	
})
//删除一行
//职位佣金配置-销售类型
 var posSale =function(index)
   	{
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#posSaleTab tr").length;
	       $("#posSaleTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   	}
//职位佣金配置-业态细类
 var posFine =function(index)
   	{
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#posFineTab tr").length;
	       $("#posFineTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   	}
//职位佣金配置-业态大类
 var classPos=function(index)
   	{
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#classPosTab tr").length;
	       $("#classPosTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   	}
//职位佣金配置-职位
 var positionP=function(index)
   	{
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#positionPTab tr").length;
	       $("#positionPTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   	}
 
 
 //到账与日结比例配置-业态细类
 var arrivalDay=function(index)
 {
	 var confirmWin = layer.confirm("是否确认删除？",function(){
		 var _len = $("#arrivalDayTab tr").length;
 	     $("#arrivalDayTab tr[id='"+index+"']").remove();//删除当前行
 	     layer.close(confirmWin);
     });
 }
  //到账与日结比例配置-业态大类
 var arrivalbigDay=function(index)
 {
	 var confirmWin = layer.confirm("是否确认删除？",function(){
		 var _len = $("#arrivalbigDayTab tr").length;
 	     $("#arrivalbigDayTab tr[id='"+index+"']").remove();//删除当前行
 	     layer.close(confirmWin);
     });
 }
 //到账与日结比例配置-默认
 var arrivalproDay=function(index)
 {
	 var confirmWin = layer.confirm("是否确认删除？",function(){
		 var _len = $("#arrivalproDayTab tr").length;
 	     $("#arrivalproDayTab tr[id='"+index+"']").remove();//删除当前行
 	     layer.close(confirmWin);
     });
 }
 
 
//佣金计算方式配置-房源类型
 var commisNorCal =function(index)
   	{
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#commisNorCalfyTab tr").length;
	       $("#commisNorCalfyTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   	}
//佣金计算方式配置-销售类型
 var commisNorCalxs =function(index)
   	{
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#commisNorCalxsTab tr").length;
	       $("#commisNorCalxsTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   	}
//佣金计算方式配置-业态细类
 var commisNorCalxl =function(index)
   	{
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#commisNorCalxlTab tr").length;
	       $("#commisNorCalxlTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   	}
//佣金计算方式配置-业态大类
 var commisNorCaldl =function(index)
   	{
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#commisNorCaldlTab tr").length;
	       $("#commisNorCaldlTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   	}
 
 
//提佣办法配置-职位对应
 var commisPos =function(index)
   	{
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#commisPosTab tr").length;
	       $("#commisPosTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   	}
//提佣办法配置-默认
 var commisNor =function(index)
   	{
	   var confirmWin = layer.confirm("是否确认删除？",function(){
	       var _len = $("#commisNorTab tr").length;
	       $("#commisNorTab tr[id='"+index+"']").remove();//删除当前行
	       layer.close(confirmWin);
       });
   	}