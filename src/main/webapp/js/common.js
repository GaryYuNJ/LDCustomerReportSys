 
/**
 * @author 
 * @ps 
 */
(function(o,w){
	if(!w.so)w.so = {};
	return (function(so){
		so.$1 = !0,//true
		so.$0 = !1;//false
		/**
		 * 全选
		 */
		so.checkBoxInit = function(prentCheckbox,childCheckbox){
			childCheckbox = o(childCheckbox),prentCheckbox = o(prentCheckbox);
			//先取消全选。
			//childCheckbox.add(prentCheckbox).attr('checked',!1);
			//全选
			prentCheckbox.on('click',function(){
				childCheckbox.attr('checked',this.checked);
			});
			//子选择
			childCheckbox.on('click',function(){
				prentCheckbox.attr('checked',childCheckbox.length === childCheckbox.end().find(':checked').not(prentCheckbox).length);
			});
		},
		//初始化
		so.init = function(fn){
			o(function(){fn()});
		}
		so.id = function(id){
			return o('#' + id);
		}
		so.getRootPath = function(){
		    var curWwwPath=window.document.location.href;
		    var pathName=window.document.location.pathname;
		    var pos=curWwwPath.indexOf(pathName);
		    var localhostPaht=curWwwPath.substring(0,pos);
		    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		    return(localhostPaht+projectName);
		}
		so.checkPhone = function(phone){ 
		    if(!(/^1[34578]\d{9}$/.test(phone))){ 
		        return false; 
		    }
		    return true;
		}
		so.clearToNum = function(obj){
			obj.value = obj.value.replace(/[^(\-)\d.]/g,""); //清除"数字"和"."以外的字符
			obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是
			obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
			obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
			obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d\d\d).*$/,'$1$2.$3'); //只能输入两个小数
		}
		so.formatDate = {
				changeMonth: true,
		        changeYear: true,
		        dateFormat: "yy-mm-dd",
		        monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
		        monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'],  
		        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		        dayNamesMin: ['日','一','二','三','四','五','六']
		}
		so.default = function(){}
		
	})(so);
})($,window);


