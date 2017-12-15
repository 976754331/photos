//页面加载完事件
$(function() {
	//删除一级分类
	mui('.mui-table-view').on('tap', '.deleteFirst', function(e) {
		e.stopPropagation();
		var typeId = $(this).parent().attr("data-id");
		alert(typeId);
	    mui.confirm('确认要删除此分类吗？', '提示',  function(e) {
	        if (e.index == 1) {
	            //确认删除
	            simpAjax(Url + "/type/deleteFirst.do", {typeId:typeId}, function(result) {
					if(result.rtn_code == 0) {
						mui.toast(result.msg);
						location.reload();
					} else {
						mui.toast(result.msg);
					}
				})         
	        } else {
	            //取消
	        }
	    })		
	})
	
	//删除二级分类
	//mui-table-view secondDelete
	mui('.mui-table-view').on('tap', '.secondDelete', function(e) {
		e.stopPropagation();
		var typeId = $(this).parent().attr("data-id");
	    mui.confirm('确认要删除此分类吗？', '提示',  function(e) {
	        if (e.index == 1) {
	            //确认删除
	            simpAjax(Url + "/type/deleteSecond.do", {typeId:typeId}, function(result) {
					if(result.rtn_code == 0) {
						mui.toast(result.msg);
						location.reload();
					} else {
						mui.toast(result.msg);
					}
				})         
	        } else {
	            //取消
	        }
	    })		
	})
	
	//创建二级分类
	mui('.mui-table-view').on('tap', '.createSecond', function(e) {
		e.stopPropagation();
		var parentId = $(this).parent().attr("data-id");
		var secondType = prompt("请输入分类名称");
		if(secondType == "") {
			alert("请输入正确的分类名称");
		}else{
			if(secondType == null) {		
				//点击取消
			} else {
				var params = {parentId:parentId,typeName:secondType}
				simpAjax(Url + "/type/insertSecond.do", params, function(result) {
					if(result.rtn_code == 0) {
						btnHtml = createSecondType(secondType, result.data.typeId)
						$(obj).parent().append(btnHtml);
						btnHtml = "";
						mui.toast("创建成功");	
						window.location.reload();
					} else {
						mui.toast(result.data.msg);
					}
				})				
			}
		}		
	})
	
	//创建一级分类	
	document.getElementById("addFirstType").onclick = function() {
		var liHtml = "";
		var firstType = prompt("请输入分类名称");
		if(firstType == "") {
			alert("请输入正确的分类名称");
		}else{
			if(firstType == null) {		
				//点击取消
			} else {
				var params = {typeName:firstType}
				simpAjax(Url + "/type/insertFirst.do", params, function(result) {
					if(result.rtn_code == 0) {
						liHtml = createFirstType(firstType, result.data.typeId);
						$("#mainUl").append(liHtml);
						liHtml = "";
						mui.toast("创建成功");
						
					} else {
						mui.toast(result.data.msg);
					}
				})				
			}
		}
		
	}
	
	//上传本地文件点击事件
	document.getElementById("diskBtn").onclick = function() {
		var diskPath = prompt("请输入本地文件磁盘路径，根路径哦", "比如F:/test");
		if(diskPath == "比如F:/test" || diskPath == "") {
			alert("请输入正确的文件路径");
		} else {
			if(diskPath == null) {
				//点击取消
			} else {
				var params = {dirPath:diskPath,userId:localStorage.getItem('userid')};
				simpAjax(Url + "/file/insertPic.do", params, function(result) {
					if(result.rtn_code == 0) {
						if(result.data.msg == "ok"){
							mui.toast("上传成功");
						}else{
							mui.toast(result.data.msg);
						}
					} else {
						mui.toast(result.data.msg);
					}
				})
			}

		}
	};

})

/* -------------------------------------------------------页面加载事件------------------------------------------------------*/

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
				btnHtml = createSecondType(menu[i].type_name, menu[i].type_id)
				menuDiv.append(btnHtml);
				btnHtml = "";
			}

			//加载图片
			mui('.mui-scroll').on('tap', '.secondButton', function(e) {
				e.stopPropagation();
				var typeId = $(this).parent().attr("data-id");
				var btnName = $(this).text();
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
				
				liHtml = createFirstType(menu[i].type_name, menu[i].type_id);
				$("#mainUl").append(liHtml);
				liHtml = "";
			}
		} else {
			mui.toast(result.msg);
		}
	})

	
}


//创建一级分类标签
function createFirstType(typeName, typeId){
	var liHtml = "";
	liHtml += '<li class="mui-table-view-cell mui-collapse" style="position:relative;" data-id=' + typeId + ' >'
	liHtml += '<a style = "display: inline;" >' + typeName + '</a>'
	liHtml += '	<span style="position:absolute; left:16.5rem;" class="createSecond" > '
	liHtml += '	<img  src="../../images/icon/addType.jpg" style="width: 1.8rem;height:1.8rem;position:absolute; margin-top:-0.1rem;margin-left:0.5rem;"/> '
	liHtml += '	</span>'
	liHtml += '	<span style="position:absolute; left:18.5rem; " class="deleteFirst" > '
	liHtml += '	<img  src="../../images/icon/delete.png" style="width: 1.7rem;height:1.7rem;position:absolute; margin-top:-0.0rem;margin-left:0.5rem;"/> '
	liHtml += '	</span>'
	liHtml += '<div class="mui-collapse-content">'
	liHtml += '<div class="mui-scroll-wrapper  mui-segmented-control mui-slider-indicator mui-segmented-control-inverted" >'
	liHtml += '<ul class="mui-scroll" >'

	liHtml += '</ul>'
	liHtml += '</div>'
	liHtml += '</div>'
	liHtml += '</li>'
	return liHtml;
}

var classArr = ["mui-button-row", "mui-btn-green", "mui-btn-blue", "mui-btn-danger", "mui-btn-grey", "mui-btn-yellow", ];
//创建二级分类按钮   onclick="deleteSecond(event)"
function createSecondType(typeName, typeId){
	var classBtn = classArr[parseInt(Math.random() * 6 + 1)];
	var btnHtml = "";
	btnHtml = `<li data-id=${typeId} class="mui-control-item secondTypeLi" style="position: relative; top:-0.6rem;height: 2rem;">
	<button style="height: 100%;" class="${classBtn} secondButton">${typeName}
	 </button>
	 <span style = "padding:0; margin:0" class = "secondDelete" >
	 <img  src="../../images/icon/delete.png" style="width: 1.5rem;height:1.5rem;position: relative; top:0.1rem;left:-0.3rem;" /> 
	 </span>
	 </li>`;
	return btnHtml;
}