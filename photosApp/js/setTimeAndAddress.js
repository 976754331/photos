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