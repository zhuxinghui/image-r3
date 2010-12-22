			dhtmlx.skin = "dhx_skyblue";
			var slMenu = 'welcomeMenu' ;
			function changeCSS(menuName){
				if($("#" + slMenu)!=$("#" + menuName))
				{
					$("#" + slMenu).removeClass("head_menu_Tab_Choosed").addClass("head_menu_Tab_NoChoosed");
					$("#" + menuName).removeClass("head_menu_Tab_NoChoosed").addClass("head_menu_Tab_Choosed");
					slMenu = menuName ;
				}
			}
			var slDiv = "";
			function changeDiv(menuName,fun){
				if($("#" + slDiv)!=$("#" + menuName))
				{
					$("#" + slDiv).removeClass("head_menu_Tab_Choosed").addClass("head_menu_Tab_NoChoosed");
					$("#" + menuName).removeClass("head_menu_Tab_NoChoosed").addClass("head_menu_Tab_Choosed");
					slDiv = menuName ;
				}
				if(fun)
					fun();
			}
			function changeFunction(funName){
				doGet('index.rv','indexPanel',function(){changeCSS('rivuletMenu');tree.selectItem(funName,true ,false);});
			}
			function changeMonitor(){
				doGet('crawl.rv','indexPanel',function(){changeCSS('crawlMenu');});
			}
			function doPost(form , panelId,func , errorFun){
				$.blockUI();
				var options = {
				   success: function(data) {
					    $.unblockUI();
						$("#" + panelId).empty().show().html(data);
						if(document.getElementById('login')){
							location.href="welcome.rv";
						}
						if(func){
							func();
						}
						if(document.getElementById("msg")){
							jAlert($("#msg").html(),"Message");
						}
				   },
				   error:function (xhr, ajaxOptions, thrownError){	
					 $.unblockUI();
                     jAlert(xhr.getResponseHeader("emsg") ,"Message");
					 if(errorFun){
						 errorFun();
					 }
				   }
				};
				if(!valiHelpler.validateForm(form)){
					 $.unblockUI();
					return false;
				}
				$(form).ajaxSubmit(options);
				return false;
			}
			function doGet(ajaxUrl,panelId,func , errorFun)
			{
				$.blockUI();
				$.ajax({
					url: ajaxUrl,
					type: "get",
					success: function(data){
						$.unblockUI();
						$("#" + panelId).empty().show().html(data);
						if(document.getElementById('login')){
							location.href="welcome.rv";
						}
						if(func){
							func();
						}
					},
					error:function (xhr, ajaxOptions, thrownError){ 
						$.unblockUI();
						if(xhr.getResponseHeader("emsg")==''){
							jAlert("未知错误，请检查服务状态","Message");
						}else{
							jAlert(xhr.getResponseHeader("emsg") ,"Message");
						}
						
					 //window.open(ajaxUrl) ;
						if(errorFun)
							 errorFun();
				   }
				});
			}
			function doLogin(form,func,errfun){
				$.blockUI();
				var options = {
				   success: function(data) {
					    $.unblockUI();
						if(func)
							func();
				   },
				   error:function (xhr, ajaxOptions, thrownError){	
					 $.unblockUI();
                     jAlert(xhr.getResponseHeader("emsg") ,"Message",function(){if(errfun) errfun();});
				   }
				};
				if(!valiHelpler.validateForm(form)){
					 $.unblockUI();
					return false;
				}
				$(form).ajaxSubmit(options);
				return false ;
			}
			function doLogout(ajaxUrl,func , errorFun)
			{
				$.blockUI();
				$.ajax({
					url: ajaxUrl,
					type: "get",
					success: function(data){
						$.unblockUI();
						if(func){
							func();
						}
					},
					error:function (xhr, ajaxOptions, thrownError){ 
						$.unblockUI();
						if(xhr.getResponseHeader("emsg")==''){
							jAlert("unknow error,may be server is down","Message");
						}else{
							jAlert(xhr.getResponseHeader("emsg") ,"Message");
						}
						if(errorFun)
							 errorFun();
				   }
				});
			}
			function doGetUpdate(ajaxUrl,panelId,func , errorFun)
			{
				$.ajax({
					url: ajaxUrl,
					type: "get",
					success:function(data){
						$("#" + panelId).empty().show().html(data);
						if(func){
							func();
						}
					},
					error:function (xhr, ajaxOptions, thrownError){ 
						if(xhr.getResponseHeader("emsg")==''){
							jAlert("unknow error,may be server is down","Message");
						}else{
							jAlert(xhr.getResponseHeader("emsg") ,"Message");
						}
						
					 //window.open(ajaxUrl) ;
						if(errorFun)
							 errorFun();
				   }
				});
			}
			function doUpdateTaskStatus(t)
			{
				var taskList = document.getElementsByName("con");
				if(taskList && taskList.length>t)
				{
					$("#p_"+taskList[t].tid).everyTime(3000, function(i) { 
						if(taskList[t] && taskList[t].tid)
						{
							$.ajax({
								url: "/comet?t="+taskList[t].tid,
								type: "get",
								success: function(rdata){
									var data = rdata.split(":");
									if(data[0]=='-1'){
										document.getElementById('m_'+taskList[t].tid).src='../images/list/cha.png';
										document.getElementById('m_'+taskList[t].tid).alt=rdata.substring(3);
									}
									if(data[0]!='1' && data[0]!='2'){
										$('#p_'+taskList[t].tid).show();
										$('#r_'+taskList[t].tid).hide();
										$("#p_"+taskList[t].tid).stopTime();
									}
								}
							});	
						}
					}) ;
				}
			}
			var running = false ;
			var param ="msg=" ;
			function doUpdateRunningStatus()
			{
				running = true ;
				$("#taskConsole").everyTime(3000, function(i) { 
					doGetUpdate('console.rv','taskConsole',null);
				}) ;
			}
			function selectAll(){
				$("input[name='id']").each(function(){
					$(this).attr('checked',$('#allid').attr('checked')==true);
				}) ;
			}
			function info(msg){
				jAlert(msg ,"Message");
			}
			function infoTheme(msg){
				doGet('system/project.rv','crawlMainPanel',function(){$.fancybox.close();jAlert(msg ,"Message");});
			}
			function infoOther(msg,func){
				$.fancybox.close();
				jAlert(msg ,"Message");
				if(func){
					func();
				}
			}
			function welcomeDate(panel){
				var rnd = getRnd(10,true,true,false);
				$("#"+panel).html("<span id='"+rnd+"' class='text-key'>Loading...</span>");
				setInterval(function(){
					var holiday="";
					
					var calendar = new Date();
					var day = calendar.getDay();
					var month = calendar.getMonth();
					var date = calendar.getDate();
					var year = calendar.getFullYear();
					month++;
					
					//母亲节
					var _date=new Date("May 0 "+year);
					if(_date.getDay()==0){
						var _n=14
					}else{
						var _n=14-_date.getDay();
					}
					
					if ((month == 1) && (date == 1)) holiday ="元旦";
					if ((month == 2) && (date == 14)) holiday ="情人节";
					if ((month == 3) && (date == 15)) holiday ="消费者权益日";
					if ((month == 3) && (date == 8)) holiday ="妇女节";
					if ((month == 4) && (date == 1)) holiday ="愚人节";
					if ((month == 3) && (date == 12)) holiday ="植树节 孙中山逝世纪念日";
					if ((month == 5) && (date == 1)) holiday ="国际劳动节";
					if ((month == 5) && (date==_n) && day==0) holiday ="母亲节";
					if ((month == 5) && (date == 4)) holiday ="青年节";
					if ((month == 6) && (date == 1)) holiday ="国际儿童节";
					if ((month == 7) && (date == 1)) holiday ="香港回归纪念日";
					if ((month == 9) && (date == 10)) holiday ="中国教师节";
					if ((month == 9) && (date == 18)) holiday ="九·一八事变纪念日";
					if ((month == 9) && (date == 28)) holiday ="孔子诞辰";
					if ((month == 10) && (date == 6)) holiday ="老人节";
					if ((month == 12) && (date == 20)) holiday ="澳门回归纪念";
					if ((month == 12) && (date == 24)) holiday ="平安夜"; 
					if ((month == 12) && (date == 25)) holiday ="圣诞节"; 
				
					$("#"+panel).html(new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay())+" "+holiday);
				},1000);
			}

			function getRnd(len,upper,lower,num){
				var a=new Array(); 
				var b=new Array(""); 
				var c=new Array(""); 
				var e=""; 

				a[0]= ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
				a[1]= ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
				a[2]= ["0","1","2","3","4","5","6","7","8","9"]; 

				if(upper){c=b.concat(a[0]);}
				if(lower){c=b.concat(a[1]);}
				if(num){c=b.concat(a[1]);}

				for (var i=0;i<len;i++){ 
					e+=c[Math.round(Math.random()*(c.length-1))] 
				}
				return e; 
			}
			/**
			var isOverSearchIn = false ;
			var isOverSearchInDiv = false;
			var searchBox ;

			$(document).ready(function(){
				$("td[name='searchIn']").hover(
					function () {
						if(searchBox){
							$("div[name='searchInBox']").hide();
						}
						var hight = 0;
						
						$("div[name='searchInBox']").css("top",$(this).offset().top+$(this).height()).css("left",$(this).offset().left);
						(searchBox = $("div[name='searchInBox']")).show() ;
						isOverSearchIn = true ;
					},
					function () {
						if(!isOverSearchInDiv)
							$("div[name='searchInBox']").hide();
					}
				);
				$("div[name='searchInBox']").hover(
					function () {
						isOverSearchInDiv = true ;
					},
					function () {
						isOverSearchInDiv = false ;
						$("div[name='searchInBox']").hide();
					}
				);
			});
			**/

			
			function isMouseLeaveOrEnter(e, handler) {     
				if (e.type != 'mouseout' && e.type != 'mouseover') return false;     
				var reltg = e.relatedTarget ? e.relatedTarget : e.type == 'mouseout' ? e.toElement : e.fromElement;     
				while (reltg && reltg != handler)     
					reltg = reltg.parentNode;     
				return (reltg != handler);     
			}
			function shutdown(){
				doGet('welcome!shutdown.rv','temp');
				//jAlert('演示系统，请勿关闭ESP-R3服务器，操作终止！','提示窗口');
			}