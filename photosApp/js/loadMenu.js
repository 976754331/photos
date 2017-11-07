//页面加载完事件
$(function() {
	//上传本地文件点击事件
	document.getElementById("diskBtn").onclick = function() {
		var diskPath = prompt("请输入本地文件磁盘路径，根路径哦", "比如F:/test");
		if(diskPath == "比如F:/test" || diskPath == "") {
			alert("请输入正确的文件路径");
		} else {
			if(diskPath == null) {
				
			} else {
				var params = {dirPath:diskPath,userId:localStorage.getItem('userid')}
				simpAjax(Url + "/file/insertPic.do", params, function(result) {
					if(result.rtn_code == 0) {
						
					} else {
						mui.toast(result.msg);
					}
				})
			}

		}
	};

})

//加载二级分类
function loadSecondMenu(dataid, menuDiv) {
	var params = {
		parentId: dataid
	};
	var classArr = ["mui-button-row", "mui-btn-green", "mui-btn-blue", "mui-btn-danger", "mui-btn-grey", "mui-btn-yellow", ];

	var btnHtml = "";
	simpAjax(Url + "/type/second.do", params, function(result) {
		if(result.rtn_code == 0) {
			menuDiv.empty();
			var menu = result.data;
			for(var i = 0; i < menu.length; i++) {
				var classBtn = classArr[parseInt(Math.random() * 6 + 1)];
				btnHtml = '<li data-id=' + menu[i].type_id + ' class="mui-control-item"><button class=' + classBtn + '>' + menu[i].type_name + '</button></li>';
				menuDiv.append(btnHtml);
				btnHtml = "";
			}

			//加载图片
			mui('.mui-scroll').on('tap', '.mui-control-item', function(e) {
				e.stopPropagation();
				var typeId = $(this).attr("data-id");
				var btnName = $(this).children().text();
				mui.openWindow({
					url: encodeURI('../person/picture.html?' + typeId + '?' + btnName),
					id: '../person/picture.html',
					extras: {
						typeId: typeId,
						btnName: btnName
					}

				});

				//loadImage(typeId);				
			})
		} else {
			mui.toast(result.msg);
		}
	})
}



//加载主分类菜单
function loadMenu() {
	simpAjax(Url + "/type/first.do", "", function(result) {
		if(result.rtn_code == 0) {
			var menu = result.data;
			var liHtml = "";
			var aHtml = "";
			for(var i = 0; i < menu.length; i++) {
				liHtml += '<li class="mui-table-view-cell mui-collapse" data-id=' + menu[i].type_id + ' >'
				liHtml += '<a class="mui-navigate-right">' + menu[i].type_name + '</a>'
				liHtml += '<div class="mui-collapse-content">'
				liHtml += '<div class="mui-scroll-wrapper  mui-segmented-control mui-slider-indicator mui-segmented-control-inverted" >'
				liHtml += '<ul class="mui-scroll" >'

				liHtml += '</ul>'
				liHtml += '</div>'
				liHtml += '</div>'
				liHtml += '</li>'
				$("#mainUl").append(liHtml);
				liHtml = "";
			}
		} else {
			mui.toast(result.msg);
		}
	})

	/*
	
	$.getJSON("../../js/json/loadUrl.json", function(data) {

		mui(".mui-scroll").on('tap', '.mui-control-item', function() {
			//获取id
			var id = this.getAttribute("id");
			var strs = id.split("666");
			var strI = strs[1];
			var strJ = strs[2];
			var btnB = data.data.menu[strI];
			var btnUrl = data.data[btnB][strJ];
			mui.openWindow({
				id: 'detail',
				url: btnUrl
			});
		})
	})
	*/
}