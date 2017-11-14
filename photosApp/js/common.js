//默认书籍封面图
var defaultImg = '/images/zw.png';

//加载图片根路径
var picSrc = "http://127.0.0.1:8080/bookcity/file/downPic.do?attId="


//是否debug调试
var isDebug = true;

//隐藏android原生底部TAB
function hideTab(){
	if(isDebug==false){
		window.location.href='protocol://android/bookcity?param={"code":"1","data":"","msg":""}';
	}	
}
//显示android原生底部TAB
function showTab(){
	if(isDebug==false){
		window.location.href='protocol://android/bookcity?param={"code":"2","data":"","msg":""}';
	}	
}
//封装通用获取url参数方法
function GetQueryString(key){
    var reg = new RegExp("(^|&)"+ key +"=([^&]*)(&|$)");
    var result = window.location.search.substr(1).match(reg);
    return result?decodeURIComponent(result[2]):null;
}

//定义ajax URL
//var Url = "http://10.50.30.201:9001/bookcity";
//var Url = "http://192.168.0.104:8080/bookcity";
var Url = "http://10.50.30.141:8080/bookcity";

//封装通用AJAX方法
function simpAjax(url, data, successCallBack, errorCallBack, async) {
	$.ajax({
		url:url,
		data: data,
		dataType: 'json', //服务器返回json格式数据
		type: 'post', //HTTP请求类型
		success: function(data) {
		   if(data.rtn_code=='10000'){
		        mui.alert('操作超时，请重新登录','提示',['确认'],function(){
		            mui.openWindow('index.html');
		        })
		    }else{
		        successCallBack(data);
		    }
		},
		error: function(xhr, type, errorThrown) {
			if(typeof(errorCallBack) != 'undefined') {
				errorCallBack(xhr, type, errorThrown);
			} else {
				mui.toast('服务器连接失败,请检查网络');
			}
		},
		async: typeof(async) == 'undefined'?true:async
	});
}

//获取当前客户端时间
function getDate(){
	var time = new Date();
   // 程序计时的月从0开始取值后+1
   var m = time.getMonth() + 1, s;
   var t = time.getFullYear() + "-" + m + "-"
     + time.getDate() + " " + time.getHours() + ":"
     + time.getMinutes() + ":" + (s = time.getSeconds() < 10 ? '0' + time.getSeconds() : time.getSeconds());
     return t;
}

//无书籍时显示内容
function noBookList(str){
	var box = $('.empty');
	if(!box.size()){
		return '<div class="empty"><div class="noInternate"></div><p>'+str+'</p></div>';
	}
	box.show();
}

//剩余天数计算
function daysCalc(d){
	var data = new Date(Date.parse(d.replace(/-/g, "/"))),
	surPlus = data.getTime() - new Date().getTime();
	if(surPlus < 0) return '';
	var dc = surPlus / (24 * 60 * 60 * 1000);
	return ~~dc;
}

//邮箱验证
function isEmail(strEmail) {
	if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1){
		return strEmail;
	}
}

//特殊字符判断
function isSpecialStr(str){
	var pattern = new RegExp("[ `~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	return pattern.test(str);
}
