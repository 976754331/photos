function unloadImage(str, json) {
	$.getJSON("../../js/json/"+json+".json", function(data) {
		var nanxingList = data.data[str];
		var length = nanxingList.length;
		var strHtml = "";
		for(var i = 0; i < length; i++) {
			strHtml += '<div class="mui-slider-item">'
			strHtml += '<div style="margin-top: 3rem;">'
			strHtml += '<span><img src=' + nanxingList[i].image + ' alt="" /></span>'
			strHtml += '</div>'
			strHtml += '<div class="mui-detail-information">'
			strHtml += '<h4>图片简介</h4>'
			strHtml += '<p>' + nanxingList[i].intro + '</p>'
			strHtml += '</div>'
			strHtml += '<div class="mui-detail-information">'
			strHtml += '<h4>图片描述</h4>'
			strHtml += '<p>' + nanxingList[i].descrip + '</p>'
			strHtml += '</div>'
			strHtml += '</div>'

			$(".mui-slider-group").append(strHtml);
			strHtml = "";
		}

	})
}