function loadImage(typeId) {
	var types = {
		typeId: typeId
	};
	simpAjax(Url + "/picture/list.do", types, function(result) {
		if(result.rtn_code == 0) {
			var pics = result.data;
			var strHtml = "";
			var timeHtml = "";
			for(var i = 0; i < pics.length; i++) {
				$("#timeAddr").empty();
				strHtml += '<div class="mui-slider-item">'
				strHtml += '<div style="margin-top: 3rem;">'
				strHtml += '<span><img data-id='+pics[i].pic_id+'  src="'+picSrc+pics[i].attachment_id+'" alt="" /></span>'
				strHtml += '</div>'
				strHtml += '<div class="mui-detail-information">'
				strHtml += '<h4>图片简介</h4>'
				strHtml += '<p>' + pics[i].intro + '</p>'
				strHtml += '</div>'
				strHtml += '<div class="mui-detail-information">'
				strHtml += '<h4>图片描述</h4>'
				strHtml += '<p>' + pics[i].desc + '</p>'
				strHtml += '</div>'
				strHtml += '</div>'
				
				timeHtml += '<div style="float: left;width: 57%;line-height:1.7rem;">'
				timeHtml += '<p>时间： ' + pics[i].shoot_date + '</p>'
				timeHtml += '</div>'
				timeHtml += '<div style="float: left;width: 43%;line-height:1.7rem;">'
				timeHtml += '<p>地点： ' + pics[i].addr + '</p>'
				timeHtml += '</div>'
				
				$("#timeAddr").append(timeHtml);
				$(".mui-slider-group").append(strHtml);
				strHtml = "";
				timeHtml = "";
			}

		} else {
			mui.toast(result.msg);
		}
	})

	/*
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
	
	function setTimeAndAddress(type, str) {
	$.getJSON("../../js/json/timeAddr.json", function(data) {
		var strHtml = "";
		var timeAddr = data.data[type][str];
		strHtml += '<div style="float: left;width: 37%;line-height:2rem;">'
		strHtml += '<p>时间： ' + timeAddr.date + '</p>'
		strHtml += '</div>'
        strHtml += '<div style="float: left;width: 63%;line-height:2rem;">'
		strHtml += '<p>地点： ' + timeAddr.address + '</p>'
		strHtml += '</div>'
		$("#timeAddr").append(strHtml);
	})
}
	
	*/
}