function loadImage(typeId) {
	var types = {
		typeId: typeId
	};
	simpAjax(Url + "/picture/list.do", types, function(result) {
		if(result.rtn_code == 0) {
			var pics = result.data;
			var strHtml = "";
			var timeHtml = "";
			if(pics.length==0){
				strHtml += `<div class="mui-slider-item">
				<div style="margin-top: 3rem;margin-bottom: 1rem;">
				<span><h2>暂无图片</h2></span>
				</div>	
				<button class="toUpload"><h4>点我上传图片</h4></button>				
				</div>`				
				$(".mui-slider-group").append(strHtml);
				strHtml = "";
			}else{
				for(var i = 0; i < pics.length; i++) {
					if(pics[i].intro==null){pics[i].intro="暂无简介"}else{pics[i].intro}
					if(pics[i].desc==null){pics[i].desc="暂无描述"}else{pics[i].desc}
					if(pics[i].addr==null){pics[i].addr="暂无地点"}else{pics[i].addr}
					if(pics[i].attachment_id==null){pics[i].attachment_id="01854199a7334165b55db9dffabac2a0"}else{pics[i].attachment_id}
					$("#timeAddr").empty();
					strHtml += `<div class="mui-slider-item">
					<div style="margin-top: 3rem;">
					<span><img data-id=${pics[i].pic_id} src="${picSrc}${pics[i].attachment_id}" alt="" /></span>
					</div>
					<form >
					<div class="mui-detail-information">
					<h4>图片简介</h4>
					<textarea>${pics[i].intro}</textarea>
					</div>
					<div class="mui-detail-information">
					<h4>图片描述</h4>
					<textarea>${pics[i].desc} </textarea>
					</div>					
					<div  style="position: relative;margin-bottom:1rem">
					<span><h4>时间：</h4> <input type="text" value=${pics[i].shoot_date}  style="height:1.9rem; width:13rem;position: absolute;top: -0.4rem; left:3.2rem"/> </span> 	
					</div>
					<div  style="position: relative;margin-top:1rem">
					<span><h4>地点：</h4> <input type="text" value=${pics[i].addr}  style="height:1.9rem; width:13rem;position: absolute;top: -0.4rem; left:3.2rem"/> </span> 	
					</div>
					<div style="margin:1rem">
					<input type="button" value="修改" class="modifyInfo">
					<input type="button" value="上传" class="toUpload">
					</div>
					</form>
					
					</div>`
					$(".mui-slider-group").append(strHtml);
					strHtml = "";
				}
			}
			

		} else {
			mui.toast(result.msg);
		}
	})

}

//页面加载完成事件
$(function(){
	
	//修改页面信息
	mui('.mui-slider-group').on('tap', '.modifyInfo', function(e) {
		var divs = $(this).parent().parent().children();
		var intro = $(divs[0]).children('textarea').val();
		var desc = $(divs[1]).children('textarea').val();
		var time = $(divs[2]).children().children('input').val();
		var attr = $(divs[3]).children().children('input').val();
		
	})
	
	//上传图片按钮
	mui('.mui-slider-group').on('tap', '.toUpload', function(e) {
		var popHtml = `
				<div class="mui-input-group" style="margin:0;padding:0;position: relative;">
					<div class="mui-input-row mui-radio mui-left" style="margin:0;padding:0;">
						<label style="position: absolute;top: -0.14rem;left:-1rem">从相册中选取照片</label>
						<input name="radio1" type="radio" value="0">
					</div>
					<div class="mui-input-row mui-radio mui-left" style="margin:0;padding:0;">
						<label style="position: absolute;top: -0.14rem;left:-2.5rem">拍照上传</label>
						<input name="radio1" type="radio" checked value="1">
					</div>					
				</div>
			`;
		
		mui.confirm(popHtml, '提示',  function(e) {
			if (e.index == 1) {
	            //确认选择
	            var getRadio = $(".mui-input-group input:checked").val();
	            if(getRadio == 0){ //从相册中选取照片
		            plus.gallery.pick(function(a) {
			        plus.io.resolveLocalFileSystemURL(a, function(entry) {
			            plus.io.resolveLocalFileSystemURL("_doc/", function(root) {
			                root.getFile("head.png", {}, function(file) {
			                      //文件已存在
			                      file.remove(function() {			                          
			                          entry.copyTo(root, "head.png", function(e) {
			                              var e = e.fullPath + "?version=" + new Date().getTime();
			                             uploadHead(e); /*上传图片*/
			                              //变更大的图预览的src
			                              //目前只有一张图暂且这样处理，后续需要用标准组件实现
			                          }, function(e) {
			                              console.log("copy img file:" + e.message);
			                          });
			                      }, function() {
			                          console.log("删除" + e.message)
			                      });
			                  }, function() {
			                      //文件不存在
			                      entry.copyTo(root, "head.png", function(e) {
			                          var path = e.fullPath + "?version=" + new Date().getTime();
			                          uploadhead(path); /*上传图片*/
			                      }, function(e) {
			                          console.log("copy img file:" + e.message);
			                      });
			                  });
			              }, function(e) {
			                  console.log("get _www folder fail");
			              })
			          }, function(e) {
			              console.log("读取拍照文件错误" + e.message);
			          })
			      }, function(a) {}, {
			          filter: "image"
			      })
	          }else{ //拍照上传
	          	  var c = plus.camera.getCamera();
			      c.captrueImage(function(e) {
			          plus.io.resolveLocalFileSystemURL(e, function(entry) {
			              var s = entry.toLocalURL() + "?version=" + new Date().getTime();
			              uploadHead(s); //上传图片
			          }, function(e) {
			              console.log("读取拍照文件错误" + e.message)
			          })
			      }, function(s) {
			          console.log("error" + s);
			      }, {
			          filname: "_doc/head.png"
			      })
	          }
	                     
	        } else {
	            //取消
	        }
		})
	})
	
})

//上传头像图片
 function uploadHead(imgPath) {
      var mainImage = document.getElementById("headimg");
      mainImage.src = imgPath;
  
      var image = new Image();
      image.src = imgPath;
      image.onload = function() {
          var imgData = getBase64Image(image);
  
          $.ajax({
              type: "post",
              data: imgData,
              url: "http://10.8.165.31/ceshi/reuserimg.php",
             success: function(data) {
                 console.log("ajax成功")
                 console.log(data);
             },
             error: function() {
                 console.log("ajax失败")
            }
         });
     }
 }
 
 
 //转换64
 function getBase64Image(img) {
     var canvas = document.createDocument("canvas");
     var width = img.width;
     var height = img.height;
 
     if (width > height) {
         if (width > 100) {
             height = Math.round(height *= 100 / width);
             width = 100;
         }
     } else {
         if (height > 100) {
             width = Math.round(width *= 100 / height);
             height = 100;
         }
     }
 
     canvas.width = width;
     canvas.height = height;
 
     var ctx = canvas.getContext("2d");
     ctx.drawImage(img, 0, 0, width, height); /*绘图*/
     var dataURL = canvas.toDataURL("image/png", 0.8);
     return dataURL.replace("data:image/png;base64,", "");
 
 }
