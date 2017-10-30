function loadMenu() {
	$.getJSON("../../js/json/loadMenu.json", function(data) {
		var menu = data.data.menu;
		var liHtml = "";
		var aHtml = "";
		for(var i = 0; i < menu.length; i++) {
			liHtml += '<li class="mui-table-view-cell mui-collapse">'
			liHtml += '<a class="mui-navigate-right">' + menu[i] + '</a>'
			liHtml += '<div class="mui-collapse-content">'
			liHtml += '<div class="mui-scroll-wrapper  mui-segmented-control mui-slider-indicator mui-segmented-control-inverted" >'
			liHtml += '<div class="mui-scroll" id=' + i + 888 + '>'

			liHtml += '</div>'
			liHtml += '</div>'
			liHtml += '</div>'
			liHtml += '</li>'
			$("#mainUl").append(liHtml);
			liHtml = "";

		}
		var classArr = ["mui-button-row","mui-btn-green","mui-btn-blue","mui-btn-danger","mui-btn-grey","mui-btn-yellow",];
		for(var i = 0; i < menu.length; i++) {
			var btn = data.data[menu[i]];
			for(var j = 0; j < btn.length; j++) {
				var classBtn = classArr[parseInt(Math.random()*6+1)];
				aHtml += '<a id =' + 666 + i + 666 + j + '   class="mui-control-item"><button class='+classBtn+'>' + btn[j] + '</button></a>'
				$("#" + i + "888").append(aHtml);
				aHtml = "";
			}
		}
	})
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
}