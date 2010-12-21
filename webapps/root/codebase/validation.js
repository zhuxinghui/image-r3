var valiHelpler = new ValidationHelper() ;
var glableObject ;
var glableObjectDis ;
function isEmpty(str){
	return (str==null||str.replace(/(^\s*)|(\s*$)/g, "").length==0);
}
function isNumber(v) {
	return  (!isNaN(v) && !/^\s+$/.test(v));
}

function isDigits(v) {
				return   !/[^\d]/.test(v);
			}
function isRegTime(v) {
				return   !/[^\d\-,－，]/.test(v);
			}
function isAlpha(v) {
				return   v=='' || /^[a-z\_\*A-Z0-9-]+$/.test(v)
			}

function isAlphaNumber(v) {
				return   !/\W/.test(v)
			}

function isDate(v) {
				var test = new Date(v);
				return  !isNaN(test);
			}


function isEmail(v) {
				if($.trim(v)==""){
					return true;
				}
				return  /\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/.test(v)
			}
function isUrl(v) {
				return  /^(http|https|ftp):\/\/(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i.test(v)
			}

function isDateAu(v) {
				var regex = /^(\d{2})\/(\d{2})\/(\d{4})$/;
				if(!regex.test(v)) return false;
				var d = new Date(v.replace(regex, '$2/$1/$3'));
				return ( parseInt(RegExp.$2, 10) == (1+d.getMonth()) ) && 
							(parseInt(RegExp.$1, 10) == d.getDate()) && 
							(parseInt(RegExp.$3, 10) == d.getFullYear() );
			}

function isCurrencyDollar(v) {
				// [$]1[##][,###]+[.##]
				// [$]1###+[.##]
				// [$]0.##
				// [$].##
				return  /^\$?\-?([1-9]{1}[0-9]{0,2}(\,[0-9]{3})*(\.[0-9]{0,2})?|[1-9]{1}\d*(\.[0-9]{0,2})?|0(\.[0-9]{0,2})?|(\.[0-9]{1,2})?)$/.test(v)
			}

function eluEquals(v,elmId) {
				var p = $("input[name='"+elmId+"']");
				return (v=='' && p.val()=='') || v==p.val();
			}


function isDateCn(v) {				
				var regex = /^(\d{4})-(\d{2})-(\d{2})$/;
				if(!regex.test(v)) return false;
				var d = new Date(v.replace(regex, '$1/$2/$3'));
				return ( parseInt(RegExp.$2, 10) == (1+d.getMonth()) ) && 
							(parseInt(RegExp.$3, 10) == d.getDate()) && 
							(parseInt(RegExp.$1, 10) == d.getFullYear() );
			}
function isInteger(v) {
				return  (/^[-+]?[\d]+$/.test(v));
			}

function isChinese(v) {
				return  (/^[\u4e00-\u9fa5]+$/.test(v));
			}

function isIp(v) {
				return  (/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(v));
			}

function isZip(v) {
				return  (/^[0-9]\d{5}$/.test(v));
			}

function isPhone(v) {
				return  /^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/.test(v);
			}

function isMobilePhone(v) {
				return  (/(^0?[1][35][0-9]{9}$)/.test(v));
			}
function lengthMin(v,args) {
		return v.length >= parseInt(args[0]);
	}
function lengthMax(v,args) {
		return  v.length <= parseInt(args[0]);
	}
function numberMax(v,args) {
		return  parseFloat(v) <= parseFloat(args[0]);
	}

function numberMin(v,args) {
		return parseFloat(v) >= parseFloat(args[0]);
	}
var rulesSource=[
	['required',  isEmpty,"不能为空，请输入正确的数据",1],
	['validate-number', isNumber,"请填写整数",1],
	['validate-regtime', isRegTime,"请填写正确的时间范围",1],
	['validate-alpha', isAlpha,"请填写字母",1],
	['validate-digits',  isDigits,"数字格式错误，请输入数字",1],
	['validate-date', isDate,"日期格式错误",1],
	['validate-email',  isEmail,"邮件格式错误",1],
	['validate-url',isUrl,"网址格式错误，请重新输入",1],
	['validate-integer',  isInteger,"请输入整数",1],
	['validate-chinese', isChinese,"请输入中文信息",1],
	['validate-ip',isIp,"IP地址格式错误，请输入正确的IP地址",1],
	['validate-zip',isZip,"邮政编码格式错误，请输入正确的邮政编码",1],			
	['validate-phone', isPhone,"电话号码格式错误，请输入正确的电话号码",1],
	['validate-mobile-phone',isMobilePhone,"<br>手机号码格式错误，请输入正确的手机号码",1],
	['length-min',lengthMin,"长度不能小于%s",2],
	['length-max',lengthMax,"长度不能大于%s",2],
	['number-min',numberMin,"数字不能小于%s",2],
	['number-max',numberMax,"数字不能大于%s",2],
	['elu-equals',eluEquals,"两次输入值不一致",3]
];
function ValidationHelper(){
	this.getRule=function(className){
		var ruleNameResult=getRuleName(className);			
		return rulesSource[ruleNameResult];
	}
	this.classNameToArray=function(className)
	{
		return className.split(' ');
	}
	this.getRuleType=function (className){
		var ruleNameResult ;
		
		for(var ruleName in rulesSource) {
			if(className.indexOf(rulesSource[ruleName][0])>=0) {
				ruleNameResult = rulesSource[ruleName][3]
				break;
			}
		}	
		return ruleNameResult;
	}
	this.getRuleName=function (className){
		var ruleNameResult ;
		
		for(var ruleName in rulesSource) {
			if(className == rulesSource[ruleName][0]) {
				ruleNameResult = rulesSource[ruleName][1];
				break;
			}	
			if(className != rulesSource[ruleName][0] && className.indexOf(rulesSource[ruleName][0])==0) {
				ruleNameResult = new Array();
				ruleNameResult[0] = rulesSource[ruleName][1];
				ruleNameResult[1] = className.split(":")[1] ;
				break;
			}
		}	
		return ruleNameResult;
	}
	this.getRuleErrorMessage=function (className){
		var ruleNameResult ;
		for(var ruleName in rulesSource) {
			if(className.indexOf(rulesSource[ruleName][0])>=0) {
				ruleNameResult = rulesSource[ruleName][2];
				break;
			}
		}	
		var num="";
		var elements=className.split(":");
		if(elements.length==2){
			ruleNameResult=ruleNameResult.replace("%s",elements[1]);	
		}
		return ruleNameResult;
	}
	/**
	**/
	this.validateForm=function (form){
		var validation = true ;
		for(i=0;i<form.length;i++){
			if(!$(form.elements[i]).attr("disabled"))
			{
				var value=form.elements[i].value;
				glableObject = form.elements[i] ;
				if(form.elements[i].className && !this.validate(value,form.elements[i].className))
				{
					validation = false ;
					break ;
				}
			}
		}
		return validation ;
	}
	/**
	 * 
	 *
	 *
	 */
	this.validate=function (v , className)
	{
		v = v.replace(/\'/g,'"');
		var validateResult = true;
		var array = valiHelpler.classNameToArray(className) ;
		for(var i=0 ; i<array.length ; i++)
		{
			var ruleName = valiHelpler.getRuleName(array[i]) ;
			if(ruleName)
			{	
				if(valiHelpler.getRuleType(array[i])==1)
				{
					var elementName=$(glableObject).attr('name')+" ";
					if(array[i]=="required")
					{
						if(ruleName(v))
						{
							validateResult = false ;
							if(glableObjectDis){
								elementName+=glableObjectDis;
							}
							jAlert(elementName+valiHelpler.getRuleErrorMessage(array[i]),"Warning Message", glableObjectFocus);
							validateResult = false ;
							break ;
						}
					}else if(!ruleName(v))
					{
						validateResult = false ;
						var elementName=$(glableObject).attr('name')+" ";
						if(glableObjectDis){
							elementName+=glableObjectDis;
						}
						jAlert(elementName+valiHelpler.getRuleErrorMessage(array[i]), "Warning Message", glableObjectFocus);
						validateResult = false ;
						break ;
					}
				}else if(valiHelpler.getRuleType(array[i])==2)
				{
					if(isNaN(parseInt(ruleName[1]))){
						jAlert(elementName+"Validation parameter error:"+array[i],"Warning Message",  glableObjectFocus);
						validateResult = false ;
						break ;
					}
					
					if(!ruleName[0](v,[ruleName[1]]))
					{
						if(glableObjectDis){
							elementName+=glableObjectDis;
						}
						jAlert(elementName+valiHelpler.getRuleErrorMessage(array[i]),"Warning Message",  glableObjectFocus);
						validateResult = false ;
						break ;
					}
				}else if(valiHelpler.getRuleType(array[i])==3)
				{
					if(!ruleName[0](v,[ruleName[1]]))
					{
						if(glableObjectDis){
							elementName+=glableObjectDis;
						}
						jAlert(elementName+valiHelpler.getRuleErrorMessage(array[i]),"Warning Message",  glableObjectFocus);
						validateResult = false ;
						break ;
					}
				}
			}
		}
		return validateResult ;
	}
}
function glableObjectFocus(){
	glableObject.focus();
}